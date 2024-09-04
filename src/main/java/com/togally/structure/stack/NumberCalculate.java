package com.togally.structure.stack;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NumberCalculate {
    public static final Map<String, Integer> priorityMap = new HashMap<>();

    static {
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
    }

    public static void main(String[] args) {
        System.out.println(cal("9 + ( 3 - 1 ) * 3 + 8 / 2"));
    }

    /**
     * 计算方法
     * @param expression 中缀表达式
     * @return double
     */
    public static double cal(String expression) {
        // 转后缀表达式
        String sufExpression = convertSuf(expression);
        // 执行后缀表达式
        return executeSuf(sufExpression);
    }

    /**
     * 中缀转后缀
     *
     * @param expression 表达式
     * @return 后缀表达式
     */
    private static String convertSuf(String expression) {
        IStack<String> stack = new ArrayStack<>();
        String[] items = expression.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String item : items) {
            if (NumberUtil.isNumber(item)) {
                sb.append(item).append(" ");
                continue;
            }

            // 括号优先级处理
            if (StrUtil.equals("(", item)) {
                stack.push(item);
                continue;
            } else if (StrUtil.equals(")", item)) {
                String stackTop;
                while (!StrUtil.equals(stackTop = stack.pop(), "(")) {
                    sb.append(stackTop).append(" ");
                }
                continue;
            }

            // 运算符号优先级
            String top = stack.getTop();
            if (comparePriority(top, item) > 0) {
                while (stack.stackLength() > 0) {
                    sb.append(stack.pop()).append(" ");
                }
            }
            stack.push(item);
        }

        // 将栈尾符号输出
        while (stack.stackLength() > 0 ){
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    /**
     * 比较表达式优先级
     *
     * @param item1 比较项1
     * @param item2 比较项2
     * @return
     */
    private static int comparePriority(String item1, String item2) {
        return Optional.ofNullable(priorityMap.get(item1)).orElse(0) - Optional.ofNullable(priorityMap.get(item2)).orElse(0);
    }

    /**
     * 执行后缀表达式
     * @param sufExpression 表达式
     * @return double
     */
    private static double executeSuf(String sufExpression) {
        IStack<Double> calStack = new ArrayStack<>();
        for (String item : sufExpression.split(" ")) {
            if (NumberUtil.isNumber(item)){
                calStack.push(Double.valueOf(item));
                continue;
            }
            Double next = calStack.pop();
            Double pre = calStack.pop();
            calStack.push(calDouble(pre,next,item));
        }
        return calStack.pop();
    }

    /**
     * 计算double
     * @param pre
     * @param next
     * @param item
     * @return
     */
    private static Double calDouble(Double pre, Double next, String item) {
        switch (item){
            case "+" : return pre + next;
            case "-" : return pre - next;
            case "*" : return pre * next;
            case "/" : return pre / next;
        }
        return null;
    }
}
