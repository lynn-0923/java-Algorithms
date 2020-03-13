package com.wu.day01.test;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class SparseTest {
    public static void main(String[] args) {
        //构建二维数组
        int[][] chessTest = new int[11][11];
        chessTest[4][5] = 2;
        chessTest[5][6] = 1;
        //获取数组有效元素个数
        int sum = 0;//记录有效元素个数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessTest[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("原始数组为-----------");
        for(int[] data:chessTest){
            for (int i = 0; i <data.length ; i++) {
                System.out.printf("%d\t",data[i]);
            }
            System.out.println();
        }
        System.out.println(sum);
        //构建稀疏数组
        int[][] sparseTest = new int[sum + 1][3];
        sparseTest[0][0] = 11;
        sparseTest[0][1] = 11;
        sparseTest[0][2] = sum;
        //获取数组有效元素的下表
        int count = 0;//记录稀释数组的下标
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessTest[i][j] != 0) {
                    count++;
                    sparseTest[count][0] = i;
                    sparseTest[count][1] = j;
                    sparseTest[count][2] = chessTest[i][j];
                }
            }
        }
       //遍历稀释数组
        System.out.println("稀疏数组为------");
        for (int[] sparse:sparseTest
             ) {
            for (int row:sparse
                 ) {
                System.out.printf("%d\t",row);
            }
            System.out.println();
        }
        //将稀释数组转回二维数组
        int[][] chessArr=new int[sparseTest[0][0]][sparseTest[0][1]];
        for (int i = 1; i <=sum ; i++) {
            chessArr[sparseTest[i][0]][sparseTest[i][1]]=sparseTest[i][2];
        }
        System.out.println("重新转化为二维数组");
        for(int[] value:chessArr){
            for (int i = 0; i <value.length ; i++) {
                System.out.printf("%d\t",value[i]);
            }
            System.out.println();
        }

    }
}
