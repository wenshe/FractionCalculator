package com.sweethome.fractioncalculator;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andrew on 2017/6/21.
 */

public class CalculatorUnitTest
{
    private void calculatorReset_isCorrect(Calculator calculator)
    {
        Fraction result=calculator.getResult();
        //result = 0/1
        assertEquals(result.doubleValue(),0.0f,1e-7);
        assertEquals(result.getNumerator(),0);
        assertEquals(result.getDenominator(),1);

        //input value _v[]={0,0}
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);

        //Calculator.CalcOperator.None
        assertEquals(calculator.getCalcOperator(), Calculator.CalcOperator.None);

        //Calculator.CalcStatus.OK
        assertEquals(calculator.getCalcStatus(),Calculator.CalcStatus.OK);
}


    @Test
    public void calculatorConstructor_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculatorReset_isCorrect(calculator);
    }

    @Test
    public void calculatorClear_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();

        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Clear();
        calculatorReset_isCorrect(calculator);

        calculator.NumPad(7);
        calculator.NumPad(8);
        calculator.NumPad(9);
        calculator.Plus();
        calculator.Clear();
        calculatorReset_isCorrect(calculator);

        calculator.NumPad(7);
        calculator.NumPad(8);
        calculator.NumPad(9);
        calculator.Equal();
        calculator.Clear();
        calculatorReset_isCorrect(calculator);

        calculator.NumPad(7);
        calculator.NumPad(8);
        calculator.NumPad(9);
        calculator.Plus();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Clear();
        calculatorReset_isCorrect(calculator);


        calculator.NumPad(7);
        calculator.NumPad(8);
        calculator.NumPad(9);
        calculator.Plus();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Equal();
        calculator.Clear();
        calculatorReset_isCorrect(calculator);
    }

    @Test
    public void calculatorClearError_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.ClearError();
        calculatorReset_isCorrect(calculator);

        calculator.Clear();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Plus();
        calculator.NumPad(4);
        calculator.NumPad(5);
        calculator.NumPad(6);
        calculator.ClearError();
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
        assertEquals(calculator.getCalcOperator(),Calculator.CalcOperator.Plus);
        assertEquals(calculator.getResult().doubleValue(),123,1e-7);


        calculator.Clear();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Slash();
        calculator.NumPad(4);
        calculator.NumPad(5);
        calculator.NumPad(6);
        calculator.ClearError();
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
    }

    @Test
    public void calculatorBackSp_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        assertEquals(calculator.getInputValue(0),123);
        calculator.BackSp();
        assertEquals(calculator.getInputValue(0),12);
        calculator.BackSp();
        assertEquals(calculator.getInputValue(0),1);
        calculator.BackSp();
        assertEquals(calculator.getInputValue(0),0);
        calculator.BackSp();
        assertEquals(calculator.getInputValue(0),0);

        calculator.Clear();
        calculator.NumPad(1);
        calculator.NumPad(2);
        calculator.NumPad(3);
        calculator.Slash();
        calculator.NumPad(4);
        calculator.NumPad(5);
        calculator.NumPad(6);
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),456);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),45);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),4);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),0);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),0);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),12);
        assertEquals(calculator.getInputValue(1),0);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),1);
        assertEquals(calculator.getInputValue(1),0);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
        calculator.BackSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
    }

    @Test
    public void calculatorNumPad_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        int v=0;
        for (int i=0;i<9;i++)
        {
            v=v*10+i;
            calculator.NumPad(i);
            assertEquals(calculator.getInputValue(calculator.getInputIndex()), v);
        }
        calculator.NumPad(9); //test the MaxNumber is 8 digi number
        assertEquals(calculator.getInputValue(calculator.getInputIndex()), 12345678);
    }

    @Test
    public void calculatorPlus_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.NumPad(1);
        calculator.Plus();
        calculator.NumPad(2);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),3);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.Clear();
        calculator.NumPad(4);
        calculator.NumPad(1);
        calculator.NumPad(5);
        calculator.Plus();
        calculator.NumPad(7);
        calculator.NumPad(5);
        calculator.NumPad(9);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),1174);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.Clear();
        calculator.NumPad(2);
        calculator.Slash();
        calculator.NumPad(3);
        calculator.Plus();
        calculator.NumPad(8);
        calculator.Slash();
        calculator.NumPad(9);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),42);
        assertEquals(calculator.getResult().getDenominator(),27);
        assertEquals(calculator.getResult().doubleValue(),42.0/27.0,1e-7);
    }

    @Test
    public void calculatorMinus_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.NumPad(1);
        calculator.NumPad(9);
        calculator.Minus();
        calculator.NumPad(2);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),17);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.Clear();
        calculator.NumPad(4);
        calculator.NumPad(1);
        calculator.NumPad(5);
        calculator.Minus();
        calculator.NumPad(7);
        calculator.NumPad(5);
        calculator.NumPad(9);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),-344);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.Clear();
        calculator.NumPad(5);
        calculator.Slash();
        calculator.NumPad(3);
        calculator.Minus();
        calculator.NumPad(8);
        calculator.Slash();
        calculator.NumPad(9);
        calculator.Equal();
        assertEquals(calculator.getResult().getNumerator(),21);
        assertEquals(calculator.getResult().getDenominator(),27);
        assertEquals(calculator.getResult().doubleValue(),21.0/27.0,1e-7);
    }
}
