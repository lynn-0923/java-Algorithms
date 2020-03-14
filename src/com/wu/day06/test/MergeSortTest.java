package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/14 - 9:37
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 6, 7, 1, 3, 6, 2, 67, 45, 8, 34};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    //归并排序  采用分治策略
    //分和并
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左分解
            mergeSort(arr, left, mid, temp);
            //向右分解
            mergeSort(arr, mid + 1, right, temp);
            //并
            merge(arr, left, mid, right, temp);
        }
    }

    //并的方法
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        //第一步
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //第二步
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //第三步
        int index = 0;
        while (left <= right) {
            arr[left++] = temp[index++];
        }
    }
}
