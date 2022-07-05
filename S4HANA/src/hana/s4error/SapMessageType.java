package hana.s4error;

public enum SapMessageType {
    INFORMATION("I"),
    SUCCESS("S"),
    WARN("W"),
    ABORT("A"),
    EXCEPTION("X"),
    ERROR("E");

    private String sapValue;

    SapMessageType(String sapValue) {
        this.sapValue = sapValue;
    }

    public String getSapValue() {
        return sapValue;
    }
}
