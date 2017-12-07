package console;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readNextLineFromConsole(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}
