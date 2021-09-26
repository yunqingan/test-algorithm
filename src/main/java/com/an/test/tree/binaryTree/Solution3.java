package com.an.test.tree.binaryTree;

import java.util.LinkedList;
import java.util.Queue;



class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};

public class Solution3 {
	
   private String  merNo;
   
   

	public String getMerNo() {
	return merNo;
}



public void setMerNo(String merNo) {
	this.merNo = merNo;
}



	public static void treeTraveral(Node node) {
		Queue<Node> qu = new LinkedList<Node>();
		qu.offer(node);
		while(!qu.isEmpty()) {
			Node nd = qu.poll();
			System.out.println(nd.val+","+nd.isLeaf);
			if(nd.topLeft!=null) {
				qu.offer(nd.topLeft);
			}
			if(nd.topRight!=null) {
				qu.offer(nd.topRight);
			}
			if(nd.bottomLeft!=null) {
				qu.offer(nd.bottomLeft);
			}
			if(nd.bottomRight!=null) {
				qu.offer(nd.bottomRight);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		Solution3 slt = new Solution3();
		int[][] grid = new int[][] {{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
		Node node =slt.construct(grid);
		System.out.println(node);
		treeTraveral(node);
  
		float  a= 4.58999999999999f;
		float  b= 4.58999999999999f;
		System.out.println(Float.valueOf(a).equals(Float.valueOf(b)));
	   final boolean rst = true;

	   

	}
	

	
      public Node construct(int[][] grid) {
        return construct(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    private Node construct(int[][] grid, int top, int bottom, int left, int right) {
        for (int i = top; i <= bottom; i++) {
            for (int j = left; j <= right; j++) {
                if (grid[i][j] != grid[top][left]) {
                    Node node = new Node(false, false);
                    int mid1 = top + ((bottom - top) >> 1), mid2 = left + ((right - left) >> 1);
                    node.topLeft = construct(grid, top, mid1, left, mid2);
                    node.topRight = construct(grid, top, mid1, mid2 + 1, right);
                    node.bottomLeft = construct(grid, mid1 + 1, bottom, left, mid2);
                    node.bottomRight = construct(grid, mid1 + 1, bottom, mid2 + 1, right);
                    return node;
                }
            }
        }
        return new Node(grid[top][left] == 1, true);
    }

 
 
}