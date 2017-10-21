package kahloringler.auxtunes.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextTest extends RendererTest {

    @Override
    public Actor make() {
        return factory.label("text test", Gdx.files.internal("fonts/raleway.ttf"), 32, Color.BLUE);
    }

}
