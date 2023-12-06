public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exchange(1, N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }

    private void swim(int k) {
        // parent of k is at k/2
        while (k > 1 && less(k/2, k)) {
            exchange(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        // 2k and 2k+1 are children of parent node k
        while (2*k <= N) {
            int j = 2*k;
            // If true, 2k+1 is largest child (j++)
            if (j < N && less(j, j+1)) j++;
            // If k is larger than j (child), sinking is finished.
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
