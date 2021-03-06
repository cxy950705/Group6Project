package com.group6.babytime.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2016/10/12.
 */
public class StringUtils {


    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;

    }

    public static boolean isNewPasswordValidate(String password){
        if(password.trim().length() <8 || password.trim().length() <24){
            return false;
        }
        return true;
    }

    public static boolean isQQValidate(String QQNum){
        boolean isValid = false;
        String expression = "^[1-9]\\d{4,10}$";
        CharSequence inputStr = QQNum;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
