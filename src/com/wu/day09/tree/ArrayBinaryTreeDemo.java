package com.wu.day09.tree;

/**
 * @author lynn
 * @date 2020/3/15 - 21:00
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7,};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        //前序
        System.out.println("前序遍历---");
        arrayBinaryTree.preOrder(0);//1,2,4,5,3,6,7

        //中序
        System.out.println();
        System.out.println("中序遍历---");
        arrayBinaryTree.infixOrder(0);//4,2,5,1,6,3,7

        //后序
        System.out.println();
        System.out.println("后序遍历---");
        arrayBinaryTree.postOrder(0);//4,5,2,6,7,3,1
    }
}

//构建一个顺序存储二叉树,要求是满二叉树
//第n个元素的左子节点为 2*n +1
//第n个元素的右子节点为 2*n +2
//第n个元素的父节点为（n -1）*2
class ArrayBinaryTree {
    private int[] arr;// 存放数据节点的数组

    //构造器
    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载方法
    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }
    //编写一个方法 完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //判断数组 是否为空 ，有无数据
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空，无法遍历");
            return;
        }
        //输出这个元素
        System.out.print(arr[index] + " ");
        //向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //编写一个方法 完成顺序存储二叉树的中序遍历

    /**
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        //判断数组 是否为空 ，有无数据
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空，无法遍历");
            return;
        }
        //向左递归
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出这个元素
        System.out.print(arr[index] + " ");
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //编写一个方法 完成顺序存储二叉树的后序遍历

    /**
     * @param index 数组的下标
     */
    public void postOrder(int index) {
        //判断数组 是否为空 ，有无数据
        if (arr == null || arr.length == 0) {
            System.out.println("当前数组为空，无法遍历");
            return;
        }
        //向左递归
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出这个元素
        System.out.print(arr[index] + " ");
    }
}

