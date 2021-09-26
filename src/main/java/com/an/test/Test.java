package com.an.test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import org.springframework.lang.NonNull;
public class Test {


	public static int[] twoSum(int[] nums, int target) {
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement)) {
	            return new int[] { map.get(complement), i };
	        }
	        System.out.println("nums[i]"+nums[i]+" i"+i);
	        map.put(nums[i], i);
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}
	
	

	public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
		return ans;
	}

	public static boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}
	
	

	    public static int lengthOfLongestSubstring1(String s) {
	        int n = s.length();
	        Set<Character> set = new HashSet<>();
	        int ans = 0, i = 0, j = 0;
	        while (i < n && j < n) {
	            // try to extend the range [i, j]
	            if (!set.contains(s.charAt(j))){
	                set.add(s.charAt(j++));
	                System.out.println("set:"+set.toString());
	                ans = Math.max(ans, j - i);
	            }
	            else {
	            	System.out.println("del:"+s.charAt(i));
	                set.remove(s.charAt(i++));
	                System.out.println("remove:"+set.toString());
	            }
	        }
	        return ans;
	    }

	
	    
	    public static double findMedianSortedArrays(int[] A, int[] B) {
	        int m = A.length;
	        int n = B.length;
	        if (m > n) { // to ensure m<=n
	            int[] temp = A; A = B; B = temp;
	            int tmp = m; m = n; n = tmp;
	        }
	        
	        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
	        while (iMin <= iMax) {
	            int i = (iMin + iMax) / 2;
	            int j = halfLen - i;
	            System.out.println("i,j,iMin,iMax,halfLen:"+i+","+j+","+iMin+","+iMax+","+halfLen);
	            if (i < iMax && B[j-1] > A[i]){
	                iMin = iMin + 1; // i is too small
	                System.out.println("iMin:"+iMin);
	            }else if (i > iMin && A[i-1] > B[j]) {
	                iMax = iMax - 1; // i is too big
	                System.out.println("iMax:"+iMax);
	            } else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}
				
				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}

	            return (maxLeft + minRight) / 2.0;
	            }
	        }
	        return 0.0;
	    }

	
	    
	    public static void mapSort(int[] A ,int [] B) {
	    	int [] rst = new int[A.length+B.length];
	    	int [] tmp;
	    	//保证b比较长
	    	if(A.length>B.length) {
	    		tmp = A;
	    		A=B;
	    		B=tmp;
	    	}
	    	int tmpA=0;
	    	int tmpB=0;
	    	for(int i=0;i<rst.length;i++) {
	    		   if(tmpA>=A.length) {
	    			   rst[i]=B[tmpB];
	    			   ++tmpB;
	    		   }else if(tmpB>=B.length){
	    			   rst[i]=A[tmpA];
	    			   ++tmpA;
	    		   }else {
	    			   if(A[tmpA]>=B[tmpB]) {
	 	  	              rst[i]=B[tmpB];
	 	  	              ++tmpB; 
	 	  	              System.out.println("tmpB:"+tmpB);
	 	  	    		}else {
	 	  	    			rst[i]=A[tmpA];
	 	  	    		    ++tmpA;
	 	  	    			System.out.println("tmpA:"+tmpA);
	 	  	    		} 
	    		   }
	    		    
	    			
	    
	    		
	    		
	    	}
	    	
	    	for(int j=0;j<rst.length;j++) {
	    		System.out.println(rst[j]);
	    	}
	   
	    	
	    }
	
	    
	    public static String longestPalindrome(String s) {
	        if (s == null || s.length() < 1) return "";
	        int start = 0, end = 0;
	        for (int i = 0; i < s.length(); i++) {
	            int len1 = expandAroundCenter(s, i, i);
	            int len2 = expandAroundCenter(s, i, i + 1);
	            int len = Math.max(len1, len2);
	            
	            if (len > end - start) {
	                start = i - (len - 1) / 2;
	                end = i + len / 2;
	            }
	        }
	        return s.substring(start, end + 1);
	    }

	    private  static int expandAroundCenter(String s, int left, int right) {
	        int L = left, R = right;
	        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        	System.out.println(s.charAt(L));
	            L--;
	            R++;
	            
	        }
	        return R - L - 1;
	    }
	    
	

	        public static double myPow(double x, int n) {
	            long N = n;
	            if (N < 0) {
	                x = 1 / x;
	                N = -N;
	            }
	            return fastPow(x, N);
	        }

	        private static double fastPow(double x, long n) {
	            if (n == 0) {
	                return 1.0;
	            }
	            double half = fastPow(x, n / 2);
	            if (n % 2 == 0) {
	                return half * half;
	            } else {
	                return half * half * x;
	            }
	        }
	 
	    
	        public static double myPow1(double x, int n) {
	            long N = n;
	            if (N < 0) {
	                x = 1 / x;
	                N = -N;
	            }
	            double answer = 1;
	            double currentProduct = x;
	            for (long i = N; i > 0; i /= 2) {
	                if (i % 2 == 1) {
	                    answer = answer * currentProduct;
	                }
	                currentProduct *= currentProduct;
	            }
	            return answer;
	        }

	        
	        
	        public static String convert(String s, int numRows) {

	            if (numRows == 1) return s;

	            List<StringBuilder> rows = new ArrayList<>();
	            for (int i = 0; i < Math.min(numRows, s.length()); i++)
	                rows.add(new StringBuilder());

	            int curRow = 0;
	            boolean goingDown = false;

	            for (char c : s.toCharArray()) {
	                rows.get(curRow).append(c);
	                System.out.println("c,curRow"+c+","+curRow);
	                if (curRow == 0 || curRow == numRows - 1) 
	                	goingDown = !goingDown;
	                curRow += goingDown ? 1 : -1;
	               // System.out.println("c,curRow:,goingDown:"+c+","+curRow+","+goingDown);
	            }

	            StringBuilder ret = new StringBuilder();
	            for (StringBuilder row : rows) {
	            	ret.append(row);
	            	System.out.println(row);
	            }
	            return ret.toString();
	        }

	        public static int reverse(int x) {
	          int rev = 0;
	            while (x != 0) {
	                int pop = x % 10;
	                x /= 10;
	                if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
	                if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
	                rev = rev * 10 + pop;
	            }
	            return rev;
	        }

	        public static int maxArea(int[] height) {
	    		int area = 0;
	    		int ai=0;
	    		int aj=0;
	    		for (int i = 0; i < height.length - 1; i++) {
	    			int tempArea = 0;
	    			for (int j = i + 1; j < height.length; j++) {
	    				int s = (j - i) * Math.min(height[i], height[j]);
	    				tempArea = Math.max(tempArea, s);
	    				if(s>=area) {
	    					ai=i;
	    					aj=j;
	    				}

	    			}
	    			area = Math.max(area, tempArea);
	    		}
	    		System.out.println("ai,aj:"+ai+","+aj);
	    		return area;
	    	}

	        public static String intToRoman(int num) {
	        	  String[] M = new String[] { "", "M", "MM", "MMM" };
	        	  String[] C = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
	        	  String[] X = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
	        	  String[] I = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
	        	  return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
	        	 }

	        public  static int romanToInt(String s) {
	        	  Map<Character, Integer> map = new HashMap();
	        	  map.put('I', 1);
	        	  map.put('V', 5);
	        	  map.put('X', 10);
	        	  map.put('L', 50);
	        	  map.put('C', 100);
	        	  map.put('D', 500);
	        	  map.put('M', 1000);

	        	  int result = 0;
	        	  for (int i = 0; i < s.length(); i++) {
	        	   if (i > 0 && map.get(s.charAt(i)) > map.get(s.charAt(i - 1))) {
	        	    result += map.get(s.charAt(i)) - 2 * map.get(s.charAt(i - 1));
	        	   } else {
	        	    result += map.get(s.charAt(i));
	        	   }
	        	  }
	        	  return result;
	        	 }

	        
	        public static void mergeSort(int[] arr, int left, int right, int[] temp) {
	            if(left < right) {
	                int mid = (left + right) / 2; //中间索引
	                //左边递归分解
	                mergeSort(arr, left, mid, temp);
	                System.out.println("left,mid:"+left+","+mid);
	                //右边递归分解
	                mergeSort(arr, mid + 1, right, temp);
	                System.out.println("mid,right:"+(mid+1)+","+right);
	            }
	        }
	        
	        public static boolean isSameTree(TreeNode p, TreeNode q) {
	  	      if (p == null || q == null) {
	  	        return p == q;
	  	      }
	  	      return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	  	    }
	  	
	        
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList();
		int len = 1;
		for (int i = 0; i < numRows; i++) {
			List<Integer> row = new ArrayList(len);
			row.add(1);
			if (i > 0) {
				List<Integer> lastRow = result.get(i - 1);
				for (int j = 1; j < len; j++) {
//					System.out.println("len,lastRow size:"+len+","+lastRow.size());
					if (j < lastRow.size()) {
						row.add(lastRow.get(j - 1) + lastRow.get(j));
					}
				}
				row.add(1);
			}
			result.add(row);
			len++;
		}
		return result;
	}
	      
	public static List<Integer> getRow(int rowIndex) {
		if (rowIndex < 0) {
			return new ArrayList();
		}
		List<List<Integer>> result = new ArrayList();
		List<Integer> row = new ArrayList();
		row.add(1);
		result.add(row);
		for (int i = 1; i <= rowIndex; i++) {
			List<Integer> newRow = new ArrayList();
			newRow.add(1);
			List<Integer> lastRow = result.get(i - 1);
			for (int j = 1; j < lastRow.size(); j++) {
				newRow.add(lastRow.get(j - 1) + lastRow.get(j));
			}
			newRow.add(1);
			result.add(newRow);
		}
		return result.get(result.size() - 1);
	}

	        
	
	public static List<Integer> getRow2(int rowIndex) {
//		List<List<Integer>> result = new ArrayList();
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < rowIndex + 1; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
//            result.add(new ArrayList<>(row));
        }
//        System.out.println(result);
        return row;
    }

	public static int trailingZeroes(int n) {
	      int result = 0;
	      while (n > 4) {
	        n /= 5;
	        result += n;
	      }
	      return result;
	    }
	        
	public static void rotateNaive(int[] nums, int k) {
        if (k == 0 || k == nums.length) {
            return;
        }
        if (k > nums.length) {
            k -= nums.length;
        }
        List<Integer> tmp = new ArrayList();
        int i = 0;
        if (nums.length - k >= 0) {
            i = nums.length - k;
            for (; i < nums.length; i++) {
                tmp.add(nums[i]);
            }
        } else {
            i = nums.length - 1;
            for (; i >= 0; i--) {
                tmp.add(nums[i]);
            }
        }
        
        
        for (i = 0; i < nums.length - k; i++) {
            tmp.add(nums[i]);
        }
        for (i = 0; i < tmp.size(); i++) {
            nums[i] = tmp.get(i);
            System.out.println(nums[i]);
        }
        
    }

	public static void rotate(int[] nums, int k) {
        int len = nums.length;
      
        int[] tmp = new int[len];
        for (int i = 0; i < len; i++) {
            tmp[(i + k) % len] = nums[i];

        }
    
        for (int i = 0; i < len; i++) {
            nums[i] = tmp[i];
            System.out.println("--"+nums[i]);
        }
        
      
    }

	public static  int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
          res += n & 1;//get the most right bit each time
          n >>>= 1;//do UN-signed right shift by 1 each time
          if (i < 31) {
            res <<=1;//shift this number to the left by 1 each time, so that eventually, this number is reversed
          }
        }
        return res;
      }
	
	public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

	
	//pretty straightforward, use a hashmap,
	//key is the number itself, value is the last index that this value appeared in the array
    //we can keep updating the value as we move forward, 
	//since if the current index minus the last index cannot be smaller than k, then
    //the later indices won't even do either. 
	//So, we only need to keep one index in the value of the HashMap. Cheers!
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
	
    class MyStack {

        Queue<Integer> q = new LinkedList();

        // Push element x onto stack.
        public void push(int x) {
            q.offer(x);
            if(x==9) {
            	while(!q.isEmpty()) {
            		System.out.println("ss---:"+q.peek());
            		q.poll();
            	}
            	
            }
            for (int i = 1; i < q.size(); i++) {
            	Integer rindex = q.remove();
            	System.out.println("---"+rindex+","+top());
                q.offer(rindex);
            }
            System.out.println("++++++++++++++++");
        }

        // Removes the element on top of the stack.
        public void pop() {
            q.poll();
        }

        // Get the top element.
        public int top() {
            return q.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q.isEmpty();
        }
    }
    
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) {
                q.offer(curr.left);
            }
            if (curr.right != null) {
                q.offer(curr.right);
            }
        }
        return root;
    }
    
    
    //with my original idea in the bottom, just dive a little bit deeper, you can realize that another
    //that is power of four is that its only single one bit must appear on the odd position, and power of two won't meet this requirement
    //decimal number 8 has binary format: 0000-0000-0000-0000-0000-0000-0000-1000
    //decimal number 16 has binary format: 0000-0000-0000-0000-0000-0000-0001-0000
    //hex number 0x55555555 has binary format: 1010-1010-1010-1010-1010-1010-1010-1010
    //thus, doing AND with 0x55555 will check if the only one bit is located on the odd position, thus ruling out those that are power of 2 but not power of 4
    public boolean isPowerOfFour_bit_manipulation(int num) {
        return (num > 0 && 1073741824 % num == 0 && (num & 0x55555555) != 0);
    }

    public boolean isPowerOfFour_base_conversion(int num) {
        //^ means to match the beginning of a line
        //$ means to match the end of a line
        //* means zero or more of the preceding character
        return Integer.toString(num, 4).matches("^10*$");
    }

    //a regular loop method to make it AC'ed
    public boolean isPowerOfFour(int num) {
        if (num < 4 && num != 1) {
            return false;
        }
        while (num != 1) {
            if (num % 4 != 0) {
                return false;
            }
            num /= 4;
        }
        return true;
    }

    //simply using the max number possible that is power of 4 won't work for this case, because, that number is a power of 2, but might
    //not be a power of 4, e.g. number 8
    public boolean isPowerOfFour_not_accepted(int num) {
        return (num > 3 && 1073741824 % num == 0);
    }

    
    public static String reverseString(String s) {
    	int i=0;
    	int j=s.length()-1;
    	char[] chars=s.toCharArray();
    	while(i<j) {
    		char temp = chars[i];
    		chars[i++]=chars[j];
    		chars[j--]=temp;
    	}
    	return new String(chars);
    }
	
    public String reverseVowels(String s) {
		StringBuilder sb = new StringBuilder(s);
		Set<Character> vowels = new HashSet();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		vowels.add('A');
		vowels.add('E');
		vowels.add('I');
		vowels.add('O');
		vowels.add('U');
		// use two pointers approach would be the fastest
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			char left = s.charAt(i);
			char right = s.charAt(j);
			while (i < j && !vowels.contains(left)) {
				i++;
				left = s.charAt(i);
			}
			while (i < j && !vowels.contains(right)) {
				j--;
				right = s.charAt(j);
			}
			char temp = left;
			sb.setCharAt(i, right);
			sb.setCharAt(j, temp);
			i++;
			j--;
		}
		return sb.toString();
	}
    
    
    public static int getSum(int a,int b) {
    	if(b==0) {
    		return a;
    	}
    	int sum=a^b;
    	int carry = (a&b)<<1;
//    	System.out.println("a,b:"+Integer.toBinaryString(a)+","+Integer.toBinaryString(b));
//    	System.out.println("a^b,carry:"+Integer.toBinaryString(a^b)+","+Integer.toBinaryString(a&b)+","+Integer.toBinaryString(carry));
    	return getSum(sum,carry);
    }
    
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private static int pathSumFrom(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (root.val == sum ? 1 : 0) + pathSumFrom(root.left, sum - root.val) + pathSumFrom(root.right, sum - root.val);
    }
    
    
    
    public static int numberOfBoomerangs(int[][] points) {
        int result = 0;
        if (points == null || points.length == 0 || points[0].length == 0) {
            return result;
        }
        int totalPts = points.length;
        Map<Long, Integer> map = new HashMap();
        for (int i = 0; i < totalPts; i++) {
            for (int j = 0; j < totalPts; j++) {
                if (i == j) {
                    continue;
                }
                long d = calcDistance(points[i], points[j]);
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            for (int val : map.values()) {
                result += val * (val - 1);
            }
            map.clear();
        }
        return result;
    }

    private static  long calcDistance(int[] p1, int[] p2) {
        long x = p2[0] - p1[0];
        long y = p2[1] - p1[1];
        return x * x + y * y;
    }

    public static void test1() {
//		int[] nums = {1,2,5,6,9,2,3};
//		int[] map = twoSum(nums,5);
//		for(int j=0;j<map.length;j++) {
//			System.out.println(map[j]);
//		}
		
//		System.out.println(lengthOfLongestSubstring1("abcdefjadck"));
//		int[] A = {3,4,5,8};
//		int[] B = {4,5,9,10};
		
// 		int[] A = {1,3,4,9,20};
 		int[] A = {1,8,6,2,5,4,8,3,7,9};
//		int[] B = {0,5,9,10};
//		System.out.println(findMedianSortedArrays(A,B));
//		mapSort(A,B);
//		System.out.println(longestPalindrome("hcabccbamd"));
//		System.out.println(myPow1(5,5));
//		System.out.println(convert("abcdefjkh",3));
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MAX_VALUE / 10);
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MIN_VALUE/10);
//		System.out.println(Short.BYTES);//2
//		System.out.println(Integer.BYTES);//4
//		System.out.println(Long.BYTES);//8
//		System.out.println(Double.BYTES);//8
//		System.out.println(Float.BYTES);//4
//        System.out.println(maxArea(A));
//        System.out.println(intToRoman(399));
//        System.out.println(romanToInt("XIII"));
//        System.out.println(Math.floor(5.3));
//        System.out.println(Math.ceil(5.3));
//        System.out.println(Math.rint(5.3));
//        int[] temp = new int[] {};
//        mergeSort(A, 0, A.length-1, temp);
//        
//        TreeNode p = new TreeNode();
//		TreeNode p1 = new TreeNode();
//		TreeNode p2 = new TreeNode();
//	
//		p.left = p1;
//		p.right = p2;
//		p.val=5;
//		p1.val=3;
//	    p2.val=6;
		
//		TreeNode q = new TreeNode();
//		TreeNode q1 = new TreeNode();
//		TreeNode q2 = new TreeNode();
//		q.left=q1;
//		q.right=q2;
//		q.val=4;
//		q1.val=3;
//		q2.val=6;
//		
//		System.out.println(isSameTree(p,q));
// 		System.out.println(getRow2(5));
//
// 		Set<String> set = new HashSet<String>();
// 		Map<String,Integer>  map= new HashMap<String,Integer>();
// 	
// 		System.out.println(trailingZeroes(40));
// 		int [] nums = {4,5,6,7};
// 		rotateNaive(nums,10);
// 		int [] nums2 = {4,5,6,7};
// 		rotate(nums2,10);
//System.out.println(Integer.toBinaryString(-60));
//System.out.println(Integer.toBinaryString(-60>>2));
//System.out.println(Integer.toBinaryString(-60>>>2));
// 		System.out.println(reverseBits(60));
// 		ListNode lp1= new ListNode(4);
// 		ListNode lp2= new ListNode(6);
// 		lp1.next=lp2;
// 		ListNode lp3= new ListNode(3);
// 		lp2.next=lp3;
// 		ListNode lp4= new ListNode(1);
// 		lp3.next=lp4;
// 		lp4.next  = null;
// 		ListNode next = reverseList(lp1);
// 		
// 		while(next!=null) {
// 			System.out.println(next.val);
// 			next = next.next;
// 		}
 		/*System.out.println(containsNearbyDuplicate(A,2));
 		Test test= new Test();
 		MyStack stack = test.new MyStack();
 		stack.push(4);
 		stack.push(5);
 		stack.push(8);
 		stack.push(2);
 		stack.push(9);
 		stack.push(80);
 		System.out.println("=========");
 		while (!stack.empty()) {
 			System.out.println(stack.top());
 			stack.pop();
 		}
 		Stack<Integer> st = new Stack<Integer>();
 		st.add(4);
 		st.add(5);
 		st.add(8);
 		st.add(2);
 		st.add(9);
 		st.add(80);
 		while (!st.empty()) {
 			System.out.println(st.peek());
 			st.pop();
 		}
 		invertTree(p);*/
//	   System.out.println(reverseString("hello this"));
//	    int a=5;
//	    int b=6;
//	    a=a^b;
//	    b=a^b;
//	    a=a^b;
//	    System.out.println(a^b^b);
//	    System.out.println(Integer.toString(0xAAAA, 2));
//	    System.out.println(Integer.toString(0x5555, 2));
//	    System.out.println(getSum(-8,-9));
//	    int array1[] = {2, 5, 8, 2, 5, 8, 6, 7};
//          int a=0;
//          for(int i=0;i<array1.length;i++) {
//        	     a^=array1[i];
//          }
//          System.out.println(a);
//          System.out.println(a&~(a-1));      
 	  int[][] points = new int[][] { { 3, 6 }, { 7, 5 }, { 3, 5 }, { 6, 2 }, { 9, 1 }, { 2, 7 },
            { 0, 9 }, { 0, 6 }, { 2, 6 }, };
    System.out.println(numberOfBoomerangs(points));
    String[] strArray = new String[] { "z", "a", "C" };
    Arrays.sort(strArray, Collections.reverseOrder());
    
    int []arr = {10,20,30,45,40,50,60};
    System.out.println(Arrays.binarySearch(arr, 0,2,40));
     List<String> list = new ArrayList<String>();
     List<String> list1 = new ArrayList<String>();
     list.add("a");
     list.add("b");
     list.add("4");
     list.add("5");
     list.add("b");
     list1.add("a");
     list1.add("4");
     list.removeAll(list1);
     System.out.println(Arrays.toString(list.toArray()));
     Vector<String> vList= new Vector<String>();
	}
    
    public  static void whenFilterListWithCombinedPredicatesUsingOr_thenSuccess(){
    	List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        List<Predicate<String>> allPredicates = new ArrayList<Predicate<String>>();
        allPredicates.add(str -> str.startsWith("A"));
        allPredicates.add(str -> str.contains("d"));        
        allPredicates.add(str -> str.length() > 4);
         
        List<String> result = names.stream()
          .filter(allPredicates.stream().reduce(x->true, Predicate::and))
          .collect(Collectors.toList());
         
//        assertEquals(1, result.size());
//        assertThat(result, contains("Alexander"));
         
        System.out.println(result.toString());
//        assertEquals(2, result.size());
//        assertThat(result, contains("John","Tom"));
    }
    
    public static boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i;
                if (i != num / i) {
                    sum += num / i;
                }
            }
        }
      
        return sum == num;
    }
    
  //The gotcha point of this question is:
    //1. if a and b are identical, then there will be no common subsequence, return -1
    //2. else if a and b are of equal length, then any one of them will be a subsequence of the other string
    //3. else if a and b are of different length, then the longer one is a required subsequence because the longer string cannot be a subsequence of the shorter one

    //Or in other words, when a.length() != b.length(), no subsequence of b will be equal to a, so return Math.max(a.length(), b.length())
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
    
    
    public int getMinimumDifference(TreeNode root) {
        TreeSet<Integer> treeset = new TreeSet<>();
        treeset.add(root.val);
        dfs(root, treeset);
        int diff = Integer.MAX_VALUE;
        Iterator<Integer> iterator = treeset.iterator();
        int prev = iterator.next();
        while (iterator.hasNext()) {
            int current = iterator.next();
            diff = Math.min(diff, Math.abs(current - prev));
            prev = current;
        }
        return diff;
    }

    private void dfs(TreeNode root, TreeSet<Integer> treeset) {
        if (root.left != null) {
            treeset.add(root.left.val);
            dfs(root.left, treeset);
        }
        if (root.right != null) {
            treeset.add(root.right.val);
            dfs(root.right, treeset);
        }
    }
    
		public static void main(String[] args) {
			int a = 1;
			TreeSet<Integer> treeset = new TreeSet<>();
			treeset.add(7);
			treeset.add(6);
			treeset.add(5);
			treeset.add(9);
			treeset.add(3);
			treeset.add(10);
			Iterator<Integer> iterator = treeset.iterator();
//		        int prev = iterator.next();
//		        System.out.println("prev:"+prev);
		        
		        while(iterator.hasNext()) {
		        	System.out.println(iterator.next());
		        }
			
			
          }


}


	
	