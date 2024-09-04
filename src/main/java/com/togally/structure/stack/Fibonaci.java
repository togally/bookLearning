package com.togally.structure.stack;

/**
 * 斐波那契函数
 */
public class Fibonaci {

    public static int fib(int index) {
        if (index < 2) {
            return index == 0 ? 0 : 1;
        }
        return fib(index - 1) + fib(index - 2);
    }

    public static void main(String[] args) {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(20));
    }
}
