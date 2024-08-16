package com.togally.structure.list.linked;

/**
 * 单向链表
 *
 * @param <T>
 */
public class UndirectLinkedList<T> extends AbstractLinkedList<T> {
    public UndirectLinkedList() {
        super.initList();
    }

    /**
     * 当单向链表遍历结束时最后一个node的next为null
     *
     * @param node 节点
     * @return boolean
     */
    @Override
    protected boolean nodeFinished(Node<T> node) {
        return null == node;
    }
}
