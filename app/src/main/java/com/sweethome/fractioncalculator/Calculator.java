package com.sweethome.fractioncalculator;

/**
 * Created by Andrew on 2017/6/21.
 */

public class Calculator
{
    enum CalcStatus
    {
        OK, Error, DivByZero, Overflow
    }

    enum CalcOperator
    {
        None, Plus, Minus, Multi, Div
    }

    private static final int MAX_NUMBER = 99999999;
    private Fraction _result;
    private int[] _v;
    private int _inputIndex;
    private Calculator.CalcStatus _calcStatus;
    private Calculator.CalcOperator _calcOperator;

    public Calculator()
    {
        super();
        Init();
        Clear();
    }

    private void Init()
    {
        _result = new Fraction();
        _v = new int[2];
        _inputIndex = 0;
        _calcStatus = Calculator.CalcStatus.OK;
        _calcOperator = Calculator.CalcOperator.None;
    }

    private Fraction GetInputValue() throws Exception
    {
        Fraction result = new Fraction(0, 1);
        if (_inputIndex==-1)
        {
            if (_v[1] <= 0)
                throw new Exception("DivByZero");
            result.setValue(_v[0], _v[1]);
        }else if (_inputIndex == 0)
        {
            result.setValue(_v[0], 1);
        } else if (_inputIndex == 1)
        {
            if (_v[1] <= 0)
                throw new Exception("DivByZero");
            result.setValue(_v[0], _v[1]);
        }
        return result;
    }

    private void ClearInputValue()
    {
        _inputIndex = 0;
        _v[0] = 0;
        _v[1] = 0;
    }

    /////////////////////////////////////////
    public void Clear()
    {
        _calcStatus = Calculator.CalcStatus.OK;
        _calcOperator = Calculator.CalcOperator.None;
        _result.setValue(0, 1);
        ClearInputValue();
    }

    public void ClearError()
    {
        ClearInputValue();
    }

    public void BackSp()
    {
        if ((_inputIndex >= 0) && (_inputIndex < 2))
        {
            if ((_v[_inputIndex] == 0) && (_inputIndex > 0))
                _inputIndex -= 1;
            else
            {
                _v[_inputIndex] /= 10;
            }
        }
    }

    public void NumPad(int num)
    {
        if ((_inputIndex >= 0) && (_inputIndex < 2))
        {
            if ((num >= 0) && (num <= 9) && (_v[_inputIndex] <= MAX_NUMBER))
                _v[_inputIndex] = (_v[_inputIndex] * 10 + num);
        }else if (_inputIndex==-1)
        {
            _inputIndex=0;
            _v[0]=num;
            _v[1]=0;
        }
    }

    public void Plus()
    {
        Equal();
        _calcOperator = Calculator.CalcOperator.Plus;
    }

    public void Minus()
    {
        Equal();
        _calcOperator = Calculator.CalcOperator.Minus;
    }

    public void Multiplication()
    {
        Equal();
        _calcOperator = Calculator.CalcOperator.Multi;
    }

    public void Division()
    {
        Equal();
        _calcOperator = Calculator.CalcOperator.Div;
    }

    public void Slash()
    {
        if (_inputIndex == 0)
        {
            _inputIndex += 1;
        }
    }

    public void Equal()
    {
        try
        {
            Fraction v = GetInputValue();
            switch (_calcOperator)
            {
                case None:
                    _result = v;
                    break;
                case Plus:
                    _result.add(v);
                    break;
                case Minus:
                    _result.dec(v);
                    break;
                case Multi:
                    _result.mul(v);
                    break;
                case Div:
                    if (v.getNumerator() == 0)
                    {
                        _calcStatus = Calculator.CalcStatus.DivByZero;
                    } else
                    {
                        _result.div(v);
                    }
                    break;
            }
            _calcOperator = Calculator.CalcOperator.None;
            ClearInputValue();
            //_v[0]=_result.getNumerator();
            //_v[1]=_result.getDenominator();
            //_inputIndex=-1;
        } catch (Exception e)
        {
            if (e.getMessage() == "DivByZero")
            {
                _calcStatus = Calculator.CalcStatus.DivByZero;
            }
            e.printStackTrace();
        }
    }
    public int getInputValue(int index)
    {
        return _v[index];
    }

    public int getInputIndex()
    {
        return _inputIndex;
    }

    public CalcOperator getCalcOperator()
    {
        return _calcOperator;
    }

    public Fraction getResult()
    {
        return _result;
    }
}



