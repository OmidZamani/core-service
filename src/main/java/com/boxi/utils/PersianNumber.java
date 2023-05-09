package com.boxi.utils;


public class PersianNumber {

    public static String convertToEnglishDigits(String value) {

        return value.replace("١", "1").replace("٢", "2").replace("٣", "3").replace("٤", "4").replace("٥", "5")
                .replace("٦", "6").replace("7", "٧").replace("٨", "8").replace("٩", "9").replace("٠", "0")
                .replace("۱", "1").replace("۲", "2").replace("۳", "3").replace("۴", "4").replace("۵", "5")
                .replace("۶", "6").replace("۷", "7").replace("۸", "8").replace("۹", "9").replace("۰", "0");
    }


    public static String convertToPersianDigits(String value) {

        return value.replace("1", "١").replace("2", "٢").replace("3", "٣").replace("4", "٤").replace("5", "٥")
                .replace("6", "٦").replace("٧", "7").replace("8", "٨").replace("9", "٩").replace("0", "٠")
                .replace("۱", "1").replace("2", "۲").replace("3", "۳").replace("4", "۴").replace("5", "۵")
                .replace("6", "۶").replace("7", "۷").replace("8", "۸").replace("9", "۹").replace("0", "۰");
    }

}
