package queue;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queue extends Copiable {
    //pre: element!= Null
    //post: size = size' + 1
    void enqueue(Object element);

    //pre: size > 0
    //post: size = size' - 1 & R != Null
    Object dequeue();

    //pre: size > 0
    //post: R != Null & size = size'
    Object element();

    //post: if (size = 0) {R = 1} else {R = 0}
    boolean isEmpty();

    //post: R = size
    int size();

    //post: head = tail & size = 0 -> queue is empty
    void clear();

    //pre: all queue's elements != null
    //post: R = Array[0...size - 1] & Array[0] ~ Head & Array[size - 1] ~ Tail
    Object[] toArray();

    //pre: all queue's elements != null
    //post: R = newQueue & newQueue = Queue (size newQueue = size Queue, head newQueue = head Queue, etc)
    Queue makeCopy();

    //post: R = newQueue & all newQueue's elements match Predicate & predicate != Null
    Queue filter(Predicate<Object> predicate);

    //post: R = newQueue & size newQueue = size Queue & all newQueue's elements( func(head newQueue) = head Queue, etc)
    // & & function != Null
    Queue map(Function<Object, Object> function);
}
