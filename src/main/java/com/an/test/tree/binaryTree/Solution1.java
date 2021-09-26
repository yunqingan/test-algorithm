package com.an.test.tree.binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution1 {

	// then I clicked its Tags, and find it's marked with so many tags: Binary
	// Search, HashTable, Two Pointers, Sort, now I'll try to do it one by one
	// inspired by this post:
	// https://discuss.leetcode.com/topic/45685/three-java-solutions
	public int[] intersection_two_pointers(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet();
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0;
		int j = 0;
		for (; i < nums1.length && j < nums2.length;) {
			if (nums1[i] < nums2[j]) {
				i++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				set.add(nums1[i]);
				i++;
				j++;
			}
		}
		int[] result = new int[set.size()];
		Iterator<Integer> it = set.iterator();
		int k = 0;
		while (it.hasNext()) {
			result[k++] = it.next();
		}
		return result;
	}

	public int[] intersection_binary_search(int[] nums1, int[] nums2) {
		// this approach is O(nlgn)
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		Set<Integer> intersect = new HashSet();
		for (int i : nums1) {
			if (binarySearch(i, nums2)) {
				intersect.add(i);
			}
		}
		int[] result = new int[intersect.size()];
		Iterator<Integer> it = intersect.iterator();
		for (int i = 0; i < intersect.size(); i++) {
			result[i] = it.next();
		}
		return result;
	}

	private boolean binarySearch(int i, int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == i) {
				return true;
			} else if (nums[mid] > i) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return false;
	}

	// tried a friend's recommended approach, didn't finish it to get it AC'ed,
	// turned to normal approach as above and got it AC'ed.
	private boolean binarySearch_not_working_version(int i, int[] nums) {
		if (nums == null || nums.length == 0) {
			return false;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > i) {
				right = mid;
			} else if (nums[mid] < 1) {
				left = mid;
			} else if (nums[mid] == i) {
				return true;
			} else {
				return false;
			}
		}
		return nums[left] == i || nums[right] == i;
	}

	public  static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer> list = new ArrayList();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
	
	
	public static  boolean isPerfectSquare(int num) {
		long i = 1;
		long temp = 1;
		while (temp < num) {
			i += 2;
			temp += i;
			System.out.println("i:"+i+",temp:"+temp);
		}
		return temp == num;
	}
	

	public int[] intersection_two_hashsets(int[] nums1, int[] nums2) {
		// this approach is O(n)
		Set<Integer> set1 = new HashSet();
		for (int i = 0; i < nums1.length; i++) {
			set1.add(nums1[i]);
		}
		Set<Integer> intersect = new HashSet();
		for (int i = 0; i < nums2.length; i++) {
			if (set1.contains(nums2[i])) {
				intersect.add(nums2[i]);
			}
		}
		int[] result = new int[intersect.size()];
		Iterator<Integer> it = intersect.iterator();
		for (int i = 0; i < intersect.size(); i++) {
			result[i] = it.next();
		}
		return result;
	}

	// so naturally, I come up with this naive O(n^2) solution and surprisingly it
	// got AC'ed immediately, no wonder it's marked as EASY.
	public int[] intersection_naive(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet();
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				if (nums1[i] == nums2[j]) {
					set.add(nums1[i]);
				}
			}
		}
		int[] result = new int[set.size()];
		Iterator<Integer> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			result[i++] = it.next();
		}
		return result;
	}

	
	
	public static void main(String... strings) {
//		Solution test = new Solution();
//		int[] nums1 = new int[] { 1, 2 };
//		int[] nums2 = new int[] { 2, 1 };
//		test.intersection_binary_search(nums1, nums2);
		isPerfectSquare(256);
	}

}