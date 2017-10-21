package kahloringler.auxtunes.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Renderer implements Disposable {
    private Stage stage = new Stage(new ScreenViewport());

    public void update(Widget toDisplay) {
        stage.clear();
        stage.addActor(toDisplay);
    }

    public void draw() {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}