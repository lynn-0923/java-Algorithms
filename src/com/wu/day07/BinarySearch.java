package com.wu.day07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lynn
 * @date 2020/3/14 - 11:16
 */
//二分查找到的数组必须是有序的
public class BinarySearch {
    public static void main(String[] args) {
       /* int[] arr = {1, 2, 6, 7, 9, 67, 78, 86};
        int i = binarySearch(arr, 0, arr.length - 1, 7);
        if (i == -1) {
            System.out.println("未找到");
        } else {
            System.out.println(i);
        }*/

        int[] arr = {5, 5, 5, 8, 10, 1000, 1234};
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 9);
        if (list.size() > 0 && list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("没有找到");
        }
    }

    /**
     * @param arr   数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param value 待查找的值
     * @return 找到查找值的下标
     */
    public static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        ArrayList<Integer> list = new ArrayList<>();//用来存放找到的索引
        if (left > right) {
            return -1;
        }
        if (arr[mid] > value) {//中间值大于查找的值
            //向左递归
            return binarySearch(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {//中间值小于查找的值
            //向右递归
            return binarySearch(arr, mid + 1, right, value);
        } else {
            return mid;
        }
    }
    //课后思考题 ：{1,8,10,1000,1000,1234} 当一个有序数组有多个相同的值时  将其全部找到

    /**
     * @param arr   数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param value 待查找的值
     * @return 找到查找值的下标
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        List<Integer> list = new ArrayList<>();//用来存放找到的索引
        if (left > right) {
            return new ArrayList<Integer>();//未找到 返回空的
        }
        if (arr[mid] > value) {//中间值大于查找的值
            //向左递归
            return binarySearch2(arr, left, mid - 1, value);
        } else if (arr[mid] < value) {//中间值小于查找的值
            //向右递归
            return binarySearch2(arr, mid + 1, right, value);
        } else {
            //找到了 mid不要急着返回
            //接着向左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {//退出
                    break;
                }
                //将temp添加到集合中
                list.add(temp);
                temp -= 1;
            }
            list.add(mid);//等于的直接添加进集合
            //向右边扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }
}
