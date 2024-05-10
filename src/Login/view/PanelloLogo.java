package Login.view;

/*
    Coded by Vito Francesco Cosola
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PanelloLogo extends JPanel {

    JLabel nomeOspedale, logo;
    ImageIcon icona = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/logo.png")));
    Image image = icona.getImage().getScaledInstance(275, 275, Image.SCALE_SMOOTH);
    ImageIcon iconaRidimensionata = new ImageIcon(image);
    public PanelloLogo() {

        nomeOspedale= new JLabel("F I D A T I N");
        nomeOspedale.setFont(new Font(null, Font.BOLD, 20));
        logo= new JLabel(iconaRidimensionata);

        setPreferredSize(new Dimension(400,100));
        setLayout(new GridBagLayout());
        //setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(new Color(135,206,250));

        GridBagConstraints gbc = new GridBagConstraints();

        add(logo);
        //add(nomeOspedale, gbc);

    }
}
