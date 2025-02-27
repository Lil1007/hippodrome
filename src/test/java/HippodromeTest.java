import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    void constructorNullHorsesThrowsIllegalArgumentException(){
        Throwable exception = null;
        try{
            new Hippodrome(null);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @Test
    void constructorNullHorsesHasCorrectMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );

        assertEquals("Horses cannot be null.", exception.getMessage() );
    }

    private List<Horse> horsesTest;

    @Test
    void constructorEmptyHorsesThrowsIllegalArgumentException(){
        horsesTest = new ArrayList<Horse>();
        Throwable exception = null;
        try{
            new Hippodrome(horsesTest);
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(IllegalArgumentException.class, exception.getClass() );
    }

    @Test
    void constructorEmptyHorsesHasCorrectMessage(){
        horsesTest = new ArrayList<>();

        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(horsesTest);
                }
        );

        assertEquals("Horses cannot be empty.", exception.getMessage() );
    }

    @Test
    void getHorses() {
        horsesTest = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horsesTest.add(new Horse(String.valueOf(i),i));
        }

        assertEquals(horsesTest,new Hippodrome(horsesTest).getHorses());

    }

    @Test
    void move() {
        horsesTest = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horsesTest.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horsesTest);

        hippodrome.move();

        for (Horse horse : horsesTest) {
            verify(horse, times(1)).move();
        }
    }

    @Test
    void getWinner() {
        horsesTest = new ArrayList<>();

        Horse horse1 = new Horse("Horse1", 10, 50);
        Horse horse2 = new Horse("Horse2", 12, 100);
        Horse horse3 = new Horse("Horse3", 15, 75);

        horsesTest.add(horse1);
        horsesTest.add(horse2);
        horsesTest.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horsesTest);

        assertEquals(horse2, hippodrome.getWinner());
    }
}