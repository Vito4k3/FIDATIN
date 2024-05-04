package GestionePrescrizioni.model;
import GestioneDottori.model.Dottore;
import GestionePazienti.model.Paziente;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import javax.swing.*;


public class DatabasePrescrizioni {
    private ArrayList<Prescrizione> listaPrescrizioni;
    private File file = new File(System.getProperty("user.home"), "databasePrescrizioni.dat");

    public DatabasePrescrizioni() {
        listaPrescrizioni = new ArrayList<Prescrizione>();
    }
    
    public void InserimentoPrescrizione(Prescrizione c){
        listaPrescrizioni.add(c);
    }


    public void rimuoviPrescrizione(int index){
        if(index>=0){
            listaPrescrizioni.remove(index);
        }
    }


    public void AggiornaDottorePrenotazione(Dottore vecchioDottore, Dottore nuovoDottore){
        for(int i=0; i<listaPrescrizioni.size(); i++){
            if(listaPrescrizioni.get(i).getDottore().equals(vecchioDottore)){
                listaPrescrizioni.get(i).setDottore(nuovoDottore);
            }
        }
        try {
            salvaSuFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AggiornaPazientePrenotazione(Paziente vecchioPaziente, Paziente nuovoPaziente){
        for(int i=0; i<listaPrescrizioni.size(); i++){
            if(listaPrescrizioni.get(i).getPaziente().equals(vecchioPaziente)){
                listaPrescrizioni.get(i).setPaziente(nuovoPaziente);
            }
        }
        try {
            salvaSuFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Prescrizione> getPrescrizioni(){
        return this.listaPrescrizioni;
    }

    public void caricaDaFile() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("File creato!");
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

            ois.close();
            fis.close();
        }
    }

    public void salvaSuFile() throws IOException {
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Prescrizione[] arrayPrescrizioni= listaPrescrizioni.toArray(new Prescrizione[listaPrescrizioni.size()]);

        oos.writeObject(arrayPrescrizioni);

        oos.close();
        fop.close();
    }

    public void salvaPrescrizioneSuFile(int index) throws IOException {
        Prescrizione prescrizioneSelezionata = listaPrescrizioni.get(index);
        String outputFile = file.getAbsolutePath();
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



