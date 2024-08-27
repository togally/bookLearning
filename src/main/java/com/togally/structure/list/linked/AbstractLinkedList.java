package com.togally.structure.list.linked;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.List;
import com.togally.structure.list.linked.node.Node;

import java.util.Objects;
public abstract class AbstractLinkedList<T,N extends Node<T>> implements List<T> {

    /**
     * 头节点
     */
    protected N first;

    /**
     * 尾节点
     */
    protected N last;

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
     * @return list
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
        N prv = first;
        N next;
        while (null != prv) {
            next = (N) prv.getNext();
            unlink(prv, next);
            prv = next;
        }
        clearNode(this.first);
        clearNode(this.last);
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * 清空Node
     *
     * @param node
     */
    protected T clearNode(N node) {
        if (null == node) return null;
        T data = node.data;
        node.data = null;
        node.next = null;
        node = null;
        return data;
    }

    /**
     * 拆分链表
     *
     * @param prv
     * @param node
     * @return
     */
    protected T unlink(N prv, N node) {
        if (null == node) {
            prv.next = null;
            return null;
        }

        // last前移
        if (node == this.last){
            this.last = prv;
        }

        final N next = (N) node.getNext();
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
        N node = getNode(index);
        return null == node ? null : node.data;
    }

    /**
     * 获取节点
     *
     * @param index
     * @return
     */
    protected N getNode(int index) {
        N node = first;
        if (null == first) return null;

        for (int j = 0; j < index; j++) {
            node = (N) node.getNext();
            if (null == node) return null;
        }
        return node;
    }

    @Override
    public int locateElem(T element) {
        if (null == first) return -1;

        N node = first;
        for (int i = 0; !nodeFinished(node); i++) {
            if (Objects.equals(node.data, element)) {
                return i;
            }
            node = (N) node.getNext();
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
        N oldNode = this.last;
        this.last = (N) Node.newInstance(data,null);
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
        N oldNode = this.first;
        this.first = (N) Node.newInstance(data,oldNode);
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

        if (index == size) return insertLast(e);

        size++;
        N oldNodePrv = getNode(index - 1);
        assert oldNodePrv != null;
        N oldNode = (N) oldNodePrv.getNext();
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
        N removePrv = getNode(index - 1);
        assert removePrv != null;
        modCount++;
        size--;
        return unlink(removePrv, (N) removePrv.next);
    }

    /**
     * 判断是否遍历完成
     *
     * @return
     */
    protected abstract boolean nodeFinished(N node);
}
