public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;

    public MaxPQ() {
        this.pq = (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.N = 0;
    }
    private void buildHeap() {
        for (int k = N / 2 - 1; k >= 0; k--) {
            sink(k);
        }
    }
    public void heapSort() {
        buildHeap();
        // Remove max from heap and place it at the back of the array.
        while (N > 1) {
            exchange(0, --N);
            sink(0);
        }
    }
    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        // Check available space
        if (N == pq.length - 1) grow();
        pq[N] = key;
        swim(N);
        N++;
    }

    private void grow() {
        Key[] newPQ = (Key[]) new Comparable[pq.length + AUTOGROW_SIZE];
        // copy array
        for (int i = 0; i < N; i++) {newPQ[i] = pq[i];}
        pq = newPQ;
    }

    public Key delMax() {
        Key max = pq[1];
        exchange(1, N);
        sink(1);
        pq[N] = null;
        N--;
        return max;
    }

    private void swim(int k) {
        while (k > 0 && less((k - 1) / 2, k)) {
            exchange(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    private void sink(int k) {
        while (2*k + 1 < N) {
            int j = 2 * k + 1;
            if (j + 1 < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
