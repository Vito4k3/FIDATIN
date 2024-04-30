package GestionePrenotazioni.view;

import javax.swing.*;
import java.awt.*;

public class MyOptionPane extends JOptionPane {

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType)
            throws HeadlessException {
        showMessageDialog(parentComponent, message, title, messageType, null);

    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon)
            throws HeadlessException {
        JOptionPane optionPane = new JOptionPane(message, messageType, JOptionPane.DEFAULT_OPTION, icon, null, null);
        optionPane.setBackground(Color.black); // Impostazione dello sfondo bianco

        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}