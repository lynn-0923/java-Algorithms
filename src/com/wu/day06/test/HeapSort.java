package com.wu.day06.test;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/18 - 10:02
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,4,76,23,53};
        heapSort(arr);
    }

    //堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;

        //将一个无序序列构建成一个堆，根据升序降序选择大顶堆还是小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //2.将栈顶元素与末尾元素交换，将最大元素沉到数组末端
        //3.重新调整结构 使其满足堆定义，然后继续交换栈顶元素与当前末尾元素，反复执行调整加+交换步骤。直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {//需调整的数-1
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
                System.out.println("大顶堆" + Arrays.toString(arr));//{9,6,8,5,4}

    }
    //调整数组为大顶堆或小顶堆 根据升序或降序来选择

    /**
     * 将数组调整为大顶堆
     *
     * @param arr    需要调整的数组
     * @param i      非叶子结点的索引
     * @param length 数组的长度
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //临时存放 便于后续交换
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                //左子节点小于右子节点
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k; //进行下次循环 ，注意 往上进行排序的时候 下面的大顶堆可能会被破坏 需重新排序
            } else {
                break;//无需交换
            }
        }
        arr[i] = temp;
    }
}
