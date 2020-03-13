package com.wu.day06.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lynn
 * @date 2020/3/12 - 14:00
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 89};
//        insertSort(arr);
        int[] array=new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i]=(int)(Math.random()*80000);
        }
        System.out.println("排序前");
        Date date1=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        //排序后
        insertSort(array);
//        String s=Arrays.toString(array);
        System.out.println("排序后");
        Date date2=new Date();
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String format2 = simpleDateFormat2.format(date2);
        System.out.println(format2);
    }

    //插入排序
    public static void insertSort(int[] arr) {

        //获取数组的第一个位置 假定为有序排序 ，之后的数据都为无序排序
        //逐步推导

      int insertIndex;//待插入的位置
        int insertValue;//待插入的数据
        for (int i = 1; i < arr.length; i++) {
             insertValue = arr[i];//待插入的数据
            for (insertIndex = i - 1; insertIndex >= 0; insertIndex--) {
                if (insertValue < arr[insertIndex]) {
                    arr[insertIndex + 1] = arr[insertIndex];//将其后移
                }else {
                    break;//如果 不比它大 则直接跳出
                }
            }
            arr[insertIndex + 1] = insertValue;//交换值
            /*System.out.println("第" + i + "次排序");
            System.out.println(Arrays.toString(arr));*/
        }

        //第一次排序  int[] arr={101,34,119,1} -》{34,101,119,1}
      /*  for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];//待插入的数据
            int insertIndex = i - 1;//待插入的位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将其后移
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;//交换值
//            System.out.println("第" + i + "次排序");
//            System.out.println(Arrays.toString(arr));
        }*/
      /*  //第二次排序  int[] arr={101,34,119,1} -》{34,101,119,1}
        insertValue=arr[2];//待插入的数据
        insertIndex=2-1;//待插入的位置
        while (insertIndex >=0 && insertValue <arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];//将其后移
            insertIndex--;//再让其和前面的数比较
        }
        //当退出循环的时候 位置已经找到
        arr[insertIndex+1]=insertValue;//交换值
        System.out.println(Arrays.toString(arr));

        //第三次排序  int[] arr={34,101,119,1} -》{1,34,101,119}
        insertValue=arr[3];//待插入的数据
        insertIndex=3-1;//待插入的位置
        while (insertIndex >=0 && insertValue <arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];//将其后移
           insertIndex--;
        }
        arr[insertIndex+1]=insertValue;//交换值
        System.out.println(Arrays.toString(arr));
*/
    }
}
