package GestionePrenotazioni.model;

import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabasePrenotazione {
    private ArrayList<Prenotazione> prenotazioni;
    private File file = new File(System.getProperty("user.home"), "databasePrenotazioni.dat");

    public DatabasePrenotazione(){
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
        List<Prenotazione> listaPrenotazioniGiornaliere = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dataGiornaliera = formatter.format(new Date());
        for(int i=0; i<prenotazioni.size(); i++){
                if(prenotazioni.get(i).getData().equals(dataGiornaliera)){
                    listaPrenotazioniGiornaliere.add(prenotazioni.get(i));
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

    public void AggiornaDottorePrenotazione(Dottore vecchioDottore, Dottore nuovoDottore){
        for(int i=0; i<prenotazioni.size(); i++){
            if(prenotazioni.get(i).getDottore().equals(vecchioDottore)){
                prenotazioni.get(i).setDottore(nuovoDottore);
            }
        }
        try {
            salvaSuFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AggiornaPazientePrenotazione(Paziente vecchioPaziente, Paziente nuovoPaziente){
        for(int i=0; i<prenotazioni.size(); i++){
            if(prenotazioni.get(i).getPaziente().equals(vecchioPaziente)){
                prenotazioni.get(i).setPaziente(nuovoPaziente);
            }
        }
        try {
            salvaSuFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
