package Style;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyTableStyle extends JTable{

    public MyTableStyle(TableModel dm) {
        super(dm);
        init();
    }

    public MyTableStyle(@NotNull Object[][] rowData, @NotNull Object[] columnNames) {
        super(rowData, columnNames);
        init();
    }

    public void init(){
        setRowHeight(getRowHeight() + 20);    //imposta la grandezza delle linee a +20 rispetto a quella originale
        getTableHeader().setReorderingAllowed(false);  //toglie possibilità di spostare le colonne
        getTableHeader().setResizingAllowed(false);   //toglie possibilità di ridimensionare le colonne
        getTableHeader().setPreferredSize(new Dimension(30, 30));
        getTableHeader().setBackground(new Color(255, 255, 255));
        getTableHeader().setBorder(new EmptyBorder(0, 0, 0, 0));
        setShowHorizontalLines(true);     //attiva linee orizzontali
        setShowVerticalLines(false);  //disattiva la visibilità delle linee verticali della tabella
        setGridColor(new Color(30, 30, 30, 50));  //imposta il colore nelle linee della tabella

        setIntercellSpacing(new Dimension(0, 2));  //disattiva il bordo delle linee verticali e setta a 3 quelle orizzontali

        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font(null, Font.PLAIN, 13));
        setForeground(new Color(0x1A5690));
    }



}

