package kahloringler.auxtunes;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import kahloringler.auxtunes.gui.WidgetFactory;

import java.util.Optional;

public interface PlaylistCreator {

    String getTypeName();

    Widget getGUI(WidgetFactory factory);

    Optional<Playlist> create();

}
