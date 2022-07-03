package org.shiloh.linkedlist.stack;

import java.util.Stack;

/**
 * 演示例子 - 数据结构：栈
 *
 * @author shiloh
 * @date 2022/7/3 22:02
 */
public class StackDemo {
    public static void main(String[] args) {
        final Stack<String> stack = new Stack<>();
        // 压栈
        stack.push("jack");
        stack.push("tom");
        stack.push("smith");

        // 出栈（先进后出）
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
