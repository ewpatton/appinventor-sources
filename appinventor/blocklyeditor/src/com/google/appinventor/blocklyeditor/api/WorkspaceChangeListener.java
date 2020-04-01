package com.google.appinventor.blocklyeditor.api;

import com.google.appinventor.blocklyeditor.BlocklyPanel;
import com.google.appinventor.blocklyeditor.events.Event;

/**
 * BlocklyWorkspaceChangeListener allows other parts of the App Inventor system to subscribe to
 * events in the Blockly panel.
 *
 * @see com.google.appinventor.client.editor.youngandroid.events.EventHelper
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 *
 */
public interface WorkspaceChangeListener {
  /**
   * Event callback when a workspace change occurs.
   *
   * @param panel Source BlocklyPanel where the event occurred.
   * @param event Native object representing the event.
   */
  void onWorkspaceChange(BlocklyPanel panel, Event event);

  void onToggleWarning(BlocklyPanel panel);
}
