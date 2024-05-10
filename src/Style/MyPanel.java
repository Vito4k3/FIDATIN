package Style;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyPanel extends JPanel {
    public MyPanel(LayoutManager layout) {
        super(layout);
    }
    int arco = 20;
    int width = -1;
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() -width, getHeight() - 1, arco, arco);
        g2.clip(shape);
        super.paintComponent(g2);
        g2.dispose();
    }

    public void setArco(int i){
        arco = i;
    }

    public void setWidth(int i){
        width = i;
    }
}
