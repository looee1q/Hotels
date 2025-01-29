# Hotels
### A small App made as a test App for CleverPumpkin

## Stack
+ Kotlin
+ Compose
+ XML
+ Clean Architecture
+ MVVM
+ Hilt
+ Ktor
+ Coroutines
+ StateFlow
+ Kotlin DSL Navigation
+ Fragments
+ Coil

## App Info
The App consists of two screens:
+ **Hotels screen** - the screen with list of hotels and brief info about each hotel.
+ **Hotel Details screen** - the screen with extended information about hotel, including Hotel image.

###  Hotels screen features
+ Compose with ComposeView for UI.
+ Include five states: Loading, Loaded, EmptyList, NoConnectionError, UnknownError.
+ Include Button for hotels sorting based on distance from center and suites availability.
+ Support both landscape and portrait orientation.

### Hotel Details screen features
+ XML for UI.
+ Include four states: Loading, Loaded, NoConnectionError, UnknownError.
+ The screen displayes the image of the hotel. This image is checked on its dimenstions to avoid showing poor quality images.
+ Coil for loading Images.
+ The separate progress bar is showing while image is loading.
+ If the loading was unsuccessful or the image is poor quality the placeholder is shown.
+ The original image is clipped by 1 pixel on each side to avoid showing red boundary.
+ Include Button for opening a map app to display the location of the hotel.
+ Support both landscape and portrait orientation.

## Potential TODO list
+ Migrate to full Compose.
+ Adding pagination on hotels screen.
+ Adding local storage using Room.
+ Adding button to clear sorting on hotels screen.