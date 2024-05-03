package Style;

import javax.swing.*;
import java.awt.*;

public class MyLabelStyle extends JLabel {
    public MyLabelStyle(String text){
        super(text);
        setFont(new Font("myLabelStyle", Font.TRUETYPE_FONT, 15));
    }
}
