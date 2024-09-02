package com.togally.structure.stack;

import com.togally.structure.list.List;
import com.togally.structure.list.linked.SinglyLinkedList;

/**
 * 基于链表的栈
 *
 * @param <T>
 */
public class LinkedStack<T> implements IStack<T> {
    private List<T> list;

    @Override
    public void init() {
        list = new SinglyLinkedList<>();
    }


    @Override
    public boolean destroyStack() {
        list = null;
        return true;
    }

    @Override
    public boolean clearStack() {
        list.clear();
        return true;
    }

    @Override
    public boolean stackEmpty() {
        return list.listEmpty();
    }

    @Override
    public T getTop() {
        return list.get(lastIndex());
    }

    @Override
    public T pop() {
        return list.remove(lastIndex());
    }

    @Override
    public void push(T e) {
        list.insert(lastIndex() + 1, e);
    }

    @Override
    public int stackLength() {
        return list.length();
    }

    @Override
    public List<T> getList() {
        return list;
    }

    private int lastIndex() {
        return list.length() - 1;
    }
}
