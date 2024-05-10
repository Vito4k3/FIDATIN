package Login.view;

/*
    Coded by Vito Francesco Cosola
 */

import Login.Eventi.Evento;
import Login.Eventi.EventoEvent;
import Login.model.IDandPassword;
import Style.MyButtonStyle;
import Style.MyPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterfacciaLogin extends MyPanel implements ActionListener {
    JLabel labelNome, labelPassword, labelTitle, labelMessaggi;
    JTextField fieldNome;
    JPasswordField fieldPassword;
    JButton bottoneInvia;
    IDandPassword idandPassword;
    private Evento evento;

    public InterfacciaLogin(){
        super(new GridBagLayout());
        setArco(40);
        setWidth(-16);


        labelTitle = new JLabel("LOGIN");
        labelTitle.setFont(new Font(null, Font.BOLD, 20));
        labelTitle.setForeground(new Color(14, 111, 173));
        labelNome= new JLabel("Nome: ");
        fieldNome= new JTextField(15);
        labelPassword= new JLabel("Password:");
        fieldPassword = new JPasswordField(15);
        bottoneInvia= new MyButtonStyle("Accedi", new Color(135,206,250));
        bottoneInvia.setPreferredSize(new Dimension(90,30));
        labelMessaggi = new JLabel(" ");

        setPreferredSize(new Dimension(200,500));
        //setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        setBackground(Color.white);

        labelTitle.setBackground(new Color(135,206,250));
        fieldNome.setBackground(new Color(240,255,255));
        fieldNome.setBorder(null);
        fieldNome.setBorder(new EmptyBorder(0,5,0,0));
        fieldNome.setFont(new Font(null, Font.BOLD, 13));
        fieldPassword.setBackground(new Color(240,255,255));
        fieldPassword.setBorder(new EmptyBorder(0,5,0,0));
        fieldPassword.setFont(new Font(null, Font.BOLD, 13));
        bottoneInvia.setBackground(new Color(135,206,250));
        bottoneInvia.setBorder(null);
        bottoneInvia.addActionListener(this);
        bottoneInvia.setFocusable(false);
        labelMessaggi.setFocusable(false);
        labelMessaggi.setBorder(null);
        labelMessaggi.setBackground(Color.white);

        idandPassword = new IDandPassword();

        gbc.gridx=0;
        gbc.gridy=0;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=30;
        gbc.ipady=30;

        gbc.gridwidth=2;

        gbc.insets= new Insets(0, 45, 30, 0);

        add(labelTitle, gbc);

        gbc.gridx=0;
        gbc.gridy=1;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=15;
        gbc.ipady=10;

        gbc.gridwidth=1;

        gbc.insets= new Insets(0, 0, 15, 5);

        add(labelNome, gbc);

        gbc.gridx=1;
        gbc.gridy=1;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=15;
        gbc.ipady=10;

        gbc.gridwidth=1;

        gbc.insets= new Insets(0, 0, 15, 0);

        add(fieldNome, gbc);

        gbc.gridx=0;
        gbc.gridy=2;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=15;
        gbc.ipady=10;

        gbc.gridwidth=1;

        gbc.insets= new Insets(0, 0, 0, 5);

        add(labelPassword, gbc);

        gbc.gridx=1;
        gbc.gridy=2;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=15;
        gbc.ipady=10;

        gbc.gridwidth=1;

        gbc.insets= new Insets(0, 0, 5, 0);

        add(fieldPassword, gbc);

        gbc.gridx=0;
        gbc.gridy=3;

        gbc.weightx=0;
        gbc.weighty=0;

        gbc.ipadx=15;
        gbc.ipady=10;

        gbc.gridwidth=2;

        gbc.insets= new Insets(30, 20, 0, 0);


        add(bottoneInvia, gbc);

        gbc.gridx=0;
        gbc.gridy=4;

        gbc.insets= new Insets(30, 50, 0, 0);

        add(labelMessaggi, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String insertId = fieldNome.getText();
        String insertPassword = String.valueOf(fieldPassword.getPassword());

        EventoEvent event = new EventoEvent(this, insertId, insertPassword, labelMessaggi);
        evento.evento(event);
    }

    public void setEvento(Evento e){
        evento=e;
    }
}