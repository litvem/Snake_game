package corn_snake;

import corn_snake.demo.FXDemo;
import corn_snake.facade.Facade;

public class Main {

    public static final Facade FACADE = new Facade();

    public static void main(String[] args) {
        FXDemo.main(args);
    }
}