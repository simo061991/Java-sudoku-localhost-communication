/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsnizadatak;

/**
 * Class Player služi za informacije o igraèu.
 * @author Šimo
 * 
 */
 class Player {
    
    private String ID;
    private String ime;
    /**
     * Metoda setID služi za postavljanje vrijednost ID-a.
     * @param ID 
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    
    /**
     * Metoda setIme služi za postavljenje vrijednost ime.
     * @param ime 
     */
    public void setIme(String ime) {
        this.ime = ime;
    }
    /**
     * Metoda getID vraæa ID vrijednost
     * @return ID
     */
    public String getID() {
        return ID;
    }
    /**
     * Metoda getIme služi za vraæanje vrijednosti ime.
     * @return ime
     */
    public String getIme() {
        return ime;
    }

    @Override
    public String toString() {
        return "Player{" + "ID=" + ID + ", ime=" + ime + '}';
    }
    
    
    
    
    
}
