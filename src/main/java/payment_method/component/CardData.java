package payment_method.component;

public class CardData {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardData(String code, String info, String name, boolean remember, CardType cardType) {
        this.code = code;
        this.info = info;
        this.name = name;
        this.remember = remember;
        this.cardType = cardType;
    }

    private String code;
    private String info;
    private String name;
    private boolean remember;
    private CardType cardType;

    public String getLast4Number() {
        if (code.length() <= 4) {
            return code;
        }
        return code.substring(code.length() - 4);
    }
}
