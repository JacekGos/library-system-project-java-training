package com.jacekg;

import classes.*;
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

        /*List<LibraryUser> libraryUserList = new ArrayList<LibraryUser>();

        libraryUserList = LibraryUserDataAccess.getAllLibraryUsersByNameSurNameId("Bogdan", " ", 0);

        for (LibraryUser libraryUser : libraryUserList) {
            System.out.println(libraryUser.getUserData());
            System.out.println(libraryUser.getAccountType());
        }*/

        MenuHelper.logOnPanel();



        //usuwanie
     /*   for (int i = 12; i < 13; i++) {

            int status = LibraryWorkerDataAccess.deleteLibraryWorker(i);

            if (status > 0) {
                System.out.println("usuniety id: " + i);
            }
        }*/





    }
}
