package com.wu.day18.horse;

import java.awt.*;
import java.security.PolicySpi;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘=》骑士周游游戏
 *
 * @author lynn
 * @date 2020/3/26 - 19:32
 */
public class HorseChessBoard {
    private static int X; //列数
    private static int Y; //行数
    private static boolean[] isVisited;//创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean isFinished;//标记棋盘的各个位置都是否被访问过

    //创建一个数组，标记棋牌的各个位置是否被访问过

    public static void main(String[] args) {
        System.out.println("骑士周游问题:");
        //测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1;//表示从第一行开始走
        int column = 1;//表示从第一列开始走

        //创建棋盘
        int[][] chess = new int[X][Y];
        isVisited = new boolean[X * Y];

        //测试一下耗时
        long start = System.currentTimeMillis();
        traversalChessBoard(chess, column - 1, row - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "毫秒");

        //输出棋盘的最后情况
        for (int[] rows : chess) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 功能：完成骑士周游问题
     *
     * @param chessBoard 棋盘
     * @param column     马儿的起始位置的列
     * @param row        马儿的起始位置的行
     * @param step       是第几步，起始位置为1步
     */
    public static void traversalChessBoard(int[][] chessBoard, int column, int row, int step) {
        //初始化第一步
        chessBoard[row][column] = step;
        // row = 4 X = 8 column = 4
        //标记当前位置已访问
        isVisited[row * X + column] = true;
        //获取当前位置还可以走的下一个位置的集合
        ArrayList<Point> next = next(new Point(column, row));
        //对next进行排序，排序的规则就是对next所有的point对象的下一步的位置的数目，进行非递减排序
        sort(next);
        //遍历
        while (!next.isEmpty()) {
            Point p1 = next.remove(0);
            //判断该点是否被访问过
            if (!isVisited[p1.y * X + p1.x]) {//说明还没有被访问过
                traversalChessBoard(chessBoard, p1.x, p1.y, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用step和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将棋盘置0
        //说明: step < X * Y  成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (step < X * Y && !isFinished) {
            chessBoard[row][column] = 0;
            isVisited[row * X + column] = false;
        } else {
            isFinished = true;//结束
        }

    }

    /**
     * 功能：根据当前位置(point对象) 计算马儿还能走那些位置 并存入arrayList中 最多八个
     *
     * @param curPoint 当前位置
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个point对象
        Point p1 = new Point();
        //判断马儿可以走哪些位置
        //表示马儿可以走5这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这步的所有的下一步选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
