---
layout: documentation
title: Experimental
---

[&laquo; Back to index](index.html)
# Experimental

Table of Contents:

* [CloudDB](#CloudDB)
* [FirebaseDB](#FirebaseDB)

## CloudDB  {#CloudDB}

### Properties  {#CloudDB-Properties}

{:.properties}

{:id="CloudDB.DefaultRedisServer"} *DefaultRedisServer*{:.text .wo .do}
: The Default Redis Server to use.

{:id="CloudDB.ProjectID"} *ProjectID*{:.text .ro}
: Gets the ProjectID for this CloudDB project.

{:id="CloudDB.RedisPort"} *RedisPort*{:.number .ro}
: The Redis Server port to use. Defaults to 6381

{:id="CloudDB.RedisServer"} *RedisServer*{:.text .ro}
: The Redis Server to use to store data. A setting of "DEFAULT" means that the MIT server will be used.

{:id="CloudDB.Token"} *Token*{:.text .ro .do}
: This field contains the authentication token used to login to the backed Redis server. For the "DEFAULT" server, do not edit this value, the system will fill it in for you. A system administrator may also provide a special value to you which can be used to share data between multiple projects from multiple people. If using your own Redis server, set a password in the server's config and enter it here.

{:id="CloudDB.UseSSL"} *UseSSL*{:.boolean .ro .do}
: Set to true to use SSL to talk to CloudDB/Redis server. This should be set to True for the "DEFAULT" server.

### Events  {#CloudDB-Events}

{:.events}

{:id="CloudDB.CloudDBError"} CloudDBError(*message*{:.text})
: Indicates that an error occurred while communicating with the CloudDB Redis server.

{:id="CloudDB.DataChanged"} DataChanged(*tag*{:.text},*value*{:.any})
: Indicates that the data in the CloudDB project has changed.
 Launches an event with the tag and value that have been updated.

{:id="CloudDB.FirstRemoved"} FirstRemoved(*value*{:.any})
: Event triggered by the "RemoveFirstFromList" function. The argument "value" is the object that was the first in the list, and which is now removed.

{:id="CloudDB.GotValue"} GotValue(*tag*{:.text},*value*{:.any})
: Indicates that a GetValue request has succeeded.

{:id="CloudDB.TagList"} TagList(*value*{:.list})
: Event triggered when we have received the list of known tags. Used with the "GetTagList" Function.

### Methods  {#CloudDB-Methods}

{:.methods}

{:id="CloudDB.AppendValueToList" class="method"} <i/> AppendValueToList(*tag*{:.text},*itemToAdd*{:.any})
: Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.

{:id="CloudDB.ClearTag" class="method"} <i/> ClearTag(*tag*{:.text})
: Remove the tag from CloudDB

{:id="CloudDB.CloudConnected" class="method returns boolean"} <i/> CloudConnected()
: returns True if we are on the network and will likely be able to connect to the CloudDB server.

{:id="CloudDB.GetTagList" class="method"} <i/> GetTagList()
: Get the list of tags for this application. When complete a "TagList" event will be triggered with the list of known tags.

{:id="CloudDB.GetValue" class="method"} <i/> GetValue(*tag*{:.text},*valueIfTagNotThere*{:.any})
: Get the Value for a tag, doesn't return the value but will cause a GotValue event to fire when the value is looked up.

{:id="CloudDB.RemoveFirstFromList" class="method"} <i/> RemoveFirstFromList(*tag*{:.text})
: Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the "FirstRemoved" event will be triggered.

{:id="CloudDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Store a value at a tag.

## FirebaseDB  {#FirebaseDB}

### Properties  {#FirebaseDB-Properties}

{:.properties}

{:id="FirebaseDB.DefaultURL"} *DefaultURL*{:.text .wo .do}
: 

{:id="FirebaseDB.DeveloperBucket"} *DeveloperBucket*{:.text .do}
: 

{:id="FirebaseDB.FirebaseToken"} *FirebaseToken*{:.text .do}
: 

{:id="FirebaseDB.FirebaseURL"} *FirebaseURL*{:.text .do}
: Gets the URL for this FirebaseDB.

{:id="FirebaseDB.Persist"} *Persist*{:.boolean .wo .do}
: If true, variables will retain their values when off-line and the App exits. Values will be uploaded to Firebase the next time the App is run while connected to the network. This is useful for applications which will gather data while not connected to the network. Note: AppendValue and RemoveFirst will not work correctly when off-line, they require a network connection.<br/><br/> <i>Note</i>: If you set Persist on any Firebase component, on any screen, it makes all Firebase components on all screens persistent. This is a limitation of the low level Firebase library. Also be aware that if you want to set persist to true, you should do so before connecting the Companion for incremental development.

{:id="FirebaseDB.ProjectBucket"} *ProjectBucket*{:.text}
: Gets the ProjectBucket for this FirebaseDB.

### Events  {#FirebaseDB-Events}

{:.events}

{:id="FirebaseDB.DataChanged"} DataChanged(*tag*{:.text},*value*{:.any})
: Indicates that the data in the Firebase has changed.
 Launches an event with the tag and value that have been updated.

{:id="FirebaseDB.FirebaseError"} FirebaseError(*message*{:.text})
: Indicates that the communication with the Firebase signaled an error.

{:id="FirebaseDB.FirstRemoved"} FirstRemoved(*value*{:.any})
: Event triggered by the "RemoveFirst" function. The argument "value" is the object that was the first in the list, and which is now removed.

{:id="FirebaseDB.GotValue"} GotValue(*tag*{:.text},*value*{:.any})
: Indicates that a GetValue request has succeeded.

{:id="FirebaseDB.TagList"} TagList(*value*{:.list})
: Event triggered when we have received the list of known tags. Used with the "GetTagList" Function.

### Methods  {#FirebaseDB-Methods}

{:.methods}

{:id="FirebaseDB.AppendValue" class="method"} <i/> AppendValue(*tag*{:.text},*valueToAdd*{:.any})
: Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.

{:id="FirebaseDB.ClearTag" class="method"} <i/> ClearTag(*tag*{:.text})
: Remove the tag from Firebase

{:id="FirebaseDB.GetTagList" class="method"} <i/> GetTagList()
: Get the list of tags for this application. When complete a "TagList" event will be triggered with the list of known tags.

{:id="FirebaseDB.GetValue" class="method"} <i/> GetValue(*tag*{:.text},*valueIfTagNotThere*{:.any})
: GetValue asks Firebase to get the value stored under the given tag.
 It will pass valueIfTagNotThere to GotValue if there is no value stored
 under the tag.

{:id="FirebaseDB.RemoveFirst" class="method"} <i/> RemoveFirst(*tag*{:.text})
: Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the "FirstRemoved" event will be triggered.

{:id="FirebaseDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Asks Firebase to store the given value under the given tag.

{:id="FirebaseDB.Unauthenticate" class="method"} <i/> Unauthenticate()
: If you are having difficulty with the Companion and you are switching between different Firebase accounts, you may need to use this function to clear internal Firebase caches. You can just use the "Do It" function on this block in the blocks editor. Note: You should not normally need to use this block as part of an application.