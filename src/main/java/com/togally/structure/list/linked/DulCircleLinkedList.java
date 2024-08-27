package com.togally.structure.list.linked;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.linked.node.DulNode;
import com.togally.structure.list.linked.node.Node;


/**
 * 双向循环链表
 *
 * @param <T>
 */
public class DulCircleLinkedList<T> extends CircleLinkedList<T, DulNode<T>> {
    @Override
    protected T clearNode(DulNode<T> node) {
        super.clearNode(node);
        if (null == node) return null;
        T data = node.data;
        node.prv = null;
        node.next = null;
        node.data = null;
        node = null;
        return data;
    }

    @Override
    protected T unlink(DulNode<T> prv, DulNode<T> node) {
        if (null == node) return null;

        DulNode<T> next = node.next;
        prv.next = next;
        next.prv = prv;
        if (nodeFinished(prv.next)) this.last = prv;

        return clearNode(node);
    }

    @Override
    protected int insertFirst(T data) {
        modCount++;
        size++;
        DulNode<T> next = this.first;
        this.first = new DulNode<>(data, this.last, this.first);
        // 如果只有一个元素则尾指针也指向头部
        if (this.last == null){
            this.last = this.first;
        } else {
            // 尾节点的下一节点优先指向本节点
            this.last.next = this.first;
        }

        if (null != next){
             next.prv = this.first;
        }
        return 0;
    }

    @Override
    protected int insertLast(T data) {
        modCount++;
        DulNode<T> prv = this.last;
        if (null == prv) return insertFirst(data);

        this.last = new DulNode<>(data, prv, this.first);
        prv.next = this.last;
        this.first.prv = this.last;
        return size++;
    }

    @Override
    public int insert(int index, T e) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("index" + index + "is out of range when insert into DulCircleLinkedList");
        }

        if (index == 0) return insertFirst(e);

        if (index == size - 1) return insertLast(e);

        // 执行插入操作
        size++;
        DulNode<T> prv = getNode(index);
        DulNode<T> next = prv.next;
        DulNode<T> node = new DulNode<>(e, prv, next);
        prv.next = node;
        next.prv = node;
        return index;
    }

    @Override
    protected DulNode<T> newInstance(T data, DulNode<T> next) {
        return new DulNode<>(data, next);
    }

    @Override
    public CircleLinkedList<T, DulNode<T>> union(CircleLinkedList<T, DulNode<T>> other) {
        this.modCount++;
        // 节点尾部和新节点头部想来哦你
        this.last.next = other.first;
        other.first.prv = this.last;

        // 新节点尾部和本节点头部相连
        other.last.next = this.first;
        this.first.prv = other.last;

        this.size += other.size;
        return this;
    }

    @Override
    public void clear() {
        DulNode<T> prv = first;
        DulNode<T> next;
        while (null != prv) {
            next = prv.getNext();
            unlink(prv, next);
            prv = next;
        }
        clearNode(this.first);
        clearNode(this.last);
        this.first = null;
        this.last = null;
        this.size = 0;
        this.modCount = 0;
    }
}
