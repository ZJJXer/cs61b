/**
 * @Author ZJJ
 * @create 2022/5/8 12:54
 */

public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node prev;
        public Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
//        public Node(LinkedListDeque<T>.Node prev, T item, LinkedListDeque<T>.Node next) {
//            this.prev = prev;
//            this.item = item;
//            this.next = next;
//        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, (T) new Object(), null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T x) {
//        sentinel = new Node(null, (T) new Object(), null);
//        sentinel.next = new Node(sentinel, x, sentinel);
//        sentinel.prev = sentinel.next;
//        size = 1;
//    }

    /** Adds an item to the front of the list. */
    public void addFirst(T x) {
        Node p = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    /** Adds an item to the end of the list. */
    public void addLast(T x) {
        Node p = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item
     * exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return res;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item
     * exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return res;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item,
     * and so forth. If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (size < index + 1 || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (size < index + 1 || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node node, int i) {
        if (i == 0) {
            return node.item;
        }
        return getRecursive(node.next, i - 1);
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node ptr = sentinel;
        while(ptr.next != sentinel) {
            System.out.print(ptr.next.item);
            System.out.print(" ");
            ptr = ptr.next;
        }
        System.out.println();
    }
}
