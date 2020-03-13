package com.wu.day06.sort;

import com.wu.day02.test.Josephu;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/12 - 23:27
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 3,11,6,34 ,7, 9, 5, 1, 6, 8};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        /*int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 8000000);
        }
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
//        shellSort(array);
        quickSort(array,0,array.length-1);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);*/
    }

    public static void quickSort(int[] arr, int left, int right) {
        //进行判断，如果左边的数值大于右边的 不合理直接退出
        if (left > right) {
            return;
        }
        //定义左边的索引
        int i = left;
        //定义右边的索引
        int j = right;
        //定义基准数  都以数组的左边为基准数
        int base = arr[left];
        //临时存放数值
        int temp = 0;
        //循环 当二者不相遇的时候一直检索
        //注意！！！左边的数为基准数的时候 从右边开始检索
        while (i != j) {
            //当右边的数大于或等于基准数的时候 继续检索 左移,且i不能大于j
            while (arr[j] >= base && i < j) {
                j--;
            }
            //当左边的数小于或等于基准数时 继续检索 右移 同样 i<j
            while (arr[i] <= base && i < j) {
                i++;
            }
            //当二者停下来的时候 可以交换数据
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //如果二者已将相遇  则将基准数和相遇的位置交换
        arr[left] = arr[i];
        arr[i] = base;

        //基准数归位是为了，左边的数字都比他小 右边的数字都比他大
        //排序左边
        quickSort(arr, left, i - 1);

        //排序右边
        quickSort(arr, i + 1, right);

    }
}
