package com.example.acm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Template {
}


////模板
//public class Main {
//    public static void main(String[] args){
//        StringBuffer stringBuffer = new StringBuffer();
//        int a;
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            stringBuffer.append(scanner.next());
//        }
//        while (scanner.hasNextLine()){
//            stringBuffer.append(scanner.nextLine());
//        }
//        while (scanner.hasNextInt()){
//            a = scanner.nextInt();
//        }
//        System.out.println(stringBuffer.toString());
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
//    }
//
//    static void otherMethod(){
//
//    }
//}

//public class Main {
//    public static void main(String[] args){
//        String[] lines = new String[2];
//        Scanner scanner = new Scanner(System.in);
//        for(int i=0;i<2;i++){
//            lines[i] = scanner.next();
//        }
//        System.out.println(lines.toString());
//    }
//}

//求修改靓号花费最少
//public class Main {
//    public static void main(String[] args){
//        String[] lines = new String[2];
//        Scanner scanner = new Scanner(System.in);
//        for(int i=0;i<2;i++){
//            lines[i] = scanner.nextLine();
//        }
//        int N = lines[1].length(),K = Integer.valueOf(lines[0].split(" ")[1]);
//
//        //读取出现数字以及记录次数
//        int[] num09 = new int[10];
//        int index;
//        for(int i =0;i<N;i++){
//            index = Integer.valueOf(lines[1].charAt(i)+"");
//            num09[index]++;
//            //不用修改就是靓号
//            if(num09[index]==K){
//                System.out.println(0);
//                System.out.println(lines[1]);
//                return;
//            }
//        }
//
//        //找到最小花费的靓号，可能多个，取最小
//        int cost = 0;
//        int phoneNum;
//        for(int i=0;i<10;i++){
//            if(num09[i]!=0){
//
//            }
//        }
//        System.out.println(lines.toString());
//    }
//}


////相邻不同的种树
//public class Main{
//    static int N,M;
//    static int[] trees;
//    static List<String> list;
//
//    public static void main(String[] args){
//        //读取N种类、M总数,均正整数
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            N = sc.nextInt();
//            trees = new int[N+1];
//            for(int i=1;i<=N;i++){
//                trees[i]=sc.nextInt();
//                M+=trees[i];
//            }
//            list=new LinkedList<>();
//
//            if(dfs(0)){
//                System.out.println(String.join(" ",list));
//            }else {
//                System.out.println("-");
//            }
//        }
//    }
//
//    //深度优先搜索，动态改变list
//    static boolean dfs(int planted){
//        if(!checkContinuePlant(M-planted)){
//            //不可以继续种
//            return false;
//        }else if(planted==M){
//            //种完
//            return true;
//        }else {
//            //没种完并且可以继续种，第一次直接种，第二次还有剩余的、不同于上次的树才可以种
//            for(int i=1;i<=N;i++){
//                if(planted==0||(trees[i]!=0&&i!=Integer.parseInt(list.get(planted-1)))){
//                    trees[i]--;
//                    list.add(i+"");
//                    if(dfs(planted+1)){
//                        return true;
//                    }
//                    list.remove(list.size()-1);
//                    trees[i]++;
//                }
//            }
//        }
//
//        //种失败
//        return false;
//    }
//
//    static boolean checkContinuePlant(int left){
//        //如果一种树剩余量减一（至少需要其他树来填充的坑位） 大于 剩余的其他树，无方案
//        for(int i=1;i<=N;i++){
//            if((trees[i]-1)>(left-trees[i])){
//                return false;
//            }
//        }
//        return true;
//    }
//}


//求数组两两匹配的最大最小值
//public class Main{
//    static int n,max,min;
//    static int[] nums;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        nums = new int[n];
//        for(int i=0;i<n;i++){
//            nums[i] = sc.nextInt();
//        }
//        Arrays.sort(nums);
//        setMaxAndMin();
//        System.out.println(max-min);
//    }
//
//    static void setMaxAndMin(){
//        max=min=nums[0]+nums[n-1];
//        int add;
//        for(int i=0;i<n/2;i++){
//            add = nums[i]+nums[n-1-i];
//            max = max<add?add:max;
//            min = min>add?add:min;
//        }
//    }
//}

//1普通攻击和2聚力攻击 考虑余数位置
//1=======|=======
//2======= =========
//public class Main{
//    static int hp,normalAttack,buffedAttack,minCount;
//    static List<String> list;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        hp = sc.nextInt();
//        normalAttack = sc.nextInt();
//        buffedAttack = sc.nextInt();
//        findMinCount();
//        System.out.println(minCount);
//    }
//
//    static void findMinCount(){
//        int left;
//        if(buffedAttack<=2*normalAttack){
//            //全程使用normalAttack
//            minCount = hp/normalAttack;
//            left = hp%normalAttack;
//            if(left>0){
//                minCount++;
//            }
//        }else {
//            //使用buffedAttack，如果余数小于normalAttack，最后一下使用normalAttack
//            minCount = 2*(hp/buffedAttack);
//            left = hp%buffedAttack;
//
//            if(0<left&&left<=normalAttack){
//                minCount++;
//            }else if(left>normalAttack){
//                minCount += 2;
//            }
//        }
//    }
//}



//旋转 源能否包含目标（构造每种可能的旋转情况太耗时
//考虑长度不满足直接返回0，拿源每一个可能的开始位置和目标比较，源通过对长度求余达到循环效果）
//public class Main{
//    static StringBuilder result = new StringBuilder();
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        for(int i=0;i<3;i++){
//            result.append(ifContains(sc.nextLine(),sc.nextLine()));
//        }
//        System.out.print(result.toString());
//    }
//
//    static int ifContains(String src,String target){
//        int srcLength = src.length();
//        if(srcLength<target.length()){
//            return 0;
//        }
//
//        int targetLength = target.length();
//        for(int i=0;i<srcLength;i++){
//            int j=i,k=0;
//            while (k<targetLength&&src.charAt(j%srcLength)==target.charAt(k)){
//                j++;
//                k++;
//            }
//            if(k==targetLength){
//                return 1;
//            }
//        }
//        return 0;
//    }
//}



//public class Main{
//    static StringBuilder result = new StringBuilder();
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        for(int i=0;i<3;i++){
//            result.append(ifContains(sc.nextLine(),sc.nextLine()));
//        }
//        System.out.print(result.toString());
//    }
//
//    static int ifContains(String src,String target){
//        int srcLength = src.length();
//        if(srcLength<target.length()){
//            return 0;
//        }
//        for(int i=0;i<srcLength;i++){
//            if(src.contains(target)){
//                return 1;
//            }
//            src = src.substring(1,srcLength)+src.substring(0,1);
//        }
//        return 0;
//    }
//}


