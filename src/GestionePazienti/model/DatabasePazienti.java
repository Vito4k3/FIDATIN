package GestionePazienti.model;

import GestioneDottori.model.Dottore;
import GestionePrenotazioni.model.Prenotazione;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabasePazienti {
    private ArrayList<Paziente> Pazienti;
    String userHome = System.getProperty("user.home");
    public File file= new File(userHome, "databasePazienti.dat");

    public DatabasePazienti() {
        Pazienti = new ArrayList<>();
    }

    public void AggiungiPaziente(Paziente p){
        this.Pazienti.add(p);
    }

    public void salvaSuFile() throws IOException {

        FileOutputStream fop = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fop);

        Paziente[] arrayPazienti = Pazienti.toArray(new Paziente[0]);

        oos.writeObject(arrayPazienti);

        oos.close();
        fop.close();
    }

    public void caricaDaFile() throws IOException {
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
