package com.wu.day15.kmp;

/**
 * @author lynn
 * @date 2020/3/23 - 17:00
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "所有遗憾的都不是未来，所有爱，最后都难免逃不过伤害";
        String s2 = "所有爱_，最后都难免逃不过伤害";
        int i = violenceMatch(s1, s2);
        System.out.println(i);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return -1;//
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1len = s1.length;
        int s2len = s2.length;

        int i = 0; //表示指向s1的索引
        int j = 0; //表示指向s2的索引
        while (i < s1len && j < s2len) { //保证匹配时不越界
            if (s1[i] == s2[j]) {//匹配成功
                i++;
                j++;
            } else {//未匹配成功
                i = i - (j - 1);//回到匹配前的下一个字符
                j = 0;
            }
        }
        if (j == s2len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
