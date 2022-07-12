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
        oneWayCircularLinkedList.add(25);
        // 输出链表内容
        oneWayCircularLinkedList.print();
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