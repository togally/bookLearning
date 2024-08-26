package com.togally.structure.list.linked;

import com.togally.structure.list.linked.node.DulNode;

/**
 * 双向单链表
 *
 * @param <T>
 */
public class DulSinglyLinkedList<T> extends SinglyLinkedList<T, DulNode<T>> {
    @Override
    protected T unlink(DulNode<T> prv, DulNode<T> node) {
        modCount++;
        if (null == node){
            return null;
        }
        // 后置节点
        DulNode<T> next = node.next;

        // 前后节点关联
        prv.next = next;
        if (null != next) next.prv = prv;

        // 节点置空
        T data = node.data;
        node.prv = null;
        node.next = null;
        node.data = null;

        size--;
        return data;
    }

    @Override
    protected int insertLast(T data) {
        modCount++;

        DulNode<T> prv = this.last;
        this.last = new DulNode<>(data, prv, null);

        // 原last指向新节点
        if (prv == null) {
            this.first = this.last;
        } else {
            prv.next = this.last;
        }
        return size++;
    }

    @Override
    protected int insertFirst(T data) {
        modCount++;

        DulNode<T> next = this.first;
        this.first = new DulNode<>(data, null, next);

        // 原first的前驱指向当前节点
        if (null == next) {
            this.last = this.first;
        } else {
            next.prv = this.first;
        }
        return 0;
    }

    @Override
    protected boolean nodeFinished(DulNode<T> node) {
        return super.nodeFinished(node);
    }

    @Override
    protected T clearNode(DulNode<T> node) {
        super.clearNode(node);
        if (null == node)return null;

        T data = node.data;
        node.data = null;
        node.prv = null;
        node.next = null;
        return data;
    }
}
