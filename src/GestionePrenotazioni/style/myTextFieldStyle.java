package GestionePrenotazioni.style;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class myTextFieldStyle extends JTextField {
    public myTextFieldStyle (int value){
        super(value);
        setBorder(new LineBorder(new Color(0x1A5690), 3));
    }
}
