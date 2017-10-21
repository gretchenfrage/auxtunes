package kahloringler.auxtunes.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.Test;

public abstract class RendererTest extends ApplicationAdapter {

    protected static final FileHandle RALEWAY = Gdx.files.internal("fonts/raleway.ttf");

    protected Renderer renderer;
    protected WidgetFactory factory;

    public abstract Actor make();

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
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        factory.dispose();
    }

}