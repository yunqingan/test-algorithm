package com.an.test.tree.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.an.test.TreeNode;

public class BinaryTree2 {
	
	 /**
	  * 
	  *
	  *@Description: TODO 
	  *二叉树是每个节点最多有两个子树的树结构。通常子树被称作“左子树”（left subtree）和“右子树”（right subtree）。
	  *二叉树常被用于实现二叉查找树和二叉堆。

二叉树的每个结点至多只有二棵子树(不存在度大于2的结点)，二叉树的子树有左右之分，次序不能颠倒。
二叉树的第i层至多有2^{i-1}个结点；深度为k的二叉树至多有2^k-1个结点；对任何一棵二叉树T，如果其终端结点数为n_0，度为2的结点数为n_2，则n_0=n_2+1。

一棵深度为k，且有2^k-1个节点称之为满二叉树；深度为k，有n个节点的二叉树，当且仅当其每一个节点都与深度为k的满二叉树中，序号为1至n的节点对应时，
称之为完全二叉树。
	  *@date: 2021年5月24日下午6:29:16
	  *@author: yunqing
	  *@params:  @param arrs
	  *@params:  @return
	  *@return: List<TreeNode>
	  *
	  */
	public List<TreeNode> initTree(int[] arrs) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int val : arrs) {
			nodes.add(new TreeNode(val));
		}
		//arrs.length / 2 - 1是确保最后一个节点的左右子节点都存在 
		for (int parentIndex = 0; parentIndex < arrs.length / 2 - 1; parentIndex++) {
			nodes.get(parentIndex).left = nodes.get(parentIndex * 2 + 1);
			nodes.get(parentIndex).right = nodes.get(parentIndex * 2 + 2); 
		}
		int lastParentIndex = arrs.length / 2 - 1;
		nodes.get(lastParentIndex).left = nodes.get(lastParentIndex * 2 + 1);
		if(arrs.length % 2 != 0){
			nodes.get(lastParentIndex).right = nodes.get(lastParentIndex * 2 + 2);
		}
		return nodes;
	}
	
	/*
	 * 先序遍历二叉树： 根左右
	 * 
	 * 使用递归
	 * 
	 * @param node
	 *      遍历的节点
	 * */
	public void preOrderTraverse(TreeNode node){
		if(node == null)
			return;
		System.out.print(node.val + " ");
		preOrderTraverse(node.left);
		preOrderTraverse(node.right);
	}
	
	/*
	 * 先序遍历二叉树： 根左右
	 * 
	 * 使用循环
	 * 
	 * @param node
	 *       遍历的节点
	 * */
	public void preOrderTraverseByWhile(TreeNode node){
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.push(node);
		TreeNode currentNode;
		while (!stack.isEmpty()) {
			currentNode = stack.pop();
			System.out.print(currentNode.val + " ");
			if(currentNode.right != null){
				stack.push(currentNode.right);
			}
			if (currentNode.left != null) {
				stack.push(currentNode.left);
			}
		}
	}
	
	/*
	 * 中序遍历二叉树： 左根右
	 * 
	 * 使用递归
	 * 
	 * @param node
	 *       遍历的节点
	 * */
	public void inOrderTraverse(TreeNode node){
		if(node == null)
			return;
		inOrderTraverse(node.left); //递归输出左节点
		System.out.print(node.val + " "); 
		inOrderTraverse(node.right); //递归输出右节点
	}
	
	/*
	 * 中序遍历二叉树： 左根右
	 * 
	 * 使用循环
	 * 
	 * @param node
	 *       遍历的节点
	 * */
	public void inOrderTraverseByWhile(TreeNode node){
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode currentNode = node;
		while (currentNode != null || !stack.isEmpty()) {
			while(currentNode != null){
				stack.push(currentNode);
				currentNode = currentNode.left;
			}
			if(!stack.isEmpty()){
				currentNode = stack.pop();
				System.out.print(currentNode.val + "");
				currentNode = currentNode.right;
			}
		}
	}
	
	/*
	 * 后序遍历二叉树： 左右根
	 * 
	 *  使用递归
	 * 
	 * @param node
	 *       遍历的节点
	 * */
	public void afterOrderTraverse(TreeNode node){
		if(node == null)
			return;
		afterOrderTraverse(node.left);//递归输出左节点
		afterOrderTraverse(node.right);//递归输出右节点
		System.out.print(node.val + " ");
	}
	
	/*
	 * 后序遍历二叉树： 左右根
	 * 
	 *  使用循环
	 * 
	 * @param node
	 *       遍历的节点
	 * */
	public void afterOrderTraverseByWhile(TreeNode node){
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode right = null;
		TreeNode currentNode = node;
		while (currentNode != null || !stack.isEmpty()) {
			while(currentNode != null){
				stack.push(currentNode);
				currentNode = currentNode.left;
			}
			currentNode = stack.pop();
			//当上一个访问的结点是右孩子或者当前结点没有右孩子则访问当前结点
			while(currentNode != null && (currentNode.right == null || currentNode.right == right)){
				System.out.print(currentNode.val + " ");
				right = currentNode;
				if(stack.isEmpty()){
					return;
				}
				currentNode = stack.pop();
			}
			stack.push(currentNode);
			currentNode = currentNode.right;
		}
	}
	
	
	public static void main(String[] args) {
		//假设二叉树中的数据为： 1,2,3,4,5,6,7,8,9从上到下，从左至右
		int[] arrs = new int[]{1,2,3,4,5,6,7,8,9};
		BinaryTree2 binaryTree = new BinaryTree2();
		List<TreeNode> binaryTreeNodes = binaryTree.initTree(arrs);
		TreeNode rootNode = binaryTreeNodes.get(0);
		//先序遍历结果
		System.out.println("递归先序遍历结果: ");
		binaryTree.preOrderTraverse(rootNode);
		System.out.println("\n循环先序遍历结果: ");
		binaryTree.preOrderTraverseByWhile(rootNode);
		//中序遍历结果
		System.out.println("\n中序遍历结果: ");
		binaryTree.inOrderTraverse(rootNode);
		System.out.println("\n循环中序遍历结果: ");
		binaryTree.inOrderTraverse(rootNode);
		//后序遍历结果
		System.out.println("\n后序遍历结果: ");
		binaryTree.afterOrderTraverse(rootNode);
		System.out.println("\n循环后序遍历结果: ");
		binaryTree.afterOrderTraverseByWhile(rootNode);
	}	

}
