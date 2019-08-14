package com.example.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SortArrayList {
   static char[] charArr = new char[]{'a','b','c','d','e','f'};

    public static void main(String[] args){
        Node[] nodeArr = new Node[6];
        nodeArr[0] = new Node(4,charArr[0]);
        nodeArr[1] = new Node(0,charArr[1]);
        nodeArr[2] = new Node(4,charArr[2]);
        nodeArr[3] = new Node(1,charArr[3]);
        nodeArr[4] = new Node(3,charArr[4]);
        nodeArr[5] = new Node(2,charArr[5]);

        System.out.println(Arrays.asList(nodeArr));
//        Arrays.sort(nodeArr);
//        BubbleSort.bubbleSort(nodeArr);
//        InsertSort.insertSort(nodeArr);
//        ShellSort.shellSort(nodeArr);
//        QuickSort.quickSort(nodeArr);
//        MergeSort.mergeSort(nodeArr);
//        HeapSort.heapSort(nodeArr);
//        System.out.println(Arrays.asList(nodeArr));
//        System.out.println(TopKByHeapSort.findTopKByHeapSort(nodeArr,2));
//        System.out.println(KthMaxByQuickSort.findKthMaxByQuickSort(nodeArr,4));

//        System.out.println(BinarySearch.binarySearchIterate(nodeArr,5));
//        System.out.println(BinarySearch.binarySearchIterate(nodeArr,4));
//        System.out.println(BinarySearch.binarySearchIterate(nodeArr,1));

        System.out.println(BinarySearch.binarySearchRecursive(nodeArr,5));
        System.out.println(BinarySearch.binarySearchRecursive(nodeArr,4));
        System.out.println(BinarySearch.binarySearchRecursive(nodeArr,1));

    }

    //只交换了a、b变量引用的对象，没有影响数组持有的引用
    // 方法退出，a、b变量消失
    public static void swap(Node a,Node b){
        Node tmp = a;
        a = b;
        b = tmp;
    }

    public static void swap(Node[] nodeArr,int i,int j){
        Node tmp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = tmp;
    }

    //按id排序默认升序，char标记排序是否稳定
    static class Node implements Comparable<Node>{
        int id;
        char name;

        Node(int id,char name){
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Node o) {
            return this.id-o.id;
        }

        @Override
        public String toString() {
            return id+String.valueOf(name);
        }
    }
}

class BinarySearch{
    public static Integer binarySearchIterate(SortArrayList.Node[] nodeArr,int value){
        //二分查找前提有序
        Arrays.sort(nodeArr);
        int left = 0,right = nodeArr.length-1;
        int middle;
        while (left<=right){
            middle = (left+right)/2;
            if(nodeArr[middle].id==value){
                return value;
            }else if(nodeArr[middle].id<value){
                left = middle+1;
            }else {
                right = middle-1;
            }
        }
        return null;
    }

    public static Integer binarySearchRecursive(SortArrayList.Node[] nodeArr,int value){
        //二分查找前提有序
        Arrays.sort(nodeArr);
       return binarySearchRecursive(nodeArr,0,nodeArr.length-1,value);
    }

    public static Integer binarySearchRecursive(SortArrayList.Node[] nodeArr,int left,int right,int value){
        if(left>right){
            return null;
        }

        int middle = (left+right)/2;;
        if(nodeArr[middle].id==value){
            return value;
        }else if(nodeArr[middle].id<value){
            return binarySearchRecursive(nodeArr,middle+1,right,value);
        }else {
            return binarySearchRecursive(nodeArr,left,middle-1,value);
        }
    }
}

class JavaMergeSort{
    public static void javaMergeSort(SortArrayList.Node[] nodeArr){
        //全新的数组
//        Object[] obArr = new LinkedList().toArray();
        //指向原数组的list，即对list的操作对Array可见
//        List list = Arrays.asList(nodeArr);
        //全新list
//        List listNew = new LinkedList(Arrays.asList(nodeArr));
        //调用Arrays.sort()
//        Collections.sort(Arrays.asList(nodeArr));
        Arrays.sort(nodeArr);
    }
}

class BubbleSort{
    public static void bubbleSort(SortArrayList.Node[] nodeArr){
        for(int i=0;i<nodeArr.length-1;i++){
            //优化标志位
            boolean sorted = true;
            for(int j=0;j<nodeArr.length-1-i;j++){
                if(nodeArr[j].id>nodeArr[j+1].id){
                    sorted = false;
                    SortArrayList.swap(nodeArr,j,j+1);
                }
            }
            if(sorted){
                return;
            }
        }
    }
}

class InsertSort{
    public static void insertSort(SortArrayList.Node[] nodeArr){
        for(int i =1;i<nodeArr.length;i++){
            SortArrayList.Node nodei = nodeArr[i];
            int j;
            for(j = i-1;j>=0&&nodeArr[j].id>nodei.id;j--){
                nodeArr[j+1] = nodeArr[j];
            }
            nodeArr[j+1]= nodei;
        }
    }
}

//gap取1，等价插入排序
class ShellSort{
    public static void shellSort(SortArrayList.Node[] nodeArr){
        int gap = nodeArr.length/2;
        while (gap>=1){
            for(int i=gap;i<nodeArr.length;i+=1){
                SortArrayList.Node tmp = nodeArr[i];
                int j=i-gap;
                for (;j>=0&&tmp.id<nodeArr[j].id;j-=gap){
                    nodeArr[j+gap] = nodeArr[j];
                }
                nodeArr[j+gap] = tmp;
            }

            gap/=2;
        }
    }
}

class MergeSort{
    public static void mergeSort(SortArrayList.Node[] nodeArr){
        mergeSort(nodeArr,0,nodeArr.length-1);
    }

    public static void mergeSort(SortArrayList.Node[] nodeArr,int start,int end){
        if(start<end){
            int middle = (start+end)/2;
            mergeSort(nodeArr,start,middle);
            mergeSort(nodeArr,middle+1,end);
            mergeArray(nodeArr,start,middle,end);
        }
    }
    public static void mergeArray(SortArrayList.Node[] arr,int start,int middle,int end){
        SortArrayList.Node[] arrC = new SortArrayList.Node[end-start+1];
        int i = start,j = middle+1,k =0;
        while (i<=middle&&j<=end){
            if(arr[i].id<=arr[j].id){
                arrC[k++] = arr[i++];
            }else {
                arrC[k++] = arr[j++];
            }
        }

        while (i<=middle){
            arrC[k++] = arr[i++];
        }
        while (j<=end){
            arrC[k++] = arr[j++];
        }

        System.arraycopy(arrC,0,arr,start,arrC.length);
    }
}

class QuickSort{
    public static void quickSort(SortArrayList.Node[] nodeArr){
        quickSort(nodeArr,0,nodeArr.length-1);
    }

    public static void quickSort(SortArrayList.Node[] nodeArr,int start,int end){
        if(start<end){
            int middle = quickDivision(nodeArr,start,end);
            quickSort(nodeArr,start,middle-1);
            quickSort(nodeArr,middle+1,end);
        }
    }

    public static int quickDivision(SortArrayList.Node[] nodeArr,int start,int end){
        SortArrayList.Node nodeFirst = nodeArr[start];
        while (start<end){
            //nodeFirst坑首先在左端，先右后左
            while (start<end&&nodeArr[end].id>=nodeFirst.id){
                end--;
            }
            //坑换到右边
            nodeArr[start] = nodeArr[end];

            while (start<end&&nodeArr[start].id<=nodeFirst.id){
                start++;
            }
            //坑换回左边
            nodeArr[end] = nodeArr[start];
        }
        //左右相碰
        nodeArr[start] = nodeFirst;
        return start;
    }
}

//数组长度n，建堆从（n/2 - 1）非叶子节点开始
// 已知i则左子2i+1右子2i+2，已知i则父亲（i-1）/2
//示例最大堆,核心就是调整堆的过程
class HeapSort{
    public static void heapSort(SortArrayList.Node[] nodeArr){
        heapCreate(nodeArr);
        for(int i=nodeArr.length-1;i>0;i--){
            SortArrayList.swap(nodeArr,0,i);
            heapAdjust(nodeArr,i-1,0);
        }
    }

    public static void heapAdjust(SortArrayList.Node[] nodeArr, int last, int parent){
        int left = 2*parent+1;
        while (left<=last){
            //如果右子存在，选择左右中最大的
            if(left+1<=last&&nodeArr[left+1].id>nodeArr[left].id){
                left++;
            }
            //如果当前父亲小于最大儿子则交换
            if(nodeArr[parent].id<nodeArr[left].id){
                SortArrayList.swap(nodeArr,parent,left);
                parent = left;
                left = 2*left+1;
            }else {
                break;
            }
        }
    }

    public static void heapCreate(SortArrayList.Node[] nodeArr){
        for(int i=nodeArr.length/2-1;i>=0;i--){
            heapAdjust(nodeArr,nodeArr.length-1,i);
        }
    }
}


//最大堆,topK
class TopKByHeapSort{
    public static List<SortArrayList.Node> findTopKByHeapSort(SortArrayList.Node[] nodeArr,int k){
        List<SortArrayList.Node> list = new LinkedList<>();
        if(k>nodeArr.length){
            return list;
        }
        HeapSort.heapCreate(nodeArr);
        for(int i=nodeArr.length-1;i>=0;i--){
            list.add(nodeArr[0]);
            if(list.size()==k){
                break;
            }
            SortArrayList.swap(nodeArr,0,i);
            HeapSort.heapAdjust(nodeArr,i-1,0);
        }

        return list;
    }
}

//快排，KthMax
class KthMaxByQuickSort{
    public static SortArrayList.Node findKthMaxByQuickSort(SortArrayList.Node[] nodeArr,int k){
        if(k>nodeArr.length){
            return null;
        }
        return findKthMaxByQuickSort(nodeArr,0,nodeArr.length-1,k);
    }

    public static SortArrayList.Node findKthMaxByQuickSort(SortArrayList.Node[] nodeArr,int start,int end,int k){
        int middle = QuickSort.quickDivision(nodeArr,start,end);
        while (middle!=k-1){
            if(middle<k-1){
                middle = QuickSort.quickDivision(nodeArr,middle+1,end);
            }else {
                middle = QuickSort.quickDivision(nodeArr,start,middle-1);
            }
        }
        return nodeArr[middle];
    }
}

/*
n^2 大多稳定（除了选择排序）：冒泡（标志位优化） 插入（根据已有排序性能高，可优化希尔排序,gap区n/2，递减至1）
nlogn 大多不稳定（除了归并）：
时间优选：快排（递归 空间O（nlgn*1 次数*每次的一个临时位） 已有排序退化 挖坑填位）
空间优选：堆排序（空间O（1）数组长度n，建堆从（n/2 - 1）非叶子节点开始，已知i则左子2i+1右子2i+2，已知i则父亲（i-1）/2）
稳定优选：归并（递归 空间O（n）合并时的临时数组 稳定 性能nlgn不退化）

快排不稳定：如 221 以第一个2为基准挖坑，等1填充后再挖坑填2，导致不稳定
堆排不稳定：     1      最大堆，从（n/2 - 1）开始，和左2交换，导致不稳定
               2   2
// 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
        if (child + 1 < length && array[child] < array[child + 1]) {
            child++;
        }

如果有相同元素，是不是应该右孩子结点的值大于“等于”左孩子结点，则选取右孩子结点呢？
像       1           建立最大堆
      2     2
答案不用，也满足最大堆，并且堆排序后不稳定
 */

/*
！！！海量数据topK问题（HashCode+堆排，需要有序输出k个最大的）
从海量数据中找出最大的前k个数，通常比较好的方案是hash分治+大小顶堆，即先将数据集按照Hash方法分解成多个小数据集，
之后用大顶堆求出每个数据集中出现频率最高的前K个数，最后在所有top K中求出最终的top K。

有10亿个浮点数，如果找出期中最大的10000个？（一亿等于10的8次方，1GB-10^3MB-10^6KB-10^9B）
1最容易想到的方法是将数据全部排序，然后在排序后的集合中进行查找，最快的排序算法的时间复杂度一般为O（nlogn），如快速排序。
很显然是不能一次将全部数据读入内存进行排序的。其实即使内存能够满足要求（我机器内存都是8GB），该方法也并不高效。
2对于jvm，每个float类型占4个字节，10亿个浮点数就要占用4GB的存储空间。
根据数据特征增加哈希函数的随机性，通过哈希散列到多个数据集尽量均分，如果大于内存，再散列直到小于内存。
对每个数据集用堆排序求出topK，再从topK里求出最终topK。

有一个10G的文件，里面是数字，怎么排序？
外部排序，分为多个文件，对这些文件读入内存进行排序，可以使用归并、快排、堆排。得到多个排序好的文件以后将这些文件合并。
具体比如分为10个文件，那么就维护10个指针，每次取最小值写入新文件中，最终可以得到。

！！！海量数据topK问题（统计词频问题、十亿个IP，获得访问次数最多的十个 HashCode+HashMap+堆排）
在大规模数据处理中，经常会遇到的一类问题：在海量数据中找出出现频率最高的前k个数。
例如，在搜索引擎中，统计搜索最热门的10个查询词；在歌曲库中统计下载最高的前10首歌等。
（一亿等于10的8次方，1GB-10^3MB-10^6KB-10^9B）求出总大小，根据内存划分文件数，求哈希划分文件（相同一定会到一个文件）
HashMap对每个文件统计词频，堆排序求topK。再从topK里求最终topK。

！！！位图法（判断是否存在只用存0、1，节约了存数字的信息，并且查看对应bit直接得到0不存在1存在）
给40亿个不重复的unsigned int的整数，没有排过序，然后再给一个数，如果快速判断这个数是否在那40亿个数当中。
如果一个int（4字节）存一个数，40亿个int需要160亿字节，即16G。用位图一个bit01代表有无，一个int用32次，则16G/32=0.5G搞定
而用位图表示的话需要大小为40亿个bit=4*10^9 bit=0.5*10^9 bytes，因此申请的内存只需要大小约为512MB左右，
这样在内存每个bit代表一个unsigned int整数，并将每个bit初始化为0，然后将40亿个unsigned int的整数读入，
每个unsigned int的整数对应bit设置为1，读入后，最后看所给定的数对应的bit是否为1，是1存在，否则不存在。

！！！找第K大问题（快排 只需要输出第K大）
divison多次

 */