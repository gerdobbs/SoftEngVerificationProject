/*
Author:         Ger Dobbs
Date:           12/02/2018
Student No.:    C00196843
Lecturer:       Dr. Chris Meudec
Purpose:        A verification project as follows:
                Task 1: Create Black Box test cases based on a specification and methods signature.
*/

import java.math.BigDecimal;
import java.util.ArrayList;

public class Rate {
    CarParkKind kind;
    BigDecimal normalRate;
    BigDecimal discountedRate;


    public Rate(CarParkKind kind, BigDecimal normalRate, BigDecimal discountedRate, ArrayList<Period> discountPeriods, ArrayList<Period> normalPeriods) throws IllegalArgumentException{
        this.kind = kind;
        this.normalRate = normalRate;
        this.discountedRate = discountedRate;
        //Checks If normalRate > 0
        if(normalRate.compareTo(new BigDecimal(0)) < 1){
            throw new IllegalArgumentException();
        }
        if(discountedRate.compareTo(new BigDecimal(0)) < 1){
            throw new IllegalArgumentException();
        }
        if(normalRate.compareTo(discountedRate)<1){
            throw new IllegalArgumentException();
        }
    }
    public BigDecimal calculate(Period stayPeriod){
        return new BigDecimal(5);
    }

}
