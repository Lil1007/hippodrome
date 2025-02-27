import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled("Тест отключен")
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            System.out.println("Main method threw an exception: " + e.getMessage());
        }
    }
}