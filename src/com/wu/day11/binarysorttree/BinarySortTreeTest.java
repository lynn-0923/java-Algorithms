package com.wu.day11.binarysorttree;

/**
 * @author lynn
 * @date 2020/3/20 - 9:10
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //遍历
     /*   System.out.println("---测试中序遍历");
        binarySortTree.infixOrder();
        //查找
        System.out.println("查找二叉树");
        binarySortTree.searchNode(10);*/
        //删除
        System.out.println("删除节点");
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        binarySortTree.infixOrder();
    }
}

//定义二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public Integer delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //查找节点
    public void searchNode(Integer value) {
        if (root != null) {
            Node searchNode = root.searchNode(value);
            if (searchNode == null) {
                System.out.printf("未找到值为%d的节点", value);
            } else {
                System.out.println(searchNode);
            }
        } else {
            System.out.println("二叉树为空，无法查找");
        }
    }

    //查找需要删除的结点
    public Node search(Integer value) {
        if (root != null) {
            return root.searchNode(value);
        } else {
            return null;
        }
    }

    //查找需要删除的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除节点
    public void delNode(Integer value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;//未找到
            }
            //如果无父节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //有父节点
            Node parent = searchParent(value);
            //删除叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断待删除的节点是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null; //左子节点
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null; //右子节点
                }
            } else if (targetNode.left != null && targetNode.right != null) {//右两个子树
                Integer minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//只有一个子树 在左
                if (targetNode.left != null) {
                    if (parent != null) {
                        //待删除的子树是其父节点的左子树
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //待删除的子树是其父节点的右子树
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//在右
                    if (targetNode.right != null) {
                        if (parent != null) {
                            if (parent.left.value == value) {
                                parent.left = targetNode.right;
                            } else {
                                parent.right = targetNode.right;
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
            root = node;
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

class Node {
    Integer value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        } else if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else if (node.value > this.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //查找结点
    public Node searchNode(Integer value) {
        if (value == null) {
            return null;
        } else if (this.value == value) {
            return this;
        } else if (this.left != null && value < this.value) {
            return this.left.searchNode(value);
        } else if (this.right != null && value >= this.value) {
            return this.right.searchNode(value);
        }
        return null;
    }

    //查找需要删除节点的父节点
    public Node searchParent(Integer value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else if (this.left != null && value < this.value) {
            return this.left.searchParent(value);
        } else if (this.right != null && value >= this.value) {
            return this.right.searchParent(value);
        } else {
            return null;
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
