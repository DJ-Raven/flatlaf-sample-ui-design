package login_register.component;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;

public class ButtonLink extends JButton {

    public ButtonLink(String text) {
        super(text);

        putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:15;" +
                "margin:1,5,1,5;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "foreground:$Component.accentColor;" +
                "background:null;");
    }
}
