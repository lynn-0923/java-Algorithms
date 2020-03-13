package com.wu.day02.linkedList;

import java.util.Stack;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode p1 = new HeroNode(1, "小吴", "吴狗狗");
        HeroNode p2 = new HeroNode(2, "小高", "高狗狗");
        HeroNode p3 = new HeroNode(3, "肖战", "小高的喜欢");
        HeroNode p4 = new HeroNode(4, "王一博", "小高的喜欢");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //按顺序放入
        singleLinkedList.addByOrder(p1);
        singleLinkedList.addByOrder(p4);
        singleLinkedList.addByOrder(p3);
        singleLinkedList.addByOrder(p2);
        singleLinkedList.reversePrint(singleLinkedList.getHead());
       /* singleLinkedList.addHeroNode(p1);
        singleLinkedList.addHeroNode(p4);
        singleLinkedList.addHeroNode(p3);
        singleLinkedList.addHeroNode(p2);*/
//        singleLinkedList.addByOrder(p2);
        //修改前
//        singleLinkedList.list();
//        HeroNode heroNode1 = new HeroNode(4, "kobe", "小吴的偶像");
//        singleLinkedList.update(heroNode1);
//        //修改
//        System.out.println("修改后的-----");
       /* singleLinkedList.delete(1);
        singleLinkedList.delete(4);
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);
        System.out.println("删除后");
        singleLinkedList.list();*/
        //获取节点个数
        /*int size = singleLinkedList.size(singleLinkedList.getHead());
        System.out.println(size);*/
        //获取指定节点
//        HeroNode her=singleLinkedList.getNode(singleLinkedList.getHead(),4);
//        System.out.println(her);
        //逆转链表
//        singleLinkedList.reverse(singleLinkedList.getHead());
//        singleLinkedList.list();
    }
}

//定义单链表
class SingleLinkedList {
    //先定义头结点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点
    public void addHeroNode(HeroNode heroNode) {
        //先判断head,head链表不能动,需要辅助遍历
        HeroNode temp = head;
        //遍历链表，找到最后节点的位置
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //没找到，接着遍历
            temp = temp.next;
        }
        //当while退出循环时，temp就指向了链表的最后
        //找到链表的最后，将链表的next新的节点
        temp.next = heroNode;
    }

    //按照英雄顺序添加
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        //判断 如果已存在则提示
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//已经到达链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位于下一个节点编号之前
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//已存在
                break;
            }
            temp = temp.next;//指向下一个
        }
        if (flag) {
            //已存在，不能添加
            System.out.printf("您要添加的英雄的编号%d已存在，无法添加\n", heroNode.no);
        } else {
            //将temp的next赋给添加的节点的下一个
            heroNode.next = temp.next;
            //将添加的节点赋给temp的下一个
            temp.next = heroNode;
        }
    }

    //修改节点信息。不能修改编号
    public void update(HeroNode heroNode) {
        //查看head的next是否为空
        if (head.next == null) {
            System.out.println("当前链表为空，不能修改");
            return;
        }
        //创建临时变量
        HeroNode temp = head.next;
        boolean flag = false;//判断链表内是否有该节点
        while (true) {
            if (temp == null) {
                break;//已经到了链表末端
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有找到编号为%d的节点\n", heroNode.no);
        }
    }

    //删除节点
    public void delete(int n) {
        //要删除的节点的编号
        //创建临时变量
        HeroNode temp = head;
        boolean flag = false;//判断链表内是否有该节点
        while (true) {
            if (temp.next == null) {
                break;//已经到了链表末端
            }
            if (temp.next.no == n) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //遍历 找到temp节点的前一个
            temp.next = temp.next.next;//不需要判断temp.next.next为空的情况，因为如果为空则为null
        } else {
            System.out.printf("没有找到编号为%d的节点\n", n);
        }
    }

    //遍历链表
    public void list() {
        //判断第一个head是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //head 已不为空
        HeroNode temp = head.next;
        //遍历链表，找到最后节点的位置
        while (temp != null) {
            //找到链表的最后
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //获取链表的有效元素个数
    public int size(HeroNode head) {
        int count = 0;
        //判断第一个head是否为空
        if (head.next == null) {
            return count;
        }
        //head 已不为空
        HeroNode temp = head.next;
        //遍历链表，找到最后节点的位置
        while (temp != null) {
            //找到链表的最后
            count++;
            temp = temp.next;
        }
        return count;
    }

    //获取倒数第k个节点
    public HeroNode getNode(HeroNode head, int index) {//index表示倒数第index
        //判断第一个head是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        //第一次遍历
        int size = size(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //逆转链表
    public void reverse(HeroNode head) {
        //判断第一个head是否为空
        if (head.next == null || head.next.next == null) {
            System.out.println("头结点为空");
        }
        //head 已不为空
        HeroNode temp = head.next;
        //指向当前节点的下一个节点【temp】
        HeroNode next ;
        //创建一个新的链表
        HeroNode reverseHead = new HeroNode(0, "0,", "");
        //遍历链表，找到最后节点的位置
        while (temp != null) {
            //找到链表的最后
            next = temp.next;//临时存放值
            temp.next = reverseHead.next;//将temp的下一个节点指向新的链表的最前端  注意！！！！！
            //存入
            reverseHead.next = temp;
            temp = next;//相当于将原始链表的位置后移
        }
        head.next = reverseHead.next;
    }
    //方式一，才从逆序但是会破坏原有的结构
    //方式二，利用栈数据结构，栈具有先进后出的特点，就实现了逆序打印的功能
    public void reversePrint(HeroNode head){
        if (head.next==null){
            return;//空链表
        }
        HeroNode temp=head.next;
        //创建一个栈
        Stack<HeroNode> heroNodes = new Stack<>();
        while (temp !=null){
            //压入栈中
            heroNodes.push(temp);
            temp=temp.next;
        }
        //将栈中的节点进行遍历,pop出栈
        while (heroNodes .size() >0){
            System.out.println(heroNodes.pop());
        }
    }
}

//定义HeroNode对象，每个heroNode对象为一个节点
class HeroNode {
    public int no;//英雄编号
    public String name;//英雄名词
    public String nickName;//外号
    public HeroNode next;//指向下一个节点

    //构造heroNode对象
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //为了方便打印重写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}