package com.example.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class QueueAndStackInJava {
    public static void main(String[] args){
//        stackByStackOrLinkedList();
        queueByLinkedList();
    }

    public static void stackByStackOrLinkedList(){
        //Stack数组实现,末尾作为栈顶，避免移动
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.toString());

        //LinkedList双向链表实现，头插法，头部作为栈顶
        LinkedList<Integer> stack1 = new LinkedList<>();
//        stack1.addFirst(1);
//        stack1.addFirst(2);
//        stack1.addFirst(3);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        System.out.println(stack1.peek());
        System.out.println(stack1.pop());
        System.out.println(stack1.toString());

    }

    public static void queueByLinkedList(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());
    }
}
