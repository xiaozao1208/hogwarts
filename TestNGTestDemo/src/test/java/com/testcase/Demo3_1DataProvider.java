package com.testcase;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Demo3_1DataProvider {

    @DataProvider(name="data")
   public Object[][] dataPut(){

        Object[][] obj = new Object[][]{
                {"包裹1",1,0,1,1,0},
                {"包裹2",0,0,1,1,1}
        };

        return obj;
   }

    @Test(dataProvider = "data")
    public void putIntMethod(String packageName,int Anum,int Bnum,int Cnum,int Dnum,int Enum){

        System.out.println(packageName+" :  装入A "+Anum+"份" );
        System.out.println(packageName+" :  装入B "+Bnum+"份" );
        System.out.println(packageName+" :  装入C "+Cnum+"份" );
        System.out.println(packageName+" :  装入D "+Dnum+"份" );
        System.out.println(packageName+" :  装入E "+Enum+"份" );

    }



}
