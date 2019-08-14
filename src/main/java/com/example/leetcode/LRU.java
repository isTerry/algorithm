package com.example.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRU {
    public static void main(String[] args){
//        LRUByLinkedListAndHashMap.insertOrUpdatePage(new Page(1,"a"));
//        LRUByLinkedListAndHashMap.insertOrUpdatePage(new Page(2,"b"));
//        LRUByLinkedListAndHashMap.insertOrUpdatePage(new Page(3,"c"));
//        LRUByLinkedListAndHashMap.getPage(1);
//        LRUByLinkedListAndHashMap.insertOrUpdatePage(new Page(4,"d"));
//        LRUByLinkedListAndHashMap.insertOrUpdatePage(new Page(3,"f"));

//        LRUByLinkedHashMap.insertOrUpdatePage(new Page(1,"a"));
//        LRUByLinkedHashMap.insertOrUpdatePage(new Page(2,"b"));
//        LRUByLinkedHashMap.insertOrUpdatePage(new Page(3,"c"));
//        LRUByLinkedHashMap.getPage(1);
//        LRUByLinkedHashMap.insertOrUpdatePage(new Page(4,"d"));
//        LRUByLinkedHashMap.insertOrUpdatePage(new Page(3,"f"));

        LRUByExtendsLinkedHashMap lruByExtendsLinkedHashMap = new LRUByExtendsLinkedHashMap(3);
        lruByExtendsLinkedHashMap.put(1,new Page(1,"a"));
        lruByExtendsLinkedHashMap.put(2,new Page(2,"b"));
        lruByExtendsLinkedHashMap.put(3,new Page(3,"c"));
        lruByExtendsLinkedHashMap.get(1);
        lruByExtendsLinkedHashMap.put(4,new Page(4,"d"));
        lruByExtendsLinkedHashMap.put(3,new Page(3,"f"));

    }
}

/*
总结：在链表中新增数据时，首先判断数据是否是老数据，是的话把数据调整到链表尾部，以保证最新数据一直在尾部。
不是的话在数据加入之后，判断是否需要删除最老的数据，需要则删除。
通过前面的分析，要真正实现LRU算法需要重写removeEldestEntry方法。
 */
class LRUByExtendsLinkedHashMap<K,V> extends LinkedHashMap<K,V>{
    private int maxSize;

    //设置访问顺序
    public LRUByExtendsLinkedHashMap(int maxSize){
        this(maxSize,16,0.75f,true);
    }
    //设置阈值
    public LRUByExtendsLinkedHashMap(int maxSize, int initialCapacity, float loadFactor, boolean accessOrder){
        super(initialCapacity, loadFactor, accessOrder);
        this.maxSize = maxSize;
    }
    //设置移除最老Entry的时机
    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size()>maxSize;
    }
}

/*
LinkedHashMap是继承于HashMap，是基于HashMap和双向链表来实现的。
HashMap无序；LinkedHashMap有序，可分为插入顺序和访问顺序两种。
如果是访问顺序，那put和get操作已存在的Entry时，都会把Entry移动到双向链表的表尾(其实是先删除再插入)。
LinkedHashMap存取数据，还是跟HashMap一样使用的Entry[]的方式，双向链表只是为了保证顺序。
LinkedHashMap是线程不安全的。
transient LinkedHashMap.Entry<K,V> head;//链表头
transient LinkedHashMap.Entry<K,V> tail;//链表尾
final boolean accessOrder;//访问顺序
static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;//每个Entry在链表里有前后
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);//Node<K,V> next对应哈希Map冲突链表里节点的下一个节点
        }
    }
 */
class LRUByLinkedHashMap{
    static LinkedHashMap<Integer,Page> pageLinkedMap = new LinkedHashMap<>(2^4,0.75f,true);
    static final int CAPACITY = 3;

    public static void insertOrUpdatePage(Page page){
        if(pageLinkedMap.size()==CAPACITY){
            deletePage();
        }
        pageLinkedMap.put(page.id,page);//插入或者修改
        System.out.println(pageLinkedMap.toString());
    }

    public static Page getPage(int id){
        Page page = pageLinkedMap.get(id);
        System.out.println(pageLinkedMap.toString());
        return page;
    }

    public static void deletePage(){
        pageLinkedMap.remove(pageLinkedMap.keySet().iterator().next());
        System.out.println(pageLinkedMap.toString());
    }
}

class LRUByLinkedListAndHashMap{
    static HashMap<Integer,Page> pageHashMap = new HashMap<>();
    static LinkedList<Page> pageList = new LinkedList<>();
    static final int CAPACITY = 3;

    //若不存在插入，判断容量，满了先删除，再加入链表尾部、加入map
    // 若存在修改，访问该节点会移到尾部，并修改值
    public static void insertOrUpdatePage(Page page){
        if(!pageHashMap.containsKey(page.id)){//插入
            if(pageList.size()==CAPACITY){
                deletePage();
            }
            pageHashMap.put(page.id,page);
            pageList.offer(page);
        }else {//修改
            Page page1 = getPage(page.id);
            page1.value = page.value;
        }
        System.out.println(pageList.toString());
    }

    //获取了某页，调整到队尾
    public static Page getPage(int id){
        Page page = pageHashMap.get(id);
        pageList.remove(page);
        pageList.offer(page);
        System.out.println(pageList.toString());
        return page;
    }

    //删除队首一页，同时处理list和map
    public static void deletePage(){
        pageHashMap.remove(pageList.poll());
        System.out.println(pageList.toString());
    }
}

class Page{
    int id;
    String value;

    Page(int id, String value){
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return id+value;
    }
}
