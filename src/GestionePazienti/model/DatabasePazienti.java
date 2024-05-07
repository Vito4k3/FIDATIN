package GestionePazienti.model;

import GestioneDottori.model.Dottore;
import GestionePrenotazioni.model.Prenotazione;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabasePazienti {
    private ArrayList<Paziente> Pazienti;
    private String fileName= "databasePazienti.dat";

    public DatabasePazienti() {
        Pazienti = new ArrayList<>();
    }

    public void AggiungiPaziente(Paziente p){
        this.Pazienti.add(p);
    }
    public boolean isAlreadyExist(Paziente pazienteSelezionato){
        for(Paziente paziente : Pazienti){
            if(paziente.equals(pazienteSelezionato)){
                return true;
            }
        }
        return false;
    }

    public void salvaSuFile(Path path) throws IOException {
        File file = new File(path.toString() + File.separator + fileName);

        FileOutputStream fop = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fop);

        Paziente[] arrayPazienti = Pazienti.toArray(new Paziente[0]);

        oos.writeObject(arrayPazienti);

        oos.close();
        fop.close();
    }

    public void caricaDaFile(Path path) throws IOException {
        File file = new File(path.toString() + File.separator + fileName);
        if(!file.exists()){
            file.createNewFile();
        }else if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois2 = new ObjectInputStream(fis);

            try {
                Paziente[] pazientiCaricati = (Paziente[]) ois2.readObject();

                Pazienti.clear();
                Pazienti.addAll(Arrays.asList(pazientiCaricati));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois2.close();
            fis.close();
        }
    }

    public List<Paziente> getPazienti(){
        return Pazienti;
    }


}
