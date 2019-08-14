package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
ThreadLocal：
不是用来解决多线程环境下的共享变量问题，而是用来提供“线程内部的共享变量”
变量在线程的生命周期内起作用，可以减少同一个线程内"多个函数之间公共变量"的传递复杂度。

get()方法：获取与当前线程关联的ThreadLocal值。
set(T value)方法：设置与当前线程关联的ThreadLocal值。
initialValue()方法：设置与当前线程关联的ThreadLocal初始值。
当调用get()方法的时候，若是与当前线程关联的ThreadLocal值已经被设置过，则不会调用initialValue()方法；
否则，会调用initialValue()方法来进行初始值的设置。
initialValue()方法是protected类型的，显然是建议在子类重载该函数的，以指定初始值
remove()方法：将与当前线程关联的ThreadLocal值删除。

1调用Thread.currentThread()获取当前线程对象t；

2根据当前线程对象，调用getMap(Thread)获取线程对应的ThreadLocalMap对象：t.threadLocals;
threadLocals是Thread类的成员变量，初始化为null：
ThreadLocal.ThreadLocalMap threadLocals = null;

4如果获取的map不为空，则在map中以ThreadLocal的引用作为key来在map中获取对应的value e
若e不为null，则返回e中存储的value值

5否则调用setInitialValue()方法，对线程的ThreadLocalMap对象进行初始化操作，
ThreadLocalMap对象的key为ThreadLocal对象，value为initialValue()方法的返回值。


如何确保线程安全？？？
Thread、ThreadLocal、ThreadLocalMap
由于Thread、ThreadLocal为“多对多关系”，需要维护多个map，
1可以每个ThreadLocal一个ThreadLocalMap记录所有“来访问的”Thread以及值，
但涉及多线程访问同一个 Map，锁的问题。
！！！2也可以每个Thread一个ThreadLocalMap记录所有“访问过的”ThreadLocal以及值，
不涉及并发不需要锁。

ThreadLocal的实现离不开ThreadLocalMap类，ThreadLocalMap类是ThreadLocal的静态内部类。
每个Thread维护一个ThreadLocalMap映射表，Entry包括key和value，
这个映射表的key是ThreadLocal实例本身，每个ThreadLocal有一个全局唯一的hashcode，
调用static的nextHashCode()方法通过static的AtomicInteger原子类生成，
value是真正需要存储的Object。
强引用链如下：
CurrentThread Ref -> Thread -> ThreadLocalMap -> Entry -> value
ThreadLocalMap：To help deal with
     * very large and long-lived usages, the hash table entries use
     * WeakReferences for keys. However, since reference queues are not
     * used, stale entries are guaranteed to be removed only when
     * the table starts running out of space.
ThreadLocalMap的Entry是使用ThreadLocal的弱引用作为Key的，弱引用对象在Java虚拟机进行垃圾回收时，就会被释放
弱引用链(虚线)如下：
ThreadLocal Ref -> ThreadLocal <- - -key

为什么采用弱引用？？？
因为要获取到value需要ThreadLocal作为key变量，
但如果ThreadLocal对象再被强引用，则多出一个强引用，线程结束之前，无法回收“ThreadLocal对象”。

为什么有内存泄漏问题、如何避免？？？
1.弱引用解决“ThreadLocal对象”无法回收的问题
2.解决key为null的“value对象”无法回收的问题
考虑：如果一个ThreadLocal没有外部关联的强引用，那么在虚拟机进行垃圾回收时，这个ThreadLocal会被回收，
这样，ThreadLocalMap中就会出现key为null的Entry，这些key对应的value也就再无法访问，
但是value却存在一条从Current Thread过来的强引用链。因此只有当Current Thread销毁时，value才能得到释放。
只要这个线程对象被gc回收，那些key为null对应的value也会被回收，这样也没什么问题，
但在线程对象不被回收的情况下，比如使用线程池的时候，核心线程是一直在运行的，线程对象不会回收，
若是在这样的线程中存在上述现象，就可能出现内存泄露的问题。

ThreadLocalMap 的 set()/getEntry()方法都会检查键为 null 的 Entry，回收其对象的值
以及 Entry 对象本身从而防止内存泄漏。
但是如果我们既不需要添加value，也不需要获取value，那还是有可能产生内存泄漏的。
所以很多情况下需要使用者手动调用ThreadLocal的remove()函数，手动删除不再需要的ThreadLocal，防止内存泄露。
若对应的key存在，remove()方法也会调用expungeStaleEntry(int)方法，来删除对应的Entry和value。

避免改变集合元素的key：
其实，最好的方式就是将ThreadLocal变量定义成private static的，这样的话ThreadLocal的生命周期就更长，
由于一直存在ThreadLocal的强引用，所以ThreadLocal也就不会被回收，
也就能保证任何时候都能根据ThreadLocal的弱引用访问到Entry的value值，然后remove它，可以防止内存泄露。
 */
public class ThreadLocalll {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Task(1000));
        executorService.execute(new Task(3000));
        executorService.shutdown();
    }

    public static void multiThreadsAccessThreadLocal(){
        threadLocal.get();
        System.out.println(Thread.currentThread().getName()+" enter");
    }

    public static void multiThreadsExitThreadLocal(){
        long cost = System.currentTimeMillis() - threadLocal.get();
        System.out.println(Thread.currentThread().getName()+" exit cost:"+cost);
    }
}

class Task implements Runnable{
    private long time;
    Task(long time){
        this.time = time;
    }

    @Override
    public void run() {
        ThreadLocalll.multiThreadsAccessThreadLocal();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadLocalll.multiThreadsExitThreadLocal();
    }
}
