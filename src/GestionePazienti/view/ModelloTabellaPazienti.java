package GestionePazienti.view;

import GestionePazienti.model.Paziente;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelloTabellaPazienti extends AbstractTableModel {
    private String[] nomiColonne = { "Nome", "Cognome", "Data di nascita", "Codice Fiscale", "Sesso", "Residenza", "Cap" , "ID"};
    private List<Paziente> listaPazienti;

    public ModelloTabellaPazienti(List<Paziente> lista){
        listaPazienti = new ArrayList<>();
        this.listaPazienti = lista;
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return nomiColonne[column];
    }

    @Override
    public int getRowCount() {
        return listaPazienti.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paziente paziente= listaPazienti.get(rowIndex);
        switch(columnIndex){
            case 0:
                return paziente.getNome();
            case 1:
                return paziente.getCognome();
            case 2:
                return paziente.getDataNascita();
            case 3:
                return paziente.getCodiceFiscale();
            case 4:
                return paziente.getSesso();
            case 5:
                return paziente.getResidenza();
            case 6:
                return paziente.getCap();
            case 7:
                return paziente.getId();
            default:
                return null;
        }
    }
}
