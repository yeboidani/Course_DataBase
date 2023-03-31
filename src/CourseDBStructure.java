import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {

    private int tableSize;
    LinkedList<CourseDBElement>[] hashTable;
    ArrayList<CourseDBElement> order;

    public CourseDBStructure(int Courses) {
        //round up
        //int temp = (int)(Courses/1.5);
        int temp = (int) Math.ceil(Courses/1.5);
        tableSize = next4K3Prime(temp);
        hashTable = new LinkedList[tableSize];
        order = new ArrayList<>();
    }

    public CourseDBStructure(String testing, int Courses) {
        tableSize = Courses;
        hashTable = new LinkedList[tableSize];
        order = new ArrayList<>();
    }

    private boolean isPrime(int num){

        if(num <= 1)
            return false;

        for(int i = 2; i < num; i++){
            if(num % i == 0)
                return false;
        }

        return true;
    }

    private int next4K3Prime(int num){

        //make sure num is odd
        if(num % 2 == 0){
            num++;
        }

        while (true) {
            if (isPrime(num) && (num - 3) % 4 == 0) {
                return num;
            }
            num += 2;
        }


    }

    /**
     * Adds a CourseDBElement object to the CourseDBStructure using the hashcode
     * of the CourseDatabaseElement object's crn value.
     * If the CourseDatabaseElement already exists, exit quietly
     *
     * @param element the CourseDBElement to be added to CourseDBStructure
     */
    public void add(CourseDBElement element){
        //to get the idx where the element should be placed
        int idx = element.hashCode() % tableSize;

        //If it's null then create a LinkedList at that location
        if(hashTable[idx] == null){
            hashTable[idx] = new LinkedList<CourseDBElement>();
        }

        //checks for duplicates and also uses crn to update info
        for(CourseDBElement check: hashTable[idx]){
            if (check.getCRN() == element.getCRN()) {
                check.setCourseID(element.getID());
                check.setCredits(element.getCredits());
                check.setRoomNumber(element.getRoomNumber());
                check.setInstructorName(element.getInstructorName());
                return;
            }

        }

        // Insert the element at the beginning of orderedElements
        order.add(0, element);
        hashTable[idx].add(element);
    }



    /**
     * Find a courseDatabaseElement based on the key (crn) of the
     * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
     * throw an IOException
     *
     * @param crn crn (key) whose associated courseDatabaseElement is to be returned
     * @return a CourseDBElement whose crn is mapped to the key
     * @throws IOException if key is not found
     */

    @Override
    public CourseDBElement get(int crn) throws IOException {
        CourseDBElement finalReturn = null;

        for(LinkedList<CourseDBElement> list: hashTable){
            if(list != null){
                for(CourseDBElement listElement: list){
                    if(listElement.compareTo(new CourseDBElement("", crn, 0, "", "")) == 0)
                        finalReturn = listElement;

                }
            }
        }
        if(finalReturn == null){
            throw new IOException("Key is not found");
        }

        return finalReturn;
    }

    /**
     * @return an array list of string representation of each course in
     * the data structure separated by a new line.
     * Refer to the following example:
     * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
     * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
     */

    //Might have to come back and implement the toString for CourseDBElement during testing
    @Override
    public ArrayList<String> showAll() {
        ArrayList<String> toReturn = new ArrayList<>();

        for(int i = 0; i < hashTable.length; i++){

            if(hashTable[i] != null){
                for(CourseDBElement element: hashTable[i]){
                    toReturn.add(element.toString());

                }
            }
        }

        return toReturn;

    }

    @Override
    public int getTableSize() {
        return tableSize;
    }


}
