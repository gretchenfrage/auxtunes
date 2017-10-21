package kahloringler.auxtunes.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import kahloringler.auxtunes.AcceptanceTest;
import org.junit.Test;

public abstract class RendererTest extends AcceptanceTest {

    protected Renderer renderer;
    protected WidgetFactory factory;

    public abstract Widget make();

    @Test
    public void testHandler() {
        super.acceptanceTest();
    }

    @Override
    public void create() {
        renderer = new Renderer();
        factory = new WidgetFactory();
        renderer.update(make());
    }

    @Override
    public void render() {
        renderer.draw();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        factory.dispose();
    }

}