/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsnizadatak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Class Main se bavi izgledom i informacijama dobivenim od igraèa te prikazom programa.
 * 
 * @author Šimo
 */
public class Main {
    
   
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner (System.in);
        ServerCom host = new ServerCom();
        String again = "1";
        host.openCon();
        System.out.println("Unesite ime");
        String ime = sc.next();
        host.play.setIme(ime);
        host.play.getIme();
        host.regPlay();
        while(again.equals("1")){
            System.out.println("Za nastavak igre pritisnite 1, za izlaz i deregistraciju 0");
            again = sc.next(); 
            if(!again.equals("1")){
                break;
            }
            host.newGame();
            System.out.println("Unesi riješenje za prvi red");
            String rijesenje1 = sc.next();
            System.out.println("Unesite riješenje za drugi red");
            String rijesenje2 = sc.next();
            System.out.println("Unesite riješenje za treci red");
            String rijesenje3 = sc.next();
            System.out.println("Unesite riješenje za cetvrti red");
            String rijesenje4 = sc.next();
            host.rijesenje = rijesenje1.trim()+rijesenje2.trim()+rijesenje3.trim()+rijesenje4.trim();
            host.chechMet(host.rijesenje);
            host.ServerTrans();//razmjena informacija samo sa strane servera
        }   
        
        host.Unreg();
        host.ServerTrans();

    }
    
 
}
