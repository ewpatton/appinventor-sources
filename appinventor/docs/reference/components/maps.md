---
layout: documentation
title: Maps
---

[&laquo; Back to index](index.html)
# Maps

Table of Contents:

* [Circle](#Circle)
* [FeatureCollection](#FeatureCollection)
* [LineString](#LineString)
* [Map](#Map)
* [Marker](#Marker)
* [Polygon](#Polygon)
* [Rectangle](#Rectangle)

## Circle  {#Circle}

### Properties  {#Circle-Properties}

{:.properties}

{:id="Circle.Description"} *Description*{:.text}
: The description displayed in the info window that appears when the user clicks on the map feature.

{:id="Circle.Draggable"} *Draggable*{:.boolean}
: The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.

{:id="Circle.EnableInfobox"} *EnableInfobox*{:.boolean}
: Enable or disable the infobox window display when the user taps the feature.

{:id="Circle.FillColor"} *FillColor*{:.color}
: The paint color used to fill in the map feature.

{:id="Circle.Latitude"} *Latitude*{:.number}
: The latitude of the center of the circle.

{:id="Circle.Longitude"} *Longitude*{:.number}
: The longitude of the center of the circle.

{:id="Circle.Radius"} *Radius*{:.number}
: The radius of the circle in meters.

{:id="Circle.StrokeColor"} *StrokeColor*{:.color}
: The paint color used to outline the map feature.

{:id="Circle.StrokeWidth"} *StrokeWidth*{:.number}
: The width of the stroke used to outline the map feature.

{:id="Circle.Title"} *Title*{:.text}
: The title displayed in the info window that appears when the user clicks on the map feature.

{:id="Circle.Type"} *Type*{:.text .ro .bo}
: 

{:id="Circle.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

### Events  {#Circle-Events}

{:.events}

{:id="Circle.Click"} Click()
: The user clicked on the feature.

{:id="Circle.Drag"} Drag()
: The user dragged the map feature.

{:id="Circle.LongClick"} LongClick()
: The user long-pressed on the feature. This event will only trigger if Draggable is false.

{:id="Circle.StartDrag"} StartDrag()
: The user started a drag operation.

{:id="Circle.StopDrag"} StopDrag()
: The user stopped a drag operation.

### Methods  {#Circle-Methods}

{:.methods}

{:id="Circle.DistanceToFeature" class="method returns number"} <i/> DistanceToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Compute the distance, in meters, between two map features.

{:id="Circle.DistanceToPoint" class="method returns number"} <i/> DistanceToPoint(*latitude*{:.number},*longitude*{:.number},*centroid*{:.boolean})
: Compute the distance, in meters, between a map feature and a latitude, longitude point.

{:id="Circle.HideInfobox" class="method"} <i/> HideInfobox()
: Hide the infobox if it is shown. If the infobox is not visible this function has no effect.

{:id="Circle.SetLocation" class="method"} <i/> SetLocation(*latitude*{:.number},*longitude*{:.number})
: Set the center of the Circle.

{:id="Circle.ShowInfobox" class="method"} <i/> ShowInfobox()
: Show the infobox for the feature. This will show the infobox even if

## FeatureCollection  {#FeatureCollection}

### Properties  {#FeatureCollection-Properties}

{:.properties}

{:id="FeatureCollection.Features"} *Features*{:.list .bo}
: The list of features placed on this map. This list also includes any features created by calls to FeatureFromDescription

{:id="FeatureCollection.FeaturesFromGeoJSON"} *FeaturesFromGeoJSON*{:.text .wo}
: Loads a collection of features from the given string. If the string is not valid GeoJSON, the ErrorLoadingFeatureCollection error will be run with url = <string>.

{:id="FeatureCollection.Height"} *Height*{:.number .bo}
: Specifies the component's vertical height, measured in pixels.

{:id="FeatureCollection.HeightPercent"} *HeightPercent*{:.number .wo .bo}
: Specifies the component's vertical height as a percentage of the height of its parent Component.

{:id="FeatureCollection.Source"} *Source*{:.text .ro}
: Gets or sets the source URL used to populate the feature collection. If the feature collection was not loaded from a URL, this will be the empty string.

{:id="FeatureCollection.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

{:id="FeatureCollection.Width"} *Width*{:.number .bo}
: Specifies the component's horizontal width, measured in pixels.

{:id="FeatureCollection.WidthPercent"} *WidthPercent*{:.number .wo .bo}
: Specifies the component's horizontal width as a percentage of the Width of its parent Component.

### Events  {#FeatureCollection-Events}

{:.events}

{:id="FeatureCollection.FeatureClick"} FeatureClick(*feature*{:.component})
: The user clicked on a map feature.

{:id="FeatureCollection.FeatureDrag"} FeatureDrag(*feature*{:.component})
: The user dragged a map feature.

{:id="FeatureCollection.FeatureLongClick"} FeatureLongClick(*feature*{:.component})
: The user long-pressed on a map feature.

{:id="FeatureCollection.FeatureStartDrag"} FeatureStartDrag(*feature*{:.component})
: The user started dragging a map feature.

{:id="FeatureCollection.FeatureStopDrag"} FeatureStopDrag(*feature*{:.component})
: The user stopped dragging a map feature.

{:id="FeatureCollection.GotFeatures"} GotFeatures(*url*{:.text},*features*{:.list})
: A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.

{:id="FeatureCollection.LoadError"} LoadError(*url*{:.text},*responseCode*{:.number},*errorMessage*{:.text})
: An error was encountered while processing a GeoJSON document at the given url. The responseCode parameter will contain an HTTP status code and the errorMessage parameter will contain a detailed error message.

### Methods  {#FeatureCollection-Methods}

{:.methods}

{:id="FeatureCollection.FeatureFromDescription" class="method returns any"} <i/> FeatureFromDescription(*description*{:.list})
: Convert a feature description into an App Inventor map feature. Currently the only
 supported conversion is from a GeoJSON point to Marker component. If the feature has
 properties, they will be mapped into App Inventor properties using the following mapping:

 * description becomes Description
 * draggable becomes Draggable
 * infobox becomes EnableInfobox
 * fill becomes FillColor
 * image becomes ImageAsset
 * stroke becomes StrokeColor
 * stroke-width becomes StrokeWidth
 * title becomes Title
 * visible becomes Visible

{:id="FeatureCollection.LoadFromURL" class="method"} <i/> LoadFromURL(*url*{:.text})
: <p>Load a feature collection in <a href="https://en.wikipedia.org/wiki/GeoJSON">GeoJSON</a> format from the given url. On success, the event GotFeatures will be raised with the given url and a list of the features parsed from the GeoJSON as a list of (key, value) pairs. On failure, the LoadError event will be raised with any applicable HTTP response code and error message.</p>

## LineString  {#LineString}

### Properties  {#LineString-Properties}

{:.properties}

{:id="LineString.Description"} *Description*{:.text}
: The description displayed in the info window that appears when the user clicks on the map feature.

{:id="LineString.Draggable"} *Draggable*{:.boolean}
: The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.

{:id="LineString.EnableInfobox"} *EnableInfobox*{:.boolean}
: Enable or disable the infobox window display when the user taps the feature.

{:id="LineString.Points"} *Points*{:.list .bo}
: A list of latitude and longitude pairs that represent the line segments of the polyline.

{:id="LineString.PointsFromString"} *PointsFromString*{:.text .wo}
: 

{:id="LineString.StrokeColor"} *StrokeColor*{:.color}
: The paint color used to outline the map feature.

{:id="LineString.StrokeWidth"} *StrokeWidth*{:.number}
: The width of the stroke used to outline the map feature.

{:id="LineString.Title"} *Title*{:.text}
: The title displayed in the info window that appears when the user clicks on the map feature.

{:id="LineString.Type"} *Type*{:.text .ro .bo}
: The type of the map feature.

{:id="LineString.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

### Events  {#LineString-Events}

{:.events}

{:id="LineString.Click"} Click()
: The user clicked on the feature.

{:id="LineString.Drag"} Drag()
: The user dragged the map feature.

{:id="LineString.LongClick"} LongClick()
: The user long-pressed on the feature. This event will only trigger if Draggable is false.

{:id="LineString.StartDrag"} StartDrag()
: The user started a drag operation.

{:id="LineString.StopDrag"} StopDrag()
: The user stopped a drag operation.

### Methods  {#LineString-Methods}

{:.methods}

{:id="LineString.DistanceToFeature" class="method returns number"} <i/> DistanceToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Compute the distance, in meters, between two map features.

{:id="LineString.DistanceToPoint" class="method returns number"} <i/> DistanceToPoint(*latitude*{:.number},*longitude*{:.number},*centroid*{:.boolean})
: Compute the distance, in meters, between a map feature and a latitude, longitude point.

{:id="LineString.HideInfobox" class="method"} <i/> HideInfobox()
: Hide the infobox if it is shown. If the infobox is not visible this function has no effect.

{:id="LineString.ShowInfobox" class="method"} <i/> ShowInfobox()
: Show the infobox for the feature. This will show the infobox even if

## Map  {#Map}

### Properties  {#Map-Properties}

{:.properties}

{:id="Map.BoundingBox"} *BoundingBox*{:.list .bo}
: Bounding box for the map stored as [[North, West], [South, East]].

{:id="Map.CenterFromString"} *CenterFromString*{:.text .wo}
: <p>Set the initial center coordinate of the map. The value is specified as a comma-separated pair of decimal latitude and longitude coordinates, for example, <code>42.359144, -71.093612</code>.</p><p>In blocks code, it is recommended for performance reasons to use SetCenter with numerical latitude and longitude rather than convert to the string representation for use with this property.</p>

{:id="Map.EnablePan"} *EnablePan*{:.boolean}
: Enable two-finger panning of the Map

{:id="Map.EnableRotation"} *EnableRotation*{:.boolean}
: If set to true, the user can use multitouch gestures to rotate the map around its current center.

{:id="Map.EnableZoom"} *EnableZoom*{:.boolean}
: If this property is set to true, multitouch zoom gestures are allowed on the map. Otherwise, the map zoom cannot be changed by the user except via the zoom control buttons.

{:id="Map.Features"} *Features*{:.list .bo}
: The list of features placed on this map. This list also includes any features created by calls to FeatureFromDescription

{:id="Map.Height"} *Height*{:.number .bo}
: Specifies the component's vertical height, measured in pixels.

{:id="Map.HeightPercent"} *HeightPercent*{:.number .wo .bo}
: Specifies the component's vertical height as a percentage of the height of its parent Component.

{:id="Map.Latitude"} *Latitude*{:.number .ro .bo}
: The latitude of the center of the map.

{:id="Map.LocationSensor"} *LocationSensor*{:.component .wo}
: Uses the provided LocationSensor for user location data rather than the built-in location provider.

{:id="Map.Longitude"} *Longitude*{:.number .ro .bo}
: The longitude of the center of the map.

{:id="Map.MapType"} *MapType*{:.number}
: The type of tile layer to use as the base of the map. Valid values are: 1 (Roads), 2 (Aerial), 3 (Terrain)

{:id="Map.Rotation"} *Rotation*{:.number}
: Sets or gets the rotation of the map in decimal degrees if any

{:id="Map.ScaleUnits"} *ScaleUnits*{:.number}
: 

{:id="Map.ShowCompass"} *ShowCompass*{:.boolean}
: Show a compass icon rotated based on user orientation.

{:id="Map.ShowScale"} *ShowScale*{:.boolean}
: Shows a scale reference on the map.

{:id="Map.ShowUser"} *ShowUser*{:.boolean}
: Show the user's location on the map.

{:id="Map.ShowZoom"} *ShowZoom*{:.boolean}
: Show zoom buttons on the map.

{:id="Map.UserLatitude"} *UserLatitude*{:.number .ro .bo}
: Returns the user's latitude if ShowUser is enabled.

{:id="Map.UserLongitude"} *UserLongitude*{:.number .ro .bo}
: Returns the user's longitude if ShowUser is enabled.

{:id="Map.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

{:id="Map.Width"} *Width*{:.number .bo}
: Specifies the component's horizontal width, measured in pixels.

{:id="Map.WidthPercent"} *WidthPercent*{:.number .wo .bo}
: Specifies the component's horizontal width as a percentage of the Width of its parent Component.

{:id="Map.ZoomLevel"} *ZoomLevel*{:.number}
: The zoom level of the map. Valid values of ZoomLevel are dependent on the tile provider and the latitude and longitude of the map. For example, zoom levels are more constrained over oceans than dense city centers to conserve space for storing tiles, so valid values may be 1-7 over ocean and 1-18 over cities. Tile providers may send warning or error tiles if the zoom level is too great for the server to support.

### Events  {#Map-Events}

{:.events}

{:id="Map.BoundsChange"} BoundsChange()
: User has changed the map bounds by panning or zooming the map.

{:id="Map.DoubleTapAtPoint"} DoubleTapAtPoint(*latitude*{:.number},*longitude*{:.number})
: The user double-tapped at a point on the map. This event will be followed by a ZoomChanged event if zooming gestures are enabled and the map is not at the highest possible zoom level.

{:id="Map.FeatureClick"} FeatureClick(*feature*{:.component})
: The user clicked on a map feature.

{:id="Map.FeatureDrag"} FeatureDrag(*feature*{:.component})
: The user dragged a map feature.

{:id="Map.FeatureLongClick"} FeatureLongClick(*feature*{:.component})
: The user long-pressed on a map feature.

{:id="Map.FeatureStartDrag"} FeatureStartDrag(*feature*{:.component})
: The user started dragging a map feature.

{:id="Map.FeatureStopDrag"} FeatureStopDrag(*feature*{:.component})
: The user stopped dragging a map feature.

{:id="Map.GotFeatures"} GotFeatures(*url*{:.text},*features*{:.list})
: A GeoJSON document was successfully read from url. The features specified in the document are provided as a list in features.

{:id="Map.InvalidPoint"} InvalidPoint(*message*{:.text})
: An invalid coordinate was supplied during a maps operation. The message parameter will have more details about the issue.

{:id="Map.LoadError"} LoadError(*url*{:.text},*responseCode*{:.number},*errorMessage*{:.text})
: An error was encountered while processing a GeoJSON document at the given url. The responseCode parameter will contain an HTTP status code and the errorMessage parameter will contain a detailed error message.

{:id="Map.LongPressAtPoint"} LongPressAtPoint(*latitude*{:.number},*longitude*{:.number})
: The user long-pressed at a point on the map.

{:id="Map.Ready"} Ready()
: Map has been initialized and is ready for user interaction.

{:id="Map.TapAtPoint"} TapAtPoint(*latitude*{:.number},*longitude*{:.number})
: The user tapped at a point on the map.

{:id="Map.ZoomChange"} ZoomChange()
: User has changed the zoom level of the map.

### Methods  {#Map-Methods}

{:.methods}

{:id="Map.CreateMarker" class="method returns component"} <i/> CreateMarker(*latitude*{:.number},*longitude*{:.number})
: Create a new marker with default properties at the specified latitude and longitude.

{:id="Map.FeatureFromDescription" class="method returns any"} <i/> FeatureFromDescription(*description*{:.list})
: Convert a feature description into an App Inventor map feature. Currently the only
 supported conversion is from a GeoJSON point to Marker component. If the feature has
 properties, they will be mapped into App Inventor properties using the following mapping:

 * description becomes Description
 * draggable becomes Draggable
 * infobox becomes EnableInfobox
 * fill becomes FillColor
 * image becomes ImageAsset
 * stroke becomes StrokeColor
 * stroke-width becomes StrokeWidth
 * title becomes Title
 * visible becomes Visible

{:id="Map.LoadFromURL" class="method"} <i/> LoadFromURL(*url*{:.text})
: <p>Load a feature collection in <a href="https://en.wikipedia.org/wiki/GeoJSON">GeoJSON</a> format from the given url. On success, the event GotFeatures will be raised with the given url and a list of the features parsed from the GeoJSON as a list of (key, value) pairs. On failure, the LoadError event will be raised with any applicable HTTP response code and error message.</p>

{:id="Map.PanTo" class="method"} <i/> PanTo(*latitude*{:.number},*longitude*{:.number},*zoom*{:.number})
: Pan the map center to the given latitude and longitude and adjust the zoom level to the specified zoom.

{:id="Map.Save" class="method"} <i/> Save(*path*{:.text})
: Save the contents of the Map to the specified path.

## Marker  {#Marker}

### Properties  {#Marker-Properties}

{:.properties}

{:id="Marker.AnchorHorizontal"} *AnchorHorizontal*{:.number}
: The horizontal alignment property controls where the Marker's anchor is located relative to its width.

{:id="Marker.AnchorVertical"} *AnchorVertical*{:.number}
: The vertical alignment property controls where the Marker's anchor is located relative to its height.

{:id="Marker.Description"} *Description*{:.text}
: The description displayed in the info window that appears when the user clicks on the map feature.

{:id="Marker.Draggable"} *Draggable*{:.boolean}
: The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.

{:id="Marker.EnableInfobox"} *EnableInfobox*{:.boolean}
: Enable or disable the infobox window display when the user taps the feature.

{:id="Marker.FillColor"} *FillColor*{:.color}
: The paint color used to fill in the map feature.

{:id="Marker.Height"} *Height*{:.number .bo}
: 

{:id="Marker.HeightPercent"} *HeightPercent*{:.number .wo .bo}
: 

{:id="Marker.ImageAsset"} *ImageAsset*{:.text}
: The ImageAsset property is used to provide an alternative image for the Marker.

{:id="Marker.Latitude"} *Latitude*{:.number}
: 

{:id="Marker.Longitude"} *Longitude*{:.number}
: 

{:id="Marker.StrokeColor"} *StrokeColor*{:.color}
: The paint color used to outline the map feature.

{:id="Marker.StrokeWidth"} *StrokeWidth*{:.number}
: The width of the stroke used to outline the map feature.

{:id="Marker.Title"} *Title*{:.text}
: The title displayed in the info window that appears when the user clicks on the map feature.

{:id="Marker.Type"} *Type*{:.text .ro .bo}
: 

{:id="Marker.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

{:id="Marker.Width"} *Width*{:.number .bo}
: 

{:id="Marker.WidthPercent"} *WidthPercent*{:.number .wo .bo}
: 

### Events  {#Marker-Events}

{:.events}

{:id="Marker.Click"} Click()
: The user clicked on the feature.

{:id="Marker.Drag"} Drag()
: The user dragged the map feature.

{:id="Marker.LongClick"} LongClick()
: The user long-pressed on the feature. This event will only trigger if Draggable is false.

{:id="Marker.StartDrag"} StartDrag()
: The user started a drag operation.

{:id="Marker.StopDrag"} StopDrag()
: The user stopped a drag operation.

### Methods  {#Marker-Methods}

{:.methods}

{:id="Marker.BearingToFeature" class="method returns number"} <i/> BearingToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Returns the bearing from the Marker to the given map feature, in degrees from due north. If the centroids parameter is true, the bearing will be to the center of the map feature. Otherwise, the bearing will be computed to the point in the feature nearest the Marker.

{:id="Marker.BearingToPoint" class="method returns number"} <i/> BearingToPoint(*latitude*{:.number},*longitude*{:.number})
: Returns the bearing from the Marker to the given latitude and longitude, in degrees from due north.

{:id="Marker.DistanceToFeature" class="method returns number"} <i/> DistanceToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Compute the distance, in meters, between two map features.

{:id="Marker.DistanceToPoint" class="method returns number"} <i/> DistanceToPoint(*latitude*{:.number},*longitude*{:.number})
: Compute the distance, in meters, between a map feature and a latitude, longitude point.

{:id="Marker.HideInfobox" class="method"} <i/> HideInfobox()
: Hide the infobox if it is shown. If the infobox is not visible this function has no effect.

{:id="Marker.SetLocation" class="method"} <i/> SetLocation(*latitude*{:.number},*longitude*{:.number})
: Set the location of the marker.

{:id="Marker.ShowInfobox" class="method"} <i/> ShowInfobox()
: Show the infobox for the feature. This will show the infobox even if

## Polygon  {#Polygon}

### Properties  {#Polygon-Properties}

{:.properties}

{:id="Polygon.Description"} *Description*{:.text}
: The description displayed in the info window that appears when the user clicks on the map feature.

{:id="Polygon.Draggable"} *Draggable*{:.boolean}
: The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.

{:id="Polygon.EnableInfobox"} *EnableInfobox*{:.boolean}
: Enable or disable the infobox window display when the user taps the feature.

{:id="Polygon.FillColor"} *FillColor*{:.color}
: The paint color used to fill in the map feature.

{:id="Polygon.HolePoints"} *HolePoints*{:.list .bo}
: 

{:id="Polygon.HolePointsFromString"} *HolePointsFromString*{:.text .wo}
: Constructs holes in a polygon from a given list of coordinates per hole.

{:id="Polygon.Points"} *Points*{:.list .bo}
: Gets or sets the sequence of points used to draw the polygon.

{:id="Polygon.PointsFromString"} *PointsFromString*{:.text .wo}
: Constructs a polygon from the given list of coordinates.

{:id="Polygon.StrokeColor"} *StrokeColor*{:.color}
: The paint color used to outline the map feature.

{:id="Polygon.StrokeWidth"} *StrokeWidth*{:.number}
: The width of the stroke used to outline the map feature.

{:id="Polygon.Title"} *Title*{:.text}
: The title displayed in the info window that appears when the user clicks on the map feature.

{:id="Polygon.Type"} *Type*{:.text .ro .bo}
: The type of the feature. For polygons, this returns the text "Polygon".

{:id="Polygon.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

### Events  {#Polygon-Events}

{:.events}

{:id="Polygon.Click"} Click()
: The user clicked on the feature.

{:id="Polygon.Drag"} Drag()
: The user dragged the map feature.

{:id="Polygon.LongClick"} LongClick()
: The user long-pressed on the feature. This event will only trigger if Draggable is false.

{:id="Polygon.StartDrag"} StartDrag()
: The user started a drag operation.

{:id="Polygon.StopDrag"} StopDrag()
: The user stopped a drag operation.

### Methods  {#Polygon-Methods}

{:.methods}

{:id="Polygon.Centroid" class="method returns list"} <i/> Centroid()
: Returns the centroid of the Polygon as a (latitude, longitude) pair.

{:id="Polygon.DistanceToFeature" class="method returns number"} <i/> DistanceToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Compute the distance, in meters, between two map features.

{:id="Polygon.DistanceToPoint" class="method returns number"} <i/> DistanceToPoint(*latitude*{:.number},*longitude*{:.number},*centroid*{:.boolean})
: Compute the distance, in meters, between a map feature and a latitude, longitude point.

{:id="Polygon.HideInfobox" class="method"} <i/> HideInfobox()
: Hide the infobox if it is shown. If the infobox is not visible this function has no effect.

{:id="Polygon.ShowInfobox" class="method"} <i/> ShowInfobox()
: Show the infobox for the feature. This will show the infobox even if

## Rectangle  {#Rectangle}

### Properties  {#Rectangle-Properties}

{:.properties}

{:id="Rectangle.Description"} *Description*{:.text}
: The description displayed in the info window that appears when the user clicks on the map feature.

{:id="Rectangle.Draggable"} *Draggable*{:.boolean}
: The Draggable property is used to set whether or not the user can drag the Marker by long-pressing and then dragging the marker to a new location.

{:id="Rectangle.EastLongitude"} *EastLongitude*{:.number}
: 

{:id="Rectangle.EnableInfobox"} *EnableInfobox*{:.boolean}
: Enable or disable the infobox window display when the user taps the feature.

{:id="Rectangle.FillColor"} *FillColor*{:.color}
: The paint color used to fill in the map feature.

{:id="Rectangle.NorthLatitude"} *NorthLatitude*{:.number}
: 

{:id="Rectangle.SouthLatitude"} *SouthLatitude*{:.number}
: 

{:id="Rectangle.StrokeColor"} *StrokeColor*{:.color}
: The paint color used to outline the map feature.

{:id="Rectangle.StrokeWidth"} *StrokeWidth*{:.number}
: The width of the stroke used to outline the map feature.

{:id="Rectangle.Title"} *Title*{:.text}
: The title displayed in the info window that appears when the user clicks on the map feature.

{:id="Rectangle.Type"} *Type*{:.text .ro .bo}
: The type of the feature. For rectangles, this returns the text "Rectangle".

{:id="Rectangle.Visible"} *Visible*{:.boolean}
: Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.

{:id="Rectangle.WestLongitude"} *WestLongitude*{:.number}
: 

### Events  {#Rectangle-Events}

{:.events}

{:id="Rectangle.Click"} Click()
: The user clicked on the feature.

{:id="Rectangle.Drag"} Drag()
: The user dragged the map feature.

{:id="Rectangle.LongClick"} LongClick()
: The user long-pressed on the feature. This event will only trigger if Draggable is false.

{:id="Rectangle.StartDrag"} StartDrag()
: The user started a drag operation.

{:id="Rectangle.StopDrag"} StopDrag()
: The user stopped a drag operation.

### Methods  {#Rectangle-Methods}

{:.methods}

{:id="Rectangle.Bounds" class="method returns list"} <i/> Bounds()
: Returns the bounding box of the Rectangle in the format ((North West) (South East)).

{:id="Rectangle.Center" class="method returns list"} <i/> Center()
: Returns the center of the Rectangle as a list of the form (Latitude Longitude).

{:id="Rectangle.DistanceToFeature" class="method returns number"} <i/> DistanceToFeature(*mapFeature*{:.component},*centroids*{:.boolean})
: Compute the distance, in meters, between two map features.

{:id="Rectangle.DistanceToPoint" class="method returns number"} <i/> DistanceToPoint(*latitude*{:.number},*longitude*{:.number},*centroid*{:.boolean})
: Compute the distance, in meters, between a map feature and a latitude, longitude point.

{:id="Rectangle.HideInfobox" class="method"} <i/> HideInfobox()
: Hide the infobox if it is shown. If the infobox is not visible this function has no effect.

{:id="Rectangle.SetCenter" class="method"} <i/> SetCenter(*latitude*{:.number},*longitude*{:.number})
: Moves the Rectangle so that it is centered on the given latitude and longitude while attempting to maintain the width and height of the Rectangle as measured from the center to the edges.

{:id="Rectangle.ShowInfobox" class="method"} <i/> ShowInfobox()
: Show the infobox for the feature. This will show the infobox even if