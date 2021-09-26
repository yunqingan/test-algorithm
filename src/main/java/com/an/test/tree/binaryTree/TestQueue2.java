package com.an.test.tree.binaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.an.test.TreeNode;

public class TestQueue2 {
    public static void main(String[] args) {
//        ArrayQueue2 arrayQueue2 = new ArrayQueue2();
//        arrayQueue2.iniQueue(3);
//   
//        arrayQueue2.addQueue(1);
//        arrayQueue2.addQueue(2);
////        arrayQueue2.addQueue(3);
//        System.out.println(arrayQueue2.getSize());
    
    	System.out.println(judgeSquareSum(2525));
    }
    
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr != null) {
                    sum += curr.val;
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }
    
    public static  boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        int left = 0;
        int right = (int) (Math.sqrt(c));
        while (left <= right) {
            int curr = left * left + right * right;
            if (curr > c) {
                right--;
            } else if (curr < c) {
                left++;
            } else {
            	System.out.println("left,right:"+left+","+right);
                return true;
            }
        }
        return false;
    }
}


/**
 * S5如何判断循环队列为空：
1）如果front与rear的值相等，那么该循环队列为空队列。

S6如何判断循环队列已满：
1）通常使用两种方式
1.多增加一个标识参数。
2.如果 (rear + 1) % 数组长度 == front 那么，已满。

   （1）增加一个标识flag ，初始的时置为false，每当有元素入队时让flag = true，每当有元素出队时，让flag = false，在触发rear = front 的上一个操作决定了是空还是满。这样在判断空的时候 ，要判断 front == rear 和 flag 为假要同时成立，即( front == rear ) && !front 整体为真时，队列空。( front == rear ) && front 时 队列满

    （2）增加一个引用计数count；

    （3）少用一个元素空间，约定以“队列头指针front在队尾指针rear的下一个位置上”作为队列“满”状态的标志。即：
  队空时： front=rear
  队满时： (rear+1)%maxsize=front
 * @Description  
 * @author yunqing
 * @data   2021年5月31日下午1:24:52
 */
class ArrayQueue2 {
    public int[] arr;
    public int rear;
    public int front;
    public int maxSize;

    //初始化队列
    public void iniQueue(int length) {
        arr = new int[length];
        front = 0;
        rear = 0;
        maxSize = length;
    }

    public boolean isEmpty() {
        return (rear + maxSize - front) % maxSize == 0;//循环队列的关键是队列中元素的绝对位置并不固定,需要通过简单运算,确定元素位置.
    }

    public boolean isFull() {
        return getSize() == front;
    }

    public void addQueue(int element) {
        if (isFull()) {
            System.out.println("满了,添加不了");
            return;
        }
        arr[rear] = element;
        rear = (rear + 1) % maxSize;//通过取余防止数组越界
        System.out.println("----"+rear);
    }

    public int getQueue() {
        if (isEmpty()) {
            System.out.println("空的,取不了");
           //这里有一个问题 返回值怎么设置
        }
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }


    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空的,取不了");
            return;
        }
        for (int i = front; i < front + getSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }//这里i的实际取值也是值得注意的问题
    }

    public  static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int product = 1;
        if (nums.length >= 3) {
            for (int i = nums.length - 1; i >= nums.length - 3; i--) {
                product *= nums[i];
            }
            int anotherProduct = nums[0] * nums[1] * nums[nums.length - 1];
            product = Math.max(product, anotherProduct);
        } else {
            for (int i = 0; i < nums.length; i++) {
                product *= nums[i];
            }
        }
        return product;
    }
    
    
    

    
    
    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }
    
    
    
    
}
