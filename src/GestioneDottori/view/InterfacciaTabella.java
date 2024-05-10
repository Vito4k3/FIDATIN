package GestioneDottori.view;


import GestioneDottori.model.Dottore;
import Style.MyScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfacciaTabella extends JPanel {
    private JTable table;
    private ModelloTabellaDottori modelloTabellaDottori;
    public InterfacciaTabella (){

        modelloTabellaDottori = new ModelloTabellaDottori();
        CustomTableCellRenderer tableCellRenderer = new CustomTableCellRenderer();

        table= new JTable(modelloTabellaDottori);
        table.setDefaultRenderer(Object.class, tableCellRenderer);

        table.setRowHeight(table.getRowHeight()+20);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(30,30));
        table.getTableHeader().setBackground(new Color(255, 255, 255));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(30, 30, 30, 50));

        table.setIntercellSpacing(new Dimension(0,2));  //disattiva il bordo delle linee verticali e setta a 3 quelle orizzontali


        table.setOpaque(false);
        table.setBackground(Color.WHITE);
        table.setFont(new Font(null, Font.PLAIN, 13));
        table.setForeground(new Color(0x800000));

        setLayout(new BorderLayout());
        add(new MyScrollPane(table));
    }

    public void setDati(List<Dottore> listaDottori){
        modelloTabellaDottori.setListaDottori(listaDottori);
    }

    public void aggiorna(){
        this.modelloTabellaDottori.fireTableDataChanged();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }



}
