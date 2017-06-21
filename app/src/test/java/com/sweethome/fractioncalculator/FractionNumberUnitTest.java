package com.sweethome.fractioncalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andrew on 2017/5/28.
 */

public class FractionNumberUnitTest
{
    @Test
    public void fractionConstructor_isCorrect() throws Exception
    {
        double v=0.0;
        double v1_2=1.0/2.0;
        double v2_3=2.0/3.0;
        double v3_5=3.0/5.0;
        double v4_7=4.0/7.0;
        double v5_11=5.0/11.0;
        double v6_1=6.0/1.0;
        Fraction f=new Fraction();
        Fraction f1_2=new Fraction(1,2);
        Fraction f2_3=new Fraction(2,3);
        Fraction f3_5=new Fraction(3,5);
        Fraction f4_7=new Fraction(4,7);
        Fraction f5_11=new Fraction(5,11);
        Fraction f5_11_clone=new Fraction(f5_11);
        Fraction f6_1=new Fraction(6);

        assertEquals(v,f.doubleValue(),1e-7);
        assertEquals(v1_2,f1_2.doubleValue(),1e-7);
        assertEquals(v2_3,f2_3.doubleValue(),1e-7);
        assertEquals(v3_5,f3_5.doubleValue(),1e-7);
        assertEquals(v4_7,f4_7.doubleValue(),1e-7);
        assertEquals(v5_11,f5_11.doubleValue(),1e-7);
        assertEquals(v5_11,f5_11_clone.doubleValue(),1e-7);
        assertEquals(v6_1,f6_1.doubleValue(),1e-7);
    }
    @Test
    public void fractionOperation_isCorrect() throws Exception
    {
        Fraction f1_2=new Fraction(1,2);
        Fraction f2_3=new Fraction(2,3);
        Fraction f3_5=new Fraction(3,5);
        Fraction f4_7=new Fraction(4,7);
        Fraction f5_11=new Fraction(5,11);
        Fraction f6_13=new Fraction(6,13);
        Fraction f14_7=new Fraction(14,7);
        Fraction f9999=new Fraction(9999,1);
        Fraction f99999999=new Fraction(99999999,1);



        double v1_2_add_f2_3=(1.0/2.0)+(2.0/3.0);
        double v3_5_dec_f4_7=(3.0/5.0)-(4.0/7.0);
        double v5_11_mul_f6_13=(5.0/11.0)*(6.0/13.0);
        double v6_13_div_f2_3=(6.0/13.0)/(2.0/3.0);
        Fraction f1_2_add_f2_3=(new Fraction(f1_2)).add(f2_3);
        Fraction f3_5_dec_f4_7=(new Fraction(f3_5)).dec(f4_7);
        Fraction f5_11_mul_f6_13=(new Fraction(f5_11)).mul(f6_13);
        Fraction f6_13_div_f2_3=(new Fraction(f6_13)).div(f2_3);

        assertEquals(v1_2_add_f2_3,f1_2_add_f2_3.doubleValue(),1e-7);
        assertEquals(v3_5_dec_f4_7,f3_5_dec_f4_7.doubleValue(),1e-7);
        assertEquals(v5_11_mul_f6_13,f5_11_mul_f6_13.doubleValue(),1e-7);
        assertEquals(v6_13_div_f2_3,f6_13_div_f2_3.doubleValue(),1e-7);
        assertEquals(2.0,f14_7.doubleValue(),1e-7);
        assertEquals(9999.0*9999.0,f9999.mul(f9999).doubleValue(),1e-7);
        assertEquals(99999999.0*99999999.0,f99999999.mul(f99999999).doubleValue(),1e-7);

    }
}
