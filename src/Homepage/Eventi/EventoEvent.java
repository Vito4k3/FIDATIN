package Homepage.Eventi;

import javax.swing.*;
import java.util.EventObject;

public class EventoEvent extends EventObject {
    private JButton buttonPrenotazioni, premuto, buttonDottori, buttonPrescrizioni, buttonPazienti, buttonHomepage;


    public EventoEvent(Object source, JButton buttonPrenotazioni, JButton buttonDottori, JButton buttonPrescrizioni, JButton buttonPazienti, JButton buttonHomepage, JButton premuto) {
        super(source);
        this.buttonPrenotazioni = buttonPrenotazioni;
        this.premuto = premuto;
        this.buttonDottori = buttonDottori;
        this.buttonPrescrizioni = buttonPrescrizioni;
        this.buttonPazienti = buttonPazienti;
        this.buttonHomepage = buttonHomepage;
    }

    public JButton getButtonPrescrizioni() {
        return buttonPrescrizioni;
    }

    public void setButtonPrescrizioni(JButton buttonPrescrizioni) {
        this.buttonPrescrizioni = buttonPrescrizioni;
    }

    public JButton getButtonPazienti() {
        return buttonPazienti;
    }

    public void setButtonPazienti(JButton buttonPazienti) {
        this.buttonPazienti = buttonPazienti;
    }

    public JButton getButtonPrenotazioni() {
        return buttonPrenotazioni;
    }

    public void setButtonPrenotazioni(JButton buttonPrenotazioni) {
        this.buttonPrenotazioni = buttonPrenotazioni;
    }

    public JButton getPremuto() {
        return premuto;
    }

    public void setPremuto(JButton premuto) {
        this.premuto = premuto;
    }

    public JButton getButtonDottori() {
        return buttonDottori;
    }

    public void setButtonDottori(JButton buttonDottori) {
        this.buttonDottori = buttonDottori;
    }

    public JButton getButtonHomepage() {
        return buttonHomepage;
    }

    public void setButtonHomepage(JButton buttonHomepage) {
        this.buttonHomepage = buttonHomepage;
    }
}
