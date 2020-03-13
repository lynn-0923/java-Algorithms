package com.wu.day02.linkedList;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        HeroNode2 p1 = new HeroNode2(1, "小吴", "吴狗狗");
        HeroNode2 p2 = new HeroNode2(2, "小高", "高狗狗");
        HeroNode2 p3 = new HeroNode2(3, "肖战", "小高的喜欢");
        HeroNode2 p4 = new HeroNode2(4, "王一博", "小高的喜欢");
        //创建链表
        DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        doubleLinkedList.addByOrder(p1);
        doubleLinkedList.addByOrder(p3);
        doubleLinkedList.addByOrder(p4);
        doubleLinkedList.addByOrder(p2);
        doubleLinkedList.list();
//        System.out.println("修改后");
//        HeroNode2 hero=new HeroNode2(4,"KOBE","偶像");
//        doubleLinkedList.update(hero);
//        doubleLinkedList.list();
        doubleLinkedList.delete(4);
        System.out.println("删除后");
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    //先定义头结点
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历链表
    public void list() {
        //判断第一个head是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //head 已不为空
        HeroNode2 temp = head.next;
        //遍历链表，找到最后节点的位置
        while (temp != null) {
            //找到链表的最后
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加节点
    public void addHeroNode(HeroNode2 heroNode) {
        //先判断head,head链表不能动,需要辅助遍历
        HeroNode2 temp = head;
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
        //找到链表的最后，形成双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按照英雄顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        //判断 如果已存在则提示
        boolean flag = false;
        HeroNode2 cur;//临时存储
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
             /* cur=temp.next;
              temp.next=heroNode;
              heroNode.pre=temp;
            if(temp.next!=null) {
                heroNode.next=cur;
                cur.pre=heroNode;
            }*/
             cur=temp.next;
             heroNode.next=temp.next;
             temp.next=heroNode;
             heroNode.pre=temp;
              if(cur!=null) {
                  cur.pre = heroNode;
              }
        }
    }

    //修改节点信息。不能修改编号
    public void update(HeroNode2 heroNode) {
        //查看head的next是否为空
        if (head.next == null) {
            System.out.println("当前链表为空，不能修改");
            return;
        }
        //创建临时变量
        HeroNode2 temp = head.next;
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
        //判断头结点下一个是否为空
        if (head.next == null){
            System.out.println("链表为空，不能忘删除");
            return;
        }
        //要删除的节点的编号
        //创建临时变量
        HeroNode2 temp = head.next;
        boolean flag = false;//判断链表内是否有该节点
        while (true) {
            if (temp == null) {
                break;//已经到了链表末端
            }
            if (temp.no == n) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
           //删除节点
            temp.pre.next=temp.next;
            //注意，需判断当前是否为最后一个节点
            if (temp.next !=null){
                temp.next.pre=temp.pre;
            }
        } else {
            System.out.printf("没有找到编号为%d的节点\n", n);
        }
    }

}

class HeroNode2 {
    public int no;//英雄编号
    public String name;//英雄名词
    public String nickName;//外号
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre; //指向前一个节点

    //构造heroNode对象
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    //为了方便打印重写toString方法

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
