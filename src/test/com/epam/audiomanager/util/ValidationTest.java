package test.com.epam.audiomanager.util;

import com.epam.audiomanager.util.valid.Validation;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test
public class ValidationTest {
    @Test
    public void emailValidatorSuccessTest() {
        assertTrue(Validation.isCorrectEmail("arturlyup@gmail.com"));
    }

    @Test
    public void emailValidatorFailedTest() {
        assertFalse(Validation.isCorrectEmail("arturlyupgmail.com"));
    }
}
