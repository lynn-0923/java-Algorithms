package com.wu.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lynn
 * @date 2020/3/18 - 12:37
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        //前序遍历
        preOrder(huffmanTree);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root !=null){
            root.preOrder();
        }else {
            System.out.println("赫夫曼树为空，不能遍历");
        }
    }
    //创建赫夫曼树

    /**
     * @param arr 原始数组
     * @return 赫夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //为了方便存储 我们遍历数组 将元素
        //生成Node对象 并添加到集合中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) { //如果size == 1 则说明已将生成了一个最优二叉树
            //进行排序
            Collections.sort(nodes);//每次都需要排序
//        System.out.println(nodes);
            //1.获取到权值最小的元素
            Node leftNode = nodes.get(0);
            //2.获取权值第二小的元素
            Node rightNode = nodes.get(1);
            //3.生成一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;
            //4.处理ArrayList中已被处理的元素
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5.将parent添加到nodes中
            nodes.add(parent);
        }
        return nodes.get(0);//将root返回  只有一个节点
    }
}

//创建节点 为了方便Collection进行排序
//实现compareto接口
class Node implements Comparable<Node> {
    public int value; //权值
    public Node left;
    public Node right;

    //构造器
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
   //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left !=null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    @Override
    public int compareTo(Node o) {
        //此处为升序
        //如果为-(this.value - 0.value)则为降序
        return this.value - o.value;
    }
}
