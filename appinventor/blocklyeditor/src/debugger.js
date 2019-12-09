'use strict';

goog.require('Blockly');
goog.require('AI.Blockly.Instrument');
goog.require('Blockly.TypeBlock');
goog.require('AI.Blockly.WorkspaceSvg');
goog.require('Blockly.Blocks.Utilities');
goog.require('goog.dom');

if (Blockly.debugWorkspace === undefined) {
  Blockly.debugWorkspace = {};
}

Blockly.allWorkspaces = {};

// Test block to be displayed in DebuggerPanel
Blockly.Blocks['test'] = {
  category: 'Text',
  helpUrl: Blockly.Msg.LANG_TEXT_TEXT_HELPURL,
  init: function () {
    this.setColour(Blockly.TEXT_CATEGORY_HUE);
    this.appendDummyInput().appendField("x");
    this.setTooltip(Blockly.Msg.LANG_TEXT_TEXT_TOOLTIP);
  },
  typeblock: [{translatedName: Blockly.Msg.LANG_CATEGORY_TEXT}]
};
