package Style;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class MyScrollPane extends JScrollPane {
    int scrollbarWidth = 10, scrollbarHeight = 20;
    public MyScrollPane(Component view) {
        super(view);

        getViewport().setBackground(Color.WHITE);
        setBorder(new EmptyBorder(5, 10, 10, 10));
        setAutoscrolls(true);

        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        verticalScrollBar.setPreferredSize(new Dimension(scrollbarWidth, scrollbarHeight));
        horizontalScrollBar.setPreferredSize(new Dimension(scrollbarWidth, scrollbarHeight));

        getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        getVerticalScrollBar().setUI(new CustomScrollBarUI());

    }
}
