package com.google.appinventor.blocklyeditor;

import com.google.gwt.query.client.builders.JsniBundle;

interface BlocklySource extends JsniBundle {
  @LibrarySource(value="blockly-all.js",
                 prepend="(function(window, document, console){\nthis.goog = goog = top.goog;\n",
                 postpend="\n}.apply(window, [$wnd, $doc, $wnd.console]));\n" +
                 "for(var ns in window.goog.implicitNamespaces_) {\n" +
                 "  if(ns.indexOf('.') !== false) ns = ns.split('.')[0];\n" +
                 "  top[ns] = window.goog.global[ns];\n" +
                 "}\nwindow['Blockly'] = top['Blockly'];\nwindow['AI'] = top['AI'];")
  void initBlockly();
}
