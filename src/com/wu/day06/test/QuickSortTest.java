package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/13 - 11:07
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] arr = {6, 3,11,2,34 ,7, 9, 5, 1, 6, 8,54,32,51};
        quickSortTest(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    //找到一个基准值 从左右两边开始检索
    //如果左边检索到比基准值大的就停下
    //如果右边检索到比基准值小的就停下
    public static void quickSortTest(int[] arr, int left, int right) {
        if (left >right){
            return;
        }
        int i = left;//左索引
        int j = right;//右索引
        int base = arr[left];//基准值
        int temp=0;
        while (i != j) {//未相遇的情况下就一直检索
            while (arr[j] >= base && i < j) {//检索的值大于等于基准值 就接着检索 左移
                j--;
            }
            while (arr[i] <= base && i < j) {//检索的值小于等于基准值 就接着检索 右移
                i++;
            }
            //当二者停下来的时候交换数据
            temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        if (i == j){
            //相遇 将该位置的值和基准值兑换
            arr[left]=arr[i];
            arr[i]=base;
        }
        //左检索
        quickSortTest(arr,left,i-1);
        //右检索
        quickSortTest(arr,i+1,right);
    }
}
