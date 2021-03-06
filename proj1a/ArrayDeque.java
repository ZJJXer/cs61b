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
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < items.length / 4 && items.length > INITIAL_CAPACITY) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    /**复制到新数组，nextFirst指向新数组的0，nextLast指向新数组的size + 1.
     * floorMod()方法很妙，妙在floorMod(-1, 8) = 7，这样就实现了addFirst从头跳到尾。*/
    private void resizeHelper(int capacity) {
        T[] a = (T []) new Object[capacity];
        for (int i = 1; i < size + 1; i++) {
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            a[i] = items[nextFirst];
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
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

    /** 当nextFirst == Math.floorMod(nextLast - 1, items.length) 时，
     * nextFirst和nextLast其实是相邻的，这时候就停止循环了，最后一个item已经打印出来了。*/
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
        return items[Math.floorMod(nextFirst + index + 1, items.length)];
    }
}
