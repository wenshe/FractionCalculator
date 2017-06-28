package com.sweethome.fractioncalculator;

/**
 * Created by Andrew on 2017/6/28.
 */

final class MathUtil
{
    static public long GCD(long a,long b)
    {
        if(b!=0) while((a %= b)!=0 && (b %= a)!=0);
        return a + b;
    }
    static public long LCM(long a,long b)
    {
        return (a/GCD(a,b))*b;
    }
}
