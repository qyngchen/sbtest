package com.qyngchen.sbeasyrule;


import org.jeasy.rules.api.Facts;

public class SbeasyruleApplication {

    public static void main(String[] args) {
        Facts facts = new Facts();
        facts.put("rain", true);
    }

}
