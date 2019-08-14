//public class Main{
//    static int N,M;
//    static List<String> list;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//
//        }
//        System.out.println("");
//    }
//
//    static void otherMethod(){
//
//    }
//}


package com.example.acm;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

//public class Main{
//    static int size,left,right,middle;
//    static int[] arr;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        size = sc.nextInt();
//        middle = size/2;
//        right = size-1;
//        left = 0;
//        arr = new int[sc.nextInt()];
//        for(int i=0;i<arr.length;i++){
//            arr[i] = sc.nextInt();
//        }
//        Arrays.sort(arr);
//        System.out.print(otherMethod());
//    }
//
//    static long otherMethod(){
//        long minVal = Long.MAX_VALUE;
//
//        //向左右靠齐的移动值
//        long minV=0;
//        int l = left,r = right;
//        int i =0,j=arr.length-1;
//        while(arr[i]<middle){
//            minV+=arr[i]-l;
//            l++;
//            i++;
//        }
//        while (arr[j]>middle){
//            minV+=r-arr[j];
//            r--;
//            j--;
//        }
//        if(i==j&&arr[i]==middle){
//            int x =Math.min(arr[i]-l,r-arr[i]);
//            minV+=x;
//        }
//        minVal = minV<minVal?minV:minVal;
//
//        //向中间靠齐的移动值
//        minV=0;
//        l = middle-1;
//        r = middle+1;
//        for(int i=0;i<arr.length;i++){
//            if(arr[i]<middle){
//                minV+=l-arr[i];
//                l--;
//            }else if(arr[i]>middle){
//                minV+=arr[i]-r;
//                r++;
//            }
//        }
//        minVal = minV<minVal?minV:minVal;
//
//        return minVal;
//    }
//}


//可能的递增序列总数
//public class Main{
//    static int length,sum,count=0;
//    static List<String> list;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        length = sc.nextInt();
//        sum = sc.nextInt();
//        otherMethod();
//        System.out.println(count);
//    }
//
//    static int otherMethod(){
//        int left=1,right=sum-1;
//        int val = 0,times =length;
//        while(length>0){
//
//            length--;
//        }
//    }
//
//    static int sumV(int left,int right,int times,int val){
//
//        for(int i =left;i<right;i++){
//
//        }
//    }
//}




//有符号int最大值能容纳约20亿
//50,未排序暴力搜索的方差，时间复杂度大
//double avg = (a+b+c)/3; 整除丢失小数了。改3.0
//public class Main{
//    static int[] arr;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        arr = new int[sc.nextInt()];
//        for(int i=0;i<arr.length;i++){
//            arr[i] = sc.nextInt();
//        }
//        Arrays.sort(arr);
//        System.out.println(otherMethod());
//    }
//
//    static String otherMethod(){
//        double min = Double.MAX_VALUE;
//        for(int i =0,j=1,k=2;i<arr.length-2;i++,j++,k++){
//            min = Math.min(min,fangcha(arr[i],arr[j],arr[k]));
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("#.00");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
//        return decimalFormat.format(min);
//    }
//
//    static double fangcha(double a, double b,double c){
//        double avg = (a+b+c)/3.0;
//        return (Math.pow(Math.abs(a-avg),2)+Math.pow(Math.abs(b-avg),2)+Math.pow(Math.abs(c-avg),2))/3.0;
//    }
//}


//public class Main{
//    static int N,M;
//    static List<String> list;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//
//        }
//        System.out.println("");
//    }
//
//    static void otherMethod(){
//
//    }
//}

//public class Main{
//    static long addSum = 0;
//    static long[] arr;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        arr = new long[sc.nextInt()];
//        for(int i=0;i<arr.length;i++){
//            arr[i] = sc.nextLong();
//        }
//        System.out.println(otherMethod());
//    }
//
//
//    static long otherMethod(){
//        if(arr.length==1){
//            return addSum;
//        }
//
//        int index = 1;
//        //找到递增、递减分界index
//        while (index<arr.length&&arr[index]>arr[index-1]){
//            index++;
//        }
//        //完全递增直接返回
//        if(index==arr.length){
//            return addSum;
//        }
//
//        //逆序处理递减序列
//        for(int i=arr.length-1;i>index+1;i--){
//            if(arr[i]>=arr[i-1]){
//                addSum+=(arr[i]+1-arr[i-1]);
//                arr[i-1]=arr[i]+1;
//            }
//        }
//
//        //把index归入递增或者递减序列
//        long toRight = 0;
//        if(index<arr.length-1&&arr[index+1]>=arr[index]){
//            toRight=(arr[index+1]+1-arr[index]);
//        }
//        long toLeft = 0;
//        if(index>0&&arr[index]<=arr[index-1]){
//            toLeft=(arr[index-1]+1-arr[index]);
//        }
//
//        long min = toLeft<toRight?toLeft:toRight;
//        addSum+=min;
//
//        return addSum;
//    }
//}



//public class Main{
//    static long times = 0;
//    static long[] arr;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        arr = new long[sc.nextInt()];
//        for(int i=0;i<arr.length;i++){
//            arr[i] = sc.nextLong();
//        }
//        Arrays.sort(arr);
//        System.out.println(otherMethod());
//    }
//
//    //先排序，一次遍历，向右找值的110%
//    static long otherMethod(){
//        for(int i=0;i<arr.length-1;i++){
//            for(int j=i+1;j<arr.length;j++){
//                long min = arr[i]<arr[j]?arr[i]:arr[j];
//                long max = arr[i]>arr[j]?arr[i]:arr[j];
//                if(min>=(max*0.9)){
//                    times++;
//                }else {
//                    break;
//                }
//            }
//        }
//
//        return times;
//    }
//}


//27%
//public class Main{
//    static int maxRiseLength = 0;
//    static List<Long> list = new LinkedList<>();
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLong()) {
//            list.add(sc.nextLong());
//        }
//        System.out.println(otherMethod());
//    }
//
//    //优化，当目前剩余长度小于目前maxRiseLength，停止搜索
//    static int otherMethod(){
//        for(int i=0;i<list.size();i++){
//            if((list.size()-i)<=maxRiseLength){
//                return maxRiseLength;
//            }
//            for(int k=i+1;k<list.size();k++){
//                long curVal = list.get(i);
//                int length =1;
//                for(int j =k;j<list.size();j++){
//                    if(list.get(j)>curVal){
//                        curVal = list.get(j);
//                        length++;
//                    }
//                }
//                maxRiseLength = maxRiseLength<length?length:maxRiseLength;
//            }
//        }
//
//        return maxRiseLength;
//    }
//}



//public class Main{
//    static int n;
//    static long minAbs;
//    static long[] arr;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        arr = new long[n];
//        for(int i=0;i<n;i++){
//            arr[i] = sc.nextLong();
//        }
//        System.out.println(otherMethod());
//    }
//
//    static String otherMethod(){
//        long first = arr[0],second = arr[1];
//        minAbs = Math.abs(second-first);
//        for(int i=2;i<arr.length;i++){
//            long newAbs = Math.abs(arr[i]-arr[i-1]);
//            if(newAbs<minAbs){
//                minAbs = newAbs;
//                first = arr[i-1];
//                second = arr[i];
//            }
//        }
//
//        return first+" "+second;
//    }
//}


//public class Main {
//    static int n, minusTimes;
//    static int[] score;
//
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        score = new int[n];
//        minusTimes = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            score[i] = Integer.valueOf(sc.nextInt());
//        }
//        for (int i = 0; i < minusTimes; i++) {
//            System.out.println(findX(Integer.valueOf(sc.nextInt())));
//        }
//    }
//
//    public static int  findX(int x){
//        int sum = 0;
//        for(int i=0;i<n;i++){
//            if(score[i]>=x){
//                score[i]--;
//                sum++;
//            }
//        }
//        return sum;
//    }
//}






//public class Main{
//    static List<String> list = new LinkedList<>();
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNextLine()) {
//            list.add(sc.nextLine());
//        }
//        String[] strArr;
//        for(String str : list){
//            strArr = str.split(" ");
//            System.out.println(findSqrt(Double.parseDouble(strArr[0]),Double.parseDouble(strArr[1])));
//        }
//    }
//
//    static String findSqrt(double n,double m){
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        double sum = 0;
//        if(m>0){
//            sum += n;
//            m--;
//            while(m>0){
//                n = Math.sqrt(n);
//                sum+=n;
//                m--;
//            }
//        }
//        return decimalFormat.format(sum);
//    }
//}

//public class Main{
//    static int M,N;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        double D = sc.nextDouble();
//        findPrice(D);
//        System.out.println(M+" "+N);
//    }
//
//    static void findPrice(double D){
//        double Ceil = Math.ceil(D)*10000;
//        double Floor = Math.floor(D);
//        double price = Floor;
//        for(double num = 1;num<=10000;num++){
//            for(double priceTotal = Floor;priceTotal<=Ceil;priceTotal++){
//                double newPrice = priceTotal/num;
//                if(Math.abs(newPrice-D)<Math.abs(price-D)){
//                    price = newPrice;
//                    N = (int)num;
//                    M = (int)priceTotal;
//                }
//            }
//        }
//    }
//}



/*
根据公式求成绩百分比，保留6位小数（考虑重复分数）
p = (大于等于当前分数的个数-1)/n*100
输入如下：
3
100 98 63
3
1
2
3
 */
//public class Main{
//    static int n,askTimes;
//    static int[] score;
//    static int[] askScore;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        score = new int[n];
//        for(int i=0;i<n;i++){
//            score[i] = Integer.valueOf(sc.nextInt());
//        }
//
//        askTimes = sc.nextInt();
//        askScore = new int[askTimes];
//        for(int i=0;i<askTimes;i++){
//            askScore[i] = score[sc.nextInt()-1];
//        }
//        Arrays.sort(score);
//
//        for(int i=0;i<askTimes;i++){
//            System.out.println(findP(askScore[i]));
//        }
//    }
//
//    static String findP(int askScore){
//        double i =0;
//        while ((i<n)&&(score[(int)i]<=askScore)){
//            i++;
//        }
//        i--;
//        return new DecimalFormat("0.000000").format((i/(double) n)*100);
//    }
//}