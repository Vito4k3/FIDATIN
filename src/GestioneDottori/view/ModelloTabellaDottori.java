package GestioneDottori.view;

import GestioneDottori.model.Dottore;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModelloTabellaDottori extends AbstractTableModel {
    List<Dottore> listaDottori;
    private String[] nomiColonne= {"Nome", "Cognome", "Tipo Operatore", "Inizio turno", "fine turno", "Stato", "ID"};
    public ModelloTabellaDottori(){}

    public void setListaDottori(List<Dottore> listaDottori){
        this.listaDottori = listaDottori;
    }

    @Override
    public String getColumnName(int column) {
        return nomiColonne[column];
    }

    @Override
    public int getRowCount() {
        return listaDottori.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dottore dottore= this.listaDottori.get(rowIndex);
        switch(columnIndex){
            case 0:
                return dottore.getNome();
            case 1:
                return dottore.getCognome();
            case 2:
                return dottore.getTipoOperatore();
            case 3:
                return dottore.getOrarioLavorativoInizio();
            case 4:
                return dottore.getOrarioLavorativoFine();
            case 5:
                return dottore.getStato();
            case 6:
                return dottore.getId();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex);
    }




}
