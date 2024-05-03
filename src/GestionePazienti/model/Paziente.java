package GestionePazienti.model;

import GestioneDottori.model.Dottore;
import GestionePazienti.view.CartellaClinica;

import java.io.Serializable;
import java.util.Objects;

public class Paziente implements Serializable {
    private String nome, cognome, codiceFiscale, residenza, dataNascita, sesso, cap;
    private CartellaClinica cartella;
    
    public Paziente(String nome, String cognome, String dataNascita, String codiceFiscale, String sesso, String residenza, String cap,
            CartellaClinica cartella) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.residenza = residenza;
        this.dataNascita = dataNascita;
        this.cartella = cartella;
        this.cap=cap;
        this.sesso = sesso;
    }

    public Paziente() {
        this.nome = "";
        this.cognome = "";
        this.codiceFiscale = "";
        this.residenza = "";
        this.dataNascita = "";
        this.cartella = null;
        this.sesso=" ";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public CartellaClinica getCartella() {
        return cartella;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setCartella(CartellaClinica cartella) {
        this.cartella = cartella;
    }

        @Override
        public String toString() {
            return "Paziente [nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", residenza="
                    + residenza + ", dataNascita=" + dataNascita + ", cartella=" + cartella + "]";
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paziente paziente = (Paziente) o;
        return Objects.equals(paziente.getCodiceFiscale(), codiceFiscale);
    }
    

}
