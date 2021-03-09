package com.Collection;

import com.Model.Car;


public class CarHashSet extends GenericCollection<Car> {

    public Car getById(int id){
        for(Node<Car> n: nodes){
            if(n!=null){
                Node<Car> iterator = n;
                while (iterator.next!=null){
                    if(iterator.data.getSerialNum() == id)
                        return iterator.data;
                    iterator = iterator.next;
                }

                if(iterator.data.getSerialNum()==id)
                    return iterator.data;
            }
        }
        return null;
    }
}


