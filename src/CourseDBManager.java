import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {

    private CourseDBStructure structure;

    public CourseDBManager() {
        this.structure = new CourseDBStructure(19);
    }

    /**
     * Adds a course (CourseDBElement) with the given information
     * to CourseDBStructure.
     * @param id course id
     * @param crn course crn
     * @param credits number of credits
     * @param roomNum course room number
     * @param instructor name of the instructor
     */
    public void add(String id, int crn, int credits, String roomNum, String instructor){
        structure.add(new CourseDBElement(id, crn, credits, roomNum, instructor));
    }



    /**
     * finds  CourseDBElement based on the crn key
     * @param crn course crn (key)
     * @return a CourseDBElement object
     *
     */

    //maybe do a try-catch if an error is thrown
    public CourseDBElement get(int crn) {
        //CourseDBElement finalReturn = new CourseDBElement("", 0, 0, "", "");

        CourseDBElement finalReturn = null;
        try {
            finalReturn = structure.get(crn);
        } catch (IOException e) {
            e.getMessage();
        }


        return finalReturn;

    }

    /**
     * Reads the information of courses from a test file and adds them
     * to the CourseDBStructure data structure
     * @param input input file
     * @throws FileNotFoundException if file does not exists
     */
    public void readFile(File input) throws FileNotFoundException{

        ArrayList<String> fileData = new ArrayList<>();
        Scanner sc = new Scanner(input);

        if(input.exists() == false){
            throw new FileNotFoundException("File doesn't exist");
        }


        while(sc.hasNext()){
            String next = sc.nextLine();
            if(next!= "")
                fileData.add(next);

        }
        //close then read file
        sc.close();

        for(String data: fileData){
            //just in case the Instructor name has a space, limiting the number of splits should work.
            String[] Array = data.split(" ", 5);
            String courseID = Array[0];
            //parse bc it's stored as a String
            int crn = Integer.parseInt(Array[1]);
            int credits = Integer.parseInt(Array[2]);
            String roomNum = Array[3];
            String Instructor = Array[4];
            add(courseID, crn, credits, roomNum, Instructor);

        }

    }

    /**
     * @return an array list of string representation of each course in
     * the data structure separated by a new line.
     * Refer to the following example:
     * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
     * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
     */

    //Come back to, kinda scuffed Implementation.
    public ArrayList<String> showAll(){
        ArrayList<String> toReturn = new ArrayList<>();

        for(int i = 0; i < structure.order.size(); i++){
            if(structure.order != null){
                for (CourseDBElement element : structure.order) {
                    toReturn.add("\n" + element.toString());
                }
            }
        }

        return toReturn;
    }



}
