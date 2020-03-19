package com.wu.day10.huffman;

import javax.xml.transform.Source;
import java.io.*;
import java.util.*;

/**
 * @author lynn
 * @date 2020/3/18 - 17:47
 */
public class HuffmanCode {
    public static void main(String[] args) {
        /*String str = "i like like like java";
        byte[] huffmanBytes = str.getBytes();
        byte[] huffmanZip = huffmanZip(huffmanBytes);
        System.out.println("压缩后的结果：" + Arrays.toString(huffmanZip) + " 长度为: " + huffmanZip.length);

        //测试二进制的转换
       *//* String string = byteToBinaryString(true, (byte) 1);
        System.out.println(string);*//*
        byte[] decode = decode(huffmanCodes, huffmanZip);
        System.out.println(new String(decode));*/


       /* System.out.println(bytes.length);

        //分布过程
        //获得node集合
        List<Node> nodes = getNodes(bytes);
        System.out.println(nodes);//未排序

        //获得赫夫曼树
        Node root = createHuffman(nodes);
        System.out.println("前序遍历");
        preOrder(root);

        //测试生成的赫夫曼编码
//        getCodes(root, "", stringBuilder);
        System.out.println("--生成的赫夫曼编码表");
        Map<Byte, String> huffmanCodes = getCodes(root);
        System.out.println(huffmanCodes);

        //测试
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));*/
        //测试压缩文件
    /*    String srcFile = "f://src.bmp";
        String ditFile = "f://dst.zip";
        zipFile(srcFile,ditFile);
        System.out.println("压缩成功");*/
        //测试解压文件
        String zipFile = "f://dst.zip";
        String dstFile = "f://src2.bmp";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功");
    }


    //解压文件
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] by = decode(huffmanCodes, huffmanBytes);
            //将数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写出数据
            os.write(by);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param srcFile 需要解压的文件
     * @param dstFile 解压后的文件
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        //创建输入流
        FileInputStream is = null;
        ObjectOutputStream oos = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            //创建和源文件大小一样的数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //完成数据解压
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes对应的二进制的字符串，形式为1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成对应的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte huffmanByte = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBinaryString(!flag, huffmanByte));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换 因为反向查询 a->100 100->a
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解为索引 扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                if (i + count > stringBuilder.length()) {
                    String key = stringBuilder.substring(i);
                    b = map.get(key);
                    break;
                } else {
                    String key = stringBuilder.substring(i, count + i);
                    b = map.get(key);
                }
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;//将i移动到count
        }//存入byte数组
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param b    需要转换的byte
     * @param flag 判断是否需要补高位 如果是true 表示需要补高位 flase则不需要
     * @return 是b对应的二进制字符串 按补码返回
     */
    public static String byteToBinaryString(boolean flag, byte b) {
        //用一个变量接收
        int temp = b;
        //如果是正数 我们还存在补高位
        if (flag) {
            temp |= 256; //按位或 10000 0000  | 0000 0001 => 100000001
        }
        String str = Integer.toBinaryString(temp);
        if (flag || temp < 0) { //负数也需要截取
            return str.substring(str.length() - 8);
        } else {
            return str;//最后一位正数则不需要补高位
        }
    }
    //使用一个方法，将前面的方法封装起来，方便我们调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理过后的字节数组(压缩后的1)
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据node创建赫夫曼树
        Node huffman = createHuffman(nodes);
        //根据赫夫曼树获得对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffman);
        //根据赫夫曼编码，亚索的刀赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 转成对应的编码数组
     *
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的huffmanmap
     * @return 返回处理后的byte[]
     * 举例： String str = "i like like like java do you like a java";
     * =》对应的byte[] huffmanCodeBytes 即8位对应一个byte，放入huffmancodeBytes
     * 1010100010111111110010001011111111001000101111111100100101001101110001110000011011
     * 101000111100101000101111111100110001001010011011100
     * huffmanCodeByte[0] = 10101000(补码) =>byte [推导 1010100 => 10101000 -1 => 10100111(反码) =》11011000(原码) -88]
     * huffmanCodeByte[0] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //返回byte[]  huffmanCodeBytes长度
        int len;
        //int len = (stringBuilder.length() + 7 ) / 8
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //用于存放在数组内的位置
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte装成一个二进制byte存入huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码表
    //将生成的赫夫曼编码表以Map<Byte,String>形式存储
    //定义一个stringbuiled 用于在生成编码表时需要拼接路径
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便 重载getCodes
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理左子树
        getCodes(root.left, "0", stringBuilder);
        //处理右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将赫夫曼树生成对应的赫夫曼编码表
     *
     * @param node          传入节点
     * @param code          路径：左子节点为0，右子节点为1
     * @param stringBuilder 用于拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node != null) {//如果为null则不处理
            if (node.data == null) {//非叶子结点
                //递归处理
                //向左
                getCodes(node.left, "0", stringBuilder1);
                //向右
                getCodes(node.right, "1", stringBuilder1);
            } else {//已经到了叶子结点
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //将nodes生成赫夫曼树
    public static Node createHuffman(List<Node> nodes) {
        while (nodes.size() > 1) {//还未到最后一个元素
            Collections.sort(nodes);//排序
            //获得第一个结点
            Node leftNode = nodes.get(0);
            //获得第二个节点
            Node rightNode = nodes.get(1);
            //生成一个新的树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //移除处理过的node
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent存入nodes
            nodes.add(parent);
        }
        return nodes.get(0);//返回根节点
    }

    //将数组存入list
    public static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        //用map来存放
        Map<Byte, Integer> map = new HashMap<>();
        //遍历数组
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count != null) {
                map.put(b, count + 1);
            } else {
                map.put(b, 1);
            }
        }
        //把每个键值转换为Node对象，并加入node集合中
        //遍历map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空，无法遍历");
        }
    }
}

//创建Node 带数据和权值
class Node implements Comparable<Node> {
    Byte data; //存放数据本身，例如 ‘a'=>97
    int weight; //权值，代表数据出现的次数
    Node left;
    Node right;

    //构造器

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}