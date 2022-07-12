package org.shiloh.linkedlist.joseph;

/**
 * 约瑟夫问题解决示例
 *
 * @author shiloh
 * @date 2022/7/11 22:49
 */
public class JosephSolution {
    public static void main(String[] args) {
        // 单向环形链表构建测试
        final OneWayCircularLinkedList oneWayCircularLinkedList = new OneWayCircularLinkedList();
        // 添加指定数量的节点
        oneWayCircularLinkedList.add(125);
        // 输出链表内容
        // oneWayCircularLinkedList.print();

        // 约瑟夫问题：测试小男孩出圈
        // 预期出圈顺序：2 --> 4 --> 1 --> 5 --> 3
        oneWayCircularLinkedList.countBoy(10, 20, 25);
    }
}

/**
 * 单向环形链表
 *
 * @author shiloh
 * @date 2022/7/11 22:54
 */
class OneWayCircularLinkedList {
    /**
     * 头节点
     */
    private Boy first = new Boy(-1);

    /**
     * 添加指定数量的节点到链表中
     *
     * @param nums 新节点数量
     * @author shiloh
     * @date 2022/7/11 22:55
     */
    public void add(final int nums) {
        if (nums < 1) {
            System.out.println("要添加的节点数量不能小于1");
            return;
        }

        // 循环添加节点
        // 借助辅助变量构建环形链表
        Boy current = null;
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            // 如果是第一个节点，则需要与自己构成环状
            if (i == 1) {
                this.first = boy;
                this.first.setNext(this.first);
                // 将辅助变量指向第一个节点
                current = this.first;
                continue;
            }

            // 将当前节点的 next 引用指向新节点
            current.setNext(boy);
            // 将新节点的 next 引用指向第一个节点
            boy.setNext(this.first);
            // 将辅助变量移动到下一个创建的节点
            current = boy;
        }
    }

    /**
     * 输出链表内容
     *
     * @author shiloh
     * @date 2022/7/11 23:02
     */
    public void print() {
        // 判断链表是否为空
        if (this.isEmpty()) {
            System.out.println("链表为空，无需打印");
            return;
        }
        // 因为不能修改头节点，这里需要借助一个辅助变量来遍历链表
        Boy current = this.first;
        while (true) {
            System.out.printf("小男孩的编号为：%d\n", current.getNo());
            // 判断是否已经遍历完毕
            if (current.getNext() == this.first) {
                break;
            }
            // 将辅助变量往后移
            current = current.getNext();
        }
    }

    /**
     * 判断链表是否为空
     *
     * @return 链表为空时返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/11 23:03
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 约瑟夫问题：计算小男孩出圈的顺序
     *
     * @param startNo  表示从第几个小男孩开始计数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小男孩在圈内
     * @author shiloh
     * @date 2022/7/12 23:18
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (this.isEmpty() || startNo < 1 || startNo > nums) {
            System.out.println("参数输入错误，请重新输入");
            return;
        }

        // 创建辅助变量，用于帮助小男孩出圈
        // 通过遍历环形链表，让辅助变量事先指向环形链表的最后一个节点
        Boy helper = first;
        // 当辅助变量的 next 引用指向头节点时，说明辅助变量已经指向了环形链表中的最后一个节点
        while (helper.getNext() != this.first) {
            // 移动辅助变量，继续往下走
            helper = helper.getNext();
        }

        // 在开始计数前，将 first 和辅助变量移动 k - 1 次，使其指向计数起始节点
        for (int j = 0; j < startNo - 1; j++) {
            this.first = this.first.getNext();
            helper = helper.getNext();
        }

        // 开始计数：将 first 和辅助变量移动 m - 1 次，然后将 first 指向的节点从环形链表中删除，即出圈操作
        // 出圈为一个循环操作，直到圈中只有一个节点才停止
        // 当 first == 辅助变量时，说明圈中只有一个节点
        while (helper != this.first) {
            // 将 first 和辅助变量同时移动 countNum - 1 次
            for (int i = 0; i < countNum - 1; i++) {
                this.first = this.first.getNext();
                helper = helper.getNext();
            }

            // 走完后，first 指向的节点就是要删除的节点
            System.out.printf("编号为：%d 的小男孩出圈\n", this.first.getNo());
            this.first = this.first.getNext();
            // 将辅助变量的 next 引用指向 first，形成一个环状，继续循环
            helper.setNext(this.first);
        }

        System.out.printf("最后留在圈中的小男孩的编号为：%d\n", this.first.getNo());
    }
}

/**
 * 代表围坐成圈的小男孩的节点类
 *
 * @author shiloh
 * @date 2022/7/11 22:50
 */
class Boy {
    /**
     * 编号
     */
    private Integer no;

    /**
     * 指向下一个节点的引用，默认为 {@code null}
     */
    private Boy next;

    public Boy(Integer no) {
        this.no = no;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}