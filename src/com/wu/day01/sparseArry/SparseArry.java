package com.wu.day01.sparseArry;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class SparseArry {
    public static void main(String[] args) {
        //创建一个二维数组，用于存储棋子
        //0表示当前位置无棋子，1表示黒棋，2表示白棋
        int[][] chessArr1=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        chessArr1[4][5]=2;
        System.out.println("原始数组为------");
        for (int[] date:chessArr1) {
            for(int row:date){
                System.out.printf("%d\t",row);
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //先遍历二维数获取有效值个数，sum
        int sum=0;
        for (int i = 0; i <11 ; i++) {
            for (int j=0; j<11; j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];
        //给数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组 将非0数据赋值给稀疏数组
        int count=0;//用于记录非0数据的位置
        for (int i = 0; i <11 ; i++) {
            for (int j=0; j<11; j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀释数组为-----");
        for (int i = 0; i <sparseArr.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        //将稀释数组恢复成二维数组
        int[][] chessArr2=new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i <sparseArr.length ; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        //转化后的二维数组
        System.out.println("转化后数组为------");
        for (int[] date:chessArr2) {
            for(int row:date){
                System.out.printf("%d\t",row);
            }
            System.out.println();
        }
    }
}
