import static org.junit.jupiter.api.Assertions.*;

import com.encora.expenses.domain.Employee;
import com.encora.expenses.exceptions.InvalidEmployeeIdException;
import com.encora.expenses.utilities.EmployeeUtilities;
import org.junit.jupiter.api.Test;

public class SampleTests {

    @Test
    public void testSomething() {

        int a = 4;
        int b = 8;
        int total = a + b;

        //A test method that's checking that when you add two numbers together, you get the answer 12.
        assertEquals(12, total);
    }

    @Test
    public void testEmployeeIdNumberIsConvertedCorrectly() throws InvalidEmployeeIdException {
        EmployeeUtilities employeeUtilities = new EmployeeUtilities();
        int result = employeeUtilities.validateEmployeeId("416");

        assertEquals(416, result);
    }

    @Test
    public void testThatExceptionIsThrownIfEmployeeIdIsNotAValidNumber(){

        EmployeeUtilities employeeUtilities = new EmployeeUtilities();

        //If it throws an exception it will pass the test
        assertThrows(InvalidEmployeeIdException.class, () -> {
            int result = employeeUtilities.validateEmployeeId("hello");
        });
    }

}
