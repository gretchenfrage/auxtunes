package kahloringler.auxtunes;

import kahloringler.auxtunes.gui.tree.GUITree;
import kahloringler.auxtunes.gui.InputEvent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenState {

    GUITree getGUITree();

    Optional<GUITree> update();

    Optional<GUITree> onInput(List<UUID> guiPath, InputEvent event);

}
