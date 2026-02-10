import java.io.*;

/**
 * Represents a Student entity with serialization capabilities.
 * <p>
 * This class demonstrates various serialization concepts including:
 * <ul>
 *     <li>Basic serialization and deserialization</li>
 *     <li>Use of transient fields for sensitive data</li>
 *     <li>Custom serialization logic</li>
 * </ul>
 * </p>
 *
 * @author Carson
 * @version 1.0
 * @since 2026-02-10
 * @see java.io.Serializable
 */
public class Student implements Serializable {

    /**
     * Serial version UID for ensuring serialization compatibility.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of the student.
     */
    private String name;

    /**
     * The age of the student in years.
     */
    private int age;

    /**
     * The password of the student.
     * <p>
     * This field is marked as transient and will not be serialized,
     * ensuring sensitive data is not persisted to storage.
     * </p>
     */
    private transient String password;

    /**
     * Constructs a new Student with the specified name and age.
     *
     * @param name the name of the student
     * @param age  the age of the student in years
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Constructs a new Student with the specified name, age, and password.
     * <p>
     * Note: The password field is transient and will not be serialized.
     * </p>
     *
     * @param name     the name of the student
     * @param age      the age of the student in years
     * @param password the password of the student (will not be serialized)
     */
    public Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return a string containing the student's name, age, and password
     */
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }

    /**
     * Custom serialization method that demonstrates custom serialization logic.
     * <p>
     * This method writes the default serializable fields and then writes
     * the age multiplied by 2 as an example of custom data transformation
     * during serialization.
     * </p>
     *
     * @param out the ObjectOutputStream to write the object to
     * @throws IOException if an I/O error occurs during writing
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(age * 2); // Custom logic: write age multiplied by 2
    }

    /**
     * Custom deserialization method that demonstrates custom deserialization logic.
     * <p>
     * This method reads the default serializable fields and then reads
     * the transformed age value, dividing it by 2 to restore the original value.
     * </p>
     *
     * @param in the ObjectInputStream to read the object from
     * @throws IOException            if an I/O error occurs during reading
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        age = in.readInt() / 2; // Custom logic: read and divide by 2 to restore original age
    }
}

