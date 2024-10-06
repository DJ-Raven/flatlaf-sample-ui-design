package database;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import net.miginfocom.swing.MigLayout;
import raven.modal.ModalDialog;
import raven.modal.component.SimpleModalBorder;

import javax.swing.*;
import java.awt.*;

public class TestDatabaseForm extends JFrame {

    public TestDatabaseForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setLayout(new MigLayout("al center center"));

        // style modal border
        ModalDialog.getDefaultOption()
                .setBorderWidth(0.5f);

        JButton button = new JButton("Show");

        button.addActionListener(actionEvent -> {
            ModalDialog.showModal(this, new SimpleModalBorder(new DatabaseForm(), "Create a new database", SimpleModalBorder.DEFAULT_OPTION, (controller, action) -> {
                if (action == SimpleModalBorder.OK_OPTION) {
                    System.out.println("OK");
                }
            }));
        });
        add(button);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("database.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestDatabaseForm().setVisible(true));
    }
}
