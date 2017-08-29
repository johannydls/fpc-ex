package main

import (
	"fmt"
	"math/rand"
)

func producer(c chan int, fail chan bool) {
	if success := rand.Float32() > 0.5; success {
		num := rand.Int()

		c <- num

		fmt.Println("Producer produced: ", num)
	} else {
		fail <- true
	}
}

func consumer(c chan int, success chan bool) {
	for {
		num := <-c
		fmt.Printf("Consumer consumed: %d\n", num)
		success <- true
	}
}

func main() {
	const nTries = 10
	c := make(chan int)
	done := make(chan bool)
	for i := 0; i < nTries; i++ {
		go producer(c, done)
		go consumer(c, done)
	}


	for i := 0; i < nTries; i++ {
		<-done
	}
	fmt.Println("All done.")
}