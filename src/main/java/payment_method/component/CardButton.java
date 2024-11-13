package payment_method.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class CardButton extends JButton {

    public void setOnEdit(Consumer<CardData> onEdit) {
        this.onEdit = onEdit;
    }

    private Consumer<CardData> onEdit;

    public CardButton(CardData data) {
        setBorder(BorderFactory.createEmptyBorder());
        putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;");
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLayout(new MigLayout("insets n 0 0 0,fillx,gapy 3", "[grow 0]0[fill]0[grow 0]"));
        add(new JLabel(new FlatSVGIcon(data.getCardType().getIcon())), "cell 0 0,span 1 2,width 50!");
        JLabel lbName = new JLabel(data.getCardType().toString());
        lbName.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:+2;");
        add(lbName, "cell 1 1");

        JButton cmdEdit = new JButton("Edit");
        cmdEdit.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:999;" +
                "font:bold -1;" +
                "margin:5,10,5,10;" +
                "background:null;");
        add(cmdEdit, "cell 2 0,span 1 2,gapx n 10");

        add(new JSeparator(), "grow 1,h 2,cell 0 2,span 3,gapy 3");

        cmdEdit.addActionListener(e -> {
            // do edit
            onEdit.accept(data);
        });
    }
}
