package payment_method;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.modal.component.SimpleModalBorder;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class CustomSimpleModalBorder extends SimpleModalBorder {

    private MigLayout layout;
    private String icon;
    private String title;

    public CustomSimpleModalBorder(Component component, String title, String icon) {
        super(component, title);
        this.title = title;
        this.icon = icon;
    }

    @Override
    protected JComponent createHeader() {
        layout = new MigLayout("insets n n 0 n,fillx,wrap", "[center]");
        JPanel panel = new JPanel(layout);
        JLabel label = (JLabel) super.createTitleComponent(title);
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +8");
        panel.add(createIcon());
        panel.add(label);

        // for close button
        panel.add(createActionTitleComponent(), "pos visual.w-pref visual.y");
        return panel;
    }

    @Override
    protected void applyBackButton(Consumer onBack) {
        super.applyBackButton(onBack);
        layout.setComponentConstraints(header.getComponent(0), "pos visual.x+8 visual.y");
    }

    protected JLabel createIcon() {
        FlatSVGIcon svgIcon = new FlatSVGIcon(icon, 0.5f).setColorFilter(new FlatSVGIcon.ColorFilter(color -> UIManager.getColor("Component.accentColor")));
        JLabel label = new JLabel(svgIcon);
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:18,18,18,18,fade($Component.accentColor,50%),,999;" +
                "background:fade($Component.accentColor,10%);");
        return label;
    }
}
