package GestionePrenotazioni.view;

import GestioneDottori.model.Dottore;
import GestionePrenotazioni.model.Prenotazione;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelloTabellaPrenotazioni extends AbstractTableModel {
    List<Prenotazione> listaPrenotazioni;
    int columnCount = 7;
    private String[] nomiColonne= {"Paziente", "Dottore", "Data", "Ora", "Reparto", "Prestazione", "ID"};
    public ModelloTabellaPrenotazioni(){}

    public void setListaPrenotazioni(List<Prenotazione> listaPrenotazioni){
        this.listaPrenotazioni= listaPrenotazioni;
    }
    public void setColumnCount(int count){
        this.columnCount = count;
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
        return columnCount;
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
            case 6:
                return prenotazione.getId();
            default:
                return null;
        }
    }

}
