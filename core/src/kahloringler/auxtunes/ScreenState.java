package kahloringler.auxtunes;

import kahloringler.auxtunes.guitree.GUITree;
import kahloringler.auxtunes.guitree.InputEvent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenState {

    GUITree getGUITree();

    Optional<GUITree> update();

    Optional<GUITree> onInput(List<UUID> guiPath, InputEvent event);

}
