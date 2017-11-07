// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0
/**
 * @license
 * @fileoverview Blocks for providing enumerations and constants.
 * @author ewpatton@mit.edu (Evan W. Patton)
 */

'use strict';

goog.provide('Blockly.Blocks.constants');

goog.require('goog.dom');
goog.require('Blockly.Blocks.Utilities');
goog.require('Blockly.Events');
goog.require('Blockly.FieldDropdown');

Blockly.Blocks['constants_enumeration'] = {
  category: 'Constants',
  init: function() {
    this.setColour(Blockly.CONSTANT_CATEGORY_HUE);
    this.dropdown_ = new Blockly.FieldDropdown(this.getValues.bind(this));
    this.enumClass_ = '';
    this.appendDummyInput().appendField(this.dropdown_, "ORDINAL");
    this.setOutput(true, Blockly.Blocks.Utilities.YailTypeToBlocklyType("enumeration",
      Blockly.Blocks.Utilities.OUTPUT));
    this.setTooltip("foo");
  },
  mutationToDom: function() {
    var mutation = document.createElement('mutation');
    var classEl = document.createElement('class');
    classEl.setAttribute('name', this.enumClass_);
    mutation.appendChild(classEl);
    return mutation;
  },
  domToMutation: function(xmlElement) {
    var children = goog.dom.getChildren(xmlElement);
    if (children.length === 1) {
      var child = children[0];
      if (child.nodeName.toLowerCase() === 'class') {
        this.enumClass_ = child.getAttribute('name');
      }
    }
    this.dropdown_.setValue(this.getValues()[0][1]);
  },
  getValues: function() {
    return this.getTopWorkspace().getComponentDatabase().getEnumeration(this.enumClass_);
  }
};
