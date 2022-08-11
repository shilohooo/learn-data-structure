package org.shiloh.recursion.maze;

/**
 * 递归 - 解决迷宫问题
 *
 * @author shiloh
 * @date 2022/8/11 21:51
 */
public class MazeExample {
    public static void main(String[] args) {
        // 创建二维数据，模拟迷宫地图，8行7列
        final int[][] mazeMap = new int[8][7];
        // 约定使用 1 表示墙体，无法通过，先把上下全部置为1
        for (int i = 0; i < 7; i++) {
            // 第一行的所有列都置为1，即上面
            mazeMap[0][i] = 1;
            // 最后一行的所有列都置为1，即下面
            mazeMap[7][i] = 1;
        }

        // 将左右全部置为1
        for (int i = 0; i < 8; i++) {
            // 将第一列的所有行置为1，即左边
            mazeMap[i][0] = 1;
            // 将最后一列的所有行置为1，即右边
            mazeMap[i][6] = 1;
        }
        // 设置挡板
        mazeMap[3][1] = 1;
        mazeMap[3][2] = 1;
        // 堵死小球
        // mazeMap[1][2] = 1;
        // mazeMap[2][2] = 1;

        System.out.println("迷宫地图如下所示：");
        printMazeMap(mazeMap);

        // 使用递归来走出迷宫
        // 测试第一种行走策略：下 -> 右 -> 上 -> 左
        // final boolean result = findTheExit(mazeMap, 1, 1);
        // 测试第二种行走策略：上 -> 右 -> 下 -> 左
        final boolean result = findTheExitUsingOtherStrategy(mazeMap, 1, 1);
        System.out.println("是否有找到出口：" + result);

        // 打印找到出口后的迷宫位置
        System.out.println("小球走过后的迷宫地图数据：");
        printMazeMap(mazeMap);
    }

    /**
     * 打印迷宫地图数据
     *
     * @param mazeMap 迷宫地图二维数组
     * @author shiloh
     * @date 2022/8/11 22:07
     */
    public static void printMazeMap(int[][] mazeMap) {
        for (final int[] nums : mazeMap) {
            for (final int num : nums) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 利用递归找出迷宫出口
     *
     * <ol>
     *     <li>当前出发点设置为 {@code mazeMap[1][1]}</li>
     *     <li>终点设置为 {@code mazeMap[6][5]}</li>
     *     <li>当小球能走到终点时，说明出口已经找到了</li>
     *     <li>
     *         约定：当地图上的点为 0 时，表明此处还没走过，为 1 时，表明此处为墙体，无法通过，为 2 时，表明此处为通路，可以走，
     *         为 3 时，表明此处已经走过了，但无法通过
     *     </li>
     *     <li>在走迷宫时，需要先确定一个策略（方法）：下 -> 右 -> 上 -> 左，如果该点走不通，再进行回溯</li>
     * </ol>
     *
     * @param mazeMap  迷宫地图二维数组
     * @param startRow 从哪行开始出发
     * @param startCol 从哪列开始出发
     * @return 如果找到了出口就返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/8/11 22:09
     */
    public static boolean findTheExit(int[][] mazeMap, int startRow, int startCol) {
        if (mazeMap[6][5] == 2) {
            // 找到出口了
            return true;
        }
        if (mazeMap[startRow][startCol] == 0) {
            // 当前的点还没走过，可以按照策略走一次：下 -> 右 -> 上 -> 左。
            // 先假设该点可以走通
            mazeMap[startRow][startCol] = 2;
            // 先往下走
            if (findTheExit(mazeMap, startRow + 1, startCol)) {
                // 往下可以走通
                return true;
            }

            // 再往右走
            if (findTheExit(mazeMap, startRow, startCol + 1)) {
                // 往右可以走通
                return true;
            }

            // 再往上走
            if (findTheExit(mazeMap, startRow - 1, startCol)) {
                // 往上可以走通
                return true;
            }

            // 最后往左走
            if (findTheExit(mazeMap, startRow, startCol - 1)) {
                // 往左可以走通
                return true;
            }

            // 如果以上策略都走不动，说明该点是思路，需要标记为走不通的点 = 3
            mazeMap[startRow][startCol] = 3;
        }
        return false;
    }

    /**
     * 利用递归找出迷宫出口
     *
     * <ol>
     *     <li>当前出发点设置为 {@code mazeMap[1][1]}</li>
     *     <li>终点设置为 {@code mazeMap[6][5]}</li>
     *     <li>当小球能走到终点时，说明出口已经找到了</li>
     *     <li>
     *         约定：当地图上的点为 0 时，表明此处还没走过，为 1 时，表明此处为墙体，无法通过，为 2 时，表明此处为通路，可以走，
     *         为 3 时，表明此处已经走过了，但无法通过
     *     </li>
     *     <li>在走迷宫时，需要先确定一个策略（方法）上 -> 右 -> 下 -> 左，如果该点走不通，再进行回溯</li>
     * </ol>
     *
     * @param mazeMap  迷宫地图二维数组
     * @param startRow 从哪行开始出发
     * @param startCol 从哪列开始出发
     * @return 如果找到了出口就返回 {@code true}，否则返回 {@code false}
     * @author shiloh
     * @date 2022/8/11 22:09
     */
    public static boolean findTheExitUsingOtherStrategy(int[][] mazeMap, int startRow, int startCol) {
        if (mazeMap[6][5] == 2) {
            // 找到出口了
            return true;
        } else {
            if (mazeMap[startRow][startCol] == 0) {
                // 当前的点还没走过，可以按照策略走一次：上 -> 右 -> 下 -> 左。
                // 先假设该点可以走通
                mazeMap[startRow][startCol] = 2;
                // 先往上走
                if (findTheExitUsingOtherStrategy(mazeMap, startRow - 1, startCol)) {
                    // 往上可以走通
                    return true;
                }

                // 再往右走
                if (findTheExitUsingOtherStrategy(mazeMap, startRow, startCol + 1)) {
                    // 往右可以走通
                    return true;
                }

                // 再往下走
                if (findTheExitUsingOtherStrategy(mazeMap, startRow + 1, startCol)) {
                    // 往下可以走通
                    return true;
                }

                // 最后往左走
                if (findTheExitUsingOtherStrategy(mazeMap, startRow, startCol - 1)) {
                    // 往左可以走通
                    return true;
                }

                // 如果以上策略都走不动，说明该点是思路，需要标记为走不通的点 = 3
                mazeMap[startRow][startCol] = 3;
                return false;
            }
            return false;
        }
    }
}
