package com.my.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomPassword {
   private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public  static String createPassword(){
        String pwd = RandomStringUtils.random( 10,characters);
        return pwd;
    }

    public RandomPassword() {
    }
}
