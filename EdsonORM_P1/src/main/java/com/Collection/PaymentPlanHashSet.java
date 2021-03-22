package com.Collection;

import com.Model.PaymentPlan;

public class PaymentPlanHashSet extends GenericCollection<PaymentPlan>{
    public PaymentPlan getById(int id){
        for(Node<PaymentPlan> n: nodes){
            if(n!=null){
                Node<PaymentPlan> iterator = n;
                while (iterator.next!=null){
                    if(iterator.data.getPlanId() == id)
                        return iterator.data;
                    iterator = iterator.next;
                }

                if(iterator.data.getPlanId()==id)
                    return iterator.data;
            }
        }
        return null;
    }

    public double getMonthlyPayment(){
        double MonthlyAmount = 0;
        for(Node<PaymentPlan> n : nodes){
            if(n!=null){
                Node<PaymentPlan> iterator = n;
                while (iterator.next!=null){
                    if(iterator.data.getMonthsLeft() > 0)
                        MonthlyAmount += iterator.data.getMonthlyPayment();
                    iterator = iterator.next;
                }

                if(iterator.data.getMonthsLeft() > 0)
                    MonthlyAmount += iterator.data.getMonthlyPayment();
            }
        }
        return MonthlyAmount;
    }
}
