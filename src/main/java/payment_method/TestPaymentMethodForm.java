package payment_method;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.option.BorderOption;

import javax.swing.*;
import java.awt.*;

public class TestPaymentMethodForm extends JFrame {

    public TestPaymentMethodForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout("al center center"));

        // style modal border
        ModalDialog.getDefaultOption()
                .setOpacity(0f)
                .getBorderOption()
                .setShadow(BorderOption.Shadow.MEDIUM);

        JButton button = new JButton("Show");

        button.addActionListener(actionEvent -> {
            String icon = "payment_method/icon/wallet.svg";
            ModalDialog.showModal(this, new CustomSimpleModalBorder(new PaymentMethodForm(), "Payments", icon), PaymentMethodForm.ID);
        });
        add(button);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("payment_method.themes");
        FlatMacLightLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestPaymentMethodForm().setVisible(true));
    }
}
