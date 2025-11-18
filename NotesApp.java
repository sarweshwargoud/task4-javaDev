import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotesApp {
    private static final String NOTES_FILE = "notes.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Choose: ");
            switch (choice) {
                case 1 -> addNote();
                case 2 -> viewNotes();
                case 3 -> deleteNote();
                case 4 -> overwriteNotes();
                case 5 -> searchNotes();
                case 6 -> {
                    System.out.println("Exiting. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== NOTES APP ===");
        System.out.println("1. Add a note (append)");
        System.out.println("2. View all notes");
        System.out.println("3. Delete a note (by number)");
        System.out.println("4. Overwrite all notes");
        System.out.println("5. Search notes");
        System.out.println("6. Exit");
    }

    // Utility read-int with validation
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    // Add note (append mode)
    private static void addNote() {
        String note = readLine("Enter note text: ");
        if (note.isEmpty()) {
            System.out.println("Note cannot be empty.");
            return;
        }
        // try-with-resources ensures FileWriter closed
        try (FileWriter fw = new FileWriter(NOTES_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(note);
            System.out.println("Note added.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    // Read all notes (returns list)
    private static List<String> readAllNotes() {
        List<String> notes = new ArrayList<>();
        File file = new File(NOTES_FILE);
        if (!file.exists()) return notes;
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) notes.add(line);
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
        return notes;
    }

    // View notes with numbers
    private static void viewNotes() {
        List<String> notes = readAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }
        System.out.println("\n--- Your Notes ---");
        for (int i = 0; i < notes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, notes.get(i));
        }
    }

    // Delete a single note by index (rewrite file)
    private static void deleteNote() {
        List<String> notes = readAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes to delete.");
            return;
        }
        viewNotes();
        int idx = readInt("Enter note number to delete: ");
        if (idx < 1 || idx > notes.size()) {
            System.out.println("Invalid note number.");
            return;
        }
        notes.remove(idx - 1);
        // overwrite file with remaining notes
        if (writeAllNotes(notes)) {
            System.out.println("Note deleted.");
        } else {
            System.out.println("Failed to delete note.");
        }
    }

    // Overwrite entire notes file (replace existing content)
    private static void overwriteNotes() {
        System.out.println("You are going to overwrite all notes. Type your notes line by line.");
        System.out.println("Enter a blank line to finish.");
        List<String> newNotes = new ArrayList<>();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            newNotes.add(line);
        }
        if (writeAllNotes(newNotes)) {
            System.out.println("Notes overwritten.");
        } else {
            System.out.println("Failed to overwrite notes.");
        }
    }

    // Write entire list to file (overwrite)
    private static boolean writeAllNotes(List<String> notes) {
        try (FileWriter fw = new FileWriter(NOTES_FILE, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (String n : notes) out.println(n);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing notes: " + e.getMessage());
            return false;
        }
    }

    // Search notes (simple substring match)
    private static void searchNotes() {
        String q = readLine("Enter search query: ").toLowerCase();
        if (q.isEmpty()) {
            System.out.println("Search query cannot be empty.");
            return;
        }
        List<String> notes = readAllNotes();
        boolean found = false;
        for (int i = 0; i < notes.size(); i++) {
            String n = notes.get(i);
            if (n.toLowerCase().contains(q)) {
                if (!found) System.out.println("\n--- Search results ---");
                System.out.printf("%d. %s%n", i + 1, n);
                found = true;
            }
        }
        if (!found) System.out.println("No matching notes found.");
    }
}
 