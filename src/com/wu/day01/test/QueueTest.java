package com.wu.day01.test;

import java.util.Scanner;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class QueueTest {
    public static void main(String[] args) {
        //测试
        System.out.println("测试数组环形队列----");
        //创建环形队列，有效数据只有3
        Queue queue = new Queue(4);
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

class Queue {
    private int maxSize;
    private int front;//第一个元素,默认为0
    private int rear;//最后一个元素的后一个位置且保留一个位置
    private int[] arr;//数组

    //创建构建数组
    public Queue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断数组是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断数组是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void addQueue(int n) {
        //判断是否已满
        if (isFull()) {
            throw new RuntimeException("数组已满，不能添加");
        }
        arr[rear] = n;
        //rear向后移，得取模，否则越界
        rear = (rear + 1) % maxSize;
    }

    //显示数据
    public void showQueue() {
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //获取数组有效元素个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //获取数据
    public int getQueue() {
        //判断是否为空
        if (isEmpty()) {
            throw new RuntimeException("数组为空，不能取出数据");
        }
        //临时存取front值，将front后移
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //获取头数据
    public int headQueue() {
        return arr[front];
    }
}
