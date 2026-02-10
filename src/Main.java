import java.io.*;

/**
 * Main class demonstrating Java serialization concepts.
 * <p>
 * This class contains exercises that demonstrate:
 * <ul>
 *     <li>Basic serialization and deserialization of objects</li>
 *     <li>Use of transient fields to exclude sensitive data from serialization</li>
 *     <li>Custom serialization with writeObject and readObject methods</li>
 * </ul>
 * </p>
 *
 * @author Carson
 * @version 1.0
 * @since 2026-02-10
 */
public class Main {

    /**
     * The main entry point of the application.
     * <p>
     * Executes all three serialization exercises in sequence.
     * </p>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Basic Serialisation and Deserialisation ===");
        exercise1();

        System.out.println("\n=== Exercise 2: Using transient Fields ===");
        exercise2();

        System.out.println("\n=== Exercise 3: Custom Serialisation ===");
        exercise3();
    }

    /**
     * Exercise 1: Demonstrates basic serialization and deserialization.
     * <p>
     * This method creates a Student object, serializes it to a file named "student.dat",
     * and then deserializes it back to demonstrate the basic serialization process.
     * </p>
     * <p>
     * The exercise shows how Java's built-in serialization mechanism can persist
     * objects to binary files and restore them later.
     * </p>
     */
    public static void exercise1() {
        Student student = new Student("Max", 22);
        String filename = "student.dat";

        // Serialise the Student object
        try (FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(student);
            System.out.println("Student object serialised and saved as " + filename);
            System.out.println("Original object: " + student);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialise the Student object
        try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Student deserialisedStudent = (Student) in.readObject();
            System.out.println("Deserialised object: " + deserialisedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exercise 2: Demonstrates the use of transient fields in serialization.
     * <p>
     * This method creates a Student object with a password field marked as transient,
     * serializes it, and then deserializes it to show that transient fields are
     * not persisted during serialization.
     * </p>
     * <p>
     * Key learning points:
     * <ul>
     *     <li>Transient fields are excluded from serialization</li>
     *     <li>After deserialization, transient fields will have their default values (null for objects)</li>
     *     <li>Use transient for sensitive data like passwords that shouldn't be persisted</li>
     * </ul>
     * </p>
     */
    public static void exercise2() {
        Student studentWithPassword = new Student("Alice", 20, "secretPassword123");
        String filename = "student_with_password.dat";

        System.out.println("Before serialisation: " + studentWithPassword);

        // Serialise the Student object with password
        try (FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(studentWithPassword);
            System.out.println("Student with password serialised and saved as " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialise the Student object
        try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Student deserialisedStudent = (Student) in.readObject();
            System.out.println("After deserialisation: " + deserialisedStudent);
            System.out.println("\nObservation: The password field is null after deserialisation!");
            System.out.println("This is because 'transient' fields are NOT serialised.");
            System.out.println("Use 'transient' for sensitive data like passwords that shouldn't be persisted.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exercise 3: Demonstrates custom serialization with writeObject and readObject methods.
     * <p>
     * This method shows how to implement custom serialization logic by overriding
     * the default serialization behavior using private writeObject and readObject methods.
     * </p>
     * <p>
     * The exercise demonstrates transforming the age field during serialization
     * (multiplying by 2) and restoring it during deserialization (dividing by 2).
     * </p>
     * <p>
     * Use cases for custom serialization include:
     * <ul>
     *     <li>Data encryption for security</li>
     *     <li>Data compression to reduce file size</li>
     *     <li>Version control for handling different object versions</li>
     *     <li>Data validation during read/write operations</li>
     *     <li>Recalculating transient fields on deserialization</li>
     * </ul>
     * </p>
     */
    public static void exercise3() {
        Student student = new Student("Bob", 25);
        String filename = "student_custom.dat";

        System.out.println("Before serialisation: " + student);

        // Serialise using custom writeObject
        try (FileOutputStream fileOut = new FileOutputStream(filename); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(student);
            System.out.println("Student serialised with custom logic (age written as " + (25 * 2) + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialise using custom readObject
        try (FileInputStream fileIn = new FileInputStream(filename); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            Student deserialisedStudent = (Student) in.readObject();
            System.out.println("After deserialisation (age restored): " + deserialisedStudent);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Why is custom serialisation needed? ---");
        System.out.println("1. Data Encryption: Encrypt sensitive data before writing");
        System.out.println("2. Data Compression: Compress data to reduce file sise");
        System.out.println("3. Version Control: Handle different versions of serialised objects");
        System.out.println("4. Validation: Validate data during serialisation/deserialisation");
        System.out.println("5. Computed Fields: Recalculate transient fields on deserialisation");
        System.out.println("6. Security: Obfuscate or transform data for security purposes");
    }
}
