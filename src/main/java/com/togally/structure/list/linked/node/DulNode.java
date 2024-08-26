package com.togally.structure.list.linked.node;

/**
 * 双向节点
 *
 * @param <T>
 */
public class DulNode<T> extends Node<T> {
    /**
     * 数据
     */
    public T data;
    /**
     * 后置节点
     */
    public DulNode<T> next;
    /**
     * 前置节点
     */
    public DulNode<T> prv;

    public DulNode(T data) {
        super(data);
        this.data = data;
    }

    public DulNode(T data, DulNode<T> prv, DulNode<T> next) {
        super(data, next);
        this.data = data;
        this.prv = prv;
        this.next = next;
    }

    public DulNode(T data, DulNode<T> next) {
        super(data, next);
        this.data = data;
        this.next = next;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public DulNode<T> getNext() {
        return next;
    }

    public void setNext(DulNode<T> next) {
        this.next = next;
    }

    public DulNode<T> getPrv() {
        return prv;
    }

    public void setPrv(DulNode<T> prv) {
        this.prv = prv;
    }
}
