package com.an.test;

/**
 * 
 * @Description Definition for a binary tree node.
 * @author yunqing
 * @data 2021年5月20日上午9:52:43
 */
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}