package GestioneDottori.view;

import GestioneDottori.model.Status;
import GestioneDottori.model.TipoOperatori;
import Style.MyButtonStyle;
import GestioneDottori.style.myLabelStyle;
import GestioneDottori.style.myTextFieldStyle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class InterfacciaInserimento extends JPanel {

    private JPanel mainPanel, panelFlowTitle, panelFlow1, panelFlow2, panelFlow4, panelFlow5, panelBox1, panelBox2, panelBox3,
            panelBox4, panelBox5, panelBox6;
    private JLabel title, labelNome, labelCognome, labelTipoOperatore, labelOrarioLavorativoInizio, labelOrarioLavorativoFine, labelStatus;
    private JComboBox boxTipoOperatore, boxStatus;
    private JTextField fieldNome, fieldCognome;
    private JButton buttonSalva;
    private JSpinner timeSpinner, timeSpinner2;
    private SpinnerDateModel timeModel, timeModel2;
    public InterfacciaInserimento(){
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Inizializzazione panel
        mainPanel = new JPanel();
        panelBox1= new JPanel();
        panelBox2= new JPanel();
        panelBox3= new JPanel();
        panelBox4= new JPanel();
        panelBox5= new JPanel();
        panelBox6= new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panelFlowTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFlow1 = new JPanel(new FlowLayout());
        panelFlow2 = new JPanel(new FlowLayout());
        panelFlow4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFlow5 = new JPanel(new FlowLayout());
        panelBox1.setLayout(new BoxLayout(panelBox1, BoxLayout.Y_AXIS));
        panelBox2.setLayout(new BoxLayout(panelBox2, BoxLayout.Y_AXIS));
        panelBox3.setLayout(new BoxLayout(panelBox3, BoxLayout.Y_AXIS));
        panelBox4.setLayout(new BoxLayout(panelBox4, BoxLayout.Y_AXIS));
        panelBox5.setLayout(new BoxLayout(panelBox5, BoxLayout.Y_AXIS));
        panelBox6.setLayout(new BoxLayout(panelBox6, BoxLayout.Y_AXIS));

        // Inizializzazione label
        title = new JLabel("Inserisci Prenotazione");
        labelNome = new myLabelStyle("Nome:");
        labelCognome = new myLabelStyle("Cognome:");
        labelTipoOperatore = new myLabelStyle("Tipo Operatore:");
        labelOrarioLavorativoInizio = new myLabelStyle("Inizio turno:");
        labelOrarioLavorativoFine = new myLabelStyle("Fine turno:");
        labelStatus = new myLabelStyle("Stato:");


        TipoOperatori[] tipoOperatori = TipoOperatori.values();
        Status[] status = Status.values();

        // Inizializzazione dei campi di testo
        boxTipoOperatore = new JComboBox(tipoOperatori);
        boxStatus = new JComboBox(status);
        fieldNome = new myTextFieldStyle(16);
        fieldNome.setFont(new Font(null, Font.BOLD, 14));
        fieldCognome = new myTextFieldStyle(15);
        fieldCognome.setFont(new Font(null, Font.BOLD, 14));

        // Inizializzazione del pulsante
        buttonSalva = new MyButtonStyle("Salva", new Color(0x800000));

        panelBox6.setPreferredSize(new Dimension(610,50));
        panelBox3.setPreferredSize(new Dimension(200,50));
        panelBox4.setPreferredSize(new Dimension(200,50));
        panelBox5.setPreferredSize(new Dimension(200,50));
        panelBox1.setPreferredSize(new Dimension(305,50));
        panelBox2.setPreferredSize(new Dimension(300,50));
        buttonSalva.setPreferredSize(new Dimension(80,40));
        title.setFont(new Font(null, Font.BOLD, 20));

        boxTipoOperatore.setBorder(new LineBorder(new Color(0x1A5690), 3));
        boxTipoOperatore.setFocusable(false);
        boxTipoOperatore.setBackground(Color.white);
        boxTipoOperatore.setUI(new BasicComboBoxUI());
        boxStatus.setBorder(new LineBorder(new Color(0x1A5690), 3));
        boxStatus.setFocusable(false);
        boxStatus.setBackground(Color.white);
        boxStatus.setUI(new BasicComboBoxUI());

        timeModel = new SpinnerDateModel();
        timeModel.setCalendarField(Calendar.MINUTE);

        timeModel2 = new SpinnerDateModel();
        timeModel2.setCalendarField(Calendar.MINUTE);

        timeSpinner = new JSpinner(timeModel);
        timeSpinner2 = new JSpinner(timeModel2);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(timeSpinner2, "HH:mm");
        timeSpinner.setEditor(timeEditor);
        timeSpinner2.setEditor(timeEditor2);

        timeSpinner.setBorder(new LineBorder(new Color(0x1A5690), 3));
        timeSpinner2.setBorder(new LineBorder(new Color(0x1A5690), 3));


        panelFlow4.add(buttonSalva);

        panelFlowTitle.add(title);

        panelBox6.add(labelStatus);
        panelBox6.add(boxStatus);

        panelFlow1.add(panelBox6);

        panelBox3.add(labelTipoOperatore);
        panelBox3.add(boxTipoOperatore);

        panelBox4.add(labelOrarioLavorativoInizio);
        panelBox4.add(timeSpinner);

        panelBox5.add(labelOrarioLavorativoFine);
        panelBox5.add(timeSpinner2);

        panelFlow2.add(panelBox3);
        panelFlow2.add(panelBox4);
        panelFlow2.add(panelBox5);

        panelBox1.add(labelNome);
        panelBox1.add(fieldNome);
        panelBox2.add(labelCognome);
        panelBox2.add(fieldCognome);

        panelFlow5.add(panelBox1);
        panelFlow5.add(panelBox2);

        // Aggiunta dei pannelli al pannello principale
        mainPanel.add(panelFlowTitle, FlowLayout.LEFT);
        mainPanel.add(panelFlow5);
        mainPanel.add(panelFlow2);
        mainPanel.add(panelFlow1);
        mainPanel.add(panelFlow4);

        // Aggiunta del pannello principale al frame
        add(mainPanel);

    }

    public void clear(){
        fieldNome.setText("");
        fieldCognome.setText("");
    }
    public void setTitle(String title){
        this.title.setText(title);
    }

    public JButton getButtonSalva(){return buttonSalva;}


    public JComboBox getBoxTipoOperatore() {
        return boxTipoOperatore;
    }

    public void setBoxTipoOperatore(TipoOperatori tp) {
        this.boxTipoOperatore.setSelectedItem(tp);
    }

    public JComboBox getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(Status status) {
        this.boxStatus.setSelectedItem(status);
    }

    public JTextField getFieldNome() {
        return fieldNome;
    }

    public void setFieldNome(String text) {
        this.fieldNome.setText(text);
    }

    public JTextField getFieldCognome() {
        return fieldCognome;
    }

    public void setFieldCognome(String text) {
        this.fieldCognome.setText(text);
    }

    public JSpinner getTimeSpinner2() {
        return timeSpinner2;
    }


    public JSpinner getTimeSpinner() {
        return timeSpinner;
    }

    public void setTimeSpinner(String oraStringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date ora = null;
        try {
            ora = sdf.parse(oraStringa);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.timeSpinner.setValue(ora);
    }
    public void setTimeSpinner2(String oraStringa) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date ora = null;
        try {
            ora = sdf.parse(oraStringa);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.timeSpinner2.setValue(ora);
    }
}