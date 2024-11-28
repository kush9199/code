/*
Find the sum of all numbers below 1000 that are multiples of 3 or 5.

Challenge:
Use minimal iterations.
Print the result in a formatted string like: "The sum of multiples of 3 or 5 below 1000 is: X".
*/

package main

import (
	"fmt"
)

func main() {
	sum := 0

	for num := 3; num < 1000; num++ {
		if num % 3 == 0 || num % 5 == 0 {
			sum += num
		}
	}

	fmt.Println(sum)
	optimize()
}


func optimize() {
	lastNum := []int{-1,-1,-1}

	for num := 999; num >= 0; num-- {
		if num%3 == 0 && lastNum[0] < 0{
			lastNum[0] = num
		}
		if num%5 == 0 && lastNum[1] < 0{
			lastNum[1] = num
		}
		if num%3 == 0 && lastNum[2] < 0{
			lastNum[2] = num
		}
		if lastNum[0] > 0 && lastNum[1] > 0 && lastNum[2] > 0 {
			break
		}
	}

	n3, n5, n15 := getNnum(3, lastNum[0], 3), getNnum(5, lastNum[1], 5), getNnum(15, lastNum[2], 15)
	s3, s5, s15 := getSum(3, lastNum[0], n3), getNnum(5, lastNum[1], n5), getSum(15, lastNum[2], n15)

	ans := s3 + s5 - s15
	fmt.Println(s3, s5, s15)
	fmt.Println(n3, n5, n15)
	fmt.Println(lastNum[0], lastNum[1], lastNum[2])
	fmt.Println(ans)

}

func getNnum(a int, l int, d int) (n int) {
	n = int((l-a)/d)

	
	return
}

func getSum(a int, l int, n int) (sum int) {
	sum = int((n * (a + l))/2)
	return
}