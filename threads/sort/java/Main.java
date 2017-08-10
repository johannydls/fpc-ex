package thread.sort;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int[] array = new int[] {3,-2,10,24,32,6,0};
		
		System.out.print("Array orginal: ");
		System.out.println(Arrays.toString(array));
		
		MergeSortMultithread.parallelMergeSort(array, 3);
		
		System.out.print("\nArray ordenado: ");
		System.out.println(Arrays.toString(array));
	}
	
}
