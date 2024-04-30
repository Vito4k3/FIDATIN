package GestionePrenotazioni.view;


import Style.MyScrollPane;
import GestionePrenotazioni.view.CustomTableCellRenderer;
import GestionePrenotazioni.model.Prenotazione;
import Style.MyTableStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class InterfacciaTabella extends JPanel {
    private JTable table;
    private ModelloTabellaPrenotazioni modelloTabellaPrenotazioni;
    public InterfacciaTabella (){

        modelloTabellaPrenotazioni= new ModelloTabellaPrenotazioni();
        CustomTableCellRenderer tableCellRenderer = new CustomTableCellRenderer();

        table= new MyTableStyle(modelloTabellaPrenotazioni);
        table.setDefaultRenderer(Object.class, tableCellRenderer);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(25);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        MyScrollPane myScrollPane = new MyScrollPane(table);

        setLayout(new BorderLayout());
        add(myScrollPane);
    }

    public void setDati(List<Prenotazione> listaPrenotazioni){
        modelloTabellaPrenotazioni.setListaPrenotazioni(listaPrenotazioni);
    }

    public void aggiorna(){
        this.modelloTabellaPrenotazioni.fireTableDataChanged();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

}

