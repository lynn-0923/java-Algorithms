package com.wu.day06.sort;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/11 - 23:28
 */
public class SelectSort {
    public static void main(String[] args) {
       /* int[] arr = {29, 3, 34,56,4,7, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));*/
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        //排序前
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
        selectSort(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
    }

    //选择排序
    public static void selectSort(int[] arr) {
        //第一次
        for (int j = 0; j < arr.length - 1; j++) {
            int min = arr[j];//从起一个位置开始比较
            int minIndex = j;//开始的索引
            for (int i = j + 1; i < arr.length; i++) {
                if (min > arr[i]) {
                    min = arr[i];//重置
                    minIndex = i;
                }
            }
            //如果minIndex没有变化
            if (minIndex != 0) {
                arr[minIndex] = arr[j];
                arr[j] = min;
            }
        }
        /*//第二次
        min = arr[1];//从起一个位置开始比较
        minIndex = 1;//开始的索引
        for (int i = 1 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];//重置
                minIndex = i;
            }
        }
        //如果minIndex没有变化
        if (minIndex != 0) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        //第三次次
        min = arr[2];//从起一个位置开始比较
        minIndex = 2;//开始的索引
        for (int i = 2 + 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];//重置
                minIndex = i;
            }
        }
        //如果minIndex没有变化
        if (minIndex != 0) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }*/
    }
}
