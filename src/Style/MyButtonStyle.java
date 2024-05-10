package Style;

import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

public class MyButtonStyle extends JButton {

    public static final int RICERCA = 0;
    private int arco = 25; // il raggio dell'arco che determina il grado di arrotondamento dei bordi

    ImageIcon icona3 = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/iconExit.png")));
    Image imageExit = icona3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
    ImageIcon iconaExitRidimensionata = new ImageIcon(imageExit);

    ImageIcon icona = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/iconSerch.png")));
    Image imageSearch = icona.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon iconaCercaRidimensionata = new ImageIcon(imageSearch);

    public MyButtonStyle(String text, Color colore) {
        super(text);
        init(colore);

    }

    public MyButtonStyle(Icon icon) {
        super(icon);
        Dimension dimension = new Dimension(icon.getIconWidth()+30, icon.getIconWidth());
        setPreferredSize(dimension);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(0,0,0,0));
        setBorder(null);
        setOpaque(false);

        setContentAreaFilled(false);
    }

    public MyButtonStyle(int i){
        if(i == RICERCA){
            setIcon(iconaCercaRidimensionata);
            Dimension dimension = new Dimension(iconaCercaRidimensionata.getIconWidth()+10, iconaCercaRidimensionata.getIconWidth());
            setPreferredSize(dimension);
            setFocusable(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBackground(new Color(0,0,0,0));
            setBorder(null);
            setOpaque(false);

            setContentAreaFilled(false);
        }
    }

    public MyButtonStyle(String text, Color colore, Color foregroundColor) {
        super(text);
        init(colore);
        setForeground(foregroundColor);
    }

    public void init(Color colore){
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.white);
        setBackground(colore); //new Color(0x800000)
        Border lineBorder= new BorderUIResource.LineBorderUIResource(Color.black, 3);
        //setBorder(lineBorder);
        setBorder(null);
        setOpaque(false);

        setPreferredSize(new Dimension(90,40));
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int intColor= colore.getRGB() + 1000000;
                Color color= new Color(intColor);
                setBackground(color); // impostiamo il colore di sfondo più scuro quando si preme il pulsante
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(colore); // reimpostiamo il colore di sfondo originale quando si rilascia il pulsante
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground()); // imposta il colore
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arco, arco)); // disegna
        super.paintComponent(g);
    }

    public Dimension getDimension(){
        return new Dimension(90,40);
    }

    public void setMyForeground(Color color){
        setForeground(color);
    }
    public void setArco(int i){
        this.arco = i;
    }

}



