package GestionePazienti.view;

import java.util.Vector;

import GestionePrescrizioni.model.Prescrizione;
import GestionePrenotazioni.model.Prenotazione;

public class CartellaClinica {
    private String GruppoSang, Diagnosi;
    private Vector Prenotazioni, Prescrizioni;
    

    public CartellaClinica() {
        GruppoSang = "";
        Diagnosi = "";
        Prenotazioni = new Vector();
        Prescrizioni = new Vector();
    }

    public CartellaClinica(String gruppoSang, String diagnosi, Vector prenotazioni, Vector prescrizioni) {
        GruppoSang = gruppoSang;
        Diagnosi = diagnosi;
        Prenotazioni = prenotazioni;
        Prescrizioni = prescrizioni;
    }

    public String getGruppoSang() {
        return GruppoSang;
    }


    public void setGruppoSang(String gruppoSang) {
        GruppoSang = gruppoSang;
    }


    public String getDiagnosi() {
        return Diagnosi;
    }


    public void setDiagnosi(String diagnosi) {
        Diagnosi = diagnosi;
    }

    public Vector getPrenotazioni() {
        return Prenotazioni;
    }


    public void setPrenotazioni(Vector prenotazioni) {
        Prenotazioni = prenotazioni;
    }


    public Vector getPrescrizioni() {
        return Prescrizioni;
    }


    public void setPrescrizioni(Vector prescrizioni) {
        Prescrizioni = prescrizioni;
    }


    //aggiunta e rimozione della prescrizione con passaggio di oggetto di tipo Prescrizione
    public void AggiuntaPrescrizione(Prescrizione p){
        Prescrizioni.add(p);
    }


    public void RimuoviPrescrizione(Prescrizione p){
        for(int i = 0; i < this.Prescrizioni.size(); i++){
            if(this.Prescrizioni.get(i).equals(p)){
                this.Prescrizioni.removeElementAt(i);
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
                this.Prenotazioni.removeElementAt(i);
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
