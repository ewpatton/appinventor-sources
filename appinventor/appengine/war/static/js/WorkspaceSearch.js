(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory(require("blockly/core"));
	else if(typeof define === 'function' && define.amd)
		define(["blockly/core"], factory);
	else {
		var a = typeof exports === 'object' ? factory(require("blockly/core")) : factory(root["Blockly"]);
		for(var i in a) (typeof exports === 'object' ? exports : root)[i] = a[i];
	}
})(this, (__WEBPACK_EXTERNAL_MODULE__573__) => {
return /******/ (() => { // webpackBootstrap
/******/ 	"use strict";
/******/ 	var __webpack_modules__ = ({

/***/ 573:
/***/ ((module) => {

module.exports = __WEBPACK_EXTERNAL_MODULE__573__;

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		var cachedModule = __webpack_module_cache__[moduleId];
/******/ 		if (cachedModule !== undefined) {
/******/ 			return cachedModule.exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => (Object.prototype.hasOwnProperty.call(obj, prop))
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
var __webpack_exports__ = {};
// This entry need to be wrapped in an IIFE because it need to be isolated against other modules in the chunk.
(() => {
// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  WorkspaceSearch: () => (/* reexport */ WorkspaceSearch)
});

;// CONCATENATED MODULE: ./src/css.js
/**
 * @license
 * Copyright 2020 Google LLC
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * @fileoverview Styling for workspace search.
 * @author aschmiedt@google.com (Abby Schmiedt)
 * @author kozbial@google.com (Monica Kozbial)
 */

/**
 * Base64 encoded data uri for close icon.
 * @type {string}
 */
const CLOSE_SVG_DATAURI =
    'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC' +
    '9zdmciIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgd2lkdGg9IjE0Ij48cGF0aC' +
    'BkPSJNMTkgNi40MUwxNy41OSA1IDEyIDEwLjU5IDYuNDEgNSA1IDYuNDEgMTAuNTkgMTIgNS' +
    'AxNy41OSA2LjQxIDE5IDEyIDEzLjQxIDE3LjU5IDE5IDE5IDE3LjU5IDEzLjQxIDEyeiIvPj' +
    'xwYXRoIGQ9Ik0wIDBoMjR2MjRIMHoiIGZpbGw9Im5vbmUiLz48L3N2Zz4=';

/**
 * Base64 encoded data uri for keyboard arrow down icon.
 * @type {string}
 */
const ARROW_DOWN_SVG_DATAURI =
    'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC' +
    '9zdmciIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgd2lkdGg9IjE0Ij48cGF0aC' +
    'BkPSJNNy40MSA4LjU5TDEyIDEzLjE3bDQuNTktNC41OEwxOCAxMGwtNiA2LTYtNiAxLjQxLT' +
    'EuNDF6Ii8+PHBhdGggZD0iTTAgMGgyNHYyNEgwVjB6IiBmaWxsPSJub25lIi8+PC9zdmc+';

/**
 * Base64 encoded data uri for keyboard arrow up icon.
 * @type {string}
 */
const ARROW_UP_ARROW_SVG_DATAURI =
    'data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC' +
    '9zdmciIGhlaWdodD0iMTQiIHZpZXdCb3g9IjAgMCAyNCAyNCIgd2lkdGg9IjE0Ij48cGF0aC' +
    'BkPSJNNy40MSAxNS40MUwxMiAxMC44M2w0LjU5IDQuNThMMTggMTRsLTYtNi02IDZ6Ii8+PH' +
    'BhdGggZD0iTTAgMGgyNHYyNEgweiIgZmlsbD0ibm9uZSIvPjwvc3ZnPg==';

/**
 * CSS for workspace search.
 * @type {Array.<string>}
 */
const CSS_CONTENT = [
  /* eslint-disable indent */
  'path.blocklyPath.blockly-ws-search-highlight {',
    'fill: #000;',
  '}',
  'path.blocklyPath.blockly-ws-search-highlight.blockly-ws-search-current {',
    'fill: grey;',
  '}',
  '.blockly-ws-search-close-btn {',
    'background: url(' + CLOSE_SVG_DATAURI + ') no-repeat top left;',
  '}',
  '.blockly-ws-search-next-btn {',
    'background: url(' + ARROW_DOWN_SVG_DATAURI + ') no-repeat top left;',
  '}',
  '.blockly-ws-search-previous-btn {',
    'background: url(' +ARROW_UP_ARROW_SVG_DATAURI + ') no-repeat top left;',
  '}',
  '.blockly-ws-search {',
    'background: #fff;',
    'border: solid lightgrey 0.5px;',
    'box-shadow: 0px 10px 20px grey;',
    'justify-content: center;',
    'padding: 0.25em;',
    'position: absolute;',
    'z-index: 70;',
  '}',
  '.blockly-ws-search-input input {',
    'border: none;',
  '}',
  '.blockly-ws-search button {',
    'border: none;',
  '}',
  '.blockly-ws-search-actions {',
    'display: flex;',
  '}',
  '.blockly-ws-search-container {',
    'display: flex;',
  '}',
  '.blockly-ws-search-content {',
    'display: flex;',
  '}',
  /* eslint-enable indent */
];

/**
 * Injects CSS for workspace search.
 */
const injectSearchCss = (function() {
  let executed = false;
  return function() {
    // Only inject the CSS once.
    if (executed) {
      return;
    }
    executed = true;
    const text = CSS_CONTENT.join('\n');
    // Inject CSS tag at start of head.
    const cssNode = document.createElement('style');
    cssNode.id = 'blockly-ws-search-style';
    const cssTextNode = document.createTextNode(text);
    cssNode.appendChild(cssTextNode);
    document.head.insertBefore(cssNode, document.head.firstChild);
  };
})();

// EXTERNAL MODULE: external {"root":"Blockly","commonjs":"blockly/core","commonjs2":"blockly/core","amd":"blockly/core"}
var core_ = __webpack_require__(573);
;// CONCATENATED MODULE: ./src/WorkspaceSearch.js
/**
 * @license
 * Copyright 2020 Google LLC
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * @fileoverview Class responsible for workspace search.
 * @author aschmiedt@google.com (Abby Schmiedt)
 * @author kozbial@google.com (Monica Kozbial)
 */





/**
 * Class for workspace search.
 * @implements {Blockly.IPositionable}
 */
class WorkspaceSearch {
  /**
   * Class for workspace search.
   * @param {!Blockly.WorkspaceSvg} workspace The workspace the search bar sits
   *     in.
   */
  constructor(workspace) {
    /**
     * The workspace the search bar sits in.
     * @type {!Blockly.WorkspaceSvg}
     * @private
     */
    this.workspace_ = workspace;

    /**
     * The unique id for this component.
     * @type {string}
     */
    this.id = 'workspaceSearch';

    /**
     * HTML container for the search bar.
     * @type {?HTMLElement}
     * @private
     */
    this.htmlDiv_ = null;

    /**
     * The div that holds the search bar actions.
     * @type {?HTMLElement}
     * @protected
     */
    this.actionDiv_ = null;

    /**
     * The text input for the search bar.
     * @type {?HTMLInputElement}
     * @private
     */
    this.inputElement_ = null;

    /**
     * The placeholder text for the search bar input.
     * @type {string}
     * @private
     */
    this.textInputPlaceholder_ = 'Search';

    /**
     * A list of blocks that came up in the search.
     * @type {!Array.<Blockly.BlockSvg>}
     * @protected
     */
    this.blocks_ = [];

    /**
     * Index of the currently "selected" block in the blocks array.
     * @type {number}
     * @protected
     */
    this.currentBlockIndex_ = -1;

    /**
     * The search text.
     * @type {string}
     * @protected
     */
    this.searchText_ = '';

    /**
     * Whether to search as input changes as opposed to on enter.
     * @type {boolean}
     */
    this.searchOnInput = true;

    /**
     * Whether search should be case sensitive.
     * @type {boolean}
     */
    this.caseSensitive = false;

    /**
     * Whether search should preserve the currently selected block by default.
     * @type {boolean}
     */
    this.preserveSelected = true;

    /**
     * Array holding info needed to unbind events.
     * Used for disposing.
     * @type {!Array<!Blockly.browserEvents.Data>}
     * @private
     */
    this.boundEvents_ = [];

    this.componentTypeDropDown = this.createDropDown_();
    this.componentInstanceDropDown = this.createDropDown_();
    this.blockTypeDropDown = this.createDropDown_();
  }

  /**
   * Initializes the workspace search bar.
   */
  init() {
    this.workspace_.getComponentManager().addComponent({
      component: this,
      weight: 0,
      capabilities: [core_.ComponentManager.Capability.POSITIONABLE],
    });
    injectSearchCss();
    this.createDom_();
    this.setVisible_(false);

    this.workspace_.resize();
  }

  /**
   * Disposes of workspace search.
   * Unlink from all DOM elements and remove all event listeners
   * to prevent memory leaks.
   */
  dispose() {
    for (const event of this.boundEvents_) {
      core_.browserEvents.unbind(event);
    }
    this.boundEvents_.length = 0;
    if (this.htmlDiv_) {
      this.htmlDiv_.remove();
      this.htmlDiv_ = null;
    }
    this.actionDiv_ = null;
    this.inputElement_ = null;
  }

  /**
   * Creates and injects the search bar's DOM.
   * @protected
   */
  createDom_() {
    /*
     * Creates the search bar. The generated search bar looks like:
     * <div class="ws-search'>
     *   <div class="ws-search-container'>
     *     <div class="ws-search-content'>
     *       <div class="ws-search-input'>
     *         [... text input goes here ...]
     *       </div>
     *       [... actions div goes here ...]
     *     </div>
     *     [... close button goes here ...]
     *   </div>
     * </div>
     */
    const injectionDiv = this.workspace_.getInjectionDiv();
    this.addEvent_(injectionDiv, 'keydown', this, (evt) => this
        .onWorkspaceKeyDown_(/** @type {KeyboardEvent} */ evt));

    this.htmlDiv_ = document.createElement('div');
    core_.utils.dom.addClass(this.htmlDiv_, 'blockly-ws-search');

    const searchContainer = document.createElement('div');
    core_.utils.dom.addClass(searchContainer, 'blockly-ws-search-container');

    const searchContent = document.createElement('div');
    core_.utils.dom.addClass(searchContent, 'blockly-ws-search-content');
    searchContainer.appendChild(searchContent);

    // SearchType Wrapper
    const searchTypeWrapper = document.createElement('div');
    Blockly.utils.dom.addClass(searchTypeWrapper, 'blockly-ws-search-select');

    // SearchType DropDown
    const searchTypeDropDown = this.createDropDown_(['keyword', 'block', 'component']);
    this.addEvent_(searchTypeDropDown, 'change', this, (e) => {
      this.searchChange_(e.target.value);
      this.searchFind_(e.target.value);
    });
    searchTypeWrapper.appendChild(searchTypeDropDown);

    const inputWrapper = document.createElement('div');
    core_.utils.dom.addClass(inputWrapper, 'blockly-ws-search-input');
    this.inputElement_ = this.createTextInput_();
    this.addEvent_(this.inputElement_, 'keydown', this, (evt) => this
        .onKeyDown_(/** @type {KeyboardEvent} */ evt));
    this.addEvent_(this.inputElement_, 'input', this, () =>this
        .onInput_());
    this.addEvent_(this.inputElement_, 'click', this, () => {
      this.searchAndHighlight(this.searchText_, this.preserveSelected);
      this.inputElement_.select();
    });
  
    // BlockType Wrapper
    const blockTypeWrapper = document.createElement('div');
    Blockly.utils.dom.addClass(blockTypeWrapper, 'blockly-ws-block-select');

    // BlockType DropDown
    this.addEvent_(this.blockTypeDropDown, 'change', this, (e) => {
      this.searchAndHighlight(e.target.value, this.preserveSelected, 'block');
    });
    blockTypeWrapper.appendChild(this.blockTypeDropDown);

    // Component Wrapper
    const componentWrapper = document.createElement('div');
    Blockly.utils.dom.addClass(componentWrapper, 'blockly-ws-component-select');

    // Component Type DropDown
    this.addEvent_(this.componentTypeDropDown, 'change', this, (e) => {
      this.updateComponentInstanceDropDown_(e.target.value);
    });
    componentWrapper.appendChild(this.componentTypeDropDown);

    // Component Instance DropDown
    this.addEvent_(this.componentInstanceDropDown, 'change', this, (e) => {
      this.searchAndHighlight(e.target.value, this.preserveSelected, 'component');
    });
    componentWrapper.appendChild(this.componentInstanceDropDown);

    // App Inventor specific elements
    searchContent.appendChild(searchTypeWrapper);
    searchContent.appendChild(blockTypeWrapper);
    searchContent.appendChild(componentWrapper);

    inputWrapper.appendChild(this.inputElement_);
    searchContent.appendChild(inputWrapper);

    this.actionDiv_ = document.createElement('div');
    core_.utils.dom.addClass(this.actionDiv_, 'blockly-ws-search-actions');
    searchContent.appendChild(this.actionDiv_);

    const nextBtn = this.createNextBtn_();
    if (nextBtn) {
      this.addActionBtn(nextBtn, () => this.next());
    }

    const previousBtn = this.createPreviousBtn_();
    if (previousBtn) {
      this.addActionBtn(previousBtn, () => this.previous());
    }

    const closeBtn = this.createCloseBtn_();
    if (closeBtn) {
      this.addBtnListener_(closeBtn, () => this.close());
      searchContainer.appendChild(closeBtn);
    }

    //hiding block and component when init
    blockTypeWrapper.style.display = 'none';
    componentWrapper.style.display = 'none';

    this.htmlDiv_.appendChild(searchContainer);

    injectionDiv.insertBefore(this.htmlDiv_, this.workspace_.getParentSvg());
  }

  /**
   * Helper method for adding an event.
   * @param {!Element} node Node upon which to listen.
   * @param {string} name Event name to listen to (e.g. 'mousedown').
   * @param {Object} thisObject The value of 'this' in the function.
   * @param {!Function} func Function to call when event is triggered.
   * @private
   */
  addEvent_(node, name, thisObject, func) {
    const event =
        core_.browserEvents.conditionalBind(node, name, thisObject, func);
    this.boundEvents_.push(event);
  }

  /**
   * Add a button to the action div. This must be called after the init function
   * has been called.
   * @param {!HTMLButtonElement} btn The button to add the event listener to.
   * @param {!Function} onClickFn The function to call when the user clicks on
   *     or hits enter on the button.
   */
  addActionBtn(btn, onClickFn) {
    this.addBtnListener_(btn, onClickFn);
    this.actionDiv_.appendChild(btn);
  }

  /**
   * Creates the text input for the search bar.
   * @returns {!HTMLInputElement} A text input for the search bar.
   * @protected
   */
  createTextInput_() {
    const textInput = document.createElement('input');
    textInput.type = 'text';
    textInput.setAttribute('placeholder', this.textInputPlaceholder_);
    return textInput;
  }

  /**
   * Creates the button used to get the next block in the list.
   * @returns {!HTMLButtonElement} The next button.
   * @protected
   */
  createNextBtn_() {
    return this.createBtn_('blockly-ws-search-next-btn', 'Find next');
  }

  /**
   * Creates the button used to get the previous block in the list.
   * @returns {!HTMLButtonElement} The previous button.
   * @protected
   */
  createPreviousBtn_() {
    return this.createBtn_('blockly-ws-search-previous-btn', 'Find previous');
  }

  /**
   * Creates the button used for closing the search bar.
   * @returns {!HTMLButtonElement} A button for closing the search bar.
   * @protected
   */
  createCloseBtn_() {
    return this.createBtn_('blockly-ws-search-close-btn', 'Close search bar');
  }

  /**
   * Creates a button for the workspace search bar.
   * @param {string} className The class name for the button.
   * @param {string} text The text to display to the screen reader.
   * @returns {!HTMLButtonElement} The created button.
   * @private
   */
  createBtn_(className, text) {
    // Create the button
    const btn = document.createElement('button');
    core_.utils.dom.addClass(btn, className);
    btn.setAttribute('aria-label', text);
    return btn;
  }

  /**
   * creates dropdown elements.
   * @param {!Array<!String>} options Options to be added to the dropdown.
   * @returns {!HTMLSelectElement} 
   * @protected
   */
  createDropDown_(options=[]) {
    const dropDown = document.createElement('select');
    for (const optionText of options) {
      const optionElem = document.createElement('option');
      optionElem.text = optionText;
      dropDown.add(optionElem);
    }
    return dropDown;
  }

  /**
   * 
   */
  updateBlockTypeDropDown_() {
    const blocksToolkit = this.getBlocksToolkit();

    // clear the old block type options.
    this.blockTypeDropDown.innerHTML = '';

    for (const optionText of Object.keys(blocksToolkit)) {
      const optionElem = document.createElement('option');
      optionElem.text = optionText;
      this.blockTypeDropDown.add(optionElem);
    }
  }

  /**
   * 
   * @private
   */
  updateComponentTypeDropDown_() {
    const compDB = this.workspace_.getComponentDatabase();
    const componentMap = compDB.getTypeToInstanceMap();

    // clear the old component type options.
    this.componentTypeDropDown.innerHTML = '';
    // // Set the display of the component instance DD to none.
    // this.componentInstanceDropDown.style.display = 'none';

    for (const optionText of componentMap.keys()) {
      const optionElem = document.createElement('option');
      optionElem.text = optionText;
      this.componentTypeDropDown.add(optionElem);
    }
    this.updateComponentInstanceDropDown_(componentMap.keys().next().value);
  }

  /**
   * updates the componenst instance dropdown.
   * @param {string} componentType the component type selected.
   * @private
   */
  updateComponentInstanceDropDown_(componentType) {
    const compDB = this.workspace_.getComponentDatabase();
    const componentMap = compDB.getTypeToInstanceMap();
    const componentInfo = componentMap.get(componentType);
    // clear the old component type options.
    this.componentInstanceDropDown.innerHTML = '';
    // Set the display of the component instance DD to flex.
    // this.componentInstanceDropDown.style.display = 'flex';

    for (const optionText of componentInfo.get('instances')) {
      const optionElem = document.createElement('option');
      optionElem.text = optionText;
      this.componentInstanceDropDown.add(optionElem);
    }

    for (const optionText of ['any', 'all']) {
      const optionElem = document.createElement('option');
      optionElem.text = `${optionText} ${componentType}`;
      this.componentInstanceDropDown.add(optionElem);
    }
  }

  /**
   * Add event listener for clicking and keydown on the given button.
   * @param {!HTMLButtonElement} btn The button to add the event listener to.
   * @param {!Function} onClickFn The function to call when the user clicks on
   *      or hits enter on the button.
   * @private
   */
  addBtnListener_(btn, onClickFn) {
    this.addEvent_(btn, 'click', this, onClickFn);
    // TODO: Review Blockly's key handling to see if there is a way to avoid
    //  needing to call stopPropogation().
    this.addEvent_(btn, 'keydown', this, (e) => {
      if (e.key === 'Enter') {
        onClickFn(e);
        e.preventDefault();
      } else if (e.key === 'Escape') {
        this.close();
      }
      e.stopPropagation();
    });
  }

  /**
   * Returns the bounding rectangle of the UI element in pixel units relative to
   * the Blockly injection div.
   * @returns {?Blockly.utils.Rect} The component’s bounding box. Null in this
   *     case since we don't need other elements to avoid the workspace search
   *     field.
   */
  getBoundingRectangle() {
    return null;
  }

  /**
   * Positions the zoom-to-fit control.
   * It is positioned in the opposite corner to the corner the
   * categories/toolbox starts at.
   * @param {!Blockly.MetricsManager.UiMetrics} metrics The workspace metrics.
   * @param {!Array<!Blockly.utils.Rect>} savedPositions List of rectangles that
   *     are already on the workspace.
   */
  position(metrics, savedPositions) {
    if (this.workspace_.RTL) {
      this.htmlDiv_.style.left = metrics.absoluteMetrics.left + 'px';
    } else {
      if (metrics.toolboxMetrics.position === core_.TOOLBOX_AT_RIGHT) {
        this.htmlDiv_.style.right = metrics.toolboxMetrics.width + 'px';
      } else {
        this.htmlDiv_.style.right = '0';
      }
    }
    this.htmlDiv_.style.top = metrics.absoluteMetrics.top + 'px';
  }

  /**
   * Handles input value change in search bar.
   * @private
   */
  onInput_() {
    if (this.searchOnInput) {
      const inputValue = this.inputElement_.value.trim();
      if (inputValue !== this.searchText_) {
        this.searchAndHighlight(inputValue, this.preserveSelected);
      }
    }
  }

  /**
   * Handles a key down for the search bar.
   * @param {KeyboardEvent} e The key down event.
   * @private
   */
  onKeyDown_(e) {
    if (e.key === 'Escape') {
      this.close();
    } else if (e.key === 'Enter') {
      if (this.searchOnInput) {
        this.next();
      } else {
        const inputValue = this.inputElement_.value.trim();
        if (inputValue !== this.searchText_) {
          this.searchAndHighlight(inputValue, this.preserveSelected);
        }
      }
    }
  }

  /**
   * Opens the search bar when Control F or Command F are used on the workspace.
   * @param {KeyboardEvent} e The key down event.
   * @private
   */
  onWorkspaceKeyDown_(e) {
    // TODO: Look into handling keyboard shortcuts on workspace in Blockly.
    if ((e.ctrlKey || e.metaKey) && e.key === 'f') {
      this.open();
      e.preventDefault();
      e.stopPropagation();
    }
  }

  /**
   * Changes the search filter.
   * @param {string} searchType Type to search blocks.
   * @private
   */
  searchChange_(searchType) {
    this.clearBlocks();

    // Define a mapping from searchType to the class name of the element that should be displayed.
    const typeToClassMap = {
        keyword: '.blockly-ws-search-input',
        block: '.blockly-ws-block-select',
        component: '.blockly-ws-component-select'
    };

    // Hide all elements
    for (const elementClass of Object.values(typeToClassMap)) {
        document.querySelector(elementClass).style.display = 'none';
    }

    // Display the appropriate element for the given searchType.
    const elementToShow = typeToClassMap[searchType];
    if (elementToShow) {
        document.querySelector(elementToShow).style.display = 'flex';
    }
  }

  /**
   * 
   */
  searchFind_(searchType) {
    if (searchType === 'block') {
      this.updateBlockTypeDropDown_();
      this.searchAndHighlight(this.blockTypeDropDown.value, this.preserveSelected, 'block');
    } else if (searchType === 'component') {
      this.updateComponentTypeDropDown_();
      this.searchAndHighlight(this.componentInstanceDropDown.value, this.preserveSelected, 'component');
    }
  }


  /**
   * Selects the previous block.
   */
  previous() {
    this.setCurrentBlock_(this.currentBlockIndex_ - 1);
  }

  /**
   * Selects the next block.
   */
  next() {
    this.setCurrentBlock_(this.currentBlockIndex_ + 1);
  }

  /**
   * Sets the placeholder text for the search bar text input.
   * @param {string} placeholderText The placeholder text.
   */
  setSearchPlaceholder(placeholderText) {
    this.textInputPlaceholder_ = placeholderText;
    if (this.inputElement_) {
      this.inputElement_.setAttribute('placeholder',
          this.textInputPlaceholder_);
    }
  }

  /**
   * Changes the currently "selected" block and adds extra highlight.
   * @param {number} index Index of block to set as current. Number is wrapped.
   * @protected
   */
  setCurrentBlock_(index) {
    if (!this.blocks_.length) {
      return;
    }
    let currentBlock = this.blocks_[this.currentBlockIndex_];
    if (currentBlock) {
      this.unhighlightCurrentSelection_(currentBlock);
    }
    this.currentBlockIndex_ =
        (index % this.blocks_.length + this.blocks_.length) %
        this.blocks_.length;
    currentBlock = this.blocks_[this.currentBlockIndex_];

    this.highlightCurrentSelection_(currentBlock);
    this.workspace_.centerOnBlock(currentBlock.id, false);
  }

  /**
   * Opens the search bar.
   */
  open() {
    this.setVisible_(true);
    this.inputElement_.focus();
    if (this.searchText_) {
      this.searchAndHighlight(this.searchText_);
    }
  }

  /**
   * Closes the search bar.
   */
  close() {
    this.setVisible_(false);
    this.workspace_.markFocused();
    this.clearBlocks();
  }

  /**
   * Shows or hides the workspace search bar.
   * @param {boolean} show Whether to set the search bar as visible.
   * @private
   */
  setVisible_(show) {
    this.htmlDiv_.style.display = show ? 'flex' : 'none';
  }

  /**
   * 
   */
  getBlocksToolkit() {
    const tree = Blockly.Drawer.buildTree_();
    const blocksToolkit = {};
    for (let key in tree) {
      const i18nBlockTypeName = key.replace('cat_', '');
      let blockType = tree[key][0].split('_')[0];

      if (blockType === 'controls'){
        blockType = 'control';
      } else if (blockType === 'color') {
        blockType = 'colors';
      } else if (blockType === 'lexical') {
        blockType = 'variables';
      } else if (blockType === 'component') {
        continue;
      }

      blocksToolkit[i18nBlockTypeName] = blockType;
    }
    return blocksToolkit;
  }

  /**
   * Searches the workspace for the current search term and highlights matching
   * blocks.
   * @param {string} searchText The search text.
   * @param {boolean=} preserveCurrent Whether to preserve the current block
   *    if it is included in the new matching blocks.
   * 
   * ToDo: Add param for searchType.
   */
  searchAndHighlight(searchText, preserveCurrent, searchType='keyword') {
    const oldCurrentBlock = this.blocks_[this.currentBlockIndex_];
    this.searchText_ = searchText.trim();
    this.clearBlocks();

    if (searchType === 'keyword') {
      // if the search was keyword based(default)
      this.blocks_ = this.getMatchingBlocks_(this.workspace_, this.searchText_, this.caseSensitive);
    } else if (searchType === 'block') {
      // if the search was blocktype based(custom)
      // in this case searchText will contain the block type
      this.blocks_ = this.getMatchingBlockType_(this.workspace_, this.searchText_);
    } else if (searchType === 'component') {
      this.blocks_ = this.getMatchingComponent_(this.workspace_, this.searchText_, this.caseSensitive);
    } 

    this.highlightSearchGroup_(this.blocks_);
    let currentIdx = 0;
    if (preserveCurrent) {
      currentIdx = this.blocks_.indexOf(oldCurrentBlock);
      currentIdx = currentIdx > -1 ? currentIdx : 0;
    }
    this.setCurrentBlock_(currentIdx);
  }

  /**
   * Returns pool of blocks to search from.
   * @param {!Blockly.WorkspaceSvg} workspace The workspace to get blocks from.
   * @returns {!Array.<!Blockly.BlockSvg>} The search pool of blocks to use.
   * @private
   */
  getSearchPool_(workspace) {
    const blocks = (
      /** @type {!Array.<!Blockly.BlockSvg>} */
      workspace.getAllBlocks(true));
    return blocks.filter((block) => {
      // Filter out blocks contained inside of another collapsed block.
      const surroundParent = block.getSurroundParent();
      return !surroundParent || !surroundParent.isCollapsed();
    });
  }

  /**
   * Returns whether the given block matches the search text.
   * @param {!Blockly.BlockSvg} block The block to check.
   * @param {string} searchText The search text. Note if the search is case
   *    insensitive, this will be passed already converted to lowercase letters.
   * @param {boolean} caseSensitive Whether the search is caseSensitive.
   * @returns {boolean} True if the block is a match, false otherwise.
   * @protected
   */
  isBlockMatch_(block, searchText, caseSensitive) {
    let blockText = '';
    if (block.isCollapsed()) {
      // Search the whole string for collapsed blocks.
      blockText = block.toString();
    } else {
      const topBlockText = [];
      block.inputList.forEach((input) => {
        input.fieldRow.forEach((field) => {
          topBlockText.push(field.getText());
        });
      });
      blockText = topBlockText.join(' ').trim();
    }
    if (!caseSensitive) {
      blockText = blockText.toLowerCase();
    }
    return blockText.indexOf(searchText) > -1;
  }

  /**
   * Returns blocks that match the given search text.
   * @param {!Blockly.WorkspaceSvg} workspace The workspace to search.
   * @param {string} searchText The search text.
   * @param {boolean} caseSensitive Whether the search should be case sensitive.
   * @returns {!Array.<Blockly.BlockSvg>} The blocks that match the search
   *    text.
   * @protected
   */
  getMatchingBlocks_(workspace, searchText, caseSensitive) {
    if (!searchText) {
      return [];
    }
    if (!this.caseSensitive) {
      searchText = searchText.toLowerCase();
    }
    const searchGroup = this.getSearchPool_(workspace);
    return searchGroup.filter(
        (block) => this.isBlockMatch_(block, searchText, caseSensitive));
  }

  /**
   * Returns blocks that match the given block type.
   * @param {!Blockly.WorkspaceSvg} workspace The workspace to search.
   * @param {string} blockType The block type.
   * @returns {!Array.<Blockly.BlockSvg>} The blocks that match the block type.
   * @protected
   */
  getMatchingBlockType_(workspace, blockType) {
    const searchGroup = this.getSearchPool_(workspace);
    const blocksToolkit = this.getBlocksToolkit();
    blockType = blocksToolkit[blockType]; 
    return searchGroup.filter(
      (blockObj) => blockObj.category.toLowerCase() === blockType
    );
  }

  /**
   * Returns block or blocks matching a component type.
   * @param {!Blockly.WorkspaceSvg} workspace The workspace to search.
   * @param {string} componentSearchKey The component to search.
   * @returns {!Array.<Blockly.BlockSvg>} The blocks that match the block type.
   */
  getMatchingComponent_(workspace, componentSearchKey, caseSensitive){
    const searchGroup = this.getSearchPool_(workspace);
    if (componentSearchKey.indexOf('all') > -1) {
      // get the component type.
      const componentType = componentSearchKey.split(" ")[1];

      // get the english name for the component type.
      const compDB = this.workspace_.getComponentDatabase();
      const componentMap = compDB.getTypeToInstanceMap();
      const componentInfo = componentMap.get(componentType);
      const engComponentType = componentInfo.get('englishName');
      // get all the blocks which match the componentType.
      return searchGroup.filter(
        (blockObj) => blockObj.typeName === engComponentType
      );
    }
    componentSearchKey = componentSearchKey.toLowerCase();
    return searchGroup.filter(
      (blockObj) => this.isBlockMatch_(blockObj, componentSearchKey, caseSensitive));
  }

  /**
   * Clears the selection group and current block.
   */
  clearBlocks() {
    this.unhighlightSearchGroup_(this.blocks_);
    const currentBlock = this.blocks_[this.currentBlockIndex_];
    if (currentBlock) {
      this.unhighlightCurrentSelection_(currentBlock);
    }
    this.currentBlockIndex_ = -1;
    this.blocks_ = [];
  }

  /**
   * Adds "current selection" highlight to the provided block.
   * Highlights the provided block as the "current selection".
   * @param {!Blockly.BlockSvg} currentBlock The block to highlight.
   * @protected
   */
  highlightCurrentSelection_(currentBlock) {
    const path = currentBlock.pathObject.svgPath;
    core_.utils.dom.addClass(path, 'blockly-ws-search-current');
  }

  /**
   * Removes "current selection" highlight from provided block.
   * @param {Blockly.BlockSvg} currentBlock The block to unhighlight.
   * @protected
   */
  unhighlightCurrentSelection_(currentBlock) {
    const path = currentBlock.pathObject.svgPath;
    core_.utils.dom.removeClass(path, 'blockly-ws-search-current');
  }

  /**
   * Adds highlight to the provided blocks.
   * @param {!Array.<Blockly.BlockSvg>} blocks The blocks to highlight.
   * @protected
   */
  highlightSearchGroup_(blocks) {
    blocks.forEach((block) => {
      const blockPath = block.pathObject.svgPath;
      core_.utils.dom.addClass(blockPath, 'blockly-ws-search-highlight');
    });
  }

  /**
   * Removes highlight from the provided blocks.
   * @param {!Array.<Blockly.BlockSvg>} blocks The blocks to unhighlight.
   * @protected
   */
  unhighlightSearchGroup_(blocks) {
    blocks.forEach((block) => {
      const blockPath = block.pathObject.svgPath;
      core_.utils.dom.removeClass(blockPath, 'blockly-ws-search-highlight');
    });
  }
}

;// CONCATENATED MODULE: ./src/index.js
/**
 * @license
 * Copyright 2020 Google LLC
 * SPDX-License-Identifier: Apache-2.0
 */



})();

/******/ 	return __webpack_exports__;
/******/ })()
;
});
//# sourceMappingURL=index.js.map