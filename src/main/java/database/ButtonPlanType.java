package database;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.text.NumberFormat;

public class ButtonPlanType extends JToggleButton {

    private final PlanType data;

    public ButtonPlanType(PlanType data) {
        this.data = data;
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap 3", "[grow 0][fill][grow 0]", "[top]"));
        putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null;" +
                "selectedBackground:null;" +
                "pressedBackground:null;" +
                "borderWidth:1;");

        JRadioButton radioButton = new JRadioButton();

        radioButton.setModel(getModel());
        add(radioButton);

        // create text
        JPanel panel = new JPanel(new MigLayout("insets 0,nogrid"));
        JLabel lbName = new JLabel(data.getName());
        lbName.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        panel.add(lbName);
        JLabel lbRegion = new JLabel(data.getRegion());
        lbRegion.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:$TextField.background;" +
                "border:3,5,3,5,$Component.borderColor,1,15;");
        lbRegion.setIcon(new FlatSVGIcon("database/icon/globe.svg", 0.9f));
        panel.add(lbRegion, "wrap");

        panel.add(new JLabel(data.getDescription()), "wrap");

        // create size
        if (data.getSize() == null) {
            // unlimited size
            JLabel lbLimit = new JLabel("Unlimited");
            lbLimit.setIcon(new FlatSVGIcon("database/icon/infinity.svg", 0.9f));
            lbLimit.putClientProperty(FlatClientProperties.STYLE, "" +
                    "foreground:#22b65a");
            panel.add(lbLimit);
        } else {
            panel.add(new JLabel(data.getSize()));
        }
        JLabel lbSizeDescription = new JLabel(data.getSizeDescription());
        lbSizeDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground;");
        panel.add(lbSizeDescription);

        add(panel);

        // panel price
        JPanel panelPrice = new JPanel(new MigLayout("insets 0,wrap"));
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        JLabel lbPrice = new JLabel("from " + nf.format(data.getMonthPrice()));
        lbPrice.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Component.accentColor;" +
                "font:bold +2;");
        panelPrice.add(lbPrice);
        panelPrice.add(new JLabel("/ month"), "al trailing");

        add(panelPrice);
    }
}
