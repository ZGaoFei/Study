package com.zgf.javalib.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class TreeStudy {

    public static void main(String[] args) {
        TreeNode<Integer> treeNode = new TreeNode<>(1);
        TreeNode<Integer> treeNode1 = new TreeNode<>(2);
        TreeNode<Integer> treeNode2 = new TreeNode<>(3);
        TreeNode<Integer> treeNode3 = new TreeNode<>(4);
        TreeNode<Integer> treeNode4 = new TreeNode<>(5);
        TreeNode<Integer> treeNode5 = new TreeNode<>(6);
        TreeNode<Integer> treeNode6 = new TreeNode<>(7);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode4.left = treeNode6;
        treeNode2.right = treeNode5;

        int max = treeDepth(treeNode);
        System.out.println(max);
        int max1 = treeDepth1(treeNode);
        System.out.println(max1);

        print(treeNode);
        test(treeNode);
    }

    /**
     * JZ55 二叉树的深度
     *
     * 深度优先
     */
    private static int treeDepth(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            return 0;
        }

        int max = Math.max(treeDepth(treeNode.left), treeDepth(treeNode.right)) + 1;
        return max;
    }

    /**
     * JZ55 二叉树的深度
     *
     * 广度优先
     */
    private static int treeDepth1(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            return 0;
        }
        // 使用的是队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        int max = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            // 一行一行的处理
            for (int i = 0; i < n; i++) {
                // 出队列，添加下一行
                // 这里poll出来的都是当前行的，添加的都是下一行的，因此是在遍历前获取的队列的长度
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            max++;
        }

        return max;
    }

    /**
     * JZ77 按之字形顺序打印二叉树
     * 奇数行从左向右
     * 偶数行从右向左
     */
    private static void print(TreeNode<Integer> treeNode) {
        TreeNode<Integer> header = treeNode;

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (header == null) {
            return;
        }
        // 记录每一行的节点
        Queue<TreeNode<Integer>> temp = new LinkedList<>();
        temp.offer(header);

        TreeNode<Integer> p;
        boolean flag = true; // 标志位是否反转
        while (!temp.isEmpty()) {
            // 记录每一行的数值
            ArrayList<Integer> row = new ArrayList<>();
            int n = temp.size();
            flag = !flag;
            for (int i = 0; i < n; i++) {
                p = temp.poll();
                row.add(p.value);
                if (p.left != null) {
                    temp.offer(p.left);
                }
                if (p.right != null) {
                    temp.offer(p.right);
                }
            }
            // 是否需要反转
            if (flag) {
                Collections.reverse(row);
            }
            res.add(row);
        }

        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }
    }

    private static void test(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            return;
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(treeNode);

        boolean flag = true;
        while (!queue.isEmpty()) {
            int n = queue.size();
            flag = !flag;
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode<Integer> poll = queue.poll();
                row.add(poll.value);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            if (flag) {
                Collections.reverse(row);
            }
            res.add(row);
        }

        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> integers = res.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j) + " ");
            }
            System.out.println();
        }
    }
}

class TreeNode<T> {
    TreeNode<T> left;
    TreeNode<T> right;
    T value;

    TreeNode(T t) {
        value = t;
    }

    TreeNode(T t, TreeNode<T> left, TreeNode<T> right) {
        value = t;
        this.left = left;
        this.right = right;
    }
}
