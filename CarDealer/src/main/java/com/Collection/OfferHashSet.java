package com.Collection;

import com.Model.Car;
import com.Model.Offer;

public class OfferHashSet extends GenericCollection<Offer> {

    @Override
    public Offer getById(int id) {
        for (Node<Offer> n : nodes) {
            if (n != null) {
                Node<Offer> iterator = n;
                while (iterator.next != null) {
                    if (iterator.data.getOfferId() == id)
                        return iterator.data;
                    iterator = iterator.next;
                }

                if (iterator.data.getOfferId() == id)
                    return iterator.data;
            }
        }
        return null;
    }

}