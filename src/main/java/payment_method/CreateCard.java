package payment_method;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import payment_method.component.CardData;
import payment_method.component.CardType;
import raven.modal.ModalDialog;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.Objects;
import java.util.function.Consumer;

public class CreateCard extends JPanel {

    private final Consumer<CardData> onCreate;
    private final Consumer onDelete;

    public CreateCard(CardData data, Consumer<CardData> onCreate, Consumer onDelete) {
        this.onCreate = onCreate;
        this.onDelete = onDelete;
        setLayout(new MigLayout("insets 0 20 n 20,wrap,fillx,width 400", "[fill]"));
        JTextArea text = new JTextArea("Enter your bank card details");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "foreground:$Label.disabledForeground;" +
                "font:+2;");
        add(text, "grow 0,al center,gapy 0 15");

        JLabel lbCard = new JLabel("Card");
        lbCard.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbCard, "split 2");

        JLabel lbInfo = new JLabel("MM/YY CVC");
        lbInfo.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;" +
                "font:-1;" +
                "border:0,0,0,10;");
        add(lbInfo, "al trailing, grow 0");

        JFormattedTextField txtFormat = createFormatText();
        JTextField txtCode = new JTextField() {
            @Override
            public boolean hasFocus() {
                return super.hasFocus() || txtFormat.hasFocus();
            }
        };
        txtCode.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Card number");
        txtFormat.setHorizontalAlignment(SwingConstants.TRAILING);
        txtFormat.setColumns(7);
        txtFormat.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                txtCode.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtCode.repaint();
            }
        });
        txtFormat.putClientProperty(FlatClientProperties.STYLE, "" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;");


        txtCode.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, txtFormat);
        add(txtCode);

        JLabel lbSecure = new JLabel("Secure 256-bit SSL");
        lbSecure.setIcon(new FlatSVGIcon("payment_method/icon/secure.svg", 0.35f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> lbSecure.getForeground())));
        lbSecure.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;");
        add(lbSecure);

        JLabel lbCardName = new JLabel("Cardholder name");

        lbCardName.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        add(lbCardName, "gapy 10 n");

        JTextField txtName = new JTextField();
        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter name on card");
        add(txtName);

        JCheckBox chRemember = new JCheckBox("Save card information");
        add(chRemember, "gapy 10 10,grow 0");

        JButton cmdAdd = new JButton(data == null ? "Add card" : "Edit card") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdAdd.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;");

        add(cmdAdd);

        cmdAdd.addActionListener(e -> {
            try {
                txtFormat.commitEdit();
            } catch (Exception ex) {
            }
            CardType cardType = data == null ? CardType.OTHER : data.getCardType();
            CardData cardData = new CardData(txtCode.getText().trim(), Objects.toString(txtFormat.getValue(), ""), txtName.getText().trim(), chRemember.isSelected(), cardType);
            onCreate.accept(cardData);
            ModalDialog.popModel(PaymentMethodForm.ID);
        });

        if (data != null) {
            txtCode.setText(data.getCode());
            txtFormat.setValue(data.getInfo());
            txtName.setText(data.getName());
            chRemember.setSelected(data.isRemember());
            add(createRemoveButton());
        }
    }

    private Component createRemoveButton() {
        JButton cmdRemove = new JButton("Remove card");

        cmdRemove.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;" +
                "foreground:#ef4444;");

        cmdRemove.addActionListener(e -> {
            onDelete.accept(null);
            ModalDialog.popModel(PaymentMethodForm.ID);
        });
        return cmdRemove;
    }

    private JFormattedTextField createFormatText() {
        MaskFormatter maskFormatter = null;
        try {
            maskFormatter = new MaskFormatter("##/## ###");
            maskFormatter.setPlaceholderCharacter('-');

        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField txt = new JFormattedTextField(maskFormatter);
        return txt;
    }
}