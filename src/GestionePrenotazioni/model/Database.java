package GestionePrenotazioni.model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Database {
    private ArrayList<Prenotazione> prenotazioni;

    public Database(){
        prenotazioni= new ArrayList<>();
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione){
        prenotazioni.add(prenotazione);
    }
    public void rimuoviPrenotazione(int id){
        prenotazioni.remove(id);
    }
    public void sostituisciPrenotazione(int index, Prenotazione prenotazione){
        prenotazioni.set(index, prenotazione);
    }

    public List<Prenotazione> prenotazioniGiornaliere(){
        List<Prenotazione> listaPrenotazioniGiornaliere = new ArrayList<Prenotazione>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for(int i=0; i<prenotazioni.size(); i++){
            try {
                if(formatter.parse(prenotazioni.get(i).getData()).equals(new Date())){
                    listaPrenotazioniGiornaliere.add(prenotazioni.get(i));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return listaPrenotazioniGiornaliere;
    }
    public void ordinaPerDataCrescente(){
        for(int i=0; i<prenotazioni.size()-1; i++){
            for(int j=i+1; j<prenotazioni.size(); j++){
                if(prenotazioni.get(i).getData().compareTo(prenotazioni.get(j).getData())>0){
                    Prenotazione temp= prenotazioni.get(i);
                    prenotazioni.set(i, prenotazioni.get(j));
                    prenotazioni.set(j, temp);
                }else if(prenotazioni.get(i).getData().compareTo(prenotazioni.get(j).getData())==0){
                    if(prenotazioni.get(i).getOra().compareTo(prenotazioni.get(j).getOra())>0) {
                        Prenotazione temp = prenotazioni.get(i);
                        prenotazioni.set(i, prenotazioni.get(j));
                        prenotazioni.set(j, temp);
                    }
                }
            }
        }
    }

    public void ordinaPerDataDecrescente(){
        for(int i=0; i<prenotazioni.size()-1; i++){
            for(int j=i+1; j<prenotazioni.size(); j++){
                if(prenotazioni.get(i).getData().compareTo(prenotazioni.get(j).getData())<0){
                    Prenotazione temp= prenotazioni.get(i);
                    prenotazioni.set(i, prenotazioni.get(j));
                    prenotazioni.set(j, temp);
                }else if(prenotazioni.get(i).getData().compareTo(prenotazioni.get(j).getData())==0){
                    if(prenotazioni.get(i).getOra().compareTo(prenotazioni.get(j).getOra())<0) {
                        Prenotazione temp = prenotazioni.get(i);
                        prenotazioni.set(i, prenotazioni.get(j));
                        prenotazioni.set(j, temp);
                    }
                }
            }
        }
    }

    public List<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }
    public void salvaSuFile(File file) throws IOException {
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Prenotazione[] arrayPrenotazioni= prenotazioni.toArray(new Prenotazione[prenotazioni.size()]);

        oos.writeObject(arrayPrenotazioni);

        oos.close();
        fop.close();
    }

    public void caricaDaFile(File file) throws IOException {
        if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                Prenotazione[] prenotazioniCaricate = (Prenotazione[]) ois.readObject();

                prenotazioni.clear();
                prenotazioni.addAll(Arrays.asList(prenotazioniCaricate));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois.close();
            fis.close();
        }
    }
}
