import static org.junit.Assert.*;
import org.junit.*;


// no main because JUnit does not require it 
public class PlayerTest {

    private static Player player1;
    private static Player player2;

    @BeforeClass  
    // the method is run before test class is created
    public static void setUp() 
    {
        player1 = new Player("Steffi Graf");
        player2 = new Player("Arancha Sanchez");
    }

    @Test
    // runs actual test
    public void canCreateNewPlayers() 
    {
    	// check if its null: no so test should pass
        assertNotNull(player1); 
        assertNotNull(player2);
    }
}

