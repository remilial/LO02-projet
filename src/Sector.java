public class Sector {
    // Contient plusieurs hex, par exemple un tableau de Hex
    // Selon les règles, chaque carte a un arrangement spécifique.
    private Hex[] hexes;

    public Sector(Hex[] hexes) {
        this.hexes = hexes;
    }

    public Hex[] getHexes() {
        return hexes;
    }
}
