
package maze;

import maze.control.Controller;
import maze.view.MazeView;

/**
 * @author jur0229
 */
public class Maze {

    public static void main(String[] args) {
        System.out.println("Hello World");
        Controller controller = new Controller();
        MazeView view = new MazeView(controller);
        controller.start();
    }

}
