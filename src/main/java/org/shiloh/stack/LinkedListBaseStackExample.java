package org.shiloh.stack;

import java.util.Scanner;

/**
 * 基于单向链表实现栈的例子
 *
 * @author shiloh
 * @date 2022/7/14 21:49
 */
public class LinkedListBaseStackExample {
    public static void main(String[] args) {
        // 测试基于单向链表实现的栈
        final LinkedListBaseStack linkedListBaseStack = new LinkedListBaseStack();
        final Scanner scanner = new Scanner(System.in);
        // 接收用户输入的指令
        String command;
        // 判断是否继续循环
        boolean loop = true;
        while (loop) {
            System.out.println("list: 列出栈中所有的数据");
            System.out.println("count: 统计栈中数据的个数");
            System.out.println("pop: 数据出栈");
            System.out.println("push: 数据入栈");
            System.out.println("exit: 退出程序");
            System.out.println("请输入上方指令中的一个进行测试：");

            command = scanner.next();
            switch (command) {
                case "list":
                    try {
                        linkedListBaseStack.list();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "count":
                    final int size = linkedListBaseStack.size();
                    System.out.printf("栈中一共有 %d 个节点数据\n", size);
                    break;
                case "pop": {
                    try {
                        final Node node = linkedListBaseStack.pop();
                        System.out.printf("出栈的节点数据内容为：%d\n", node.getData());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "push":
                    System.out.println("请输入一个整数数据，用于入栈");
                    final int data = scanner.nextInt();
                    linkedListBaseStack.push(new Node(data));
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("指令无法识别，请重新输入~");
                    break;
            }
        }
        System.out.println("程序已成功退出~");
    }
}

/**
 * 基于单向链表实现的栈数据结构
 *
 * @author shiloh
 * @date 2022/7/14 21:52
 */
class LinkedListBaseStack {
    /**
     * 栈中的数据个数
     */
    private int size;

    /**
     * 头节点
     */
    private Node first;

    /**
     * 判断栈是否为空
     *
     * @author shiloh
     * @date 2022/7/14 21:54
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取栈中的数据个数
     */
    public int size() {
        return this.size;
    }

    /**
     * 数据入栈
     *
     * @param node 待入栈的数据
     * @author shiloh
     * @date 2022/7/14 21:55
     */
    public void push(Node node) {
        // 借助临时变量缓存头节点
        final Node temp = this.first;
        this.first = node;
        this.first.setNext(temp);
        // 增加 size
        this.size++;
    }

    /**
     * 数据出栈
     *
     * @return 栈顶数据对象
     * @author shiloh
     * @date 2022/7/14 21:57
     */
    public Node pop() {
        // 栈如果为空，则给出错误提示
        if (this.isEmpty()) {
            throw new RuntimeException("栈为空，无法获取数据");
        }

        // 缓存栈顶数据
        final Node temp = this.first;
        // 将栈顶的下一个节点放到栈顶
        this.first = this.first.getNext();
        // 减少 size
        this.size--;

        return temp;
    }

    /**
     * 输出栈中的数据信息
     *
     * @author shiloh
     * @date 2022/7/14 22:00
     */
    public void list() {
        // 栈如果为空，则给出错误提示
        if (this.isEmpty()) {
            throw new RuntimeException("栈为空，无法输出");
        }

        // 借助临时变量，从栈顶开始遍历输出
        Node temp = this.first;
        while (temp != null) {
            System.out.printf("节点数据：%d\n", temp.getData());
            // 移动到下一个节点
            temp = temp.getNext();
        }
    }
}

/**
 * 链表节点类
 *
 * @author shiloh
 * @date 2022/7/14 21:51
 */
class Node {
    /**
     * 节点中的数据
     */
    private final int data;

    /**
     * 指向下一个节点的引用变量
     */
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
