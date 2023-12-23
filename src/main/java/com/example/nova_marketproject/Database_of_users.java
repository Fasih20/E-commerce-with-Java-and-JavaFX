package com.example.nova_marketproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Database_of_users {
    static File myObj = new File("Users.txt");
    static String nameofseller;
    public static void filecreation() throws IOException {
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    void writeTOfile(String name, String password) throws IOException {
        FileWriter myWriter = new FileWriter("Users.txt", true);
        String temp1 = "usrn:"+name;
        String temp2 = "psd:"+password;
        myWriter.write("\n"+temp1+"\n"+temp2);
        System.out.println("Successfully wrote to the file.");
        myWriter.close();
    }
    boolean readFormfile(String name, String password)throws IOException
    {
        int proct=0, tmp=0;
        String temp1 = "usrn:"+name;
        String temp2 = "psd:"+password;
        System.out.println(temp1);
        System.out.println(temp2);
        boolean username=false, passw=false, logger=false;
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String daa = myReader.nextLine();
                System.out.println(daa);
                if(daa.equals(temp1))
                {
                    tmp=proct+1;
                    username=true;
                    nameofseller=name;
                }
                else if(daa.equals(temp2) && username && tmp==proct)
                {
                    passw=true;
                    break;
                    //System.out.println(passw+"1");
                }
                else {
                    System.out.println("wrong again");
                }
                proct++;
            }
            if(passw==true && username==true)
            {
                logger=true;
            }
            System.out.println(logger);
            return (logger);
        }
}
