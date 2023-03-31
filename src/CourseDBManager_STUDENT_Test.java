import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class CourseDBManager_STUDENT_Test {

    private CourseDBManagerInterface Data = new CourseDBManager();

    /**
     * Create an instance of CourseDBManager
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Data = new CourseDBManager();
    }

    /**
     * Set dataMgr reference to null
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        Data = null;
    }

    @Test
    public void testAddToDB() {
        try {
            Data.add("ENG101",30555,4,"ST301","Tommy");
        }
        catch(Exception e) {
            fail("This should not have caused an Exception");
        }
    }

    @Test
    public void testShowAll() {
        Data.add("ENG101",30555,4,"ST301","Tommy");
        Data.add("ENG101",30554,4,"ST301","Haland");
        Data.add("ENG102",30556,4,"ST302","Cherry");
        ArrayList<String> list = Data.showAll();
        assertEquals(list.get(0),"\nCourse:ENG102 CRN:30556 Credits:4 Instructor:Cherry Room:ST302");
        assertEquals(list.get(1),"\nCourse:ENG101 CRN:30554 Credits:4 Instructor:Haland Room:ST301");
        assertEquals(list.get(2),"\nCourse:ENG101 CRN:30555 Credits:4 Instructor:Tommy Room:ST301");
    }



}
