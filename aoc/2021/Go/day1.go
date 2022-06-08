package main



import (
	"./utils"
	"fmt"
	"io/ioutil"
	"strconv"
	"strings"
)


func main() {
    bs, err :=  ioutil.ReadFile("input.txt")
    if err != nil {
        bs, _ = ioutil.ReadFile("input.txt")
    }
    input := strings.Split(string(bs), "\n")
    var vals []int16
    for i, value := range input {
        curr, err := strconv.Atoi(value)
        utils.Check(err, fmt.Sprintf("Error converting %s to int", value))
    }
    fmt.Println(input)
    fmt.Println(curr)
}
