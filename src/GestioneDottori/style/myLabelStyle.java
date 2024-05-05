package GestioneDottori.style;

import javax.swing.*;
import java.awt.*;

public class myLabelStyle extends JLabel {
    public myLabelStyle(String text){
        super(text);
        setFont(new Font("myLabelStyle", Font.PLAIN, 15));
    }
}
