package GestionePrescrizioni.model;
import GestionePrenotazioni.model.Prenotazione;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class GestionePrescrizioni {
    private ArrayList<Prescrizione> prescrizione;

    public GestionePrescrizioni() {
        prescrizione = new ArrayList<Prescrizione>(); 
    }
    
    public void InserimentoPrescrizione(Prescrizione c){
        prescrizione.add(c);
    }
    
    public String Stampa(){
        String s = "";
        for(Prescrizione c:prescrizione){
            s += c.toString() + "\n";
        }
    
        return s;
    }
    
    
    public void ricercaPrescrizione(String nomeCliente, String cognomeCliente){
        for(Prescrizione a:prescrizione){     
            if(a instanceof Prescrizione){
               System.out.println("Prescrizione trovata");
            }else{
                 System.out.println("Perscrizione non trovata");
        }
        
    }
        for(Prescrizione a:prescrizione){      
            if(a instanceof Prescrizione){
               System.out.println("Prescrizione trovata");
            }else{
                 System.out.println("Perscrizione non trovata");
        }
        
    }
}
    
    
    public Prescrizione rimozionePrescrizione(String nomeCliente, String cognomeCliente){
        if(prescrizione.size()-1!=0){
            prescrizione.remove(nomeCliente);
            prescrizione.remove(cognomeCliente);
              System.out.println("Prescrizione eliminata");
        }else{
            return null;
        }
        return null;
    }

    public void rimuoviPrescrizione(int index){
        if(index>=0){
            prescrizione.remove(index);
        }
    }

    public ArrayList<Prescrizione> getPrescrizioni(){
        return this.prescrizione;
    }

    public void caricaDaFile(File file) throws IOException {
        if (file.length() != 0) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                Prescrizione[] prescrizioniCaricate = (Prescrizione[]) ois.readObject();

                prescrizione.clear();
                prescrizione.addAll(Arrays.asList(prescrizioniCaricate));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ois.close();
            fis.close();
        }
    }

    public void salvaSuFile(File file) throws IOException {
        FileOutputStream fop= new FileOutputStream(file);
        ObjectOutputStream oos= new ObjectOutputStream(fop);

        Prescrizione[] arrayPrescrizioni= prescrizione.toArray(new Prescrizione[prescrizione.size()]);

        oos.writeObject(arrayPrescrizioni);

        oos.close();
        fop.close();
    }

    public void salvaPrescrizioneSuFile(File file, int index) throws IOException {
        Prescrizione prescrizioneSelezionata = prescrizione.get(index);
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

            System.out.println("File PDF creato con successo: " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void caricaFile(File file){
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File creato!");
            }else{
                caricaDaFile(file);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}



