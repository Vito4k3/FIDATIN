package GestionePrescrizioni.model;


import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;

import java.io.Serializable;

public class Prescrizione implements Serializable {
    protected Paziente paziente;
    protected Dottore dottore;
    protected String oggettoPrescrizione;
    private int id;
    private static int conteggio=0;

    public Prescrizione(Paziente paziente, Dottore dottore, String oggettoPrescrizione) {
        this.paziente = paziente;
        this.dottore = dottore;
        this.oggettoPrescrizione = oggettoPrescrizione;
        this.id=conteggio;
        conteggio++;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public Dottore getDottore() {
        return dottore;
    }

    public void setDottore(Dottore dottore) {
        this.dottore = dottore;
    }

    public String getOggettoPrescrizione() {
        return oggettoPrescrizione;
    }

    public void setOggettoPrescrizione(String oggettoPrescrizione) {
        this.oggettoPrescrizione = oggettoPrescrizione;
    }
    public int getId(){
        return this.id;
    }
    public static void setConteggio(int i){conteggio=i;}

    @Override
    public String toString() {
        return "Prescrizione{" +
                "paziente=" + paziente +
                ", dottore=" + dottore +
                ", oggettoPrescrizione='" + oggettoPrescrizione + '\'' +
                '}';
    }
}
