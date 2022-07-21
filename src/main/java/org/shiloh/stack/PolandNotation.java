package org.shiloh.stack;

import java.util.List;
import java.util.Stack;

/**
 * 基于后缀表达式实现一个逆波兰计算器
 * <p>
 * 要求支持小括号和多位整数
 *
 * @author shiloh
 * @date 2022/7/21 22:05
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 定义一个逆波兰表达式（后缀表达式）
        // 测试 - (3 + 4) * 5 - 6 => 3 4 + 5 * 6 - => 计算结果应为：29
        // 测试 - (30 + 4) * 5 - 6 => 30 4 + 5 * 6 - => 计算结果应为：164
        // 测试 - 4 * 5 - 8 + 60 + 8 / 2 => => 计算结果应为：76
        final String suffixExpr = "4 5 * 8 - 60 + 8 2 / +";
        // 将逆波兰表达式字符串以空格分隔，转换为 List
        final List<String> reversePolandExprList = getReversePolandExprList(suffixExpr);
        // 计算结果
        final int result = calcReversePolandExpr(reversePolandExprList);
        System.out.printf("逆波兰表达式：%s 的计算结果为：%d", suffixExpr, result);
    }

    /**
     * 将逆波兰表达式字符串按空格分隔，转换为 List
     *
     * @param suffixExpr 逆波兰表达式字符串
     * @return 逆波兰表达式 List
     * @author shiloh
     * @date 2022/7/21 22:18
     */
    private static List<String> getReversePolandExprList(String suffixExpr) {
        return List.of(suffixExpr.split(" "));
    }

    /**
     * 利用栈计算逆波兰表达式：3 4 + 5 * 6 - 的结果
     * <p>
     * 思路如下：
     * <ol>
     *     <li>遍历逆波兰表达式列表，遇到数字则将其入栈</li>
     *     <li>遇到运算符则将栈顶和栈顶后一个数字取出，进行计算，然后将计算结果入栈</li>
     *     <li>重复第二步，直到栈中只剩下一个操作数，即计算结果</li>
     * </ol>
     *
     * @author shiloh
     * @date 2022/7/21 22:20
     */
    private static int calcReversePolandExpr(List<String> reversePolandExprList) {
        // 创建操作数栈
        final Stack<String> stack = new Stack<>();
        // 遍历逆波兰表达式
        for (final String item : reversePolandExprList) {
            // 如果当前遍历得到的是数字，则直接入栈
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                // 如果当前遍历得到的数据是运算符，则从栈中取出2个操作数进行计算
                // 然后将计算结果入栈
                final int num2 = Integer.parseInt(stack.pop());
                final int num1 = Integer.parseInt(stack.pop());
                switch (item) {
                    case "+":
                        stack.push(String.valueOf(num1 + num2));
                        break;
                    case "-":
                        // 减法需要注意操作数的顺序：
                        // 应该用后面出栈的数字减去前面出栈的数字
                        stack.push(String.valueOf(num1 - num2));
                        break;
                    case "*":
                        stack.push(String.valueOf(num1 * num2));
                        break;
                    case "/":
                        // 除法需要注意操作数的顺序：
                        // 应该用后面出栈的数字除以前面出栈的数字
                        stack.push(String.valueOf(num1 / num2));
                        break;
                    default:
                        throw new RuntimeException("运算符有误，请检查表达式~");
                }
            }
        }
        // 返回计算结果
        return Integer.parseInt(stack.pop());
    }
}