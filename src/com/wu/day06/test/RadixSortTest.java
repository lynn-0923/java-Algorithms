package com.wu.day06.test;

import javax.xml.transform.Source;
import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/14 - 10:12
 */
public class RadixSortTest {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];

        //代表对应桶中数据的个数
        int[] bucketElementCount = new int[10];
        //遍历数组 获取最大元素的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int k = 0; k < maxLength; k++) {
            //遍历数组 按照个位数的顺序放入桶
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / (int) (Math.pow(10, k)) % 10;
                bucket[digit][bucketElementCount[digit]] = arr[i];
                bucketElementCount[digit]++;
            }
            //排序后重新取出数据放入数组
            int index = 0;
            //遍历每个桶
            for (int i = 0; i < bucketElementCount.length; i++) {
                //判断桶中是否有数据
                if (bucketElementCount[i] != 0) {
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                //重置 方便下一轮使用
                bucketElementCount[i] = 0;
            }
        }

    }
}
