package com.wu.day07;

import java.util.ArrayList;

/**
 * @author lynn
 * @date 2020/3/14 - 14:19
 */
//注意 插值查找比较适合分布均匀 有规律的
    //如果波动较大可能二分查找会更加适合
public class InsertValSearch {
    public static void main(String[] args) {
     /*   int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }*/
          int[] arr = {1, 2, 6, 7, 9, 67, 78, 86};
        int i = insertValSearch(arr, 0, arr.length - 1, 9);
//        int i = binarySearch(arr, 0, arr.length - 1, 1);
        if (i == -1) {
            System.out.println("未找到");
        } else {
            System.out.println(i);
        }
    }
    //插值查找
    //也必须是有序的

    /**
     * @param arr     待查找的数组
     * @param left    左下标
     * @param right   右下标
     * @param findVal 待查找的值
     * @return 如果有则返回 如果没有则返回-1
     */
    public static int insertValSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找");
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);//mid 自适应
        int midVal=arr[mid];
        //必须要判断 否则mid会越界 如果 |findVal >arr[arr.length-1]  将会超过数组的大小
        if (left > right || findVal <arr[0] ||findVal >arr[arr.length-1]){
            return -1;
        }
        if (findVal <midVal){
            return insertValSearch(arr,left,mid -1,findVal);
        }else if (findVal >midVal){
            return insertValSearch(arr,mid +1,right,findVal);
        }else {
            return mid;
        }
    }
    //二分查找
    public static int binarySearch(int[] arr, int left, int right, int value) {
        System.out.println("二分查找次数");
        int mid = (left + right) / 2;
        ArrayList<Integer> list = new ArrayList<>();//用来存放找到的索引
        if (left > right) {
            return -1;
        }
        if (arr[mid] > value) {//中间值大于查找的值
            //向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {//中间值小于查找的值
            //向右递归
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }
}
