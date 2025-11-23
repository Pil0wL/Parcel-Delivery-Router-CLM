


package parceldeliveryproject.ds;

public class StackX<T> // imported from the midterm exam
{
    private final int maxSize; 
    private final T[] stackArray; 

    private int top;
    public StackX(int max) {
        maxSize = max;

        stackArray = (T[]) new Object[maxSize];

        top = -1;
    }
    public void push(T j) {
        stackArray[++top] = j; 
    }

    public T pop() {
        return stackArray[top--]; 
    }

    public T peek() {
        return stackArray[top];
    }
    
    public StackX<T> cloneMe() {
        StackX<T> created = new StackX<>(maxSize);
        
        for (int index = 0; index < (top + 1); index++) {
            created.push(stackArray[index]);
        }
        
        return created;
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public boolean isFull() {
        return (top == (maxSize - 1));
    }
}