package corn_snake;

import corn_snake.facade.Facade;

public class Main {

    public static final Facade FACADE = new Facade();
    public static final String VERSION = "Alpha";

    public static void main(String[] args) {
        SnakeMain.main(args);
    }
}
