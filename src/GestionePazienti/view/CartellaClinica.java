package GestionePazienti.view;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import GestionePazienti.model.Paziente;
import GestionePrescrizioni.model.Prescrizione;
import GestionePrenotazioni.model.Prenotazione;

public class CartellaClinica implements Serializable {
    private String Dati;
    private List<Prenotazione> Prenotazioni;
    private List<Prescrizione> Prescrizioni;
    

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

    public List<Prenotazione> getPrenotazioni() {
        return Prenotazioni;
    }


    public void setPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
        Prenotazioni = prenotazioni;
    }


    public List<Prescrizione> getPrescrizioni() {
        return Prescrizioni;
    }


    public void setPrescrizioni(ArrayList<Prescrizione> prescrizioni) {
        Prescrizioni = prescrizioni;
    }


    //aggiunta e rimozione della prescrizione con passaggio di oggetto di tipo Prescrizione

    public List<Prenotazione> caricaPrenotazioniPaziente(Paziente paziente){
        List<Prenotazione> listaPrenotazioniPaziente = new ArrayList<>();
        for(int i=0; i<Prenotazioni.size(); i++){
            if(Prenotazioni.get(i).getPaziente().equals(paziente)){
                listaPrenotazioniPaziente.add(Prenotazioni.get(i));
            }
        }
        return listaPrenotazioniPaziente;
    }

    public List<Prescrizione> caricaPrescrizioniPaziente(Paziente paziente){
        List<Prescrizione> listaPrescrizioniPaziente = new ArrayList<>();
        for(int i=0; i<Prescrizioni.size(); i++){
            if(Prescrizioni.get(i).getPaziente().equals(paziente)){
                listaPrescrizioniPaziente.add(Prescrizioni.get(i));
            }
        }
        return listaPrescrizioniPaziente;
    }

    public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni){
        Prenotazioni= listaPrenotazioni;
    }
    public void setListaPrescrizioni(List<Prescrizione> listaPrescrizioni){
        Prescrizioni= listaPrescrizioni;
    }
}
