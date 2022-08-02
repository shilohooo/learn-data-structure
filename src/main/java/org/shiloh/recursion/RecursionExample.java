package org.shiloh.recursion;

/**
 * 递归示例
 *
 * @author shiloh
 * @date 2022/8/2 22:12
 */
public class RecursionExample {
    public static void main(String[] args) {
        // print(10);
        final int result = factorial(10);
        System.out.println("10 的阶乘 = " + result);
    }

    /**
     * 递归打印数字
     *
     * @param n 数字
     * @author shiloh
     * @date 2022/8/2 22:13
     */
    public static void print(int n) {
        if (n > 2) {
            // 此处发生递归调用
            print(n - 1);
        }
        System.out.println("n = " + n);
    }

    /**
     * 求阶乘
     *
     * @param n 需要求阶乘的数字
     * @return 阶乘
     * @author shiloh
     * @date 2022/8/2 22:26
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        // 此处发生了递归
        return factorial(n - 1) * n;
    }
}
