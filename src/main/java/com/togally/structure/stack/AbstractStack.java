package com.togally.structure.stack;

import com.togally.structure.list.List;

/**
 * 栈抽象数据类型
 *
 * @param <T>
 */
public abstract class AbstractStack<T> implements IStack<T> {
    /**
     * 栈是基于线性表来做的
     */
    protected List<T> list;

    @Override
    public void destroyStack() {
        list = null;
    }

    @Override
    public void clearStack() {
        list.clear();
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
