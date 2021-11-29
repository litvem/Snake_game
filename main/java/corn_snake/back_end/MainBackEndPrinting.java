package corn_snake.back_end;

public class MainBackEndPrinting {
    public static void main(String[] args) {
        Field field = new Field();
        field.printField();
        System.out.println();
        field.moveSnake("l");
        field.moveSnake("l");
        field.moveSnake("u");

        field.printField();
    }
}
