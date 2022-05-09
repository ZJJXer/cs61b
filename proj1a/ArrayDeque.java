/**
 * @Author ZJJ
 * @create 2022/5/8 19:41
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        items = (T []) new Object[INITIAL_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < items.length / 4 && items.length > 8) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resizeHelper(int capacity) {
//        T[] a = (T []) new Object[capacity];
//        if (nextFirst == 0) {
//            System.arraycopy(items, 0, a, 0, size);
//        }else {
//            System.arraycopy(items, 0, a, 0, nextLast + 1);
//            System.arraycopy(items, nextFirst, a, size + nextFirst, size - nextFirst);
//            nextFirst = size + nextFirst;
//        }
//        items = a;

//        T[] a = (T []) new Object[capacity];
//        for (int i = 0; i < size; i++) {
//            nextFirst = Math.floorMod(nextFirst + 1, items.length);
//            a[i] = items[nextFirst];
//        }
//        nextFirst = 0;
//        nextLast = size - 1;
//        items = a;
        T[] temp = items;
        int begin = Math.floorMod(nextFirst + 1, items.length);
        int end = Math.floorMod(nextLast - 1, items.length);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i=begin; i != end; i = Math.floorMod(i + 1, items.length)) {
            items[nextLast] = temp[i];
            nextLast = Math.floorMod(nextLast + 1, items.length);
        }
        items[nextLast] = temp[end];
        nextLast = Math.floorMod(nextLast + 1, items.length);
    }

    public void addFirst(T x) {
        resize();
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    public void addLast(T x) {
        resize();
        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
    }

    public T removeFirst() {
        resize();
        if (size == 0) {
            return null;
        }
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        T res = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return res;
    }

    public T removeLast() {
        resize();
        if (size == 0) {
            return null;
        }
        nextLast = Math.floorMod(nextLast - 1, items.length);
        T res = items[nextLast];
        items[nextLast] = null;
        size--;
        return res;
    }

    private T getFirst() {
        return items[Math.floorMod(nextFirst + 1, items.length)];
    }

    private T getLast() {
        return items[Math.floorMod(nextLast - 1, items.length)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0 ? true : false);
    }

    public void printDeque() {
        while (nextFirst != Math.floorMod(nextLast - 1, items.length)) {
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            System.out.println(items[nextFirst]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T get(int index) {
        if (size < index + 1 || index <0) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index, items.length)];
    }
}
