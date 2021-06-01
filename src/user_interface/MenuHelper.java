package user_interface;

import java.util.Scanner;

public interface MenuHelper {

    Scanner myInput = new Scanner( System.in );

    static int checkChoosedOptionValidation(int amountOfOptions){

        boolean isCorrect = false;
        int value = 0;

        System.out.print("Wybierz opcje: ");

        while (isCorrect != true){


            try{
                value = myInput.nextByte();
            }catch (Exception e){
                System.out.print("Nieprawidłowa wartość, spróbuj ponownie: ");
                myInput.nextLine(); //Added because scanner had all the time first introduced value so there was always an exception
                continue;
            }

            if (value > 0 && value <= amountOfOptions){
                isCorrect = true;
            }else{
                System.out.print("Nieprawidłowa wartość, spróbuj ponownie: ");
                continue;
            }
        }

        return value;
    }

}
