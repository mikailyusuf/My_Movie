# My Movie

<p align="left">  
My Movie  an Android application based on  Dagger Hilt, Coroutines,Jetpack (Room, ViewModel,Navigation LiveData) based on MVVM architecture. Also fetching data from the network and integrating local data in the database via repository pattern.
</p>



## Tech stack - Library
- [Kotlin](https://kotlinlang.org/) , [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) 
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- JetPack
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
  - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [MVVM Architecture]() (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)

## Architecture
My Movie is based on MVVM architecture and a repository pattern.

![architecture](https://raw.githubusercontent.com/fevziomurtekin/hackernewsapp/master/screenshot/mvvm.png)

## API
Used Moviedb API provides a RESTful API. [Link](https://developers.themoviedb.org/3)