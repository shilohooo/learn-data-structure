package org.shiloh.stack;

import java.util.Scanner;

/**
 * 代码示例：基础数据实现的栈
 *
 * @author shiloh
 * @date 2022/7/13 23:00
 */
public class ArrayBaseStackExample {
    public static void main(String[] args) {
        // 栈功能测试
        // 创建一个栈实例
        final ArrayBaseStack arrayBaseStack = new ArrayBaseStack(4);
        String key;
        // 控制是否退出循环的标志
        boolean loop = true;
        final Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：打印栈内容");
            System.out.println("exit：退出程序");
            System.out.println("push：添加数据到栈中（入栈）");
            System.out.println("pop：从栈中取出数据（出栈）");

            System.out.println("请输入指令：");

            key = scanner.next();

            switch (key) {
                case "show": {
                    try {
                        arrayBaseStack.print();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "push": {
                    System.out.println("请输入一个整数：");
                    final int val = scanner.nextInt();
                    try {
                        arrayBaseStack.push(val);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "pop": {
                    try {
                        final int val = arrayBaseStack.pop();
                        System.out.printf("从栈中取出的数据为：%d\n", val);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("未知指令，请重新输入");
                    break;
            }

            System.out.println();

        }
        System.out.println();
        System.out.println("程序已退出");
    }
}

/**
 * 基于数组的栈
 *
 * @author shiloh
 * @date 2022/7/13 23:00
 */
class ArrayBaseStack {
    /**
     * 栈的大小
     */
    private final int maxSize;

    /**
     * 存储数据的容器
     */
    private final int[] data;

    /**
     * 指向栈顶的 top，初始化为 -1
     */
    private int top = -1;

    public ArrayBaseStack(int maxSize) {
        this.maxSize = maxSize;
        this.data = new int[this.maxSize];
    }

    /**
     * 判断栈是否满了
     *
     * @return 如果栈满了就返回 {@code true}, 否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/13 23:02
     */
    public boolean isFull() {
        return this.top == (this.maxSize - 1);
    }

    /**
     * 判断栈是否为空
     *
     * @return 如果为空就返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/13 23:03
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 数据入栈
     *
     * @author shiloh
     * @date 2022/7/13 23:04
     */
    public void push(int val) {
        // 栈满则给出错误提示
        if (this.isFull()) {
            throw new RuntimeException("栈满，数据入栈失败");
        }

        // 数据入栈
        this.top++;
        this.data[this.top] = val;
    }

    /**
     * 数据出栈，将栈顶的数据返回给客户端
     *
     * @return 栈顶数据
     * @author shiloh
     * @date 2022/7/13 23:05
     */
    public int pop() {
        // 如果栈空，则给出错误提示
        if (this.isEmpty()) {
            throw new RuntimeException("栈空，无法获取任何数据");
        }

        // 数据出栈
        final int val = this.data[this.top];
        this.top--;
        return val;
    }

    /**
     * 输出栈中的内容，注意：应该从栈顶开始
     *
     * @author shiloh
     * @date 2022/7/13 23:07
     */
    public void print() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈中没有数据，无法打印");
        }

        // 遍历输出栈中的内容
        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, this.data[i]);
        }
    }
}