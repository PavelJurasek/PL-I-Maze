
package maze.model.map;

/**
 * @author jur0229
 */
public enum TileType {
    EMPTY(" "),
    START("S"),
    END("E"),
    WALL("X"),
    HELPER("T");

    private String fieldDescription;

    private TileType(String desc) {
        this.fieldDescription = desc;
    }

    public String toString() {
        return this.fieldDescription;
    }

}
