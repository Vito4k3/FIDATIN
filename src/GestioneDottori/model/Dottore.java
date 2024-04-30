package GestioneDottori.model;


import java.io.Serializable;

public class Dottore implements Serializable {
    private String nome, cognome;
    private TipoOperatori tipoOperatore;
    private Status stato;
    private String orarioLavorativoInizio, orarioLavorativoFine;

    public Dottore(String nome, String cognome, TipoOperatori tipoOperatore, Status stato, String orarioLavorativoInizio, String orarioLavorativoFine) {
        this.nome = nome;
        this.cognome = cognome;
        this.tipoOperatore = tipoOperatore;
        this.stato = stato;
        this.orarioLavorativoInizio = orarioLavorativoInizio;
        this.orarioLavorativoFine = orarioLavorativoFine;
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

    public TipoOperatori getTipoOperatore() {
        return tipoOperatore;
    }

    public void setTipoOperatore(TipoOperatori tipoOperatore) {
        this.tipoOperatore = tipoOperatore;
    }

    public Status getStato() {
        return stato;
    }

    public void setStato(Status stato) {
        this.stato = stato;
    }

    public String getOrarioLavorativoInizio() {
        return orarioLavorativoInizio;
    }

    public void setOrarioLavorativoInizio(String orarioLavorativoInizio) {
        this.orarioLavorativoInizio = orarioLavorativoInizio;
    }

    public String getOrarioLavorativoFine() {
        return orarioLavorativoFine;
    }

    public void setOrarioLavorativoFine(String orarioLavorativoFine) {
        this.orarioLavorativoFine = orarioLavorativoFine;
    }

    @Override
    public String toString() {
        return "Dottore{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", tipoOperatore=" + tipoOperatore +
                ", stato=" + stato +
                ", orarioLavorativoInizio='" + orarioLavorativoInizio + '\'' +
                ", orarioLavorativoFine='" + orarioLavorativoFine + '\'' +
                '}';
    }
}
