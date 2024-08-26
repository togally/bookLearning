package com.togally.structure.list.linked;


import java.io.Serializable;

/**
 * 循环链表
 * <p>
 * 循环链表应当首位相连
 *
 * @param <T>
 */
public class CircleLinkedList<T> extends AbstractLinkedList<T> implements ICircleLinkedList<T> {
    /**
     * 循环链表当遍历到首位时节点结束
     *
     * @param node 节点
     * @return 结束
     */
    @Override
    protected boolean nodeFinished(Node<T> node) {
        return this.first == node;
    }

    /**
     * 循环链表的尾插是指向头节点的
     *
     * @param data 节点数据
     * @return index
     */
    @Override
    protected int insertLast(T data) {
        if (null == this.last) return super.insertFirst(data);
        // 1.记录末尾节点 2.last指向新创建的node 3.老node指向信node
        Node<T> oldNode = this.last;
        super.last = new Node<T>(data, super.first);
        oldNode.next = super.last;

        this.size++;
        return 0;
    }

    /**
     * 循环连标做union操作时只需要将头尾节点相连即可
     *
     * @param other 另外一个链表
     * @return
     */
    @Override
    public CircleLinkedList<T> union(CircleLinkedList<T> other) {
        this.modCount++;
        this.last.next = other.first;
        other.last.next = this.first;
        this.size += other.size;
        return this;
    }
}
