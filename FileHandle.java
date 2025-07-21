import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileHandle {

    private static Scanner scanner = new Scanner(System.in);
    private static String filePath;

    public static void main(String[] args) {
        System.out.print("Enter the file path: ");
        filePath = scanner.nextLine();

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Append to file");
            System.out.println("4. Modify file content");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    writeToFile();
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    appendToFile();
                    break;
                case 4:
                    modifyFileContent();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void writeToFile() {
        System.out.print("Enter content to write: ");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("File content:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void appendToFile() {
        System.out.print("Enter content to append: ");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public static void modifyFileContent() {
        System.out.print("Enter word to replace: ");
        String oldWord = scanner.nextLine();
        System.out.print("Enter new word: ");
        String newWord = scanner.nextLine();

        try {
            Path path = Paths.get(filePath);
            String content = new String(Files.readAllBytes(path));
            content = content.replaceAll(oldWord, newWord);
            Files.write(path, content.getBytes());
            System.out.println("File content modified.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
