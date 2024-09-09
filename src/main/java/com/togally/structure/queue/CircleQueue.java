package com.togally.structure.queue;

public class CircleQueue<T> implements Queue<T> {
    private static int capacity = 0;
    /**
     * 数组
     */
    private Object[] data;

    /**
     * 头指针
     */
    private int front;

    /**
     * 尾指针
     */
    private int rear;

    /**
     * 大小
     */
    private int size;

    public CircleQueue(int queueLength) {
        capacity = queueLength;
        this.initQueue();
    }

    @Override
    public void initQueue() {
        data = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public void destroyQueue() {
        this.data = null;
        this.front = 0;
        this.rear = 0;
    }

    @Override
    public void clearQueue() {
        for (Object datum : data) {
            datum = null;
        }
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public boolean queueEmpty() {
        return this.size == 0;
    }

    @Override
    public T getHead() {
        return getItem(front);
    }

    @Override
    public void enQueue(T e) {
        size++;
        data[rear] = e;
        this.rearMove();
    }

    /**
     * 尾指针移动
     * 可用长度为 data.length - 1  因为当rear = font当时候我们无法判断是空集合还是，满集合
     */
    private void rearMove() {
        this.rear = ++this.rear % data.length;
        if (this.rear == this.front){
            throw new RuntimeException("rear is out of size");
        }
    }

    /**
     * 头指针移动
     */
    private void frontMove() {
        this.front = ++this.front % data.length;
        if (this.front == this.rear){
            throw new RuntimeException("queue is empty!");
        }
    }

    @Override
    public T deQueue() {
        size--;
        T item = getItem(front);
        data[front] = null;
        frontMove();
        return item;
    }

    @Override
    public int queueLength() {
        return size;
    }

    @SuppressWarnings("unchecked")
    protected T getItem(int index) {
        return (T) data[index];
    }
}
