package com.an.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class TreeNodeUtil {
	
	
	/**
     * 构建二叉树
     * @param list   输入序列
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer val = list.removeFirst();
        if(val!=null){
            node = new TreeNode(val);
            node.left = createBinaryTree(list);
            node.right = createBinaryTree(list);
        }
        return node;
    }
    
    
    
    /**
     * 二叉树前序遍历   根-> 左-> 右
     * @param node    二叉树节点
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.val+" ");
        preOrderTraveral(node.left);
        preOrderTraveral(node.right);
    }
    
    
    /**
     * 二叉树中序遍历   左-> 根-> 右
     * @param node   二叉树节点
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        inOrderTraveral(node.left);
        System.out.print(node.val+" ");
        inOrderTraveral(node.right);
    }
    
    
    /**
     * 二叉树后序遍历   左-> 右-> 根
     * @param node    二叉树节点
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        postOrderTraveral(node.left);
        postOrderTraveral(node.right);
        System.out.print(node.val+" ");
    }
    
    /**
     * 还是一样，先看非递归前序遍历

    首先申请一个新的栈，记为stack；
    声明一个结点treeNode，让其指向node结点；
    如果treeNode的不为空，将treeNode的值打印，并将treeNode入栈，然后让treeNode指向treeNode的右结点，
    重复步骤3，直到treenode为空；
    然后出栈，让treeNode指向treeNode的右孩子
    重复步骤3，直到stack为空.

     *
     *@Description: TODO 
     *@date: 2021年5月24日下午5:14:35
     *@author: yunqing
     *@params:  @param node
     *@return: void
     *
     */
    public static void preOrderTraveralWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = node;
        while(treeNode!=null || !stack.isEmpty()){
            //迭代访问节点的左孩子，并入栈
            while(treeNode != null){
                System.out.print(treeNode.val+" ");
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }
    
    
    /**
     * 中序遍历非递归
    申请一个新栈，记为stack，申请一个变量cur，初始时令treeNode为头节点；
    先把treeNode节点压入栈中，对以treeNode节点为头的整棵子树来说，依次把整棵树的左子树压入栈中，即不断令treeNode=treeNode.left，然后重复步骤2；
    不断重复步骤2，直到发现cur为空，此时从stack中弹出一个节点记为treeNode，打印node的值，并让treeNode= treeNode.right，然后继续重复步骤2；
    当stack为空并且cur为空时结束。

     *
     *@Description: TODO 
     *@date: 2021年5月24日下午5:15:00
     *@author: yunqing
     *@params:  @param node
     *@return: void
     *
     */
    public static void inOrderTraveralWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = node;
        while(treeNode!=null || !stack.isEmpty()){
            while(treeNode != null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                System.out.print(treeNode.val+" ");
                treeNode = treeNode.right;
            }

        }
    }
    /**
     * 
     *
     *@Description: TODO 
     *@date: 2021年5月24日下午5:15:23
     *@author: yunqing
     *@params:  @param node
     *@return: void
     *
     */
    public static void postOrderTraveralWithStack(TreeNode node){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = node;
        TreeNode lastVisit = null;   //标记每次遍历最后一次访问的节点
        while(treeNode!=null || !stack.isEmpty()){//节点不为空，结点入栈，并且指向下一个左孩子
            while(treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            //栈不为空
            if(!stack.isEmpty()){
                //出栈
                treeNode = stack.pop();
                /**
                 * 这块就是判断treeNode是否有右孩子，
                 * 如果没有输出treeNode.val，让lastVisit指向treeNode，并让treeNode为空
                 * 如果有右孩子，将当前节点继续入栈，treeNode指向它的右孩子,继续重复循环
                 */
                if(treeNode.right == null || treeNode.right == lastVisit) {
                    System.out.print(treeNode.val + " ");
                    lastVisit = treeNode;
                    treeNode  = null;
                }else{
                    stack.push(treeNode);
                    treeNode = treeNode.right;
                }

            }

        }
    }
    
    
    /**
       * 最后再给大家介绍一下层序遍历
	具体步骤如下：
	    首先申请一个新的队列，记为queue；
	    将头结点head压入queue中；
	    每次从queue中出队，记为node，然后打印node值，如果node左孩子不为空，则将左孩子入队；如果node的右孩子不为空，则将右孩子入队；
	    重复步骤3，直到queue为空。
     *
     *@Description: TODO 
     *@date: 2021年5月24日下午5:15:49
     *@author: yunqing
     *@params:  @param root
     *@return: void
     *
     */
    public static void levelOrder(TreeNode root){
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.pop();
            System.out.print(root.val+" ");
            if(root.left!=null) queue.add(root.left);
            if(root.right!=null) queue.add(root.right);
        }
    }
    
//    public static void main(String[] args) {
//		
//    	LinkedList<Integer> list  = new LinkedList<>();
//    	list.add(3);
//    	list.add(1);
//    	list.add(0);
//    	list.add(4);
//    	list.add(5);
//    	list.add(6);
//    	list.add(8);
//    	list.add(9);
//    	TreeNode node = createBinaryTree(list);
//    	preOrderTraveral(node);
//    	System.out.println("--------");
//    	inOrderTraveral(node);
//    	System.out.println("--------");
//    	postOrderTraveral(node);
//	}
    
    public static void main(String[] args) {

        UnaryOperator<Integer> u1 = integer ->
                integer + 100;

        System.out.println(u1.apply(100));

        System.out.println("-----------");

        List<String> strings = Arrays.asList(
                "asas",
                "ddd",
                "csd"
        );

        UnaryOperator<String> unaryOperator = s ->
                s;

        for (String s : strings) {
            System.out.println(unaryOperator.apply(s));
        }

        System.out.println("-----------");

        List<Integer> list = Arrays.asList(10,20,30,40,50);

        UnaryOperator<Integer> unaryOperator2 = integer -> integer*integer;

        unaryOperatorFun(unaryOperator2,list)
                .forEach(System.out::println);
        System.out.println(testFunction(5,i -> i * 2 + 1,j -> j * j));
        
    }

    private static List<Integer> unaryOperatorFun(UnaryOperator<Integer> unaryOpt, List<Integer> list){
        List<Integer> uniList = new ArrayList<>();
        list.forEach(i->uniList.add(unaryOpt.apply(i)));
        return uniList;
    }

    public static int testFunction(int i, Function<Integer,Integer> function1,Function<Integer,Integer> function2) {

        return  function1.andThen(function2).apply(i);
    }

    
}
