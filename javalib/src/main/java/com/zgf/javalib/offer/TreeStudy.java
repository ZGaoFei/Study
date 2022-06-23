package com.zgf.javalib.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

        TreeNode<Integer> treeNodeA = new TreeNode<>(10);
        TreeNode<Integer> treeNodeB = new TreeNode<>(6);
        TreeNode<Integer> treeNodeC = new TreeNode<>(14);
        treeNodeA.left = treeNodeB;
        treeNodeA.right = treeNodeC;

        TreeNode<Integer> treeNodeD = new TreeNode<>(4);
        TreeNode<Integer> treeNodeE = new TreeNode<>(8);
        treeNodeB.left = treeNodeD;
        treeNodeB.right = treeNodeE;

        TreeNode<Integer> treeNodeF = new TreeNode<>(12);
        TreeNode<Integer> treeNodeG = new TreeNode<>(16);
        treeNodeC.left = treeNodeF;
        treeNodeC.right = treeNodeG;
        kthNode(treeNodeA, 4);

        int[] pre = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] vin = new int[]{3, 2, 4, 1, 6, 5, 7};
        TreeNode<Integer> node = reConstructBinaryTree(pre, vin);
        printTreeNode(node);

        boolean b = hasSubtree(treeNodeA, treeNodeB);
        System.out.println("hasSubtree: " + b);

        mirror(node);
        printTreeNode(node);
        mirror1(node);

        int[] nums = new int[]{1, 2, 3, 4, 5};
        boolean ofBST = verifySequenceOfBST(nums);
        System.out.println(ofBST);

        boolean b1 = verifySquenceOfBST(nums);
        System.out.println(b1);
    }

    /**
     * JZ55 二叉树的深度
     * <p>
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
     * <p>
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

    private static void printTreeNode(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            System.out.println(-1);
            return;
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> poll = queue.poll();
                System.out.print(poll.value + ",");

                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            System.out.println();
        }
    }

    /**
     * JZ54 二叉搜索树的第k个节点
     * <p>
     * 二叉查找树（Binary Search Tree）
     * （又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
     * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
     * 它的左、右子树也分别为二叉排序树。
     */
    static int count = 0;
    static TreeNode<Integer> res;

    private static void kthNode(TreeNode<Integer> treeNode, int k) {
        if (treeNode == null) {
            System.out.println(-1);
            return;
        }

        minOrder(treeNode, k);
        if (res == null) {
            System.out.println(-1);
        } else {
            System.out.println(res.value);
        }
    }

    /**
     * 前序遍历：根左右
     * 中序遍历：左根右
     * 后序遍历：左右根
     * 区分：根在某个位置即为什么遍历
     */
    // 中序遍历
    private static void minOrder(TreeNode<Integer> treeNode, int k) {
        if (treeNode == null || count > k) {
            return;
        }
        minOrder(treeNode.left, k);
        count++;
        if (count == k) {
            res = treeNode;
        }

        minOrder(treeNode.right, k);
    }

    /**
     * JZ7 重建二叉树
     * <p>
     * 给定节点数为 n 的二叉树的前序遍历和中序遍历结果，
     * 请重建出该二叉树并返回它的头结点。
     */
    private static TreeNode<Integer> reConstructBinaryTree(int[] pre, int[] vin) {
        int m = pre.length;
        int n = vin.length;
        if (m == 0 || n == 0) {
            return null;
        }

        TreeNode<Integer> treeNode = new TreeNode<>(pre[0]);
        for (int i = 0; i < vin.length; i++) {
            if (pre[0] == vin[i]) {
                treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(vin, 0, i));
                treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(vin, i + 1, vin.length));
                break;
            }
        }
        return treeNode;
    }

    /**
     * JZ26 树的子结构
     */
    private static boolean hasSubtree(TreeNode<Integer> parent, TreeNode<Integer> child) {
        if (parent == null || child == null) {
            return false;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(child);
        while (!queue.isEmpty()) {
            TreeNode<Integer> poll = queue.poll();
            if (poll.value.equals(child.value)) {
                if (helper(parent, child)) {
                    return true;
                }
            }

            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return false;
    }

    private static boolean helper(TreeNode<Integer> parent, TreeNode<Integer> child) {
        Queue<TreeNode<Integer>> q1 = new LinkedList<>();
        Queue<TreeNode<Integer>> q2 = new LinkedList<>();
        q1.offer(parent);
        q2.offer(child);
        while (!q2.isEmpty()) {
            TreeNode<Integer> poll1 = q1.poll();
            TreeNode<Integer> poll2 = q2.poll();
            if (poll1 == null || !poll1.value.equals(poll2.value)) {
                return false;
            }

            if (poll2.left != null) {
                q1.offer(poll1.left);
                q2.offer(poll2.left);
            }
            if (poll2.right != null) {
                q1.offer(poll1.right);
                q2.offer(poll2.right);
            }
        }
        return true;
    }

    /**
     * JZ27 二叉树的镜像
     */
    private static void mirror(TreeNode<Integer> treeNode) {
        if (treeNode != null) {
            mirrorHelper(treeNode);
            mirror(treeNode.left);
            mirror(treeNode.right);
        }
    }

    private static void mirror1(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode<Integer> pop = stack.pop();
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }

            mirrorHelper(pop);
        }

        printTreeNode(treeNode);
    }

    private static void mirrorHelper(TreeNode<Integer> treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode<Integer> temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
    }

    /**
     * JZ33 二叉搜索树的后序遍历序列
     */
    private static boolean verifySequenceOfBST(int[] sequence) {
        if(sequence.length == 0) return false;
        Stack<Integer> s = new Stack<>();
        s.add(Integer.MAX_VALUE);
        int root = Integer.MAX_VALUE;
        // 以根，右子树，左子树顺序遍历
        for(int i = sequence.length - 1; i >= 0; i--) {
            System.out.println(sequence[i] + "====" + root);
            // 确定根后一定是在右子树节点都遍历完了，因此当前sequence未遍历的节点中只含左子树，左子树的节点如果>root则说明违背二叉搜索的性质
            if(sequence[i] > root) return false;
            // 进入左子树的契机就是sequence[i]的值小于前一项的时候，这时可以确定root
            while(!s.isEmpty() && s.peek() > sequence[i]) {
                root = s.pop();
            }
            // 每个数字都要进一次栈
            s.add(sequence[i]);
        }
        return true;
    }

    public static boolean verifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0) return false;
        return order(sequence, 0, sequence.length - 1);
    }

    public static boolean order(int [] sequence, int l, int r) {
        // 剩一个节点的时候 返回 true
        if(l >= r) return true;
        int j;
        int mid = sequence[r];

        // 找到左子树和右子树的分界点，j代表左子树的最后一个索引位置
        for(j = r; j >= l; j--) {
            int cur = sequence[j];
            if(cur < mid) break;
        }

        // 判断所谓的左子树中是否又不合法（不符合二叉搜索树）的元素
        for(int i = j; i >= l; i--) {
            int cur = sequence[i];
            if(cur > mid) return false;
        }
        return order(sequence, l, j) && order(sequence, j+1, r-1);
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
