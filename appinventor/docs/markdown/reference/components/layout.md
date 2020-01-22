---
layout: documentation
title: Layout
---

[&laquo; Back to index](index.html)
# Layout

Table of Contents:

* [HorizontalArrangement](#HorizontalArrangement)
* [HorizontalScrollArrangement](#HorizontalScrollArrangement)
* [Menu](#Menu)
* [TableArrangement](#TableArrangement)
* [VerticalArrangement](#VerticalArrangement)
* [VerticalScrollArrangement](#VerticalScrollArrangement)

## HorizontalArrangement  {#HorizontalArrangement}

### Properties  {#HorizontalArrangement-Properties}

{:.properties}

{:id="HorizontalArrangement.AlignHorizontal" .number} *AlignHorizontal*
: A number that encodes how contents of the HorizontalArrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.

{:id="HorizontalArrangement.AlignVertical" .number} *AlignVertical*
: A number that encodes how the contents of the HorizontalArrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.

{:id="HorizontalArrangement.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the HorizontalArrangement

{:id="HorizontalArrangement.Height" .number .bo} *Height*
: Specifies the vertical height of the HorizontalArrangement, measured in pixels.

{:id="HorizontalArrangement.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the HorizontalArrangement as a percentage of the height of the Screen.

{:id="HorizontalArrangement.Image" .text} *Image*
: Returns the path of the background image of the HorizontalArrangement.

{:id="HorizontalArrangement.Visible" .boolean} *Visible*
: Returns true iff the HorizontalArrangement is visible.

{:id="HorizontalArrangement.Width" .number .bo} *Width*
: Specifies the horizontal width of the HorizontalArrangement, measured in pixels.

{:id="HorizontalArrangement.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the HorizontalArrangement as a percentage of the width of the Screen.

## HorizontalScrollArrangement  {#HorizontalScrollArrangement}

### Properties  {#HorizontalScrollArrangement-Properties}

{:.properties}

{:id="HorizontalScrollArrangement.AlignHorizontal" .number} *AlignHorizontal*
: A number that encodes how contents of the HorizontalScrollArrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.

{:id="HorizontalScrollArrangement.AlignVertical" .number} *AlignVertical*
: A number that encodes how the contents of the HorizontalScrollArrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.

{:id="HorizontalScrollArrangement.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the HorizontalScrollArrangement

{:id="HorizontalScrollArrangement.Height" .number .bo} *Height*
: Specifies the vertical height of the HorizontalScrollArrangement, measured in pixels.

{:id="HorizontalScrollArrangement.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the HorizontalScrollArrangement as a percentage of the height of the Screen.

{:id="HorizontalScrollArrangement.Image" .text} *Image*
: Returns the path of the background image of the HorizontalScrollArrangement.

{:id="HorizontalScrollArrangement.Visible" .boolean} *Visible*
: Returns true iff the HorizontalScrollArrangement is visible.

{:id="HorizontalScrollArrangement.Width" .number .bo} *Width*
: Specifies the horizontal width of the HorizontalScrollArrangement, measured in pixels.

{:id="HorizontalScrollArrangement.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the HorizontalScrollArrangement as a percentage of the width of the Screen.

## Menu  {#Menu}

### Properties  {#Menu-Properties}

{:.properties}

{:id="Menu.Items" .list .ro .bo} *Items*
: Items property getter method: returns a YailList copy containing
 all items under this menu, or an empty list if no menu item exists.
 Changes to the YailList itself will have no effect on the menu, but changes
 to properties of its elements will be reflected in the corresponding item.

{:id="Menu.ShowAbout" .boolean} *ShowAbout*
: Returns true if an About option that displays additional info is shown.

{:id="Menu.ShowStop" .boolean} *ShowStop*
: Returns true if a Stop option for users to exit the app is shown.

### Events  {#Menu-Events}

{:.events}

{:id="Menu.Initialize"} Initialize()
: Menu created (occurs after screen initialization)

{:id="Menu.ItemSelected"} ItemSelected(*itemIndex*{:.number},*item*{:.component})
: Event raised when user selects an item from the options menu.

## TableArrangement  {#TableArrangement}

### Properties  {#TableArrangement-Properties}

{:.properties}

{:id="TableArrangement.Columns" .number .do} *Columns*
: Columns property getter method.

{:id="TableArrangement.Height" .number .bo} *Height*
: Specifies the vertical height of the TableArrangement, measured in pixels.

{:id="TableArrangement.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the TableArrangement as a percentage of the height of the Screen.

{:id="TableArrangement.Rows" .number .do} *Rows*
: Rows property getter method.

{:id="TableArrangement.Visible" .boolean} *Visible*
: Returns true iff the TableArrangement is visible.

{:id="TableArrangement.Width" .number .bo} *Width*
: Specifies the horizontal width of the TableArrangement, measured in pixels.

{:id="TableArrangement.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the TableArrangement as a percentage of the width of the Screen.

## VerticalArrangement  {#VerticalArrangement}

### Properties  {#VerticalArrangement-Properties}

{:.properties}

{:id="VerticalArrangement.AlignHorizontal" .number} *AlignHorizontal*
: A number that encodes how contents of the VerticalArrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.

{:id="VerticalArrangement.AlignVertical" .number} *AlignVertical*
: A number that encodes how the contents of the VerticalArrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.

{:id="VerticalArrangement.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the VerticalArrangement

{:id="VerticalArrangement.Height" .number .bo} *Height*
: Specifies the vertical height of the VerticalArrangement, measured in pixels.

{:id="VerticalArrangement.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the VerticalArrangement as a percentage of the height of the Screen.

{:id="VerticalArrangement.Image" .text} *Image*
: Returns the path of the background image of the VerticalArrangement.

{:id="VerticalArrangement.Visible" .boolean} *Visible*
: Returns true iff the VerticalArrangement is visible.

{:id="VerticalArrangement.Width" .number .bo} *Width*
: Specifies the horizontal width of the VerticalArrangement, measured in pixels.

{:id="VerticalArrangement.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the VerticalArrangement as a percentage of the width of the Screen.

## VerticalScrollArrangement  {#VerticalScrollArrangement}

### Properties  {#VerticalScrollArrangement-Properties}

{:.properties}

{:id="VerticalScrollArrangement.AlignHorizontal" .number} *AlignHorizontal*
: A number that encodes how contents of the VerticalScrollArrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.

{:id="VerticalScrollArrangement.AlignVertical" .number} *AlignVertical*
: A number that encodes how the contents of the VerticalScrollArrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.

{:id="VerticalScrollArrangement.BackgroundColor" .color} *BackgroundColor*
: Returns the background color of the VerticalScrollArrangement

{:id="VerticalScrollArrangement.Height" .number .bo} *Height*
: Specifies the vertical height of the VerticalScrollArrangement, measured in pixels.

{:id="VerticalScrollArrangement.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the VerticalScrollArrangement as a percentage of the height of the Screen.

{:id="VerticalScrollArrangement.Image" .text} *Image*
: Returns the path of the background image of the VerticalScrollArrangement.

{:id="VerticalScrollArrangement.Visible" .boolean} *Visible*
: Returns true iff the VerticalScrollArrangement is visible.

{:id="VerticalScrollArrangement.Width" .number .bo} *Width*
: Specifies the horizontal width of the VerticalScrollArrangement, measured in pixels.

{:id="VerticalScrollArrangement.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the VerticalScrollArrangement as a percentage of the width of the Screen.