package GestionePrenotazioni.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {     //quando una riga della tabella viene cliccata

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(isSelected) {
            c.setBackground(new Color(0x1A60A1));  // colore di sfondo su cui si desidera cambiare
            c.setForeground(Color.white);
        } else {
            c.setBackground(table.getBackground()); //impostare il colore di sfondo predefinito della tabella
            c.setForeground(table.getForeground());
        }
        return c;
    }

}
