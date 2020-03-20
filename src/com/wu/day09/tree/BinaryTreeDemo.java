package com.wu.day09.tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lynn
 * @date 2020/3/15 - 13:10
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //测试 构建二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "wu"); //根节点
        HeroNode node2 = new HeroNode(2, "gao");
        HeroNode node3 = new HeroNode(3, "高狗");
        HeroNode node4 = new HeroNode(4, "吴狗");
        HeroNode node5 = new HeroNode(5, "高狗狗");
        HeroNode node6 = new HeroNode(6, "高");
      /*  HeroNode node7 = new HeroNode(7, "狗狗");
        HeroNode node8 = new HeroNode(8, "小高");*/
        binaryTree.setRoot(root);
        root.setLeft(node4);
        root.setRight(node2);
        node2.setLeft(node3);
        node2.setRight(node5);
        node3.setLeft(node6);
       /* node3.setRight(node7);
        node5.setRight(node8);*/
        System.out.println("删除前");
        binaryTree.preOrder();
        //打印
        ArrayList<Integer> integers = binaryTree.printFromTopToBottom(root);
        for (Integer i : integers){
            System.out.println(i);
        }
        /*System.out.println("删除后");
        binaryTree.deletePlus(2);
        binaryTree.preOrder();*/
     /*   //测试前序
        System.out.println("前序遍历");//1,4,2,3,5
        binaryTree.preOrder();

        //中序遍历
        System.out.println("中序遍历");//4,1,3,2,5
        binaryTree.infixOrder();

        //后序遍历
        System.out.println("后序遍历");//4,3,5,2,1
        binaryTree.postOrder();*/

      /*  //前序查找
        System.out.println("进入前序查找---");
        HeroNode resNode = binaryTree.preOrderSearch(3);
        if (resNode !=null){
            System.out.printf("找到了，id=%d,name=%s",resNode.getId(),resNode.getName());
        }else {
            System.out.printf("没有找到id为%d的节点",3);
        }*/

        //中序查找
   /*     System.out.println("进入中序查找----");
        HeroNode resNode = binaryTree.infixOrderSearch(3);
        if (resNode !=null){
            System.out.printf("找到了，id=%d,name=%s",resNode.getId(),resNode.getName());
        }else {
            System.out.printf("没有找到id为%d的节点",3);
        }*/

    /*    //后序查找
        System.out.println("进入后序查找---");
        HeroNode resNode = binaryTree.postOrderSearch(3);
        if (resNode !=null){
            System.out.printf("找到了，id=%d,name=%s",resNode.getId(),resNode.getName());
        }else {
            System.out.printf("没有找到id为%d的节点",3);
        }*/

    }
}

//定义二叉树
class BinaryTree {
    //定义根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    public   ArrayList<Integer> printFromTopToBottom(HeroNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<HeroNode> queue = new LinkedList<>();
        HeroNode node =root;
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            list.add(node.getId());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return list;
    }
    //删除结点
    public void delete(int no) {
        if (this.root != null) {
            if (this.root.getId() == no) {
                this.root = null;
            } else {
                this.root.delete(no);
            }
        } else {
            System.out.println("当前树为空，无法删除");
        }
    }

    //结点且保留非叶子结点的子树
    public void deletePlus(int no) {
        if (this.root != null) {
            if (this.root.getId() == no) {
                HeroNode curNode = this.root;
                if (this.root.getLeft() != null) {
                    //借助辅助节点存放
                    this.root = this.root.getLeft();
                    this.root.setRight(curNode.getRight());
                    return;
                } else if (this.root.getLeft() == null && this.root.getRight() != null) {
                    this.root = this.root.getRight();
                    this.root.setLeft(curNode.getLeft());
                    return;
                } else if (this.root.getLeft() == null && this.root.getRight() == null) {
                    this.root = null;
                    return;//终止查找
                }
            } else {
                this.root.deletePlus(no);
            }
        } else {
            System.out.println("当前树为空，无法删除");
        }
    }

    //遍历序列
    //前序遍历
    public void preOrder() {
        if (root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树无空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树无空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//定义节点
class HeroNode {
    private Integer id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    //删除结点
    //思路1:如果删除的结点下含有
    public void delete(int no) {
        //注意 删除时是删除结点的子节点
        //1.判断当前的左子节点是否存在，再判断是否等于待删除的节点 如果是 则截this.left =null ,
        //1.判断当前的右子节点是否存在，再判断是否等于待删除的节点 如果是 则截this.right =null ,
        //3.如果都不是 则向左递归查找，
        //4.如果左递归仍然没有找到，则向右递归查找
        if (this.left != null && this.left.id == no) {
            this.left = null;
            return;//终止查找
        }
        if (this.right != null && this.right.id == no) {
            this.right = null;
            return;
        }
        //向左递归
        if (this.left != null) {
            this.left.delete(no);
        }
        //向右递归
        if (this.right != null) {
            this.right.delete(no);
        }
    }

    //删除进阶
    //如果待删除的结点是非叶子结点 ，现在我们不希望删除该非叶子结点下的子树
    //现有规则
    //1.如果该非叶子结点下只有一个节点  则让其代替待删除结点
    //2.如果该非叶子结点 含有两个结点，则让左边的结点代替删除的结点 且将右边的节点赋给该新节点
    //思路1:如果删除的结点下含有
    public void deletePlus(int no) {
        //注意 删除时是删除结点的子节点
        //1.判断当前的左子节点是否存在，再判断是否等于待删除的节点 如果是 则截this.left =null ,
        //1.判断当前的右子节点是否存在，再判断是否等于待删除的节点 如果是 则截this.right =null ,
        //3.如果都不是 则向左递归查找，
        //4.如果左递归仍然没有找到，则向右递归查找
        //借助辅助节点存放
        HeroNode curNode = this;
        if (this.left != null && this.left.id == no) {
            if (this.left.left != null) {
                curNode = this.left.right;
                this.left = this.left.left;
                this.left.setRight(curNode);
                return;
            } else if (this.left.left == null && this.left.right != null) {
                this.left = this.left.right;
                return;
            } else if (this.left.left == null && this.left.right == null) {
                this.left = null;
                return;//终止查找
            }
        }
        if (this.right != null && this.right.id == no) {
            if (this.right.left != null) {
                curNode = curNode.right.right;//临时存放
                this.right = this.right.left;
                this.right.setRight(curNode);
                return;
            } else if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
                return;
            } else if (this.right.left == null && this.right.right == null) {
                this.right = null;
                return;//终止查找
            }
        }
        //向左递归
        if (this.left != null) {
            this.left.deletePlus(no);
        }
        //向右递归
        if (this.right != null) {
            this.right.deletePlus(no);
        }
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);//输出父节点
        if (this.left != null) {//向左遍历
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {//向左遍历
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {//向左遍历
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        HeroNode heroNode = null; //用于存放找到了的值
        System.out.println("进入前序查找");
        //先和根节点进行比较 如果是 则直接返回
        if (this.id == no) {
            return this;//直接返回
        }
        //如果不是 则向左进行递归 如果相等 则直接返回
        //先判断左节点是否为空
        if (this.left != null) {
            heroNode = this.left.preOrderSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        //判断有节点是否为空 且如果找到了直接返回
        if (this.right != null) {
            heroNode = this.right.preOrderSearch(no);
        }
        return heroNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode heroNode = null; //用于存放找到了的值
        //如果不是 则向左进行递归 如果相等 则直接返回
        //先判断左节点是否为空
        if (this.left != null) {
            heroNode = this.left.infixOrderSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        //再和根节点进行比较 如果是 则直接返回
        System.out.println("进入中序查找");
        if (this.id == no) {
            return this;//直接返回
        }
        //如果不是 则向右进行递归 如果相等 则直接返回
        //先判断右节点是否为空
        if (this.right != null) {
            heroNode = this.right.infixOrderSearch(no);
        }
        return heroNode;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode heroNode = null; //用于存放找到了的值
        //如果不是 则向左进行递归 如果相等 则直接返回
        //先判断左节点是否为空
        if (this.left != null) {
            heroNode = this.left.postOrderSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        //如果不是 则向右进行递归 如果相等 则直接返回
        //先判断右节点是否为空
        if (this.right != null) {
            heroNode = this.right.postOrderSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }
        //再和根节点进行比较 如果是 则直接返回
        System.out.println("进入后序查找");
        if (this.id == no) {
            return this;//直接返回
        }
        return heroNode;//都没有找到 则直接返回空值
    }
}