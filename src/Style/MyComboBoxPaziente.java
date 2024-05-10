package Style;

import GestionePazienti.model.Paziente;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.List;

public class MyComboBoxPaziente extends JComboBox<Paziente>{
    private DefaultComboBoxModel<Paziente> modelloCombo;

    public MyComboBoxPaziente(List<Paziente> listaPazienti) {
        super();
        setBorder(new LineBorder(new Color(0x1A5690), 3));
        setFocusable(false);
        setBackground(Color.white);
        setUI(new BasicComboBoxUI());
        Paziente[] pazienti = listaPazienti.toArray(new Paziente[0]);
        modelloCombo = new DefaultComboBoxModel<>(pazienti);
        setModel(modelloCombo);
        setRenderer(new PazienteComboBoxRenderer());

    }


    public void sostituisciLista(List<Paziente> lista) {
        // Rimuovi tutti gli elementi dal modello del combobox
        modelloCombo.removeAllElements();

        // Aggiungi gli elementi della nuova lista al modello del combobox
        for (Paziente paziente : lista) {
            modelloCombo.addElement(paziente);
        }

        // Notifica il combobox che il modello dei dati è stato cambiato
        revalidate();
        repaint();
    }

    private static class PazienteComboBoxRenderer extends JLabel implements ListCellRenderer<Paziente> {
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
