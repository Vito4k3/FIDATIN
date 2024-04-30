package GestioneDottori.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(isSelected) {
            c.setBackground(new Color( 0x9B3F3F));  // colore di sfondo su cui si desidera cambiare
            c.setForeground(Color.white);
            c.setFont(new Font(null, Font.BOLD, 15));
        } else {
            c.setBackground(table.getBackground()); //impostare il colore di sfondo predefinito della tabella
            c.setForeground(table.getForeground());
            c.setFont(table.getFont());
        }
        return c;

    }




}
