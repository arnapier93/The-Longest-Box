The Longest Box is a comic book cataloging app that allows you to scan in images of your comics and store all the info about all your comics in one place, and when you want to go out and buy some more comics you can hit the pin in the toolbar and find local comic book stores in your area.

--- Screens & Major Features ---

Login -- Screen 1
Unfinished, just checks to make sure a username and password has been entered before logging you in I plan on connecting this to APIs in the future to actually register users and verify them through e-mail etc I obviously just didn't have the time and it was not the main focus of the app so I kept it simple

Libarary -- Screen 2
Stores all of the user's comic library in a SQLite Database using the abstraction class Room as recommended on the Android developers site. It has recycler views for all the issues and info on them in 4 different tabs sorted by publisher (Marvel, DC, Indie, All)

Add new -- Screen 3
Allows you to add new items to the libarary currently the only things being stored are the series title, issue number, writer, artist, and the image associated with it. There are fields for inputting the published and purchased dates and also the price paid. I had planned on adding another screen that could show a calendar and tell you how much you spent on comics in a certain period maybe also have a editable budget that tracks how much over or under your alotted budget you are

Find local stores -- Screen 4
Uses location data to show you nearby comic book stores. It uses google maps and places APIs and searches for "comic book store" within a 25 mile radius up to a maximum of 20 results and adds markers to those spots which can be clicked on to reveal the business's name and then again clicking on the maptoolbar provided by the API it loads an intent to launch google maps with directions (a small bug I noticed is using the provided toolbar doesn't always give you the exact adress somehow so I plan on fixing that using my own FAB in the future). Lastly you can move around the map and if you single tap anywhere on the map it will search again using the tap location as the center of the 25 mile circle removing old markers and setting new ones for that area
