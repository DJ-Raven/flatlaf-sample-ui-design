package login_register;

import com.formdev.flatlaf.FlatClientProperties;
import login_register.component.ButtonLink;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;

import javax.swing.*;

public class ForgotPassword extends JPanel {

    public ForgotPassword() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Please enter the email address you used to create\nyour account, and we'll send you a link to reset\nyour password.");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;");
        add(text);

        add(new JSeparator(), "gapy 15 15");

        JLabel lbEmail = new JLabel("Email");
        lbEmail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbEmail);

        JTextField txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your email");
        add(txtEmail);

        JButton cmdSubmit = new JButton("Submit") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdSubmit.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");

        add(cmdSubmit, "gapy 15 15");

        ButtonLink cmdBackLogin = new ButtonLink("Back to login");
        add(cmdBackLogin, "grow 0,al center");

        // event
        cmdBackLogin.addActionListener(actionEvent -> {
            ModalDialog.popModel(Login.ID);
        });
    }
}
