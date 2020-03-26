package com.wu.day17.dijkstra;

import java.util.Arrays;

/**
 * @author lynn
 * @date 2020/3/25 - 20:35
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
//        graph.showGraph();

        //测试迪杰斯特拉算法
        graph.djs(2);
        //显示结果
        graph.showDijkstra();//A(7) B(12) C(0) D(17) E(8) F(13) G(9)
    }
}

class Graph {
    private char[] vertex; // 顶点数组
    private int[][] matrix; // 邻接矩阵
    private VisitedVertex vv; //已访问的顶点的集合

    // 构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }


    //显示结果
    public void showDijkstra() {
        vv.show();
    }

    // 显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 出发顶点的下标
     */
    public void djs(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//继续选择并访问新的顶点
            update(index);
        }
    }

    //更新index下标顶店到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len的含义是：出发顶点到index顶点的距离 + index顶点到i顶点的距离 动态更新
            len = vv.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发点到i顶点的距离 就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);//更新i顶点的前驱顶点为index 动态更新
                vv.updateDis(i, len);//更新i到出发顶点的距离 动态更新
            }
        }
        /*System.out.println(Arrays.toString(vv.pre_visited));
        System.out.println(Arrays.toString(vv.already_arr));
        System.out.println(Arrays.toString(vv.dis));*/
    }
}

//已访问顶点的结合
class VisitedVertex {
    //记录各个顶点是否访问过 1表示访问过 0表示未访问过
    public int[] already_arr;
    //每个下标对应的值为前一个顶点的下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，求的最短距离就会放到dis
    public int[] dis;

    //构造器

    /**
     * @param index  ：出发顶点对应的下标，比如G顶点，下标就是6
     * @param length ：表示顶点的个数
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;//设置出发顶点已被访问过
        this.dis[index] = 0;//设置出发顶点的距离为0
    }

    /**
     * 功能：判断index是否被访问过
     *
     * @param index
     * @return 如果访问过则返回true，否则false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能：更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 功能：更新pre这个顶点的前驱结点为index结点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能：返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 功能：继续选择并返回新的顶点，例如这里的G顶点访问完后，就是A作为新的访问点（注意不是出发顶点）
     *
     * @return 最短距离的顶点
     */
    public int updateArr() {
        int min = 65525, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后的结果
    //即将三个数组的情况输出
    public void show() {

        System.out.println("==========================");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }
}