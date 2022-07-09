import java.io.BufferedReader;
import java.io.FileReader;
public class Day1

{
    public static void main(String [] args)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("day1.txt"));
            int countIncrease = 0;
            int lastNum = -1;
            String line;
            while((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                int i = Integer.parseInt(line);
                if (lastNum == -1) {
                    lastNum = i;
                    continue;
                }
                if (lastNum < i) {
                    countIncrease++;
                }
                lastNum = i;
            } System.out.println(countIncrease);
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
