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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(val, node.val) &&
                Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}
