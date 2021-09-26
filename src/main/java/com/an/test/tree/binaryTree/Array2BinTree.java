package com.an.test.tree.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

import com.an.test.TreeNode;

/**
 * 数组转二叉树
 * 测试用例:
 *{8,8,7,9,2,#,#,#,#,4,7},{8,9,2}
 * Integer.MIN_VALUE代表空节点#
 * Created by ZeHua on 2017/5/13.
 */
public class Array2BinTree {
    /**
     * 用树的层次遍历的方法转二叉树
     * @param array
     * @return
     */
    public static TreeNode array2BinTree(int[] array){
        if(array.length==0){
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        int index = 0;
        TreeNode root = new TreeNode(array[index++]);
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(index<array.length){
                cur.left=new TreeNode(array[index++]);
                if(cur.left.val!=Integer.MIN_VALUE){
                    queue.add(cur.left);
                }else {//空结点不入队
                    cur.left=null;
                }
                cur.right=new TreeNode(array[index++]);
                if(cur.right.val!=Integer.MIN_VALUE){
                    queue.add(cur.right);
                }else {//空结点不入队
                    cur.right=null;
                }
            }
        }

        return root;
    }

    
    public static TreeNode createTreeNode(Integer[] array){
        TreeNode root = createTreeNode(array, 1);
        return root;
    }

    private static TreeNode createTreeNode(Integer[] array, int index) {
        if(index > array.length){
            return null;
        }
        Integer value = array[index - 1];
        if(value == null){
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = createTreeNode(array, index * 2);
        node.right = createTreeNode(array, index * 2 + 1);
        return node;
    }
    
    /**
     * 
     *
     *@Description: TODO 改进的算法
     *
		https://www.cnblogs.com/ghimtim/p/12905792.html
		输入：root = [2,null,4,null,null,9,8,null,null,null,null,null,null,4]
		空子节点下没有子节点，因此不需要这么多冗余数据。
		因此输入应该为：
		输入：root = [2,null,4,9,8,null,null,4]
		再次分析数组和二叉树之间的关系，发现数组中的数据顺序是按照二叉树的层级排列的，
		这时可以使用队列的数据结构，数组中的节点按顺序进队，
		每次节点出队，如果不是空节点，那么紧跟着是该节点的左子节点和右子节点
     *@date: 2021年5月27日上午11:36:32
     *@author: yunqing
     *@params:  @param array
     *@params:  @return
     *@return: TreeNode
     *
     */
    
    public static TreeNode arrayToTreeNode(Integer[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeft = true;
        for(int i = 1; i < array.length; i++){
            TreeNode node = queue.peek();
            if(isLeft){
                if(array[i] != null){
                    node.left = new TreeNode(array[i]);
                    queue.offer(node.left);
                }
                isLeft = false;
            }else {
                if(array[i] != null){
                    node.right = new TreeNode(array[i]);
                    queue.offer(node.right);
                }
                queue.poll();
                isLeft = true;
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
        int[] a1 = {8,8,7,9,2,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,4,7};
        int[] a2 = {8,9,2};
        Integer[] a11 = {8,8,7,9,2,null,null,null,null,4,7};
        
        TreeNode at1  = createTreeNode(a11);
        System.out.println(at1);
        TreeNode t1 = array2BinTree(a1);
        System.out.println(t1);
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');

    }
}
