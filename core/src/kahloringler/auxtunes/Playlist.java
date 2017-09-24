package kahloringler.auxtunes;

import kahloringler.auxtunes.guitree.GUITree;
import kahloringler.auxtunes.guitree.InputEvent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Playlist extends ScreenState {

    String getName();

    PlaylistSettings getSettings();

}
