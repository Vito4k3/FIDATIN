package GestionePazienti.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import GestionePrescrizioni.model.Prescrizione;
import GestionePrenotazioni.model.Prenotazione;

public class CartellaClinica implements Serializable {
    private String Dati;
    private ArrayList<Prenotazione> Prenotazioni;
    private ArrayList<Prescrizione> Prescrizioni;
    

    public CartellaClinica() {
        Dati = "";
        Prenotazioni = new ArrayList<>();
        Prescrizioni = new ArrayList<>();
    }

    public CartellaClinica(String dati, ArrayList<Prenotazione> prenotazioni, ArrayList<Prescrizione> prescrizioni) {
        Dati = dati;
        Prenotazioni = prenotazioni;
        Prescrizioni = prescrizioni;
    }

    public String getDati() {
        return Dati;
    }


    public void setDati(String dati) {
        Dati = dati;
    }

    public ArrayList<Prenotazione> getPrenotazioni() {
        return Prenotazioni;
    }


    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        Prenotazioni = prenotazioni;
    }


    public ArrayList<Prescrizione> getPrescrizioni() {
        return Prescrizioni;
    }


    public void setPrescrizioni(ArrayList<Prescrizione> prescrizioni) {
        Prescrizioni = prescrizioni;
    }


    //aggiunta e rimozione della prescrizione con passaggio di oggetto di tipo Prescrizione
    public void AggiuntaPrescrizione(Prescrizione p){
        Prescrizioni.add(p);
    }


    public void RimuoviPrescrizione(Prescrizione p){
        for(int i = 0; i < this.Prescrizioni.size(); i++){
            if(this.Prescrizioni.get(i).equals(p)){
                this.Prescrizioni.remove(i);
            }
        }
    }

    //aggiunta e rimozione prescrizione con passaggio di oggetto di tipo Prenotazione
    private void AggiuntaPrenotazione(Prenotazione p){
        Prenotazioni.add(p);
    }

    public void RimuoviPrenotazione(Prenotazione p){
        for(int i = 0; i < this.Prenotazioni.size(); i++){
            if(this.Prenotazioni.get(i).equals(p)){
                this.Prenotazioni.remove(i);
            }
        }
    }


    public String leggiPrenotazioni(){
        String s = "";
        for(int i = 0 ; i < this.Prenotazioni.size(); i++){
            Prenotazione p = (Prenotazione)this.Prenotazioni.get(i);
            s = s + p.toString();
        }

        return s;
    }

    public String leggiPrescrizioni(){
        String s = "";
        for(int i = 0 ; i < this.Prescrizioni.size(); i++){
            Prescrizione p = (Prescrizione)this.Prescrizioni.get(i);
            s = s + p.toString();
        }

        return s;
    }
}
