package com.wu.day15.kmp;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/23 - 18:50
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD_;

        int[] kmpNext = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(kmpNext));

        int i = kmpSearch(str1, str2, kmpNext);
        System.out.println(i);
    }



    /**
     * 写出kmp搜索算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next kpm表
     * @return 如果匹配成功则返回下标 未成功则返回-1
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0 , j = 0; i < str1.length() ; i++) {
            //str1.charAt(i) ！= str2.charAt(j) 去调整j的大小
            //kmp算法的核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){//找到了 j = 3, i=2
                return i - j + 1;
            }
        }
        return -1;
    }
    //获取到一个字符串(子串)的部分匹配表
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串长度是1的话，部分匹配值的长度也为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1] 获取新的j
            //直到我们发现有 dest.charAt(i) == dest.charAt(j) 才退出
            //这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
