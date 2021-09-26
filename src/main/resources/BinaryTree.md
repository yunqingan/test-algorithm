# **java  树的结构**

资源：1.算法  http://jeffe.cs.illinois.edu/teaching/algorithms/#book

​           2. 算法刷题分类 https://www.educative.io/courses/grokking-the-coding-interview?aff=K7qB

### 1.普通二叉树

   二叉树是使用链表的形式,其优点是便于插入和删除,但是查找速度很慢,占用空间也很大.所以现在用数组的形式来构建二叉树,节点存在数组中,而不是由引用相连,节点在数组中的位置对应它在树中的位置,下标为0 的节点为根节点,下标为1是根的左节点,2为根节点的右节点,依次类推,从左到右的顺序存储树的每一层,包括空节点.如下图:


​                          ![img](https://img-blog.csdn.net/20160708110724036?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)                                                                                                    

找节点的子节点和父节点可以利用简单的算术计算它们在数组中的索引值

设某个节点索引值为index,则节点的**左子节**点索引为:

2*index+1

**右子节**点索引为:

2*index+2

**父节点**索引为:

(index-1)/2

**总结:**大多数情况下用数组表示数不是很有效率,除非是完全二叉树.但是普通的二叉树,特别是有很多空节点的.会有很多空洞,浪费存储空间.用数组表示树,删除节点是很费时费力的.所以用数组表示树适合用于 **完全二叉树**查找,和插入.

2、性质

（1）若二叉树的层次从0开始，则在二叉树的第i层至多有2^i个结点(i>=0)；

（2）高度为k的二叉树最多有2^(k+1) - 1个结点(k>=-1)。 (空树的高度为-1)；

（3）对任何一棵二叉树，如果其叶子结点(度为0)数为m, 度为2的结点数为n, 则m = n + 1。

### 2.满二叉树



### 3.完全二叉树



### 4.线索二叉树

### 5.哈夫曼树

### 6.二叉搜索树（排序树）

二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树： 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。

### 7.平衡二叉树

平衡二叉树（Self-balancing binary search tree）又被称为AVL树（有别于AVL算法），且具有以下性质：**它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树**。平衡二叉树的常用实现方法有[红黑树](https://baike.baidu.com/item/红黑树)、[AVL](https://baike.baidu.com/item/AVL/7543015)、[替罪羊树](https://baike.baidu.com/item/替罪羊树)、[Treap](https://baike.baidu.com/item/Treap)、[伸展树](https://baike.baidu.com/item/伸展树)等。 最小二叉平衡树的节点的公式如下 F(n)=F(n-1)+F(n-2)+1 这个类似于一个递归的[数列](https://baike.baidu.com/item/数列)，可以参考Fibonacci(斐波那契)数列，1是根节点，F(n-1)是左子树的节点数量，F(n-2)是右子树的节点数量。

### 8.AVL平衡二叉树



### 9.红黑树



### 10.B树



### 11.B+树



### 12.堆

堆（英语：heap)是计算机科学中一类特殊的数据结构的统称。

堆通常是一个可以被看做一棵树的数组对象。堆总是满足下列性质：
1.堆中某个节点的值总是不大于或不小于其父节点的值；
2.堆总是一棵完全二叉树。

常见的堆有二叉堆、斐波那契堆等。

堆的定义：n个元素的序列{k1,k2,ki,…,kn}当且仅当满足下关系时，称之为堆。
(ki <= k2i,ki <= k2i+1)或者(ki >= k2i,ki >= k2i+1), (i = 1,2,3,4…n/2)

堆是一颗完全二叉树，在这棵树中，所有父节点都满足大于等于其子节点的堆叫大根堆，所有父节点都满足小于等于其子节点的堆叫小根堆。
堆虽然是一颗树，但是通常存放在一个数组中，父节点和孩子节点的父子关系通过数组下标来确定。如下图的小根堆及存储它的数组：
在这里插入图片描述![在这里插入图片描述](https://img-blog.csdnimg.cn/20181113191641624.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTM3MjgwMjE=,size_16,color_FFFFFF,t_70)

在这里插入图片描述

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181113191730311.jpg)

从图中，我们可以很容易总结出，通过一个节点在数组中的索引怎么计算出它的父节点及左右孩子节点的索引？下面直接给出对应的Java

```java
public int left(int i) {
        return (i + 1) * 2 - 1;
   }

public int right(int i) {
    return (i + 1) * 2;
}
public int parent(int i) {
    // i为根结点
    if (i == 0) {
        return -1;
    }
    return (i - 1) / 2;
}

```
















