package maze.control;

import maze.graph.Graph;
import maze.graph.Node;
import maze.model.map.Map;
import maze.view.MazeView;

import java.awt.*;
import java.util.HashMap;

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

        Graph g = new Graph(map);
        if (g.findPath()) {
            System.out.println("End found!");
            g.printToConsole();

            HashMap<Node, Node> path = g.getPath();
            Node n = path.get(g.getEnd());
            while (path.containsKey(n)) {
                view.colorPath(n.getTile().getX(), n.getTile().getY());
                n = path.get(n);
            }
        } else {
            System.out.println("End not reachable!");
        }

        view.repaint();
    }

}
