package parceldeliveryproject.ds;
import parceldeliveryproject.model.Parcel;

public class PriorityQueue {
    Parcel[] heap;
    int size;
    
    public PriorityQueue(int capacity) {
        heap = new Parcel[capacity];
        size = 0;
    }

    public void insert(Parcel p) {
        if (size == heap.length) {
            System.out.println("Queue is Full");
            return;
        }
        heap[size] = p;
        int i = size;
        size++;
        
        while (i > 0 && !heap[parent(i)].fragperi && heap[i].fragperi) {
            Parcel temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }
    
    public Parcel remove() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return null;
        }
        Parcel root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
        return root;
    }
    
    private void heapify(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int highest = i;
        
        if (left < size && heap[left].fragperi && !heap[highest].fragperi)
            highest = left;
        if (right < size && heap[right].fragperi && !heap[highest].fragperi)
            highest = right;
        
        if (highest != i) {
            Parcel temp = heap[i];
            heap[i] = heap[highest];
            heap[highest] = temp;
            heapify(highest);
        }
    }
    
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    public void display() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.println("\n=====Priority Delivery Queue (Fragile Fisrt):=====");
        for (int i = 0; i < size; i++) {
            System.out.println(" " + heap[i]);
        }
    }
}
