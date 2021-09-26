package com.an.test;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * 快速排序的三种实现
 * https://www.sohu.com/a/246785807_684445
 * @Description  
 * @author yunqing
 * @data   2021年5月19日下午5:36:51
 */
public class QuickSort {

	/**
	 * 挖坑法
	 *
	 *@Description: TODO 
	 *@date: 2021年5月19日下午5:37:47
	 *@author: yunqing
	 *@params:  @param arr
	 *@params:  @param startIndex
	 *@params:  @param endIndex
	 *@return: void
	 *
	 */
	public static void quickSort1(int[] arr, int startIndex, int endIndex) {
		// 递归结束条件：startIndex大等于endIndex的时候
		if (startIndex >= endIndex) {
			return;
		}
		// 得到基准元素位置
		int pivotIndex = partition1(arr, startIndex, endIndex);
		// 用分治法递归数列的两部分
		quickSort1(arr, startIndex, pivotIndex - 1);
		quickSort1(arr, pivotIndex + 1, endIndex);
	}

	private static int partition1(int[] arr, int startIndex, int endIndex) {
		// 取第一个位置的元素作为基准元素
		int pivot = arr[startIndex];
		int left = startIndex;
		int right = endIndex;
		// 坑的位置，初始等于pivot的位置
		int index = startIndex;
		// 大循环在左右指针重合或者交错时结束
		while (right >= left) {
			// right指针从右向左进行比较
			while (right >= left) {
				if (arr[right] < pivot) {
					arr[left] = arr[right];
					index = right;
					left++;
					break;
				}
				right--;
			}
			// left指针从左向右进行比较
			while (right >= left) {
				if (arr[left] > pivot) {
					arr[right] = arr[left];
					index = left;
					right--;
					break;
				}
				left++;
			}
		}
		
		arr[index] = pivot;
		return index;
	}


	/**
	 * 
	 *
	 *@Description: TODO 指针交换法
	 *@date: 2021年5月19日下午5:38:01
	 *@author: yunqing
	 *@params:  @param arr
	 *@params:  @param startIndex
	 *@params:  @param endIndex
	 *@return: void
	 *
	 */
	public static void quickSort2(int[] arr, int startIndex, int endIndex) {
		// 递归结束条件：startIndex大等于endIndex的时候
		if (startIndex >= endIndex) {
			return;
		}
		// 得到基准元素位置
		int pivotIndex = partition2(arr, startIndex, endIndex);
		// 根据基准元素，分成两部分递归排序
		quickSort2(arr, startIndex, pivotIndex - 1);
		quickSort2(arr, pivotIndex + 1, endIndex);
	}

	private static int partition2(int[] arr, int startIndex, int endIndex) {
		// 取第一个位置的元素作为基准元素
		int pivot = arr[startIndex];
		int left = startIndex;
		int right = endIndex;
		while (left != right) {
			// 控制right指针比较并左移
			while (left < right && arr[right] > pivot) {
				right--;
			}
			// 控制right指针比较并右移
			while (left < right && arr[left] <= pivot) {
				left++;
			}
			// 交换left和right指向的元素
			if (left < right) {
				int p = arr[left];
				arr[left] = arr[right];
				arr[right] = p;
			}
		}
		// pivot和指针重合点交换
		int p = arr[left];
		arr[left] = arr[startIndex];
		arr[startIndex] = p;
		return left;
	}

	/**
	 * 
	 *
	 *@Description: TODO 非递归
	 *@date: 2021年5月19日下午5:38:18
	 *@author: yunqing
	 *@params:  @param arr
	 *@params:  @param startIndex
	 *@params:  @param endIndex
	 *@return: void
	 *
	 */
	public static void quickSort3(int[] arr, int startIndex, int endIndex) {
		// 用一个集合栈来代替递归的函数栈
		Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();
		// 整个数列的起止下标，以哈希的形式入栈
		Map rootParam = new HashMap();
		rootParam.put("startIndex", startIndex);
		rootParam.put("endIndex", endIndex);
		quickSortStack.push(rootParam);
		// 循环结束条件：栈为空时结束
		while (!quickSortStack.isEmpty()) {
			// 栈顶元素出栈，得到起止下标
			Map<String, Integer> param = quickSortStack.pop();
			// 得到基准元素位置
			int pivotIndex = partition3(arr, param.get("startIndex"), param.get("endIndex"));
			// 根据基准元素分成两部分, 把每一部分的起止下标入栈
			if (param.get("startIndex") < pivotIndex - 1) {
				Map<String, Integer> leftParam = new HashMap<String, Integer>();
				leftParam.put("startIndex", param.get("startIndex"));
				leftParam.put("endIndex", pivotIndex - 1);
				quickSortStack.push(leftParam);
			}
			if (pivotIndex + 1 < param.get("endIndex")) {
				Map<String, Integer> rightParam = new HashMap<String, Integer>();
				rightParam.put("startIndex", pivotIndex + 1);
				rightParam.put("endIndex", param.get("endIndex"));
				quickSortStack.push(rightParam);
			}
		}
	}

	private static int partition3(int[] arr, int startIndex, int endIndex) {
		// 取第一个位置的元素作为基准元素
		int pivot = arr[startIndex];
		int left = startIndex;
		int right = endIndex;
		while (left != right) {
			// 控制right指针比较并左移
			while (left < right && arr[right] > pivot) {
				right--;
			}
			// 控制right指针比较并右移
			while (left < right && arr[left] <= pivot) {
				left++;
			}
			// 交换left和right指向的元素
			if (left < right) {
				int p = arr[left];
				arr[left] = arr[right];
				arr[right] = p;
			}
		}
		// pivot和指针重合点交换
		int p = arr[left];
		arr[left] = arr[startIndex];
		arr[startIndex] = p;
		return left;
	}

	/**
	 * 随机快速排序Java代码实现
	 * 快速排序，使得整数数组 arr 有序
	 */
	public static void quickSort4(int[] arr) {
	    if (arr == null || arr.length < 2) {
	        return;
	    }
	    quickSort4(arr, 0, arr.length - 1);
	}

	/**
	 * 快速排序，使得整数数组 arr 的 [L, R] 部分有序
	 */
	public static void quickSort4(int[] arr, int L, int R) {
	    if(L < R) {
	        // 把数组中随机的一个元素与最后一个元素交换，这样以最后一个元素作为基准值实际上就是以数组中随机的一个元素作为基准值
//	        swap4(arr, new Random().nextInt(R - L + 1) + L, R);
	        int[] p = partition4(arr, L, R);
	        quickSort4(arr, L, p[0] - 1);
	        quickSort4(arr, p[1] + 1, R);
	    }
	}

	/**
	 * 分区的过程，整数数组 arr 的[L, R]部分上，使得：
	 *   大于 arr[R] 的元素位于[L, R]部分的右边，但这部分数据不一定有序
	 *   小于 arr[R] 的元素位于[L, R]部分的左边，但这部分数据不一定有序
	 *   等于 arr[R] 的元素位于[L, R]部分的中间
	 * 返回等于部分的第一个元素的下标和最后一个下标组成的整数数组
	 */
	public static int[] partition4(int[] arr, int L, int R) {

	    int basic = arr[R];
	    int less = L - 1;
	    int more = R + 1;
	    while(L < more) {
	        if(arr[L] < basic) {
	        	++less;
	            swap4(arr,less, L);
	            System.out.println(arr[L]+","+arr[less]);
	            System.out.println("less,L:"+less+","+L++);
	        } else if (arr[L] > basic) {
	            swap4(arr, --more, L);
	        } else {
	            L++;
	        }
	    }

	    return new int[] { less + 1, more - 1 };

	}

	/*
	 * 交换数组 arr 中下标为 i 和下标为 j 位置的元素
	 */
	public static void swap4(int[] arr, int i, int j) {
	    int temp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = temp;
	}


	
	

	public static void main(String[] args) {
		int[] arr = new int[] { 4, 7, 6, 5, 3, 2, 8, 1 };
//		quickSort1(arr, 0, arr.length - 1);
//		quickSort2(arr, 0, arr.length - 1);
//		quickSort3(arr, 0, arr.length - 1);
		quickSort4(arr);
		System.out.println(Arrays.toString(arr));
		Deque<String> deque = new LinkedList<String>();
		Queue queue=new LinkedList<>();
		
		
	}
	
	
	

}
