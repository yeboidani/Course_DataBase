public class CourseDBElement {
    private String courseID;
    private int CRN;
    private int credits;
    private String roomNumber;
    private String instructorName;

    public CourseDBElement(){
        this("", 0, 0, "", "");
    }

    public CourseDBElement(String courseID, int CRN, int credits, String roomNumber, String instructorName) {
        this.courseID = courseID;
        this.CRN = CRN;
        this.credits = credits;
        this.roomNumber = roomNumber;
        this.instructorName = instructorName;
    }


    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int hashCode(){
        return String.valueOf(CRN).hashCode();
    }


    public int compareTo(CourseDBElement element) {
        return hashCode() - element.hashCode();
    }

    public String getID() {
        return courseID;
    }

    public String getRoomNum() {
        return roomNumber;
    }


    @Override
    public String toString() {
        return "Course:" + courseID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructorName + " Room:" + roomNumber;
    }


}
