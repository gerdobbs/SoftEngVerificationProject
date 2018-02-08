/*
Author:         Ger Dobbs
Date:           12/02/2018
Student No.:    C00196843
Lecturer:       Dr. Chris Meudec
Purpose:        A verification project as follows:
                Task 1: Create Black Box test cases based on a specification and methods signature.
*/


import org.junit.Before;
import org.junit.Assert;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class DobbsGerTesting {
    ArrayList<Period> discountPeriods;      //Used to test Rate()
    ArrayList<Period> normalPeriods;
    ArrayList<Period> chargeDiscountPeriods;//Used to test charge()
    ArrayList<Period> chargeNormalPeriods;
    Rate rate;
    //Tests for Rate Constructor
    @Before
    public void beforeTest(){
        discountPeriods= new ArrayList<Period>(){{
            add(new Period(5,6));
            add(new Period(13,14));
        }};
        normalPeriods= new ArrayList<Period>(){
            {
                add(new Period(6, 7));
            }};
        chargeDiscountPeriods= new ArrayList<Period>(){{
            add(new Period(2,4));
            add(new Period(18,19));
        }};
        chargeNormalPeriods= new ArrayList<Period>(){{
            add(new Period(0,2));
            add(new Period(13,17));
            add(new Period(23,24));
        }};
    }

    //Rate Test #1:
    @org.junit.Test
    public void kindIsValid(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(-1),new BigDecimal(-2),discountPeriods,normalPeriods);
    }

    //Rate Test #2:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateLessThanZero(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(-1),new BigDecimal(-2),discountPeriods,normalPeriods);
    }

    //Rate Test #3:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateEqualsZero(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(0),new BigDecimal(-1),discountPeriods,normalPeriods);
    }

    //Rate Test #4
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateEqualsChar(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal('B'),new BigDecimal(-1),discountPeriods,normalPeriods);
    }

    //Rate Test #5: lower boundary for normalRate =1
    //Test will fail on discountedRate =0
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateEqualsOne(){
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(1),new BigDecimal(0),discountPeriods,normalPeriods);
    }

    //Rate Test #6:  mid range of normalrate = 10
    @org.junit.Test
    public void normalRateMidRange(){
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(10),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    //Rate Test #7:  Max of normalrate = 100000000
    @org.junit.Test
    public void normalRateMax(){
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(10000000),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    //Rate Test #8:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateLessThanZero(){
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(5),new BigDecimal(-1),discountPeriods,normalPeriods);
    }

    //Rate Test #9:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateEqualsZero(){
        rate = new Rate(CarParkKind.STAFF, new BigDecimal(5),new BigDecimal(0),discountPeriods,normalPeriods);
    }

    //Rate Test #10
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountRateEqualsChar(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(2),new BigDecimal('B'),discountPeriods,normalPeriods);
    }

    //Rate Test #11:
    @org.junit.Test
    public void discountRateEqualsOne(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(5),new BigDecimal(1),discountPeriods,normalPeriods);
    }

    //Rate Test #12:
    @org.junit.Test
    public void discountRateMidRange(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(11),new BigDecimal(10),discountPeriods,normalPeriods);
    }

    //Rate Test #13:
    @org.junit.Test
    public void discountRateMax(){
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(10000001),new BigDecimal(10000000),discountPeriods,normalPeriods);
    }

    //Rate Test #14:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateLessThanDiscountRate(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(2),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #15:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalRateEqualsDiscountRate(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(2),new BigDecimal(2),discountPeriods,normalPeriods);
    }

    //Rate Test #16:
    @org.junit.Test
    public void normalRateGreaterThanDiscountRate(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #17:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountPeriodsOverlap(){
        discountPeriods= new ArrayList<Period>(){{
            add(new Period(5,7));
            add(new Period(6,8));
        }};
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }


    //Rate Test #18:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void discountPeriodsOverlapNormalPeriods(){
        discountPeriods= new ArrayList<Period>(){{
            add(new Period(5,7));
        }};
        rate = new Rate(CarParkKind.VISITOR, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #19:
    @org.junit.Test
    public void discountPeriodsLengthIsMax(){
        discountPeriods= new ArrayList<Period>(){{  //Create ArrayList of size Max = 24
            for(int i=1;i<23;i++){
                discountPeriods.add(new Period(i,i+1));
            }
        }};
        normalPeriods= new ArrayList<Period>();     //Create ArrayList of size = 0
        rate = new Rate(CarParkKind.STAFF, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #20:
    @org.junit.Test
    public void discountPeriodsLengthIsZero(){
        normalPeriods= new ArrayList<Period>(){{  //Create ArrayList of size = 0
            for(int i=1;i<23;i++){
                normalPeriods.add(new Period(i,i+1));
            }
        }};
        discountPeriods= new ArrayList<Period>();     //Create ArrayList of size = 0
        rate = new Rate(CarParkKind.STAFF, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #21:
    @org.junit.Test
    public void discountPeriodNotOverlapWithNormalPeriods(){
        normalPeriods= new ArrayList<Period>(){{
            add(new Period(5,7));
        }};
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #22:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalPeriodsOverlap(){
        normalPeriods= new ArrayList<Period>(){{
            add(new Period(2,4));
            add(new Period(3,4));
        }};
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #23:
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void normalPeriodsOverlapDiscountPeriods(){
        normalPeriods= new ArrayList<Period>(){{
            add(new Period(12,14));
        }};
        rate = new Rate(CarParkKind.STUDENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #24:
    @org.junit.Test
    public void normalPeriodsLengthIsMax(){
        normalPeriods= new ArrayList<Period>(){{  //Create ArrayList of size Max = 24
            for(int i=1;i<23;i++){
                normalPeriods.add(new Period(i,i+1));
            }
        }};
        discountPeriods= new ArrayList<Period>();     //Create ArrayList of size = 0
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #25:
    @org.junit.Test
    public void normalPeriodsLengthIsZero(){
        discountPeriods= new ArrayList<Period>(){{  //Create ArrayList of size = 0
            for(int i=1;i<23;i++){
                discountPeriods.add(new Period(i,i+1));
            }
        }};
        normalPeriods= new ArrayList<Period>();     //Create ArrayList of size = 0
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #26:
    @org.junit.Test
    public void normalPeriodsAndDiscountPeriodsEmpty(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }

    //Rate Test #27:
    @org.junit.Test
    public void normalPeriodNotOverlapWithDiscountPeriods(){
        discountPeriods= new ArrayList<Period>();
        normalPeriods= new ArrayList<Period>();
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(6),new BigDecimal(5),discountPeriods,normalPeriods);
    }



    //Calculate Test #1:
    @org.junit.Test
    public void normalPeriodStartsAtZero(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(3),rate.calculate(new Period(0,1)));
    }

    //Calculate Test #2:
    @org.junit.Test
    public void normalPeriodEndsAtTwentyFour(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(3),rate.calculate(new Period(23,24)));
    }

    //Calculate Test #3:
    @org.junit.Test
    public void normalPeriodRandom(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(9),rate.calculate(new Period(14,17)));
    }

    //Calculate Test #4: chargeNormalPeriods & chargeDiscountPeriods swapped in constructor to match test design
    @org.junit.Test
    public void discountPeriodStartsAtZero(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(4),new BigDecimal(1),chargeNormalPeriods,chargeDiscountPeriods);
        assertEquals(new BigDecimal(2),rate.calculate(new Period(0,2)));
    }

    //Calculate Test #5: chargeNormalPeriods & chargeDiscountPeriods swapped in constructor to match test design
    @org.junit.Test
    public void discountPeriodEndsAtTwentyFour(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(4),new BigDecimal(1),chargeNormalPeriods,chargeDiscountPeriods);
        assertEquals(new BigDecimal(1),rate.calculate(new Period(23,24)));
    }

    //Calculate Test #6: chargeNormalPeriods & chargeDiscountPeriods swapped in constructor to match test design
    @org.junit.Test
    public void discountPeriodRandom(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(4),new BigDecimal(1),chargeNormalPeriods,chargeDiscountPeriods);
        assertEquals(new BigDecimal(3),rate.calculate(new Period(14,17)));
    }

    //Calculate Test #7: chargeNormalPeriods & chargeDiscountPeriods swapped in constructor to match test design
    @org.junit.Test
    public void noCharge(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(4),new BigDecimal(1),chargeNormalPeriods,chargeDiscountPeriods);
        assertEquals(new BigDecimal(0),rate.calculate(new Period(4,5)));
    }

    //Calculate Test #8: Test the calculation when the stay period includes a free period & a Normal Period
    @org.junit.Test
    public void freePeriodAndNormalPeriod(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(3),rate.calculate(new Period(16,18)));
    }

    //Calculate Test #9: Test the calculation when the stay period includes a normal period & a discount Period
    @org.junit.Test
    public void normalPeriodAndDiscountPeriod(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(5),rate.calculate(new Period(1,3)));
    }

    //Calculate Test #10: Test the calculation when the stay period includes a discount period & a free Period
    @org.junit.Test
    public void discountPeriodAndFreePeriod(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(2),rate.calculate(new Period(18,20)));
    }

    //Calculate Test #11: Test the calculation when the stay period includes a free period & a discount  & normal Period
    @org.junit.Test
    public void freePeriodAndDiscountPeriodAndNormalPeriod(){
        rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal(3),new BigDecimal(2),chargeDiscountPeriods,chargeNormalPeriods);
        assertEquals(new BigDecimal(8),rate.calculate(new Period(16,24)));
    }



}