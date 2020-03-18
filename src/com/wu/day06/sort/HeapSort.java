package com.wu.day06.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/17 - 22:58
 */
public class HeapSort {
    public static void main(String[] args) {
        //调成大顶堆是升序的
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        /*int[] array=new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*8000000);
        }
        System.out.println("排序前");
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
        heapSort(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);*/
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
      /*  //分步完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第一次排序" + Arrays.toString(arr));//4,9,8,5,6}

        adjustHeap(arr, 0, arr.length);
        System.out.println("第二次排序" + Arrays.toString(arr));//{9,6,8,5,4}*/

        //完成最终的代码
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

    /**
     * 功能：将以i为节点的数调成大顶堆
     * 举例：int[] arr ={4,6,8,5,9}; => i= 1;adjustHeap =>{4,9,8,5,6}
     * 如果再次调用 i=0 => {9,6,8,5,4}
     *
     * @param arr    需要调整的数组
     * @param i      表示非叶子结点在数组中的索引
     * @param length //表示对多少个元素进行调整，length是在逐步减少的
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; //先取出当前元素的值 临时存放
        //开始调整
        //说明
        //1. k=i*2 +1 是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子节点小于右子节点
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {
                //交换
                arr[i] = arr[k];
                //注意 如果交换栈顶元素时 可能会破坏下面元素的结构 需要重新排序
                i = k; //指向k  继续循环比较；
            } else {
                break;//!
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值放在了最顶上 只是局部
        arr[i] = temp; //将temp放在调整后的位置
    }
}