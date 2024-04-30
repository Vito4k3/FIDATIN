/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionePrescrizioni;
import Style.*;

import java.awt.*;
import java.awt.event.*;
import javax.naming.BinaryRefAddr;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SchermataPrescrizione extends JPanel{
    
 final int larghezza = 800;
    final int altezza = 600;
    private JPanel containerJp;
    private JPanel toolbarJp;
    private JPanel southJp;
    private JLabel southJl;
    private JPanel ovestJp;
    private JPanel estJp;
    private JPanel centerJp;
    private JTable table;
    private JLabel vuota = new JLabel(" ");
    private JLabel vuota1 = new JLabel(" ");
    private final Object colonne[] = {"Paziente","Dottore","Data","Contenuto"};
    private InterfacciaTab interfacciaTab;
    
    public SchermataPrescrizione(){
        init();
    }
    
    private void init(){

        setLayout(new BorderLayout());
        setVisible(true);
        
       
        containerJp = new JPanel(new BorderLayout(20, 10));
        interfacciaTab = new InterfacciaTab("Gestione Prescrizioni");
        interfacciaTab.setColor(new Color(54, 159, 148));
        
        // NORTH
        toolbarJp = new JPanel(new BorderLayout());
        toolbarJp.setBorder(new EmptyBorder(0,0,40,0));
        toolbarJp.add(interfacciaTab);
        
      
        containerJp.add(toolbarJp, BorderLayout.NORTH);


        
        // OVEST:
        ovestJp = new JPanel();
        ovestJp.setBorder(new EmptyBorder(0,10,0,0));
        ovestJp.setLayout(new GridLayout(11,1));
        JButton pulsanteCreaPrescrizione = new MyButtonStyle ("Crea Prescrizione", new Color(54, 159, 148));
        pulsanteCreaPrescrizione.setFont(new Font("Arial",1,30));
        pulsanteCreaPrescrizione.setPreferredSize(new Dimension(270,30));
        ovestJp.add(pulsanteCreaPrescrizione);
        ovestJp.add(vuota);
        JButton pulsanteStampa = new MyButtonStyle("Stampa", new Color(54, 159, 148));
        pulsanteStampa .setFont(new Font("Arial",1,30));
        ovestJp.add(pulsanteStampa);
        ovestJp.add(vuota1);
        JButton pulsanteModifica = new MyButtonStyle ("Modifica", new Color(54, 159, 148));
       pulsanteModifica .setFont(new Font("Arial",1,30));
       ovestJp.add(pulsanteModifica);
        
      
        containerJp.add(ovestJp, BorderLayout.WEST);
        
        // EST
        estJp = new JPanel();
        // add to container panel
        containerJp.add(estJp, BorderLayout.EAST);
        
        //CENTER
        centerJp = new MyPanel(new GridLayout(1, 1, 10, 5));
        centerJp.setBorder(new EmptyBorder(0,0,30,0));

         
        table = new MyTableStyle(new Object[4][5],colonne);
        JScrollPane scrollPane = new MyScrollPane(table);
        centerJp.add(scrollPane);
        containerJp.add(centerJp);
        
        add(containerJp);

        setVisible(true);
    }

    public static void main(String[] args) {
        new SchermataPrescrizione();
    }

    public InterfacciaTab getTab(){
        return interfacciaTab;
    }
}

