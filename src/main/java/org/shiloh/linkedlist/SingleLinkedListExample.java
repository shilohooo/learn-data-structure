package org.shiloh.linkedlist;

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
        final HeroNode heroNode01 = new HeroNode(1, "宋江", "及时雨");
        final HeroNode heroNode02 = new HeroNode(2, "卢俊义", "玉麒麟");
        final HeroNode heroNode03 = new HeroNode(3, "吴用", "智多星");
        final HeroNode heroNode04 = new HeroNode(4, "林冲", "豹子头");

        // 实例化链表
        final SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 添加节点
        // singleLinkedList.add(heroNode01);
        // singleLinkedList.add(heroNode04);
        // singleLinkedList.add(heroNode02);
        // singleLinkedList.add(heroNode03);

        // 测试按照英雄编号顺序添加
        singleLinkedList.addByNo(heroNode01);
        singleLinkedList.addByNo(heroNode04);
        singleLinkedList.addByNo(heroNode02);
        singleLinkedList.addByNo(heroNode03);

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
        final HeroNode heroNode05 = new HeroNode(5, "关胜", "大刀");
        final HeroNode heroNode06 = new HeroNode(6, "秦明", "霹雳火");
        final HeroNode heroNode07 = new HeroNode(7, "呼延灼", "双鞭");
        final HeroNode heroNode08 = new HeroNode(8, "花荣", "小李广");
        final SingleLinkedList linkB = new SingleLinkedList();
        linkB.addByNo(heroNode05);
        linkB.addByNo(heroNode07);
        linkB.addByNo(heroNode08);
        linkB.addByNo(heroNode06);
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
    public static int getLength(HeroNode head) {
        // 定义一个临时遍历，用于遍历链表
        HeroNode current = head.getNext();
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
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 定义一个临时遍历，用于遍历链表
        HeroNode tempNode = head.getNext();
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
    public static void reverseLink(HeroNode head) {
        // 如果链表为空，或者只包含一个有效节点，则无需遍历
        HeroNode firstNode = head.getNext();
        if (firstNode == null || firstNode.getNext() == null) {
            return;
        }

        // 定义一个临时遍历，辅助遍历链表，用于获取每次遍历到的节点
        HeroNode next;
        // 定义一个临时头部节点，用作新链表的头节点
        final HeroNode reverseHead = new HeroNode(0, "", "");
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
    public static void reversePrint(HeroNode head) {
        HeroNode currentNode = head.getNext();
        if (currentNode == null) {
            System.out.println("链表为空");
            return;
        }

        // 将链表中的所有节点都压入到栈中
        final Stack<HeroNode> stack = new Stack<>();
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
    public static SingleLinkedList merge(HeroNode headA, HeroNode headB) {
        final SingleLinkedList newLink = new SingleLinkedList();
        HeroNode currentA = headA.getNext();
        HeroNode currentB = headB.getNext();
        HeroNode tempNode = newLink.getHead();
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
     * 获取头节点
     *
     * @return 头节点
     * @author shiloh
     * @date 2022/6/27 22:17
     */
    public HeroNode getHead() {
        return this.HEAD;
    }

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
     * 根据编号将英雄人物插入到指定的位置中，如果该位置已有数据，则不能添加，需要给出错误提示
     *
     * @param newNode 新的节点
     * @author shiloh
     * @date 2022/6/21 22:48
     */
    public void addByNo(HeroNode newNode) {
        // 在不修改头节点的情况下，使用临时变量找到新节点的位置
        // 由于单链表的特性，临时变量应该位于新节点的前一个位置，此时才能正确的插入新的节点。
        HeroNode tempNode = HEAD;
        // 标识英雄人物的编号是否已经存在
        boolean isExist = false;
        // 遍历链表，找到新节点的位置
        final int newNodeNo = newNode.getNo();
        while (true) {
            // 当前遍历节点的 next 引用为空时，说明已经遍历到最后一个节点
            // 此时应该直接退出循环，无需继续查找
            // 随后将新节点直接添加到链表最后即可
            final HeroNode nextNode = tempNode.getNext();
            if (nextNode == null) {
                break;
            }
            // 如果 nextNode 的编号大于新节点的编号，则说明新节点的位置已经找到
            // 新节点的位置就位于 tempNode 和 nextNode 之间
            final int nextNodeNo = nextNode.getNo();
            if (nextNodeNo > newNodeNo) {
                break;
            }
            // 如果 nextNode 的编号等于新节点的编号，则说明该编号已经存在，不能添加
            if (nextNodeNo == newNodeNo) {
                isExist = true;
                break;
            }
            // 以上条件不成立时，将临时节点指向下一个节点，继续遍历
            tempNode = nextNode;
        }

        // 如果 isExist 为 true，则说明该编号已经存在，不能添加
        if (isExist) {
            System.out.printf("编号为：%d 的英雄人物已经存在，不能添加\n", newNodeNo);
            return;
        }

        // 将新的节点插入到链表中
        newNode.setNext(tempNode.getNext());
        tempNode.setNext(newNode);
    }

    /**
     * 根据英雄编号修改节点信息（编号不能修改）
     *
     * @param heroNode 要修改的节点
     * @author shiloh
     * @date 2022/6/24 22:33
     */
    public void update(HeroNode heroNode) {
        // 先判断链表是否为空
        if (this.isEmpty()) {
            System.out.println("链表为空，不能修改");
            return;
        }

        // 借助临时变量遍历节点
        HeroNode tempNode = HEAD.getNext();
        boolean isExist = false;
        final int heroNodeNo = heroNode.getNo();
        while (true) {
            // 当临时遍历为空 时，说明已经到了链表末尾
            if (tempNode == null) {
                break;
            }
            // 如果临时变量的编号等于要修改的节点的编号，则说明已经找到了要修改的节点
            if (tempNode.getNo().equals(heroNodeNo)) {
                isExist = true;
                break;
            }
            // 将临时节点指向下一个节点，然后继续遍历
            tempNode = tempNode.getNext();
        }

        // 如果找到了则修改节点的英雄姓名和昵称
        if (!isExist) {
            System.out.printf("没有找到编号为：%d 的英雄信息，无法修改", heroNodeNo);
            return;
        }

        tempNode.setName(heroNode.getName());
        tempNode.setNickname(heroNode.getNickname());
    }

    /**
     * 根据编号删除指定的节点，思路如下：
     * <ol>
     *     <li>借助临时变量，找到要删除的节点的上一个节点</li>
     *     <li>将临时变量的 next 引用指向要删除的节点的 next 引用</li>
     * </ol>
     *
     * @author shiloh
     * @date 2022/6/24 22:54
     */
    public void deleteByNo(final int no) {
        // 链表为空，不能删除
        if (this.isEmpty()) {
            System.out.println("链表为空，不能删除");
            return;
        }

        // 借助临时变量遍历链表
        HeroNode tempNode = HEAD;
        boolean isExist = false;
        while (true) {
            // 当临时变量为空时，说明已经到了链表末尾
            if (tempNode == null) {
                break;
            }
            // 当临时变量的 next 引用指向的节点的编号等于要删除的节点的编号时，说明找到了要删除的节点
            if (tempNode.getNext().getNo().equals(no)) {
                isExist = true;
                break;
            }
            // 将临时变量指向下一个节点，然后继续遍历
            tempNode = tempNode.getNext();
        }

        if (!isExist) {
            System.out.printf("没有找到编号为：%d 的英雄信息，无法删除\n", no);
            return;
        }

        // 如果找到了要删除的节点，则将临时变量的 next 引用指向要删除的节点的 next 引用
        tempNode.setNext(tempNode.getNext().getNext());
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
    private String name;

    /**
     * 昵称
     */
    private String nickname;

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

    public Integer getNo() {
        return no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
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