package Style;

import Eventi.Evento;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterfacciaTab extends JPanel {

    private JLabel title;
    private JButton buttonEsci, button2, buttonIcon;
    private JPanel panel, panel2, panel3;
    private Evento evento;
    private Color defaultColor;
    ImageIcon icona = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/menu_list_options_basic_icon_192477.png")));
    Image image = icona.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
    ImageIcon iconaRidimensionata = new ImageIcon(image);


    public InterfacciaTab(String titleTab){
        init(titleTab);
        panel.add(title);
        panel2.add(buttonEsci);

        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.LINE_END);
    }
    public InterfacciaTab(String titleTab, int i){
        //button2= new MyButtonStyle("Profilo", Color.white);
        buttonIcon= new MyButtonStyle(iconaRidimensionata);

        init(titleTab);
        panel3.add(buttonIcon);
        panel.add(title);
        //panel2.add(button2);
        panel2.add(buttonEsci);

        add(panel3, BorderLayout.LINE_START);
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.LINE_END);
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
        panel3= new JPanel(new FlowLayout());
        panel3.setOpaque(false);

        title= new JLabel(titleTab);
        title.setFont(new Font(null, Font.BOLD, 15));
        title.setForeground(Color.white);
        title.setBorder(new EmptyBorder(12,0,0,0));
        buttonEsci= new MyButtonStyle("HOME", Color.white);
        buttonEsci.setForeground(Color.black);
        buttonEsci.setPreferredSize(new Dimension(70,45));
        buttonEsci.setFocusable(false);
        buttonEsci.setBackground(Color.white);

        button2= new MyButtonStyle("Profilo", Color.white);
        button2.setForeground(Color.black);
        button2.setPreferredSize(new Dimension(70,45));
        button2.setFocusable(false);
        button2.setBackground(Color.white);
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
