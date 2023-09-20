import java.util.Arrays;
import java.util.List;

public class sumListArrayNoReduce {

    public static void main(String [] args) {

        List<Integer> numbers = Arrays.asList(3, 7, 8, 1, 5, 9);
        
        Integer sum = 0;
        for (int num: numbers) {
            sum = sum + num;
        }

        System.out.println(sum);

    }
}
