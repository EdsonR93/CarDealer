package com.Collection;

import com.Model.Car;


public class CarHashSet extends GenericCollection {
    //TODO: this class needs an Iterator!!
    private static final int INITIAL_CAPACITY = 8;
    private int size;
    private int i;
    private final MyNode<Car>[] nodes;
    private MyNode<Car> currentIndex;
    private MyNode<Car> prevIndex;


    public CarHashSet(){
        this(INITIAL_CAPACITY);
        this.size = 0;
        i =0;
        currentIndex = nodes[i];
        prevIndex = null;
    }

    public CarHashSet(int capacity){
        this.nodes = new MyNode[capacity];
    }

    @Override
    boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void add(Car c){
        int index = c.hashCode() % nodes.length;

        MyNode<Car> current = nodes[index];
        MyNode<Car> newNode = new MyNode<Car>(c);

        if(current == null){ // the list is empty
            nodes[index] = newNode;
            size++;
            return;
        }
        while(current.next != null){
            if(current.data.equals(c)){
                return;
            }
            current = current.next;
        }

        if(!current.data.equals(c)){
            current.next = newNode;
            size++;
            return;
        }

        System.out.println("Duplicate value");
    }

    @Override
    public boolean remove(Car c) {
        int index = c.hashCode() % nodes.length;

        MyNode<Car> current = nodes[index];
        MyNode<Car> prevNode = null;

        //TODO: provide more semantic messages when converting to boolean
        if(current == null){ // the list is empty
            return true;
        }
        while(current.next != null){
            if(current.data.equals(c)){ // current is the node we want to remove
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
        if(current.data.equals(c)){//to check the final node
            if (prevNode != null) {
                prevNode.next = null;
                size--;
                return true;
            }else{
                System.out.println("something very bad happened");
                return false;
            }
        }
        return false;
    }

    @Override
    protected void clear() {
        for (MyNode<Car> n : nodes ){
            n = null;
        }
        size = 0;
    }

    @Override
    public Car get(Car c) {

        int index = c.hashCode() % nodes.length;
        MyNode<Car> current = nodes[index];

        if(current == null){ // the list is empty
            return null;
        }
        while(current.next != null){
            if(current.data.equals(c)){
                return current.data;
            }
            current = current.next;
        }
        if(current.data.equals(c)){//to check the final node
           return current.data;
        }
        return null;
    }
    @Override
    public Car next() {
        boolean lookForNext = false;

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
        int x =0;
        for(MyNode<Car> n: nodes){
            out.append("Node ").append(x++).append("\n\t");

            if(n!=null){
                MyNode<Car> iterator = n;
                while (iterator.next!=null){
                    out.append(iterator.data.toString()).append("\n\t");
                    iterator = iterator.next;
                }

                out.append(iterator.data.toString()).append("\n");
            }else{
                out.append("\n");
            }
        }
        return out.toString();
    }
}

class MyNode<T> {
    T data;
    MyNode<T> next;

    public MyNode(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}


