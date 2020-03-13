package com.wu.day05;

/**
 * @author lynn
 * @date 2020/3/11 - 10:06
 */
public class MiGong {
    public static void main(String[] args) {
        //使用二维数组模拟迷宫
        int[][] map = new int[8][7];
        //1代表墙
        //先将左右两设为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //将上下两边设为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //设置两个挡板
        map[3][1] = 1;
        map[3][2] = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //使用递归找路
        setWay(map, 1, 1);
        System.out.println("路线");
        //引用数据类型，可以改变
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //使用递归来回溯迷宫小球找路
    //1.map表示地图
    //2.i,j表示起始位置
    //3.map[6][5]表示路已找到
    //4.约定 map[i][j]为0表示还未走过，1表示为墙，2表示可以走通，3表示已走过且不同
    //5.约定一个策略 下-》左-》上-》-》右
    //定义一个方法 判定能否走通

    /**
     * @param map 传入的地图
     * @param i   从哪个位置开始找
     * @param j
     * @return 如果找到通路则返回true
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//表示已经找到了
            return true;
        } else {
            if (map[i][j] == 0) {
                //表示还未走过
                map[i][j] = 2;//假定可以走的通
                //按着路线走
                if (setWay(map, i + 1, j)) {
                    return true;//向下 表示走的通
                } else if (setWay(map, i, j + 1)) {
                    return true;//向右
                } else if (setWay(map, i - 1, j)) {
                    return true;//向上
                } else if (setWay(map, i, j - 1)) {
                    return true;//向左
                } else {
                    //完全走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //表示非0 则为 1,2,3
                return false;
            }
        }
    }
}
