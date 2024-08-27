package com.togally.structure.stack;

import com.togally.structure.list.linked.SinglyLinkedList;

/**
 * 基于链表的栈
 *
 * @param <T>
 */
public class LinkedStack<T> extends AbstractStack<T> {
    @Override
    public void init() {
        super.list = new SinglyLinkedList<>();
    }
}
