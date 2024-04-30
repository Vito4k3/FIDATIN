package Login.Eventi;

import javax.swing.*;
import java.util.EventObject;

public class EventoEvent extends EventObject {
    private String insertId, insertPassword;
    private JLabel labelMessaggi;
    public EventoEvent(Object source, String insertId, String insertPassword, JLabel labelMessaggi) {
        super(source);
        this.insertId = insertId;
        this.insertPassword = insertPassword;
        this.labelMessaggi = labelMessaggi;
    }

    public String getInsertId() {
        return insertId;
    }

    public void setInsertId(String insertId) {
        this.insertId = insertId;
    }

    public String getInsertPassword() {
        return insertPassword;
    }

    public void setInsertPassword(String insertPassword) {
        this.insertPassword = insertPassword;
    }

    public JLabel getLabelMessaggi() {
        return labelMessaggi;
    }

    public void setLabelMessaggi(JLabel labelMessaggi) {
        this.labelMessaggi = labelMessaggi;
    }
}
