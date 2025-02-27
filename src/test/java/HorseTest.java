import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void constructorNullNameThrowsIllegalArgumentException(){
        Throwable exception = null;
        try{
            new Horse(null,10);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @Test
    void constructorNullNameHasCorrectMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null,10);
                }
        );

        assertEquals("Name cannot be null.", exception.getMessage() );
    }

    @ParameterizedTest
    @MethodSource("argsName")
    void constructorEmptyNameThrowsIllegalArgumentException (String name){
        Throwable exception = null;
        try{
            new Horse(name,10);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @ParameterizedTest
    @MethodSource("argsName")
    void constructorEmptyNameHasCorrectMessage(String name){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name,10);
                }
        );

        assertEquals("Name cannot be blank.", exception.getMessage() );
    }

    static Stream<String> argsName() {
        return Stream.of("", "  ", "        " );
    }

    @Test
    void constructorNegativeSpeedThrowsIllegalArgumentException(){
        Throwable exception = null;
        try{
            new Horse("name",-1);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @Test
    void constructorNegativeSpeedHasCorrectMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name",-1);
                }
        );

        assertEquals("Speed cannot be negative.", exception.getMessage() );
    }

    @Test
    void constructorNegativeDistanceThrowsIllegalArgumentException(){
        Throwable exception = null;
        try{
            new Horse("name",1,-1);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @Test
    void constructorNegativeDistanceHasCorrectMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name",1,-1);
                }
        );

        assertEquals("Distance cannot be negative.", exception.getMessage() );
    }

    @Test
    void getName() {
        assertEquals("BlazeTest",new Horse("BlazeTest",10).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(8,new Horse("BlazeTest",8).getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(100,new Horse("BlazeTest",8,100).getDistance());
    }

    @Test
    void getDistanceZero() {
        assertEquals(0,new Horse("BlazeTest",8).getDistance());
    }

    @Test
    void move() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            Horse horse = new Horse("BlazeTest", 16.0, 10.0);

            horse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.7, 0.9})
    void moveWithParameterized(double value) {

        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            Horse horse = new Horse("BlazeTest", 16.0, 10.0);

            horse.move();
            assertEquals(10.0 + 16.0 * value, horse.getDistance());
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}