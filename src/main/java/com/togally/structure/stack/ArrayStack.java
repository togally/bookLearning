package com.togally.structure.stack;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.List;
import com.togally.structure.list.array.ArrayList;


/**
 * 栈抽象数据类型
 *
 * @param <T>
 */
public class ArrayStack<T> implements IStack<T> {
    /**
     * 栈是基于线性表来做的
     */
    private Object[] list;
    /**
     * 栈顶
     */
    private int top;

    @Override
    public void init() {
        top = 0;
        list = new Object[100];
    }

    @Override
    public void destroyStack() {
        list = null;
    }

    @Override
    public void clearStack() {
        for (Object t : list) {
            t = null;
        }
        list = null;
        top = 0;
    }

    @Override
    public boolean stackEmpty() {
        return list == null || top == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getTop() {
        return (T) list[top];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        Object topObj = list[top];
        list[top] = null;
        top--;
        return (T) topObj;
    }

    @Override
    public void push(T e) {
        if (top + 1 > list.length || top < 0){
            throw new OutOfRangeException("stack push error");
        }
        list[++top] = e;
    }

    @Override
    public int stackLength() {
        return top;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList() {
        return null == list ? null : new ArrayList<>(list);
    }
}
