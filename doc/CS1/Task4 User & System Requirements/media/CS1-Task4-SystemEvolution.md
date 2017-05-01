# CS1 Task 4: Requirements Document Part "System Evolution"
For a more momentary view about the System Requirements check the corresponding chapter "System Requirements". A short overview over the components and technology used for this application:
  * Server
    * Web- and Databaseserver
    * (Backup server)
  * Client
    * Mobile device
  * Connectivity
    * Internet connection
    * Local network on customer site

## Application relevant developments
**Mobile devices** are developed further all the time, especially in terms of processing power and resolution. In order to look good the application needs to scale correctly on high-DPI (hdpi) devices (devices with a very high resolution) since mobile devices with hdpi screens are becoming the norm. To stay future proof in this regard the application code should implement a way to scale correctly on various devices.

## Not application relevant developments
**Web-, Database-** and, if implemented **Backupservers**, are built to match the requirements of today. Unless there is a massive increase like 3 times the current users using the application and therefore generating load on the servers the requirements in this area stay the same. But even if that should occur todays servers are scalable and no changes in the server side code are necessary.

In terms of **Internet connectivity** even today most devices can connect to the internet over the mobile network (3G, LTE). The amount of mobile devices with internet access will only increase so there is no need for action.

Terms of this chapter in the glossary: 3G, LTE, HDPI
