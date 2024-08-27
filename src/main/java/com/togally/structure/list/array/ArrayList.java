package com.togally.structure.list.array;

import com.togally.exception.OutOfRangeException;
import com.togally.structure.list.List;

import java.util.Arrays;
import java.util.Objects;


public class ArrayList<E> implements List<E> {
    /**
     * 初始容量
     */
    private static final Object[] DEFAULT_EMPTY_DATA = {};

    /**
     * 默认初始容量
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 元素数组最大值
     */
    private static final int MAX_LENGTH = Integer.MAX_VALUE - 8;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 数组
     */
    transient Object[] elementData;

    /**
     * 数组长度
     */
    private int length;

    /**
     * 线性表长度
     */
    private int size;

    /**
     * 修改次数
     */
    private int modCount;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayList(int initCapacity) {
        if (initCapacity > 0) {
            this.elementData = new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.elementData = DEFAULT_EMPTY_DATA;
        } else {
            throw new IllegalArgumentException("init capacity is wrong : " + initCapacity);
        }
        this.length = initCapacity;
    }

    @Override
    public List<E> initList() {
        return null;
    }

    @Override
    public Boolean listEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        modCount++;
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elemData(elementData[index]);
    }

    @Override
    public int locateElem(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int insert(int index, E e) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(index);
        size++;
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        return index;
    }

    @Override
    public void insert(E e) {
        ensureExplicitCapacity(size + 1);
        elementData[size++] = e;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        ensureCapacityInternal(index);
        E oldElem = get(index);
        int moveNum = length - index - 1;
        if (moveNum > 0)
            System.arraycopy(elementData, index + 1, elementData, index, moveNum);
        elementData[--size] = null;
        return oldElem;
    }

    @Override
    public int length() {
        return this.size;
    }

    /**
     * 越界校验
     *
     * @param index 下标
     */
    private void rangeCheck(int index) {
        if (index > size) {
            throw new OutOfRangeException("arrayList index out of range ,index = " + index);
        }
    }

    /**
     * 范型强转
     *
     * @param elem 元素
     * @return E
     */
    @SuppressWarnings("unchecked")
    private E elemData(Object elem) {
        return null == elem ? null : (E) elem;
    }

    /**
     * 保证容量够用
     *
     * @param minCapacity 最小容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    /**
     * 扩容方法
     *
     * @param minCapacity 最小容量
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        if (minCapacity - length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩容
     *
     * @param minCapacity 最小容量
     */
    private void grow(int minCapacity) {
        int oldLength = this.length;
        int newLength = oldLength + oldLength >> 1;
        if (newLength - minCapacity < 0) {
            newLength = minCapacity;
        } else if (newLength - MAX_LENGTH > 0) {
            newLength = hugeCapacity(minCapacity);
        }
        this.elementData = Arrays.copyOf(elementData, newLength);
        this.length = newLength;
    }

    /**
     * 大容量
     *
     * @param minCapacity 最小容量
     * @return minCapacity
     */
    private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return minCapacity - Integer.MAX_VALUE < 0 ? minCapacity : Integer.MAX_VALUE;
    }

    /**
     * 计算容量
     *
     * @param elementData 元素数组
     * @param minCapacity 最小容量
     * @return capacity
     */
    private int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    /**
     * 新增范围查询
     *
     * @param index 下标
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new OutOfRangeException("out of range exception when arrayList add for index = " + index);
        }
    }
}
