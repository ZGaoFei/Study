package com.zgf.javalib.algorithm;

import java.util.LinkedList;

public class MyQueue<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void enqueue(T t) {
        list.addLast(t);
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item: " + list.get(i));
        }
    }
}
