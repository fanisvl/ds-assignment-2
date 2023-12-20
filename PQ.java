import java.util.Arrays;

// Priority Queue implementation with generic type T, where T extends Comparable<T>
public class PQ<T extends Comparable<T>> {
    // Constants for default capacity and resize factor
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_RESIZE = 0.75;

    // Array to store the minimum heap elements
    private T[] minHeap;

    // Index array for real elements
    private int[] indexes;

    // Current size of the priority queue
    private int size;

    // Constructor to initialize the priority queue
    public PQ() {
        this.minHeap = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.indexes = new int[100];
        this.size = 0;
    }

    // Method to insert an element into the priority queue
    public void insert(T element) {
        // Check if resizing is required
        
        if (size + 1 > minHeap.length * DEFAULT_RESIZE) {
            resize();
        }

        // Insert the element at the end of the array
        minHeap[size] = element;

        // Update the index of the inserted element
        indexes[hash(element)] = size;

        size++;

        // Restore the heap property by moving the inserted element up the heap
        heapifyUp(size - 1);
    }

    // Method to get and remove the minimum element from the priority queue
    public T getmin() {
        // Remove and return the minimum element
        return remove(min());
    }

    // Method to remove a specific element from the priority queue
    public T remove(T element) {
        int indexToRemove = indexOf(element);

        // Check if the element is not found or the index is out of bounds
        if (indexToRemove == -1 || indexToRemove >= size) {
            throw new IllegalArgumentException("Element not found in the priority queue");
        }

        T removedT = minHeap[indexToRemove];

        // If the element to remove is at the last index, simply decrement the size
        if (indexToRemove == size - 1) {
            size--;

            // Mark that the element no longer exists
            indexes[hash(removedT)] = -1;

            return removedT;
        }

        // Replace the element to remove with the last element in the array
        minHeap[indexToRemove] = minHeap[size - 1];

        // Update the index of the moved element
        indexes[hash(minHeap[indexToRemove])] = indexToRemove;

        size--;

        // Restore the heap property
        if (indexToRemove < size) {
            heapifyDown(indexToRemove);
            heapifyUp(indexToRemove);
        }

        // Mark that the element no longer exists
        indexes[hash(removedT)] = -1;

        return removedT;
    }

    // Method to resize the minHeap array
    private void resize() {
        int newCapacity = minHeap.length * 2;
        T[] newMinHeap = (T[]) new Comparable[newCapacity];

        // Copy elements from the old array to the new array
        for (int i = 0; i < size; i++) {
            newMinHeap[i] = minHeap[i];
        }

        minHeap = newMinHeap;

        // Reassign indexes if necessary
        reassignIndexes();
    }

    // Method to reassign indexes
    private void reassignIndexes() {
        // Initialize all indexes to -1
        Arrays.fill(indexes, -1);

        // Use hash(element) as the index for each element in the heap
        for (int i = 0; i < size; i++) {
            indexes[hash(minHeap[i])] = i;
        }
    }

    // Method to check if the priority queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to calculate the hash value of an element
    private int hash(T element) {
        // Use the ID if the element is an instance of City
        if (element instanceof City) {
            return ((City) element).getID();
        }
        // Otherwise, use the default hashCode
        return element.hashCode();
    }

    // Method to get the index of an element in the priority queue
    private int indexOf(T element) {
        return indexes[hash(element)];
    }

    // Method to heapify up the priority queue from a given index
    private void heapifyUp(int currentIndex) {
        T currentElement = minHeap[currentIndex];

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            T parentElement = minHeap[parentIndex];

            // If the current element is greater than or equal to its parent, break the loop
            if (currentElement.compareTo(parentElement) >= 0) {
                break;
            }

            // Swap the current element with its parent
            swap(currentIndex, parentIndex);

            // Move up to the parent index
            currentIndex = parentIndex;
        }
    }

    // Method to heapify down the priority queue from a given index
    private void heapifyDown(int currentIndex) {
        int leftChildIndex, rightChildIndex, smallestChildIndex;

        while (true) {
            leftChildIndex = 2 * currentIndex + 1;
            rightChildIndex = 2 * currentIndex + 2;
            smallestChildIndex = currentIndex;

            // Find the smallest child among left and right children
            if (leftChildIndex < size && minHeap[leftChildIndex].compareTo(minHeap[smallestChildIndex]) < 0) {
                smallestChildIndex = leftChildIndex;
            }

            if (rightChildIndex < size && minHeap[rightChildIndex].compareTo(minHeap[smallestChildIndex]) < 0) {
                smallestChildIndex = rightChildIndex;
            }

            // If the smallest child is not the current index, swap and continue
            if (smallestChildIndex != currentIndex) {
                swap(currentIndex, smallestChildIndex);
                currentIndex = smallestChildIndex;
            } else {
                // If the current element is smaller than its children, break the loop
                break;
            }
        }
    }

    // Method to swap elements at two given indices
    private void swap(int index1, int index2) {
        T temp = minHeap[index1];
        minHeap[index1] = minHeap[index2];
        minHeap[index2] = temp;

        // Update indexes for the swapped elements
        indexes[hash(minHeap[index1])] = index1;
        indexes[hash(minHeap[index2])] = index2;
    }

    // Method to get the size of the priority queue
    public int size() {
        return size;
    }

    // Method to get the minimum element in the priority queue
    public T min() {
        // Check if the priority queue is empty
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        return minHeap[0];
    }

    // η υλοποιηση τησ μεθοδου ειναι Ν δεν βρηκα πιο απλο και συντομο τροπο!!!!!
    public T getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Min Heap is empty");
        }
    
        T maxItem = minHeap[0];
        for (T item : minHeap) {
            if (item != null && item.compareTo(maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
    
}
