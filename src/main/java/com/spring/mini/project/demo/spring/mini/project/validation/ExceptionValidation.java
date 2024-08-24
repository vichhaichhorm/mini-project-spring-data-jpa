package com.spring.mini.project.demo.spring.mini.project.validation;


import java.util.regex.Pattern;

public class ExceptionValidation {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regex to match phone numbers in the format 0xxxxxxx
        String phoneNumberRegex = "^0\\d{8,10}$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);
        if (phoneNumber == null) {
            return false;
        }
        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isValidDate(String date) {
        // Regex to match dates in the format YYYY-MM-DD
        String dateRegex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        Pattern pattern = Pattern.compile(dateRegex);
        if (date == null) {
            return false;
        }
        return pattern.matcher(date).matches();
    }

    public static boolean isValidName(String name) {
        // Regex to match names with letters, spaces, hyphens, and apostrophes
        String nameRegex = "^[a-zA-Z\\s'-]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        if (name == null) {
            return false;
        }
        return pattern.matcher(name).matches();
    }
    public  static  boolean isValidGender(Character gender){
        if(gender==null){
            return  false;
        }
        char genderUper=Character.toUpperCase(gender);
        return (genderUper=='M' || genderUper=='F');
    }

}
