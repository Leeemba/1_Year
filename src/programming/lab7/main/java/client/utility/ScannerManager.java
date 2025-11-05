package client.utility;

import java.util.Scanner;



public class ScannerManager {
    private static volatile ScannerManager instance;
    private Scanner userScanner;


    private ScannerManager(){

    }

    public static ScannerManager getScannerManager(){
        if(instance == null){
            synchronized (ScannerManager.class){
            if(instance == null) instance = new ScannerManager();
            }
        }
        return instance;
    }
    public synchronized Scanner getUserScanner(){
        return userScanner;
    }
    public synchronized void setUserScanner(Scanner userScanner){
            this.userScanner = userScanner;
    }
}
