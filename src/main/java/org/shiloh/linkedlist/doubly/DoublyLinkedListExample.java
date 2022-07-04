package org.shiloh.linkedlist.doubly;

import org.shiloh.linkedlist.doubly.DoublyLinkedList.DoublyHeroNode;

/**
 * 双向链表 Demo
 *
 * @author shiloh
 * @date 2022/7/4 22:36
 */
public class DoublyLinkedListExample {
    public static void main(String[] args) {
        // 双向链表测试

        // 创建节点数据
        final DoublyHeroNode doublyHeroNode1 = new DoublyHeroNode(1, "宋江", "及时雨");
        final DoublyHeroNode doublyHeroNode2 = new DoublyHeroNode(2, "卢俊义", "玉麒麟");
        final DoublyHeroNode doublyHeroNode3 = new DoublyHeroNode(3, "吴用", "智多星");
        final DoublyHeroNode doublyHeroNode4 = new DoublyHeroNode(4, "林冲", "豹子头");

        // 实例化双向链表对象
        final DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        // 添加节点
        // doublyLinkedList.add(doublyHeroNode1);
        // doublyLinkedList.add(doublyHeroNode2);
        // doublyLinkedList.add(doublyHeroNode3);
        // doublyLinkedList.add(doublyHeroNode4);

        // 测试按编号顺序添加节点
        doublyLinkedList.addByNo(doublyHeroNode2);
        doublyLinkedList.addByNo(doublyHeroNode4);
        doublyLinkedList.addByNo(doublyHeroNode3);
        doublyLinkedList.addByNo(doublyHeroNode1);

        // 打印链表数据
        // System.out.println("原链表：");
        doublyLinkedList.list();

        // 修改节点测试
        // 新建一个待修改的节点
        // final DoublyHeroNode doublyHeroNode = new DoublyHeroNode(4, "公孙胜", "入云龙");
        // doublyLinkedList.updateByNo(doublyHeroNode);

        // 打印修改后的链表
        // System.out.println("修改后的链表：");
        // doublyLinkedList.list();

        // 删除指定的节点
        // doublyLinkedList.deleteByNo(3);
        // System.out.println("删除某个节点后的链表：");
        // doublyLinkedList.list();
    }
}