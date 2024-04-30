package Eventi;

import javax.swing.*;
import java.util.EventObject;

public class EventoEvent extends EventObject {
    private JButton buttonAggiungi, buttonModifica, buttonElimina, buttonPremuto, buttonOrdina;
    private JCheckBox visualizzaDottoriAttivi;
    private JFrame frame;

    public EventoEvent(Object source, JButton buttonAggiungi, JButton buttonModifica, JButton buttonElimina, JCheckBox visualizzaDottoriAttivi, JButton buttonPremuto) {
        super(source);
        this.buttonAggiungi = buttonAggiungi;
        this.buttonModifica = buttonModifica;
        this.buttonElimina = buttonElimina;
        this.buttonPremuto = buttonPremuto;
        this.visualizzaDottoriAttivi= visualizzaDottoriAttivi;
    }

    public EventoEvent(Object source, JButton buttonAggiungi, JButton buttonModifica, JButton buttonElimina, JButton buttonOrdina, JButton buttonPremuto) {
        super(source);
        this.buttonAggiungi = buttonAggiungi;
        this.buttonModifica = buttonModifica;
        this.buttonElimina = buttonElimina;
        this.buttonOrdina = buttonOrdina;
        this.buttonPremuto = buttonPremuto;
    }

    public JFrame getFrame(){return this.frame;}
    public JCheckBox getVisualizzaDottoriAttivi() {
        return visualizzaDottoriAttivi;
    }

    public void setVisualizzaDottoriAttivi(JCheckBox visualizzaDottoriAttivi) {
        this.visualizzaDottoriAttivi = visualizzaDottoriAttivi;
    }

    public JButton getButtonAggiungi() {
        return buttonAggiungi;
    }

    public void setButtonAggiungi(JButton buttonAggiungi) {
        this.buttonAggiungi = buttonAggiungi;
    }

    public JButton getButtonModifica() {
        return buttonModifica;
    }

    public void setButtonModifica(JButton buttonModifica) {
        this.buttonModifica = buttonModifica;
    }

    public JButton getButtonElimina() {
        return buttonElimina;
    }

    public void setButtonElimina(JButton buttonElimina) {
        this.buttonElimina = buttonElimina;
    }


    public JButton getButtonPremuto() {
        return buttonPremuto;
    }

    public void setButtonPremuto(JButton buttonPremuto) {
        this.buttonPremuto = buttonPremuto;
    }
    public JButton getButtonOrdina() {
        return buttonOrdina;
    }

    public void setButtonOrdina(JButton buttonOrdina) {
        this.buttonOrdina = buttonOrdina;
    }
}
