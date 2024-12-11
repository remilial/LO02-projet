public enum SystemType {
    NONE(0),
    LEVEL1(1),
    LEVEL2(2),
    LEVEL3(3), // Triprime
    private int level;
    SystemType(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
}


