/*
Write a program that prints the first N Fibonacci numbers (input N from the user).

Challenge:

Ensure the program handles edge cases like N = 0 or N = 1.
Format the output as 1st: 0, 2nd: 1, 3rd: 1, etc.
*/

package main

import (
	"fmt"
	"errors"
)

func main() {
	res, err := getFibSeries(6)
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(formatFibSeries(res))
	}
	printFibSeries(6)
	fmt.Println(recFibPrinter(6))
}

func getFibSeries(N uint8) ([]uint64, error) {
	if N == 0 {
		return nil, errors.New("Enter valid value")
	}
	var num1 uint64 = 0
	var num2 uint64 = 1
	series := []uint64{num1, num2}

	for i := 2; uint8(i) < N; i++ {
		series = append(series, series[i-2] + series[i-1])
	}
	return series, nil
}

func formatFibSeries(series []uint64) (out string) {
	for idx, value := range series {
		out += fmt.Sprintf("%d: %d \n", idx + 1, value)
	}
	return
}

func printFibSeries(N uint8) error {
	var num1 uint64 = 0
	var num2 uint64 = 1
	if N <= 0 {
		return errors.New("enter valid number")
	}

	fmt.Printf("%d \t", num1)
	for i := 1; uint8(i) < N; i++ {
		fmt.Printf("%d \t", num2)
		temp := num2
		num2 += num1
		num1 = temp
	}
	return nil
}

func recFibPrinter(N uint64) (uint64){
	if N == 1 || N == 0{
		return N
	}
	return recFibPrinter(N-2) + recFibPrinter(N-1)
}