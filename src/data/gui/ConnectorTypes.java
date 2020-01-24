package data.gui;

public enum ConnectorTypes {
    IN("IN"),
    OUT("OUT"),
    OUT_TRUE("OUT_TRUE"),
    OUT_FALSE("OUT_FALSE");

    private String type;

    ConnectorTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType();
    }
}
