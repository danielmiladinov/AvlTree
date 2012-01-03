package net.miladinov.util;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public void push(T value) {
        storage.addFirst(value);
    }

    public T pop() {
        return storage.removeFirst();
    }

    public T peek() {
        return storage.getFirst();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
