package Style;

import Eventi.Evento;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class InterfacciaTab extends JPanel {

    private JLabel title, logo;
    private JButton buttonEsci, button2, buttonIcon, buttonEsciLogo;
    private JPanel panel, panel2, panelMenu;
    private Evento evento;
    private Color defaultColor;
    ImageIcon icona = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/iconBar.png")));
    Image image = icona.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconaRidimensionata = new ImageIcon(image);

    ImageIcon icona2 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/logo.png")));
    Image imageLogo = icona2.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon iconaLogoRidimensionata = new ImageIcon(imageLogo);

    ImageIcon icona3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/iconExit.png")));
    Image imageExit = icona3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconaExitRidimensionata = new ImageIcon(imageExit);

    ImageIcon icona4 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/iconHome.png")));
    Image imageHome = icona4.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconaHomeRidimensionata = new ImageIcon(imageHome);


    public InterfacciaTab(String titleTab){
        init(titleTab);
        panel.add(title);

        add(panel, BorderLayout.CENTER);
        add(buttonEsci, BorderLayout.LINE_END);
    }
    public InterfacciaTab(String titleTab, int i){
        setLayout(new BorderLayout());
        defaultColor= new Color(0x1A5690);
        setPreferredSize(new Dimension(1,60));
        setBackground(defaultColor);

        Border lineBorder = new LineBorder(Color.black, 3);
        setBorder(lineBorder);

        logo = new JLabel(iconaLogoRidimensionata);
        buttonIcon= new MyButtonStyle(iconaRidimensionata);
        buttonEsci= new MyButtonStyle(iconaExitRidimensionata);

        buttonIcon.setBorder(new EmptyBorder(0,10,0,0));
        add(buttonIcon, BorderLayout.LINE_START);
        add(logo, BorderLayout.CENTER);
        add(buttonEsci, BorderLayout.LINE_END);
    }

    public void init(String titleTab){
        setLayout(new BorderLayout());

        defaultColor= new Color(0x1A5690);
        setPreferredSize(new Dimension(1,60));
        setBackground(defaultColor);

        Border lineBorder = new LineBorder(Color.black, 3);
        setBorder(lineBorder);

        panel= new JPanel(new FlowLayout());
        panel2= new JPanel(new FlowLayout());
        panel.setOpaque(false);
        panel2.setOpaque(false);
        panelMenu= new JPanel(new FlowLayout());
        panelMenu.setOpaque(false);

        title= new JLabel(titleTab);
        title.setFont(new Font(null, Font.BOLD, 15));
        title.setForeground(Color.white);
        title.setBorder(new EmptyBorder(12,0,0,0));
        buttonEsci= new MyButtonStyle(iconaHomeRidimensionata);

    }

    public void init2(String titleTab){
        init(titleTab);
    }

    public void setEvento(Evento e){
        this.evento=e;
    }

    public void setColor(Color color){
        setBackground(color);
    }

    public JButton getButtonIcon(){
        return this.buttonIcon;
    }

    public JButton getButtonEsci(){
        return buttonEsci;
    }
}
