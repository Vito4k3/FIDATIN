package Style;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyTextArea extends JTextArea {
    public MyTextArea(){
        init();
    }

    public void init(){
        setBackground(Color.white);

        Border emptyBorder = new EmptyBorder(5,5,5,5);
        setBorder(emptyBorder);

    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.clip(shape);
        super.paintComponent(g2);
        g2.dispose();
    }
}
