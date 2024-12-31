package model.board;

public class Hex {
    private final int id;
    private SystemType systemType;
    public Hex(int id, SystemType systemType) {
        this.id = id;
        this.systemType = systemType;
    }
    public int getId() {
        return id;
    }
    public SystemType getSystemType() {
        return systemType;
    }
    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }
    @Override
    public String toString() {
        return "Hex{" + "id=" + id + ", systemType=" + systemType + '}';
    }
}