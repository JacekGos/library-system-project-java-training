package com.jacekg;

import classes.*;
import data_access.LibraryWorkerDataAccess;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
       // LibraryWorker worker1 = new LibraryWorker(6, "Lukasz", "Gos", "lukasz.gos", "a", 1);
       MenuHelper.logOnPanel();

        /*LibraryWorker worker1 = new LibraryWorker();
        LibraryWorker worker2 = null;

        System.out.println(Objects.isNull(worker1));
        System.out.println(Objects.isNull(worker2));*/


        //usuwanie
     /*   for (int i = 12; i < 13; i++) {

            int status = LibraryWorkerDataAccess.deleteLibraryWorker(i);

            if (status > 0) {
                System.out.println("usuniety id: " + i);
            }
        }*/





    }
}
