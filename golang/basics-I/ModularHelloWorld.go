/*

Write a program that prints “Hello, World!” but breaks the functionality into three separate functions:

A function to generate the message.
A function to display the message.
A main function to orchestrate the two.

*/

package main

import (
	"fmt"
)

func main() {
	printGreetings(generateGreetings("Kush9199"))
}

func generateGreetings(name string) (message string) {
	message = "Hello, " + name
	return
}

func printGreetings(message string) {
	fmt.Println(message)
}