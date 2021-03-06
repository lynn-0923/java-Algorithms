package com.wu.day02.test;

/**
 * @author lynn
 * @date ${date} -${time}
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
     DoubleLinkedList doubleLinkedList=new DoubleLinkedList();
        DoublePeople p1 = new DoublePeople(1, "小高", "高狗狗");
        DoublePeople p2 = new DoublePeople(3, "小吴", "吴狗狗");
        DoublePeople p3 = new DoublePeople(7, "王一博", "高狗狗最爱");
        DoublePeople p4 = new DoublePeople(6, "肖战", "高狗狗最爱");
        //无序添加
//     doubleLinkedList.add(p1);
//     doubleLinkedList.add(p2);
//     doubleLinkedList.add(p3);
//     doubleLinkedList.add(p4);
//     doubleLinkedList.list();
        //有序添加
        doubleLinkedList.addByOrder(p1);
        doubleLinkedList.addByOrder(p2);
        doubleLinkedList.addByOrder(p3);
        doubleLinkedList.addByOrder(p4);
        doubleLinkedList.list();
        //修改
       /* System.out.println("修改后");
        DoublePeople p5=new DoublePeople(5,"肖战","最爱");
        doubleLinkedList.update(p5);
        doubleLinkedList.list()*/;
        //删除
        System.out.println("删除后");
        doubleLinkedList.delete(7);
        doubleLinkedList.list();
    }
}
//双向链表
class DoubleLinkedList {
    //定义头结点
    private DoublePeople head = new DoublePeople(0, "", "");

    //获取头结点
    public DoublePeople getHead() {
        return head;
    }

    public void setHead(DoublePeople head) {
        this.head = head;
    }

    //    遍历链表
    public void list() {
        //判断头结点是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        DoublePeople temp = head.getNext();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    //增加链表人物,无序
    public void add(DoublePeople people) {
        //因为头指针不能动 所以需要借助辅助指针进行遍历
        DoublePeople temp = head;//指向下一个
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
            people.setPre(temp);
        }
    }

    //有序添加链表
    public void addByOrder(DoublePeople people) {
        DoublePeople temp = head;
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
            people.setPre(temp);
        }
    }
    //修改节点信息
    public void update(DoublePeople people) {
        //先判断链表为空的情况
        if (head.getNext() == null) {
            System.out.println("链表为空，无法修改");
            return;
        }
        DoublePeople temp = head.getNext();//指向下一个节点
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
        if (head.getNext() == null){
            System.out.println("链表为空，不能忘删除");
            return;
        }
        //要删除的节点的编号
        //创建临时变量
        DoublePeople temp = head.getNext();
        boolean flag = false;//判断链表内是否有该节点
        while (true) {
            if (temp == null) {
                break;//已经到了链表末端
            }
            if (temp.getId() == n) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            //删除节点
            temp.getPre().setNext(temp.getNext());
            //注意，需判断当前是否为最后一个节点
            if (temp.getNext() !=null){
                temp.getNext().setPre(temp.getPre());
            }
        } else {
            System.out.printf("没有找到编号为%d的节点\n", n);
        }
    }
}
//双向链表属性
class DoublePeople{
    private Integer id;//人物编号
    private String name; // 任务姓名
    private String desc;//人物描述
    private DoublePeople next; // 指向下一个人物
    private DoublePeople pre;//指向前一个人物
    //创建构造人物属性方法
    public DoublePeople(int id, String name, String desc) {
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

    public DoublePeople getNext() {
        return next;
    }

    public void setNext(DoublePeople next) {
        this.next = next;
    }

    public DoublePeople getPre() {
        return pre;
    }

    public void setPre(DoublePeople pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "DoublePeople{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
