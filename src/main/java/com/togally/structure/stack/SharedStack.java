package com.togally.structure.stack;

import com.togally.exception.OutOfRangeException;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SharedStack<T> extends ArrayStack<T> implements IShardedStack<T> {

    private int top2;

    private int top1;

    @Override
    public void init() {
        super.init();
        this.top1 = 0;
        this.top2 = list.length - 1;
        this.direction = 0;
    }

    @Override
    public void destroyStack(int num) {
        stackChge(num,super::destroyStack);
    }

    @Override
    public void clearStack(int num) {
        stackChge(num,super::clearStack);
    }


    @Override
    public boolean stackEmpty(int num) {
        return stackChge(num,super::stackEmpty);
    }

    @Override
    public T getTop(int num) {
        return stackChge(num,super::getTop);
    }

    @Override
    public T pop(int num) {
        return stackChge(num,super::pop);
    }

    @Override
    public void push(T e, int num) {
        stackChge(num,super::push,e);
    }

    @Override
    public int stackLength(int num) {
        return stackChge(num,super::stackLength);
    }

    /**
     * 槽切换
     *
     * @param num
     */
    private void stackChge(int num, Consumer<T> function,T e) {
        if (num == 1) {
            top = top1;
            super.direction = 0;
            function.accept(e);
            top1 = top;
        } else if (num == 2) {
            top = top2;
            super.direction = 1;
            function.accept(e);
            top2 = top;
        } else {
            throw new OutOfRangeException("sharedStack out of range for num : " + num);
        }
    }


    /**
     * 槽切换
     *
     * @param num
     */
    private <T,R> R stackChge(int num, Function<T,R> function,T e) {
        if (num == 1) {
            top = top1;
            super.direction = 0;
            R r = function.apply(e);
            top1 = top;
            return r;
        } else if (num == 2) {
            top = top2;
            super.direction = 1;
            R r = function.apply(e);
            top2 = top;
            return r;
        } else {
            throw new OutOfRangeException("sharedStack out of range for num : " + num);
        }
    }

    /**
     * 槽切换
     *
     * @param num
     */
    private <T> T stackChge(int num, Supplier<T> supplier) {
        if (num == 1) {
            top = top1;
            super.direction = 0;
            T obj = supplier.get();
            top1 = top;
            return obj;
        } else if (num == 2) {
            top = top2;
            super.direction = 1;
            T obj = supplier.get();
            top2 = top;
            return obj;
        } else {
            throw new OutOfRangeException("sharedStack out of range for num : " + num);
        }
    }
    @Override
    public boolean destroyStack() {return true;}
}
