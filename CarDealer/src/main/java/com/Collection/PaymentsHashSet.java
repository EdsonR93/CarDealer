package com.Collection;

import com.Model.Payment;

public class PaymentsHashSet extends GenericCollection<Payment>{
    public Payment getById(int id){
        for(Node<Payment> n: nodes){
            if(n!=null){
                Node<Payment> iterator = n;
                while (iterator.next!=null){
                    if(iterator.data.getPaymentId() == id)
                        return iterator.data;
                    iterator = iterator.next;
                }

                if(iterator.data.getPaymentId()==id)
                    return iterator.data;
            }
        }
        return null;
    }
}
