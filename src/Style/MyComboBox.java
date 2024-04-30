package Style;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.Vector;

public class MyComboBox extends JComboBox {

    public MyComboBox(Object[] items){
        super(items);
        setBorder(new LineBorder(new Color(0x1A5690), 3));
        setFocusable(false);
        setBackground(Color.white);
        setUI(new BasicComboBoxUI());

    }

}
