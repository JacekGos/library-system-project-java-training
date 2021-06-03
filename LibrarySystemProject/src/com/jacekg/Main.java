package com.jacekg;

import classes.*;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

public class Main {

    public static void main(String[] args) {
        Book a = new Book(1, "LOTR", 1, 0, 100);

        //MenuHelper.logOnPanel();
        //WorkerMenu.showWorkerMenu();

        SqlDataBaseConnection.sqlConnection();

    }
}
