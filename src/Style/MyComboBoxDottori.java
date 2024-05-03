package Style;

import GestioneDottori.model.Dottore;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class MyComboBoxDottori extends JComboBox<Dottore>{
    private DefaultComboBoxModel<Dottore> modelloCombo;


    public MyComboBoxDottori(List<Dottore> listaDottori) {
        super();
        setBorder(new LineBorder(new Color(0x1A5690), 3));
        setFocusable(false);
        setBackground(Color.white);
        setUI(new BasicComboBoxUI());

        // Imposta il renderer personalizzato per la JComboBox
        setRenderer(new DottoreComboBoxRenderer());

        Dottore[] arrayDottori = listaDottori.toArray(new Dottore[listaDottori.size()]);
        modelloCombo = new DefaultComboBoxModel<>(arrayDottori);
        setModel(modelloCombo);
    }


    /*public Dottore getDottoreSelezionato() {
        return (Dottore) modelloCombo.getSelectedItem();
    }*/
    public void sostituisciLista(List<Dottore> lista) {
        // Rimuovi tutti gli elementi dal modello del combobox
        modelloCombo.removeAllElements();

        // Aggiungi gli elementi della nuova lista al modello del combobox
        for (Dottore dottore : lista) {
            modelloCombo.addElement(dottore);
        }

        // Notifica il combobox che il modello dei dati è stato cambiato
        revalidate();
        repaint();
    }

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
