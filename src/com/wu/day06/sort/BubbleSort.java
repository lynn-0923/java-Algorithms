package com.wu.day06.sort;

import com.sun.deploy.panel.ITreeNode;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/11 - 20:15
 */
public class BubbleSort {
    //冒泡排序
    public static void main(String[] args) {
//        int[] array = {3, 9, -1, 10, -2};
//        System.out.println("排序前");
//        String string = Arrays.toString(array);
//        System.out.println(string);
        //排序后
//        测试冒泡排序的速度
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
        bubbleSort(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
//        System.out.println(s);


    }

    //将冒泡排序封装成方法
    public static void bubbleSort(int[] array) {
        //用于交换数据
        int temp = 0;
        boolean flag = false; // 用来标志排序过程中是否有交换
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;//有交换
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "次排序");
//            System.out.println(Arrays.toString(array));
            if (!flag) {
                break;//没有过交换
            } else {
                flag = false;//重置！！！！ 每次循环都会进行判断
            }
        }
    }
}
