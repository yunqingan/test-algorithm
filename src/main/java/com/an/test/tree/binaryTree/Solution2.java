package com.an.test.tree.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    

    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        int guess=0;
        while (left+1 < right) {
            int mid = left + (right - left) / 2;
//            System.out.println("left,mid,right:"+left+","+mid+","+right);
            int g = guess(mid);
            if (g == 0) {
                guess= mid;
                break;
            } else if (g > 0) {
                left = mid;
            } else {
                right = mid;
            }
        }
//        if (guess(left) == 0) {
//            return left;
//        }
//        return right;
        return guess;
    }

    
    private int guess(int num) {
        if (num > 6) {
            return -1;
        } else if (num < 6) {
            return 1;
        } else {
            return 0;
        }
    }

    public  static boolean canConstruct(String ransomNote, String magazine) {
        char[] mchars = magazine.toCharArray();
        int[] mcnt = new int[256];
        for (int i = 0; i < mchars.length; i++) {
            mcnt[mchars[i] - 'a']++;
        }

        char[] rchars = ransomNote.toCharArray();
        for (int i = 0; i < rchars.length; i++) {
            if (mcnt[rchars[i] - 'a'] <= 0) {
                return false;
            }
            mcnt[rchars[i] - 'a']--;
        }
        return true;
    }


    public  static int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String s = Integer.toString(start);
        System.out.println("n,s:"+n+","+s);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
    
    public  static List<String> readBinaryWatch(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 60 + m) == num) {
                    times.add(String.format("%d:%02d", h, m));//%02 means to pad this two-digit decimal number on the left with zeroes
                }
            }
        }
        return times;
    }
    
    

    public static String toHex(int num) {
        char[] hexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String result = "";
        while (num != 0) {
            result = hexChars[(num & 15)] + result;
            num >>>= 4;
        }
        return result.isEmpty() ? "0" : result;
    }
    
    public static void main(String... strings) {
//        Solution test = new Solution();
//    	Runtime.getRuntime().gc();
//        System.gc();
//        for(int i=7;i<Integer.MAX_VALUE;i++) {
////        	if(test.guessNumber(i)!=6) {
////        		System.out.println(i);
////        		break;
////        	}
//        	System.out.println("i,rs:"+i+","+test.guessNumber(i));
//        }
        
    	System.out.println(canConstruct("abcd","mdab"));
    	System.out.println(findNthDigit(192));
    	System.out.println(Integer.bitCount(254));
    	System.out.println(toHex(66));
    }

}