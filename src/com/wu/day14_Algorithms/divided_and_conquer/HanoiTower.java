package com.wu.day14_Algorithms.divided_and_conquer;

/**
 * @author lynn
 * @date 2020/3/22 - 21:16
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔摆放
     *
     * @param num 盘的数量
     * @param A   //代表塔
     * @param B
     * @param C
     */
    public static void hanoiTower(int num, char A, char B, char C) {
        //只有一个数
        if (num == 1) {
            System.out.println("第1个盘从" + A + " -> " + C);
        } else {
            //如果我们有n >= 2的情况 我们总是可以看做是两个盘 ：最下面的盘和最上面所有的盘
            //1.先把上面所有盘移动 A->B 中间会用到C
            hanoiTower(num - 1, A, C, B);
            //2.把最下面的盘 A->C
            System.out.println("第" + num + "个盘从" + A + " -> " + C);
            //3.把B所有的盘从B->C,移动过程中使用到A
            hanoiTower(num - 1, B, A, C);
        }
    }
}
