---
layout: documentation
title: Drawing and Animation
---

[&laquo; Back to index](index.html)
# Drawing and Animation

Table of Contents:

* [Ball](#Ball)
* [Canvas](#Canvas)
* [ImageSprite](#ImageSprite)

## Ball  {#Ball}

### Properties  {#Ball-Properties}

{:.properties}

{:id="Ball.Enabled"} *Enabled*{:.boolean}
: Controls whether the sprite moves when its speed is non-zero.

{:id="Ball.Heading"} *Heading*{:.number}
: Returns the sprite's heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.

{:id="Ball.Interval"} *Interval*{:.number}
: The interval in milliseconds at which the sprite's position is updated.  For example, if the interval is 50 and the speed is 10, then the sprite will move 10 pixels every 50 milliseconds.

{:id="Ball.PaintColor"} *PaintColor*{:.color}
: 

{:id="Ball.Radius"} *Radius*{:.number}
: 

{:id="Ball.Speed"} *Speed*{:.number}
: he speed at which the sprite moves.  The sprite moves this many pixels every interval.

{:id="Ball.Visible"} *Visible*{:.boolean}
: True if the sprite is visible.

{:id="Ball.X"} *X*{:.number}
: The horizontal coordinate of the left edge of the sprite, increasing as the sprite moves to the right.

{:id="Ball.Y"} *Y*{:.number}
: The vertical coordinate of the top of the sprite, increasing as the sprite moves down.

{:id="Ball.Z"} *Z*{:.number}
: How the sprite should be layered relative to other sprits, with higher-numbered layers in front of lower-numbered layers.

### Events  {#Ball-Events}

{:.events}

{:id="Ball.CollidedWith"} CollidedWith(*other*{:.component})
: Handler for CollidedWith events, called when two sprites collide.
 Note that checking for collisions with a rotated ImageSprite currently
 checks against the sprite's unrotated position.  Therefore, collision
 checking will be inaccurate for tall narrow or short wide sprites that are
 rotated.

{:id="Ball.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number})
: Handler for Dragged events.  On all calls, the starting coordinates
 are where the screen was first touched, and the "current" coordinates
 describe the endpoint of the current line segment.  On the first call
 within a given drag, the "previous" coordinates are the same as the
 starting coordinates; subsequently, they are the "current" coordinates
 from the prior call.  Note that the Sprite won't actually move
 anywhere in response to the Dragged event unless MoveTo is
 specifically called.

{:id="Ball.EdgeReached"} EdgeReached(*edge*{:.number})
: Event handler called when the sprite reaches an edge of the screen. If Bounce is then called with that edge, the sprite will appear to bounce off of the edge it reached.  Edge here is represented as an integer that indicates one of eight directions north(1), northeast(2), east(3), southeast(4), south (-1), southwest(-2), west(-3), and northwest(-4).

{:id="Ball.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number})
: When a fling gesture (quick swipe) is made on the sprite: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector.

{:id="Ball.NoLongerCollidingWith"} NoLongerCollidingWith(*other*{:.component})
: Event indicating that a pair of sprites are no longer colliding.

{:id="Ball.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the sprite (places finger on sprite and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Ball.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the sprite (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Ball.Touched"} Touched(*x*{:.number},*y*{:.number})
: When the user touches the sprite and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas

### Methods  {#Ball-Methods}

{:.methods}

{:id="Ball.Bounce" class="method"} <i/> Bounce(*edge*{:.number})
: Makes this sprite bounce, as if off a wall.  For normal bouncing, the edge argument should be the one returned by EdgeReached.

{:id="Ball.CollidingWith" class="method returns boolean"} <i/> CollidingWith(*other*{:.component})
: Indicates whether a collision has been registered between this sprite
 and the passed sprite.

{:id="Ball.MoveIntoBounds" class="method"} <i/> MoveIntoBounds()
: Moves the sprite back in bounds if part of it extends out of bounds,
 having no effect otherwise. If the sprite is too wide to fit on the
 canvas, this aligns the left side of the sprite with the left side of the
 canvas. If the sprite is too tall to fit on the canvas, this aligns the
 top side of the sprite with the top side of the canvas.

{:id="Ball.MoveTo" class="method"} <i/> MoveTo(*x*{:.number},*y*{:.number})
: Moves the sprite so that its left top corner is at the specfied x and y coordinates.

{:id="Ball.PointInDirection" class="method"} <i/> PointInDirection(*x*{:.number},*y*{:.number})
: Turns the sprite to point towards the point with coordinates as (x, y).

{:id="Ball.PointTowards" class="method"} <i/> PointTowards(*target*{:.component})
: Turns the sprite to point towards a designated target sprite. The new heading will be parallel to the line joining the centerpoints of the two sprites.

## Canvas  {#Canvas}

### Properties  {#Canvas-Properties}

{:.properties}

{:id="Canvas.BackgroundColor"} *BackgroundColor*{:.color}
: The color of the canvas background.

{:id="Canvas.BackgroundImage"} *BackgroundImage*{:.text}
: The name of a file containing the background image for the canvas

{:id="Canvas.FontSize"} *FontSize*{:.number}
: The font size of text drawn on the canvas.

{:id="Canvas.Height"} *Height*{:.number .bo}
: Specifies the component's vertical height, measured in pixels.

{:id="Canvas.HeightPercent"} *HeightPercent*{:.number .wo .bo}
: Specifies the component's vertical height as a percentage of the height of its parent Component.

{:id="Canvas.LineWidth"} *LineWidth*{:.number}
: The width of lines drawn on the canvas.

{:id="Canvas.PaintColor"} *PaintColor*{:.color}
: The color in which lines are drawn

{:id="Canvas.TextAlignment"} *TextAlignment*{:.number}
: Determines the alignment of the text drawn by DrawText() or DrawAngle() with respect to the point specified by that command: point at the left of the text, point at the center of the text, or point at the right of the text.

{:id="Canvas.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

{:id="Canvas.Width"} *Width*{:.number .bo}
: Specifies the component's horizontal width, measured in pixels.

{:id="Canvas.WidthPercent"} *WidthPercent*{:.number .wo .bo}
: Specifies the component's horizontal width as a percentage of the Width of its parent Component.

### Events  {#Canvas-Events}

{:.events}

{:id="Canvas.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number},*draggedAnySprite*{:.boolean})
: When the user does a drag from one point (prevX, prevY) to
 another (x, y).  The pair (startX, startY) indicates where the
 user first touched the screen, and "draggedAnySprite" indicates whether a
 sprite is being dragged.

{:id="Canvas.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number},*flungSprite*{:.boolean})
: When a fling gesture (quick swipe) is made on the canvas: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector. The value "flungSprite" is true if a sprite
 was located near the the starting point of the fling gesture.

{:id="Canvas.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the canvas (places finger on canvas and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Canvas.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the canvas (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Canvas.Touched"} Touched(*x*{:.number},*y*{:.number},*touchedAnySprite*{:.boolean})
: When the user touches the canvas and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas.  TouchedAnySprite
 is true if the same touch also touched a sprite, and false otherwise.

### Methods  {#Canvas-Methods}

{:.methods}

{:id="Canvas.Clear" class="method"} <i/> Clear()
: Clears anything drawn on this Canvas but not any background color or image.

{:id="Canvas.DrawArc" class="method"} <i/> DrawArc(*left*{:.number},*top*{:.number},*right*{:.number},*bottom*{:.number},*startAngle*{:.number},*sweepAngle*{:.number},*useCenter*{:.boolean},*fill*{:.boolean})
: Draw an arc on Canvas, by drawing an arc from a specified oval (specified by left, top, right & bottom). Start angle is 0 when heading to the right, and increase when rotate clockwise. When useCenter is true, a sector will be drawed instead of an arc. When fill is true, a filled arc (or sector) will be drawed instead of just an outline.

{:id="Canvas.DrawCircle" class="method"} <i/> DrawCircle(*centerX*{:.number},*centerY*{:.number},*radius*{:.number},*fill*{:.boolean})
: Draws a circle (filled in) with the given radius centered at the given coordinates on the canvas

{:id="Canvas.DrawLine" class="method"} <i/> DrawLine(*x1*{:.number},*y1*{:.number},*x2*{:.number},*y2*{:.number})
: Draws a line between the given coordinates on the canvas.

{:id="Canvas.DrawPoint" class="method"} <i/> DrawPoint(*x*{:.number},*y*{:.number})
: Draws a point at the given coordinates on the canvas.

{:id="Canvas.DrawShape" class="method"} <i/> DrawShape(*pointList*{:.list},*fill*{:.boolean})
: Draws a shape on the canvas. pointList should be a list contains sub-lists with two number which represents a coordinate. The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3)) When fill is true, the shape will be filled.

{:id="Canvas.DrawText" class="method"} <i/> DrawText(*text*{:.text},*x*{:.number},*y*{:.number})
: Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.

{:id="Canvas.DrawTextAtAngle" class="method"} <i/> DrawTextAtAngle(*text*{:.text},*x*{:.number},*y*{:.number},*angle*{:.number})
: Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.

{:id="Canvas.GetBackgroundPixelColor" class="method returns color"} <i/> GetBackgroundPixelColor(*x*{:.number},*y*{:.number})
: Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.

{:id="Canvas.GetPixelColor" class="method returns color"} <i/> GetPixelColor(*x*{:.number},*y*{:.number})
: Gets the color of the specified point.

{:id="Canvas.Save" class="method returns text"} <i/> Save()
: Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.

{:id="Canvas.SaveAs" class="method returns text"} <i/> SaveAs(*fileName*{:.text})
: Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.

{:id="Canvas.SetBackgroundPixelColor" class="method"} <i/> SetBackgroundPixelColor(*x*{:.number},*y*{:.number},*color*{:.color})
: Sets the color of the specified point. This differs from DrawPoint by having an argument for color.

## ImageSprite  {#ImageSprite}

### Properties  {#ImageSprite-Properties}

{:.properties}

{:id="ImageSprite.Enabled"} *Enabled*{:.boolean}
: Controls whether the sprite moves when its speed is non-zero.

{:id="ImageSprite.Heading"} *Heading*{:.number}
: Returns the sprite's heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.

{:id="ImageSprite.Height"} *Height*{:.number .bo}
: 

{:id="ImageSprite.Interval"} *Interval*{:.number}
: The interval in milliseconds at which the sprite's position is updated.  For example, if the interval is 50 and the speed is 10, then the sprite will move 10 pixels every 50 milliseconds.

{:id="ImageSprite.Picture"} *Picture*{:.text}
: The picture that determines the sprite's appearence

{:id="ImageSprite.Rotates"} *Rotates*{:.boolean}
: If true, the sprite image rotates to match the sprite's heading. If false, the sprite image does not rotate when the sprite changes heading. The sprite rotates around its centerpoint.

{:id="ImageSprite.Speed"} *Speed*{:.number}
: he speed at which the sprite moves.  The sprite moves this many pixels every interval.

{:id="ImageSprite.Visible"} *Visible*{:.boolean}
: True if the sprite is visible.

{:id="ImageSprite.Width"} *Width*{:.number .bo}
: 

{:id="ImageSprite.X"} *X*{:.number}
: The horizontal coordinate of the left edge of the sprite, increasing as the sprite moves to the right.

{:id="ImageSprite.Y"} *Y*{:.number}
: The vertical coordinate of the top of the sprite, increasing as the sprite moves down.

{:id="ImageSprite.Z"} *Z*{:.number}
: How the sprite should be layered relative to other sprits, with higher-numbered layers in front of lower-numbered layers.

### Events  {#ImageSprite-Events}

{:.events}

{:id="ImageSprite.CollidedWith"} CollidedWith(*other*{:.component})
: Handler for CollidedWith events, called when two sprites collide.
 Note that checking for collisions with a rotated ImageSprite currently
 checks against the sprite's unrotated position.  Therefore, collision
 checking will be inaccurate for tall narrow or short wide sprites that are
 rotated.

{:id="ImageSprite.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number})
: Handler for Dragged events.  On all calls, the starting coordinates
 are where the screen was first touched, and the "current" coordinates
 describe the endpoint of the current line segment.  On the first call
 within a given drag, the "previous" coordinates are the same as the
 starting coordinates; subsequently, they are the "current" coordinates
 from the prior call.  Note that the Sprite won't actually move
 anywhere in response to the Dragged event unless MoveTo is
 specifically called.

{:id="ImageSprite.EdgeReached"} EdgeReached(*edge*{:.number})
: Event handler called when the sprite reaches an edge of the screen. If Bounce is then called with that edge, the sprite will appear to bounce off of the edge it reached.  Edge here is represented as an integer that indicates one of eight directions north(1), northeast(2), east(3), southeast(4), south (-1), southwest(-2), west(-3), and northwest(-4).

{:id="ImageSprite.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number})
: When a fling gesture (quick swipe) is made on the sprite: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector.

{:id="ImageSprite.NoLongerCollidingWith"} NoLongerCollidingWith(*other*{:.component})
: Event indicating that a pair of sprites are no longer colliding.

{:id="ImageSprite.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the sprite (places finger on sprite and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="ImageSprite.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the sprite (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="ImageSprite.Touched"} Touched(*x*{:.number},*y*{:.number})
: When the user touches the sprite and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas

### Methods  {#ImageSprite-Methods}

{:.methods}

{:id="ImageSprite.Bounce" class="method"} <i/> Bounce(*edge*{:.number})
: Makes this sprite bounce, as if off a wall.  For normal bouncing, the edge argument should be the one returned by EdgeReached.

{:id="ImageSprite.CollidingWith" class="method returns boolean"} <i/> CollidingWith(*other*{:.component})
: Indicates whether a collision has been registered between this sprite
 and the passed sprite.

{:id="ImageSprite.MoveIntoBounds" class="method"} <i/> MoveIntoBounds()
: Moves the sprite back in bounds if part of it extends out of bounds,
 having no effect otherwise. If the sprite is too wide to fit on the
 canvas, this aligns the left side of the sprite with the left side of the
 canvas. If the sprite is too tall to fit on the canvas, this aligns the
 top side of the sprite with the top side of the canvas.

{:id="ImageSprite.MoveTo" class="method"} <i/> MoveTo(*x*{:.number},*y*{:.number})
: Moves the sprite so that its left top corner is at the specfied x and y coordinates.

{:id="ImageSprite.PointInDirection" class="method"} <i/> PointInDirection(*x*{:.number},*y*{:.number})
: Turns the sprite to point towards the point with coordinates as (x, y).

{:id="ImageSprite.PointTowards" class="method"} <i/> PointTowards(*target*{:.component})
: Turns the sprite to point towards a designated target sprite. The new heading will be parallel to the line joining the centerpoints of the two sprites.