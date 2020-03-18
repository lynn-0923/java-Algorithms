package com.wu.day09.tree.threadedbinarytree;

/**
 * @author lynn
 * @date 2020/3/17 - 12:08
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        HeroNode root = new HeroNode(1, "wu"); //根节点
        HeroNode node2 = new HeroNode(3, "gao");
        HeroNode node3 = new HeroNode(6, "高狗");
        HeroNode node4 = new HeroNode(8, "吴狗");
        HeroNode node5 = new HeroNode(10, "高狗狗");
        HeroNode node6 = new HeroNode(14, "高");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //二叉树 后续会递归创建 现在先手动创建
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.infixThreadedNodes();
        //测试线索化
        //判断10的前驱和后继
       /* HeroNode left = node5.getLeft();
        HeroNode right = node5.getRight();
        System.out.println("10的前驱结点为：" + left);//3
        System.out.println("10的后继结点为：" + right);//1*/
       //中序线索化遍历节点
        threadedBinaryTree.infixThreadedBinaryTree();//8,3,10,1,14,6
    }
}

//线索化二叉树
class ThreadedBinaryTree {
    //定义根节点
    private HeroNode root;

    //为了实现线索化 需要创建需要线索化的前驱结点
    private HeroNode pre = null; //前驱节点

    //重载
    public void infixThreadedNodes() {
        this.infixOrderThreadedBinaryTree(root);
    }
    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //中序遍历线索二叉树
    public void infixThreadedBinaryTree() {
        //从root开始遍历
        HeroNode node = root;
        while (node != null) {
            //循环找到leftType=1的节点 说明是线索化
            while (node.getLeftType() == 0) {
                node = node.getLeft(); //一直向左遍历
            }
            //找到后  打印这个节点
            System.out.println(node);
            //接着判断该节点的rightType是否=1
            if (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的这个节点
            node = node.getRight();
        }
    }

    //中序线索化
    public void infixOrderThreadedBinaryTree(HeroNode node) {
        //判空
        if (node == null) {
            return;
        }
        //先线索化左子树
        infixOrderThreadedBinaryTree(node.getLeft());
        //线索化当前节点
        //注意 因为线索化的节点需找到该节点的前驱节点
        //线索化前驱结点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            //修改左指针的类型
            node.setLeftType(1);
        }
        //注意 线索化右节点时需找到该节点的前一个节点，例如 假如需要线索化当前节点的node 则先找到pre
        //线索化后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱的右指针类型
            pre.setRightType(1);
        }
        //注意！！！！ 需要保证当前节点是下一个节点的前驱节点
        pre = node;
        infixOrderThreadedBinaryTree(node.getRight());
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
    private HeroNode left; //节点的左指针
    private HeroNode right; //节点的右指针

    //1.如果leftType=1 ,代表前驱节点，0代表左子树
    //2.如果rightType=1,代表后继节点，0代表右子树
    private int leftType;
    private int rightType;


    public HeroNode(int no, String name) {
        this.id = no;
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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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