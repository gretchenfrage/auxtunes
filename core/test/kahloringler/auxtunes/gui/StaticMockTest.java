package kahloringler.auxtunes.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class StaticMockTest extends RendererTest {
    @Override
    public Actor make() {
        Table bar = factory.table();
        bar.setBackground(factory.monochromeBackground(Color.GRAY));
        bar.add(factory.label("AuxTunes", RALEWAY, 32, Color.WHITE));
        bar.row();
        for (int i = 0; i < 5; i++) {
            bar.add(factory.label("Playlist " + i, RALEWAY, 16, Color.WHITE));
            bar.row();
        }
        bar.add(factory.label("+", RALEWAY, 32, Color.RED));

        Table master = factory.table();
        master.setFillParent(true);
        master.add(bar).align(Align.left);

        return master;
    }
}
