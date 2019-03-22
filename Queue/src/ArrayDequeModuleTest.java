import queue.ArrayQueueModule;

public class ArrayDequeModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i);
        }
        for (int i = 10; i < 15; i++) {
            ArrayQueueModule.push(i);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.peek() + " " +
                            ArrayQueueModule.remove()
            );
        }
    }

    public static void main(String[] args) {
        fill();
        dump();
        fill();
        ArrayQueueModule.clear();
        dump();
        ArrayQueueModule.enqueue("Hello");
        ArrayQueueModule.enqueue(47);
        dump();
    }
}
