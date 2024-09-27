My Comics App is a comic book cataloging app that allows you to scan in images of your comics and store all the info about all your comics in one place, and when you want to go out and buy some more comics you can hit the pin in the toolbar and find local comic book stores in your area.

Major Features/Screens - Include short descriptions of each.

Login -- Screen 1
Unfinished, just checks to make sure a username and password has been entered before logging you in I plan on connecting this to APIs in the future to actually register users and verify them through e-mail etc I obviously just didn't have the time and it was not the main focus of the app so I kept it simple

Libarary -- Screen 2
Stores all of the user's comic library in a SQLite Database using the abstraction class Room as recommended on the Android developers site. It has recycler views for all the issues and info on them in 4 different tabs sorted by publisher (Marvel, DC, Indie, All)

Add new -- Screen 3
Allows you to add new items to the libarary currently the only things being stored are the series title, issue number, writer, artist, and the image associated with it. There are fields for inputting the published and purchased dates and also the price paid. I had planned on adding another screen that could show a calendar and tell you how much you spent on comics in a certain period maybe also have a editable budget that tracks how much over or under your alotted budget you are

Find local stores -- Screen 4
Uses location data to show you nearby comic book stores. It uses google maps and places APIs and searches for "comic book store" within a 25 mile radius up to a maximum of 20 results and adds markers to those spots which can be clicked on to reveal the business's name and then again clicking on the maptoolbar provided by the API it loads an intent to launch google maps with directions (a small bug I noticed is using the provided toolbar doesn't always give you the exact adress somehow so I plan on fixing that using my own FAB in the future). Lastly you can move around the map and if you single tap anywhere on the map it will search again using the tap location as the center of the 25 mile circle removing old markers and setting new ones for that area

• Optional Features - Include specific directions on how to test/demo each feature and declare the exact set that adds up to 50 pts. If you do not declare your set, I will randomly pick optional features to grade.

7.5 pts – Displaying data using Recycler View
-----------------------------------------------------------
This is in the library screen you can see it once you add some comics into the database. Required fields are Series Title, Issue Number, Artist, and Writer, optional but functional field is the scan of the comic, the other optional fields are not functional at the moment.

7.5 pts – Allow users to select date and time to support some features of your app. For example, schedule the date and time for an appointment.
-------------------------------------------------------------
This didn't get finished but was my intention, it was supposed to collect the date info from the Add Comic screen and use it to sort the recycler view or also record into the Calendar/Budget screen which never got added

7.5 pts – Displaying different categories of data using tabs on at least one screen
--------------------------------------------------------------
The library screen uses tabs to seperate the comics by publisher

5 pts – Alert dialogs that allow users to confirm some actions
---------------------------------------------------------------
When optional fields like the scan to add or price are not entered in before adding the comic it alerts the user and asks them to confirm or go back if they press go back the data stays in the fields but doesn't get added. If they confirm it does get added and the fields are cleared.

5 pts – using checkbox or radio buttons
---------------------------------------------------------------
Checkboxes are used to select the publisher in the add comic screen

7.5 pts – Camera - Your app uses the camera to take a picture and use it directly in the app. The image is saved on your phone.
----------------------------------------------------------------
On the add comic screen you can press the scan button to open a document scanner that uses your phones camera. The scan is then returned and made into a bitmap which is then scaled down to store well in SQLite and is added to the local database.

10 pts - Uses local data
----------------------------------------------------------------
I wish I could've implemented the request permissions stuff but it does use your local data as long as you set it to allow location permissions and select the option for precise location. It uses the local lattitude and longitude as the center of a circle with radius 25 miles within which to search for comic book stores. The search results are then used to add markers to the screen that the user can select and use the toolbar in the button right to get directions to that location.

• Usage - Include any special info we need to run the app.
Needs permissions for precise location, couldn't get the request permissions to work.

• Lessons Learned - What did you learn about mobile development through this process?
ALOT! It was quite the experience, I think the biggest lesson I learned is don't try to mess with something 3 hours before you're supposed to turn it in... I created a bug and was chasing it down trying to figure out how to fix it for ... about 3 hours. More seriously though I learned alot about how APIs work and how to implement them in an app to do some really impressive stuff.