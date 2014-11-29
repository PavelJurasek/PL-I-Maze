
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
    }

    @Override
    public void paint(Graphics g) {
        final int width = getWidth() / map.getWidth();
        final int height = getHeight() / map.getHeight();

        g.setColor(Color.BLACK);


        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                TileType type = map.getTile(x, y).getType();
                switch (type) {
                    case WALL:
                        g.fillRect(x * width, y * height, width, height);

                        break;
                    case START:
                        g.setColor(Color.BLUE);
                        g.fillRect(x * width, y * height, width, height);
                        g.setColor(Color.BLACK);

                        break;
                    case END:
                        g.setColor(Color.RED);
                        g.fillRect(x * width, y * height, width, height);
                        g.setColor(Color.BLACK);

                        break;
                }
            }
        }
    }

}
