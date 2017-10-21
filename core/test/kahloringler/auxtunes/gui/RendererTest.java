package kahloringler.auxtunes.gui;

import kahloringler.auxtunes.AcceptanceTest;
import kahloringler.auxtunes.AuxTunes;
import org.junit.Test;

import static org.junit.Assert.*;

public class RendererTest {

    @Test
    public void testHandler() {
        AcceptanceTest.run(new AuxTunes(), "renderer test");
    }

}