package com.wu.day13.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author lynn
 * @date 2020/3/22 - 9:53
 */
//邻接矩阵
public class Graph {
    private ArrayList<String> vertexList; //顶点的数量
    private int[][] edges; //表示邻接矩阵
    private int numOfEdges; //边的数量
    private boolean[] isVisited; //判断是否已被访问

    public static void main(String[] args) {
        //初始化
        int n = 8; //顶点的数量
//        String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1);//A-B
//        graph.insertEdge(0, 2, 1);//A-C
//        graph.insertEdge(1, 2, 1);//B-C
//        graph.insertEdge(1, 3, 1);//B-D
//        graph.insertEdge(1, 4, 1);//B-E

        //显示矩阵
       /* graph.showGraph();
        //测试深度优先遍历是否ok
        System.out.println("深度遍历");
        graph.dfs();//A->B->C->D->E

        //测试广度优先遍历
        System.out.println();
        System.out.println("广度遍历");
        graph.bfs();*/
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        //测试深度优先遍历是否ok
        System.out.println("深度遍历");
        graph.dfs();//1->2->4->8->5->3->6->7

        //测试广度优先遍历
        System.out.println();
        System.out.println("广度遍历");
        graph.bfs();//1->2->3->4->5->6->7->8
    }

    //对一个顶点广度优先遍历算法
    private void bfsFirst(boolean[] isVisited, int i) {
        int u; //表示队列的头指针
        int w; //表示邻顶点
        LinkedList queue = new LinkedList();//队列 记录顶点之间的顺序
        //访问顶点 输出节点信息
        System.out.print(getVertex(i) + "=>");
        //标记已访问
        isVisited[i] = true;
        //将顶点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出头顶点
            u = (int) queue.removeFirst();
            //获得邻接顶点
            w = getFirstNeighbor(u);
            while (w != -1) {
                //判断是否已被访问
                if (!isVisited[w]) {
                    System.out.print(getVertex(w) + "=>");//输出
                    isVisited[w] = true;
                    //加入队列
                    queue.addLast(w);
                }
                //以u为当前顶点 找w后面的下一个顶点
                w = getNextNeighbor(u, w);
            }
        }
    }

    //遍历所有顶点 都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];//重置
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfsFirst(isVisited, i);
            }
        }
    }

    //对一个顶点深度优先遍历算法

    private void dfsFirst(boolean[] isVisited, int i) {
        //首先访问该节点输出
        System.out.print(getVertex(i) + "=>");
        //标明已被访问
        isVisited[i] = true;
        //接着以i这个顶点深度遍历
        //查找i顶点的第一个邻接顶点
        int w = getFirstNeighbor(i);
        //当存在的时候一直遍历当前顶点
        while (w != -1) {//说明有
            if (!isVisited[w]) {//判断是否被访问过
                dfsFirst(isVisited, w);
            }
            //如果已被访问过 则获取第一个邻接顶点的下一个顶点
            w = getNextNeighbor(i, w);
        }
    }

    //深度优先算法
    // 对dfs进行重载 遍历所有的顶点 并进行dfs(回溯)
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];//是否被访问 默认false
        //遍历所有顶点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfsFirst(isVisited, i);
            }
        }
    }

    /**
     * 得到第一个邻接顶点的下标
     *
     * @param index 当前顶点
     * @return 返会当前顶点后第一个可以直连的顶点 如果有则返回下标 如果没有则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 如果获得第一个顶点后可以直连的下标，但是已被访问了 就接着往后遍历
     *
     * @param v1 当前顶点
     * @param v2 已获得第一个可直连的顶点
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //图中常用的方法
    //返回顶点的数量
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的数量
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回顶点(下标)对应的顶点 "0" - A "1" - B
    public String getVertex(int index) {
        return vertexList.get(index);
    }

    //返回v1,v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        //遍历
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //添加顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边

    /**
     * 添加边 顶点之间的关系
     *
     * @param v1     表示点的下标和第几个顶点 "A-B" "A"-0 "B"-1
     * @param v2
     * @param weight 1表示顶点之间可以直连 0表示顶点之间不可连
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = 1;//无向图
        edges[v2][v1] = 1;
        numOfEdges++;//边的数量
    }
}
