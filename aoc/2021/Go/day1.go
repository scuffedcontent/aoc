package main

import (
	"fmt"
	"io/ioutil"
	"strconv"
	"strings"
)

//
func main() {
	input, err := ioutil.ReadFile("input.txt")
	if err != nil {
		fmt.Println(err)
	}
	data, err := strconv.Atoi(strings.TrimSpace(string(input)))
	if err != nil {
		fmt.Println(err)
	}
	depth := data[0]
	count := 0
	for i := 1; i < len(data); i++ {
		if data[i] > depth {
			count++
		}
		depth = data[i]
	}
	fmt.Println(count)
}
