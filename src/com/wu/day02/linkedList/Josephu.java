package com.wu.day02.linkedList;

import java.text.BreakIterator;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class Josephu {
    public static void main(String[] args) {
        //测试
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(25);
        circleSingleLinkedList.showBoy();
        //出圈
        circleSingleLinkedList.countBoy(1,2,25);
    }
}

//单向循环链表
class CircleSingleLinkedList {
    //初始化第一个节点，当前节点无编号
    private Boy first = null;

    //添加节点
    public void add(int nums) {
        //传入需要创建节点的额数量，并构成一个环
        //对传入的nums进行校验
        if (nums < 1) {
            System.out.println("nums格式错误");
            return;
        }
        //借助辅助指针，来构建环形
        Boy curBoy = null;
        //用for循环来构建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                //如果是第一个小孩
                first = boy;
                first.setNext(first);
                curBoy = boy;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形所有节点
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("当前没有小孩---");
            return;
        }
        //借助辅助指针遍历
        Boy temp = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", temp.getNo());
            if (temp.getNext() == first) {
                //已经遍历完毕
                break;
            }
            temp = temp.getNext();//后移
        }
    }
    //根据用户的输入，完成出圈顺序

    /**
     * @param startNo 表示从第几个开始数
     * @param countNo 表示报数的个数
     * @param nums    表示共有多少人
     */
    public void countBoy(int startNo, int countNo, int nums) {
        //先对数据记性校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入的参数有误，请重新输入");
            return;
        }
        //借助辅助函数helper,并将helper移动到最后一个位置
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {//已指向最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //根据开始报数的位置将helper和first移动到开始数数的位置
        for (int i = 0; i < startNo - 1; i++) {
            helper = helper.getNext();
            first = first.getNext();
        }
        while (true) {
            if (helper == first) {
                //圈内只有一个元素
                break;
            }
            //当小孩开始报数 让helper，helper同时移动countNo-1次
            for (int i = 0; i < countNo - 1; i++) {
                helper = helper.getNext();
                first = first.getNext();
            }
            //将first所指的元素删除
            System.out.printf("要出圈的小孩的编号为%d\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后一个小孩的编号为%d\n", first.getNo());
    }
}

//定义一个boy  为节点
class Boy {
    private int no;//该节点的编号
    private Boy next; //指向下一个节点

    //构造一个创建boy的方法
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}