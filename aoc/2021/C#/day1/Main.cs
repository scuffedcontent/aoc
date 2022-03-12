using System;

namespace main
{
    class Program
    {
        static void Main()
        {
            string[] lines = System.IO.File.ReadAllLines(@"/home/hakku/work/git_repo/aoc/2021/C#/data/day1.txt");
            
            foreach(string line in lines)
            {
                Console.WriteLine("\t" + line);
            }
        }
    }
}
