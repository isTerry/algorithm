package com.example.leetcode;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Concurrency {
    private static int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws Exception {
//        new ReentrantLock();
//        new CountDownLatchRace().race();
//        new CyclicBarrierCooperate().cooperate();

//        new ProducerAndConsumerByWaitAndNotifyAll().startPAndC();

//        new ProducerAndConsumerByAwaitAndSignalAll().startPAndC();

//        new ProducerAndConsumerByBlockingQueue().startPAndC();

//        new ReadAndWriteLock().moreReadThread();

//        new CallableWithFutreTask().runAndget();


    }

    public static void initValue(){
        int[] num09 = new int[10];
        System.out.println(num09[0]);

        int cost = 0;
        System.out.println(cost);
    }

    public static void chatAndInt(){
        System.out.println(Integer.valueOf('5'));
        System.out.println(Integer.valueOf('5'+""));
        System.out.println(Integer.valueOf("5"));
        System.out.println('5');
    }

    public synchronized static void increment(){
        for(int i=0;i<1000;i++){
            count++;
        }
    }

    public static void safeIncrement(){
        for(int i=0;i<1000;i++){
            atomicInteger.incrementAndGet();
        }
    }
}

class HashMapNull{
    public static void printNull(){
        Map<String,String> map = new HashMap<>();
        System.out.println("init map.size:"+map.size());
        System.out.println("init map.containsKey(null):"+map.containsKey(null));

        map.put("ss",null);
        map.put(null,"pp");

        System.out.println("afterPut ss and null, map.size:"+map.size());
        System.out.println("afterPut ss and null, map.containsKey(null):"+map.containsKey(null));
        System.out.println("afterPut ss and null, map.containsKey(ss):"+map.containsKey("ss"));
        System.out.println("afterPut ss and null, map.containsKey(aa):"+map.containsKey("aa"));

        System.out.println("afterPut ss and null, map.get(null):"+map.get(null));
        System.out.println("afterPut ss and null, map.get(ss):"+map.get("ss"));
    }
}

/*
Future如何获得结果？
是一个可取消的异步计算，可选择进入等待队列或者超时获取结果，底层使用LockSupport.park()，可被中断。
底层基于java内存模型（线程缓存、主内存）及线程间通信（共享内存、信号量、锁）。

之前是基于AbstractQueuedSynchronizer
并现在用一个state状态，以及CAS去无锁修改，维护等待线程的一个WaitNode链表
a "state" field updated via CAS to track completion, along
 with a simple Treiber stack to hold waiting threads.
 可能的状态有：
private static final int NEW          = 0;
private static final int COMPLETING   = 1;
private static final int NORMAL       = 2;
private static final int EXCEPTIONAL  = 3;
private static final int CANCELLED    = 4;
private static final int INTERRUPTING = 5;
private static final int INTERRUPTED  = 6;

可能的流程有（每个流程结束都会调用finishCompletion();把结果返回给等待线程）：
 * Possible state transitions:
     * NEW -> COMPLETING -> NORMAL------------------- 正常执行结束的流程，执行结果已保存
     * NEW -> COMPLETING -> EXCEPTIONAL------------------- 执行过程中出现异常的流程，异常信息已经保
     * NEW -> CANCELLED-------------------被取消，即调用了cancel(false)，不要求中断正在执行的线程
     * NEW -> INTERRUPTING -> INTERRUPTED-------------------被中断，即调用了cancel(true)，并要求中断线程的执行
 */
class CallableWithFutreTask implements Callable<String>{
    public void runAndget() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        FutureTask<String> future = new FutureTask<>(new CallableWithFutreTask());
//        executorService.submit(future);

        Future future = executorService.submit(this);
        Thread.sleep(1000);
        if(future.isDone()){
            System.out.println(future.get());
        }
        System.out.println("end------but FixedThreadPool not end");
    }

    @Override
    public String call() throws Exception {
        return "Terry";
    }

    //FutureTask实现了Runnable,run()方法内部其实调用callable.call()方法
    //private Callable<V> callable;为内部维护的Callable对象，可能由Runnable+result(null)转换而来
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        FutureTask futureTask = new FutureTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("ddddddd");
            }
        },"ppppppp");

        executorService.execute(futureTask);//void
        System.out.println(futureTask.get());//"ppppppp"
        executorService.shutdown();
    }
}

class ReadAndWriteLock{
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void moreReadThread(){
        new Thread(()->{
            read();
        }).start();
        new Thread(()->{
            read();
        }).start();
    }

    private void read(){
        reentrantReadWriteLock.readLock().lock();
        try{
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis()-start<1000){
                System.out.println(Thread.currentThread().getName()+" 正在读");
            }
            System.out.println(Thread.currentThread().getName()+" 读完毕");
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}


class Interruptbly{
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void runInterruptblyByTryLock() throws InterruptedException {
        reentrantLock.lock();
        Thread thread = new Thread(() ->{
//            reentrantLock.tryLock()
            try {
                if(reentrantLock.tryLock(10000,TimeUnit.MILLISECONDS)){
                    try{
                        System.out.println(Thread.currentThread().isInterrupted());
                    }finally {
                        reentrantLock.unlock();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("eeeeeeeeee");
        });
        thread.start();
//        Thread.sleep(1000);
        TimeUnit.MILLISECONDS.sleep(1000);
        thread.interrupt();
        System.out.println("ssssssssssss");
        Thread.sleep(100000);
    }

    public void runInterruptblyByLockInterruptibly() throws InterruptedException {
        reentrantLock.lock();
        Thread thread = new Thread(() ->{
            try {
                reentrantLock.lockInterruptibly();
                try {
                    System.out.println("aaaaaaaaaa");
                }finally {
                    reentrantLock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            System.out.println("eeeeeeeeee");
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("ssssssssssss");
        Thread.sleep(100000);

    }
}

class NotInterruptbly{
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void runNotInterruptblyByLock() throws InterruptedException {
        reentrantLock.lock();
        Thread thread = new Thread(() ->{
            reentrantLock.lock();
            try{
                System.out.println(Thread.currentThread().isInterrupted());
            }finally {
                reentrantLock.unlock();
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        System.out.println("ssssssssssss");
        Thread.sleep(100000);
    }

    public void runNotInterruptblyBySynchronized() throws InterruptedException {
        synchronized (reentrantLock){
            Thread thread = new Thread(() ->{
                synchronized (reentrantLock){
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            });
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("ssssssssssss");
            Thread.sleep(100000);
        }
    }
}


class Increment implements Runnable{
    @Override
    public void run() {
        Concurrency.increment();
    }
}

class SafeIncrement implements Runnable{
    @Override
    public void run() {
        Concurrency.safeIncrement();
    }
}

class  Singleton{
    private volatile static Singleton singleton;

    private Singleton(){}

    public static Singleton getSingleton(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    return new Singleton();
                }
            }
        }
        return singleton;
    }
}

enum EasySingleton implements Runnable{
    SINGLETON;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        atomicInteger.incrementAndGet();
    }

    public int getInt(){
        return atomicInteger.get();
    }
}

class StaticLazySingleton{
    static class StaticLazySingletonHolder{
        private static final StaticLazySingleton lazySingleton = new StaticLazySingleton();
    }

    private StaticLazySingleton(){}

    public static StaticLazySingleton getInstance(){
        return StaticLazySingletonHolder.lazySingleton;
    }
}

class NotLazySingleton{
    private static final NotLazySingleton notLazySingleton = new NotLazySingleton();

    private NotLazySingleton(){}

    public static NotLazySingleton getInstance(){
        return notLazySingleton;
    }
}

class CountDownLatchRace{
    private int runnerCount = 5;
    private CountDownLatch beginLatch = new CountDownLatch(1);
    private CountDownLatch endLatch = new CountDownLatch(runnerCount);
    private AtomicInteger ranking = new AtomicInteger();

    public void race(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=1;i<=runnerCount;i++){
            executorService.execute(() -> {
                try {
                    beginLatch.await();
                    Thread.sleep(1000);
                    System.out.println("参赛选手跑道"+Thread.currentThread().getName()+" 排名："+ranking.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    endLatch.countDown();
                }
            });
        }
        beginLatch.countDown();
        System.out.println("比赛开始，参赛选手个数："+runnerCount);
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("比赛结束！");
            executorService.shutdown();
        }
    }
}

class CyclicBarrierCooperate{
    private int workerNum = 3;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(workerNum);
    private CountDownLatch endLatch = new CountDownLatch(workerNum);

    public void cooperate() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<workerNum;i++){
            executorService.execute(() ->{
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                    cyclicBarrier.await();
                    System.out.println("Already"+Thread.currentThread().getName());
                    endLatch.countDown();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        endLatch.await();
        System.out.println("End");
        executorService.shutdown();
    }
}

class ProducerAndConsumerByBlockingQueue{
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    public void startPAndC() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            executorService.execute(new Producer());
            executorService.execute(new Consumer());
        }
        Thread.sleep(3000);
        executorService.shutdownNow();
        System.out.println("++++++++++++++");
    }

    class Producer implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    arrayBlockingQueue.put(1);
                    System.out.println(Thread.currentThread().getName()+" put");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            TAG:
            while (!Thread.currentThread().isInterrupted()){
                try {
                    arrayBlockingQueue.take();
                    System.out.println(Thread.currentThread().getName()+" take");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }
}


class ProducerAndConsumerByWaitAndNotifyAll{
    private static final int maxSize = 5;
    private Queue<Cargo> queue = new LinkedList<Cargo>();

    public void startPAndC() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            executorService.execute(new Producer());
            executorService.execute(new Consumer());
        }
        Thread.sleep(3000);
        executorService.shutdownNow();
        System.out.println("++++++++++++++");
    }

    class Producer implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                synchronized (queue){
                    try{
                        while(!Thread.currentThread().isInterrupted()&&queue.size()==maxSize){//这里使用if的话，就会存在睡眠前后wait条件变化造成程序错误的问题
                            System.out.println(Thread.currentThread().getName()+" queue full:wait");
                            queue.wait();
                        }
                        queue.add(new Cargo(1,"terry"));
                        System.out.println(Thread.currentThread().getName()+" Cargo add");
                        queue.notifyAll();
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();//或者break tag到最外层循环
                    }
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            TAG:
            while (!Thread.currentThread().isInterrupted()){
                synchronized (queue){
                    try{
                        while(!Thread.currentThread().isInterrupted()&&queue.size()==0){//这里使用if的话，就会存在睡眠前后wait条件变化造成程序错误的问题
                            System.out.println(Thread.currentThread().getName()+" queue empty:wait");
                            queue.wait();
                        }
                        System.out.println(Thread.currentThread().getName()+" Cargo remove:"+queue.remove());
                        queue.notifyAll();
                    }catch (InterruptedException e) {
                        break TAG;//或者自己再次设置中断
                    }
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }

    class Cargo{
        private int id;
        private String name;

        private Cargo(int id,String name){
            this.id = id;
            this.name = name;
        }
    }
}

class ProducerAndConsumerByAwaitAndSignalAll{
    private final int maxSize = 5;
    private Queue<Cargo> queue = new LinkedList<Cargo>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();

    public void startPAndC() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<5;i++){
            executorService.execute(new Producer());
            executorService.execute(new Consumer());
        }
        Thread.sleep(3000);
        executorService.shutdownNow();
        System.out.println("++++++++++++++");
    }

    class Producer implements Runnable{
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                lock.lock();
                try{
                    while(!Thread.currentThread().isInterrupted()&&queue.size()==maxSize){//这里使用if的话，就会存在睡眠前后wait条件变化造成程序错误的问题
                        System.out.println(Thread.currentThread().getName()+" queue full:wait");
                        full.await();
                    }
                    if(queue.size()!=maxSize){
                        queue.add(new Cargo(1,"terry"));
                        System.out.println(Thread.currentThread().getName()+" Cargo add");
                    }
                    empty.signalAll();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();//或者break tag到最外层循环
                }finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            TAG:
            while (!Thread.currentThread().isInterrupted()){
                lock.lock();
                try{
                    while(!Thread.currentThread().isInterrupted()&&queue.size()==0){//这里使用if的话，就会存在睡眠前后wait条件变化造成程序错误的问题
                        System.out.println(Thread.currentThread().getName()+" queue empty:wait");
                        empty.await();
                    }
                    if(queue.size()!=0){
                        System.out.println(Thread.currentThread().getName()+" Cargo remove:"+queue.remove());
                    }
                    full.signalAll();
                }catch (InterruptedException e) {
                    break TAG;//或者自己再次设置中断
                }finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName()+" out");
        }
    }

    class Cargo{
        private int id;
        private String name;

        private Cargo(int id,String name){
            this.id = id;
            this.name = name;
        }
    }
}