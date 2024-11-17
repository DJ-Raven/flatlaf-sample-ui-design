package demo;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import database.TestDatabaseForm;
import login.TestLoginForm;
import login_register.TestLoginRegisterForm;
import net.miginfocom.swing.MigLayout;
import payment.TestPaymentForm;
import payment_method.TestPaymentMethodForm;
import raven.extras.LightDarkButton;

import javax.swing.*;
import java.awt.*;

public class AllDemo extends JFrame {

    public AllDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setLayout(new MigLayout("al center center,wrap", "[fill]"));

        JButton cmdDatabase = new JButton("Database demo");
        JButton cmdLogin = new JButton("Login demo");
        JButton cmdLoginRegister = new JButton("Login and Register demo");
        JButton cmdPayment = new JButton("Payment demo");
        JButton cmdPaymentMethod = new JButton("Payment Method demo");

        cmdDatabase.addActionListener(e -> showFrame(new TestDatabaseForm()));
        cmdLogin.addActionListener(e -> showFrame(new TestLoginForm()));
        cmdLoginRegister.addActionListener(e -> showFrame(new TestLoginRegisterForm()));
        cmdPayment.addActionListener(e -> showFrame(new TestPaymentForm()));
        cmdPaymentMethod.addActionListener(e -> showFrame(new TestPaymentMethodForm()));

        add(cmdDatabase);
        add(cmdLogin);
        add(cmdLoginRegister);
        add(cmdPayment);
        add(cmdPaymentMethod);

        add(new JSeparator());

        LightDarkButton lightDarkButton = new LightDarkButton();
        lightDarkButton.installAutoLafChangeListener();
        add(lightDarkButton);
    }

    private void showFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("demo.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new AllDemo().setVisible(true));
    }
}
