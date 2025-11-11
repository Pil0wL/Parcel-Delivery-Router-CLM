package parcelrouter;

public class Queue {
    Parcel[] data;
    int front, rear, size;
    
    public Queue(int capacity) {
        data = new Parcel[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }     
    
    public void enqueue(Parcel p) {
        if (size == data.length) {
            System.out.println("Queue is Full");
            return;
        }
        rear = (rear + 1) % data.length;
        data[rear] = p;
        size++;
    }
    
    public Parcel dequeue() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return null;  
        }
        Parcel p = data[front];
        front = (front + 1) % data.length;
        size--;
        return p;
    }
        
    public void display() {
        if (size == 0) {
            System.out.println("Queue is Empty");
            return;
        }
        System.out.println("\nPickup Queue:");
        for (int i = 0; i < size; i++) {
            int index = (front + 1) % data.length;
            System.out.println(" " + data[index]);
        }
    }
}
    
