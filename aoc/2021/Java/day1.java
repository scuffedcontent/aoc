import java.io.BufferedReader;
import java.io.FileReader;

public class day1
{
   
    public static void main(String [] args)
    {
        try {
            BufferedReader br = new BufferedReader(new java.io.BufferedReader(new FileReader("day1.txt")));
            String line;
            while((line =  br.readLine()) != null) {
                System.out.println(line);
            } br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}