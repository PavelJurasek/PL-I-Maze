
package maze.model.map;

/**
 * @author jur0229
 */
public class Tile {

    private int x;
    private int y;
    TileType type;


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;

        this.type = type;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

}
