package org.shiloh.linkedlist.single;

/**
 * 定义单向链表，用于管理水浒传英雄人物排行榜
 *
 * @author shiloh
 * @date 2022/6/20 23:11
 */
public class SingleLinkedList {
    /**
     * 定义头节点，无需存放具体的数据
     */
    private final SingleHeroNode head = new SingleHeroNode(0, "", "");

    /**
     * 获取头节点
     *
     * @return 头节点
     * @author shiloh
     * @date 2022/6/27 22:17
     */
    public SingleHeroNode getHead() {
        return this.head;
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
    public void add(SingleHeroNode newNode) {
        // 在不修改头节点的情况下，使用临时变量
        SingleHeroNode tempNode = this.head;
        // 遍历链表，找到最后一个节点
        while (true) {
            // 当前遍历节点的 next 引用为空时，说明已经遍历到最后一个节点
            final SingleHeroNode nextNode = tempNode.getNext();
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
    public void addByNo(SingleHeroNode newNode) {
        // 在不修改头节点的情况下，使用临时变量找到新节点的位置
        // 由于单链表的特性，临时变量应该位于新节点的前一个位置，此时才能正确的插入新的节点。
        SingleHeroNode tempNode = this.head;
        // 标识英雄人物的编号是否已经存在
        boolean isExist = false;
        // 遍历链表，找到新节点的位置
        final int newNodeNo = newNode.getNo();
        while (true) {
            // 当前遍历节点的 next 引用为空时，说明已经遍历到最后一个节点
            // 此时应该直接退出循环，无需继续查找
            // 随后将新节点直接添加到链表最后即可
            final SingleHeroNode nextNode = tempNode.getNext();
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
     * @param singleHeroNode 要修改的节点
     * @author shiloh
     * @date 2022/6/24 22:33
     */
    public void update(SingleHeroNode singleHeroNode) {
        // 先判断链表是否为空
        if (this.isEmpty()) {
            System.out.println("链表为空，不能修改");
            return;
        }

        // 借助临时变量遍历节点
        SingleHeroNode tempNode = this.head.getNext();
        boolean isExist = false;
        final int heroNodeNo = singleHeroNode.getNo();
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

        tempNode.setName(singleHeroNode.getName());
        tempNode.setNickname(singleHeroNode.getNickname());
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
        SingleHeroNode tempNode = this.head;
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
        SingleHeroNode tempNode = this.head.getNext();
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
        return head.getNext() == null;
    }

    /**
     * 定义 HeroNode 类，每个 HeroNode 对象就是一个节点
     *
     * @author shiloh
     * @date 2022/6/20 23:06
     */
    public static class SingleHeroNode {
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
        private SingleHeroNode next;

        /**
         * 构造器
         *
         * @param no       编号
         * @param name     姓名
         * @param nickname 昵称
         * @author shiloh
         * @date 2022/6/20 23:09
         */
        public SingleHeroNode(Integer no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        public void setNext(SingleHeroNode next) {
            this.next = next;
        }

        public SingleHeroNode getNext() {
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
}
