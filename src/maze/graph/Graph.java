package maze.graph;

import maze.model.map.Map;
import maze.model.map.Tile;
import maze.model.map.TileType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jur0229
 */
public class Graph {

    private final int X = 0;
    private final int Y = 1;

    private final int directions[][] = new int[][] {
            new int[] {-1, 0}, // left
            new int[] {0, -1}, // top
            new int[] {1, 0}, // right
            new int[] {0, 1} // bottom
    };

    private Map map;

    private Node root;
    private Node end;

    private HashMap<int[], Node> hash;
    private HashMap<Node, Node> predecessors;


    public Graph(Map map) {

        this.map = map;
        hash = new HashMap<int[], Node>();

        buildNodes();
        buildNeighbors();
    }


    private void buildNodes() {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Tile t = map.getTile(x, y);

                if (t.getType() != TileType.WALL) {
                    Node n = new Node(t);
                    hash.put(new int[]{x, y}, n);

                    if (root == null && t == map.getStartTile()) {
                        root = n;
                    } else if (end == null && t == map.getEndTile()) {
                        end = n;
                    }
                }
            }
        }
    }


    private void buildNeighbors() {
        for (int position[]: hash.keySet()) {
            for (int[] dir: directions) {

                int pos[] = { position[X] + dir[X], position[Y] + dir[Y] };

                int k[] = containsKey(pos); // workaround for int[] reference inequality
                if (k != null) {
                    hash.get(position).addNeighbor(hash.get(k));
                }

            }
        }
    }


    private int[] containsKey(int key[]) {
        for (int k[]: hash.keySet()) {
            if (k[0] == key[0] && k[1] == key[1]) {
                return k;
            }
        }

        return null;
    }


    public boolean findShortestPath() {
        Queue<Node> depository = new LinkedList<Node>();
        HashMap<Node, Node> pre = new HashMap<Node, Node>();

        depository.add(root);

        while (!depository.isEmpty()) {
            Node n = depository.remove();

            if (n == this.end) {
                predecessors = pre;
                return true;
            }

            for (Node neighbor: n.getNeighbors()) {
                if (neighbor.getStatus().equals(Status.INITIAL)) {
                    pre.put(neighbor, n);
                    neighbor.setStatus(Status.FOUND);
                    depository.add(neighbor);
                }
            }

            n.setStatus(Status.PROCESSED);
        }

        predecessors = null;
        return false;
    }


    public Node findFurthermost(Node node) {
        for (int k[]: hash.keySet()) {
            hash.get(k).setStatus(Status.INITIAL);
        }

        Queue<Node> depository = new LinkedList<Node>();
        HashMap<Node, Node> pre = new HashMap<Node, Node>();
        HashMap<Node, Integer> depth = new HashMap<Node, Integer>();

        Node deepest = node;

        depth.put(node, 0);
        depository.add(node);

        while (!depository.isEmpty()) {
            Node n = depository.remove();

            if (depth.containsKey(n) && depth.get(n) > depth.get(deepest)) {
                deepest = n;
            }

            for (Node neighbor: n.getNeighbors()) {
                if (neighbor.getStatus().equals(Status.INITIAL)) {
                    pre.put(neighbor, n);
                    depth.put(neighbor, depth.get(n)+1);
                    neighbor.setStatus(Status.FOUND);
                    depository.add(neighbor);
                }
            }

            n.setStatus(Status.PROCESSED);
        }

        predecessors = pre;
        return deepest;
    }


    public void printToConsole() {
        if (predecessors == null) {
            return;
        }

        Node n = end;
        while (predecessors.containsKey(n)) {
            System.out.print("[" + n.getTile().getX() + "," + n.getTile().getY() + "] <- ");
            n = predecessors.get(n);
        }
        System.out.println("[" + root.getTile().getX() + "," + root.getTile().getY() + "]");
    }


    public HashMap<Node, Node> getPath() {
        return predecessors;
    }


    public Node getEnd() {
        return end;
    }

}
