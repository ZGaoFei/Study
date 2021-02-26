package com.zgf.javalib;

import com.zgf.javalib.algorithm.ArrayAlgorithm;
import com.zgf.javalib.algorithm.MyQueue;
import com.zgf.javalib.algorithm.MyStack;
import com.zgf.javalib.algorithm.NodeAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.TreeMap;


public class JavaLib {

    public static void main(String[] args) {
//        int result = Tools.add(5, 6);
//        System.out.print(result);

//        arrayTest();

//        nodeTest();

//        stackTest();

//        queueTest();

        test();
    }

    private static void arrayTest() {
        int[] array = {0, 1, 3, 4, 5};
        ArrayAlgorithm.arrayAddData(array, 2, 2);
    }

    private static void nodeTest() {
        NodeAlgorithm nodeAlgorithm = new NodeAlgorithm();
        nodeAlgorithm.newFirstNode("one");
        nodeAlgorithm.addNode("two");
        nodeAlgorithm.addNode("three");
        nodeAlgorithm.addNode("four");
        nodeAlgorithm.printNodes();

        nodeAlgorithm.printNodesRev(nodeAlgorithm.getFirst());

        NodeAlgorithm.Node first = nodeAlgorithm.getFirst();
        System.out.println("first: " + first.element);

        NodeAlgorithm.Node last = nodeAlgorithm.getLast();
        System.out.println("last: " + last.element);

        NodeAlgorithm.Node node = nodeAlgorithm.revList(nodeAlgorithm.getFirst());
        while (node != null) {
            System.out.println("==print==" + node.element);
            node = node.next;
        }
    }

    private static void stackTest() {
        MyStack<String> stack = new MyStack<>();
        stack.push("one");
        stack.push("two");
        stack.push("three");
        stack.push("four");

        stack.print();

        String pop = stack.pop();
        System.out.println("pop: " + pop);

        stack.print();
    }

    private static void queueTest() {
        MyQueue<String> queue = new MyQueue<>();

        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        queue.enqueue("four");

        queue.print();

        String dequeue = queue.dequeue();
        System.out.println("dequeue: " + dequeue);

        queue.print();
    }
    
    private static void test() {
        /**
         * Set
         * HashSet
         * TreeSet
         *
         * List
         * ArrayList
         * LinkedList
         *
         * Vector/Stack
         * Queue
         *
         * HashMap
         * HashTable
         * TreeMap
         * LinkedHashMap
         * SparseArray
         * ArrayMap
         */
        ArrayList arrayList;
        LinkedList linkedList;

        HashMap hashMap;
        TreeMap treeMap = new TreeMap();
        Object o = new Object();
        treeMap.put("1", "");
        treeMap.put("null", "2");
        String string;

        Hashtable hashtable = new Hashtable();
        hashtable.put("null", "null");
    }
}
