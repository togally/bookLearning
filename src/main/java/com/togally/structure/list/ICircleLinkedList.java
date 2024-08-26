package com.togally.structure.list;

import com.togally.structure.list.linked.CircleLinkedList;

public interface ICircleLinkedList<T> {

    /**
     * 合并两个链表
     *
     * @param other 另外一个链表
     * @return 合并后的链表
     */
    CircleLinkedList<T,?> union(CircleLinkedList<T,?> other);
}