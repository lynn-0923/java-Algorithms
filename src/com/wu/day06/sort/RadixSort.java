package com.wu.day06.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/13 - 22:10
 */
public class RadixSort {
    public static void main(String[] args) {
        /*int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);*/
        //测试
        int[] array = new int[8000000];
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
        radixSort(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
    }

    public static void radixSort(int[] arr) {
        //基数排序
        //创建一个二维数组 共十个， 代表桶的位置
        //思想：先将每个数按个位数排序 依次放入桶中

        //创建二维数组，为了防止数据溢出 每个桶的初始大小设为数组的长度
        int[][] bucket = new int[10][arr.length];//有十个桶 每个桶最多放置arr数组长度的数据

        //为了方便记录每个桶中数据的个数，创建一个一维数组
        //例如 bucketElementCount[0]就代表0号桶中数据的个数
        int[] bucketElementCount = new int[10];

        //使用for循环来简化代码
        //获取数组最大元素的长度
        int max=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if (arr[i] >max){
                max=arr[i];
            }
        }
        //获取长度
        int maxLength=(max+ "").length();
        for (int k = 0; k <maxLength ; k++) {//根据数的位数
            for (int i = 0; i < arr.length; i++) {
                //获取每个元素对应的位数
                int digit = arr[i] /(int) (Math.pow(10,k)) % 10;//digit就代表几号桶
                bucket[digit][bucketElementCount[digit]] = arr[i];//此处bucketElementCount[digit] 为0 因为未初始化 默认为0 刚好代表下标
                bucketElementCount[digit]++;//代表digit号桶中的数据加1，且后移 方便存储下一个位置
            }

            //按照桶的顺序 依次取出放入arr数组中
            int index = 0;
            //遍历每个桶
            //先判断桶中是否有数据
            for (int i = 0; i < bucketElementCount.length; i++) {
                if (bucketElementCount[i] != 0) {
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                //注意 每次循环完毕 将桶内的数据清零 方便下次存放
                bucketElementCount[i] = 0;
            }
//            System.out.println("第"+(k+1)+"轮排序");
//            System.out.println(Arrays.toString(arr));
        }
      /*  //第一轮 依次遍历数组 按个位数进行排序依次放入桶中
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] % 10;//digit就代表几号桶
            bucket[digit][bucketElementCount[digit]] = arr[i];//此处bucketElementCount[digit] 为0 因为未初始化 默认为0 刚好代表下标
            bucketElementCount[digit]++;//代表digit号桶中的数据加1，且后移 方便存储下一个位置
        }

        //按照桶的顺序 依次取出放入arr数组中
        int index = 0;
        //遍历每个桶
        //先判断桶中是否有数据
        for (int i = 0; i < bucketElementCount.length; i++) {
            if (bucketElementCount[i] != 0) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            //注意 每次循环完毕 将桶内的数据清零 方便下次存放
            bucketElementCount[i] = 0;
        }
        System.out.println("第一轮排序");
        System.out.println(Arrays.toString(arr));


        //第二轮 依次遍历数组 按十位数进行排序依次放入桶中
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] /10 % 10;//digit就代表几号桶
            bucket[digit][bucketElementCount[digit]] = arr[i];//此处bucketElementCount[digit] 为0 因为未初始化 默认为0 刚好代表下标
            bucketElementCount[digit]++;//代表digit号桶中的数据加1，且后移 方便存储下一个位置
        }

        //按照桶的顺序 依次取出放入arr数组中
         index = 0;
        //遍历每个桶
        //先判断桶中是否有数据
        for (int i = 0; i < bucketElementCount.length; i++) {
            if (bucketElementCount[i] != 0) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            //注意 每次循环完毕 将桶内的数据清零 方便下次存放
            bucketElementCount[i] = 0;
        }
        System.out.println("第二轮排序");
        System.out.println(Arrays.toString(arr));

        //第三轮 依次遍历数组 按十位数进行排序依次放入桶中
        for (int i = 0; i < arr.length; i++) {
            int digit = arr[i] /100 % 10;//digit就代表几号桶
            bucket[digit][bucketElementCount[digit]] = arr[i];//此处bucketElementCount[digit] 为0 因为未初始化 默认为0 刚好代表下标
            bucketElementCount[digit]++;//代表digit号桶中的数据加1，且后移 方便存储下一个位置
        }

        //按照桶的顺序 依次取出放入arr数组中
        index = 0;
        //遍历每个桶
        //先判断桶中是否有数据
        for (int i = 0; i < bucketElementCount.length; i++) {
            if (bucketElementCount[i] != 0) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
            }
            //注意 每次循环完毕 将桶内的数据清零 方便下次存放
            bucketElementCount[i] = 0;
        }
        System.out.println("第三轮排序");
        System.out.println(Arrays.toString(arr));*/
    }
}


