package com.an.test.tree.binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import com.an.test.TreeNode;

public class Solution4 {

    public static class BSTSolution {
        
        public TreeNode convertBST(TreeNode root) {
            dfs(root, 0);
            return root;
        }

        private int dfs(TreeNode root, int val) {
            if (root == null) {
                return val;
            }
            root.val += dfs(root.right, val);
            return dfs(root.left, root.val);
        }
    }

    public  TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.empty() || node != null){

            while (node != null){
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();

            sum += node.val;
            node.val = sum;

            node = node.left;
        }

        return root;
    }

    
    public static class GenericSolution {
        //This solution is generic for both BST and regular binary trees
        public TreeNode convertBST(TreeNode root) {
            if (root == null) {
                return root;
            }
            List<Integer> list = new ArrayList<>();
            putNodeToList(list, root);
            Collections.sort(list);
            int[] sums = new int[list.size()];
            sums[list.size() - 1] = 0;
            for (int i = list.size() - 2; i >= 0; i--) {
                sums[i] = sums[i + 1] + list.get(i + 1);
            }
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < list.size(); i++) {
                treeMap.put(list.get(i), sums[i]);
            }
            TreeNode result = new TreeNode(treeMap.get(list.get(0)));
            return generateResultRoot(root, treeMap, result);
        }

        private TreeNode generateResultRoot(TreeNode root, TreeMap<Integer, Integer> treeMap, TreeNode result) {
            if (root != null) {
                result.val = treeMap.get(root.val) + root.val;
            }
            if (root.left != null) {
                result.left = new TreeNode(0);
                generateResultRoot(root.left, treeMap, result.left);
            }
            if (root.right != null) {
                result.right = new TreeNode(0);
                generateResultRoot(root.right, treeMap, result.right);
            }
            return result;
        }

        private void putNodeToList(List<Integer> list, TreeNode root) {
            if (root != null) {
                list.add(root.val);
            }
            if (root.left != null) {
                putNodeToList(list, root.left);
            }
            if (root.right != null) {
                putNodeToList(list, root.right);
            }
        }
    }

    
    public static boolean canPlaceFlowers_more_concise_version(int[] flowerbed, int n) {
        int count = 0;
        int i = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                count++;
                flowerbed[i] = 1;
            }
            if (count >= n) {
                return true;
            }
            i++;
        }
        if (count >= n) {
            return true;
        }
        return false;
    }

    public static  boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        if (len == 1) {
            if ((flowerbed[0] == 0 && n <= 1) || n == 0) {
                return true;
            }
            return false;
        }
        
        if (flowerbed[0] == 0 && flowerbed[1] == 0) {
            flowerbed[0] = 1;
            n--;
        }
        
        for (int i = 1; i < len - 1; i++) {
            if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                n--;
              //modify the input, discuss this with interviwer, if not allowed, then have a copy of this input and modify copy
                flowerbed[i] = 1;
            }
            if (n <= 0) {
                return true;
            }
        }
        
        if (len >= 2 && flowerbed[len - 2] == 0 && flowerbed[len - 1] == 0) {
            n--;
        }
        if (n <= 0) {
            return true;
        }
        return false;
    }
    
    
    public static  String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        String result = "" + t.val;
        String left = tree2str(t.left);
        String right = tree2str(t.right);
        if (left.equals("") && right.equals("")) {
            return result;
        }
        if (left.equals("")) {
            return result + "()(" + right + ")";
        }
        if (right.equals("")) {
            return result + "(" + left + ")";
        }
        return result + "(" + left + ")(" + right + ")";
    }

    public static  String tree2str1(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(t, sb);
        return sb.toString();
    }

    private static  void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        if (root.left != null) {
            sb.append("(");
            preorder(root.left, sb);
            sb.append(")");
        }
        if (root.right != null) {
            if (root.left == null) {
                sb.append("()");
            }
            sb.append("(");
            preorder(root.right, sb);
            sb.append(")");
        }
    }

    
    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode mergedNode = null;
        if (t1 != null && t2 != null) {
            mergedNode = new TreeNode(t1.val + t2.val);
        } else if (t1 != null) {
            mergedNode = t1;
        } else if (t2 != null) {
            mergedNode = t2;
        }
        mergedNode.left = mergeTrees(t1.left, t2.left);
        mergedNode.right = mergeTrees(t1.right, t2.right);
        return mergedNode;
    }
    
    
    public static TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode mergedNode = new TreeNode(t1.val + t2.val);
        mergedNode.left = mergeTrees(t1.left, t2.left);
        mergedNode.right = mergeTrees(t1.right, t2.right);
        return mergedNode;
    }

    
    public static void main(String[] args) {
    	Runtime rt = Runtime.getRuntime();
    	System.out.println(rt.freeMemory()/1024/1024);
    	System.out.println(rt.maxMemory()/1024/1024);
    	System.out.println(rt.totalMemory()/1024/1024);
    	int n=1;
    	int[] flowered = {0};
    	System.out.println(canPlaceFlowers(flowered,n));
    	System.out.println(canPlaceFlowers_more_concise_version(flowered,n));
    	
		}
    	
    	
    	

}