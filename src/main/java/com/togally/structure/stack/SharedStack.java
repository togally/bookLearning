package com.togally.structure.stack;

import com.togally.structure.list.List;

public class SharedStack<T> implements IStack<T> {
    /**
     * 容量
     */
    private static final int CAPACITY = 100;
    /**
     * 数据
     */
    private Object[] data;

    /**
     * 栈顶1
     */
    private Integer top1;

    /**
     * 栈顶2
     */
    private Integer top2;

    @Override
    public void init() {
        data = new Object[CAPACITY];
        top1 = 0;
        top2 = data.length - 1;
    }

    @Override
    public void destroyStack() {
        clearStack();
        data = null;
        top1 = null;
        top2 = null;
    }

    @Override
    public void clearStack() {
        for (Object datum : data) {
            datum = null;
        }
    }

    @Override
    public boolean stackEmpty() {
        return false;
    }

    @Override
    public T getTop() {
        return null;
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public void push(T e) {

    }

    @Override
    public int stackLength() {
        return 0;
    }

    @Override
    public List<T> getList() {
        return null;
    }
}
