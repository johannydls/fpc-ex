package main

import (
	"fmt"
	"time"
)

func main() {

	notas := []string{"Do", "Re", "Mi", "Fa", "Sol"}

	fmt.Println(notas, "\n")

	NotasTrans(notas, 1)

	//Sleep para esperar a goroutine terminar
	time.Sleep(time.Second *1)

	fmt.Println(notas)

}

func translate(lista []string, start int, end int) []string {

	notes := map[string] string {
		"Do" : "C",
		"Re" : "D",
		"Mi" : "E",
		"Fa" : "F",
		"Sol": "G",
		"La" : "A",
		"Si" : "B",
	}

	for i := start; i < end; i++ {
		lista[i] = notes[lista[i]]
	}

	return lista
}

func NotasTrans(lista []string, numGoroutines int) []string {

	if numGoroutines > len(lista) {
		translate(lista, 0, len(lista))
		return lista

	} else {

		var numParallelism int = len(lista)/numGoroutines
		var start int = 0

		for i := 0; i < numGoroutines; i++ {
			go translate(lista, start, start+numParallelism)
			start += numParallelism
		}

		return lista
	}

}