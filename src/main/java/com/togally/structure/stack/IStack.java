package com.togally.structure.stack;

import com.togally.structure.list.List;

/**
 * 栈
 */
public interface IStack<T> {
    /**
     * 初始化
     */
    void init();

    /**
     * 销毁栈
     */
    void destroyStack();

    /**
     * 清空栈
     */
    void clearStack();

    /**
     * 栈是否为空
     *
     * @return true 空 false 非空
     */
    boolean stackEmpty();

    /**
     * 若栈非空，返回栈顶元素
     *
     * @return T
     */
    T getTop();

    /**
     * 弹栈
     *
     * @return 栈顶元素
     */
    T pop();

    /**
     * 压栈
     *
     * @param e 元素
     */
    void push(T e);

    /**
     * 返回栈长度
     *
     * @return int
     */
    int stackLength();

    /**
     * 获取栈存储list(非标准中包含该方法)
     * @return list
     */
    List<T> getList();
}
