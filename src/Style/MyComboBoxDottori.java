package Style;

import GestioneDottori.model.Dottore;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class MyComboBoxDottori extends JComboBox<Dottore>{
    private DefaultComboBoxModel<Dottore> modelloCombo;

    public MyComboBoxDottori(Dottore[] listaDottori) {
        super();
        setBorder(new LineBorder(new Color(0x1A5690), 3));
        setFocusable(false);
        setBackground(Color.white);
        setUI(new BasicComboBoxUI());
        modelloCombo = new DefaultComboBoxModel<>(listaDottori);
        setModel(modelloCombo);
        setRenderer(new DottoreComboBoxRenderer());
    }

    /*public Dottore getDottoreSelezionato() {
        return (Dottore) modelloCombo.getSelectedItem();
    }*/

    private class DottoreComboBoxRenderer extends JLabel implements ListCellRenderer<Dottore> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Dottore> list, Dottore dottore, int index, boolean isSelected, boolean cellHasFocus) {
            if (dottore != null) {
                setText(dottore.getNome() + " " + dottore.getCognome());
                setOpaque(true);
                setBackground(isSelected ? new Color(163,184,204) : Color.WHITE);
                setForeground(Color.BLACK);
            }else{
                setText("Nessun dottore esistente");
            }
            return this;
        }
    }

}
