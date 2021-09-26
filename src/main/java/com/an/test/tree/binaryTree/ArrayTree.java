package com.an.test.tree.binaryTree;

/**
 * 
 * @Description 数组 二叉树
 * @author yunqing
 * @data 2021年5月25日上午10:39:22
 */
public class ArrayTree {

	private int[] arrayTree;
	private int size = 0;

	public ArrayTree() {
		super();
		arrayTree = new int[10];
	}

	public void insert(int num) {
		if (search(0, num) != -1)
			return;
		if (arrayTree.length == 0) {
			arrayTree[0] = num;
		} else {
			insert(0, num);
		}

	}

	public void insert(int index, int num) {
		if (arrayTree.length < 2 * index + 2) {
			reSize(2 * index + 2);
		}
		if (arrayTree[index] == 0) {
			arrayTree[index] = num;
			size++;
			return;
		}

		if (num > arrayTree[index]) {
			insert(2 * index + 2, num);
		} else {
			insert(2 * index + 1, num);
		}

	}

	public void reSize(int length) {
		int[] newArrayTree = new int[length];
		System.arraycopy(arrayTree, 0, newArrayTree, 0, arrayTree.length);
		arrayTree = newArrayTree;
	}

	public int search(int num) {
		return search(0, num);
	}

	public int search(int index, int num) {
		if (arrayTree.length <= index)
			return -1;

		if (arrayTree[index] == num)
			return index;

		if (num > arrayTree[index]) {
			return search(2 * index + 2, num);
		} else {
			return search(2 * index + 1, num);
		}

	}

	public int get(int index) {
		if (arrayTree.length > index) {
			return arrayTree[index];
		} else {
			return -1;
		}

	}

//	public static void main(String[] args) {
//
//		ArrayTree arrayTree = new ArrayTree();
//		arrayTree.insert(50);
//		arrayTree.insert(25);
//		arrayTree.insert(76);
//		arrayTree.insert(37);
//		arrayTree.insert(62);
//		arrayTree.insert(84);
//		arrayTree.insert(31);
//		arrayTree.insert(43);
//		arrayTree.insert(55);
//		arrayTree.insert(92);
//
//		System.out.println("get:" + arrayTree.get(10));
//		System.out.println("index:" + arrayTree.search(24));
//		System.out.println("size:" + arrayTree.size);
//
//	}

	public static void main(String args[]) {
		int i, level;
		int data[] = { 6, 3, 5, 9, 7, 8, 4, 2 }; /* 原始数组 */
		int btree[] = new int[16];
		for (i = 0; i < 16; i++)
			btree[i] = 0;
		System.out.print("原始数组内容: \n");
		for (i = 0; i < 8; i++) {
			System.out.print("[" + data[i] + "] ");
		}
		System.out.println();
		for (i = 0; i < 8; i++) { /* 把原始数组中的值逐一对比 */
			System.out.println("i==>" + i);
			for (level = 1; btree[level] != 0;) { /* 比较树根及数组内的值 */
				if (data[i] > btree[level]) { /* 如果数组内的值大于树根，则往右子树比较 */
					level = level * 2 + 1;
				} else { /* 如果数组内的值小于或等于树根，则往左子树比较 */
					level = level * 2;
				}
			} /* 如果子树节点的值不为0，则再与数组内的值比较一次 */
			btree[level] = data[i]; /* 把数组值放入二叉树 */
			System.out.println("data[" + i + "]==>" + data[i] + "，进入" + " btree[" + level + "]==>" + btree[level]);
		}
		System.out.print("二叉树内容：\n");
		for (i = 1; i < 16; i++) {
			System.out.print("[" + btree[i] + "] ");
		}
		System.out.print("\n");
	}

}
