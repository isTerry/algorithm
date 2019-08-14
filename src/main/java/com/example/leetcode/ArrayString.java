package com.example.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ArrayString {
    public static void main(String[] args) throws InterruptedException {

    }



    //4sum:3sum转换，多加一个循环
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new LinkedList<>();

        for(int i=0;i<nums.length-3;i++){
            //跳过i重复
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                //跳过j重复
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int left=j+1,right=nums.length-1;
                while (left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        lists.add(new LinkedList<>(Arrays.asList(nums[i],nums[j],nums[left],nums[right])));
                        while (left<right&&nums[left]==nums[left+1]){
                            left++;
                        }
                        while (left<right&&nums[right]==nums[right-1]){
                            right--;
                        }
                        left++;
                        right--;
                    }else if(sum<target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }

        return lists;
    }


    //3sum：2sum转化；排序和跳过重复
    public List<List<Integer>> threeSum(int[] nums) {
        //排序
        Arrays.sort(nums);
        List<List<Integer>> lists = new LinkedList<>();
        for(int i=0;i<nums.length-2;i++){
            //跳过i重复
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }

            int left=i+1,right=nums.length-1;
            while (left<right){
                int sum = nums[i]+nums[left]+nums[right];
                if(sum==0){
                    lists.add(new LinkedList<Integer>(Arrays.asList(nums[i],nums[left],nums[right])));
                    //跳过left重复
                    while (left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    //跳过right重复
                    while (left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum<0){
                    left++;
                }else {
                    right--;
                }
            }
        }

        return lists;
    }

    //返回目标数或者下标
    public static int[] twoSum(int[] nums, int target) {
        int[] indexArr = new int[2];
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(hashMap.containsKey(target-nums[i])){
                indexArr[0]=i;
                indexArr[1]=hashMap.get(target-nums[i]);
                return indexArr;
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return null;
    }

    //返回目标数
//    public static int[] twoSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        int[] indexArr = new int[2];
//        int i=0,j=nums.length-1;
//        while (i<j){
//            int sum = nums[i]+nums[j];
//            if(sum==target){
//                indexArr[0]=nums[i];
//                indexArr[1]=nums[j];
//                return indexArr;
//            }else if(sum<target){
//                i++;
//            }
//            else {
//                j--;
//            }
//        }
//        return null;
//    }

    public static String earnMoney(float salaryPerYear,float profit,int year){
        float base = 0;
        for(int i=0;i<year;i++){
            base=(base+salaryPerYear)*profit;
        }
        return String.valueOf(base/10000);
    }

}
