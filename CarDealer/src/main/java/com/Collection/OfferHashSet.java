package com.Collection;

import com.Model.Car;
import com.Model.Offer;

public class OfferHashSet extends GenericCollection<Offer> {
    private static final int INITIAL_CAPACITY = 8;
    private int size;
    private int i;
    private final Node<Offer>[] nodes;
    private Node<Offer> currentIndex;
    private Node<Offer> prevIndex;

    public OfferHashSet() {
        this(INITIAL_CAPACITY);
        this.size = 0;
        i =0;
        currentIndex = nodes[i];
        prevIndex = null;
    }

    public OfferHashSet(int capacity) {
        this.nodes = new Node[capacity];
    }

    @Override
    public Offer get(Offer o) {
        int index = o.hashCode() % nodes.length;
        Node<Offer> current = nodes[index];

        if(current == null){ // the list is empty
            return null;
        }
        while(current.next != null){
            if(current.data.equals(o)){
                return current.data;
            }
            current = current.next;
        }
        if(current.data.equals(o)){//to check the final node
            return current.data;
        }
        return null;
    }

    @Override
    public boolean remove(Offer o) {
        int index = o.hashCode() % nodes.length;

        Node<Offer> current = nodes[index];
        Node<Offer> prevNode = null;

        //TODO: provide more semantic messages when converting to boolean
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

    @Override
    public void add(Offer o) {
        int index = o.hashCode() % nodes.length;

        Node<Offer> current = nodes[index];
        Node<Offer> newNode = new Node<Offer>(o);

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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Offer getById(String id) {
        for(Node<Offer> n: nodes){
            if(n!=null){
                Node<Offer> iterator = n;
                while (iterator.next!=null){
                    if(String.valueOf(iterator.data.getOfferId()).equalsIgnoreCase(id))
                        return iterator.data;
                    iterator = iterator.next;
                }

                if(String.valueOf(iterator.data.getOfferId()).equalsIgnoreCase(id))
                    return iterator.data;
            }
        }
        return null;
    }

    @Override
    public Offer next() {

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
                prevIndex = nodes[0];
                currentIndex = prevIndex.next;
                i = 0;
            }
            return prevIndex.data;
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int x = 0;
        for (Node<Offer> n : nodes) {
            out.append("Node ").append(x++).append("\n\t"); //This line is for debugging purposes!

            if (n != null) {
                Node<Offer> iterator = n;
                while (iterator.next != null) {
                    out.append(iterator.data.toString()).append("\n\t");
                    iterator = iterator.next;
                }

                out.append(iterator.data.toString()).append("\n");
            } else {
                out.append("\n");
            }
        }
        return out.toString();
    }

    @Override
    boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    protected void clear() {
        for (Node<Offer> n : nodes ){
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