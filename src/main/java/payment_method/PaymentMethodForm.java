package payment_method;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import payment_method.component.CardButton;
import payment_method.component.CardData;
import payment_method.component.CardType;
import raven.modal.ModalDialog;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PaymentMethodForm extends JPanel {

    public static final String ID = "payment_method_id";

    private JScrollPane scrollPane;
    private JPanel panelCard;

    public PaymentMethodForm() {
        setLayout(new MigLayout("insets 0 20 n 20,wrap,fillx,width 400", "[fill]"));
        JTextArea text = new JTextArea("Effortlessly manage your payments");
        text.setEditable(false);
        text.setFocusable(false);
        text.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0;" +
                "background:null;" +
                "foreground:$Label.disabledForeground;" +
                "font:+2;");
        add(text, "grow 0,al center,gapy 0 15");

        add(createPanelCard());
    }

    private JPanel createPanelCard() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 5 0 n 0,hidemode 3", "[fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:1,1,1,1,$Component.borderColor,,15;" +
                "[light]background:shade($Panel.background,2%);" +
                "[dark]background:tint($Panel.background,2%);");

        panelCard = new JPanel(new MigLayout("wrap,fillx,insets 0,gapy 0", "[fill]"));
        panelCard.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:shade($Panel.background,2%);" +
                "[dark]background:tint($Panel.background,2%);");
        scrollPane = new JScrollPane(panelCard);
        JScrollBar sv = scrollPane.getVerticalScrollBar();
        sv.putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:$ScrollBar.thumbArc;");
        sv.setUnitIncrement(5);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        scrollPane.setVisible(false);

        panel.add(createNewButton(), "gapy 5 5,grow 0,al center");

        // create sample card item
        addCard(new CardData("8965632458777", "10/24 222", "Dara", false, CardType.VISA), getLastIndex());
        addCard(new CardData("8965632458777", "10/24 222", "Dara", false, CardType.MASTER), getLastIndex());
        addCard(new CardData("8965632458777", "10/24 222", "Dara", false, CardType.OTHER), getLastIndex());

        return panel;
    }

    private void addCard(CardData data, int index) {
        scrollPane.setVisible(true);
        final CardButton cardButton = new CardButton(data);
        cardButton.setOnEdit(cardData -> {
            // do edit card
            createCard(cardData, panelCard.getComponentZOrder(cardButton));
        });
        panelCard.add(cardButton, index);
        panelCard.repaint();
        panelCard.revalidate();
    }

    private int getLastIndex() {
        return panelCard.getComponentCount();
    }

    private JButton createNewButton() {
        JButton button = new JButton("+ Add payment method");
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;" +
                "foreground:$Component.accentColor;" +
                "font:bold+1;" +
                "margin:2,10,2,10;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;");

        button.addActionListener(e -> {
            // add new card
            createCard(null, -1);
        });
        return button;
    }

    private void createCard(CardData data, int index) {
        Consumer<CardData> onCreate = cardData -> {
            if (index == -1) {
                // add new
                addCard(cardData, getLastIndex());
            } else {
                // edit
                panelCard.remove(index);
                addCard(cardData, index);
            }
        };

        Consumer onDelete = object -> {
            panelCard.remove(index);
            panelCard.repaint();
            panelCard.revalidate();
            scrollPane.setVisible(panelCard.getComponentCount() != 0);
        };

        String icon = "payment_method/icon/wallet.svg";
        String title = data == null ? "Add card" : "Edit card";
        ModalDialog.pushModal(new CustomSimpleModalBorder(new CreateCard(data, onCreate, onDelete), title, icon), PaymentMethodForm.ID);
    }
}
