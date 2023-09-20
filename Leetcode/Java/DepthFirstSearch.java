
/* I am just testing the output of a 2D array using Depth first search
 *
 *
 */

import java.util.Arrays;

public class DepthFirstSearch
{
    public static void main(String [] args)
    {
        int rows =4;
        int columns=5;
        int[][] array = new int[rows][columns];

        for (int[] row:array ){
        System.out.println(Arrays.toString(row));
        }
    }
}
