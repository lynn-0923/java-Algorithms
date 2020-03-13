package com.wu.day02.test;

import java.util.Stack;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        People p1 = new People(1, "小高", "高狗狗");
        People p2 = new People(3, "小吴", "吴狗狗");
        People p3 = new People(5, "王一博", "高狗狗最爱");
        People p4 = new People(4, "肖战", "高狗狗最爱");

        People p5 = new People(1, "小高", "高狗");
        People p6 = new People(6, "小吴", "吴狗");
        People p7 = new People(3, "王一博", "高狗最爱");
        People p8 = new People(8, "肖战", "高狗最爱");
        //无序添加
        /*singleLinkedList.add(p1);
        singleLinkedList.add(p2);
        singleLinkedList.add(p3);
        singleLinkedList.add(p3);*/
        //有序添加
        singleLinkedList.addByOrder(p1);
        singleLinkedList.addByOrder(p2);
        singleLinkedList.addByOrder(p3);
        singleLinkedList.addByOrder(p4);

        singleLinkedList2.addByOrder(p5);
        singleLinkedList2.addByOrder(p6);
        singleLinkedList2.addByOrder(p7);
        singleLinkedList2.addByOrder(p8);
//        singleLinkedList.list();
//        singleLinkedList2.list();
        System.out.println("------------");
        People combine = singleLinkedList.combine(singleLinkedList.getHead(), singleLinkedList2.getHead());
        while (combine.getNext() != null) {
            System.out.println(combine.getNext());
            combine = combine.getNext();
        }

//        singleLinkedList.list();
        //记录元素个数
        /*People head = singleLinkedList.getHead();
        int size = singleLinkedList.size(head);
        System.out.println(size);*/
        //修改
       /* System.out.println("修改后");
        People p5=new People(4,"肖战","最爱");
        singleLinkedList.update(p5);*/
        //删除
        /*singleLinkedList.delete(3);
        System.out.println("删除后");
        singleLinkedList.list();*/
        //获取倒数第k个节点
        /*People p = singleLinkedList.getP(singleLinkedList.getHead(),2);
        System.out.println(p);*/
        //链表的逆转
        /*singleLinkedList.reverse(singleLinkedList.getHead());
        System.out.println("逆转后");
        singleLinkedList.list();*/
        //逆向打印
       /* System.out.println("逆向打印");
        singleLinkedList.reversePrint(singleLinkedList.getHead());*/
    }
}

//单向链表
class SingleLinkedList {
    //定义头结点
    private People head = new People(0, "", "");

    //获取头结点
    public People getHead() {
        return head;
    }

    public void setHead(People head) {
        this.head = head;
    }

    //    遍历链表
    public void list() {
        //判断头结点是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头指针不能动 所以需要借助辅助指针进行遍历
        People people = head.getNext();//指向下一个
        while (true) {
            if (people == null) {
                return;
            }
            System.out.println(people);
            people = people.getNext();//将指针后移
        }
    }

    //增加链表人物,无序
    public void add(People people) {
        //因为头指针不能动 所以需要借助辅助指针进行遍历
        People temp = head;//指向下一个
        boolean flag = false;//判断链表内是否含有该元素
        while (true) {
            if (temp.getNext() == null) {
                //到了链表的最后
                break;
            }
            if (temp.getNext().getId() == people.getId()) {
                flag = true;
                break;
            }
            //没找到 接着遍历
            temp = temp.getNext();
        }
        if (flag) {
            System.out.printf("链表内已经含有%d人物了\n", people.getId());
        } else {
            temp.setNext(people);
        }
    }

    //有序添加链表
    public void addByOrder(People people) {
        People temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getId() > people.getId()) {
                break;
            } else if (temp.getNext().getId() == people.getId()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            System.out.printf("链表内已经含有%d人物了\n", people.getId());
        } else {
            people.setNext(temp.getNext());
            temp.setNext(people);
        }
    }

    //修改节点信息
    public void update(People people) {
        //先判断链表为空的情况
        if (head.getNext() == null) {
            System.out.println("链表为空，无法修改");
            return;
        }
        People temp = head.getNext();//指向下一个节点
        boolean flag = false; // 判断是否找到
        while (true) {
            if (temp == null) {
                //已经到了链表的末端
                break;
            }
            if (temp.getId() == people.getId()) {
                //已经找到
                flag = true;
                break;
            }
            temp = temp.getNext();//遍历
        }
        if (flag) {
            temp.setName(people.getName());
            temp.setDesc(people.getDesc());
        } else {
            System.out.printf("编号为%d的人物不存在\n", people.getId());
        }
    }

    //删除节点
    //注意 需要找到要删除节点的前一个节点
    public void delete(int n) {
        //先判断链表为空的情况
        if (head.getNext() == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        People temp = head;
        boolean flag = false; // 判断是否有该编号的节点
        while (true) {
            if (temp.getNext() == null) {
                //链表末端
                break;
            }
            if (temp.getNext().getId() == n) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.printf("编号为%d的人物不存在\n", n);
        }
    }

    //获取链表有效节点的个数
    public int size(People people) {
        if (people.getNext() == null) {
            //只有头节点 链表为空
            return 0;
        }
        People temp = head.getNext();
        int sum = 0;//记录个数
        while (temp != null) {
            sum++;
            temp = temp.getNext();
        }
        return sum;
    }

    //获取倒数第k个节点
    public People getP(People people, int k) {
        if (people.getNext() == null) {
            //只有头节点 链表为空
            System.out.println("链表为空 无法查询");
            return null;
        }
        int size = size(people);//获取节点个数
        People temp = people.getNext();
        for (int i = 0; i < size - k; i++) {
            temp = temp.getNext();
        }
        return temp;
    }

    //单链表的反转
    //需借助辅助链表
    public void reverse(People people) {
        //对people进行校验
        if (people == null || people.getNext() == null || people.getNext().getNext() == null) {
            System.out.println("数据有误，无法进行反转");
        }
        People reverseHead = new People(0, "", "");//临时存放转化后的节点
        People next;//指向下一个节点
        //因为头指针不能动
        People temp = people.getNext();
        while (temp != null) {
            next = temp.getNext();//临时存放要取出节点的下一个节点
            temp.setNext(reverseHead.getNext());
            reverseHead.setNext(temp);
            temp = next;
        }
        people.setNext(reverseHead.getNext());
    }

    //反向打印单链表
    //利用栈先进后出的特点打印
    public void reversePrint(People people) {
        //创建栈
        Stack<People> stack = new Stack<>();
        //对people进行判断
        if (people.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        People temp = head.getNext();
        //存入栈中
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }
        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
    public People combine(People p1, People p2) {
        //校验
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        //构建新链表 存放合并后 的链表,递归
        People combineHead;
        if (p1.getId() <= p2.getId()) {
            combineHead = p1;
            combineHead.setNext(combine(p1.getNext(), p2));
        } else {
            combineHead = p2;
            combineHead.setNext(combine(p1, p2.getNext()));
        }
        return combineHead;
    }
}

//每个people属于一个节点
class People {
    private Integer id;//人物编号
    private String name; // 任务姓名
    private String desc;//人物描述
    private People next; // 指向下一个人物

    //创建构造人物属性方法
    public People(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public People getNext() {
        return next;
    }

    public void setNext(People next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}