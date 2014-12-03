
package maze;

import maze.control.Controller;
import maze.view.MazeView;

/**
 * @author jur0229
 */
public class Maze {

    public static void main(String[] args) {
        Controller controller = new Controller();
        MazeView view = new MazeView(controller);
        controller.start();
    }

}
