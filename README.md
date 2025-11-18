📘 Notes App – Java File I/O Project

This is a simple console-based Notes Application built using Java File Handling.
It demonstrates how to read, write, append, overwrite, search, and delete text stored in a file (notes.txt).
This project is part of the Elevate Labs Java Internship – Task 4.

🚀 Features

Add new notes (append mode)

View all notes

Delete individual notes by number

Overwrite the entire notes file

Search notes by keyword

Stores data permanently in notes.txt

Uses FileReader, FileWriter, BufferedReader, BufferedWriter, and PrintWriter

Proper exception handling using try-catch

Safe file operations via try-with-resources

🛠 Technologies Used

Java (JDK 17+ or 21)

Terminal / Command Prompt

File I/O Streams

🧩 Step-by-Step Implementation
1️⃣ Project Setup

Created a single Java file NotesApp.java

The program automatically creates notes.txt when writing notes for the first time

2️⃣ Reading Notes

Implemented a method readAllNotes()

Used:

FileReader

BufferedReader

Read each line and stored all notes in an ArrayList<String>

3️⃣ Adding Notes (Append Mode)

Implemented addNote() using:

new FileWriter("notes.txt", true)


This allows adding new notes without deleting previous ones

Wrapped writer with:

BufferedWriter

PrintWriter

4️⃣ Viewing Notes

Implemented viewNotes() to print each note with numbering

Helps identify which note to delete or inspect

5️⃣ Deleting Notes

Loaded all notes into a list

Removed the selected index

Rewrote the file using:

new FileWriter("notes.txt", false)


This overwrites the existing file with updated content

6️⃣ Overwriting All Notes

Implemented overwriteNotes()

Allows entering multiple lines

Blank line ends input

Entire file replaced using writeAllNotes()

7️⃣ Searching Notes

Implemented a case-insensitive search using String.contains()

Displays all matching note lines with their line numbers

8️⃣ Exception Handling

Used try-catch blocks around all file operations

Handled IOException with user-friendly messages

Used try-with-resources so streams close automatically and safely

9️⃣ Menu-Driven Program

Created a loop-based menu using:

Scanner

switch-case

Options for add, view, delete, overwrite, search, and exit

▶️ How to Run the Program
Compile
javac NotesApp.java

Run
java NotesApp


The file notes.txt will be created in the same folder.

📂 File Structure

NotesApp/

 ├── NotesApp.java
 
 └── notes.txt (auto-created)
