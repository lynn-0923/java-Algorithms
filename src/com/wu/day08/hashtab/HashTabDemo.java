package com.wu.day08.hashtab;

/**
 * @author lynn
 * @date 2020/3/14 - 20:41
 */

//哈希表  主要构成为链表和数组
public class HashTabDemo {
    public static void main(String[] args) {
        Emp emp1 = new Emp(1, "tom");
        Emp emp3 = new Emp(3, "mary");
        Emp emp2 = new Emp(2, "jack");
        Emp emp4 = new Emp(10, "lynn");
        Emp emp5 = new Emp(26, "gao");
        Emp emp6 = new Emp(18, "gao");
        HashTab hashTab = new HashTab(8);
        hashTab.add(emp1);
        hashTab.add(emp2);
        hashTab.add(emp3);
        hashTab.add(emp4);
        hashTab.add(emp5);
        hashTab.add(emp6);
        hashTab.add(emp6);
//        hashTab.findById(3);
//        System.out.println("删除前");
        hashTab.list();
     /*   System.out.println("删除后");
        hashTab.deleteById(18);
        hashTab.list();*/
    }
}

//哈希表 hashTable 用于管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private Integer size;//链表的个数

    //构建器

    public HashTab(Integer size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //注意 得初始化链表！！
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加员工
    public void add(Emp emp) {
        //根据员工的id 决定放到哪条链表
        int empLinkedListNo = hashFun(emp.id);

        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表
    public void list() {
        //遍历哈希表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    // 查找
    public void findById(int id) {
        //根据散列函数 找到对应的链表
        int hashFun = hashFun(id);
        Emp emp = empLinkedListArray[hashFun].findById(id);
        if (emp != null) {//找到
            System.out.printf("在第%d号链表找到%d号员工\n", (hashFun + 1), hashFun);
        } else {
            System.out.println("在哈希表中未找到该员工");
        }
    }

    //删除节点
    public void deleteById(int id) {
        //根据散列函数 找到对应的链表
        int hashFun = hashFun(id);
        empLinkedListArray[hashFun].delete(id);
    }

    //编写一个散列函数  取模法
    public int hashFun(int id) {
        return id % size;
    }
}

//链表
class EmpLinkedList {
    private Emp head; //因为指向第一个员工 默认 为null

    //添加员工
    //假定id为自增的也就是不能重复的
    //直接添加到链表的最后
    public void add(Emp emp) {
        //判断当前链表是否有员工
        if (head == null) {
            head = emp;
            return;
        }
        //定义一个辅助指针 找到链表的最后
        Emp curEmp = head;
        boolean flag = false;
        while (true) {
            if (curEmp.next == null) {
                break; //已经遍历到链表的最后了
            }
            //按id大小排放
            if (curEmp.next.id > emp.id) {//位于下一个节点编号之前
                break;
            } else if ( curEmp.next.id == emp.id) {
                flag = true;//已存在
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag) {
            //已存在，不能添加
            System.out.printf("您要添加的员工的编号%d已存在，无法添加\n", emp.id);
        } else {
            //将temp的next赋给添加的节点的下一个
            emp.next = curEmp.next;
            //将添加的节点赋给temp的下一个
            curEmp.next = emp;
        }
    }

    //遍历链表
    public void list(int no) {
        //判空
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表信息为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表信息为:");
        //定义一个辅助指针 遍历链表
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d,name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //查找雇员
    //如果找到了则返回 如果未找到则返回null
    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表为空，无法查找");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;//已找到
            }
            //退出
            if (curEmp.next == null) {
                curEmp = null;
                break; //置空返回
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //删除员工
    public void delete(int id) {
        if (head == null) {
            System.out.println("链表为空,无法删除");
            return;
        }
        //如果要查找的为头指针 后面还有数据 则将head置为null 将后面的值赋给head
        if (head.id == id && head.next != null) {
            head = head.next;
            return;
        }
        //如果要查找的为头指针 且后面没有数据了 则将head置为null
        if (head.id == id && head.next == null) {
            head = null;
            return;
        }
        //借助辅助函数遍历
        Emp curEmp = head;
        boolean flag = false;//判断是否找到
        while (true) {
            if (curEmp.next == null) {//遍历结束
                break;
            }
            //找到待删除节点的前一个节点
            if (curEmp.next.id == id) {
                flag = true;
                break;//找到了
            }
            curEmp = curEmp.next;
        }
        if (flag) {
            curEmp.next = curEmp.next.next;//删除节点
        } else {
            System.out.printf("没有找到编号为%d的节点\n", id);
        }
    }
}

//表示一个雇员
class Emp {
    public Integer id;//雇员id
    public String name; //员工姓名
    public Emp next; //指向下一个员工 默认为null

    //构造器
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
