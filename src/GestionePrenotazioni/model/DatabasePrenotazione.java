package GestionePrenotazioni.model;

import GestioneDottori.model.Dottore;
import GestioneDottori.model.Status;
import GestionePazienti.model.Paziente;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabasePrenotazione {
    private ArrayList<Prenotazione> prenotazioni;
    public String fileName = "databasePrenotazioni.dat";

    public DatabasePrenotazione(){
        prenotazioni= new ArrayList<>();
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione){
        prenotazioni.add(prenotazione);
    }
    public void rimuoviPrenotazione(int id){
        for(int i=0; i<prenotazioni.size(); i++){
            if(prenotazioni.get(i).getId() == id){
                prenotazioni.remove(prenotazioni.get(i));
            }
        }
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

    public Prenotazione ricercaPrenotazione(int id){
        for(Prenotazione prenotazione : prenotazioni){
            if(prenotazione.getId() == id){
                return prenotazione;
            }
        }
        return null;
    }

    public List<Prenotazione> getPrenotazioni(){
        return prenotazioni;
    }
    public void salvaSuFile(Path path) throws IOException {
        File file = new File(path.toString() + File.separator + fileName);
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Prenotazione[] arrayPrenotazioni= prenotazioni.toArray(new Prenotazione[0]);

        oos.writeObject(arrayPrenotazioni);

        oos.close();
        fop.close();
    }

    public void caricaDaFile(Path path) throws IOException {
        File file = new File(path.toString() + File.separator + fileName);

        if(!file.exists()){
            file.createNewFile();
        }else if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                Prenotazione[] prenotazioniCaricate = (Prenotazione[]) ois.readObject();

                prenotazioni.clear();
                prenotazioni.addAll(Arrays.asList(prenotazioniCaricate));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            int max = prenotazioni.getFirst().getId();
            for(Prenotazione prenotazione : prenotazioni){
                if(prenotazione.getId() > max){
                    max = prenotazione.getId();
                }
            }

            Prenotazione.setContatore(max+1);

            ois.close();
            fis.close();
        }
    }
    public boolean visualizzaDisponibilita(Dottore dottore, Date orario){
        if(dottore.isAttivo()) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(orario.toInstant(), java.time.ZoneId.systemDefault());
            LocalTime nuovoOrario = localDateTime.toLocalTime();
            LocalTime orarioLavorativoInizio = LocalTime.parse(dottore.getOrarioLavorativoInizio());
            LocalTime orarioLavorativoFine = LocalTime.parse(dottore.getOrarioLavorativoFine());


            for (Prenotazione prenotazione : prenotazioni) {
                LocalTime orarioPrenotazione = LocalTime.parse(prenotazione.getOra());
                if (prenotazione.getDottore().getId() == dottore.getId() && (!(nuovoOrario.isAfter(orarioPrenotazione.plusMinutes(10))
                        || nuovoOrario.isBefore(orarioPrenotazione.minusMinutes(10)))
                        || !(nuovoOrario.isAfter(orarioLavorativoInizio) && nuovoOrario.isBefore(orarioLavorativoFine)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean visualizzaDisponibilita(Paziente paziente, Date orario){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(orario.toInstant(), java.time.ZoneId.systemDefault());
        LocalTime nuovoOrario = localDateTime.toLocalTime();

        for(Prenotazione prenotazione : prenotazioni){
            LocalTime orarioPrenotazione = LocalTime.parse(prenotazione.getOra());
            if(prenotazione.getPaziente().equals(paziente) && !(nuovoOrario.isAfter(orarioPrenotazione.plusMinutes(10))
                    || nuovoOrario.isBefore(orarioPrenotazione.minusMinutes(10)))){
                return false;
            }
        }
        return true;
    }

    public void AggiornaDottorePrenotazione(Dottore vecchioDottore, Dottore nuovoDottore){
        for(int i=0; i<prenotazioni.size(); i++){
            if(prenotazioni.get(i).getDottore().equals(vecchioDottore)){
                prenotazioni.get(i).setDottore(nuovoDottore);
            }
        }
    }

    public void situazioneDottoreEliminato(int id){
        for(int i = prenotazioni.size() - 1; i >= 0; i--){
            if(prenotazioni.get(i).getDottore().getId() == id ){
                prenotazioni.remove(prenotazioni.get(i));
            }
        }
    }

    public void situazionePazienteEliminato(int id){
        for (int i = prenotazioni.size() - 1; i >= 0; i--) {
            if (prenotazioni.get(i).getPaziente().getId() == id) {
                prenotazioni.remove(i);
            }
        }
    }

    public void AggiornaPazientePrenotazione(Paziente vecchioPaziente, Paziente nuovoPaziente){
        for(int i=0; i<prenotazioni.size(); i++){
            if(prenotazioni.get(i).getPaziente().equals(vecchioPaziente)){
                prenotazioni.get(i).setPaziente(nuovoPaziente);
            }
        }
    }

}
