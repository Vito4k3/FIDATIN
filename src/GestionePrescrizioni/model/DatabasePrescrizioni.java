package GestionePrescrizioni.model;
import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import javax.swing.*;


public class DatabasePrescrizioni {
    private ArrayList<Prescrizione> listaPrescrizioni;
    private String fileName = "databasePrescrizioni.dat";

    public DatabasePrescrizioni() {
        listaPrescrizioni = new ArrayList<>();
    }
    
    public void InserimentoPrescrizione(Prescrizione c){
        listaPrescrizioni.add(c);
    }


    public void rimuoviPrescrizione(int id){
        if(id>=0) {
            for (int i = 0; i < listaPrescrizioni.size(); i++) {
                if (listaPrescrizioni.get(i).getId() == id) {
                    listaPrescrizioni.remove(listaPrescrizioni.get(i));
                }
            }
        }
    }


    public void AggiornaDottorePrenotazione(Dottore vecchioDottore, Dottore nuovoDottore){
        for(int i=0; i<listaPrescrizioni.size(); i++){
            if(listaPrescrizioni.get(i).getDottore().equals(vecchioDottore)){
                listaPrescrizioni.get(i).setDottore(nuovoDottore);
            }
        }
    }

    public void AggiornaPazientePrenotazione(Paziente vecchioPaziente, Paziente nuovoPaziente){
        for(int i=0; i<listaPrescrizioni.size(); i++){
            if(listaPrescrizioni.get(i).getPaziente().equals(vecchioPaziente)){
                listaPrescrizioni.get(i).setPaziente(nuovoPaziente);
            }
        }
    }

    public void situazioneDottoreEliminato(int id){
        for(int i = listaPrescrizioni.size() - 1; i >= 0; i--){
            if(listaPrescrizioni.get(i).getDottore().getId() == id ){
                listaPrescrizioni.remove(listaPrescrizioni.get(i));
            }
        }
    }

    public void situazionePazienteEliminato(int id){
        for(int i = listaPrescrizioni.size() - 1; i >= 0; i--){
            if(listaPrescrizioni.get(i).getPaziente().getId() == id ){
                listaPrescrizioni.remove(listaPrescrizioni.get(i));
            }
        }
    }

    public Prescrizione ricercaPrescrizione(int id){
        for(Prescrizione prescrizione: listaPrescrizioni){
            if(prescrizione.getId() == id ){
                return prescrizione;
            }
        }
        return null;
    }
    public ArrayList<Prescrizione> getPrescrizioni(){
        return this.listaPrescrizioni;
    }

    public void caricaDaFile(Path path) throws IOException {
        File file = new File(path.toString() + File.separator + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }else if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                Prescrizione[] prescrizioniCaricate = (Prescrizione[]) ois.readObject();

                listaPrescrizioni.clear();
                listaPrescrizioni.addAll(Arrays.asList(prescrizioniCaricate));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            int max = listaPrescrizioni.getFirst().getId();
            for(Prescrizione prescrizione : listaPrescrizioni){
                if(prescrizione.getId() > max){
                    max = prescrizione.getId();
                }
            }
            Prescrizione.setConteggio(max+1);

            ois.close();
            fis.close();
        }
    }

    public void salvaSuFile(Path path) throws IOException {

        File file = new File(path.toString() + File.separator + fileName);
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Prescrizione[] arrayPrescrizioni= listaPrescrizioni.toArray(new Prescrizione[0]);

        oos.writeObject(arrayPrescrizioni);

        oos.close();
        fop.close();
    }

    public void salvaPrescrizioneSuFile(int id, File fileToSave) throws IOException {
        Prescrizione prescrizioneSelezionata = ricercaPrescrizione(id);
        String outputFile = fileToSave.getAbsolutePath();
        String font = "src/GestionePrescrizioni/MyFont.TTF";

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType0Font.load(document, new File(font)), 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.setLeading(14.5f);
            contentStream.showText("PRESCRIZIONE MEDICA OSPEDALE FIDATIN");
            contentStream.newLine();contentStream.newLine();
            contentStream.showText("DOTTORE: " + prescrizioneSelezionata.getDottore().getNome()+ " " + prescrizioneSelezionata.getDottore().getCognome());
            contentStream.newLine();
            contentStream.showText("PAZIENTE: " + prescrizioneSelezionata.getPaziente().getNome()+ " " + prescrizioneSelezionata.getPaziente().getCognome());
            contentStream.newLine();contentStream.newLine();
            contentStream.showText("OGGETTO PRESCRIZIONE: ");
            contentStream.newLine();
            contentStream.showText(prescrizioneSelezionata.getOggettoPrescrizione());
            contentStream.endText();
            contentStream.close();

            document.save(outputFile);
            document.close();

            JOptionPane.showMessageDialog(null, "Prescrizione stampata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}



