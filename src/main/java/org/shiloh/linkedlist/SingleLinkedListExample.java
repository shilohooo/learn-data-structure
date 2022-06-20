package org.shiloh.linkedlist;

/**
 * 单链表应用实例
 *
 * @author shiloh
 * @date 2022/6/20 23:05
 */
public class SingleLinkedListExample {
    public static void main(String[] args) {
        // 单向链表测试
        // 创建节点
        final HeroNode heroNode01 = new HeroNode(1, "宋江", "及时雨");
        final HeroNode heroNode02 = new HeroNode(2, "卢俊义", "玉麒麟");
        final HeroNode heroNode03 = new HeroNode(3, "吴用", "智多星");
        final HeroNode heroNode04 = new HeroNode(4, "林冲", "豹子头");

        // 实例化链表
        final SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加节点
        singleLinkedList.add(heroNode01);
        singleLinkedList.add(heroNode04);
        singleLinkedList.add(heroNode02);
        singleLinkedList.add(heroNode03);
        // 显示链表
        singleLinkedList.list();
    }
}

/**
 * 定义单向链表，用于管理水浒传英雄人物排行榜
 *
 * @author shiloh
 * @date 2022/6/20 23:11
 */
class SingleLinkedList {
    /**
     * 定义头节点，无需存放具体的数据
     */
    private final HeroNode HEAD = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表中（不考虑英雄人物编号顺序）
     * <p>
     * 实现思路：
     * <ol>
     *     <li>找出当前链表中的最后一个节点</li>
     *     <li>将最后一个节点的 next 引用 指向新的节点</li>
     * </ol>
     *
     * @param newNode 新的节点
     * @author shiloh
     * @date 2022/6/20 23:16
     */
    public void add(HeroNode newNode) {
        // 在不修改头节点的情况下，使用临时变量
        HeroNode tempNode = HEAD;
        // 遍历链表，找到最后一个节点
        while (true) {
            // 当前遍历节点的 next 引用为空时，说明已经遍历到最后一个节点
            final HeroNode nextNode = tempNode.getNext();
            if (nextNode == null) {
                break;
            }
            // 否则将临时节点指向下一个节点
            tempNode = nextNode;
        }
        // 当循环结束后，tempNode 指向最后一个节点
        // 此时，将最后一个节点的 next 引用指向新的节点即可
        tempNode.setNext(newNode);
    }

    /**
     * 输出链表信息
     *
     * @author shiloh
     * @date 2022/6/20 23:23
     */
    public void list() {
        // 如果链表为空，则无需遍历
        if (this.isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        // 不修改头节点的情况下，借助临时变量来遍历链表
        HeroNode tempNode = HEAD.getNext();
        // 遍历链表
        while (tempNode != null) {
            // 输出节点信息
            System.out.println(tempNode);
            // 将临时节点指向下一个节点
            tempNode = tempNode.getNext();
        }
    }

    /**
     * 判断链表是否为空（头节点不存储数据）
     *
     * @return 如果链表为空则返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/6/20 23:25
     */
    public boolean isEmpty() {
        return HEAD.getNext() == null;
    }
}

/**
 * 定义 HeroNode 类，每个 HeroNode 对象就是一个节点
 *
 * @author shiloh
 * @date 2022/6/20 23:06
 */
class HeroNode {
    /**
     * 编号
     */
    private final Integer no;

    /**
     * 姓名
     */
    private final String name;

    /**
     * 昵称
     */
    private final String nickname;

    /**
     * 指向下一个节点的引用
     */
    private HeroNode next;

    /**
     * 构造器
     *
     * @param no       编号
     * @param name     姓名
     * @param nickname 昵称
     * @author shiloh
     * @date 2022/6/20 23:09
     */
    public HeroNode(Integer no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}