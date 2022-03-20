package com.zgf.javalib.algorithm;

import java.util.ArrayList;

public class MyStack<T> {
    private ArrayList<T> list;

    public MyStack() {
        list = new ArrayList<>();
    }

    public T pop() {
        int size = list.size();
        T t = list.get(size - 1);
        T remove = list.remove(size - 1);
        return t;
    }

    public void push(T data) {
        list.add(data);
    }

    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("item: " + list.get(i));
        }
    }
}
