package com.wu.day16.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 *
 * @author lynn
 * @date 2020/3/25 - 13:49
 */
public class KruskalDemo {
    private int edgeNum; //边的数量
    private char[] vertexs; //顶点的数量
    private int[][] matrix; //邻接矩阵

    //使用INF表示两个顶点不能连通
    public static final int INF = Integer.MAX_VALUE;//

    public static void main(String[] args) {
        //测试
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'G', 'F'};
        //克鲁斯卡尔的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        //构建实例
        KruskalDemo kruskalDemo = new KruskalDemo(vertexs, matrix);
        //打印
//        kruskalDemo.print();
        //测试获得边
       /* EData[] edges = kruskalDemo.getEdges();
        kruskalDemo.sortEData(edges);
        System.out.println(Arrays.toString(edges));*/
        //测试最终的克鲁斯卡尔算法 生成的最小生成树
        kruskalDemo.kruskal();
    }

    //克鲁斯卡尔算法
    public void kruskal() {
        int index = 0; //表示最后结果的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中的每个顶点的在最小生成树中的额终点
        //创建结果数组，保存最后的最小生成树，大小为边的数量，依旧是顶点的数量-1
        EData[] res = new EData[vertexs.length - 1];
        //获取图中所有边的集合，共12
        EData[] edges = getEdges();
        //按权值从小到大排序
        sortEData(edges);

        //遍历edges数组，将边添加到最小生成树种，判断是准备加入的边是否形成了回路 ，如果没有则加入res,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p1在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            //判断是否形成回路
            if (m != n) {
                ends[m] = n; //设置m在"已有最小生成树"中的终点<E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                res[index++] = edges[i];
            }
        }
        //打印“最小生成树”
        System.out.println(Arrays.toString(res)); //< E,G>= 2], EData[< C,D>= 3], EData[< D,E>= 4], EData[< B,G>= 7], EData[< E,F>= 8], EData[< A,B>= 12
    }

    //打印邻接矩阵
    public void print() {
        System.out.printf("打印邻接矩阵:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-15d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行处理 冒泡排序
    public void sortEData(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 功能：获取图中的边，放到EData[] 数组中 后期遍历需要用
     * 是通过matrix邻接矩阵来获取
     * EData[]形式 [['A','B' 12].......]
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {//j = i +1 =》不统计自己
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 返回顶点对应的下标
     *
     * @param ch 顶点的值 比如 'A' 'B'
     * @return 返回ch对应的下标 如果没找到则返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于判断后面两个顶点是否相等
     *
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i    表示传入的顶点的对应的下标
     * @return 返回的是下标为i的这个顶点对应终点的下标，
     */
    private int getEnd(int[] ends, int i) {// i = 5 [0,0,0,0,5,0,0,0,0,0,0,0]
        while (ends[i] != 0) { //不需要存入本身
            i = ends[i];
        }
        return i;//如果还未加入，则返回本身这个点
    }

    //构造器
    public KruskalDemo(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vLen = vertexs.length;

        //初始化顶点,复制拷贝的方式
        this.vertexs = new char[vLen];
        for (int i = 0; i < vLen; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，复制拷贝的方式
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {//不统计自己
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }
}

//创建EData ，它的对象实例表示一条边
class EData {
    char start;//边的一个点
    char end; // 边的另一个点
    int weight; //边的权值

    //构造器

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    //重写toString 方便打印

    @Override
    public String toString() {
        return "EData[" + "< " + start + "," + end + ">= " + weight + ']';
    }
}