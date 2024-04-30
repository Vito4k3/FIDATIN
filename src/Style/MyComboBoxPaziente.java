package Style;

import GestionePazienti.model.Paziente;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class MyComboBoxPaziente extends JComboBox<Paziente>{
    private DefaultComboBoxModel<Paziente> modelloCombo;

    public MyComboBoxPaziente(Paziente[] listaPazienti) {
        super();
        setBorder(new LineBorder(new Color(0x1A5690), 3));
        setFocusable(false);
        setBackground(Color.white);
        setUI(new BasicComboBoxUI());
        modelloCombo = new DefaultComboBoxModel<>(listaPazienti);
        setModel(modelloCombo);
        setRenderer(new PazienteComboBoxRenderer());
    }


    private class PazienteComboBoxRenderer extends JLabel implements ListCellRenderer<Paziente> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Paziente> list, Paziente paziente, int index, boolean isSelected, boolean cellHasFocus) {
            if (paziente != null) {
                setText(paziente.getNome() + " " + paziente.getCognome());
                setOpaque(true);
                setBackground(isSelected ? new Color(163,184,204) : Color.WHITE);
                setForeground(Color.BLACK);
            }else{
                setText("Nessun paziente esistente");
            }
            return this;
        }
    }

}
