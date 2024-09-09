package com.togally.structure.queue;

public class LinkedQueue<T> implements Queue<T> {
    protected Node<T> front;

    protected Node<T> rear;

    protected int size;

    public LinkedQueue() {
        this.initQueue();
    }

    @Override
    public void initQueue() {
        this.front = new Node<>(null, this.rear);
        this.rear = new Node<>(this.front, null);
        this.front.next = this.rear;
        this.size = 0;
    }

    @Override
    public void destroyQueue() {
        while (this.front.next != this.rear) {
            Node<T> f = this.front;
            this.front = this.front.next;
            destroyNode(f);
        }
        destroyNode(this.front);
        destroyNode(this.rear);
        this.size = 0;
    }

    /**
     * 销毁节点
     *
     * @param node
     */
    protected void destroyNode(Node<T> node) {
        node.data = null;
        node.next = null;
        node.prv = null;
        node = null;
    }

    @Override
    public void clearQueue() {
        destroyQueue();
        initQueue();
    }

    @Override
    public boolean queueEmpty() {
        return size == 0;
    }

    @Override
    public T getHead() {
        return this.front.next == this.rear ? null : this.front.next.getData();
    }

    @Override
    public void enQueue(T e) {
        size++;
        Node<T> oldRear = this.rear.prv;
        oldRear.next = new Node<>(oldRear, this.rear, e);
        this.rear.prv = oldRear.next;

    }

    @Override
    public T deQueue() {
        size--;
        if (size < 0) {
            throw new RuntimeException("queue is empty!");
        }
        Node<T> oldFront = this.front.next;
        Node<T> newFront = oldFront.next;
        this.front.next = newFront;
        newFront.prv = this.front;

        T data = oldFront.getData();
        destroyNode(oldFront);
        return data;
    }

    @Override
    public int queueLength() {
        return size;
    }

    protected static class Node<T> {

        protected Node<T> next;

        protected Node<T> prv;

        protected T data;

        public Node(Node<T> prv, Node<T> next) {
            this.next = next;
            this.prv = prv;
        }

        public Node(Node<T> prv, Node<T> next, T data) {
            this.next = next;
            this.prv = prv;
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
