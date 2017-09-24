package kahloringler.auxtunes;

import kahloringler.auxtunes.gui.tree.GUITree;

import java.util.Optional;

public interface PlaylistCreator {

    String getTypeName();

    GUITree getGUITree();

    Optional<Playlist> create();

}
