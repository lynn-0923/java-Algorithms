package com.wu.day15.dynamic;

/**
 * @author lynn
 * @date 2020/3/23 - 14:13
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; // 物品的价值
        int m = 4; //背包的容量
        int n = val.length; // 物品的个数

        //创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入重量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //进行动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                //公式
                if (w[i - 1] > j) {//因为程序i是从1开始的
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为公式是从1开的 所以需要调整
//                    v[i][j]= max(v[i-1][j],v[i]+v[i-1][j-w[i]]);
//                    v[i][j]= Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);

                }
            }
        }
        //输出一下v看下情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }
}
