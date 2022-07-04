package org.shiloh.linkedlist.doubly;

/**
 * 双向链表
 *
 * @author shiloh
 * @date 2022/7/4 22:38
 */
public class DoublyLinkedList {
    /**
     * 初始化头节点
     */
    private final DoublyHeroNode head = new DoublyHeroNode(0, "", "");

    /**
     * 获取头节点
     *
     * @return 头节点对象
     * @author shiloh
     * @date 2022/7/4 22:45
     */
    public DoublyHeroNode getHead() {
        return this.head;
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
        DoublyHeroNode tempNode = this.head.getNext();
        // 遍历链表
        while (tempNode != null) {
            // 输出节点信息
            System.out.println(tempNode);
            // 将临时节点指向下一个节点
            tempNode = tempNode.getNext();
        }
    }

    /**
     * 添加节点到链表中，默认添加到链表的末尾处
     *
     * @param newNode 新节点
     * @author shiloh
     * @date 2022/7/4 22:51
     */
    public void add(DoublyHeroNode newNode) {
        // 借助临时变量遍历链表
        DoublyHeroNode tempNode = this.head;
        while (true) {
            // 如果当前遍历的节点的 next 引用为 null，则说明已经遍历到链表末尾处了
            final DoublyHeroNode nextNode = tempNode.getNext();
            if (nextNode == null) {
                break;
            }

            // 往下走
            tempNode = nextNode;
        }

        // 当循环结束后，临时节点就指向了链表的末尾处
        // 形成双向链表
        tempNode.setNext(newNode);
        newNode.setPrev(tempNode);
    }

    /**
     * 根据英雄的编号按顺序添加节点，如果该节点已存在则不能添加，需要给出错误提示
     *
     * @param newNode 待添加的新节点
     * @author shiloh
     * @date 2022/7/4 23:29
     */
    public void addByNo(DoublyHeroNode newNode) {
        // 借助临时节点遍历链表
        DoublyHeroNode tempNode = this.head;
        // 标识节点是否已存在
        boolean exists = false;
        final int newNodeNo = newNode.getNo();
        while (true) {
            final DoublyHeroNode nextNode = tempNode.getNext();
            // 如果临时节点的 next 引用为 null，说明已经遍历到了链表的末尾处
            if (nextNode == null) {
                break;
            }

            // 如果 nextNode 的编号大于待添加节点的编号，说明已经找到了要插入的位置
            final int nextNodeNo = nextNode.getNo();
            if (nextNodeNo > newNodeNo) {
                break;
            }

            // 如果 nextNode 的编号等于待添加节点的编号，说明节点已存在
            if (nextNodeNo == newNodeNo) {
                exists = true;
                break;
            }

            // 往下走
            tempNode = nextNode;
        }

        // 如果节点已存在，则给出错误提示
        if (exists) {
            System.out.printf("编号为 %d 的节点已存在，无法重复添加", newNodeNo);
            return;
        }

        // 将新节点添加到链表中
        final DoublyHeroNode doublyHeroNode = tempNode.getNext();
        // 此处是为了防止当链表仅有一个节点时，该节点的 next 引用为 null
        // 从而导致出现 NPE 的情况
        if (doublyHeroNode != null) {
            doublyHeroNode.setPrev(newNode);
        }

        tempNode.setNext(newNode);
        newNode.setNext(doublyHeroNode);
        newNode.setPrev(tempNode);
    }

    /**
     * 根据编号修改节点信息，此操作与单向链表的修改操作几乎一样，只是节点的数据类型不同
     *
     * @param doublyHeroNode 待修改的节点信息
     * @author shiloh
     * @date 2022/7/4 22:58
     */
    public void updateByNo(DoublyHeroNode doublyHeroNode) {
        // 如果链表为空则不做任何操作
        if (this.isEmpty()) {
            System.out.println("链表为空，无法更新");
            return;
        }

        // 定义一个临时节点，用于遍历链表
        DoublyHeroNode tempNode = this.head.getNext();
        // 标识要修改的节点是否存在
        boolean exists = false;
        final int updateNo = doublyHeroNode.getNo();
        while (true) {
            // 如果临时节点为空，则说明链表中没有任何数据
            if (tempNode == null) {
                break;
            }

            // 如果临时节点的编号等于待修改的节点的编号，则说明找到了需要修改的节点
            final int no = tempNode.getNo();
            if (no == updateNo) {
                exists = true;
                break;
            }

            // 往下走
            tempNode = tempNode.getNext();
        }

        // 如果没有找到要修改的节点，则无需进行任何操作
        if (!exists) {
            System.out.printf("链表中不存在编号为：%d 的节点信息，无法修改", updateNo);
            return;
        }

        // 修改节点数据
        tempNode.setName(doublyHeroNode.getName());
        tempNode.setNickname(doublyHeroNode.getNickname());
    }

    /**
     * 根据编号删除指定的节点
     * <p>
     * 对于双向链表，无需再找到待删除节点的前一个节点，可以直接找到要删除的节点
     * <p>
     * 然后进行自我删除即可
     *
     * @param no 待删除节点的编号
     * @author shiloh
     * @date 2022/7/4 23:04
     */
    public void deleteByNo(Integer no) {
        // 链表为空时无需删除
        if (this.isEmpty()) {
            System.out.println("链表为空，无法删除任何数据");
            return;
        }

        // 借助临时变量遍历链表
        DoublyHeroNode tempNode = this.head.getNext();
        // 标识待删除的节点是否存在
        boolean exists = false;
        while (true) {
            // 如果临时节点为 null，则说明已经遍历到了链表的末尾处
            if (tempNode == null) {
                break;
            }

            // 如果临时节点的编号等于待删除的节点的编号，则说明找到了要删除的节点
            if (no.equals(tempNode.getNo())) {
                exists = true;
                break;
            }

            // 往下找
            tempNode = tempNode.getNext();
        }

        // 如果没有找到要删除的节点，则无需进行任何操作
        if (!exists) {
            System.out.printf("链表中不存在编号为：%d 的节点，无法删除", no);
            return;
        }

        // 删除节点
        tempNode.getPrev().setNext(tempNode.getNext());
        // 若待删除节点不是最后一个节点，则需要设置该节点的下一个节点的 prev 引用
        // 以此形成双向联保
        final DoublyHeroNode next = tempNode.getNext();
        if (next != null) {
            next.setPrev(tempNode.getPrev());
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
        return this.head.getNext() == null;
    }

    /**
     * 定义 HeroNode 类，每个 HeroNode 对象就是一个节点
     *
     * @author shiloh
     * @date 2022/6/20 23:06
     */
    public static class DoublyHeroNode {
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
         * 指向前一个节点的引用，默认为 {@code null}
         */
        private DoublyHeroNode prev;

        /**
         * 指向下一个节点的引用，默认为 {@code null}
         */
        private DoublyHeroNode next;

        /**
         * 构造器
         *
         * @param no       编号
         * @param name     姓名
         * @param nickname 昵称
         * @author shiloh
         * @date 2022/6/20 23:09
         */
        public DoublyHeroNode(Integer no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        public Integer getNo() {
            return no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public DoublyHeroNode getPrev() {
            return prev;
        }

        public void setPrev(DoublyHeroNode prev) {
            this.prev = prev;
        }

        public DoublyHeroNode getNext() {
            return next;
        }

        public void setNext(DoublyHeroNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "DoublyHeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
}
