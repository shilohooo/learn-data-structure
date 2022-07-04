package org.shiloh.linkedlist.single;

import org.shiloh.linkedlist.single.SingleLinkedList.SingleHeroNode;

import java.util.Stack;

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
        final SingleHeroNode singleHeroNode01 = new SingleHeroNode(1, "宋江", "及时雨");
        final SingleHeroNode singleHeroNode02 = new SingleHeroNode(2, "卢俊义", "玉麒麟");
        final SingleHeroNode singleHeroNode03 = new SingleHeroNode(3, "吴用", "智多星");
        final SingleHeroNode singleHeroNode04 = new SingleHeroNode(4, "林冲", "豹子头");

        // 实例化链表
        final SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加节点
        // singleLinkedList.add(heroNode01);
        // singleLinkedList.add(heroNode04);
        // singleLinkedList.add(heroNode02);
        // singleLinkedList.add(heroNode03);

        // 测试按照英雄编号顺序添加
        singleLinkedList.addByNo(singleHeroNode01);
        singleLinkedList.addByNo(singleHeroNode04);
        singleLinkedList.addByNo(singleHeroNode02);
        singleLinkedList.addByNo(singleHeroNode03);

        // System.out.println("修改前的链表数据：");
        // singleLinkedList.list();
        //
        // // 测试修改节点信息
        // singleLinkedList.update(new HeroNode(4, "老林头", "豹子头~~"));
        //
        // // 测试插入编号已存在的节点
        // // singleLinkedList.addByNo(heroNode03);
        //
        // // 显示链表
        // System.out.println("修改后的链表数据：");
        // singleLinkedList.list();
        //
        // // 测试获取链表中的有效节点数量
        // final int length = getLength(singleLinkedList.getHead());
        // System.out.printf("链表中有效节点的数量：%d\n", length);
        //
        // // 测试删除节点
        // System.out.println("删除节点前的链表数据：");
        // singleLinkedList.list();
        //
        // singleLinkedList.deleteByNo(1);
        // // singleLinkedList.deleteByNo(4);
        // // singleLinkedList.deleteByNo(3);
        // singleLinkedList.deleteByNo(2);
        //
        // System.out.println("删除节点后的链表数据：");
        // singleLinkedList.list();
        //
        // // 测试获取倒数第 k 个节点
        // final int k = 1;
        // final HeroNode result = findLastIndexNode(singleLinkedList.getHead(), 2);
        // System.out.printf("倒数第 %d 个节点为：%s", k, result);

        // 测试反转链表
        // System.out.println("反转前的链表数据：");
        // singleLinkedList.list();
        //
        // reverseLink(singleLinkedList.getHead());
        // System.out.println("反转后的链表数据：");
        // singleLinkedList.list();

        // 测试逆序打印链表
        // System.out.println("原来的链表：");
        // singleLinkedList.list();

        // 逆序打印
        // System.out.println("逆序打印的链表：");
        // reversePrint(singleLinkedList.getHead());

        // 测试合并两个有序的链表
        // 创建链表B
        final SingleHeroNode singleHeroNode05 = new SingleHeroNode(5, "关胜", "大刀");
        final SingleHeroNode singleHeroNode06 = new SingleHeroNode(6, "秦明", "霹雳火");
        final SingleHeroNode singleHeroNode07 = new SingleHeroNode(7, "呼延灼", "双鞭");
        final SingleHeroNode singleHeroNode08 = new SingleHeroNode(8, "花荣", "小李广");
        final SingleLinkedList linkB = new SingleLinkedList();
        linkB.addByNo(singleHeroNode05);
        linkB.addByNo(singleHeroNode07);
        linkB.addByNo(singleHeroNode08);
        linkB.addByNo(singleHeroNode06);
        final SingleLinkedList newLink = merge(singleLinkedList.getHead(), linkB.getHead());
        // 输出合并后的链表
        System.out.println("合并后的有序链表为：");
        newLink.list();
    }

    /**
     * 面试题解答：求单链表中节点的个数（如果是带头节点，则需要去掉头节点，因为头节点并不存放数据）
     *
     * @param head 头节点
     * @return 单链表中有效节点的个数
     * @author shiloh
     * @date 2022/6/27 22:11
     */
    public static int getLength(SingleHeroNode head) {
        // 定义一个临时遍历，用于遍历链表
        SingleHeroNode current = head.getNext();
        if (current == null) {
            // 如果 current 为空，说明链表为空，直接返回 0 即可
            return 0;
        }

        int length = 0;
        // 遍历链表
        while (current != null) {
            // 节点数量递增
            length++;
            // 继续往下遍历
            current = current.getNext();
        }

        // 遍历结束后，返回节点数量
        return length;
    }

    /**
     * 面试题解答：查找单链表中的倒数第 k 个节点，思路如下：
     *
     * <ol>
     *     <li>编写一个方法，要求接收头节点和一个代表 k 的 index</li>
     *     <li>从头开始遍历链表，得到链表的总长度：length</li>
     *     <li>再从链表的第一个节点开始遍历，直接（length - k）个节点，即可找到要查找的节点</li>
     *     <li>如果找不到，则返回 {@code null}</li>
     * </ol>
     *
     * @param head  头节点
     * @param index 代表倒数第 k 个节点的索引
     * @return 如果找到了，则倒数第 k 个节点，否则返回 {@code null}
     * @author shiloh
     * @date 2022/6/27 22:23
     */
    public static SingleHeroNode findLastIndexNode(SingleHeroNode head, int index) {
        // 定义一个临时遍历，用于遍历链表
        SingleHeroNode tempNode = head.getNext();
        // 如果链表为空，则直接返回 null
        if (tempNode == null) {
            return null;
        }

        // 得到链表的总长度
        final int length = getLength(head);

        // 第二次遍历，从链表的第一个节点开始遍历，直接（length - index）个节点，即可找到要查找的节点
        // 先校验 index 是否合法
        if (index < 0 || index > length) {
            return null;
        }

        // 遍历
        for (int i = 0; i < (length - index); i++) {
            tempNode = tempNode.getNext();
        }

        return tempNode;
    }

    /**
     * 面试题解答：反转单链表，实现思路如下：
     *
     * <ol>
     *     <li>定义一个临时变量，用于遍历链表</li>
     *     <li>从头开始遍历链表，每遍历一个节点，就将其取出，并放到临时变量的最前端</li>
     *     <li>遍历完成后，将原先的头节点的 next 引用指向临时遍历的 next 引用</li>
     * </ol>
     *
     * @param head 头节点
     * @author shiloh
     * @date 2022/6/30 22:23
     */
    public static void reverseLink(SingleHeroNode head) {
        // 如果链表为空，或者只包含一个有效节点，则无需遍历
        SingleHeroNode firstNode = head.getNext();
        if (firstNode == null || firstNode.getNext() == null) {
            return;
        }

        // 定义一个临时遍历，辅助遍历链表，用于获取每次遍历到的节点
        SingleHeroNode next;
        // 定义一个临时头部节点，用作新链表的头节点
        final SingleHeroNode reverseHead = new SingleHeroNode(0, "", "");
        // 遍历链表
        while (firstNode != null) {
            // 缓存下一个节点
            next = firstNode.getNext();
            // 将当前节点的 next 引用指向新链表的最前端，第一次遍历时，临时头部变量的 next 引用为 null
            // 后续遍历时，当前节点的 next 引用指向临时头部节点的 next 引用
            firstNode.setNext(reverseHead.getNext());
            // 将当前节点连接到新链表上
            reverseHead.setNext(firstNode);
            // 移动到下一个节点，继续遍历
            firstNode = next;
        }
        // 遍历结束后，将原先的头节点的 next 引用指向临时头部变量的 next 引用，实现单链表反转
        head.setNext(reverseHead.getNext());
    }

    /**
     * 面试题解答：逆序打印单链表，使用栈数据结构，利用其先进后出的特点即可。
     *
     * @param head 头节点
     * @author shiloh
     * @date 2022/7/3 22:12
     */
    public static void reversePrint(SingleHeroNode head) {
        SingleHeroNode currentNode = head.getNext();
        if (currentNode == null) {
            System.out.println("链表为空");
            return;
        }

        // 将链表中的所有节点都压入到栈中
        final Stack<SingleHeroNode> stack = new Stack<>();
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.getNext();
        }

        // 遍历栈中的内容
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序的链表，合并后的链表依然是有序的
     * <p>
     * 实现思路如下：
     * <p>
     * 按顺序创建新的链表，当发现两个链表中较小的一个节点时，就把这个节点添加到新链表中，
     * 然后再往下对比下一个节点，再把较小的节点添加到链表中
     * <p>
     * 当某个需要合并的链表为空时，则无需继续循环
     * 将剩余的节点直接添加到新链表中即可
     *
     * @param headA 需要合并的链表 A 的头节点
     * @param headB 需要合并的链表 B 的头节点
     * @return 合并后的有序链表
     * @author shiloh
     * @date 2022/7/3 22:44
     */
    public static SingleLinkedList merge(SingleHeroNode headA, SingleHeroNode headB) {
        final SingleLinkedList newLink = new SingleLinkedList();
        SingleHeroNode currentA = headA.getNext();
        SingleHeroNode currentB = headB.getNext();
        SingleHeroNode tempNode = newLink.getHead();
        while (currentA != null && currentB != null) {
            final int noA = currentA.getNo();
            final int noB = currentB.getNo();
            if (noA > noB) {
                tempNode.setNext(currentB);
                currentB = currentB.getNext();
            } else {
                tempNode.setNext(currentA);
                currentA = currentA.getNext();
            }
            // 往后移
            tempNode = tempNode.getNext();
        }

        // 添加剩余节点
        if (currentA != null) {
            tempNode.setNext(currentA);
        }
        if (currentB != null) {
            tempNode.setNext(currentB);
        }

        return newLink;
    }
}

