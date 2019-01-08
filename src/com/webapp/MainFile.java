package com.webapp;

import java.io.File;

public class MainFile {

    public static void main (String[] args){

        printAllFileName("C:\\Users\\matt\\basejava\\src");

    }

    public static void printAllFileName(String path){
        File source = new File(path);
        for (File file : source.listFiles()){
            if (file.isDirectory()) {
                System.out.println("-----------------------\nDIRECTORY^ " + file.getName() + " ->");
                printAllFileName(file.getPath());
                System.out.println("-----------------------");
            }
            else System.out.println(file.getName());
        }
    }
}
