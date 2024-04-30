package Login.view;

/*
    Coded by Vito Francesco Cosola
 */

import javax.swing.*;
import java.awt.*;

public class PanelloLogo extends JPanel {

    JLabel nomeOspedale, logo;
    public PanelloLogo() {

        String image= "src/Icon/Logo.png";
        ImageIcon immagineLogo = new ImageIcon(image);

        nomeOspedale= new JLabel("F I D A T I N");
        nomeOspedale.setFont(new Font(null, Font.BOLD, 20));
        logo= new JLabel();
        logo.setIcon(immagineLogo);

        setPreferredSize(new Dimension(400,100));
        setLayout(new GridBagLayout());
        //setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(new Color(135,206,250));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy=0;
        gbc.gridx=0;

        //add(logo, gbc);

        gbc.gridy=1;
        gbc.gridx=0;

        add(nomeOspedale, gbc);

    }
}
