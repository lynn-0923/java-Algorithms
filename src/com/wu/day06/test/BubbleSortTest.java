package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/13 - 9:16
 */
public class BubbleSortTest {
    public static void main(String[] args) {
         int[] arr={1,5,2,6,5,7,4,34,65,3};
         bubbleSortTest(arr);
        System.out.println(Arrays.toString(arr));
    }

    //冒泡排序
    //思想
    //从第一个数  相邻的数可是比较 如果逆序 则交换位置  第一轮后将会选出最大的位置
    //之后接着从第一个是数到最后一个数的前一个数  因为最后一个数已经确定了
    //共进行 数组长度-1轮
    public static void bubbleSortTest(int[] arr){

        int temp;//临时存放值
        boolean flag=false;
        for (int j = 0; j <arr.length-1 ; j++) {
            for (int i = 0; i <arr.length-1-j ; i++) {
                if (arr[i+1] < arr[i]){
                    flag=true;//标志着该轮数值是由有交换
                    temp=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;//交换
                }
            }
            if(!flag){
                break;
            }else {
                flag=false;//重置
            }
        }
    }
}
