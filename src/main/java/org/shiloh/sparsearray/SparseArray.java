package org.shiloh.sparsearray;

import java.io.*;

/**
 * 稀疏数组示例
 * <p>
 * 存储五子棋数据：将二维数组转换为稀疏数组
 * 默认棋盘大小：11 * 11
 * 默认无效数据为0，黑子为1，蓝子为 2
 *
 * @author shiloh
 * @date 2022/6/12 17:13
 */
public class SparseArray {
    /**
     * 棋盘大小：行数
     */
    private static final int CHESSBOARD_ROWS = 11;

    /**
     * 棋盘大小：列数
     */
    private static final int CHESSBOARD_COLUMNS = 11;

    /**
     * 空棋子
     */
    private static final int EMPTY_CHESS = 0;

    /**
     * 稀疏数组固定总列数
     */
    private static final int SPARSE_ARRAY_TOTAL_COLUMNS = 3;

    /**
     * 稀疏数组第一行记录总行数的索引
     */
    private static final int SPARSE_ARRAY_ROW_INDEX = 0;

    /**
     * 稀疏数组第一行记录总列数的索引
     */
    private static final int SPARSE_ARRAY_COLUMN_INDEX = 1;

    /**
     * 稀疏数组第一行记录有效数据个数的索引
     */
    private static final int SPARSE_ARRAY_VAL_INDEX = 2;

    /**
     * 存储稀疏数组数据的文件路径
     */
    private static final String SPARSE_ARRAY_FILE_PATH = "chess_array.data";

    public static void main(String[] args) {
        // 创建一个原始的二维数组，大小：11 * 11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        final int[][] chessArray = createOriginalChessArray();
        // 将二维数组转换为稀疏数组
        // 1.遍历二维数组，得到非0数据的个数
        int total = countValidData(chessArray);
        // 2.创建对应的稀疏数组
        final int[][] sparseArray = createSparseArray(chessArray, total);

        // 将稀疏数组序持久化到文件中
        persistSparseArray2File(sparseArray);

        // 将稀疏数组恢复为原始的二维数组
        // 1.从文件中读取稀疏数组的数据到内存中
        final int[][] sparseArrayFromFile = readSparseArrayFromFile();
        // 2.读取稀疏数组的第一行，根据第一行存储的数据，创建原始的二维数组
        final int[][] originalChessArray = recoverChessArray(sparseArrayFromFile);
        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组：");
        for (final int[] rows : originalChessArray) {
            for (final int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 创建原始的二维数组
     *
     * @return 二维数组
     * @author shiloh
     * @date 2022/6/12 18:24
     */
    private static int[][] createOriginalChessArray() {
        final int[][] chessArray = new int[CHESSBOARD_ROWS][CHESSBOARD_COLUMNS];
        // 分别下入一个黑子和蓝子
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][5] = 2;
        // 打印原始二维数组
        System.out.println("原始的二维数组：");
        for (final int[] rows : chessArray) {
            for (final int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return chessArray;
    }

    /**
     * 统计原始二维数组中的有效数据个数
     *
     * @return 原始二维数组中的有效数据个数
     * @author shiloh
     * @date 2022/6/12 18:25
     */
    private static int countValidData(int[][] chessArray) {
        int total = 0;
        for (final int[] rows : chessArray) {
            for (final int data : rows) {
                if (EMPTY_CHESS != data) {
                    total++;
                }
            }
        }
        System.out.println();
        System.out.printf("二维数组中总共有 %d 个有效数据\n", total);
        return total;
    }

    /**
     * 创建稀疏数组
     *
     * @param chessArray 原始二维数组
     * @param total      原始二维数组中的有效数据个数
     * @return 稀疏数据
     * @author shiloh
     * @date 2022/6/12 18:26
     */
    private static int[][] createSparseArray(int[][] chessArray, int total) {
        final int[][] sparseArray = new int[total + 1][SPARSE_ARRAY_TOTAL_COLUMNS];
        // 3.给稀疏数组赋值
        // 第一行为：二维数组有几行几列，以及有几个不同的有效数据
        sparseArray[0][0] = CHESSBOARD_ROWS;
        sparseArray[0][1] = CHESSBOARD_COLUMNS;
        sparseArray[0][2] = total;
        // 4.遍历二维数组，将非 0 的值放到稀疏数组中
        // 声明一个变量，用于记录是第几个有效数据
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            final int[] rows = chessArray[i];
            for (int j = 0; j < rows.length; j++) {
                final int data = rows[j];
                if (EMPTY_CHESS != data) {
                    count++;
                    sparseArray[count][SPARSE_ARRAY_ROW_INDEX] = i;
                    sparseArray[count][SPARSE_ARRAY_COLUMN_INDEX] = j;
                    sparseArray[count][SPARSE_ARRAY_VAL_INDEX] = data;
                }
            }
        }
        // 5.输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (final int[] rows : sparseArray) {
            final int rowIndex = rows[0];
            final int columnIndex = rows[1];
            final int val = rows[2];
            System.out.printf("%d\t%d\t%d\t\n", rowIndex, columnIndex, val);
        }
        return sparseArray;
    }

    /**
     * 将稀疏数组持久化到文件中
     *
     * @param sparseArray 稀疏数组
     * @author shiloh
     * @date 2022/6/12 18:32
     */
    private static void persistSparseArray2File(int[][] sparseArray) {
        try (
                final FileOutputStream fileOutputStream = new FileOutputStream(SPARSE_ARRAY_FILE_PATH);
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(sparseArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件中读取稀疏数组
     *
     * @return 稀疏数组
     * @author shiloh
     * @date 2022/6/12 18:28
     */
    private static int[][] readSparseArrayFromFile() {
        try (
                final FileInputStream fileInputStream = new FileInputStream(SPARSE_ARRAY_FILE_PATH);
                final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            return (int[][]) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据稀疏数组恢复原始的二维数组
     *
     * @param sparseArrayFromFile 从文件中读取到的稀疏数组
     * @return 原始的二维数组
     * @author shiloh
     * @date 2022/6/12 18:30
     */
    private static int[][] recoverChessArray(int[][] sparseArrayFromFile) {
        final int totalRows = sparseArrayFromFile[0][0];
        final int totalColumns = sparseArrayFromFile[0][1];
        final int[][] newChessArray = new int[totalRows][totalColumns];
        // 3.读取稀疏数组除第一行以外的数据，并赋值给新的二维数组
        for (int i = 1; i < sparseArrayFromFile.length; i++) {
            final int[] rows = sparseArrayFromFile[i];
            final int row = rows[SPARSE_ARRAY_ROW_INDEX];
            final int column = rows[SPARSE_ARRAY_COLUMN_INDEX];
            final int data = rows[SPARSE_ARRAY_VAL_INDEX];
            newChessArray[row][column] = data;
        }
        return newChessArray;
    }
}
