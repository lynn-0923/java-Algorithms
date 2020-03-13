package com.wu.day05;

import java.util.concurrent.CountDownLatch;

/**
 * @author lynn
 * @date 2020/3/11 - 14:20
 */
public class Queen8 {
    //定义一个数组 用于存放皇后
    int max = 8;//皇后的数量
    static int count=0;//多少方案
    static  int judgeCount=0;//判断的次数
    int[] array = new int[max];

    public static void main(String[] args) {
         Queen8 queen8=new Queen8();
         queen8.check(0);
        System.out.println("方案="+ count);
        System.out.println("判断的次数为="+ judgeCount);

    }

    //检查
    //注意check是每一次递归时 都会有循环
    public void check(int n) {
        if (n == 8) {//已经含有8个皇后了 直接打印输出
              print();
              return;
        }
        //依次将皇后放入数组 并且判断是否冲入
        for (int i = 0; i <max ; i++) {
            //将皇后放在每行的第一列
            array[n]=i;
            if (judge(n)){
                check(n+1);//接着放入
            }
            //如果有冲突 它会默认往右移
        }
    }
    //
    //校验前后的皇后是否冲突

    /**
     * array[i] == array[n] 表示是否在同一列
     * Math.abs(n-i) ==Math.abs(array[n]-array[i]) 表示是否在同一直线
     * 不需要判定在同一行 因为数组的下标就代表了行  所以不可能相同
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {//放入皇后的数量
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //打印数组
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
