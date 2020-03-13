package com.wu.day01.queue;

import java.util.Scanner;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建队列
        ArrayQueue queue = new ArrayQueue(3);
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
                    }catch (Exception e){
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

//使用数组模拟队列-编写一个ArrayQueue
class ArrayQueue{
    private int maxSize;//数组的最大容量
    private int front; //队列头
    private int rear; // 队列尾
    private int[] arr;//数组

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部,分析出front是指向队列头的前一个位置
        rear = -1; //指向队尾，队列尾部的数据，即最后一个数据
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
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
        rear++;
        arr[rear] = n;
    }

    //取出队列数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出");
        }
        front++;
        return arr[front];
    }

    //显示所有数据
    public void showQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列头的数据，非取出
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无数据");
        }
        return arr[front + 1];
    }
}

