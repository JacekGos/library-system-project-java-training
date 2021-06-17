package com.jacekg;

import classes.LibraryUser;
import classes.LibraryWorker;
import classes.Request;
import data_access.BookDataAccess;
import data_access.BorrowingDataAccess;
import data_access.RequestDataAccess;
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

        /*LibraryWorker libraryWorker = new LibraryWorker();

        int userId = RequestDataAccess.getUserIdByBorrowingId(libraryWorker, 8);

        System.out.println(userId);*/
    }
}
