package com.jacekg;

import classes.LibraryUser;
import data_access.BookDataAccess;
import data_access.BorrowingDataAccess;
import user_interface.MenuHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        MenuHelper.logOnPanel();


       /* LibraryUser user = new LibraryUser(1, "Jacek", "Gos", "jg", "pwd", 2, 0);

        int result = BorrowingDataAccess.getLastBorrowingID(user);
        System.out.println(result);*/

    }
}
