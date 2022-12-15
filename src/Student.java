public class Student extends User{
    private String name;
    private int course;

    public Student(String username, String password, String name, int course) {
        super(username, password);
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
