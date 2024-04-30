package GestionePazienti;

import java.io.*;
import java.util.ArrayList;

public class Profilo{
    private String nome, cognome, orariLavorativi;

    public Profilo(String nome, String cognome, String orariLavorativi) {
        this.nome = nome;
        this.cognome = cognome;
        this.orariLavorativi = orariLavorativi;
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

    public String getOrariLavorativi() {
        return orariLavorativi;
    }

    public void setOrariLavorativi(String orariLavorativi) {
        this.orariLavorativi = orariLavorativi;
    }

    @Override
    public String toString() {
        return " nome e cognome: " + nome + " " + cognome + ", orariLavorativi: " + orariLavorativi + " ";
    }


}
        

