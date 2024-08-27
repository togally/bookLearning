package com.togally.structure.stack;

import com.togally.structure.list.array.ArrayList;

/**
 * 基于数据的栈
 *
 * @param <T>
 */
public class ArrayStack<T> extends AbstractStack<T> {
    @Override
    public void init() {
        super.list = new ArrayList<>();
    }
}
