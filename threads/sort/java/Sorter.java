package thread.sort;

public class Sorter implements Runnable {
	
	private int[] array;
	private int numThread;
	
	public Sorter(int[] array, int numThread) {
		this.array = array;
		this.numThread = numThread;
	}

	@Override
	public void run() {
		
		MergeSortMultithread.parallelMergeSort(array, numThread);
		
	}

}
