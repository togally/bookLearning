package com.togally.structure.stack;

import com.togally.structure.list.List;

public interface IShardedStack<T> extends IStack<T>{
    /**
     * 初始化
     */
    void init();

    /**
     * 销毁栈
     */
    void destroyStack(int num);

    /**
     * 清空栈
     */
    void clearStack(int num);

    /**
     * 栈是否为空
     *
     * @return true 空 false 非空
     */
    boolean stackEmpty(int num);

    /**
     * 若栈非空，返回栈顶元素
     *
     * @return T
     */
    T getTop(int num);

    /**
     * 弹栈
     *
     * @return 栈顶元素
     */
    T pop(int num);

    /**
     * 压栈
     *
     * @param e 元素
     */
    void push(T e,int num);

    /**
     * 返回栈长度
     *
     * @return int
     */
    int stackLength(int num);
}
