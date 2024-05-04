package GestionePazienti.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionePazienti{
    private ArrayList<Paziente> Pazienti;
    String userHome = System.getProperty("user.home");
    public File file= new File(userHome, "databasePazienti.dat");

    public GestionePazienti() {
        Pazienti = new ArrayList<>();
    }

    public void AggiungiPaziente(Paziente p){
        this.Pazienti.add(p);
    }

    public void RimuoviPaziente(String nomePaziente, String cognomePaziente){
        for(int i = 0; i < this.Pazienti.size(); i++){
            if(((Paziente)this.Pazienti.get(i)).getNome().equals(nomePaziente) && ((Paziente)this.Pazienti.get(i)).getCognome().equals(cognomePaziente)){
                this.Pazienti.remove(i);
            }
        }
    }

    public void salvaSuFile() throws IOException {

        FileOutputStream fop = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fop);

        Paziente[] arrayPazienti = Pazienti.toArray(new Paziente[Pazienti.size()]);

        oos.writeObject(arrayPazienti);

        oos.close();
        fop.close();
    }

    public void caricaDaFile() throws IOException {
        if (file.length() != 0) {
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
