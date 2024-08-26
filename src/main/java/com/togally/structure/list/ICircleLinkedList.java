package com.togally.structure.list;

import com.togally.structure.list.linked.CircleLinkedList;
import com.togally.structure.list.linked.node.Node;

public interface ICircleLinkedList<T,N extends Node<T>> {

    /**
     * 合并两个链表
     *
     * @param other 另外一个链表
     * @return 合并后的链表
     */
    CircleLinkedList<T,N> union(CircleLinkedList<T,N> other);
}
