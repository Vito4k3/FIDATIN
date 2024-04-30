package GestionePrescrizioni.model;
import java.util.ArrayList;
    


public class Gestione {

        ArrayList<Prescrizione> prescrizione;

    public Gestione() {
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
    
}



