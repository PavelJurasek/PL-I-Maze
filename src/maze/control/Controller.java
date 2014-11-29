package maze.control;

import maze.model.map.Map;
import maze.view.MazeView;

/**
 * @author jur0229
 */
public class Controller {

    private MazeView view;

    private Map map;

    public Controller() {
        map = new Map(20, 15);
    }

    public void setView(MazeView maze) {
        view = maze;
    }


    public void start() {
        map.build();
        map.printToConsole();

        map.setStartTile(1, 1);
        map.setEndTile(map.getWidth()-2, map.getHeight()-2);

        view.setMap(map);
        view.repaint();
    }

}
