package main

import "fmt"
import "io/ioutil"
import "strings"
//import "io/fs"
//import "os"
//import "sort"


func main() {
    bs, err :=  ioutil.ReadFile("input.txt")

    if err != nil {
        bs, _ = ioutil.ReadFile("input.txt")
    }
    input := strings.Split(string(bs), "\n")

    var vals []int16
    for _, value := range input


    fmt.Println(input)

}
