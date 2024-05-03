package GestioneDottori.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseDottori {
    private ArrayList<Dottore> dottori;

    public DatabaseDottori(){
        dottori = new ArrayList<Dottore>();
    }

    public void aggiungiDottore(Dottore dottore){
        dottori.add(dottore);
    }
    public void rimuoviDottore(int id){
        dottori.remove(id);
    }
    public void sostituisciDottore(int index, Dottore dottore){
        dottori.set(index, dottore);
    }
    public List<Dottore> visualizzaDottoriAttivi(){
        List<Dottore> listaDottoriAttivi = new ArrayList<Dottore>();
        for(int i=0; i<dottori.size(); i++){
            if(dottori.get(i).getStato().equals(Status.ATTIVO)){
                listaDottoriAttivi.add(dottori.get(i));
            }
        }
        return listaDottoriAttivi;
    }

    public List<Dottore> getDottori(){
        return dottori;
    }
    public void salvaSuFile(File file) throws IOException {
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Dottore[] arrayDottori= dottori.toArray(new Dottore[dottori.size()]);

        oos.writeObject(arrayDottori);

        oos.close();
        fop.close();
    }

    public void caricaDaFile(File file) throws IOException {
        if (file != null && file.length() > 0) { // !!!
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois2 = new ObjectInputStream(fis);

            try {
                Dottore[] dottoriCaricati = (Dottore[]) ois2.readObject();

                dottori.clear();
                dottori.addAll(Arrays.asList(dottoriCaricati));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois2.close();
            fis.close();
        }
    }
}
