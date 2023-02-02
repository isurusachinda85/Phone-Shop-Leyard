package lk.ijse.phoneshop.to;

public class Attendance {
    private String attendanceId;
    private String employeeId;
    private String name;
    private String date;
    private String sate;
    private String inTime;
    private String outTime;

    public Attendance() {
    }

    public Attendance(String attendanceId, String employeeId, String name, String date, String sate, String inTime, String outTime) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.name = name;
        this.date = date;
        this.sate = sate;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
