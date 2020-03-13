package com.wu.day02.test;

/**
 * @author lynn
 * @date 2020/3/10 - 8:42
 */
public class Josephu {
    public static void main(String[] args) {
        //测试 查看是否形成成功
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
//        circleSingleLinkedList.list();
        //测试报数
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//定义一个单向循环链表
class CircleSingleLinkedList {
    //定义一个指针，只指向链表的第一个
    private Boy first = null;

    //添加小孩节点，构成一个环形链表
    public void add(int nums) {//传入小孩的数量
        //校验数量
        if (nums < 1) {
            System.out.println("输入的数量有误");
            return;
        }
        Boy curBoy = null;//辅助函数，辅助形成链表
        //用for循环形成
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                //如果小孩数量为1个，则自身形成环形链表
                first = boy;
                first.setNext(first);//指向自己
                curBoy = boy;
            } else {
                curBoy.setNext(boy);//指向下一个
                boy.setNext(first);//形成环路
                curBoy = boy;
            }
        }
    }

    //遍历链表
    public void list() {
        //判空
        if (first == null) {
            System.out.println("链表为空，无法遍历");
            return;
        }
        //同样需要借助辅助函指针遍历
        Boy temp = first;
        while (true) {
            System.out.printf("小孩编号为%d\n", temp.getNo());
            if (temp.getNext() == first) {
                break;
            }
            temp = temp.getNext();
        }
    }
    //根据输入小孩的数量，计算小孩出圈的顺序

    /**
     * @param startNo 开始报数的位置
     * @param countNo 报数的次数
     * @param nums    有多少人在圈内
     */
    public void countBoy(int startNo, int countNo, int nums) {
        //对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("数据格式有误，请重新输入");
            return;
        }
        //需要借助辅助变量 指向链表的最后
        Boy helper = first;
        //遍历 找到链表的最后
        while (true) {
            if (helper.getNext() == first) {
                //将最后一个指针赋给helper
                break;
            }
            helper = helper.getNext();
        }
        //将first和helper同时移动到起始位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        while (true){
            if (first==helper) {
                break;//只有一个节点
            }
        //小孩报数，让helper和和first同时移动
        for (int i = 0; i < countNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //让first指向的小孩出圈
        System.out.printf("编号为%d的小孩出圈\n", first.getNo());
        first = first.getNext();
        helper.setNext(first);
        }
        System.out.printf("编号为%d的小孩最后出圈",first.getNo());
    }
}

class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个

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

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}