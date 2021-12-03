package corn_snake.demo;

import corn_snake.back_end.GameOverException;

import java.util.Scanner;

class MainDemo {

    static final Facade FACADE = new Facade();

    public static void main(String[] args) {
        Input input = new Input();
        String currentCommand = "d";
        FACADE.printField();
        try {
            while (true) {
                String command = input.readLine("Enter a command: ");
                switch (command) {
                    case "u":
                        if (currentCommand.equals("d")) {
                            break;
                        }
                        currentCommand = "u";
                        break;
                    case "d":
                        if (currentCommand.equals("u")) {
                            break;
                        }
                        currentCommand = "d";
                        break;
                    case "l":
                        if (currentCommand.equals("r")) {
                            break;
                        }
                        currentCommand = "l";
                        break;
                    case "r":
                        if (currentCommand.equals("l")) {
                            break;
                        }
                        currentCommand = "r";
                        break;
                    default:
                        break;
                }

                FACADE.moveSnake(currentCommand);
                FACADE.printField();
            }
        } catch (GameOverException e) {
            System.out.println("Game Over");
        }
    }

    private static class Input {

        private final Scanner input;

        Input() {
            this.input = new Scanner(System.in);
        }

        String readLine(String prompt) {
            System.out.print(prompt);
            return input.nextLine();
        }

        void close() {
            input.close();
        }
    }
}
