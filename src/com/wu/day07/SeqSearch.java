package com.wu.day07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lynn
 * @date 2020/3/14 - 10:57
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] ={1,34,-2,6,3,86,6,31,5};//无序
        List<Integer> integers = seqSearch(arr, 8);
        if (integers !=null && integers.size()>0) {
            for (Integer i : integers
            ) {
                System.out.print(i + " ");
            }
        }else {
            System.out.println("没有查找到");
        }
    }
    public static List<Integer> seqSearch(int[] arr, int value){//有可能有多个相同的 存入集合
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] == value){
                list.add(i);
            }
        }
        return list;
    }
}
