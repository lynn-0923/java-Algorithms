package com.wu.day01.queue;

import java.util.Scanner;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组环形队列----");
        //创建环形队列，有效数据只有3
        CircleArray queue = new CircleArray(4);
        char key = ' ';//接收用户输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.print("s(show):显示队列--");
            System.out.print("e(exit):退出--");
            System.out.print("a(add):增加数据到队列--");
            System.out.print("g(get):获取队列--");
            System.out.println("h(head):显示头队列");
            System.out.print("请输入您的选项：");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入数据:");
                    int i = scanner.nextInt();
                    try {
                        queue.addQueue(i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int queue1 = queue.getQueue();
                        System.out.println("取出的数据为：" + queue1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int queue2 = queue.headQueue();
                        System.out.println("取出的头数据为：" + queue2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序");
    }
}

class CircleArray {
    private int maxSize;//数组的最大容量
    private int front; //队列头
    //front变量的含义做一个调整：front就指向队列的第一个元素,也就是说arr[front]就是队列的第一个元素
    // front的初始值=0
    private int rear; // 队列尾
    //rear变量的含义作出调整：rear指向最后一个元素的后一个位置，空一个位置作为保留
    //rear的初始值=0
    private int[] arr;//数组

    //构建环形队列
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //增加队列数据
    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满，不能添加");
        }
        //将数据直接存入
        arr[rear] = n;
        //将rear后移，需考虑取模，否则会越界
        rear = (rear + 1) % maxSize;
    }

    //取出队列数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出");
        }
        //临时存放front的值
        int value = arr[front];
        //将front后移 也需要取模
        front = (front + 1) % maxSize;
        return value;
    }

    //显示所有数据,得注意循环的起始位置
    public void showQueue() {
        for (int i = front; i <front+ size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //获取有效元素个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头的数据，非取出
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无数据");
        }
        return arr[front];
    }
}