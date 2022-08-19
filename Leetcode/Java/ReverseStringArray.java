/* Given an array of chars {'h', 'e', 'l', 'l', 'o'}
 * reverse the array in place without creating
 * another array in memory
 * example output: {'o', 'l', 'l', 'e', 'h'}
 */

class Solution {
    public static void main(Char[] Array) {
        char temp;
        for (int i = 0; i < Array.length / 2; i++) {
            temp = Array[i];
            Array[i] = Array[Array.length - i - 1];
            Array[Array.length - i - 1] = temp;
        }
        System.out.println(Array);
    }
}

class BlazinglyFastSolution {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}