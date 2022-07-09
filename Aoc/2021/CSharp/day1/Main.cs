using System;
using System.IO;

namespace ReadWriteFiles
{
    class Program
    {
        static void Main(string[] args)
        {
            if (File.Exists("day1.txt"))
            {
                string[] lines = File.ReadAllLines(@"/data/day1.txt");
                Console.WriteLine(lines);
            }
        }
    }
}
