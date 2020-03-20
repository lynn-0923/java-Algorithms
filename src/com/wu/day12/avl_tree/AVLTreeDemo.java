package com.wu.day12.avl_tree;

/**
 * @author lynn
 * @date 2020/3/20 - 17:45
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int arr[] = {4, 3, 6, 5, 7, 8};
        int arr[] = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("测试中序遍历");
        avlTree.infixOrder();

        //获得高度
        System.out.println(avlTree.getRoot().height());
        //获得左子树高度
        System.out.println(avlTree.getRoot().leftHeight());
        //获得右子树高度
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

//创建平衡二叉树
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找需要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    //删除含有两个结点的子树

    /**
     * @param node 找到该节点下的最小值
     * @return 返回待删除节点的右子树的最小值  循环向左遍历
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        //删除该节点的值
        delNode(target.value);
        return target.value;
    }

    /**
     * @param node 找到该节点下的最大值
     * @return 返回待删除节点的左子树的最大值  循环向右遍历
     */
    public int delLeftTreeMax(Node node) {
        Node target = node;
        while (target.right != null) {
            target = target.right;
        }
        //删除
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //查找需要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {
                //没有找到
                return;
            }
            //如果没有父节点
            if (root.left == null && root.right == null) {
                root = null; //说明targetNode = root 也只有root
                return;
            }
            //有父节点
            Node parentNode = searchParent(value);//根据上个判断 说明是有的
            //删除叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是parentNode的左子节点还是右子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    //左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    //右子节点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//右两个子树
                /*int minVal = delRightTreeMin(targetNode.right);//从右子树遍历找到最小值
                targetNode.value = minVal;*/
                int maxVal = delLeftTreeMax(targetNode.left);//从左子树遍历找到最小值
                targetNode.value = maxVal;
            } else {//删除只有一颗节点的树
                //如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //如果targetNode是parentNode的左子节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else {
                            //如果targetNode是parentNode的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    //如果要删除的节点有右子节点
                    if (targetNode.right != null) {
                        if (parentNode != null) {
                            //如果targetNode是parentNode的左子节点
                            if (parentNode.left.value == value) {
                                parentNode.left = targetNode.right;
                            } else {
                                //如果targetNode是parentNode的右子节点
                                parentNode.right = targetNode.right;
                            }
                        } else {
                            root = targetNode.right;
                        }
                    }
                }
            }
        }
    }

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;//直接添加到root
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}

//创建节点
class Node {
    int value;
    Node left;
    Node right;

    //构造器
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //左旋转
    public void leftRotate() {
        //1.创建新的节点，以当前节点的值创建
        Node newNode = new Node(this.value);
        //2.将新节点的左子树指向当前节点的左子树
        newNode.left = this.left;
        //3.将新节点的右子树指向当前节点右子树的左子树
        newNode.right = this.right.left;
        //4.将当前节点的右子树的值赋给当前节点
        this.value = this.right.value;
        //5.将当前节点的右子树指向右子树的右子树
        this.right = this.right.right;
        //6.将当前的左子树指向新节点
        this.left = newNode;
    }

    //右旋转
    public void rightRotate() {
        //创建新节点 以当前节点的值创建
        Node newNode = new Node(this.value);
        //将新节点的右子树指向当前节点的右子树
        newNode.right = this.right;
        //将新节点的左子树指向当前节点左子树的右子树
        newNode.left = this.left.right;
        //将当前节点的左子树的值赋给当前节点
        this.value = this.left.value;
        //将当前节点的左子树指向当前节点的左子树的左子树
        this.left = this.left.left;
        //将当前节点的右子树指向新节点
        this.right = newNode;
    }

    //返回当前节点的左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    //获得当前节点的高度
    public int height() {
        return Math.max(left == null ? 0 : this.left.height(), right == null ? 0 : this.right.height()) + 1;//需要加上当前节点
    }
    //查找需要删除的节点

    /**
     * @param value 待查找的值
     * @return 找到后则返回节点  未找到则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value && this.left != null) {//如果查找的值小于当前节点的值
            return this.left.search(value);
        } else if (value >= this.value && this.right != null) {//如果查找的值大于当前节点的值
            return this.right.search(value);
        } else {
            return null;
        }
    }
    //查找需要删除结点的父节点

    /**
     * @param value 待查找的值
     * @return 返回找到的父节点 未找到则返回null
     */
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
            //如果查找的值小于当前节点的值 且当前节点的左子节点不为空
        } else if (this.left != null && value < this.value) {
            return this.left.searchParent(value);//向做递归
            //如果查找的值大于当前节点的值 且当前节点的右子节点不为空
        } else if (this.right != null && value >= this.value) {
            return this.right.searchParent(value);//向右递归
        } else {
            return null;
        }
    }

    //添加结点的方法
    //递归的形式添加，注意需要满足二叉排序树的特点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {//比当前节点的值小
            if (this.left == null) {
                //直接添加
                this.left = node;
            } else {
                this.left.add(node);//向左递归
            }
        } else {//比当前节点的值大
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);//向右递归
            }
        }
        //在次判断是否需要左旋转 即右子树的高度 - 左子树的 > 1
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果当前节点的右子树的左子树的高度大于当前节点的右子树的右子树
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //先右子树进行右旋转
                rightRotate();
            }
            //再进行左旋转
            leftRotate();
            return;//提高效率 或防止bug 必须要
        }
        //在次判断是否需要右旋转 即左子树的高度 - 右子树的 > 1
        if (this.leftHeight() - this.rightHeight() > 1) {
            //如果当前节点的左子树的右子树的高度大于当前节点的左子树的左子树
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //先对左子树进行左旋转
                left.leftRotate();
            }
            //在进行右旋转
            rightRotate();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}