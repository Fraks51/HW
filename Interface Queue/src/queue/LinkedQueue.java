package queue;

public class LinkedQueue extends AbstractQueue implements Copiable {
    private Node head, tail;

    protected void enqueueImpl(Object element) {
        if (size == 0) {
            head = tail = new Node(element, null);
        } else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
    }

    protected void dequeueImpl() {
        head = head.next;
        if (size == 1) {
            tail = null;
        }
    }

    protected Object elementImpl() {
        return head.value;
    }

    protected void clearImpl() {
        size = 0;
        head = tail = null;
    }

    public LinkedQueue makeCopy() {
        final LinkedQueue copy = new LinkedQueue();
        copy.size = size;
        copy.head = head;
        copy.tail = tail;
        return copy;
    }

    private class Node {
        private Object value;
        private Node next;

        Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}
