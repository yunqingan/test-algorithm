**本篇主要内容如下：**





![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpoTldBcndDbHZYRXljaE0xU0x1MTZoYWFoWkp1dExsd21tTkxkbElDUzBtT0NzdDl6MVNaWXcvNjQw?x-oss-process=image/format,png)

  本篇主要内容 

**帮你总结好的阻塞队列：**

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkprOVNZSWFTOWdNRzN2U0plVFZhWkowclJRcDRkMWRiZ1U1WWxyUVhzTTJIYTV6MDJibllBWncvNjQw?x-oss-process=image/format,png)

  18种Queue总结  


## 一、Queue自我介绍 

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpad2ljWWhVYWo0MGhNb2NoSW54ZjAwM1NtTUd0TFFiNWFmYU9aMGp1aWN5MlBUNUVTaWNwWUt3N3cvNjQw?x-oss-process=image/format,png)

  队列原理图 

### 1.1 Queue自我介绍

hi，大家好，我的英文名叫`Queue`，中文名叫`队列`，无论现实生活中还是计算机的世界中，我都是一个很重要的角色哦~

我是一种`数据结构`，大家可以把我想象成一个数组，元素从我的一头进入、从另外一头出去，称为FIFO原则（先进先出原则）。

我还有两个亲兄弟：`List`（列表）、`Set`（集），他们都是`Collection`的儿子，我还有一个远房亲戚：`Map`（映射）。他们都是`java.util`包这个大家庭的成员哦~

### 1.2 现实生活中的场景

- 海底捞排号等位（先排号的优先进餐厅）
- 邮政员寄送信件（信箱是队列）

### 1.3 计算机世界中的场景

- 消息队列 RabbitMQ
- UDP协议（接收端将消息存放在队列中，从队列中读取数据）

## 二、高屋建瓴，纵览全局 

**18种队列分为三大类：** 接口、抽象类、普通类。

弄清楚下面的继承实现关系对后面理解18种队列有很大帮助。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkppYnd4R3Nna0R6ZGljRTcxTkd0aWFZVHVyUDR1cTJUdnZPeXROREVZenh2cmVKMlVLVUk4cThTVUEvNjQw?x-oss-process=image/format,png)

  18个Queue的继承实现关系图 

- `Queue`接口**继承** `Collection`接口，`Collection`接口**继承**  `Iterable`接口
- `BlockingQueue`接口、`Deque`接口 **继承** `Queue`接口
- `AbstractQueue`抽象类**实现** `Queue`接口
- `BlockingDeque`接口、`TransferQueue`接口**继承** `BlockingQueue`接口
- `BlockingDeque`接口继承`Deque`接口
- `LinkedBlockingDeque`类**实现** `BlockingDeque`接口
- `LinkedTransferQueue`类接口**实现** `TransferQueue`接口
- `LinkedList`类、`ArrayDeque`类、`ConcurrentLinkedDeque`类**实现** 了`Deque`接口
- `ArrayBlockingQueue`类、`LinkendBlockingQueue`类、`LinkedBlockingDeque`类、`LinkedTransferQueue`类、`SynchronousQueue`类、`PriorityBlockQueue`类、`DelayQueue类`**继承** 了`AbstractQueue`抽象类和**实现**了BlockingQueue接口
- `PriorityQueue`类和`ConcurrentLinkedQueue`类**继承** 了`AbstractQueue`抽象类

**注意：**

- Deque：全称Double-Ended queue，表示双端队列。
- 类实现接口，用implements
- 接口继承接口，用 extends
- 类继承类，用extends

## 三、万物归宗Queue接口 

### 2.1 深入理解Queue接口的本质

- Queue接口是一种Collection，被设计用于处理之前临时保存在某处的元素。
- 除了基本的Collection操作之外，队列还提供了额外的插入、提取和检查操作。每一种操作都有两种形式：如果操作失败，则抛出一个异常；如果操作失败，则返回一个特殊值（null或false，取决于是什么操作）。
- 队列通常是以FIFO（先进先出）的方式排序元素，但是这不是必须的。
- 只有优先级队列可以根据提供的比较器对元素进行排序或者是采用正常的排序。无论怎么排序，队列的头将通过调用remove()或poll()方法进行移除。在FIFO队列种，所有新的元素被插入到队尾。其他种类的队列可能使用不同的布局来存放元素。
- 每个Queue必须指定排序属性。

### 2.2 Queue接口的核心方法

总共有3组方法，每一组方法对应两个方法。如下图所示：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpMaFpCWXFsMnBiSm15YTR3cWZpY3g4T1BpYkRVUFl3UUVpY2FkaWFKV1Bob3o4ZmN6a2tIOEJsbkNRLzY0MA?x-oss-process=image/format,png)

  Queue的核心方法 



| 动作    | 抛异常    | 返回特殊值 |
| ------- | --------- | ---------- |
| Insert  | add(e)    | offer(e)   |
| Remove  | remove()  | poll       |
| Examine | element() | peek()     |



- （1）比如`添加（Insert）`元素的动作，会有两种方式：`add(e)`和`offer(e)`。如果调用add(e)方法时，添加失败，则会`抛异常`，而如果调用的是offer(e)方法失败时，则会`返回false`。offer方法用于异常是正常的情况下使用，比如在有界队列中，优先使用offer方法。假如队列满了，不能添加元素，offer方法返回false，这样我们就知道是队列满了，而不是去handle运行时抛出的异常。
- （2）同理，移除（Remove）元素的动作，队列为空时，remove方法抛异常，而poll返回null。如果移除头部的元素成功，则返回移除的元素。
- （3）同理，检测（Examine）元素的动作，返回头部元素（最开始加入的元素），但不删除元素， 如果队列为空，则element()方法抛异常，而peek()返回false。
- （4）Queue接口没有定义阻塞队列的方法，这些方法在BlockQueue接口中定义了。
- （5）Queue实现类通常不允许插入null元素，尽管一些实现类比如LinkedList不禁止插入null，但是还是不建议插入null，因为null也被用在poll方法的特殊返回值，以说明队列不包含元素。

## 四、双端可用Deque接口 

### 4.1 深入理解Deque接口的原理

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko4TUxROXdpYXVsdjNiWWliM3pPTHV2bmZiRm5rZ3JGQjhma3JpYUhxRlROMm50dVdPRkxXbHNXdncvNjQw?x-oss-process=image/format,png)

  双端队列Deque 

**（1）Deque概念：** 支持两端元素插入和移除的线性集合。名称`deque`是双端队列的缩写，通常发音为`deck`。大多数实现Deque的类，对它们包含的元素的数量没有固定的限制的，支持有界和无界。

**（2）Deque方法说明：**

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko3Vkk4aWJXUHNHZlpwc1VudVNGYzJoTk5JN0IzenpkakxadGJSTUpWbEhLY1Z2NXZMbDlGQmxRLzY0MA?x-oss-process=image/format,png)

  Deque方法 

**说明：**

- 该列表包含包含访问deque两端元素的方法，提供了插入，移除和检查元素的方法。
- 这些方法种的每一种都存在两种形式：如果操作失败，则会抛出异常，另一种方法返回一个特殊值（null或false，取决于具体操作）。
- 插入操作的后一种形式专门设计用于容量限制的Deque实现，大多数实现中，插入操作不能失败，所以可以用插入操作的后一种形式。
- Deque接口扩展了Queue接口，当使用deque作为队列时，作为FIFO。元素将添加到deque的末尾，并从头开始删除。
- 作为FIFO时等价于Queue的方法如下表所示：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkp4WDlSMHlhZnh2WHd5RUJJQjRycmliMmphZHBVZWZFSGljcWtpYWF0NVU5enJBNWN5WGliRzhNQWxnLzY0MA?x-oss-process=image/format,png)

比如Queue的add方法和Deque的addLast方法等价。

- Deque也可以用作LIFO（后进先出）栈，这个接口优于传统的Stack类。当作为栈使用时，元素被push到deque队列的头，而pop也是从队列的头pop出来。

- Stack（栈）的方法正好等同于Deque的如下方法：

  

注意：peek方法不论是作为栈还是队列，都是从队列的检测队列的头，返回最先加入的元素。比如第一次put 100，第二次put 200，则peek返回的是100。如下图所示：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkozTEY4OVRpYnNoSnZ2SVA4b3hCMWRsYzBpYzF0SnpraWE2R0dpYlo4NzZwYmUxcmtoNE1pY0h4YmVDdy82NDA?x-oss-process=image/format,png)

  示例代码 

### 4.1 哪些类实现了Deque接口

- LinkedList类
- ArrayDeque类
- ConcurrentLinkedDeque类
- LinkedBlockingDeque类

### 4.2 哪些类继承了Deque接口

- BlockingDeque接口

## 五、队列骨架AbstractQueue抽象类 

### 5.1  深入理解AbstractQueue抽象类

AbstractQueue是一个抽象类，继承了Queue接口，提供了一些Queue操作的骨架实现。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkoyRzlrQ2lhMWU3a3lad2h6ZDB6dDFUT2lhVXpXU3VRNGlhWkRzZlg0aFZJaWNpYVNKaHNVMU1qQ3Vjdy82NDA?x-oss-process=image/format,png)

  AbstractQueue的方法 

方法add、remove、element方法基于offer、poll和peek。也就是说如果不能正常操作，则抛出异常。我们来看下AbstactQueue是怎么做到的。

- AbstractQueue的add方法

```go
public boolean add(E e) {



    if (offer(e))



        return true;



    else



        throw new IllegalStateException("Queue full");



}
```

- AbstractQueue的remove方法

```go
public E remove() {



    E x = poll();



    if (x != null)



        return x;



    else



        throw new NoSuchElementException();



}
```

- AbstractQueue的element方法

```go
public E element() {



    E x = peek();



    if (x != null)



        return x;



    else



        throw new NoSuchElementException();



}
```

注意：

- 如果继承AbstractQueue抽象类则必须保证offer方法不允许null值插入。

### 5.2 哪些类继承了AbstractQueue抽象类

- `ArrayBlockingQueue`类、`LinkendBlockingQueue`类、`LinkedBlockingDeque`类、`LinkedTransferQueue`类、`SynchronousQueue`类、`PriorityBlockQueue`类、`DelayQueue类`**继承** 了`AbstractQueue`抽象类
- `PriorityQueue`类和`ConcurrentLinkedQueue`类**继承** 了`AbstractQueue`抽象类

## 六、阻塞缓冲BlockingQueue接口 

### 6.1 宏观来看BlockingQueue（阻塞队列）

- BlockQueue满了，PUT操作被阻塞

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpxbmF3OFhBYWVSQWNBcm8zb0JzZFJKOWpKdXJUazFyZUdpYXN3bzh5Z1hFSHhMbGJ2UWpxRUN3LzY0MA?x-oss-process=image/format,png)

  阻塞队列满了的情况 

- BlockQueue为空，Take操作被阻塞

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpQTjJLMHdDUUs4SmljYlp3aWNDV1dCMVA3aWFpY1dpY2tRUXBmRG01Qm5BZE55dzFIQ0JuMmFiTWFXUS82NDA?x-oss-process=image/format,png)

  阻塞队列为空的情况 

（1）BlockingQueue（阻塞队列）也是一种队列，支持阻塞的插入和移除方法。

（3）阻塞的插入：当队列满时，队列会阻塞插入元素的线程，直到队列不满。

（4）阻塞的移除：当队列为空，获取元素的线程会等待队列变为非空。

（5）应用场景：生产者和消费者，生产者线程向队列里添加元素，消费者线程从队列里移除元素，阻塞队列时获取和存放元素的容器。

（6）为什么要用阻塞队列：生产者生产和消费者消费的速率不一样，需要用队列来解决速率差问题，当队列满了或空的时候，则需要阻塞生产或消费动作来解决队列满或空的问题。

### 6.2 案例解析

线程A往阻塞队列（Blocking Queue）中`添加`元素，而线程B从阻塞队列中`移除`元素。

- **当阻塞队列为空的时候** （一个元素都没有），则从队列中获取元素的操作将会被阻塞。
- - 生活中的案例：去海底捞吃火锅的时候，早上8点没人来吃火锅，所以需要等客人过来。
  - 翻译成线程：现在没有元素需要添加，而且阻塞队列为空，所以线程B不需要从队列中拿元素出来，所以线程B获取元素的操作被阻塞了。
- **当阻塞队列满了的时候** （所有位置都放有元素），则从队列中添加元素的操作将会被阻塞。
- - 生活中的案例：去海底捞吃火锅的时候，人太多了，需要排号，等其他桌空出来了才能进去。
  - 翻译成线程：线程A往阻塞队列中添加元素，将队列填满了，线程B现在正在忙，无法拿出队列中的元素，所以阻塞队列没有地方再放元素了，这个时候线程A添加元素的操作就被阻塞了

### 6.3 操刀BlockingQueue接口

**BlockingQueue接口的10个核心方法：**

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpUeDZEbnZwdGZLUVdMSnZpY2dLemU1bW1KNGFoaWNPWmRWOWRGTjJlRm02WWNFQ0V6U290VlhrQS82NDA?x-oss-process=image/format,png)

  继承的方法 

**10个核心方法总结如下：**

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkozZ2NnaFJxekoxeUU2Vnh4TkxLNU9wMGpJS3BEUHdaQ1JQaWFTTFFtaWExc1c5ZTdKemFHeUp4US82NDA?x-oss-process=image/format,png)

  BlockingQueue接口的10个核心方法 

有三大类操作：插入、移除、检查。

- **插入有四种方法：** add、offer、put、offer超时版。
- - `IllegalStateException` - 队列满了
  - `ClassCastException` - 添加的元素类型不匹配
  - `NullPointerException` - 添加的元素为null
  - `IllegalArgumentException` - 添加的元素某些属性不匹配
  - add方法特别之处用于添加失败时抛出异常，共有四种异常：
  - offer方法特别之处用于添加失败时只返回false
  - put方法特别之处用于当阻塞队列满时，生产者如果往队列里put元素，则队列会一直阻塞生产者线程，直到队列可用或者响应中断退出
  - offer超时方法特别之处用于当阻塞队列满时，生产者如果往队列里面插入元素，队列会阻塞生产者线程一段时间，如果超过了指定时间，生产者线程会退出，并返回false。
- **移除有四种方法：** remove、poll、take、poll超时版
- - `NoSuchElementException` - 如果这个队列是空的
  - remove方法特别之处用于移除失败时抛出异常
  - poll方法特别之处用于移除失败时返回null
  - take方法特别之处用于当阻塞队列为空时，消费者线程如果从队列里面移除元素，则队列会一直阻塞消费者线程，直到队列不为空
  - poll超时方法特别之处用于当阻塞队列空时，消费者如果从队列里面删除元素，则队列会一直阻塞消费者线程，如果超过了指定时间，消费者线程会退出，并返回null
- **检查有两种方法：** element、peek
- - element方法用于检测头部元素的存在性，如果队列为空，则抛出异常，否则返回头部元素。
  - peek方法用于检测头部元素的存在性，如果队列为空，返回特殊值null，否则返回头部的元素。

### 6.4 BlockingQueue通过什么来阻塞插入和移除的？

- 当往队列里插入一个元素时，如果队列不可用，那么阻塞生产者主要通过LockSupport. park（this）来实现。
- park这个方法会阻塞当前线程，只有以下4种情况中的一种发生时，该方法才会返回。
- - 与park对应的unpark执行或已经执行时。“已经执行”是指unpark先执行，然后再执行park的情况。
  - 线程被中断时。
  - 等待完time参数指定的毫秒数时。
  - 异常现象发生时，这个异常现象没有任何原因。

### 6.5 哪些类继承了BlockingQueue接口？

- BlockingDeque接口 - 双端阻塞队列
- TransferQueue接口 - 传输队列

### 6.6 哪些类实现了BlockingQueue接口？

- ArrayBlockingQueue类 - 由数组构成的有界阻塞队列
- LinkedBlockingQueue类 - 由链表构成的有界阻塞队列，界限默认大小为Integer.MAX_Value（2^31-1），值非常大，相当于无界。
- LinkedBlockingDeque类 - 由链表构成的双向阻塞队列
- LinkedTransferQueue类 - 由链表构成的无界阻塞队列
- SynchronousQueue类 - 不存储元素的阻塞队列，只有一个元素进行数据传递。
- LinkedTransferQueue类 - 由链表构成的无界阻塞TransferQueue队列
- DelayQueue类 - 使用优先级队列实现的延迟无界阻塞队列

### 6.6 BlockingQueue接口继承了哪些接口

- BlockingQueue接口继承了Queue接口，可作为队列使用

## 七、双端阻塞BlockingDeque接口 

### 7.1 从原理图上理解BlockDeque

- BlockQueue满了，两端的Take操作被阻塞

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkp1MEpnMkE2OWljOTNieHhkRmpwc0xzRklndWhiSzlLZTgybXFqaWFzd3p6d0syeFhpY1ppY1ZVQVFnLzY0MA?x-oss-process=image/format,png)

  BlockingDeque满了 

- BlockQueue为空，两端的Take操作被阻塞

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpBaWFiVW9abzNIOUw2elAxNlplcTYxMzl3Mmp4SWNuVTdaZUU3YmNmdWcwVUZibzBpYUxiajhPUS82NDA?x-oss-process=image/format,png)

  BlockQueue为空 

### 7.2 BlockingDeque接口方法

是阻塞队列`BlockingQueue`和双向队列`Deque`接口的结合。有如下方法：

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpxTzYyMk4wTjlVcVYzQVh2YkoyUERZSWFDaWFoV3ExT1lpYkdhWjhuME1xZW9pY2liUFhQZDNRSHJnLzY0MA?x-oss-process=image/format,png)

  BlockingDeque接口方法 

示例：

尝试执行以下方法：

```go
LinkedBlockingDeque queue = new LinkedBlockingDeque();



queue.addFirst("test1");



queue.addFirst(300);



queue.addLast("400");
```

最后队列中的元素顺序如下：

300, test1, 400。

先添加了test1放到队列的头部，然后在头部的前面放入300，所以300在最前面，成为头部，然后将400放入队列的尾部，所以最后的结果是300, test1, 400。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko2b29uTnFPZmliTmE4WXd0ZXh6eFV6WFBnRkR0ZlZMZWhJMHlGQ3QxelFsN05vVDRNZE9UdFB3LzY0MA?x-oss-process=image/format,png)

  队列种的元素 

### 7.3 BlockDeque和BlockQueue的对等方法

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpkdlJzaWFFY3JBYkJzZ2JaVzBkcTduQjFKSmo4TWUwNzJpYzRtNzFlbUJwYXVMNklYcElwSGVzUS82NDA?x-oss-process=image/format,png)

  BlockDeque和BlockQueue的对等方法 

### 7.4 BlockingDeque的特点

- 线程安全。
- 不允许使用null元素。
- 无界和有界都可以。

### 7.5 BlockingDeque接口继承了哪些接口？

- Queue接口，具有队列的功能
- Deque接口，具有双端队列的功能
- BlockingQueue接口，可作为阻塞队列使用

### 7.6 哪些类实现了BlockDeque接口？

- LinkedBlockingDeque

## 八、使命必达TransferQueue接口 

### 8.1 Transfer怎么理解？

如果有消费者正在获取元素，则将队列中的元素传递给消费者。如果没有消费者，则等待消费者消费。我把它称作使命必达队列，必须将任务完成才能返回。

### 8.2 生活中的案例

- **针对TransferQueue的transfer方法**
- - 圆通快递员要将小明的2个快递送货到门，韵达快递员也想将小明的2个快递送货到门。小明一次只能拿一个，快递员必须等小明拿了一个后，才能继续给第二个。
- **针对TransferQueue的tryTransfer方法**
- - 圆通快递员要将小明的2个快递送货到门，韵达快递员也想将小明的2个快递送货到门。发现小明不在家，就把快递直接放到菜鸟驿站了。
- **针对TransferQueue的tryTransfer超时方法**
- - 圆通快递员要将小明的2个快递送货到门，韵达快递员也想将小明的2个快递送货到门。发现小明不在家，于是先等了5分钟，发现小明还没有回来，就把快递直接放到菜鸟驿站了。

### 8.3 TransferQueue的原理解析

- `transfer(E e)`

  原理如下图所示：

  

  ​    transfer方法的原理   

- - 原理图解释：生产者线程Producer Thread尝试将元素B传给消费者线程，如果没有消费者线程，则将元素B放到尾节点。并且生产者线程等待元素B被消费。当元素B被消费后，生产者线程返回。
  - 如果当前有消费者正在等待接收元素（消费者通过take方法或超时限制的poll方法时），transfer方法可以把生产者传入的元素立刻transfer（传输）给消费者。
  - 如果没有消费者等待接收元素，transfer方法会将元素放在队列的tail（尾）节点，并等到该元素被消费者消费了才返回。

- `tryTransfer(E e)`

- - 试探生产者传入的元素是否能直接传给消费者。
  - 如果没有消费者等待接收元素，则返回false。
  - 和transfer方法的区别是，无论消费者是否接收，方法立即返回。

- `tryTransfer(E e, long timeout, TimeUnit unit)`

- - 带有时间限制的tryTransfer方法。
  - 试图把生产者传入的元素直接传给消费者。
  - 如果没有消费者消费该元素则等待指定的时间再返回。
  - 如果超时了还没有消费元素，则返回false。
  - 如果在超时时间内消费了元素，则返回true。

- `getWaitingConsumerCount()`

- - 获取通过BlockingQueue.take()方法或超时限制poll方法等待接受元素的消费者数量。近似值。
  - 返回等待接收元素的消费者数量。

- `hasWaitingConsumer()`

- - 获取是否有通过BlockingQueue.tabke()方法或超时限制poll方法等待接受元素的消费者。
  - 返回true则表示至少有一个等待消费者。

### 8.3 TransferQueue接口继承了哪些接口？

- BlockingQueue接口，可作为阻塞队列使用
- Queue接口，可作为队列使用

### 8.4 哪些类实现了TransferQueue接口？

- LinkedTranferQueue接口

## 九、优先由你PriorityQueue类 

### 9.1 理解PriorityQueue类

- 本应该按照升序排序

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpUY3dxUmdncmY2aWFJb3NyV2N5RkVDb2tzVjBpY0xNTXVpYzJFcG93TEY0d1dTYXEwd09wRGlhdVV3LzY0MA?x-oss-process=image/format,png)

  本应该按照升序排序 

- 按照倒叙排序

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko1elczZUpXRldlNGJxaWF1ZDN5c1ZLaks2bkQ0ZTliaGFXT1NDaHAzRW44WGowN3NhTUJva21BLzY0MA?x-oss-process=image/format,png)

  按照自定义优先级排序 

- PriorityQueue是一个支持优先级的无界阻塞队列。
- 默认自然顺序升序排序。
- 可以通过构造参数Comparator来对元素进行排序。

```go
public PriorityQueue(Comparator<? super E> comparator) {



     this(DEFAULT_INITIAL_CAPACITY, comparator);



}
```

- 自定义实现comapreTo()方法来指定元素排序规则。

```go
public Comparator<? super E> comparator() {



    return comparator;



}
```

- 不允许插入null元素。
- 实现PriorityQueue接口的类，不保证线程安全，除非是PriorityBlockingQueue。
- PriorityQueue的迭代器不能保证以任何特定顺序遍历元素，如果需要有序遍历，请考虑使用`Arrays.sort(pq.toArray)`。
- 进列(`offer`、`add`)和出列（ `poll`、`remove()`）的时间复杂度O(log(n))。
- remove(Object) 和 contains(Object)的算法时间复杂度O(n)。
- peek、element、size的算法时间复杂度为O(1)。

### 9.2 PriorityQueue类继承了哪些类？

- AbstractQueue抽象类，具有队列的功能

### 9.2 PriorityQueue类实现了哪些接口？

- Queue接口，可作为队列使用。

## 十、双向链表LinkedList类 

### 10.1 LinkedList的结构

- LinkedList实现了List和Deque接口，所以是一种双链表结构，可以当作堆栈、队列、双向队列使用。
- 一个双向列表的每一个元素都有三个整数值：元素、向后的节点链接、向前的节点链接

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpLbVh2WWpKTE1zY3FpYW5HbzFMNGVzdmZ1eDBXUUpCNDlYMkcwSVdSUmF2aWFORjlkOFpnaWJSSHcvNjQw?x-oss-process=image/format,png)

  LinkedList的结构 

我们来看下节点类Node

```go
private static class Node<E> {



    E item; //元素



    Node<E> next; //向后的节点链接



    Node<E> prev; //向前的节点链接



 



    Node(Node<E> prev, E element, Node<E> next) {



        this.item = element;



        this.next = next;



        this.prev = prev;



    }



}
```

### 10.2 与ArrayList的区别

- 1.LinkedList的增加和删除效率相对较高，而查找和修改的效率相对较低。
- 2.以下情况建议使用ArrayList
- - 频繁访问列表中的一个元素。
  - 只在列表的首尾添加元素。
- 3.以下情况建议使用LinkedList
- - 频繁地在列表开头、中间、末尾添加和删除元素。
  - 需要通过循环迭代来访问列表中的元素。

### 10.3 LinkedList不是线程安全的

LinkedList不是线程安全的，所以可以使用如下方式保证线程安全。

```go
List list = Collections.synchronizedList(new LinkedList<>());
```

### 10.4 LinkedList的家庭成员关系

- LinkedList 继承了 AbstractSequentialList 类。
- LinkedList 实现了 Queue 接口，可作为队列使用。
- LinkedList 继承了 AbstractQueue抽象类，具有队列的功能。
- LinkedList 实现了 List 接口，可进行列表的相关操作。
- LinkedList 实现了 Deque 接口，可作为双向队列使用。
- LinkedList 实现了 Cloneable 接口，可实现克隆。
- LinkedList 实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。

## 十一、并发安全ConcurrentLinkedQueue类 

### 11.1 理解ConcurrentLinkedQueue

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpFaWFpYTFaQXlxSW85d2RUSUhTTXd6MDg2SHhNU1I5UGs4a3I1cnlPWG9jZE9HQlhZT3lCZ21YQS82NDA?x-oss-process=image/format,png)

  ConcurrentLinkedQueue原理 

- ConcurrentLinked是由链表结构组成的线程安全的先进先出无界队列。
- 当多线程要共享访问集合时，ConcurrentLinkedQueue是一个比较好的选择。
- 不允许插入null元素
- 支持非阻塞地访问并发安全的队列，不会抛出ConcurrentModifiationException异常。
- size方法不是准确的，因为在统计集合的时候，队列可能正在添加元素，导致统计不准。
- 批量操作addAll、removeAll、retainAll、containsAll、equals和toArray不保证原子性（操作不可分割）
- 添加元素happen-before其他线程移除元素。
- 用法如下：

```go
ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();



BuildingBlockWithName buildingBlock = new BuildingBlockWithName("三角形", "A");



concurrentLinkedQueue.add(buildingBlock);
```

### 11.2 ConcurrentLinkedQueue类继承了哪些类？

- AbstractQueue抽象类，具有队列的功能

### 11.3 ConcurrentLinkedQueue类实现了哪些接口？

- Queue接口，可作为队列使用

## 十二、双向数组ArrayDeque类 

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpNbXJpYjRkd25XR1RzeWdyRndUV2liVlVyc3dDSEJJMlFnVXdaOGlhU2w4UlE0Y0dLbFI5MGliMmlhdy82NDA?x-oss-process=image/format,png)

  ArrayDeque原理图 

### 12.1 理解ArrayDeque

- 由数组组成的双端队列。
- 没有容量限制，根据需要扩容。
- 不是线程安全的。
- 禁止插入null元素。
- 当用作栈时，比栈速度快，当用作队列时，速度比LinkList快。
- 大部分方法的算法时间复杂度为O(1)。
- remove、removeFirstOccurrence、removeLastOccurrence、contains、remove 和批量操作的算法时间复杂度O(n)

### 12.2 使用方法

创建一个ArrayDeque，往arrayDeque队尾添加元素。

```go
ArrayDeque arrayDeque = new ArrayDeque();



for (int i = 0; i < 50; i++) {



    arrayDeque.add(buildingBlock); // add方法等价于addLast方法



}
```

### 12.3 ArrayDeque实现了哪些接口

- Deque接口 - 可用于双端队列

## 十三、双向并发ConcurrentLinkedDeque类 

### 13.1 理解ConcurrentLinkedDeque类

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko5eGoyRkhJNW9ZdW1sdllGTXFUcXJsMEhaNHBTVTE2R0ttVEUwNUJSVmpyd05Ga3hnSjZaUHcvNjQw?x-oss-process=image/format,png)

  ConcurrentLinkedDeque原理图 

- 由链表结构组成的双向无界阻塞队列
- 插入、删除和访问操作可以并发进行，线程安全的类
- 不允许插入null元素
- 在并发场景下，计算队列的大小是不准确的，因为计算时，可能有元素加入队列。
- 批量操作addAll、removeAll、retainAll、containsAll、equals和toArray不保证原子性（操作不可分割）

### 13.2 ConcurrentLinkedDeque使用示例

创建两个积木：三角形、四边形，然后添加到队列：

```go
BuildingBlockWithName buildingBlock1 = new BuildingBlockWithName("三角形", "A");



BuildingBlockWithName buildingBlock2 = new BuildingBlockWithName("四边形", "B");



ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();



concurrentLinkedDeque.addFirst(buildingBlock1);



concurrentLinkedDeque.addLast(buildingBlock2);



//结果：顺序：三角形、四边形
```

### 13.3 ConcurrentLinkedDeque实现了哪些接口

- Deque接口 - 可用于双端队列

## 十四、数组阻塞ArrayBlockingQueue类 

### 14.1 理解ArrayBlockingQueue

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko0QWVscmJuRGNFVm9pYk5JS1l3MnY3YjdGZm5qTWJZV0t1aWNQbFhtMVRqYmZKMmVaVHZSc2dpY0EvNjQw?x-oss-process=image/format,png)

  ArrayBlockingQueuey原理图 

- ArrayBlockingQueue是一个用数组实现的有界阻塞队列。
- 队列慢时插入操作被阻塞，队列空时，移除操作被阻塞。
- 按照先进先出（FIFO）原则对元素进行排序。
- 默认不保证线程公平的访问队列。
- 公平访问队列：按照阻塞的先后顺序访问队列，即先阻塞的线程先访问队列。
- 非公平性是对先等待的线程是非公平的，当队列可用时，阻塞的线程都可以争夺访问队列的资格。有可能先阻塞的线程最后才访问访问队列。
- 公平性会降低吞吐量。

### 14.2 ArrayBlockingQueue使用示例

创建两个积木：三角形、四边形，然后添加到队列：

```go
BuildingBlockWithName buildingBlock1 = new BuildingBlockWithName("三角形", "A");



BuildingBlockWithName buildingBlock2 = new BuildingBlockWithName("四边形", "B");



ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100, true);



arrayBlockingQueue.add(buildingBlock1);



arrayBlockingQueue.add(buildingBlock2);
```

### 14.3 ArrayBlockQueue实现了哪些接口

- Deque接口 - 可用于双端队列

## 十五、链表阻塞LinkedBlockingQueue类 

### 15.1 理解LinkedBlockingQueue

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko1Mk53b3EwdGljS1pEZ0JIWHdDQzFycndlZVRtMzZmc1h6dTJ0cUtGMmM4MHJySzR4ZE1iSzFRLzY0MA?x-oss-process=image/format,png)

  LinkedBlockingQueue原理 

- LinkedBlockingQueue具有单链表和有界阻塞队列的功能。
- 队列慢时插入操作被阻塞，队列空时，移除操作被阻塞。
- 默认和最大长度为Integer.MAX_VALUE，相当于无界(值非常大：2^31-1)。

### 15.2 LinkedBlockingQueue使用示例

```go
LinkedList linkedList1 = new LinkedList();



linkedList1.add("A");



linkedList1.add("B");



linkedList1.add("C");
```

### 15.3 LinkedBlockingQueue的应用场景

- 吞吐量通常要高于ArrayBlockingQueue。创建线程池时，参数runnableTaskQueue（任务队列），用于保存等待执行的任务的阻塞队列可以选择LinkedBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。

### 15.4 LinkedBlockingQueue实现了哪些接口

- LinkedBlockingQueue继承了 BlockingQueue类，可作为阻塞队列使用
- LinkedBlockingQueue继承了 AbstractQueue抽象类，具有队列的功能。
- LinkedBlockingQueue实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。

## 十六、双向阻塞LinkedBlockingDeque类 

### 16.1 理解LinkedBlockingDeque类

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpVTzVsTWFUakRTTVlKUjhqcUY2YTh1c3ZZUjgzdFZ0Z01IaWNScWlhbE5VT0lxUHlGeXBGSnlpY3cvNjQw?x-oss-process=image/format,png)

  LinkedBlockingDeque原理图 

- 由链LinkedBlockingDeque = 阻塞队列+链表+双端访问
- 线程安全。
- 多线程同时入队时，因多了一端访问入口，所以减少了一半的竞争。
- 默认容量大小为Integer.MAX_VALUE。可指定容量大小。

### 16.2 LinkedBlockingDeque的应用场景

LinkedBlockingDeque可以用在“工作窃取“模式中。

`工作窃取算法`：某个线程比较空闲，从其他线程的工作队列中的队尾窃取任务来帮忙执行。

### 16.3 LinkedBlockingDeque实现了哪些接口

- LinkedBlockingDeque继承了 BlockingDeque类，可作为阻塞队列使用
- LinkedBlockingDeque继承了 AbstractQueue抽象类，具有队列的功能。
- LinkedBlockingDeque实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。

## 十七、链表阻塞LinkedTransferQueue类 

### 17.1 理解LinkedTransferQueue类

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpudUVpYThXNkREejkzM1B4cElpYk5xZmljUnE4WDJCdk5YZW5NWmFEaWF3M0RaV0I3Z3ZyMFVvYUNRLzY0MA?x-oss-process=image/format,png)

  LinkedTransferQueue原理图 

LinkedTransferQueue = 阻塞队列+链表结构+TransferQueue

之前我们讲“使命必达TransferQueue接口时**已经介绍过了TransferQueue接口** ，所以LinkedTransferQueue接口跟它相似，只是加入了阻塞插入和移除的功能，以及结构是链表结构。

之前的TransferQueue也讲到了3个案例来说明TransferQueue的原理，大家可以回看TransferQueue。

### 17.2 LinkedTransferQueue接口比其他阻塞队列多了5个方法

- transfer(E e)
- tryTransfer(E e)
- tryTransfer(E e, long timeout, TimeUnit unit)
- getWaitingConsumerCount()
- hasWaitingConsumer()

### 17.3 LinkedTransferQueue代码示例

- 创建一个LinkedTransferQueue，生产者1 依次往队列中添加 A、B、C

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkp6aWMwT2dLT2IwUEdoenBrV3ZMUGRsazdVVUxLdzVjYXRPZlFuQ01tMjl4YkNjSDl2SFJXQm5nLzY0MA?x-oss-process=image/format,png)

  生产者1 依次往队列中添加 A、B、C 

- 生产者2 依次往队列中添加 D、E、F

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpRTWlhMGVEY2ljQXVFaEFmdlBsUVNsU2puOUJLY3JlcXJvOUozaWEwdHQ3U1B6MU40R0tQamJrdGcvNjQw?x-oss-process=image/format,png)

  生产者2 依次往队列中添加 D、E、F 

- 消费者依次从队列首部开始消费元素，每次消费前，先sleep 2s，来演示transfer方法是否进行了等待。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpUSllGaWJpYXF0TE5ERUNmRWtIZzJaazF6emtFSWxRSmNhVkt2dFIxdjNOeDdrV043aWFOc2tmVncvNjQw?x-oss-process=image/format,png)

  消费者消费元素 

- 运行结果

```go
生产者1     transfer A 



生产者2     transfer D 



 



2s后...



  



消费者      take A



生产者1     put B 



 



2s后...



     



消费者      take D



生产者2     transfer E 



    



2s后...



  



消费者      take B



生产者1     transfer C 
```

- 代码执行结果分析

（1）首先生产者线程1和2 调用transfer方法来传输A和D，发现没有消费者线程接收，所以被阻塞。

（2）消费者线程过了2s后将A拿走了，然后生产者1 被释放继续执行，传输元素B，发现又没有消费者消费，所以进行了等待。

（3）消费者线程过了2s后，将排在队列首部的D元素拿走，生产者2继续往下执行，传输元素E，发现没有消费者，所以进行了等待。

（4）消费者线程过了2s后，将排在队列首部的B元素拿走，生产者1传输C元素，等待消费者拿走。

（5）消费者不再消费了，所以生产者1和生产者2都被阻塞了，元素C和，元素E都没有被拿走，而且生产者2的F元素还没有开始传输，因为在等待元素D被拿走。

（6）看下队列里面确实有C和E元素，而且E排在队列的首部。

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpweVpONTlUcEViQXozZlZXVkY5STJpYWliZGxWYkN5cFoyTUZJOVNoUDFBcDVUTGhkelN6UjgyZy82NDA?x-oss-process=image/format,png)

  队列里面的元素 

### 17.4 LinkedTransferQueue实现了哪些接口

- LinkedBlockingDeque继承了 BlockingQeque类，可作为阻塞队列使用
- LinkedBlockingDeque继承了 AbstractQueue抽象类，具有队列的功能。

## 十八、传球好手SynchronousQueue类 

### 18.1 理解SynchronousQueue类

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbko2elREeHBpYlgyZ3Fsb05FZEhJTGtpYk1WcGZpYWNPYlVNR3hWM1JIVlo1T3U0Mm5Wd3BGRXZxalEvNjQw?x-oss-process=image/format,png)

  SynchronousQueue原理图 

- 我称SynchronousQueue为”传球好手“。想象一下这个场景：小明抱着一个篮球想传给小花，如果小花没有将球拿走，则小明是不能再拿其他球的。
- SynchronousQueue负责把生产者产生的数据传递给消费者线程。
- SynchronousQueue本身不存储数据，调用了put方法后，队列里面也是空的。
- 每一个put操作必须等待一个take操作完成，否则不能添加元素。
- 适合传递性场景。
- 性能高于ArrayBlockingQueue和LinkedBlockingQueue。

### 18.2 SynchronousQueue示例

我们创建了两个线程，一个线程用于生产，一个线程用于消费

- 生产的线程依次put A、B、C三个值

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpMaGliRzBEaGljU1RkWFRpYW9oUTNheGY3aWJFc1hLSThXMmliUGppYUtGY0pEVDF1bmliZG9DaWJiUzFXQS82NDA?x-oss-process=image/format,png)

  生产的线程依次put A、B、C三个值 

- 消费线程使用take来消费阻塞队列中的内容，每次消费前，等待5秒

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpjY0FpYUR4alJqRU5EMTlxdnU3THh3SlBCcTVpY0cyenEyaWNKbjhqT0drQTVmODA3UnhpY1NZRVB3LzY0MA?x-oss-process=image/format,png)

  消费线程每隔5s调用take方法 

- 运行结果

```go
t1     put A 



t2     take A 



 



5秒后...



 



t1     put B 



t2     take B 



 



5秒后...



 



t1     put C 



t2     take C 
```

小结：说明生产线程执行put第一个元素"A" 操作后，需要等待消费者线程take完“A”后，才能继续往下执行代码。

### 18.1 SynchronousQueue应用场景

- 吞吐量通常要高于LinkedBlockingQueue。创建线程池时，参数runnableTaskQueue（任务队列），用于保存等待执行的任务的阻塞队列可以选择SynchronousQueue。静态工厂方法Executors.newCachedThreadPool()使用了这个队列

### 18.2 SynchronousQueue和LinkedTransferQueue的区别

- SynchronousQueue 不存储元素，而LinkedTransferQueue存储元素。
- SynchronousQueue 队列里面没有元素，而LinkedTransferQueue可以有多个存储在队列等待传输。
- LinkedTransferQueue还支持若传输不了，则丢到队列里面去。
- LinkedTransferQueue还支持若超过一定时间传输不了，则丢到队列里面去。

## 十九、优先级阻塞PriorityBlockingQueue类 

### 19.1 理解PriorityBlockQueue类

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkp2NUtOandHUE1EMXZjRkt1MUxRMXBDaWFBUUFSQTFpY1JlU0hUTkNIbzJRRGljM2tVcHhEZTAyMlEvNjQw?x-oss-process=image/format,png)

  PriorityBlockQueue的原理图 

- PriorityBlockQueue = PriorityQueue + BlockingQueue
- 之前我们也讲到了PriorityQueue的原理，支持对元素排序。
- 元素默认自然排序。
- 可以自定义CompareTo()方法来指定元素排序规则。
- 可以通过构造函数构造参数Comparator来对元素进行排序。

### 19.2 PriorityBlockQueue实现了哪些接口

- LinkedBlockingQueue继承了 BlockingQueue接口，可作为阻塞队列使用
- LinkedBlockingQueue继承了 AbstractQueue抽象类，具有队列的功能。
- LinkedBlockingQueue实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。

## 二十、延时阻塞DelayQueue类 

### 20.1 理解DelayQueue

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpWNnJNODBkWXB5cTU3NmFubnhYMU1iUkM2N0VSYkRFZEZMUlQ5ZkR5ZVdhdm9KMHpZUGp1VHcvNjQw?x-oss-process=image/format,png)

  DelayQueue原理图 

- DelayQueue = Delayed + BlockingQueue。队列中的元素必须实现Delayed接口。

```go
public class DelayQueue<E extends Delayed> extends AbstractQueue<E>



    implements BlockingQueue<E> {
```

- 在创建元素时，可以指定多久可以从队列中获取到当前元素。只有在延时期满才能从队列中获取到当前元素。

### 20.2 源码解析

- 添加元素时，指定延时多久可以从队列中获取元素

```go
public boolean offer(E e, long timeout, TimeUnit unit) {



    return offer(e);



}
```

- 获取元素的方法poll需要等待延时时间过了才能获取到元素

```go
if (first == null || first.getDelay(NANOSECONDS) > 0)



    return null;



else



    return q.poll();
```

![img](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy9TZkFITXVVeHFKMTVGRDNlYmtzWmRuWmVlV2ljc3lEbkpwZHU0eHlRUWZRdzdWbGVqRmVsYnY2WGVpY3NXSzRJZ2lhaWFjMW9EbU1sU2ljT0x0S3ZmWEdiMzRnLzY0MA?x-oss-process=image/format,png)

  poll方法 

### 20.3 应用场景

- 缓存系统的设计：可以用DelayQueue保存缓存元素的有效期。然后用一个线程循环的查询DelayQueue队列，一旦能从DelayQueue中获取元素时，表示缓存有效期到了。
- 定时任务调度：使用DelayQueue队列保存当天将会执行的任务和执行时间，一旦从DelayQueue中获取到任务就开始执行。比如Java中的TimerQueue就是使用DelayQueue实现的。

### 20.4 DelayQueue实现了哪些接口

- DelayQueue实现了 BlockingQueue接口，可作为阻塞队列使用

这一篇花了很多心思在上面，看**官方英文文档、画原理图、写demo代码，排版**。这恐怕是市面上**最全最细**讲解Queue的。

*来源：https://github.com/feigeswjtu/java-basics*