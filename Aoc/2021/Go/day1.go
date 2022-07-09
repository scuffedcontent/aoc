package main

import (
	"fmt"
	"io/ioutil"
	"strconv"
	"strings"
)

//
func main() {
	bs, err := ioutil.ReadFile("input.txt")
	if err != nil {
		bs, _ = ioutil.ReadFile("input.txt")
	}
	input := strings.Split(string(bs), "\n")
	var vals []int16
	for _, value := range input {
		val, _ := strconv.ParseInt(value, 10, 16)
		vals = append(vals, int16(val))
	}
	values := vals[:len(vals)-1]
	fmt.Println(values)
}

// input, err := ioutil.ReadFile("input.txt")
// if err != nil {
// 	fmt.Println(err)
// }
// input := strings.Split(string(input), "\n")

// var vals []int
// for _, value := range input {
// 	val, _ := strconv.ParseInt(value, 10, 16)
// 	vals = append(vals, int16(value))
// }
// fmt.Println(vals)
//fmt.Println(strings.Join(strings.Split(string(input), "\n"), ""), "")

// data, err := strconv.Atoi(strings.TrimSpace(string(input)))
// if err != nil {
// 	fmt.Println(err)
// }
// fmt.Println(input)
// depth := data[0]
// count := 0
// for i := 1; i < len(data); i++ {
// 	if data[i] > depth {
// 		count++
// 	}
// 	depth = data[i]
// }
// fmt.Println(count)
