package com.togally.structure.queue;

public interface Queue<T> {
    /**
     * 初始化队列
     */
    void initQueue();

    /**
     * 销毁队列
     */
    void destroyQueue();

    /**
     * 清空队列
     */
    void clearQueue();

    /**
     * 队列是否为空
     *
     * @return true/false
     */
    boolean queueEmpty();

    /**
     * 获取队列头元素
     *
     * @return T
     */
    T getHead();

    /**
     * 插入新元素并成为队尾元素
     *
     * @param e
     */
    void enQueue(T e);

    /**
     * 删除队头并用返回元素
     *
     * @return e
     */
    T deQueue();

    /**
     * 对列长度
     *
     * @return int
     */
    int queueLength();
}
