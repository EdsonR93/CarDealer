package com.Collection;


import com.Model.Offer;

public abstract class GenericCollection<T> {

    private static final int INITIAL_CAPACITY = 8;
    private int size;
    private int i;
    protected final Node<T>[] nodes;
    private Node<T> currentIndex;
    private Node<T> prevIndex;

    public GenericCollection() {
        this(INITIAL_CAPACITY);
        this.size = 0;
        i =0;
        currentIndex = nodes[i];
        prevIndex = null;
    }

    public GenericCollection(int capacity) {
        this.nodes = new Node[capacity];
    }


    public T get(T t) {
        int index = t.hashCode() % nodes.length;
        Node<T> current = nodes[index];

        if(current == null){ // the list is empty
            return null;
        }
        while(current.next != null){
            if(current.data.equals(t)){
                return current.data;
            }
            current = current.next;
        }
        if(current.data.equals(t)){//to check the final node
            return current.data;
        }
        return null;
    }


    public boolean Remove(T o) {
        int index = o.hashCode() % nodes.length;

        Node<T> current = nodes[index];
        Node<T> prevNode = null;


        if(current == null){ // the list is empty
            return true;
        }
        while(current.next != null){
            if(current.data.equals(o)){ // current is the node we want to remove
                if(prevNode != null){
                    prevNode.next = current.next;
                }else{
                    current = current.next;
                }
                size--;
                return true;
            }
            prevNode = current;
            current = current.next;
        }
        if(current.data.equals(o)){//to check the final node
            if (prevNode != null) {
                prevNode.next = null;
                size--;
                return true;
            }else{
                System.out.println("Couldnt remove from OfferHashSet");
                return false;
            }
        }
        return false;
    }


    public void Add(T o) {
        int index = o.hashCode() % nodes.length;

        Node<T> current = nodes[index];
        Node<T> newNode = new Node<T>(o);

        if(current == null){ // the list is empty
            nodes[index] = newNode;
            size++;
            return;
        }
        while(current.next != null){
            if(current.data.equals(o)){
                return;
            }
            current = current.next;
        }

        if(!current.data.equals(o)){
            current.next = newNode;
            size++;
            return;
        }

        System.out.println("Duplicate value");
    }

    public int Size() {
        return this.size;
    }

    public abstract T getById(int id);

    public T Next() {

        if(size ==0)
            return null;

        if(currentIndex != null){
            if(prevIndex==null) {
                prevIndex = currentIndex;
                currentIndex = currentIndex.next;
                return prevIndex.data;
            }else{
                prevIndex = prevIndex.next;
                currentIndex = currentIndex.next;

                return prevIndex.data;
            }
        }else{
            ++i;
            while (i < nodes.length && nodes[i] == null) {
                i++;
            }
            if(i < nodes.length && nodes[i]!=null){
                prevIndex = nodes[i];
                currentIndex = prevIndex.next;
            }else{
                return null;
            }
            return prevIndex.data;
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int x = 0;
        out.append("\t");
        for (Node<T> n : nodes) {
            if (n != null) {
                Node<T> iterator = n;
                while (iterator.next != null) {
                    out.append(iterator.data.toString()).append("\n\t");
                    iterator = iterator.next;
                }
                out.append(iterator.data.toString()).append("\n\t");
            }
        }
        return out.toString();
    }


    boolean isEmpty() {
        return this.size == 0;
    }


    protected void Clear() {
        for (Node<T> n : nodes ){
            n = null;
        }
        size = 0;
    }

}
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}