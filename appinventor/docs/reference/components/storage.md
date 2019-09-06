---
layout: documentation
title: Storage
---

[&laquo; Back to index](index.html)
# Storage

Table of Contents:

* [File](#File)
* [FusiontablesControl](#FusiontablesControl)
* [TinyDB](#TinyDB)
* [TinyWebDB](#TinyWebDB)

## File  {#File}

### Events  {#File-Events}

{:.events}

{:id="File.AfterFileSaved"} AfterFileSaved(*fileName*{:.text})
: Event indicating that the contents of the file have been written.

{:id="File.GotText"} GotText(*text*{:.text})
: Event indicating that the contents from the file have been read.

### Methods  {#File-Methods}

{:.methods}

{:id="File.AppendToFile" class="method"} <i/> AppendToFile(*text*{:.text},*fileName*{:.text})
: Appends text to the end of a file storage, creating the file if it does not exist. See the help text under SaveFile for information about where files are written.

{:id="File.Delete" class="method"} <i/> Delete(*fileName*{:.text})
: Deletes a file from storage. Prefix the filename with / to delete a specific file in the SD card, for instance /myFile.txt. will delete the file /sdcard/myFile.txt. If the file does not begin with a /, then the file located in the programs private storage will be deleted. Starting the file with // is an error because assets files cannot be deleted.

{:id="File.ReadFrom" class="method"} <i/> ReadFrom(*fileName*{:.text})
: Reads text from a file in storage. Prefix the filename with / to read from a specific file on the SD card. for instance /myFile.txt will read the file /sdcard/myFile.txt. To read assets packaged with an application (also works for the Companion) start the filename with // (two slashes). If a filename does not start with a slash, it will be read from the applications private storage (for packaged apps) and from /sdcard/AppInventor/data for the Companion.

{:id="File.SaveFile" class="method"} <i/> SaveFile(*text*{:.text},*fileName*{:.text})
: Saves text to a file. If the filename begins with a slash (/) the file is written to the sdcard. For example writing to /myFile.txt will write the file to /sdcard/myFile.txt. If the filename does not start with a slash, it will be written in the programs private data directory where it will not be accessible to other programs on the phone. There is a special exception for the AI Companion where these files are written to /sdcard/AppInventor/data to facilitate debugging. Note that this block will overwrite a file if it already exists.

If you want to add content to a file use the append block.

## FusiontablesControl  {#FusiontablesControl}

### Properties  {#FusiontablesControl-Properties}

{:.properties}

{:id="FusiontablesControl.ApiKey"} *ApiKey*{:.text}
: Your Google API Key. For help, click on the questionmark (?) next to the FusiontablesControl component in the Palette. 

{:id="FusiontablesControl.KeyFile"} *KeyFile*{:.text}
: Specifies the path of the private key file.  This key file is used to get access to the FusionTables API.

{:id="FusiontablesControl.LoadingDialogMessage"} *LoadingDialogMessage*{:.text}
: Set the loading message for the dialog.

{:id="FusiontablesControl.Query"} *Query*{:.text}
: The query to send to the Fusion Tables API. <p>For legal query formats and examples, see the <a href="https://developers.google.com/fusiontables/docs/v2/getting_started" target="_blank">Fusion Tables API v2.0 reference manual</a>.</p> <p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure it follows the syntax described in the reference manual, which means that things like capitalization for names of columns matters, and that single quotes need to be used around column names if there are spaces in them.</p> 

{:id="FusiontablesControl.ServiceAccountEmail"} *ServiceAccountEmail*{:.text}
: The Service Account Email Address when service account authentication is in use.

{:id="FusiontablesControl.ShowLoadingDialog"} *ShowLoadingDialog*{:.boolean}
: Whether or not to show the loading dialog

{:id="FusiontablesControl.UseServiceAuthentication"} *UseServiceAuthentication*{:.boolean}
: Indicates whether a service account should be used for authentication

### Events  {#FusiontablesControl-Events}

{:.events}

{:id="FusiontablesControl.GotResult"} GotResult(*result*{:.text})
: Indicates that the Fusion Tables query has finished processing, with a result.  The result of the query will generally be returned in CSV format, and can be converted to list format using the "list from csv table" or "list from csv row" blocks.

### Methods  {#FusiontablesControl-Methods}

{:.methods}

{:id="FusiontablesControl.DoQuery" class="method"} <i/> DoQuery()
: DEPRECATED. This block is deprecated as of the end of 2012.  Use SendQuery.

{:id="FusiontablesControl.ForgetLogin" class="method"} <i/> ForgetLogin()
: Forget end-users login credentials. Has no effect on service authentication

{:id="FusiontablesControl.GetRows" class="method"} <i/> GetRows(*tableId*{:.text},*columns*{:.text})
: Gets all the rows from a specified fusion table. The tableId field is the id of therequired fusion table. The columns field is a comma-separeted list of the columns to retrieve.

{:id="FusiontablesControl.GetRowsWithConditions" class="method"} <i/> GetRowsWithConditions(*tableId*{:.text},*columns*{:.text},*conditions*{:.text})
: Gets all the rows from a fusion table that meet certain conditions. The tableId field isthe id of the required fusion table. The columns field is a comma-separeted list of the columns toretrieve. The conditions field specifies what rows to retrieve from the table, for example the rows in whicha particular column value is not null.

{:id="FusiontablesControl.InsertRow" class="method"} <i/> InsertRow(*tableId*{:.text},*columns*{:.text},*values*{:.text})
: Inserts a row into the specified fusion table. The tableId field is the id of thefusion table. The columns is a comma-separated list of the columns to insert values into. The values field specifies what values to insert into each column.

{:id="FusiontablesControl.SendQuery" class="method"} <i/> SendQuery()
: Send the query to the Fusiontables server.

## TinyDB  {#TinyDB}

### Properties  {#TinyDB-Properties}

{:.properties}

{:id="TinyDB.Namespace"} *Namespace*{:.text}
: Namespace for storing data.

### Methods  {#TinyDB-Methods}

{:.methods}

{:id="TinyDB.ClearAll" class="method"} <i/> ClearAll()
: Clear the entire data store

{:id="TinyDB.ClearTag" class="method"} <i/> ClearTag(*tag*{:.text})
: Clear the entry with the given tag

{:id="TinyDB.GetTags" class="method returns any"} <i/> GetTags()
: Return a list of all the tags in the data store

{:id="TinyDB.GetValue" class="method returns any"} <i/> GetValue(*tag*{:.text},*valueIfTagNotThere*{:.any})
: Retrieve the value stored under the given tag.  If there's no such tag, then return valueIfTagNotThere.

{:id="TinyDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Store the given value under the given tag.  The storage persists on the
 phone when the app is restarted.

## TinyWebDB  {#TinyWebDB}

### Properties  {#TinyWebDB-Properties}

{:.properties}

{:id="TinyWebDB.ServiceURL"} *ServiceURL*{:.text}
: 

### Events  {#TinyWebDB-Events}

{:.events}

{:id="TinyWebDB.GotValue"} GotValue(*tagFromWebDB*{:.text},*valueFromWebDB*{:.any})
: Indicates that a GetValue server request has succeeded.

{:id="TinyWebDB.ValueStored"} ValueStored()
: Event indicating that a StoreValue server request has succeeded.

{:id="TinyWebDB.WebServiceError"} WebServiceError(*message*{:.text})
: Indicates that the communication with the Web service signaled an error

### Methods  {#TinyWebDB-Methods}

{:.methods}

{:id="TinyWebDB.GetValue" class="method"} <i/> GetValue(*tag*{:.text})
: GetValue asks the Web service to get the value stored under the given tag.
 It is up to the Web service what to return if there is no value stored
 under the tag.  This component just accepts whatever is returned.

{:id="TinyWebDB.StoreValue" class="method"} <i/> StoreValue(*tag*{:.text},*valueToStore*{:.any})
: Asks the Web service to store the given value under the given tag