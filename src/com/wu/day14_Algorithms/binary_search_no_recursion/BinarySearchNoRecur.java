package com.wu.day14_Algorithms.binary_search_no_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lynn
 * @date 2020/3/22 - 20:04
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {5, 5, 5, 8, 10, 1000, 1234};
        List<Integer> integerList = binarySearchNoRecur(arr, 8);
        if (integerList.size() > 0 && integerList != null) {
            for (int i = 0; i < integerList.size(); i++) {
                System.out.println(integerList.get(i));
            }
        } else {
            System.out.println("没有找到");
        }
    }

    /**
     *  二分查找非递归实现 升序
     * @param arr 待查找的数组
     * @param target 待查找的值
     * @return 如果找到则返回 未找到则返回空
     */
   public static ArrayList<Integer> binarySearchNoRecur(int[] arr , int target){
       ArrayList<Integer> list = new ArrayList();
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                int temp = mid - 1;
                while (true) {
                    if (temp < 0 || arr[temp] != target) {
                        break;
                    }
                    list.add(temp);
                    temp -= 1;
                }
                list.add(mid);
               temp = mid + 1;
                while (true) {
                    if (temp > arr.length - 1 || arr[temp] != target) {
                        break;
                    }
                    list.add(temp);
                    temp += 1;
                }
                return list;//未找到则为null
            }else if(arr[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return new ArrayList<Integer>();
   }
}
