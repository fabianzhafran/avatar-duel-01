import static org.junit.Assert.assertEquals;
import com.avatarduel.Phase.*;
import org.junit.Test;

public class PhaseTest {

    @Test
    public void testPhaseSequence() {
        Phase fakePhase = new Phase();
        fakePhase.nextPhase();
        assertEquals("must be draw phase", 1, fakePhase.getPhaseNumber());
        assertEquals("must be player 1's turn", 1, fakePhase.getPlayerTurn());

        fakePhase.nextPhase();
        assertEquals("must be main phase", 2, fakePhase.getPhaseNumber());

        fakePhase.nextPhase();
        assertEquals("must be battle phase", 3, fakePhase.getPhaseNumber());

        fakePhase.nextPhase();
        assertEquals("must be end phase", 4, fakePhase.getPhaseNumber());

        // After this, the player turn must be player 2
        fakePhase.nextPhase();
        assertEquals("must be draw phase", 1, fakePhase.getPhaseNumber());
        assertEquals("must be player 2's turn", 2, fakePhase.getPlayerTurn());

    }

}
