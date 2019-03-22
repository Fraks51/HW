import queue.ArrayQueue;

public class ArrayQueueTest {
    public static void fill(ArrayQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 10; i < 15; i++) {
            queue.push(i);
        }
    }

    public static void dump(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                    queue.size() + " " +
                            queue.element() + " " +
                            queue.dequeue()
            );
            System.out.println(
                    queue.size() + " " +
                            queue.peek() + " " +
                            queue.remove()
            );
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
        fill(queue);
        queue.clear();
        dump(queue);
        queue.enqueue("Hello");
        queue.enqueue(47);
        dump(queue);
    }
}
