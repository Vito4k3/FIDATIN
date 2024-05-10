package Style;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {
    private final Dimension MINIMUM_THUMB_SIZE = new Dimension(30, 30);
    private final int TRACK_ARC_DIAMETER = 10;

    protected void configureScrollBarColors() {
        this.thumbColor = new Color(220, 220, 220);
        this.thumbDarkShadowColor = new Color(220, 220, 220);
        this.thumbHighlightColor = new Color(220, 220, 220);
        this.thumbLightShadowColor = new Color(220, 220, 220);
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Disegna la track con gli angoli arrotondati
        g2.setColor(Color.LIGHT_GRAY);
        int trackX = trackBounds.x + TRACK_ARC_DIAMETER / 2;
        int trackY = trackBounds.y + TRACK_ARC_DIAMETER / 2;
        int trackWidth = trackBounds.width - TRACK_ARC_DIAMETER;
        int trackHeight = trackBounds.height - TRACK_ARC_DIAMETER;
        g2.fillRoundRect(trackX, trackY, trackWidth, trackHeight, TRACK_ARC_DIAMETER, TRACK_ARC_DIAMETER);

        g2.dispose();
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Disegna la thumb con gli angoli arrotondati
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, TRACK_ARC_DIAMETER, TRACK_ARC_DIAMETER);

        g2.dispose();
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        // Imposta la dimensione minima della thumb
        return MINIMUM_THUMB_SIZE;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        // Sostituisce il pulsante di diminuzione con un pulsante vuoto
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        // Sostituisce il pulsante di aumento con un pulsante vuoto
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }
}
