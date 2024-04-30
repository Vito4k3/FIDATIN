package GestionePrenotazioni.model;



import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;

import java.io.Serializable;


public class Prenotazione implements Serializable {
    private Paziente paziente;
    private Dottore dottore;
    private String data, ora;
    private int id;
    private Reparto reparto;
    private TipoPrenotazione tipoPrenotazione;
    private static int contatore=0;

    public Prenotazione(Paziente paziente, Dottore dottore, String data, String ora, Reparto reparto, TipoPrenotazione tipoPrenotazione) {
        this.paziente = paziente;
        this.dottore = dottore;
        this.data = data;
        this.ora= ora;
        this.id = contatore;
        this.reparto = reparto;
        this.tipoPrenotazione = tipoPrenotazione;
        contatore++;
    }

    public Paziente getPaziente() {
        return paziente;
    }

    public void setPaziente(Paziente cliente) {
        this.paziente = cliente;
    }

    public Dottore getDottore() {
        return dottore;
    }

    public void setDottore(Dottore dottore) {
        this.dottore = dottore;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
    }

    public TipoPrenotazione getTipoPrenotazione() {
        return tipoPrenotazione;
    }

    public void setTipoPrenotazione(TipoPrenotazione tipoPrenotazione) {
        this.tipoPrenotazione = tipoPrenotazione;
    }

    public static int getContatore() {
        return contatore;
    }

    public static void setContatore(int contatore) {
        Prenotazione.contatore = contatore;
    }

}

