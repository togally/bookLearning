package com.togally.structure.stack;

import com.togally.support.exception.OutOfRangeException;
import com.togally.structure.list.List;
import com.togally.structure.list.array.ArrayList;

import java.util.Arrays;


/**
 * 栈抽象数据类型
 *
 * @param <T>
 */
public class ArrayStack<T> implements IStack<T> {
    /**
     * 栈是基于线性表来做的
     */
    protected Object[] list;
    /**
     * 栈顶
     */
    protected int top;
    /**
     * 方向 0 正向 1 负向
     */
    protected int direction;

    public ArrayStack() {
        this.init();
    }

    @Override
    public void init() {
        top = 0;
        list = new Object[100];
        direction = 0;
    }

    @Override
    public boolean destroyStack() {
        list = null;
        return true;
    }

    @Override
    public boolean clearStack() {
        if (direction == 0) {
            for (int i = 0; i <= top; i++) {
                list[i] = null;
            }
        } else if (direction == 1) {
            for (int i = list.length - 1; i >= top; i--) {
                list[i] = null;
            }
        }
        top = direction == 0 ? 0 : 99;
        return true;
    }

    @Override
    public boolean stackEmpty() {
        return list == null || (top == 0 && direction == 0) || (top == list.length - 1 && direction == 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getTop() {
        if (this.stackLength() == 0){
            return null;
        }
        return (T) list[direction == 0 ? top - 1 : top + 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        int topIndex = direction == 0 ? top - 1 : top + 1;
        Object topObj = list[topIndex];
        list[topIndex] = null;
        if (direction == 0) {
            top--;
        } else {
            top++;
        }
        return (T) topObj;
    }

    @Override
    public void push(T e) {
        if (top + 1 > list.length || top < 0) {
            throw new OutOfRangeException("stack push error");
        }

        int next = direction == 0 ? top++ : top--;
        if (list[next] != null) {
            throw new OutOfRangeException("stack push error");
        }
        list[next] = e;
    }

    @Override
    public int stackLength() {
        return direction == 0 ? top : list.length - top - 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getList() {
        if (null == list)return null;
        return top == 0 ? new ArrayList<>() : new ArrayList<>(Arrays.copyOf(list, top));
    }
}
