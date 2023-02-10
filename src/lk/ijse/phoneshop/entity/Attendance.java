package lk.ijse.phoneshop.entity;

public class Attendance {
    private String aid;
    private String employeeName;
    private String date;
    private String sate;
    private String inTime;
    private String outTime;
    private String eid;

    public Attendance(String aid, String employeeName, String date, String sate, String inTime, String outTime, String eid) {
        this.aid = aid;
        this.employeeName = employeeName;
        this.date = date;
        this.sate = sate;
        this.inTime = inTime;
        this.outTime = outTime;
        this.eid = eid;
    }

    public Attendance() {
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSate() {
        return sate;
    }

    public void setSate(String sate) {
        this.sate = sate;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
