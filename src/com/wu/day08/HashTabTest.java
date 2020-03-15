package com.wu.day08;

import java.lang.reflect.WildcardType;
import java.net.HttpRetryException;

/**
 * @author lynn
 * @date 2020/3/15 - 9:49
 */
public class HashTabTest {
    public static void main(String[] args) {
        Hero hero1 = new Hero(1, "tom");
        Hero hero2 = new Hero(3, "mary");
        Hero hero3 = new Hero(2, "jack");
        Hero hero4 = new Hero(10, "lynn");
        Hero hero5 = new Hero(26, "gao");
        Hero hero6 = new Hero(18, "gao");
        HashTab hashTab = new HashTab(8);
        hashTab.add(hero1);
        hashTab.add(hero2);
        hashTab.add(hero3);
        hashTab.add(hero4);
        hashTab.add(hero5);
        hashTab.add(hero6);
        hashTab.add(hero6);
        hashTab.findById(3);
        System.out.println("删除前");
        hashTab.list();
        System.out.println("删除后");
        hashTab.deleteById(18);
        hashTab.list();
    }
}
class HashTab{
    //存放链表数组
    private HeroLinkedList[] heroLinkedListArray;
    //链表的数量
    private Integer size;

    public HashTab(Integer size) {
        this.size = size;
       heroLinkedListArray =new HeroLinkedList[size];
        for (int i = 0; i < heroLinkedListArray.length; i++) {
            heroLinkedListArray[i] = new HeroLinkedList();//初始化
        }
    }

    //添加方法
    public void  add(Hero hero){
        int p = getP(hero.id);
        heroLinkedListArray[p].add(hero);
    }
    //遍历
    public void list(){
        for (int i = 0; i < size; i++) {
            heroLinkedListArray[i].list(i);
        }
    }
    //查找
        public void findById(int id) {
            //根据散列函数 找到对应的链表
            int p = getP(id);
            Hero hero = heroLinkedListArray[p].findById(id);
            if (hero != null) {//找到
                System.out.printf("在第%d号链表找到%d号英雄\n", (p + 1), p);
            } else {
                System.out.println("在哈希表中未找到该英雄");
            }
        }
    //删除节点
    public void deleteById(int id) {
        //根据散列函数 找到对应的链表
        int p = getP(id);
        heroLinkedListArray[p].delete(id);
    }
    //散列函数，获取存放链表的位置
    public int getP(int id){
        return id % size;
    }
}
class HeroLinkedList{
    private Hero head;//头指针

    //有序添加
    public void add(Hero hero){
        //如果head为空 则直接填入head
        if (head ==null){
            head = hero;
            return;
        }
        //如果不为空 则找到需要添加的位置的前一个节点
        //需借助辅助指针
        boolean flag=false;
        Hero curHero = head;
        while (true){
            if (curHero.next == null){
                break;
            }
            if (curHero.next.id > hero.id){
                break;
            }else if (curHero.next.id == hero.id){
                   flag = true;
                   break;
            }
            curHero = curHero.next;
        }
        if (flag){
            //已存在，不能添加
            System.out.printf("您要添加的英雄的编号%d已存在，无法添加\n", hero.id);
        }else {
            hero.next = curHero.next;
            curHero.next = hero;
        }
    }

    //遍历链表
    public void list(int no){
        //判空
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表信息为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表信息为:");
        //辅助指针
        Hero curHero = head;
        while (true){
            System.out.printf("=> id=%d,name=%s\t", curHero.id, curHero.name);
            if (curHero.next == null){
                break;
            }
            curHero = curHero.next;
        }
        System.out.println();
    }
    //查找
    public Hero findById(int id) {
        if (head == null) {
            System.out.println("链表为空，无法查找");
            return null;
        }
        //辅助指针
        Hero curHero = head;
        while (true) {
            if (curHero.id == id) {
                break;//已找到
            }
            //退出
            if (curHero.next == null) {
                curHero = null;
                break; //置空返回
            }
            curHero = curHero.next;
        }
        return curHero;
    }
    //删除
    public void delete(int id){
        if (head == null) {
            System.out.println("链表为空,无法删除");
            return;
        }
        if (head.id == id && head.next == null) {
            head = null ;
            return;
        }
        if (head.id == id && head.next != null) {
            head=head.next;
            return;
        }
        boolean flag = false;
        Hero curHero =head.next;
        while (true){
            if (curHero.next ==null ){
                break;
            }
            if (curHero.next.id == id){
                flag =true;
                break;
            }
            curHero = curHero.next;
        }
        if (flag) {
            curHero.next = curHero.next.next;//删除节点
        } else {
            System.out.printf("没有找到编号为%d的节点\n", id);
        }
    }
}
class Hero{
    public Integer id;
    public String name;
    public Hero next;
    public Hero(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
