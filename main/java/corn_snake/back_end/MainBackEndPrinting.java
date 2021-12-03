package corn_snake.back_end;

public class MainBackEndPrinting {
    public static void main(String[] args) {
        Field field = new Field();
        field.printField();
        System.out.println();

        try {
            field.moveSnake("l");
            field.moveSnake("l");
            field.moveSnake("u");
            field.moveSnake("u");


        } catch (GameOverException exception){
            System.out.println(exception.getMessage());
        }

        field.printField();
    }
}
