
package zavrsnizadatak;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/**
 * Klasa ServerCom komunicira sa Serverom.
 * @author Šimo
 */
public class ServerCom extends Player {
    
    // String line je u atributu zato sto moze biti pozivan od strane bilo koje metode.
    
    Player play = new Player();
    int trigger = 0; // u metodi chech i unreg
    String s = "";
    ArrayList<String> list = new ArrayList<String>();
    String rijesenje = "";
    String line = "";
    String lineNewGame = "";
    String lineCheck = "";
    String lineUnreg = "";
    
    
    /**
     * Metoda postavlja i spaja osnovnu konekciju sa "localhost" serverom.
     * @throws MalformedURLException Baca iznimku ukoliko nije došao ispravan URL ili ga je nemoguæe parsati.
     * @throws IOException Ukoliko se javlja IOException
     */
    public void openCon() throws MalformedURLException{
        try {
            URL url = new URL("http://localhost/");
            URLConnection conn = url.openConnection();
            conn.connect();
            System.out.println("Uspješno ste spojeni na server");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Metoda se spaja na localhost sa http adresom koja u sebi sadrži komande
     * i ime igraèa koji se želi spojiti.
     * @throws MalformedURLException Baca iznimku ukoliko nije došao ispravan URL ili ga je nemoguæe parsati.
     * @throws IOException Ukoliko se javlja IOException
     */  
    public void regPlay(){
        
        try{
            URL urla = new URL("http://localhost/?command=register&player="+play.getIme().trim());
            URLConnection conn = urla.openConnection();
            conn.connect();
            Scanner inn = new Scanner(conn.getInputStream());
            while (inn.hasNextLine()) {
                line = inn.nextLine();
                BufferedReader ina = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));//èita sa url.
                //Dobivanje ID-a iz String line metodom "lastIndexOf".
                String ID = line.substring(line.lastIndexOf(",") + 1); 
                play.setID(ID);
                play.getID();
                System.out.println(line);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        
        }
        
        
    }
    
    /**
     * Metoda se spaja na localhost sa http adresom koja u sebi sadrži komandu new
     * i ID igraèa koji se želi spojiti.
     * Pokretanjem programa ova metoda æe dodijeliti novu igru igraèem sa postavljenim ID-om.
     * @throws MalformedURLException Baca iznimku ukoliko nije došao ispravan URL ili ga je nemoguæe parsati.
     * @throws IOException Ukoliko se javlja IOException
     */
    public void newGame() throws MalformedURLException, IOException{
        String l = "";
        URL urll = new URL("http://localhost/?command=new&"+play.getID().trim());
        URLConnection connn = urll.openConnection();
        connn.connect();
        Scanner inm = new Scanner(connn.getInputStream());
        while (inm.hasNextLine()) {
            lineNewGame = inm.nextLine();
            String SerIn = "";
            String SerBr = "";
            ArrayList<String> listaIspisa = new ArrayList<String>();
            // Spremanje odreðenih dijelova iz String lineNewGame u posebne varijable radi lakšeg handlanja.
            if(lineNewGame.length() < 5){
                System.out.println();
            }
            if(lineNewGame.length()>20){
                SerIn = lineNewGame;
            }
                
            if(lineNewGame.length()< 20){
                SerBr = lineNewGame;
            }
            System.out.println(SerIn);
            listaIspisa.add(SerBr);
            //Dio koda s kojim se radi ispis 4 x 4.
            for(String s : listaIspisa){
                for(int i = 1; i <= s.length()-4; i++){
                    if(i == 1){
                        System.out.println(Arrays.asList(s.substring(i - 1, i + 3)));
                    }
                    if( i % 4 == 0){
                        System.out.println(Arrays.asList(s.substring(i, i + 4)));
                    }
                }
            }
        }
    }
    
    /**
     * Metoda se spaja na localhost sa http adresom koja u sebi sadrži komandu check,
     * ID igraèa koji se želi spojiti i riješenje koje je korisnik unio
     * Pokretanjem programa ova metoda æe provjeriti toènost riješenja.
     * @param rijesenje Parametar metode checkMet
     * @throws MalformedURLException Baca iznimku ukoliko nije došao ispravan URL ili ga je nemoguæe parsati.
     * @throws IOException Ukoliko se javlja IOException
     */
    public void chechMet(String rijesenje) throws MalformedURLException, IOException{ 
        Long p = null;	
    	try{
         p = Long.parseLong(rijesenje);
    	}catch(Exception e){
    		
        }
        URL urlla = new URL("http://localhost/?command=check&solution="+p+"&"+play.getID().trim());
        URLConnection connnm = urlla.openConnection();
        connnm.connect();
        Scanner inma = new Scanner(connnm.getInputStream());
        while (inma.hasNextLine()) {
            lineCheck = inma.nextLine();
            System.out.println(lineCheck);
            if(lineCheck.equals("Correct solution provided from the player with the"+play.getID())){
                trigger ++;
                
            }
            
        }
        System.out.println("Da li zelite novu igru?");
    }
    
    /**
     * Metoda se spaja na localhost sa http adresom koja u sebi sadrži komandu unregister i
     * ID igraèa.
     * Pokretanjem programa ova metoda æe deregistrirati igraèa.
     * @return Vraæa String varijablu u kojoj je spremljena informacija od servera.
     * @throws MalformedURLException Baca iznimku ukoliko nije došao ispravan URL ili ga je nemoguæe parsati.
     * @throws IOException Ukoliko se javlja IOException
     */
    public String Unreg() throws MalformedURLException, IOException{
        URL urllam = new URL("http://localhost/?command=unregister&"+play.getID().trim());
        URLConnection connnma = urllam.openConnection();
        connnma.connect();
        Scanner inmar = new Scanner(connnma.getInputStream());
        while (inmar.hasNextLine()) {
            lineUnreg = inmar.nextLine();
            System.out.println(lineUnreg);
        }
        System.out.println(trigger);
        return lineUnreg;
        
    }
    
    /**
     * Metoda stvara datoteku i u nju zapisuje komunikaciju sa serverom
     * @throws IOException Ukoliko se javlja IOException
     */
    public  void ServerTrans() throws IOException{
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.
        File dat = new File("Datoteka-TransakcijeServer.txt");
        if(!dat.exists()){
            dat.createNewFile();
        }
        // Ukoliko datoteka postoji zapisuje String linije u "Datoteka-TransakcijeServer.txt"
        FileWriter fw = new FileWriter(dat,true);
        fw.write("-" + "Nova Komunikacija sa serverom, vrijeme: " + df.format(date) + newLine + "1) Prema Serveru " + play.getIme() + "" + rijesenje + newLine + 
                "2) Od Servera korisniku " + line + lineNewGame + lineUnreg + lineCheck + newLine);
        fw.flush();
    }
}

    

