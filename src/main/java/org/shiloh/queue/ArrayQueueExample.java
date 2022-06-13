package org.shiloh.queue;

import java.util.Scanner;

/**
 * 基于数组实现队列示例
 *
 * @author shiloh
 * @date 2022/6/13 22:24
 */
public class ArrayQueueExample {
    public static void main(String[] args) {
        // 测试队列是否能正常使用
        // 创建一个队列
        final ArrayQueue arrayQueue = new ArrayQueue(3);
        // 接收用户输入，将用户输入的数字添加到队列中
        char key;
        final Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列数据");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列中");
            System.out.println("g(get)：获取队列头部数据");
            System.out.println("p(peek)：查看队列头部数据");
            // 接收用户输入的菜单选项
            key = scanner.next()
                    .charAt(0);
            switch (key) {
                case 's':
                    // 显示队列数据
                    arrayQueue.printQueue();
                    break;
                case 'a':
                    // 添加数据到队列中
                    System.out.println("请输入一个整数：");
                    final int val = scanner.nextInt();
                    arrayQueue.add(val);
                    break;
                case 'g':
                    try {
                        // 获取队列头部数据
                        final int res = arrayQueue.getVal();
                        System.out.printf("取出的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        // 查看队列头部数据
                        final int res = arrayQueue.peek();
                        System.out.printf("队列头部数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    // 退出程序
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

/**
 * 基于数组的队列
 *
 * @author shiloh
 * @date 2022/6/13 22:25
 */
class ArrayQueue {
    /**
     * 队列最大容量，即表示数组的最大容量
     */
    private final int maxSize;

    /**
     * 队列头部指针
     */
    private int front;

    /**
     * 队列尾部指针
     */
    private int rear;

    /**
     * 存放数据的数组
     */
    private final int[] data;

    /**
     * 构造器
     * <p>
     * 当前还没有存储数据到队列中，因此：
     * 头部指针指向第一个元素的前一个位置
     * 尾部指针指向队列尾部，即队列最后一个元素的位置
     *
     * @param maxSize 队列最大容量
     * @author shiloh
     * @date 2022/6/13 22:28
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        // 初始化数组
        this.data = new int[maxSize];
        // 初始化头部、尾部指针
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 判断队列是否已满
     *
     * @author shiloh
     * @date 2022/6/13 22:33
     */
    public boolean isFull() {
        return this.rear == (this.maxSize - 1);
    }

    /**
     * 判断队列是否为空
     *
     * @author shiloh
     * @date 2022/6/13 22:34
     */
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    /**
     * 添加数据到队列中
     *
     * @param val 数据
     * @author shiloh
     * @date 2022/6/13 22:36
     */
    public void add(int val) {
        // 如果队列满了则不能添加
        if (this.isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }

        // 后移尾部指针
        this.rear++;
        // 将数据存储到数组中
        this.data[this.rear] = val;
    }

    /**
     * 获取队列数据，即数据出队
     *
     * @return 位于队列头部的数据
     * @author shiloh
     * @date 2022/6/13 22:40
     */
    public int getVal() {
        // 如果队列为空，则不能获取数据，抛出异常交给客户端处理
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空，不能获取数据");
        }

        // 后移头部指针，将其指向队列头部
        this.front++;
        return this.data[this.front];
    }

    /**
     * 打印队列信息
     *
     * @author shiloh
     * @date 2022/6/13 22:46
     */
    public void printQueue() {
        // 如果队列为空则无需打印
        if (this.isEmpty()) {
            System.out.println("队列为空，没有任何数据~");
            return;
        }

        // 打印队列中的数据
        for (int i = this.front + 1; i <= this.maxSize - 1; i++) {
            final int val = this.data[i];
            System.out.printf("data[%d] = %d\n", i, val);
        }
    }

    /**
     * 查看队列头部数据，并非获取
     *
     * @return 队列头部数据
     * @author shiloh
     * @date 2022/6/13 22:49
     */
    public int peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空，无法获取头部数据");
        }

        return this.data[this.front + 1];
    }
}

