package com.zgf.javalib.algorithm;

import java.util.Objects;

public class Node<T> {
    T val;
    Node<T> next;

    public Node() {

    }

    public Node(T t) {
        val = t;
    }

    public Node(T t, Node<T> n) {
        val = t;
        next = n;
    }

}
