public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;  // The array representing the max priority queue
    private int N;   // The number of elements in the priority queue
    private static final int DEFAULT_CAPACITY = 4;  // Default initial capacity of the array
    private static final int AUTOGROW_SIZE = 4;     // Amount by which the array grows when it's full

    // Constructor initializes an empty priority queue with default capacity
    public MaxPQ() {
        this.pq = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.N = 0;
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return N == 0;
    }

    // Get the number of elements in the priority queue
    public int getSize() {
        return N;
    }

    // Insert a new item into the priority queue
    public void insert(T item) {
        // Check available space and grow if necessary
        if (N == pq.length - 1) grow();
        pq[N] = item;
        swim(N);  // Move the newly added item up to its correct position
        N++;
    }

    // Increase the capacity of the array by AUTOGROW_SIZE
    private void grow() {
        T[] newPQ = (T[]) new Comparable[pq.length + AUTOGROW_SIZE];
        // Copy elements from the old array to the new array
        System.arraycopy(pq, 0, newPQ, 0, N);
        pq = newPQ;
    }


    // Get the item at a specific index in the priority queue
    public T get(int i) {
        return pq[i];
    }

    // Move the item at index k up to its correct position
    private void swim(int k) {
        while (k > 0 && less((k - 1) / 2, k)) {
            exchange(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    // Move the item at index k down to its correct position
    private void sink(int k) {
        while (2 * k + 1 < N) {
            int j = 2 * k + 1;
            if (j + 1 < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    // Build a max heap from the current array
    private void buildHeap() {
        for (int k = N / 2 - 1; k >= 0; k--) {
            sink(k);
        }
    }

    // Perform heap sort on the current array
    public void heapSort() {
        buildHeap();
        // Remove the maximum item from the heap and place it at the end of the array
        while (N > 1) {
            exchange(0, --N);
            sink(0);
        }
    }

    // Helper function to check if item at index i is less than item at index j
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // Helper function to exchange items at indices i and j
    private void exchange(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
