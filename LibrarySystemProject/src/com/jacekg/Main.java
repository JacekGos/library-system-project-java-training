package com.jacekg;

import classes.*;
import data_access.LibraryWorkerDataAccess;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

public class Main {

    public static void main(String[] args) {
        LibraryWorker worker1 = new LibraryWorker(2, "Dominik", "Gos", "dominix", "a", 1);

        //MenuHelper.logOnPanel();
        //WorkerMenu.showWorkerMenu();

        //SqlDataBaseConnection.sqlConnection();

        int status = LibraryWorkerDataAccess.updateLibraryWorker(worker1);

        if (status > 0) {
            System.out.println(status + " User modified successfully!");
        }


    }
}
