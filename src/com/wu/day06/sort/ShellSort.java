package com.wu.day06.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/12 - 19:21
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0,11,10,13,14,12,16};
//        shellSort2(arr);
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
//        shellSort(array);
        shellSort2(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
    }

    //希尔排序  -- 交换
    //逐步推导的方法
    //第一轮 按10/2=5 组分组排序
    public static void shellSort(int[] arr) {
        int temp;//临时存放
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//分组的次数
            for (int i = gap; i < arr.length; i++) {
                //每组两个元素 步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {//让其只进行一轮排序
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第" + (++count) + "次排序后");
//            System.out.println(Arrays.toString(arr));
        }
      /*  //第一次排序
        for (int i = 5; i < arr.length; i++) {
            //共五组 每组两个元素 步长为5
            for (int j = i - 5; j >= 0; j -= 5) {//让其只进行一轮排序
                 if (arr[j] >arr[j+5]){
                     temp=arr[j];
                     arr[j]=arr[j+5];
                     arr[j+5]=temp;
                 }
            }
        }
        System.out.println("第一次排序后");
        System.out.println(Arrays.toString(arr));

        //第二次排序 5/2
        for (int i = 2; i < arr.length; i++) {
            //共两组 每组五个元素 步长为2
            for (int j = i - 2; j >= 0; j -= 2) {//
                if (arr[j] >arr[j+2]){
                    temp=arr[j];
                    arr[j]=arr[j+2];
                    arr[j+2]=temp;
                }
            }
        }
        System.out.println("第二次排序后");
        System.out.println(Arrays.toString(arr));

        //第三次排序 2/2=1 组 十个元素 步长为1
        for (int i = 1; i < arr.length; i++) {
            //共两组 每组五个元素 步长为2
            for (int j = i - 1; j >= 0; j -= 1) {//
                if (arr[j] >arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        System.out.println("第三次排序后");
        System.out.println(Arrays.toString(arr));
    */
    }

    // 对交换式的希尔排序进行优化 -- 移位法  也就是插入排序
    public static void shellSort2(int[] arr) {
        //先分组 再进行插入排序
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;//插入的位置
                int insertValue = arr[insertIndex];//要插入的值
                while (insertIndex - gap >= 0 && arr[insertIndex] < arr[insertIndex - gap]) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                arr[insertIndex] = insertValue;
            }
//            System.out.println("第"+(++count)+"次排序");
//            System.out.println(Arrays.toString(arr));
        }
    }
}