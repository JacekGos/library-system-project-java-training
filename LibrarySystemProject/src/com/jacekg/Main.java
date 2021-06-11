package com.jacekg;

import classes.*;
import data_access.BookDataAccess;
import data_access.LibraryUserDataAccess;
import data_access.LibraryWorkerDataAccess;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        List<Book> bookList = new ArrayList<Book>();

        //MenuHelper.logOnPanel();

        bookList = BookDataAccess.getAllBooksByTitleAndSort("Harry P" , "Kryminał");

        for (Book book : bookList) {
            System.out.println(book.getLibraryElementData());
        }







    }
}
