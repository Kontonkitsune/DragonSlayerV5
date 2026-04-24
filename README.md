# DragonSlayerV5
Capstone Project for UNO class CSCI4970.

## Prerequisites:
- JDK21
- Apache Maven

## How to use:
Ensure that Maven is installed within your environmental variables.

Run the command: `mvn clean package -DskipTests -Pdev`
- This will create a standalone `.exe` for testing.
- It can be found within `{installationDirectory}\dragonslayer\target\distributable\Dragonslayer`

## Changelog:
#### Version 4.0.0
###### Major Updates
- Updated from Java 17 --> Java 21
- Updated from JavaFX 18 --> JavaFX 21.0.8

###### Additions
- Included `Add` button on the Title tab for the customer.
- Added `Delete` button on the Title tab for the customer as well.

###### Bug Fixes
- Fixed ghost title issue, where titles would randomly pop-up.
- Fixed flagging issues

#### Version 4.0.1
##### Additions
- Added deletion confirmation message for customers within the title tab.
- Highlights new customers with no orders.

##### Bug Fixes
- Fixed issue where sort by title was slow.

#### Version 4.1.0
###### Additions
- Included `DerbyDB` folder button
- Changed `DerbyDB` folder location to reflect APP_Image distribution
- Added JUnit 5 + Simple Testing suite

###### Bug fixes
- Fixed title overflowing into other fields

#### Version 4.2.0
###### Additions
- Included Sort by Last flagged column
- Previous Customers Tab, along with associated junction table
- Added refresh button to refresh Database connection
- Changed icon
- Added save test in hopes of squashing the bug
- Added "Pending" stats in the flagged column
- Created a pop-up to save to two different stores
- Created "View-Only" mode.
- Included Show Previous Box to show titles with older customers

###### Bug fixes
- Added potential fix for exports not saving correctly
- Fixed bug where the customer wasn't being added to a title
- Fixed performance searching performance issues
- Fixed edge-case where names with hyphens will break the SQL query
- Fixed titles not displaying after being created.


#### Version 5.0
- Changed management

#### Version 5.0.1
###### Bug Fixes
- Fixed bug where the view-only mode could access Edit, Delete, Add, and other features.
- Removed the ability to flag titles in View-only mode (It still checks, but this is a visual bug)
- Fixed the issue where deleting a title after a request for it is made will not delete the title due to SQL foreign key constraint.

#### Version 5.1
##### Features
- Added Tags and Aliases to Titles.
- Added an options menu for title-searching. You can now search by title, ID, tag, notes, and aliases all at once or separately.

#### Version 5.2
##### Features
- Changed the Add Order menu to allow for changing the customer after selecting it from a customer.
- Changed the Add Order submenu from the Titles Tab to execute the same menu as the one from the Customers Tab, rather than outdated code.
- Set the Add order menu to autofill customer or title, depending on where you're adding the order from.
- You can now just press "Enter" to confirm the password box for edit mode.
###### Bug Fixes
- Fixed "Reset all flags" causing pretty much every feature of the Titles tab to break... probably.


#### Version 5.3
##### Features
- The program is now compatible with earlier versions of the database, and will add the Aliases and Tags column if the database doesn't already have them.
- Parity for the title searching feature for the Customers tab. New search options menu!
- Can now search by full name in FirstName LastName or Lastname, Firstname formats and both are accepted if Full name is selected under options
###### Bug fixes
- Fixed the sizing of some windows: edit/add title, and search options.


#### Version 5.4
##### Features
- TagOrders! You can now create special orders for customers which will include certain tags, which will dynamically update if titles are given said tags. Since they are auto-generated, they show up as Green to differentiate them from normal orders.
- TagOrders can only be Added and Deleted from the customers menu. They cannot be edited, and if they are, this is untested.
- Delinquent customers show up as red in the Customers, Titles, and Requests menus! You can mark customers as delinquent or remove their delinquency from the Customers tab.
- Title and Customer search options now have select/deselect all
- Orders now have a Notes section, useful for specialized information specific to a request.
###### Bug fixes
- Fixed the sizing of many windows.
- I literally do not remember the rest of the bugfixes because I honestly was not paying attention and most of them were trivial enough I didn't bother writing it down. There were probably a lot of them.