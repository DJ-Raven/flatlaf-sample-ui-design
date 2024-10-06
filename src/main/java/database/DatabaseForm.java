package database;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseForm extends JPanel {

    public DatabaseForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fillx,insets n 35 n 35", "[fill]"));

        add(createBoldText("Organization"), "cell 0 0");
        add(createBoldText("Database name"), " cell 2 0");

        JComboBox comboOrganization = new JComboBox();
        comboOrganization.addItem("TechNova");
        comboOrganization.addItem("GreenWave");
        comboOrganization.addItem("Skyline");
        comboOrganization.addItem("BlueOcean");
        comboOrganization.addItem("UrbanCore");

        add(comboOrganization, "grow 0,cell 0 1");

        add(new JLabel("/"), "cell 1 1");

        JTextField txtDatabase = new JTextField();
        txtDatabase.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "database-name");
        add(txtDatabase, "cell 2 1,width 400");

        JLabel lbDataDescription = new JLabel("Lowercase alphanumeric characters, dashes and underscores only");
        lbDataDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground");
        add(lbDataDescription, "cell 2 2,gapy n 10");

        JComboBox comboRegion = new JComboBox();
        comboRegion.addItem("us-east-1 – US East (N. Virginia)");
        comboRegion.addItem("eu-west-1 – Europe (Ireland)");
        comboRegion.addItem("ap-southeast-1 – Asia Pacific (Singapore)");
        comboRegion.addItem("sa-east-1 – South America (São Paulo)");
        comboRegion.addItem("af-south-1 – Africa (Cape Town)");

        comboRegion.setRenderer(new CustomComboRenderer(comboRegion));

        add(comboRegion, "span 3");

        JLabel lbRegionDescription = new JLabel("For best performance, choose a region closest to your application");
        lbRegionDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:$Label.disabledForeground");
        add(lbRegionDescription, "span 3,gapy n 10");

        add(createBoldText("Plan type"));

        // create plan type
        ButtonGroup group = new ButtonGroup();
        List<PlanType> list = getListPlanType();
        for (int i = 0; i < list.size(); i++) {
            PlanType planType = list.get(i);
            ButtonPlanType buttonPlanType = new ButtonPlanType(planType);
            group.add(buttonPlanType);
            if (i == list.size() - 1) {
                add(buttonPlanType, "span 3,gapy 5 10");
            } else {
                add(buttonPlanType, "span 3, gapy 5");
            }
        }

        // create action button
        JButton cmdCancel = new JButton("Cancel");
        JButton cmdNext = new JButton("Next step", new FlatSVGIcon("database/icon/next.svg", 0.9f)) {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };

        cmdNext.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#F0F0F0;");
        cmdNext.setHorizontalTextPosition(SwingConstants.LEADING);
        cmdCancel.addActionListener(actionEvent -> {
            ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.CANCEL_OPTION);
        });

        cmdNext.addActionListener(actionEvent -> {
            ModalBorderAction.getModalBorderAction(this).doAction(SimpleModalBorder.OK_OPTION);
        });

        add(cmdCancel, "span 3,split 2, grow 0");
        add(cmdNext, "grow 0, al trailing");
    }

    private List<PlanType> getListPlanType() {
        List<PlanType> list = new ArrayList<>();
        list.add(new PlanType("Business", "AWS ap-east-1", "Choose the compute and memory resources for your needs", null, "row reads and writes", 48));
        list.add(new PlanType("Pro", "AWS ap-east-1", "Read/write-based billing for lower traffic applications", "100 billion", "row reads and 50 million row writes / month", 28));
        list.add(new PlanType("Free", "AWS ap-east-1", "Read/write-based billing for lower traffic applications", "1 billion", "row reads and 10 million row writes / month", 0));
        return list;
    }

    private JLabel createBoldText(String text) {
        JLabel label = new JLabel(text);
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold;");
        return label;
    }

    private static class CustomComboRenderer extends DefaultListCellRenderer {

        private final JComboBox combo;
        private final JPanel panel;

        public CustomComboRenderer(JComboBox combo) {
            this.combo = combo;
            panel = new JPanel(new MigLayout("insets 0 1 0 1,fill", "[grow 0][grow 0][fill]", "[fill]"));
            JLabel lbRegion = new JLabel("Region");
            lbRegion.putClientProperty(FlatClientProperties.STYLE, "" +
                    "foreground:$Label.disabledForeground;");
            panel.add(lbRegion);
            panel.add(new JSeparator(JSeparator.VERTICAL));
        }

        @Override
        public Component getListCellRendererComponent(JList<?> jList, Object o, int i, boolean b, boolean b1) {
            Component com = super.getListCellRendererComponent(jList, o, i, b, b1);
            if (i == -1) {
                com.setBackground(combo.getBackground());
                if (panel.getComponentCount() > 2) {
                    panel.remove(2);
                }
                panel.add(com);
                return panel;
            } else {
                return com;
            }
        }
    }
}
