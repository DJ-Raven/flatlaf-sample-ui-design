package payment_method;

import net.miginfocom.swing.MigLayout;
import payment_method.component.CardData;

import javax.swing.*;
import java.util.function.Consumer;

public class CreateCard extends JPanel {

    private final Consumer<CardData> onCreate;
    private final Consumer onDelete;

    public CreateCard(CardData data, Consumer<CardData> onCreate, Consumer onDelete) {
        this.onCreate = onCreate;
        this.onDelete = onDelete;
        setLayout(new MigLayout("insets 0 20 n 20,wrap,fillx,width 400", "[fill]"));
        
    }
}
