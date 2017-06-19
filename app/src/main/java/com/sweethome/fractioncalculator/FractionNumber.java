package com.sweethome.fractioncalculator;

/**
 * Created by Andrew on 2017/5/28.
 */

final class Fraction extends Number {
    private int numerator;
    private int denominator;

    //Constructor
    public Fraction()
    {
        this.setValue(0,1);
    }
    public Fraction(int numerator, int denominator)
    {
        this.setValue(numerator,denominator);
    }
    public Fraction(Fraction f)
    {
        this.numerator=f.numerator;
        this.denominator=f.denominator;
    }
    public Fraction(int numerator) {
        this.setValue(numerator,1);
    }


    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setNumerator(int numerator)
    {
        setValue(numerator,this.denominator);
    }

    public void setDenominator(int denominator)
    {
        setValue(this.numerator,denominator);
    }

    public void setValue(int numerator,int denominator)
    {
        /*
        if(denominator == 0) {
            throw new IllegalArgumentException("denominator is zero");
        }
        */
        if(denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public byte byteValue() {
        return (byte) this.doubleValue();
    }

    public double doubleValue() {
        return ((double) numerator)/((double) denominator);
    }

    public float floatValue() {
        return (float) this.doubleValue();
    }

    public int intValue() {
        return (int) this.doubleValue();
    }

    public long longValue() {
        return (long) this.doubleValue();
    }

    public short shortValue() {
        return (short) this.doubleValue();
    }

    public boolean equals(Fraction frac) {
        return this.compareTo(frac) == 0;
    }

    public int compareTo(Fraction frac) {
        long t = this.getNumerator() * frac.getDenominator();
        long f = frac.getNumerator() * this.getDenominator();
        int result = 0;
        if(t>f) {
            result = 1;
        }
        else if(f>t) {
            result = -1;
        }
        return result;
    }

    public Fraction add(Fraction value)
    {
        this.setValue(this.numerator*value.denominator+value.numerator*this.denominator,this.denominator*value.denominator);
        return this;
    }
    public Fraction dec(Fraction value)
    {
        this.setValue(this.numerator*value.denominator-value.numerator*this.denominator,this.denominator*value.denominator);
        return this;
    }
    public Fraction mul(Fraction value)
    {
        this.setValue(this.numerator*value.numerator,this.denominator*value.denominator);
        return this;
    }
    public Fraction div(Fraction value)
    {
        this.setValue(this.numerator*value.denominator,this.denominator*value.numerator);
        return this;
    }

}