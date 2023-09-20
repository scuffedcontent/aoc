/* Advent of Code C
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_LINE_LENGTH 8
#define MAX_VALUES 2000
int main()
{
    FILE *file = fopen("day1.txt", "r");
    if (file == 0)
    {
        printf("Could not open file. \n");
        return 1;
    }
    char line[MAX_LINE_LENGTH];
    int values[MAX_VALUES];
    int counter = 0;
    while(fgets(line, MAX_LINE_LENGTH, file))
    {
        values[counter] = atoi(line);
        counter++;
    }
    int increases = 0;
    for (int idx = 1; idx < MAX_VALUES; idx++)
    {
        int current = values[idx];
        int prev = values[idx-1];
        if (current > prev)
        {
            increases++;
        }
    }
    fclose(file);
    printf("Count of increases: %i\n", increases);
    return 0;
}
