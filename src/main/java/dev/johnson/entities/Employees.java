package dev.johnson.entities;

public class Employees {

    private int eId;
    private String fName;
    private String lName;
    private String phNum;
    private String dpt;
    private String title;
    private int salary;


    public Employees (){}

    public Employees(int eId, String fName, String lName, String phNum, String dpt, String title, int salary) {
        this.eId = eId;
        this.fName = fName;
        this.lName = lName;
        this.phNum = phNum;
        this.dpt = dpt;
        this.title = title;
        this.salary = salary;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "eId=" + eId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phNum='" + phNum + '\'' +
                ", dpt='" + dpt + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}
