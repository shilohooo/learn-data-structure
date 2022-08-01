package org.shiloh.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.shiloh.stack.constant.OperatorConstants.getOperatorPriority;

/**
 * 基于后缀表达式实现一个逆波兰计算器
 * <p>
 * 要求支持小括号和多位整数
 *
 * @author shiloh
 * @date 2022/7/21 22:05
 */
public class PolandNotation {

    /**
     * 左括号
     */
    public static final String LEFT_BRACE = "(";

    /**
     * 右括号
     */
    public static final String RIGHT_BRACE = ")";

    public static void main(String[] args) {
        // 测试中缀表达式字符串转中缀表达式字符串列表
        final String infixExpr = "1+((2+3)*4)-5";
        final List<String> infixExprList = toInfixExprList(infixExpr);
        System.out.println("中缀表达式字符串列表：" + infixExprList);
        // 测试将中缀表达式字符串列表转换为后缀表达式字符串列表
        final List<String> suffixExprList = toSuffixExprList(infixExprList);
        System.out.println("后缀表达式字符串列表：" + suffixExprList);
        // 计算结果
        final int result = calcReversePolandExpr(suffixExprList);
        System.out.printf("逆波兰表达式：%s 的计算结果为：%d", suffixExprList, result);
    }

    /**
     * 将中缀表达式字符串转换为字符串列表，注意多位数拼接问题
     *
     * @param infixExpr 中缀表达式字符串
     * @return 中缀表达式字符串列表
     * @author shiloh
     * @date 2022/7/31 23:07
     */
    public static List<String> toInfixExprList(String infixExpr) {
        final List<String> infixExprList = new ArrayList<>();
        // 扫描指针
        int index = 0;
        // 多位数拼接 builder
        StringBuilder multiDigitBuilder;
        // 每次遍历得到的字符
        char c;
        final int length = infixExpr.length();
        do {
            c = infixExpr.charAt(index);
            // 如果当前遍历得到的字符不是一个数字，则直接添加到列表中
            if (!Character.isDigit(c)) {
                infixExprList.add(String.valueOf(c));
                index++;
                continue;
            }

            // 如果当前遍历得到的字符是一个数字，则需要考虑多位数的问题
            multiDigitBuilder = new StringBuilder();
            while (index < length && Character.isDigit(c = infixExpr.charAt(index))) {
                multiDigitBuilder.append(c);
                index++;
            }
            // 将多位数拼接结果添加到列表
            infixExprList.add(multiDigitBuilder.toString());
        } while (index < length);

        return infixExprList;
    }

    /**
     * 将中缀表达式字符串列表转换为后缀表达式字符串列表
     *
     * @param infixExprList 中缀表达式字符串列表
     * @return 后缀表达式字符串列表
     * @author shiloh
     * @date 2022/8/1 21:56
     */
    public static List<String> toSuffixExprList(List<String> infixExprList) {
        // 定义两个栈
        // 运算符栈
        final Stack<String> operatorStack = new Stack<>();
        // 中间结果栈
        // final Stack<String> midResultStack = new Stack<>();
        // 因为中间结果栈在整个转换过程中没有涉及到出栈（pop）操作，且后面需要逆序输出
        // 比较麻烦，这里直接使用字符串列表代替
        final List<String> suffixExprList = new ArrayList<>();

        // 遍历中间表达式字符串列表
        for (final String item : infixExprList) {
            if (item.matches("\\d+")) {
                // 如果是一个数字，则直接加入到列表中
                suffixExprList.add(item);
            } else if (LEFT_BRACE.equals(item)) {
                // 如果是一个左括号，则直接入运算符栈
                operatorStack.push(item);
            } else if (RIGHT_BRACE.equals(item)) {
                // 如果是右括号，则依次弹出栈顶运算符，并添加到列表中，直到遇到左括号为止
                // 此时将这一对括号丢弃
                while (!LEFT_BRACE.equals(operatorStack.peek())) {
                    suffixExprList.add(operatorStack.pop());
                }
                // 丢弃括号
                operatorStack.pop();
            } else {
                // 如果当前遍历得到的运算符的优先级小于等于栈顶运算符
                // 需要将栈顶运算符添加到列表中，然后再与新的栈顶运算符比较优先级

                // 获取运算符的优先级
                final int currentPriority = getOperatorPriority(item.charAt(0));
                while (!operatorStack.isEmpty()
                        && getOperatorPriority(operatorStack.peek().charAt(0)) >= currentPriority) {
                    suffixExprList.add(operatorStack.pop());
                }
                // 将当前遍历得到的运算符入栈
                operatorStack.push(item);
            }
        }

        // 最后将栈中剩余的内容添加到列表中
        while (!operatorStack.isEmpty()) {
            suffixExprList.add(operatorStack.pop());
        }

        // 这里用的是列表，可以直接返回结果，无需逆序
        return suffixExprList;
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

