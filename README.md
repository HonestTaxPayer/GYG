# GYG
Demo reviews project

This project is a single activity app. For architecture, I chose to go for a clean approach, with
codebase split into 3 packages (presentation, domain, data). I would usually use 3 separate modules
for this, but since the project has such a reduced scope, I considered it unnecessary. For the
presentation logic I chose MVVM with Livedata.

The fonts and color scheme are borrowed from the original GetYourGuide Android apk.

Navigation between fragments is done with the navigation component + safe args. Navigation component
only supports replace at the moment(no add), and workarounds take a long time to implement, therefore
the `onViewCreated()` method is called on each `onBackPressed()` -> the call gets re-done.

For network operations, I use the paging library + retrofit + coroutines

For the details screen, the pic changes depending if the author actually has a photo (either photo or
capitalized initial in red circle). The author info contains the name, the country and the traveller
type.

Added also unit tests for relevant classes.

