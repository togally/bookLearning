package com.togally.structure.list;

/**
 * 线性表定义接口
 */
public interface List<T> {

    /**
     * 初始化线性表
     *
     * @return list
     */
    List<T> initList();

    /**
     * 线性表为空返回true 否则返回false
     *
     * @return boolean true false
     */
    Boolean listEmpty();

    /**
     * 清空线性表
     */
    void clear();

    /**
     * 获取下表为i的元素
     *
     * @param index 下标
     * @return T
     */
    T get(int index);

    /**
     * 查找线性表中是否有元素element
     *
     * @param element 目标元素
     * @return 0 失败 index 目标元素下标
     */
    int locate(T element);

    /**
     * 位置index插入元素e
     *
     * @param index 位置
     * @param e     元素
     * @return 插入后的下标
     */
    int insert(int index, T e);

    /**
     * 删除index位置的数据
     *
     * @param index index位置
     * @return T被删除的元素
     */
    T remove(int index);

    /**
     * 获取线性表的元素个数
     *
     * @return 线性表的元素个数
     */
    int length();
}
