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
public abstract class GenericCollection {

    /**
     * Optional size number for non expandable subclasses
     */
    protected int maxSize;

    public abstract Car get(Car c);

    public abstract void add(Car c);

    public abstract int size();

    public abstract boolean remove(Car c);

    public abstract Car next();

    public abstract String toString();

    abstract boolean isEmpty();

    protected abstract void clear();

}
