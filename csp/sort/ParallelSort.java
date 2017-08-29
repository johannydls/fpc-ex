public class ParallelSorting {

    private static int[] privateData;
    private final static boolean UP = true, DOWN = false;

    /**
     * Sort data int array using a given parallelism level
     * @param data
     * @param level
     * @return
     */
    public static int[] sort(int[] data, int level) {
        privateData = data;
        parallelSort(0, privateData.length, UP, level);
        return privateData;
    }

    public static void parallelSort(int lowValue, int n, boolean direction, int level) {
        if (n > 1) {
            int m = n / 2;
            if (level == 0) {
                parallelSort(lowValue, m, UP, level);
                parallelSort(lowValue + m, m, DOWN, level);
                merge(lowValue, n, direction);
            } else {
                Thread tOne = new Thread(new Runner(lowValue, m, UP, level - 1));
                Thread tTwo = new Thread(new Runner(lowValue + m, m, DOWN, level - 1));
                tOne.start();
                tTwo.start();
                try {
                    tOne.join();
                    tTwo.join();
                } catch (InterruptedException ie) {
                    //do nothing
                }
                merge(lowValue, n, direction);
            }
        }
    }

    private static void merge(int lowValue, int n, boolean direction) {
        if (n > 1) {
            int m = n / 2;
            for (int i = lowValue; i < lowValue + m; i++)
                compare(i, i + m, direction);
            merge(lowValue, m, direction);
            merge(lowValue + m, m, direction);
        }
    }

    private static void compare(int i, int j, boolean direction) {
        if (direction == (privateData[i] > privateData[j]))
            swap(i, j);
    }

    private static void swap(int i, int j) {
        int t = privateData[i];
        privateData[i] = privateData[j];
        privateData[j] = t;
    }

    public static class Runner implements Runnable {

        private boolean dir;
        private int level;
        private int min;
        private int max;

        public Runner(int min, int max, boolean direction, int level) {
            this.dir = direction;
            this.level = level;
            this.min = min;
            this.max = max;
        }

        public void run() {
            ParallelSort.parallelSort(min, max, dir, level);
        }
    }
}
