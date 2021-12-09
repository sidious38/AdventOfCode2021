package at.rennweg.htl.day5;

import java.util.Arrays;

/**
 * <strong>VentDiagram class</strong><br>
 * Represents a vent diagram
 *
 * @author sidious38
 */
public class VentDiagram {
    /**
     * The vent diagram (2D-Array)
     */
    private int[][] diagram;
    /**
     * True/false whether diagonal lines should be used or not
     */
    private boolean useDiagonalLines;

    /**
     * <strong>Default constructor</strong>
     * @param size Size of the vent diagram
     * @param diagonal True/false whether diagonal lines should be drawn or not
     */
    public VentDiagram(int size, boolean diagonal) {
        this.diagram = new int[size][size];
        this.useDiagonalLines = diagonal;
    }

    /**
     * Add a line to the vent diagram
     *
     * @param x1 Start X
     * @param y1 Start Y
     * @param x2 End X
     * @param y2 End Y
     */
    public void addLine(int x1, int y1, int x2, int y2) {
        if (y1 == y2) {
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                diagram[x][y1]++;
            }
        } else if (x1 == x2) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                diagram[x1][y]++;
            }
        } else if ((x1 < x2) && (y1 < y2) && useDiagonalLines) {
            for (int x = x1, y = y1; (x <= x2) && (y <= y2); x++, y++) {
                diagram[x][y]++;
            }
        } else if ((x1 > x2) && (y1 > y2) && useDiagonalLines) {
            for (int x = x1, y = y1; (x >= x2) && (y >= y2); x--, y--) {
                diagram[x][y]++;
            }
        } else if ((x1 < x2) && (y1 > y2) && useDiagonalLines) {
            for (int x = x1, y = y1; (x <= x2) && (y >= y2); x++, y--) {
                diagram[x][y]++;
            }
        } else if ((x1 > x2) && (y1 < y2) && useDiagonalLines) {
            for (int x = x1, y = y1; (x >= x2) && (y <= y2); x--, y++) {
                diagram[x][y]++;
            }
        }
    }

    /**
     * Add a line to the vent diagram
     *
     * @param start int[] with the start points (x, y)
     * @param end int[] with the end points (x, y)
     * @throws IllegalArgumentException Start and end point must consist of x and y value
     */
    public void addLine(int[] start, int[] end) {
        if ((start.length != 2) || (end.length != 2)) {
            throw new IllegalArgumentException("Start and end point must consist of x and y value");
        }

        addLine(start[0], start[1], end[0], end[1]);
    }

    /**
     * Get the amount of overlap points<br>
     * The value of an overlap point is > 1
     *
     * @return Amount of overlap points
     */
    public int getOverlapPoints() {
        int amountOverlapPoints = 0;

        for (int[] column : diagram) {
            for (int point : column) {
                if (point > 1) {
                    amountOverlapPoints++;
                }
            }
        }

        return amountOverlapPoints;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int[] column : diagram) {
            str.append(Arrays.toString(column)).append("\n");
        }

        return str.toString();
    }
}
