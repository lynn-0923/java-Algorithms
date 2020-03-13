package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/13 - 10:43
 */
public class ShellSortTest {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, 11, 10, 13, 14, 12, 16};
        shellSortTest(arr);
    }

    //希尔排序  是插入排序的优化版
    //先分组 例如 有十个元素的话 先分成5组 每组两个元素 进行排序
    public static void shellSortTest(int[] arr) {

        for (int gap =arr.length /2 ; gap >0; gap /=2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertValue = arr[insertIndex];
                while (insertIndex - gap >= 0 && arr[insertIndex - gap] > insertValue) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = insertValue;
            }
            System.out.println(Arrays.toString(arr));
        }
        /* //第一轮，分层8组
        for (int i = 8; i <arr.length ; i++) {
                int insertIndex=i;
                int insertValue=arr[insertIndex];
                while (insertIndex -8 >=0 && arr[insertIndex-8] >insertValue ){
                    arr[insertIndex]=arr[insertIndex-8];
                    insertIndex -=8;
                }
                arr[insertIndex]=insertValue;
            }
        System.out.println(Arrays.toString(arr));

        //第二轮，分层4组
        for (int i = 4; i <arr.length ; i++) {
            int insertIndex=i;
            int insertValue=arr[insertIndex];
            while (insertIndex -4 >=0 && arr[insertIndex-4] >insertValue ){
                arr[insertIndex]=arr[insertIndex-4];
                insertIndex -=4;
            }
            arr[insertIndex]=insertValue;
        }
        System.out.println(Arrays.toString(arr));*/
    }
}
