package payment_method.component;

public enum CardType {
    VISA("Visa", "visacard.svg"), MASTER("Mastercard", "mastercard.svg"), OTHER("Other", "othercard.svg");

    String name;
    String icon;

    private CardType(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getIcon() {
        return "payment_method/icon/" + icon;
    }
}
