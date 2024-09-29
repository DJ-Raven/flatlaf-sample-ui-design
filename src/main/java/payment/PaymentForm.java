package payment;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;

import javax.swing.*;

public class PaymentForm extends JPanel {

    public PaymentForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap 2,fillx,insets n 35 n 35", "[fill,200]"));

        JLabel lbContactDetail = new JLabel("Contact Details");
        lbContactDetail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2;");
        add(lbContactDetail, "gapy 10 10,span 2");

        add(new JLabel("Payment option type"), "span 2");
        JComboBox comboPaymentType = new JComboBox();
        comboPaymentType.addItem("Bank Transfer");
        comboPaymentType.addItem("Cash");
        comboPaymentType.addItem("Mobile Payment");
        comboPaymentType.addItem("Online Payment Gateways");
        comboPaymentType.addItem("Credit/Debit Card");

        add(comboPaymentType, "gapy n 5,span 2");

        add(new JLabel("Name"));
        add(new JLabel("Email"));

        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField();
        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Name");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "example@mail.com");
        add(txtName);
        add(txtEmail);

        JLabel lbRequestDetail = new JLabel("Request Details");
        lbRequestDetail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2;");
        add(lbRequestDetail, "gapy 10 10,span 2");

        add(new JLabel("Amount"));
        add(new JLabel("Due Date"));

        JTextField txtAmount = new JTextField();
        JFormattedTextField dateEditor = new JFormattedTextField();
        DatePicker datePicker = new DatePicker();
        datePicker.setEditor(dateEditor);

        txtAmount.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0.00");
        JLabel lbDollar = new JLabel("$");
        lbDollar.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,8,0,0;");
        txtAmount.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_COMPONENT, lbDollar);
        add(txtAmount);
        add(dateEditor);

        add(new JLabel("Company name to show on request"), "gapy 5,span 2");
        JComboBox comboCompany = new JComboBox();
        comboCompany.addItem("TechNova Solutions");
        comboCompany.addItem("GreenWave Enterprises");
        comboCompany.addItem("Skyline Innovations");
        comboCompany.addItem("BlueOcean Ventures");
        comboCompany.addItem("UrbanCore Technologies");

        add(comboCompany, "Span 2");

        add(new JLabel("Destination Account"), "gapy 5,span 2");
        JComboBox comboAccount = new JComboBox();
        comboAccount.addItem("Skyline_Tech2024");
        comboAccount.addItem("GreenEnergyPro");
        comboAccount.addItem("Oceanic_Trader");
        comboAccount.addItem("UrbanCore_Admin");
        comboAccount.addItem("NovaBiz_Account");

        add(comboAccount, "Span 2");

        JTextArea textArea = new JTextArea();
        textArea.setEnabled(false);
        textArea.setText("Incoming payment are placed in a secure receiving account to keep\ndestination account details anonymous.");
        textArea.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "font:-1;" +
                "background:null;");
        add(textArea, "gapy 5 5,span 2");

        JLabel lbAlerts = new JLabel("Payment link expires on December 11,2024");
        lbAlerts.setIcon(new FlatSVGIcon("payment/icon/clock.svg"));
        lbAlerts.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:8,8,8,8;" +
                "arc:$Component.arc;" +
                "background:fade(#1aad2c,10%);");
        add(lbAlerts, "gapy n 10,span 2");

        // action button

        JButton cmdCancel = new JButton("Cancel");
        JButton cmdPayment = new JButton("Request Payment") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdCancel.addActionListener(actionEvent -> {
            ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.CANCEL_OPTION);
        });

        cmdPayment.addActionListener(actionEvent -> {
            ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
        });

        add(cmdCancel, "grow 0");
        add(cmdPayment, "grow 0, al trailing");
    }
}
