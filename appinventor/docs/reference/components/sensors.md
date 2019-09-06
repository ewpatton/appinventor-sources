---
layout: documentation
title: Sensors
---

[&laquo; Back to index](index.html)
# Sensors

Table of Contents:

* [AccelerometerSensor](#AccelerometerSensor)
* [BarcodeScanner](#BarcodeScanner)
* [Clock](#Clock)
* [GyroscopeSensor](#GyroscopeSensor)
* [LocationSensor](#LocationSensor)
* [NearField](#NearField)
* [OrientationSensor](#OrientationSensor)
* [Pedometer](#Pedometer)
* [ProximitySensor](#ProximitySensor)

## AccelerometerSensor  {#AccelerometerSensor}

### Properties  {#AccelerometerSensor-Properties}

{:.properties}

{:id="AccelerometerSensor.Available"} *Available*{:.boolean .ro .bo}
: 

{:id="AccelerometerSensor.Enabled"} *Enabled*{:.boolean}
: 

{:id="AccelerometerSensor.LegacyMode"} *LegacyMode*{:.boolean .wo .do}
: Prior to the release that added this property the AccelerometerSensor component passed through sensor values directly as received from the Android system. However these values do not compensate for tablets that default to Landscape mode, requiring the MIT App Inventor programmer to compensate. However compensating would result in incorrect results in Portrait mode devices such as phones. We now detect Landscape mode tablets and perform the compensation. However if your project is already compensating for the change, you will now get incorrect results. Although our preferred solution is for you to update your project, you can also just set this property to “true” and our compensation code will be deactivated. Note: We recommend that you update your project as we may remove this property in a future release.

{:id="AccelerometerSensor.MinimumInterval"} *MinimumInterval*{:.number}
: The minimum interval, in milliseconds, between phone shakes

{:id="AccelerometerSensor.Sensitivity"} *Sensitivity*{:.number}
: A number that encodes how sensitive the accelerometer is. The choices are: 1 = weak, 2 = moderate,  3 = strong.

{:id="AccelerometerSensor.XAccel"} *XAccel*{:.number .ro .bo}
: 

{:id="AccelerometerSensor.YAccel"} *YAccel*{:.number .ro .bo}
: 

{:id="AccelerometerSensor.ZAccel"} *ZAccel*{:.number .ro .bo}
: 

### Events  {#AccelerometerSensor-Events}

{:.events}

{:id="AccelerometerSensor.AccelerationChanged"} AccelerationChanged(*xAccel*{:.number},*yAccel*{:.number},*zAccel*{:.number})
: Indicates the acceleration changed in the X, Y, and/or Z dimensions.

{:id="AccelerometerSensor.Shaking"} Shaking()
: Indicates the device started being shaken or continues to be shaken.

## BarcodeScanner  {#BarcodeScanner}

### Properties  {#BarcodeScanner-Properties}

{:.properties}

{:id="BarcodeScanner.Result"} *Result*{:.text .ro .bo}
: Text result of the previous scan.

{:id="BarcodeScanner.UseExternalScanner"} *UseExternalScanner*{:.boolean}
: If true App Inventor will look for and use an external scanning program such as "Bar Code Scanner."

### Events  {#BarcodeScanner-Events}

{:.events}

{:id="BarcodeScanner.AfterScan"} AfterScan(*result*{:.text})
: Indicates that the scanner has read a (text) result and provides the result

### Methods  {#BarcodeScanner-Methods}

{:.methods}

{:id="BarcodeScanner.DoScan" class="method"} <i/> DoScan()
: Begins a barcode scan, using the camera. When the scan is complete, the AfterScan event will be raised.

## Clock  {#Clock}

### Properties  {#Clock-Properties}

{:.properties}

{:id="Clock.TimerAlwaysFires"} *TimerAlwaysFires*{:.boolean}
: Will fire even when application is not showing on the screen if true

{:id="Clock.TimerEnabled"} *TimerEnabled*{:.boolean}
: Fires timer if true

{:id="Clock.TimerInterval"} *TimerInterval*{:.number}
: Interval between timer events in ms

### Events  {#Clock-Events}

{:.events}

{:id="Clock.Timer"} Timer()
: Timer has gone off.

### Methods  {#Clock-Methods}

{:.methods}

{:id="Clock.AddDays" class="method returns InstantInTime"} <i/> AddDays(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some days after the argument

{:id="Clock.AddDuration" class="method returns InstantInTime"} <i/> AddDuration(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some duration after the argument

{:id="Clock.AddHours" class="method returns InstantInTime"} <i/> AddHours(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some hours after the argument

{:id="Clock.AddMinutes" class="method returns InstantInTime"} <i/> AddMinutes(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some minutes after the argument

{:id="Clock.AddMonths" class="method returns InstantInTime"} <i/> AddMonths(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some months after the argument

{:id="Clock.AddSeconds" class="method returns InstantInTime"} <i/> AddSeconds(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some seconds after the argument

{:id="Clock.AddWeeks" class="method returns InstantInTime"} <i/> AddWeeks(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some weeks after the argument

{:id="Clock.AddYears" class="method returns InstantInTime"} <i/> AddYears(*instant*{:.InstantInTime},*quantity*{:.number})
: An instant in time some years after the argument

{:id="Clock.DayOfMonth" class="method returns number"} <i/> DayOfMonth(*instant*{:.InstantInTime})
: The day of the month

{:id="Clock.Duration" class="method returns number"} <i/> Duration(*start*{:.InstantInTime},*end*{:.InstantInTime})
: Milliseconds elapsed between instants

{:id="Clock.DurationToDays" class="method returns number"} <i/> DurationToDays(*duration*{:.number})
: convert duration to days

{:id="Clock.DurationToHours" class="method returns number"} <i/> DurationToHours(*duration*{:.number})
: convert duration to hours

{:id="Clock.DurationToMinutes" class="method returns number"} <i/> DurationToMinutes(*duration*{:.number})
: convert duration to minutes

{:id="Clock.DurationToSeconds" class="method returns number"} <i/> DurationToSeconds(*duration*{:.number})
: convert duration to seconds

{:id="Clock.DurationToWeeks" class="method returns number"} <i/> DurationToWeeks(*duration*{:.number})
: convert duration to weeks

{:id="Clock.FormatDate" class="method returns text"} <i/> FormatDate(*instant*{:.InstantInTime},*pattern*{:.text})
: Text representing the date of an instant in the specified pattern

{:id="Clock.FormatDateTime" class="method returns text"} <i/> FormatDateTime(*instant*{:.InstantInTime},*pattern*{:.text})
: Text representing the date and time of an instant in the specified pattern

{:id="Clock.FormatTime" class="method returns text"} <i/> FormatTime(*instant*{:.InstantInTime})
: Text representing the time of an instant

{:id="Clock.GetMillis" class="method returns number"} <i/> GetMillis(*instant*{:.InstantInTime})
: The instant in time measured as milliseconds since 1970.

{:id="Clock.Hour" class="method returns number"} <i/> Hour(*instant*{:.InstantInTime})
: The hour of the day

{:id="Clock.MakeDate" class="method returns InstantInTime"} <i/> MakeDate(*year*{:.number},*month*{:.number},*day*{:.number})
: Allows the user to set the clock to be a date value.
Valid values for the month field are 1-12 and 1-31 for the day field.

{:id="Clock.MakeInstant" class="method returns InstantInTime"} <i/> MakeInstant(*from*{:.text})
: An instant in time specified by MM/dd/YYYY hh:mm:ss or MM/dd/YYYY or hh:mm

{:id="Clock.MakeInstantFromMillis" class="method returns InstantInTime"} <i/> MakeInstantFromMillis(*millis*{:.number})
: An instant in time specified by the milliseconds since 1970.

{:id="Clock.MakeInstantFromParts" class="method returns InstantInTime"} <i/> MakeInstantFromParts(*year*{:.number},*month*{:.number},*day*{:.number},*hour*{:.number},*minute*{:.number},*second*{:.number})
: Allows the user to set the date and time to be displayed when the clock opens.
Valid values for the month field are 1-12 and 1-31 for the day field.

{:id="Clock.MakeTime" class="method returns InstantInTime"} <i/> MakeTime(*hour*{:.number},*minute*{:.number},*second*{:.number})
: Allows the user to set the time of the clock - Valid format is hh:mm:ss

{:id="Clock.Minute" class="method returns number"} <i/> Minute(*instant*{:.InstantInTime})
: The minute of the hour

{:id="Clock.Month" class="method returns number"} <i/> Month(*instant*{:.InstantInTime})
: The month of the year represented as a number from 1 to 12)

{:id="Clock.MonthName" class="method returns text"} <i/> MonthName(*instant*{:.InstantInTime})
: The name of the month

{:id="Clock.Now" class="method returns InstantInTime"} <i/> Now()
: The current instant in time read from phone's clock

{:id="Clock.Second" class="method returns number"} <i/> Second(*instant*{:.InstantInTime})
: The second of the minute

{:id="Clock.SystemTime" class="method returns number"} <i/> SystemTime()
: The phone's internal time

{:id="Clock.Weekday" class="method returns number"} <i/> Weekday(*instant*{:.InstantInTime})
: The day of the week represented as a number from 1 (Sunday) to 7 (Saturday)

{:id="Clock.WeekdayName" class="method returns text"} <i/> WeekdayName(*instant*{:.InstantInTime})
: The name of the day of the week

{:id="Clock.Year" class="method returns number"} <i/> Year(*instant*{:.InstantInTime})
: The year

## GyroscopeSensor  {#GyroscopeSensor}

### Properties  {#GyroscopeSensor-Properties}

{:.properties}

{:id="GyroscopeSensor.Available"} *Available*{:.boolean .ro .bo}
: Indicates whether a gyroscope sensor is available.

{:id="GyroscopeSensor.Enabled"} *Enabled*{:.boolean}
: If enabled, then sensor events will be generated and XAngularVelocity, YAngularVelocity, and ZAngularVelocity properties will have meaningful values.

{:id="GyroscopeSensor.XAngularVelocity"} *XAngularVelocity*{:.number .ro .bo}
: The angular velocity around the X axis, in degrees per second.

{:id="GyroscopeSensor.YAngularVelocity"} *YAngularVelocity*{:.number .ro .bo}
: The angular velocity around the Y axis, in degrees per second.

{:id="GyroscopeSensor.ZAngularVelocity"} *ZAngularVelocity*{:.number .ro .bo}
: The angular velocity around the Z axis, in degrees per second.

### Events  {#GyroscopeSensor-Events}

{:.events}

{:id="GyroscopeSensor.GyroscopeChanged"} GyroscopeChanged(*xAngularVelocity*{:.number},*yAngularVelocity*{:.number},*zAngularVelocity*{:.number},*timestamp*{:.number})
: Indicates that the gyroscope sensor data has changed. The timestamp parameter is the time in nanoseconds at which the event occurred.

## LocationSensor  {#LocationSensor}

### Properties  {#LocationSensor-Properties}

{:.properties}

{:id="LocationSensor.Accuracy"} *Accuracy*{:.number .ro .bo}
: 

{:id="LocationSensor.Altitude"} *Altitude*{:.number .ro .bo}
: 

{:id="LocationSensor.AvailableProviders"} *AvailableProviders*{:.list .ro .bo}
: 

{:id="LocationSensor.CurrentAddress"} *CurrentAddress*{:.text .ro .bo}
: 

{:id="LocationSensor.DistanceInterval"} *DistanceInterval*{:.number}
: Determines the minimum distance interval, in meters, that the sensor will try to use for sending out location updates. For example, if this is set to 5, then the sensor will fire a LocationChanged event only after 5 meters have been traversed. However, the sensor does not guarantee that an update will be received at exactly the distance interval. It may take more than 5 meters to fire an event, for instance.

{:id="LocationSensor.Enabled"} *Enabled*{:.boolean}
: 

{:id="LocationSensor.HasAccuracy"} *HasAccuracy*{:.boolean .ro .bo}
: 

{:id="LocationSensor.HasAltitude"} *HasAltitude*{:.boolean .ro .bo}
: 

{:id="LocationSensor.HasLongitudeLatitude"} *HasLongitudeLatitude*{:.boolean .ro .bo}
: 

{:id="LocationSensor.Latitude"} *Latitude*{:.number .ro .bo}
: 

{:id="LocationSensor.Longitude"} *Longitude*{:.number .ro .bo}
: 

{:id="LocationSensor.ProviderLocked"} *ProviderLocked*{:.boolean .bo}
: 

{:id="LocationSensor.ProviderName"} *ProviderName*{:.text .bo}
: 

{:id="LocationSensor.TimeInterval"} *TimeInterval*{:.number}
: Determines the minimum time interval, in milliseconds, that the sensor will try to use for sending out location updates. However, location updates will only be received when the location of the phone actually changes, and use of the specified time interval is not guaranteed. For example, if 1000 is used as the time interval, location updates will never be fired sooner than 1000ms, but they may be fired anytime after.

### Events  {#LocationSensor-Events}

{:.events}

{:id="LocationSensor.LocationChanged"} LocationChanged(*latitude*{:.number},*longitude*{:.number},*altitude*{:.number},*speed*{:.number})
: Indicates that a new location has been detected.

{:id="LocationSensor.StatusChanged"} StatusChanged(*provider*{:.text},*status*{:.text})
: Indicates that the status of the location provider service has changed, such as when a
 provider is lost or a new provider starts being used.

### Methods  {#LocationSensor-Methods}

{:.methods}

{:id="LocationSensor.LatitudeFromAddress" class="method returns number"} <i/> LatitudeFromAddress(*locationName*{:.text})
: Derives latitude of given address

{:id="LocationSensor.LongitudeFromAddress" class="method returns number"} <i/> LongitudeFromAddress(*locationName*{:.text})
: Derives longitude of given address

## NearField  {#NearField}

### Properties  {#NearField-Properties}

{:.properties}

{:id="NearField.LastMessage"} *LastMessage*{:.text .ro .bo}
: 

{:id="NearField.ReadMode"} *ReadMode*{:.boolean}
: 

{:id="NearField.TextToWrite"} *TextToWrite*{:.text .bo}
: 

{:id="NearField.WriteType"} *WriteType*{:.number .ro .bo}
: 

### Events  {#NearField-Events}

{:.events}

{:id="NearField.TagRead"} TagRead(*message*{:.text})
: Indicates that a new tag has been detected.
 Currently this is only a plain text tag, as specified in the
 manifest.  See Compiler.java.

{:id="NearField.TagWritten"} TagWritten()
: Event for TagWritten

## OrientationSensor  {#OrientationSensor}

### Properties  {#OrientationSensor-Properties}

{:.properties}

{:id="OrientationSensor.Angle"} *Angle*{:.number .ro .bo}
: 

{:id="OrientationSensor.Available"} *Available*{:.boolean .ro .bo}
: 

{:id="OrientationSensor.Azimuth"} *Azimuth*{:.number .ro .bo}
: 

{:id="OrientationSensor.Enabled"} *Enabled*{:.boolean}
: 

{:id="OrientationSensor.Magnitude"} *Magnitude*{:.number .ro .bo}
: 

{:id="OrientationSensor.Pitch"} *Pitch*{:.number .ro .bo}
: 

{:id="OrientationSensor.Roll"} *Roll*{:.number .ro .bo}
: 

### Events  {#OrientationSensor-Events}

{:.events}

{:id="OrientationSensor.OrientationChanged"} OrientationChanged(*azimuth*{:.number},*pitch*{:.number},*roll*{:.number})
: Default OrientationChanged event handler.

 <p>This event is signalled when the device's orientation has changed.  It
 reports the new values of azimuth, pich, and roll, and it also sets the Azimuth, Pitch,
 and roll properties.</p>
 <p>Azimuth is the compass heading in degrees, pitch indicates how the device
 is tilted from top to bottom, and roll indicates how much the device is tilted from
 side to side.</p>

## Pedometer  {#Pedometer}

### Properties  {#Pedometer-Properties}

{:.properties}

{:id="Pedometer.CalibrateStrideLength"} *CalibrateStrideLength*{:.boolean .bo}
: 

{:id="Pedometer.Distance"} *Distance*{:.number .ro .bo}
: The approximate distance traveled in meters.

{:id="Pedometer.ElapsedTime"} *ElapsedTime*{:.number .ro .bo}
: Time elapsed in milliseconds since the pedometer was started.

{:id="Pedometer.Moving"} *Moving*{:.boolean .ro .bo}
: 

{:id="Pedometer.SimpleSteps"} *SimpleSteps*{:.number .ro .bo}
: The number of simple steps taken since the pedometer has started.

{:id="Pedometer.StopDetectionTimeout"} *StopDetectionTimeout*{:.number}
: The duration in milliseconds of idleness (no steps detected) after which to go into a "stopped" state

{:id="Pedometer.StrideLength"} *StrideLength*{:.number}
: Set the average stride length in meters.

{:id="Pedometer.UseGPS"} *UseGPS*{:.boolean .wo .bo}
: 

{:id="Pedometer.WalkSteps"} *WalkSteps*{:.number .ro .bo}
: the number of walk steps taken since the pedometer has started.

### Events  {#Pedometer-Events}

{:.events}

{:id="Pedometer.CalibrationFailed"} CalibrationFailed()
: Event for CalibrationFailed

{:id="Pedometer.GPSAvailable"} GPSAvailable()
: Event for GPSAvailable

{:id="Pedometer.GPSLost"} GPSLost()
: Event for GPSLost

{:id="Pedometer.SimpleStep"} SimpleStep(*simpleSteps*{:.number},*distance*{:.number})
: This event is run when a raw step is detected

{:id="Pedometer.StartedMoving"} StartedMoving()
: Event for StartedMoving

{:id="Pedometer.StoppedMoving"} StoppedMoving()
: Event for StoppedMoving

{:id="Pedometer.WalkStep"} WalkStep(*walkSteps*{:.number},*distance*{:.number})
: This event is run when a walking step is detected. A walking step is a step that appears to be involved in forward motion.

### Methods  {#Pedometer-Methods}

{:.methods}

{:id="Pedometer.Pause" class="method"} <i/> Pause()
: Pause counting of steps and distance.

{:id="Pedometer.Reset" class="method"} <i/> Reset()
: Resets the step counter, distance measure and time running.

{:id="Pedometer.Resume" class="method"} <i/> Resume()
: Resumes counting, synonym of Start.

{:id="Pedometer.Save" class="method"} <i/> Save()
: Saves the pedometer state to the phone. Permits permits accumulation of steps and distance between invocations of an App that uses the pedometer. Different Apps will have their own saved state.

{:id="Pedometer.Start" class="method"} <i/> Start()
: Start counting steps

{:id="Pedometer.Stop" class="method"} <i/> Stop()
: Stop counting steps

## ProximitySensor  {#ProximitySensor}

### Properties  {#ProximitySensor-Properties}

{:.properties}

{:id="ProximitySensor.Available"} *Available*{:.boolean .ro .bo}
: Reports whether or not the device has a proximity sensor

{:id="ProximitySensor.Distance"} *Distance*{:.number .ro .bo}
: Returns the distance from the object to the device

{:id="ProximitySensor.Enabled"} *Enabled*{:.boolean}
: If enabled, then device will listen for changes in proximity

{:id="ProximitySensor.KeepRunningWhenOnPause"} *KeepRunningWhenOnPause*{:.boolean}
: If set to true, it will keep sensing for proximity changes even when the app is not visible

{:id="ProximitySensor.MaximumRange"} *MaximumRange*{:.number .ro .bo}
: Reports the Maximum Range of the device's ProximitySensor

### Events  {#ProximitySensor-Events}

{:.events}

{:id="ProximitySensor.ProximityChanged"} ProximityChanged(*distance*{:.number})
: Triggered when distance (in cm) of the object to the device changes.