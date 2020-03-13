package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/13 - 10:03
 */
public class InsertSortTest {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        insertSortTest(arr);
    }
    //插入排序f
    //以第一个数为为标准 该数后面的如果比前面的数小则后移
    public static void insertSortTest(int[] arr){
        //双for循环
        for (int i = 0; i < arr.length-1 ; i++) {
        int insertValue=arr[i+1];
        int insertIndex=i;
//           for(insertIndex >=0 && arr[insertIndex] > insertValue){
           for(insertIndex=i; insertIndex>=0;insertIndex --){
               if (arr[insertIndex] > insertValue) {
                   arr[insertIndex + 1] = arr[insertIndex];//后移
               }else {
                   break;
               }
            }
            arr[insertIndex+1]=insertValue;
        System.out.println(Arrays.toString(arr));
        }

   /*     for (int i = 0; i < arr.length-1 ; i++) {
            int insertValue=arr[i+1];
            int insertIndex=i;
            while (insertIndex >=0 && arr[insertIndex] > insertValue){
                arr[insertIndex+1]=arr[insertIndex];//后移
                insertIndex --;
            }
            arr[insertIndex+1]=insertValue;
            System.out.println(Arrays.toString(arr));

        }*/
        /*//第二轮
          insertValue=arr[1+1];
                insertIndex=1;
                    while (insertIndex >=0 && arr[insertIndex] > insertValue){
                        arr[insertIndex+1]=arr[insertIndex];//后移
                        insertIndex --;
                    }
                    arr[insertIndex+1]=insertValue;
                System.out.println(Arrays.toString(arr));*/
    }
}
