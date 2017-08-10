package thread.sort;

import java.util.Arrays;

public class MergeSortMultithread {

	
	public static void merge(int[] left, int[] right, int[] array) {
		
		int i1 = 0;
		int i2 = 0;
		
		for (int i = 0; i < array.length; i++) {
			
			if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
				
				array[i] = left[i1];
				i1++;
				
			} else {
				
				array[i] = right[i2];
				i2++;
				
			}
		}
	}
	
	
	public static void mergeSort(int[] array) {
		
		if (array.length >= 2) {
			
			//Divide o array no meio
			int[] left = Arrays.copyOfRange(array, 0, array.length/2);
			int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
			
			//ordena as duas metades
			mergeSort(left);
			mergeSort(right);
			
			//junta a duas partes ordenadas
			merge(left, right, array);
		}
	}
	
	public static void parallelMergeSort(int[] array, int numThread) {
		
		if (numThread <= 1) {
			
			mergeSort(array);
			
		} else if (array.length >= 2) {
			
			//Divide o array no meio
			int[] left = Arrays.copyOfRange(array, 0, array.length/2);
			int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
			
			//Aplica uma thread em cada metade do array
			Thread threadLeft = new Thread(new Sorter(left, numThread/2));
			Thread threadRight = new Thread(new Sorter(right, numThread/2));
			
			threadLeft.start();
			threadRight.start();
			
			try {
				threadLeft.join();
				threadRight.join();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
			merge(left, right, array);
		}
	}
}
