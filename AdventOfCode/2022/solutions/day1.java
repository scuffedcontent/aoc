import java.io.BufferedReader;
import java.io.FileReader;

public class day1
{
    
    public static void main(String [] args)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/mnt/c/Users/plump/work/git_repos/AdventOfCode/2022/inputs/day1_input.txt"));
            String line;
            while((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                System.out.println(line);
            } 
            br.close();
        } catch (Exception e) 
        {
            e.printStackTrace();              
        }

    }
}