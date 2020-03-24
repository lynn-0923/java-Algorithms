package com.wu.day16.prim;

import java.util.Arrays;
import java.util.jar.JarEntry;

/**
 * Prim算法-最小生成树
 *
 * @author lynn
 * @date 2020/3/24 - 18:59
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        //邻接矩阵的关系用二维数组表示,用10000这个大叔，表示这两个点不连接
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        //创建Graph对象
        Graph graph = new Graph(vertexs);
        //创建minTree
        minTree minTree = new minTree();
        minTree.createGraph(graph, vertexs, data, weight);
        //输出
        minTree.showGraph(graph);
        //测试prim
        minTree.prim(graph, 0);
    }
}

//创建最小生成树 =》村庄的图
class minTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph   图对象
     * @param vertexs 节点的个数
     * @param data    图中各顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(Graph graph, int vertexs, char[] data, int[][] weight) {
        for (int i = 0; i < vertexs; i++) {//顶点
            graph.data[i] = data[i];
            for (int j = 0; j < vertexs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示邻接矩阵
    public void showGraph(Graph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法
     *
     * @param graph 图
     * @param start 表示从第几个顶点开始生成 'A' - 0 'B' - 1
     */
    public void prim(Graph graph, int start) {
        //标记节点是否被访问过
        int[] visited = new int[graph.vertexs];

        //将此节点标记为已访问
        visited[start] = 1;

        int minWeight = 10000; //初始为一个较大的数，后面在遍历过程中会被替换
        //h1,h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        for (int k = 1; k < graph.vertexs; k++) {//因为有graph.vertex个顶点，普利姆算法结束后会有graph.vertex - 1个边
            //这是确定每个生成的的子图，和那个节点的距离最近
            for (int i = 0; i < graph.vertexs; i++) { //i表示已被访问过的节点
                for (int j = 0; j < graph.vertexs; j++) { //j表示未被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的节点和未访问过的节点的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条最小的边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值： " + minWeight);
            //标记当前节点已访问
            visited[h2] = 1;
            //重置
            minWeight = 10000;
        }
    }
}

class Graph {
    int vertexs; //图中节点的个数
    char[] data; //节点
    int[][] weight; //存放边  就是邻接矩阵

    public Graph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}