package org.shiloh.stack.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 运算符常量
 *
 * @author shiloh
 * @date 2022/7/20 23:04
 */
public final class OperatorConstants {
    private OperatorConstants() {
    }

    /**
     * 乘号
     */
    public static final char MULTIPLICATION_SIGN = '*';

    /**
     * 除号
     */
    public static final char DIVISION_SIGN = '/';

    /**
     * 加号
     */
    public static final char PLUS = '+';

    /**
     * 减号
     */
    public static final char MINUS_SIGN = '-';

    /**
     * 运算符优先级缓存
     */
    public static final Map<Character, Integer> OPERATOR_PRIORITY_CACHE = new HashMap<>(4);

    static {
        // 初始化运算符优先级
        OPERATOR_PRIORITY_CACHE.put(MULTIPLICATION_SIGN, 1);
        OPERATOR_PRIORITY_CACHE.put(DIVISION_SIGN, 1);
        OPERATOR_PRIORITY_CACHE.put(PLUS, 0);
        OPERATOR_PRIORITY_CACHE.put(MINUS_SIGN, 0);
    }

}
