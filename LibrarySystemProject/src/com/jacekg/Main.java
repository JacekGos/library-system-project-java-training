package com.jacekg;

import classes.*;
import data_access.LibraryWorkerDataAccess;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LibraryWorker worker1 = new LibraryWorker(6, "Lukasz", "Gos", "lukasz.gos", "a", 1);


        //MenuHelper.logOnPanel();
        //WorkerMenu.showWorkerMenu();

        //SqlDataBaseConnection.sqlConnection();

        List<LibraryWorker> libraryWorkerList = LibraryWorkerDataAccess.getAllLibraryWorkers();
        
        for (LibraryWorker libraryWorker : libraryWorkerList) {
            libraryWorker.getUserData();
        }

        if (libraryWorkerList.size() > 0) {
            System.out.println("Users found successfully!");

            for (LibraryWorker libraryWorker : libraryWorkerList) {
                System.out.println(libraryWorker.getUserData());
            }

        }





    }
}
