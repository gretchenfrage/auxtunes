package kahloringler.auxtunes;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import kahloringler.auxtunes.gui.GUIPath;
import kahloringler.auxtunes.gui.InputEvent;
import kahloringler.auxtunes.gui.WidgetFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenState {

    Widget getGUITree(WidgetFactory factory);

    Optional<Widget> update(WidgetFactory factory);

    Optional<Widget> onInput(GUIPath guiPath, InputEvent event, WidgetFactory factory);

}
