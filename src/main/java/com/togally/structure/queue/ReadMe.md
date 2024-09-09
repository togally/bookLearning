# 队列
## 定义
队列是一种[特殊的线性表](),他的特殊性在于将线性表的操作限定为[表尾插入，表头删除]()

通过这个特殊的限定我们可以达到[FIFO(first in first out)]()的目的


## 抽象数据类型
[Queue.java](Queue.java)

## 队列的数组实现（循环队列）
[CircleQueue.java](CircleQueue.java)

### 队列数据存储的问题
虽然我们采用数组来作为数据存储的基本结构，但是由于队列的使用过程中头尾指针是动态的。

所以我们很容一造成头指针到index = 0 之间的数据浪费

关注一下尾指针移动的几个问题，
[CircleQueue.java#rearMove](CircleQueue.java)

## 队列的链表实现实现
