package com.wu.day06.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/13 - 14:35
 */
public class MergeSort {
    public static void main(String[] args) {
      /*  int[] arr = {8, 4, 5, 7, 1, 3, 6, 2,67,45,8,34};

        mergeS(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        int temp[] = new int[arr.length];
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
       mergeS(arr, 0, arr.length - 1, temp);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
    }

    //分解+合的方法
    public static void mergeS(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左分解
            mergeS(arr, left, mid, temp);
            //向右分解
            mergeS(arr, mid + 1, right, temp);

            //合并
            merge(arr, left, right,mid, temp);
        }
    }

    //合并的方法

    /**
     * @param arr   需要进行排序的原始数组
     * @param left  左边序列的起始位置
     * @param right 右边索引
     * @param mid   中间索引
     * @param temp  临时交换的数组
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int i = left;//左边有序序列的起始位置
        int j = mid + 1; //右边序列的起始位置
        int t = 0;  //temp数组的索引


        //1.将左右两边的数据按照规则填入temp数组
        while (i <= mid && j <= right) {//未填入完成
            //将左边序列比右边序列小的数据依次填入
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {//将左边序列比右边序列小的数据依次填入
                temp[t++] = arr[j++];
            }
        }

        //2.检测哪边未完全填入 将其依次填入
        //左边数据未完全填入
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //右边数据未完全填入
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //3.将temp数组复制到arr
        //注意 不是每次拷贝都是所有的数据
        t = 0;
        int tempLeft = left;
//        System.out.println("tempLeft="+tempLeft+" right="+right);
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
