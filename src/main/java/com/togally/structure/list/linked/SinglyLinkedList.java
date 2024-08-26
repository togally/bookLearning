package com.togally.structure.list.linked;

import com.togally.structure.list.linked.node.Node;

/**
 * 单向链表
 *
 * @param <T>
 */
public class SinglyLinkedList<T,N extends Node<T>> extends AbstractLinkedList<T,N> {
    public SinglyLinkedList() {
        super.initList();
    }

    /**
     * 当单向链表遍历结束时最后一个node的next为null
     *
     * @param node 节点
     * @return boolean
     */
    @Override
    protected boolean nodeFinished(N node) {
        return null == node;
    }
}
