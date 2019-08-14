package com.example.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public static void main(String[] args){

    }

    //递归
    public static boolean isEqualRecursive(TreeNode rootA,TreeNode rootB){
        if(rootA==null&&rootB==null){
            return true;
        }else if(rootA!=null&&rootB!=null&&rootA.value==rootB.value){
            return isEqualRecursive(rootA.left,rootB.left)&&isEqualRecursive(rootA.right,rootB.right);
        }else {
            return false;
        }
    }

    //非递归
    public static boolean isEqualIterate(TreeNode rootA,TreeNode rootB){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootA);
        queue.offer(rootB);
        TreeNode a,b;
        while (!queue.isEmpty()){
            a = queue.poll();
            b = queue.poll();
            if(a==null&&b==null){
                continue;
            }else if(a!=null&&b!=null&&a.value==b.value){
                queue.offer(a.left);
                queue.offer(b.left);
                queue.offer(a.right);
                queue.offer(b.right);
            }else {
                return false;
            }
        }
        return true;
    }

    //递归
    public static boolean isSymetric(TreeNode root){
        if(root==null){
            return true;
        }else {
            return isSymetric(root.left,root.right);
        }
    }
    public static boolean isSymetric(TreeNode rootA,TreeNode rootB){
        if(rootA==null&&rootB==null){
            return true;
        }else if(rootA!=null&&rootB!=null&&rootA.value==rootB.value){
            return isSymetric(rootA.left,rootB.right)&&isSymetric(rootA.right,rootB.left);
        }else {
            return false;
        }
    }

    //非递归
    public static boolean isSymetricIterate(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        TreeNode a,b;
        while (!queue.isEmpty()){
            a = queue.poll();
            b = queue.poll();
            if(a==null&&b==null){
                continue;
            }else if(a!=null&&b!=null&&a.value==b.value){
                queue.offer(a.left);
                queue.offer(b.right);
                queue.offer(a.right);
                queue.offer(b.left);
            }else {
                return false;
            }
        }
        return true;
    }
}


class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;
}

class BinarySearchTree{

}