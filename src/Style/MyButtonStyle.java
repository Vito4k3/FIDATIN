package Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class MyButtonStyle extends JButton {

    private static final long serialVersionUID = 356381274638960888L;
    private int arco = 25; // il raggio dell'arco che determina il grado di arrotondamento dei bordi



    public MyButtonStyle(String text, Color colore) {
        super(text);
        init(colore);

    }

    public MyButtonStyle(Icon icon) {
        super(icon);
        setPreferredSize(new Dimension(60,40));
        init(new Color(0,0,0,0));
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
        g2.setColor(getBackground()); // impostiamo il colore di sfondo del pulsante
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arco, arco)); // disegnamo il RoundRectangle2D
        super.paintComponent(g);
    }

    public Dimension getDimension(){
        return new Dimension(90,40);
    }

    public void setMyForeground(Color color){
        setForeground(color);
    }

}



