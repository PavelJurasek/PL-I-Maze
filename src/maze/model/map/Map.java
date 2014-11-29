
package maze.model.map;

import java.awt.Point;

/**
 * @author jur0229
 */
public class Map {

    private final int width;
    private final int height;

    private final Tile map[][];

    private int helperCount = 0;
    private int emptyCount = 0;

    private Tile startTile;
    private Tile endTile;


    public Map(int width, int height) {
        this.width = width % 2 == 1 ? width : ++width;
        this.height = height % 2 == 1 ? height : ++height;
        map = new Tile[width][height];
    }

    private void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = initTile(x, y);
            }
        }
    }

    private Tile initTile(int x, int y) {
        Tile t = new Tile(x, y);

        if (x == 0 || y == 0 || x == width-1 || y == height-1) {
            t.setType(TileType.WALL);

        } else if (x % 2 == 0 && y % 2 == 0) {
            helperCount++;
            t.setType(TileType.HELPER);

        } else {
            emptyCount++;
            t.setType(TileType.EMPTY);

        }


        return t;
    }


    public Point findTileByType(TileType type, int offset) {
        int counter = 0;

        Point p = null;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (map[x][y].getType() == type) {
                    if (++counter == offset) {
                        p = new Point(x, y);
                        break;
                    }
                }
            }
        }

        return p;
    }


    public Point findRandomTileByType(TileType type) {
        int offset = 1;
        switch (type) {
            case HELPER:
                offset = helperCount > 1 ? random(helperCount - 1) + 1 : 1;
                break;
            case EMPTY:
                offset = random(emptyCount - 1) + 1;
                break;
        }

        return findTileByType(type, offset);
    }


    public void build() {
        clear();
        final double probability = 0.05;

        while (helperCount > 0) {
            boolean dissolve = random() < probability;

            Point p = findRandomTileByType(TileType.HELPER);

            if (p == null) {
                System.err.println("this shouldn't have happened");

            } else if (dissolve) {
                helperCount--;
                map[p.x][p.y] = new Tile(p.x, p.y, TileType.WALL);

            } else {
                int dx = 0, dy = 0;

                switch (random(4)) {
                    case 0:
                        dx = -1; dy = 0;
                        break;
                    case 1:
                        dx = 0; dy = -1;
                        break;
                    case 2:
                        dx = 1; dy = 0;
                        break;
                    case 3:
                        dx = 0; dy = 1;
                        break;
                }

                while (map[p.x][p.y].getType() != TileType.WALL) {
                    if (map[p.x][p.y].getType() == TileType.HELPER) {
                        helperCount--;
                    } else {
                        emptyCount--;
                    }

                    map[p.x][p.y] = new Tile(p.x, p.y, TileType.WALL);
                    p.x += dx;
                    p.y += dy;
                }
            }
        }
    }


    private double random() {
        return Math.random();
    }


    private int random(int max) {
        return ((int)(random() * 1000) % max);
    }


    public void printToConsole() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(" " + map[x][y].getType() + " ");
            }
            System.out.println("");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTile(int x, int y) {
        return map[x][y];
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(int x, int y) {
        map[x][y].setType(TileType.START);
        this.startTile = map[x][y];
    }

    public Tile getEndTile() {
        return endTile;
    }

    public void setEndTile(int x, int y) {
        map[x][y].setType(TileType.END);
        this.endTile = map[x][y];
    }

    public static void main(String[] args) {
        Map m = new Map(80, 60);
        m.build();
        m.printToConsole();
    }

}
