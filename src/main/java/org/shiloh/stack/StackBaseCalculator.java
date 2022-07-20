package org.shiloh.stack;

import static org.shiloh.stack.constant.OperatorConstants.*;

/**
 * 基于栈实现的综合计算器
 *
 * @author shiloh
 * @date 2022/7/20 22:57
 */
public class StackBaseCalculator {
    public static void main(String[] args) {
        // 测试基于栈实现的综合计算器
        // 定义需要计算的表达式字符串
        // 问题：如何处理多位数？
        // final String expression = "70+20*6-4";
        final String expression = "7*2*2-5+1-5+3-4";
        // 创建操作数栈，运算符栈
        final CalculatorArrayBaseStack numbers = new CalculatorArrayBaseStack(10);
        final CalculatorArrayBaseStack operators = new CalculatorArrayBaseStack(10);
        // 借助临时变量 index 遍历表达式，用于扫描表达式内容
        int index = 0;
        // 定义用于计算的2个操作数变量
        int num1;
        int num2;
        // 定义操作符变量
        char operator;
        // 存储计算结果
        int calcResult;
        // 存储每次遍历得到的运算符
        char item;
        // 存储多位数
        StringBuilder multiDigitBuilder = new StringBuilder();
        // 开始扫描表达式
        while (index < expression.length()) {

            item = expression.charAt(index);
            // 判断当前遍历得到的数据是否为运算符
            if (operators.isOperator(item)) {
                // 如果是，则再判断当前运算符栈是否为空
                if (!operators.isEmpty()) {
                    // 如果运算符栈不为空，则需要将当前运算符的优先级与栈顶运算符的优先级进行比较
                    // 注意不能直接弹出栈顶运算符，后续还需要使用
                    final int priority = operators.getPriority(item);
                    final int topPriority = operators.getPriority((char) operators.peek());
                    // 如果当前运算符的优先级小于等于栈顶运算符的优先级
                    if (priority <= topPriority) {
                        // 则需要从操作数栈中取出2个操作数，再从运算符栈中取出一个运算符，进行计算
                        num1 = numbers.pop();
                        num2 = numbers.pop();
                        operator = (char) operators.pop();
                        calcResult = numbers.calc(num1, num2, operator);
                        // 然后将计算结果压入操作数栈中
                        numbers.push(calcResult);
                        // 最后将当前运算符入栈
                        operators.push(item);
                        index++;
                        continue;
                    }
                    // 当前运算符的优先级大于栈顶运算符的优先级，直接入栈
                    operators.push(item);
                    index++;
                    continue;
                }
                // 栈空，运算符入栈
                operators.push(item);
                index++;
                continue;
            }
            // 当前数据为数字时，将字符类型的数字转为数字类型的数字，然后直接压入操作数栈
            // 字符 0 在 ASCII 码表中为 48，假设当前数字为字符类型的 '1'，对应的 ASCII 码为 49
            // '1' - '0' = 49 - 48 = 1
            // 当处理多位数时，不能得到一个数字就立即入栈，因为多位数有多个字符
            // 在处理多位数时，需要向表达式的后面再判断一位，即 index + 1 的位置
            // 如果 index + 1 的位置还是数字，则还需要扫描
            // 如果 index + 1 的位置是符号，这时候才能将操作数入栈
            // 因此需要定义一个变量，用于拼接多位数

            // 处理多位数
            multiDigitBuilder.append(item);
            // 先判断是否走到了最后，如果是则将操作数直接入栈
            if (index == expression.length() - 1) {
                numbers.push(Integer.parseInt(multiDigitBuilder.toString()));
                index++;
                continue;
            } else {
                // 判断当前操作数的下一个数据是否为运算符
                if (numbers.isOperator(expression.charAt(index + 1))) {
                    // 下一个数据为运算符代表操作数可以直接入栈
                    numbers.push(Integer.parseInt(multiDigitBuilder.toString()));
                    // 充值存储多位数变量的值
                    multiDigitBuilder = new StringBuilder();
                }
            }
            // 继续扫描
            index++;
        }
        // 扫描结束，按顺序从操作数栈中取出2个数字
        // 再从运算符栈中取出一个运算符，进行计算
        // 直到运算符栈为空时，则计算完毕，当计算完毕时，操作数栈中应该只能有一个数组，即表达式的计算结果
        while (!operators.isEmpty()) {
            num1 = numbers.pop();
            num2 = numbers.pop();
            operator = (char) operators.pop();
            calcResult = numbers.calc(num1, num2, operator);
            // 将计算结果压入操作数栈中
            numbers.push(calcResult);
        }
        // 输出表达式结果
        System.out.printf("表达式：%s = %d\n", expression, numbers.pop());
    }
}

/**
 * 基于数组的栈
 *
 * @author shiloh
 * @date 2022/7/13 23:00
 */
class CalculatorArrayBaseStack {
    /**
     * 栈的大小
     */
    private final int maxSize;

    /**
     * 存储数据的容器
     */
    private final int[] data;

    /**
     * 指向栈顶的 top，初始化为 -1
     */
    private int top = -1;

    public CalculatorArrayBaseStack(int maxSize) {
        this.maxSize = maxSize;
        this.data = new int[this.maxSize];
    }

    /**
     * 判断栈是否满了
     *
     * @return 如果栈满了就返回 {@code true}, 否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/13 23:02
     */
    public boolean isFull() {
        return this.top == (this.maxSize - 1);
    }

    /**
     * 判断栈是否为空
     *
     * @return 如果为空就返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/13 23:03
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 数据入栈
     *
     * @author shiloh
     * @date 2022/7/13 23:04
     */
    public void push(int val) {
        // 栈满则给出错误提示
        if (this.isFull()) {
            throw new RuntimeException("栈满，数据入栈失败");
        }

        // 数据入栈
        this.top++;
        this.data[this.top] = val;
    }

    /**
     * 数据出栈，将栈顶的数据返回给客户端
     *
     * @return 栈顶数据
     * @author shiloh
     * @date 2022/7/13 23:05
     */
    public int pop() {
        // 如果栈空，则给出错误提示
        if (this.isEmpty()) {
            throw new RuntimeException("栈空，无法获取任何数据");
        }

        // 数据出栈
        final int val = this.data[this.top];
        this.top--;
        return val;
    }

    /**
     * 获取栈顶的数据，非弹出
     *
     * @return 栈顶数据
     * @author shiloh
     * @date 2022/7/20 23:33
     */
    public int peek() {
        return this.data[this.top];
    }

    /**
     * 输出栈中的内容，注意：应该从栈顶开始
     *
     * @author shiloh
     * @date 2022/7/13 23:07
     */
    public void print() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈中没有数据，无法打印");
        }

        // 遍历输出栈中的内容
        for (int i = this.top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, this.data[i]);
        }
    }

    /**
     * 获取运算符的优先级，该优先级由客户端决定
     * <p>
     * 此处将优先级定义为数字，数字越大代表优先级越高
     *
     * @param operator 运算符
     * @return 运算符的优先级
     * @author shiloh
     * @date 2022/7/20 22:59
     */
    public int getPriority(char operator) {
        // 此处按数学运算符定义优先级：先乘除后加减
        // 当运算符不是加减乘除时，则返回 - 1
        // 假定当前支持的表达式里面仅含有加减乘除
        return OPERATOR_PRIORITY_CACHE.getOrDefault(operator, -1);
    }

    /**
     * 判断数据是否为一个运算符
     *
     * @param data 数据
     * @return 如果数据为一个运算符则返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/7/20 23:10
     */
    public boolean isOperator(char data) {
        return OPERATOR_PRIORITY_CACHE.containsKey(data);
    }

    /**
     * 操作数计算
     *
     * @param num1     操作数1
     * @param num2     操作数2
     * @param operator 运算符
     * @return 计算结果
     * @author shiloh
     * @date 2022/7/20 23:12
     */
    public int calc(int num1, int num2, char operator) {
        // 计算结果
        switch (operator) {
            // 加法
            case PLUS:
                return num1 + num2;
            // 减法
            case MINUS_SIGN:
                // 这里需要注意：应该有后面取出的操作数减去前面取出的操作数
                // 比如：3 - 1，3 是先入栈的，后面取出的顺序则相反，先取出 1
                // 所以应该用后面取出的减去前面取出的
                return num2 - num1;
            // 乘法
            case MULTIPLICATION_SIGN:
                return num1 * num2;
            // 除法
            case DIVISION_SIGN:
                // 这里需要注意与减法一样的顺序问题
                return num2 / num1;
            default:
                return 0;
        }
    }
}