public class Employee {

    //Properties
    private int id;
    private String title;
    private String firstName;
    private String surname;
    private String jobTitle;
    private String department;
    private ExpenseClaim[] claims;

    //Constructor that allows to use every get or set method in here
    //No need to create it manually though, java does this for you.
    public Employee() {

    }

    //Constructor that allows to create a Employee when you instantiate the class
    //This way you do it directly with only id and jobTitle (in this case)
    public Employee(int id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    //Constructor that allows you to create an employee when you instantiate the class
    //This way the class would appear to be read-only
    //By using this constructor the set Methods are not necessary only the get methods.
    public Employee(int id, String title, String firstName, String surname, String jobTitle, String department) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    //Any other method for this class
    public String getMailingName() {
        return title + " " + firstName + " " + surname;
    }

    public String getMailingName(boolean firstInitialOnly) {
        if (firstInitialOnly) {
            return title + " " + firstName.substring(0, 1) + " " + surname;
        } else{
            return title + " " + surname;
        }
    }

    //Setters and Getters Methods
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.length() < 2) {
            System.out.println("Error - first name must be at least 2 characters");
        } else {
            this.firstName = firstName;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}
