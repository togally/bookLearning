package com.togally.structure.list.linked;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.List;

import java.util.Objects;

/**
 * 单向链表
 *
 * @param <T>
 */
public class UndirectLinkedList<T> implements List<T> {
    private Node<T> first;

    private int size;

    private int modCount;

    public UndirectLinkedList() {
        initList();
    }

    @Override
    public List<T> initList() {
        this.first = null;
        this.size = 0;
        this.modCount = 0;
        return this;
    }

    @Override
    public Boolean listEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        Node<T> prv = first;
        Node<T> next;
        while (null != prv) {
            next = prv.next;
            unlink(prv, next);
            prv = next;
        }
        this.size = 0;
    }

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        return null == node ? null : node.item;
    }

    @Override
    public int locateElem(T element) {
        if (null == first) return -1;

        Node<T> node = first;
        for (int i = 0; null != node; i++) {
            if (Objects.equals(node.item, element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public int insert(int index, T e) {
        indexCheck(index);
        modCount++;

        if (index == 0) return insertFirst(e);

        Node<T> oldNodePrv = getNode(index - 1);
        assert oldNodePrv != null;
        Node<T> oldNode = oldNodePrv.next;
        oldNodePrv.next = new Node<>(e, oldNode);
        return index;
    }

    /**
     * 获取节点
     *
     * @param index
     * @return
     */
    private Node<T> getNode(int index) {
        Node<T> node = first;
        if (null == first) return null;

        for (int j = 0; j < index; j++) {
            node = node.next;
            if (null == node) return null;
        }
        return node;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size) {
            throw new OutOfRangeException("index out of range for linked list");
        }
    }

    @Override
    public void insert(T e) {
        modCount++;
        insertFirst(e);
    }

    /**
     * 头插
     *
     * @param data 节点数据
     */
    private int insertFirst(T data) {
        Node<T> oldNode;
        oldNode = this.first;
        this.first = new Node<>(data, oldNode);
        this.size++;
        return 0;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Node<T> removePrv = getNode(index - 1);
        assert removePrv != null;
        modCount++;
        return unlink(removePrv, removePrv.next);
    }

    @Override
    public int length() {
        return this.size;
    }

    /**
     * 拆链
     *
     * @param pre  前置节点
     * @param node 节点
     * @return 被拆节点数据
     */
    private T unlink(Node<T> pre, Node<T> node) {
        if (null == node){
            T data = pre.item;
            pre.next = null;
            pre.item = null;
            return data;
        }
        final Node<T> next = node.next;
        if (null == pre) {
            this.first = next;
        } else {
            pre.next = next;
        }
        T data = node.item;
        node.item = null;
        node.next = null;
        return data;
    }

    /**
     * 节点类
     *
     * @param <T>
     */
    protected static class Node<T> {

        protected T item;

        protected Node<T> next;

        public Node() {
        }

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
