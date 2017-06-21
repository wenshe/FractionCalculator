package com.sweethome.fractioncalculator;

import org.junit.Test;

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

        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.clear();
        calculatorReset_isCorrect(calculator);

        calculator.numPad(7);
        calculator.numPad(8);
        calculator.numPad(9);
        calculator.plus();
        calculator.clear();
        calculatorReset_isCorrect(calculator);

        calculator.numPad(7);
        calculator.numPad(8);
        calculator.numPad(9);
        calculator.equal();
        calculator.clear();
        calculatorReset_isCorrect(calculator);

        calculator.numPad(7);
        calculator.numPad(8);
        calculator.numPad(9);
        calculator.plus();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.clear();
        calculatorReset_isCorrect(calculator);


        calculator.numPad(7);
        calculator.numPad(8);
        calculator.numPad(9);
        calculator.plus();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.equal();
        calculator.clear();
        calculatorReset_isCorrect(calculator);
    }

    @Test
    public void calculatorClearError_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.clearError();
        calculatorReset_isCorrect(calculator);

        calculator.clear();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.plus();
        calculator.numPad(4);
        calculator.numPad(5);
        calculator.numPad(6);
        calculator.clearError();
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
        assertEquals(calculator.getCalcOperator(),Calculator.CalcOperator.Plus);
        assertEquals(calculator.getResult().doubleValue(),123,1e-7);


        calculator.clear();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.slash();
        calculator.numPad(4);
        calculator.numPad(5);
        calculator.numPad(6);
        calculator.clearError();
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
    }

    @Test
    public void calculatorBackSp_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        assertEquals(calculator.getInputValue(0),123);
        calculator.backSp();
        assertEquals(calculator.getInputValue(0),12);
        calculator.backSp();
        assertEquals(calculator.getInputValue(0),1);
        calculator.backSp();
        assertEquals(calculator.getInputValue(0),0);
        calculator.backSp();
        assertEquals(calculator.getInputValue(0),0);

        calculator.clear();
        calculator.numPad(1);
        calculator.numPad(2);
        calculator.numPad(3);
        calculator.slash();
        calculator.numPad(4);
        calculator.numPad(5);
        calculator.numPad(6);
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),456);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),45);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),4);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),1);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),0);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),123);
        assertEquals(calculator.getInputValue(1),0);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),12);
        assertEquals(calculator.getInputValue(1),0);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),1);
        assertEquals(calculator.getInputValue(1),0);
        calculator.backSp();
        assertEquals(calculator.getInputIndex(),0);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),0);
        calculator.backSp();
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
            calculator.numPad(i);
            assertEquals(calculator.getInputValue(calculator.getInputIndex()), v);
        }
        calculator.numPad(9); //test the MaxNumber is 8 digi number
        assertEquals(calculator.getInputValue(calculator.getInputIndex()), 12345678);
    }

    @Test
    public void calculatorPlus_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(1);
        calculator.plus();
        calculator.numPad(2);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),3);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(4);
        calculator.numPad(1);
        calculator.numPad(5);
        calculator.plus();
        calculator.numPad(7);
        calculator.numPad(5);
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),1174);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(2);
        calculator.slash();
        calculator.numPad(3);
        calculator.plus();
        calculator.numPad(8);
        calculator.slash();
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),42);
        assertEquals(calculator.getResult().getDenominator(),27);
        assertEquals(calculator.getResult().doubleValue(),42.0/27.0,1e-7);
    }

    @Test
    public void calculatorMinus_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(1);
        calculator.numPad(9);
        calculator.minus();
        calculator.numPad(2);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),17);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(4);
        calculator.numPad(1);
        calculator.numPad(5);
        calculator.minus();
        calculator.numPad(7);
        calculator.numPad(5);
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),-344);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(5);
        calculator.slash();
        calculator.numPad(3);
        calculator.minus();
        calculator.numPad(8);
        calculator.slash();
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),21);
        assertEquals(calculator.getResult().getDenominator(),27);
        assertEquals(calculator.getResult().doubleValue(),21.0/27.0,1e-7);
    }

    @Test
    public void calculatorMultiplication_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(8);
        calculator.numPad(4);
        calculator.multi();
        calculator.numPad(5);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),420);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(4);
        calculator.numPad(1);
        calculator.numPad(5);
        calculator.multi();
        calculator.numPad(7);
        calculator.numPad(5);
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),314985);
        assertEquals(calculator.getResult().getDenominator(),1);

        calculator.clear();
        calculator.numPad(5);
        calculator.slash();
        calculator.numPad(3);
        calculator.multi();
        calculator.numPad(8);
        calculator.slash();
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),40);
        assertEquals(calculator.getResult().getDenominator(),27);
        assertEquals(calculator.getResult().doubleValue(),40.0/27.0,1e-7);
    }

    @Test
    public void calculatorDivision_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.numPad(8);
        calculator.numPad(4);
        calculator.div();
        calculator.numPad(5);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),84);
        assertEquals(calculator.getResult().getDenominator(),5);

        calculator.clear();
        calculator.numPad(4);
        calculator.numPad(1);
        calculator.numPad(5);
        calculator.div();
        calculator.numPad(7);
        calculator.numPad(5);
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),415);
        assertEquals(calculator.getResult().getDenominator(),759);

        calculator.clear();
        calculator.numPad(5);
        calculator.slash();
        calculator.numPad(3);
        calculator.div();
        calculator.numPad(8);
        calculator.slash();
        calculator.numPad(9);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),45);
        assertEquals(calculator.getResult().getDenominator(),24);
        assertEquals(calculator.getResult().doubleValue(),45.0/24.0,1e-7);
    }

    @Test
    public void calculatorEqual_isCorrect() throws Exception
    {
        Calculator calculator=new Calculator();
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),0);
        assertEquals(calculator.getResult().getDenominator(),1);
        assertEquals(calculator.getCalcOperator(), Calculator.CalcOperator.None);
        assertEquals(calculator.getInputIndex(),-1);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),1);

        calculator.numPad(6);
        calculator.slash();
        calculator.numPad(4);
        calculator.plus();
        calculator.numPad(4);
        calculator.slash();
        calculator.numPad(2);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),28);
        assertEquals(calculator.getResult().getDenominator(),8);
        assertEquals(calculator.getCalcOperator(), Calculator.CalcOperator.None);
        assertEquals(calculator.getInputIndex(),-1);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),1);

        calculator.numPad(7);
        calculator.slash();
        calculator.numPad(8);
        calculator.plus();
        calculator.numPad(6);
        calculator.slash();
        calculator.numPad(5);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),83);
        assertEquals(calculator.getResult().getDenominator(),40);
        assertEquals(calculator.getCalcOperator(), Calculator.CalcOperator.None);
        assertEquals(calculator.getInputIndex(),-1);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),1);



        calculator.plus();
        calculator.numPad(1);
        calculator.slash();
        calculator.numPad(2);
        calculator.equal();
        assertEquals(calculator.getResult().getNumerator(),206);
        assertEquals(calculator.getResult().getDenominator(),80);
        assertEquals(calculator.getCalcOperator(), Calculator.CalcOperator.None);
        assertEquals(calculator.getInputIndex(),-1);
        assertEquals(calculator.getInputValue(0),0);
        assertEquals(calculator.getInputValue(1),1);









    }
}
