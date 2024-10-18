package login_register;

import com.formdev.flatlaf.FlatClientProperties;
import login_register.component.ButtonLink;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;

import javax.swing.*;

public class SignUp extends JPanel {

    public SignUp() {
        setLayout(new MigLayout("insets n 20 n 20,fillx,wrap,width 380", "[fill]"));

        JTextArea text = new JTextArea("Become a member you'll enjoy exclusive deals,\noffers, invites and rewards.");
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
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "example@mail.com");
        add(txtEmail);

        JLabel lbPassword = new JLabel("Create a password");
        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbPassword, "gapy 10 n");

        JTextField txtPassword = new JTextField();
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "at least 8 characters");
        add(txtPassword);

        JLabel lbDateOfBirth = new JLabel("Date of birth");
        lbDateOfBirth.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbDateOfBirth, "gapy 10 n");

        JTextField txtDateOfBirth = new JTextField();
        txtDateOfBirth.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MM / DD / YYYY");
        add(txtDateOfBirth);

        JLabel lbNote = new JLabel("We want to give you a special treat on your birthday");
        lbNote.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:-1;" +
                "foreground:$Label.disabledForeground;");
        add(lbNote);

        add(new JCheckBox("Subscribe to newsletter"), "gapy 10 10");

        JButton cmdSignUp = new JButton("Sign up") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdSignUp.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");
        add(cmdSignUp);

        add(new JSeparator(), "gapy 15 15");

        add(new JLabel("Already have an account ?"), "split 2, gapx push n");

        ButtonLink cmdBackLogin = new ButtonLink("Login");
        add(cmdBackLogin, "gapx n push");

        // event
        cmdBackLogin.addActionListener(actionEvent -> {
            ModalDialog.popModel(Login.ID);
        });
    }
}
