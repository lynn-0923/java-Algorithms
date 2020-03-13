package com.wu.day03;

import java.util.Scanner;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack2 stack = new ArrayStack2(4);
        String key = "";
        boolean loop = true;
        //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择:");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//定义一个数组 表示栈
class ArrayStack {
    public int maxSize; //数组大小
    public int[] array; //数组
    public int top = -1;//表示栈顶

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        array[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = array[top];
        top--;
        return value;
    }

    //显示，注意是从上往下
    public void list() {
        //判断栈kong
        if (isEmpty()) {
            System.out.println("栈空，没有数据显示");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }
}
