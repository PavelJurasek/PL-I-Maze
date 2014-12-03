
package maze.view;

import maze.control.Controller;
import maze.model.map.Map;
import maze.model.map.TileType;

import javax.swing.*;
import java.awt.*;

/**
 * @author jur0229
 */
public class MazeView extends JFrame {

    private Controller controller;

    private Map map;

    private Color grid[][];
    private int width;
    private int height;


    public MazeView(Controller controller) {
        this.controller = controller;
        controller.setView(this);

        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    public void setMap(Map map) {
        this.map = map;
        width = getWidth() / map.getWidth();
        height = getHeight() / map.getHeight();

        grid = new Color[map.getWidth()][map.getHeight()];

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                setBlockColor(x, y, getColorType(x, y));
            }
        }

    }


    private Color getColorType(int x, int y) {
        TileType type = map.getTile(x, y).getType();
        switch (type) {
            case START:
                return Color.BLUE;
            case END:
                return Color.RED;
            case WALL:
                return Color.BLACK;
            case EMPTY:
            default:
                return new Color(0,0,0,0); // transparent
        }
    }


    public void colorPath(int x, int y) {
        setBlockColor(x, y, Color.GRAY);
    }


    private void setBlockColor(int x, int y, Color col) {
        grid[x][y] = col;
    }


    @Override
    public void paint(Graphics g) {
        if (map == null) {
            return;
        }

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                g.setColor(grid[x][y]);
                g.fillRect(x * width, y * height, width, height);
            }
        }
    }

}
