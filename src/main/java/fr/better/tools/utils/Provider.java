package fr.better.tools.utils;

import java.util.*;

public class Provider<T> implements List<T>{

    private final List<T> l;

    public Provider() {
        this.l = new LinkedList<>();
    }


    @Override
    public int size() {
        return l.size();
    }

    @Override
    public boolean isEmpty() {
        return size() != 0;
    }

    @Override
    public boolean contains(Object o) {
        return l.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return l.iterator();
    }

    @Override
    public Object[] toArray() {
        return l.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return l.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return l.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return l.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return l.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return l.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return l.addAll(index,c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return l.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return l.retainAll(c);
    }

    @Override
    public void clear() {
        l.clear();
    }

    @Override
    public T get(int index) {
        return l.get(index);
    }

    @Override
    public T set(int index, T element) {
        return l.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        l.add(index, element);
    }

    @Override
    public T remove(int index) {
        return l.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return l.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return l.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return l.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return l.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return l.subList(fromIndex, toIndex);
    }
}
