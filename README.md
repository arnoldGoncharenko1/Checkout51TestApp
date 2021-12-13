# Checkout 51 Test App
# Design pattern used
I've used the MVVM pattern, jetpack libraries, and Kotlin to build out this app. This ensures that everything within the application is extendable, organized, decoupled, and individually testable.
The following jetpack utils have been used:
- AppCompat - Backwards compatibility for older version of Android
- Room - Used to create a Room database on the users local device.
- Hilt - Dependency Injection for all my different modules.
- Lifecycle - Allows subscriptions between the UI and View model layers.
- Klaxon - Allows parsing of JSON files, used to retrieve the data from the JSON file.
- DAO - Allows DB querying and insertion.
- Glide - Allows image loading.
- JUnit - Allows testing of the components available.
- Data binding - Allows data to be directly binding on the XML file. Useful to be used together with Lifecycle.
 
# Models
OffersModel - Basic model of the JSON based offer. Contains all the offers + Batch ID that comes in the JSON.

OfferModel - Basic model of an individual offer contained in the JSON.

OffersDatabaseEntry - Basic model of how an offer would look inside the local Room Database.
 
# Services
OffersRepository - Provides a layer to add new JSON data and retrieve it from the local Room DB.
 
# utils
constants - A small file to store constants to be used elsewhere, notably the DB name and JSON file name.
 
# viewModel
offersViewModel - View model that provides information to the view when the view subscribes to the view model. Provides data logic like checking if the DB is empty, adding to the Room database, and retrieving data.
 
# Views
MainActivity - A basic activity that stores the reference to the fragment + nav graph

Offers Fragment - A fragment showing the offers that the user has received from the JSON file. Purely just subscribes to the view model and displays the data.
 
# Development Retrospective
Over the course of the development of this application, I've learned quite a bit about the various new jetpack Android libraries as well as the MVVM Architecture. But there are some issues, that if given more time, I would look into. The first is a switching the isDatabaseEmpty() check from a thread to a coroutine for better management and readability; I had trouble figuring out why the coroutine I was setting up refused to work and had to rely on threads to get the job done. The second issue is testability, I am somewhat familiar with the MVVM Architecture and I built it with testability and division of responsibilities in mind, but it was hard to find resources and proper documentation on testing of live-data that my app consists of. Given more time, I would create a more through testing suite with the information I would gather.
 
# Testing functions
OffersDaoTest - Test responsible for ensuring that DAO for offers was working correctly, this is the first place where I ran into an issue with testing live data.

OffersViewModelTest - Test responsible for ensuring that the view model was behaving in the right way but due to it consisting of almost all live data, I had trouble finding proper documentation or instructions for testing its various methods.

TestUtils - File to store several objects that could be quickly referenced in my testing classes

MainTestRunner - Test runner for my tests, customized to use the Hilt Test App

MainCoroutineRule - Used to ensure that co-routines can be used on my tests.