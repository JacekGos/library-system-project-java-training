package com.jacekg;

import classes.LibraryUser;
import data_access.BookDataAccess;
import user_interface.MenuHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Main {

    public static void main(String[] args) {

//        MenuHelper.logOnPanel();

        String datePattern = "dd-MM-yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

        String borrowingDate = null;
        borrowingDate = simpleDateFormat.format(new Date());

        System.out.println(borrowingDate);

        Date date1 = new Date();

        try {
            date1 = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(borrowingDate);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date2 = date1;
        String sDate2 = simpleDateFormat.format(date2);
        System.out.println(date2 + " ---- " + sDate2);
    }
}
