package GestioneDottori.model;

import java.io.*;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseDottori {
    private ArrayList<Dottore> dottori;
    private String fileName = "databaseDottori.dat";

    public DatabaseDottori(){
        dottori = new ArrayList<Dottore>();
    }

    public void aggiungiDottore(Dottore dottore){
        dottori.add(dottore);
    }
    public void rimuoviDottore(int id){
        for(int i=0; i<dottori.size(); i++){
            if(dottori.get(i).getId() == id){
                dottori.remove(dottori.get(i));
            }
        }
    }
    public void sostituisciDottore(Dottore vecchioDottore, Dottore dottore){
        for(int i=0; i<dottori.size(); i++){
            if(dottori.get(i).getId() == vecchioDottore.getId()){
                dottore.setId(vecchioDottore.getId());
                dottori.set(i, dottore);
            }
        }
    }
    public List<Dottore> visualizzaDottoriAttivi(){
        List<Dottore> listaDottoriAttivi = new ArrayList<>();
        for(int i=0; i<dottori.size(); i++){
            if(dottori.get(i).getStato().equals(Status.ATTIVO)){
                listaDottoriAttivi.add(dottori.get(i));
            }
        }
        return listaDottoriAttivi;
    }

    public Dottore ricercaDottore(int id){
        for(Dottore dottore : dottori){
            if(dottore.getId() == id){
                return dottore;
            }
        }
        return null;
    }

    /*public void aggiornaStatoDottori() {  //in base all'ora corrente imposta lo stato dei dottori ad attivo o non attivo
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm"); // crea un formatter per la conversione degli orari
        LocalTime now = LocalTime.now(); // ottiene l'ora attuale
        for (Dottore dottore : dottori) {
            LocalTime orarioInizio = LocalTime.parse(dottore.getOrarioLavorativoInizio()); // ottiene l'orario di inizio lavoro del dottore
            LocalTime orarioFine = LocalTime.parse(dottore.getOrarioLavorativoFine()); // ottiene l'orario di fine lavoro del dottore
            if (orarioInizio.compareTo(now) <= 0 && orarioFine.compareTo(now) >= 0) { // confronta l'ora attuale con l'intervallo di lavoro del dottore
                dottore.setStato(Status.ATTIVO); // il dottore è attivo se l'ora attuale rientra nell'intervallo di lavoro
            } else {
                dottore.setStato(Status.NON_ATTIVO); // altrimenti, il dottore è non attivo
            }
        }
    }*/

    public boolean isAlreadyExists(Dottore dottoreSelezionato){  //verifica se ci sono più dottori con lo stesso nome
        for(Dottore dottore : dottori){
            if(dottore.equals(dottoreSelezionato)){
                return true;
            }
        }
        return false;
    }

    public List<Dottore> getDottori(){
        return dottori;
    }
    public void salvaSuFile(Path path) throws IOException {
        File file = new File(path + File.separator + fileName);

        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Dottore[] arrayDottori= dottori.toArray(new Dottore[0]);

        oos.writeObject(arrayDottori);

        oos.close();
        fop.close();
    }

    public void caricaDaFile(Path path) throws IOException {
        File file = new File(path + File.separator + fileName);
        if(!file.exists()){
            file.createNewFile();
        }else if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois2 = new ObjectInputStream(fis);

            try {
                Dottore[] dottoriCaricati = (Dottore[]) ois2.readObject();

                dottori.clear();
                dottori.addAll(Arrays.asList(dottoriCaricati));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            int max = dottori.get(0).getId();
            for(Dottore dottore : dottori){
                if(dottore.getId() > max){
                    max = dottore.getId();
                }
            }

            Dottore.setConteggio(max+1);

            ois2.close();
            fis.close();
        }
    }
}
