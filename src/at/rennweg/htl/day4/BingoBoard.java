package at.rennweg.htl.day4;

import java.util.ArrayList;

/**
 * <strong>BingoBoard class</strong><br>
 * Represents a bingo board
 *
 * @author sidious38
 */
public class BingoBoard {
    /**
     * The bingo board as 3D-Array<br>
     * [x][][] row<br>
     * [][x][] column<br>
     * [][][x] number & drawn checker
     */
    private int[][][] board;

    /**
     * Length of a row/column
     */
    public static final int ROW_COLUMN_LENGTH = 5;

    /**
     * This constructor initialises the bingo board with a 3D int-Array
     *
     * @param board Bingo board
     * @throws IllegalArgumentException Board must consist of 5 rows
     * @throws IllegalArgumentException Board must consist of 5 columns
     * @throws IllegalArgumentException Every value array must contain the value and a second
     * element which is zero - can be set to one if it is drawn
     */
    public BingoBoard(int[][][] board) {
        if (board.length != ROW_COLUMN_LENGTH) {
            throw new IllegalArgumentException("Board must consist of 5 rows");
        }
        for (int[][] column : board) {
            if (column.length != ROW_COLUMN_LENGTH) {
                throw new IllegalArgumentException("Board must consist of 5 columns");
            }
            for (int[] value : column) {
                if (value.length != 2 && value[1] == 0) {
                    throw new IllegalArgumentException("Every value array must contain the value "
                            + "and a second element which is zero - can be set to one if it is "
                            + "drawn");
                }
            }
        }

        this.board = board;
    }

    /**
     * Draw a number and check if a row/column is complete
     * @param num Number
     * @return True/false whether a row/column is complete or not
     */
    public boolean draw(int num) {
        for (int colCounter = 0; colCounter < ROW_COLUMN_LENGTH; colCounter++) {
            boolean columnCompleted = true;

            for (int i = 0; i < board.length; i++) {
                boolean rowCompleted = true;

                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j][0] == num) {
                        board[i][j][1] = 1;
                    }
                    if (board[i][j][1] != 1) {
                        rowCompleted = false;
                    }
                }

                if (rowCompleted) {
                    return true;
                }

                if (board[i][colCounter][1] != 1) {
                    columnCompleted = false;
                }
            }

            if (columnCompleted) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all undrawn numbers
     * @return Undrawn numbers as ArrayList
     */
    public ArrayList<Integer> getUndrawnNumbers() {
        ArrayList<Integer> undrawnNumbers = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j][1] == 0) {
                    undrawnNumbers.add(board[i][j][0]);
                }
            }
        }

        return undrawnNumbers;
    }

    /**
     * Reset the drawn "flags"
     */
    public void resetDrawn() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j][1] = 0;
            }
        }
    }

    /**
     * Get the bingo board 3d int array
     * @return Bingo board 3d int array
     */
    public int[][][] getBoard() {
        return this.board;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                str.append(this.board[i][j][0]).append(":")
                        .append(this.board[i][j][1]).append(", ");
            }
            str.append("\n");
        }

        return str.toString();
    }
}
