package com.chengmboy.core.structure;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Stack;
import java.util.concurrent.*;


/**
 * @author cheng_mboy
 */
public class DataStructure {

    public static void main(String[] args) throws InterruptedException {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.size());

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.take());
        System.out.println(queue.size());

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        Iterator<Integer> integerIterator = linkedList.descendingIterator();
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

    }
}
