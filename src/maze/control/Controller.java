package maze.control;

import maze.graph.Graph;
import maze.graph.Node;
import maze.model.map.Map;
import maze.view.MazeView;

import java.util.HashMap;

/**
 * @author jur0229
 */
public class Controller {

    private MazeView view;

    private Map map;
    private Graph graph;

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

        graph = new Graph(map);

        longestPath();
//        shortestPath();

        view.repaint();
    }

    private void longestPath() {
        Node first = graph.findFurthermost(graph.getEnd());
        System.out.println("First: "+ first.getTile().getX() +","+ first.getTile().getY());

        Node last = graph.findFurthermost(first);
        System.out.println("Last: " + last.getTile().getX() + "," + last.getTile().getY());

//        System.out.println("Test: "+ (graph.findFurthermost(last) == first));

        map.setStartTile(first.getTile().getX(), first.getTile().getY());
        map.setEndTile(last.getTile().getX(), last.getTile().getY());

        view.setMap(map);

        HashMap<Node, Node> path = graph.getPath();
        Node n = path.get(last);

        while (path.containsKey(n)) {
            view.colorPath(n.getTile().getX(), n.getTile().getY());
            n = path.get(n);
        }
    }


    private void shortestPath() {
        view.setMap(map);

        if (graph.findShortestPath()) {
            System.out.println("End found!");
            graph.printToConsole();

            HashMap<Node, Node> path = graph.getPath();
            Node n = path.get(graph.getEnd());
            while (path.containsKey(n)) {
                view.colorPath(n.getTile().getX(), n.getTile().getY());
                n = path.get(n);
            }
        } else {
            System.out.println("End not reachable!");
        }
    }

}
