package com.bislab.hr.bislabhr.dummy;

import org.apache.commons.lang.StringUtils;

public class Calc {

    public static void main(String[] args) {
        String buchung = "018";
        Long aLong = Long.valueOf("9310000060");
        Integer integer = Integer.valueOf(buchung);
        String nextBuchungsKreis = String.valueOf(Integer.valueOf(buchung) + 1);
        if(nextBuchungsKreis.length() < 4){
            int diff = 4 - nextBuchungsKreis.length();
            for (int i = 0; i < diff; i++){
                //nextBuchungsKreis = nextBuchungsKreis.concat("0");
            }
        }
        System.out.println(StringUtils.rightPad(nextBuchungsKreis, 4, "0"));
    }
}
