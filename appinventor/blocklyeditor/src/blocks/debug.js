
goog.provide('Blockly.Blocks.debug');

Blockly.Blocks['debug_eventframe'] = {
  "invertedNotch": true,
  "previousStatement": null,
  "colour": Blockly.CONTROL_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.CONTROL_CATEGORY_HUE);
    this.appendDummyInput().appendField('on Button1.Click');
    this.appendStatementInput('VARS');
    this.setPreviousStatement(true);
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_procframe'] = {
  "invertedNotch": true,
  "previousStatement": null,
  "nextStatement": null,
  "colour": Blockly.PROCEDURE_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.PROCEDURE_CATEGORY_HUE);
    this.appendDummyInput().appendField('in doFoo');
    this.appendStatementInput('VARS');
    this.setPreviousStatement(true);
    this.setNextStatement(true);
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_variable'] = {
  "colour": Blockly.VARIABLE_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.VARIABLE_CATEGORY_HUE);
    this.appendValueInput('VALUE').appendField('x');
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_number'] = {
  "colour": Blockly.MATH_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.MATH_CATEGORY_HUE);
    this.setOutput(true);
    this.appendDummyInput().appendField(new Blockly.FieldTextInput('0'), 'NUM');
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_text'] = {
  "colour": Blockly.TEXT_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.TEXT_CATEGORY_HUE);
    this.setOutput(true);
    this.appendDummyInput().appendField(new Blockly.FieldTextInput('foo'), 'TEXT');
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_boolean'] = {
  "colour": Blockly.LOGIC_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.LOGIC_CATEGORY_HUE);
    this.setOutput(true);
    this.appendDummyInput().appendField(new Blockly.FieldDropdown([['true', 'TRUE'], ['false', 'FALSE']]), 'BOOL');
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_component_block'] = {
  "colour": Blockly.ComponentBlock.COLOUR_COMPONENT,
  "init": function() {
    this.setColour(Blockly.ComponentBlock.COLOUR_COMPONENT);
    this.setOutput(true);
    this.appendDummyInput().appendField(new Blockly.FieldDropdown([['Screen1', 'Screen1']]), 'COMPONENT_NAME');
  },
  "tooltip": "",
  "helpUrl": ""
};

Blockly.Blocks['debug_list'] = {
  "colour": Blockly.LIST_CATEGORY_HUE,
  "init": function() {
    this.setColour(Blockly.LIST_CATEGORY_HUE);
    this.setOutput(true);
    this.appendValueInput('ITEM0');
    this.appendValueInput('ITEM1');
  },
  "tooltip": "",
  "helpUrl": ""
};
