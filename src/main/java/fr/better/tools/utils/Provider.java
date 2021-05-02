package fr.better.tools.utils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Provider<T> {
    private final List<T> t;

    public Provider() {
        this.t = new LinkedList<>();
    }

    public void add(T t){
        this.t.add(t);
    }

    public void remove(T t){
        this.t.remove(t);
    }

    public T get(int index){
        return t.get(index);
    }

    public int indexOf(T value){
        return t.indexOf(value);
    }

    public void forEach(Consumer<T> consumer){
        t.forEach(t->consumer.accept(t));
    }

    public void sort(Comparator<T> comparator){
        t.sort(comparator);
    }

    public List<T> list() {
        return t;
    }
}
