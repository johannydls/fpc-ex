package main

import (
	"sync"
	"time"
	"math/rand"
	"fmt"
)

func MultiMergeSort(array []int) []int {

	if len(array) <= 1 {
		return array
	}

	mid := len(array) / 2

	wg := sync.WaitGroup{}
	wg.Add(2)

	var left []int
	var right []int

	go func() {
		left = MultiMergeSort(array[:mid])
		wg.Done()
	}()

	go func() {
		right = MultiMergeSort(array[mid:])
		wg.Done()
	}()

	wg.Wait()

	return merge(left, right)

}

func merge(left, right []int) []int {

	ret := make([]int, 0, len(left) + len(right))

	for len(left) > 0 || len(right) > 0 {

		if len(left) == 0 {
			return append(ret, right...)
		}

		if len(right) == 0 {
			return append(ret, left...)
		}

		if (left[0] <= right[0]) {
			ret = append(ret, left[0])
			left = left[1:]
		} else {
			ret = append(ret, right[0])
			right = right[1:]
		}
	}

	return ret
}

func generateArray(size int) []int {

	array := make([]int, size, size)
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < size; i++ {
		array[i] = rand.Intn(50)
	}
	return array
}

func main() {

	array := generateArray(5)

	fmt.Println("\n --- unsorted --- \n\n", array)
	fmt.Println("\n --- sorted --- \n\n", MultiMergeSort(array))

}
