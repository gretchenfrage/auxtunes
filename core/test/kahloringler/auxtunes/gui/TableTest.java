package kahloringler.auxtunes.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class TableTest extends RendererTest {
    @Override
    public Actor make() {
        Table table = factory.table();
        table.setFillParent(true);
        table.setDebug(true);
        for (int i = 0; i < 10; i++) {
            table.add(factory.label(Integer.toString(i), Gdx.files.internal("fonts/raleway.ttf"), 16, Color.BLACK));
        }
        return table;
    }
}

