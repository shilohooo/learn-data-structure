package org.shiloh.queue;

import java.util.Scanner;

/**
 * 基于数组实现的环形队列示例
 *
 * @author shiloh
 * @date 2022/6/14 22:37
 */
public class CircleArrayQueueExample {
    public static void main(String[] args) {
        // 测试环形队列是否能正常使用
        // 创建一个队列，将最大容量设置为4，队列中的有效数据个数应为3，因为需要给队列预留一个空间
        final CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
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
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    // 添加数据到队列中
                    System.out.println("请输入一个整数：");
                    final int val = scanner.nextInt();
                    circleArrayQueue.add(val);
                    break;
                case 'g':
                    try {
                        // 获取队列头部数据
                        final int res = circleArrayQueue.get();
                        System.out.printf("取出的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        // 查看队列头部数据
                        final int res = circleArrayQueue.peek();
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
 * 基于数组实现的环形队列
 *
 * @author shiloh
 * @date 2022/6/14 22:37
 */
class CircleArrayQueue {
    /**
     * 队列最大容量，即表示数组的最大容量
     */
    private final int maxSize;

    /**
     * 队列头部指针，始终指向队列第一个元素，即 front = 0
     */
    private int front;

    /**
     * 队列尾部指针，始终指向队列最后一个元素的下一个位置，初始值为 0
     * <p>
     * 此处这么做是希望队列始终预留一个空间
     */
    private int rear;

    /**
     * 存放数据的数组
     */
    private final int[] data;

    /**
     * 构造器
     *
     * @author shiloh
     * @date 2022/6/14 22:40
     */
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.data = new int[maxSize];
        System.out.printf("初始化后，头指针的位置为：%d\n", this.front);
        System.out.printf("初始化后，尾指针的位置为：%d\n", this.rear);
    }

    /**
     * 判断队列是否已满
     *
     * @author shiloh
     * @date 2022/6/14 22:41
     */
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    /**
     * 判断队列是否为空
     *
     * @author shiloh
     * @date 2022/6/14 22:52
     */
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * 添加数据到队列中
     *
     * @author shiloh
     * @date 2022/6/14 22:56
     */
    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        this.data[this.rear] = value;
        // 将尾指针后移，这里需要考虑取模，因为 rear 需要始终指向队列最后一个元素的下一个位置
        this.rear = (this.rear + 1) % this.maxSize;
        System.out.printf("添加元素后，头指针的位置为：%d\n", this.front);
        System.out.printf("添加元素后，尾指针的位置为：%d\n", this.rear);
    }

    /**
     * 获取数据
     *
     * @author shiloh
     * @date 2022/6/14 22:58
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        // 先将队列头部元素缓存到一个临时变量中
        final int value = this.data[this.front];
        // 将头指针后移，这里也需要考虑取模
        this.front = (this.front + 1) % this.maxSize;
        System.out.printf("取出元素后，头指针的位置为：%d\n", this.front);
        System.out.printf("取出元素后，尾指针的位置为：%d\n", this.rear);

        return value;
    }

    /**
     * 显示队列信息
     *
     * @author shiloh
     * @date 2022/6/14 23:18
     */
    public void showQueue() {
        if (this.isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 此处不能直接遍历数组，需要计算出有效数据个数，从头指针所处位置开始遍历
        for (int i = this.front; i < this.front + this.size(); i++) {
            // 计算实际下标
            final int index = i % this.maxSize;
            System.out.printf("data[%d] = %d\n", index, this.data[index]);
        }
    }

    /**
     * 获取队列中有效数据的个数
     *
     * @return 队列中有效数据的个数
     * @author shiloh
     * @date 2022/6/14 23:11
     */
    public int size() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }

    /**
     * 显示队列头部元素
     *
     * @author shiloh
     * @date 2022/6/14 23:22
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return this.data[this.front];
    }
}