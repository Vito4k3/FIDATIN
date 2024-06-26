package GestioneDottori.model;


import java.io.Serializable;
import java.util.Objects;

public class Dottore implements Serializable {
    private String nome, cognome;
    private TipoOperatori tipoOperatore;
    private Status stato;
    private String orarioLavorativoInizio, orarioLavorativoFine;
    private static int conteggio=0;
    private int id;

    public Dottore(String nome, String cognome, TipoOperatori tipoOperatore, Status stato, String orarioLavorativoInizio, String orarioLavorativoFine) {
        this.nome = nome;
        this.cognome = cognome;
        this.tipoOperatore = tipoOperatore;
        this.stato = stato;
        this.orarioLavorativoInizio = orarioLavorativoInizio;
        this.orarioLavorativoFine = orarioLavorativoFine;
        this.id=conteggio;
        conteggio++;
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
    public static void setConteggio(int i){conteggio=i;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAttivo(){
        if(stato.equals(Status.ATTIVO)){
            return true;
        }
        return false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dottore dottore = (Dottore) o;
        return Objects.equals(id, dottore.id);
    }
}
