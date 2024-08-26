package com.togally.structure.list.linked;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.List;

import java.util.Objects;

public abstract class AbstractLinkedList<T> implements List<T> {

    /**
     * 头节点
     */
    protected Node<T> first;

    /**
     * 尾节点
     */
    protected Node<T> last;

    /**
     * 链表长度
     */
    protected int size;

    /**
     * 记录修改次数，fast-fail机制
     */
    protected int modCount = 0;

    /**
     * 初始化链表
     *
     * @return
     */
    @Override
    public List<T> initList() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.modCount = 0;
        return this;
    }

    /**
     * 链表是否为空
     */
    @Override
    public Boolean listEmpty() {
        return this.size == 0;
    }

    /**
     * 链表长度
     *
     * @return int
     */
    @Override
    public int length() {
        return this.size;
    }

    /**
     * 清空连标
     */
    @Override
    public void clear() {
        Node<T> prv = first;
        Node<T> next;
        while (null != prv) {
            next = prv.next;
            unlink(prv, next);
            prv = next;
        }
        clearNode(this.first);
        clearNode(this.last);
        this.size = 0;
    }

    /**
     * 清空Node
     *
     * @param node
     */
    protected void clearNode(Node<T> node) {
        if (null == node) return;
        node.data = null;
        node.next = null;
        node = null;
    }

    /**
     * 拆分链表
     *
     * @param prv
     * @param node
     * @return
     */
    protected T unlink(Node<T> prv, Node<T> node) {
        if (null == node) {
            prv.next = null;
            return null;
        }
        final Node<T> next = node.next;
        if (null == prv) {
            this.first = next;
        } else {
            prv.next = next;
        }
        T data = node.data;
        node.data = null;
        node.next = null;
        return data;
    }

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        return null == node ? null : node.data;
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

    @Override
    public int locateElem(T element) {
        if (null == first) return -1;

        Node<T> node = first;
        for (int i = 0; !nodeFinished(node); i++) {
            if (Objects.equals(node.data, element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size) {
            throw new OutOfRangeException("index out of range for linked list");
        }
    }


    /**
     * 尾插
     *
     * @param data 节点数据
     */
    protected int insertLast(T data) {
        if (null == this.last) return insertFirst(data);
        // 1.记录末尾节点 2.last指向新创建的node 3.老node指向新node
        Node<T> oldNode = this.last;
        this.last = new Node<>(data, null);
        oldNode.next = this.last;

        this.size++;
        return 0;
    }

    /**
     * 头插
     *
     * @param data 节点数据
     */
    protected int insertFirst(T data) {
        Node<T> oldNode = this.first;
        this.first = new Node<>(data, oldNode);
        this.size++;
        if (1 == size) this.last = this.first;
        return 0;
    }

    /**
     * 插入index位置
     *
     * @param index 插入位置
     * @param e     插入元素
     * @return index
     */
    @Override
    public int insert(int index, T e) {
        indexCheck(index);
        modCount++;

        if (index == 0) return insertFirst(e);

        if (index == size - 1) return insertLast(e);

        Node<T> oldNodePrv = getNode(index - 1);
        assert oldNodePrv != null;
        Node<T> oldNode = oldNodePrv.next;
        oldNodePrv.next = new Node<>(e, oldNode);
        return index;
    }

    /**
     * 插入元素
     *
     * @param e 元素
     */
    @Override
    public void insert(T e) {
        modCount++;
        insertLast(e);
    }

    /**
     * 删除index元素
     *
     * @param index index位置
     * @return
     */
    @Override
    public T remove(int index) {
        indexCheck(index);
        Node<T> removePrv = getNode(index - 1);
        assert removePrv != null;
        modCount++;
        return unlink(removePrv, removePrv.next);
    }

    /**
     * 判断是否遍历完成
     *
     * @return
     */
    protected abstract boolean nodeFinished(Node<T> node);

    /**
     * Node class for the linked list
     *
     * @param <T>
     */
    protected class Node<T> {
        protected T data;
        protected Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
