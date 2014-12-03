
package maze.graph;

import maze.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jur0229
 */
public class Node {

    private Tile tile;

    private Status status;

    private List<Node> neighbors;


    public Node(Tile tile) {
        this.tile = tile;
        neighbors = new ArrayList<Node>();
        status = Status.INITIAL;
    }


    public Tile getTile() {
        return tile;
    }


    public List<Node> getNeighbors() {
        return neighbors;
    }


    public void addNeighbor(Node neighbor) {
        if (!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
            neighbor.addNeighbor(this);
        }
    }


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

}
