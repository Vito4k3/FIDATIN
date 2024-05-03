package GestionePrenotazioni.view;

import GestioneDottori.model.Dottore;
import GestionePrenotazioni.model.Prenotazione;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelloTabellaPrenotazioni extends AbstractTableModel {
    List<Prenotazione> listaPrenotazioni;
    private String[] nomiColonne= {"Paziente", "Dottore", "Data", "Ora", "Reparto", "Prestazione"};
    ModelloTabellaPrenotazioni(){}

    public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni){
        this.listaPrenotazioni= listaPrenotazioni;
    }

    public void aggiornaDatiDottore(Dottore dottore, Dottore dottoreNuovo) {
        // Itera attraverso tutte le prenotazioni
        for (Prenotazione prenotazione : listaPrenotazioni) {
            // Controlla se il dottore della prenotazione è lo stesso del dottore modificato
            if (prenotazione.getDottore().equals(dottore)) {
                // Aggiorna il dottore nella prenotazione con il nuovo dottore
                prenotazione.setDottore(dottoreNuovo);
            }
        }
        // Aggiorna la tabella
        aggiorna();
    }

    @Override
    public String getColumnName(int column) {
        return nomiColonne[column];
    }

    @Override
    public int getRowCount() {
        return listaPrenotazioni.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prenotazione prenotazione= this.listaPrenotazioni.get(rowIndex);
        switch(columnIndex){
            case 0:
                return prenotazione.getPaziente().getNome() + " " + prenotazione.getPaziente().getCognome();
            case 1:
                return prenotazione.getDottore().getNome() + " " + prenotazione.getDottore().getCognome();
            case 2:
                return prenotazione.getData();
            case 3:
                return prenotazione.getOra();
            case 4:
                return prenotazione.getReparto();
            case 5:
                return prenotazione.getTipoPrenotazione();
            default:
                return null;
        }
    }

    public void aggiorna(){
        this.fireTableDataChanged();
    }
}
