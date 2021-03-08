package com.Collection;

import com.Model.Car;

/**
 * should have
 * No duplicates (implementation detail)
 * Do not care about the order (implementation detail)
 * index should always point to the last valid object
 * No gaps
 * Resizable with conditions
 * View all elements of the collection
 *
 */
public abstract class GenericCollection<T> {

    /**
     * Optional size number for non expandable subclasses
     */
    protected int maxSize;

    public abstract Object get(T c);
    public abstract boolean remove(T c);
    public abstract void add(T c);

    public abstract int size();

    public abstract Object getById(int id);

    public abstract Object next();

    public abstract String toString();

    abstract boolean isEmpty();

    protected abstract void clear();

}
