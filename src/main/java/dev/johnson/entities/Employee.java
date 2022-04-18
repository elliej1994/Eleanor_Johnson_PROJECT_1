package dev.johnson.entities;



public class Employee {


    private int eId;
    private String fName;
    private String lName;
    private String dpt;

    public Employee(){}
    public Employee(int eId, String fName, String lName, String dpt) {
        this.eId = eId;
        this.fName = fName;
        this.lName = lName;
        this.dpt = dpt;
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

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", dpt='" + dpt + '\'' +
                '}';
    }
}
