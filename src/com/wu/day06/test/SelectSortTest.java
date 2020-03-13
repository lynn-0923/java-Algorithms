package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/13 - 9:36
 */
public class SelectSortTest {
    public static void main(String[] args) {
        int[] arr = {24, 5, 2, 6, 5, 7, 4, 34, 65, 3};
        selectSortTest(arr);
        System.out.println(Arrays.toString(arr));
    }

    //选择排序
    //第一次选择一个最小的数放在第一位
    //之后每次选择该数后面最小的数放在该数后面
    public static void selectSortTest(int[] arr) {
        //最小数的下标
        for (int j = 0; j < arr.length - 1; j++) {
            int minIndex = j;
            int minValue = arr[j];//最小数的值
            for (int i = j + 1; i < arr.length; i++) {//从第二个数开始和它比较
                if (arr[i] < minValue) {//依次找最小的数
                    minValue = arr[i];
                    minIndex = i;
                }
            }
            //找到后 交换位置
            //如果minIndex没有变化
            if (minIndex != 0) {
                arr[minIndex] = arr[j];
                arr[j] = minValue;
            }
        }
     /*   //第一轮
        int minIndex=0;
        int minValue=arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i+1] <minValue){
                minValue=arr[i+1];
                minIndex=i+1;
            }
        }
        arr[minIndex]=arr[0];
        arr[0]=minValue;*/

    /*    //第一轮
         minIndex=1;
        minValue=arr[1];
        for (int i = 1; i < arr.length-1; i++) {
            if (arr[i+1] <minValue){
                minValue=arr[i+1];
                minIndex=i+1;
            }
        }
        arr[minIndex]=arr[1];
        arr[1]=minValue;*/
    }
}
