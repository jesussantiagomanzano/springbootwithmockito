package com.example.springbootwithmockito;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class StudentCalculatorParams {

    private static Object[] parametersForTestSum(){
        return new Object[]{
                new Object[]{50,50,100},
                new Object[]{50,12,62},
                new Object[]{50,50,100},
        };
    }

    @Test
    //@Parameters(method = "testValues")
    @Parameters
    public void testSum(int a, int b, int result){
        TestCalculator sc = new TestCalculator();
        assertEquals( result, sc.calculate(a,b, result));

    }


    private class TestCalculator{

        public int calculate ( int a, int b, int expectedValue){
            return a + b ;
        }


    }
}
