# MyCoinGeckoApp

### Description

There is an app that show crypto data provided by [CoinGecko API](https://www.coingecko.com/)

### Technology stack

- Retrofit
- OkHttp
- Coil
- Coroutines
- Flow
- ViewModel
- View binding
- MVVM
- Material Design

### Process decomposition

1. Setting up Gradle
2. Setting up layers
3. Setting up layouts for fragments
4. Setting up responses, API and repositories
5. Setting up ViewModels (stream from M to VM)
6. Setting up RecyclerView
7. Setting up fragments (stream from VM to V)
8. Refactoring
9. Testing

### Architecture layers

```
App
    |--- UI
        |--- Adapters           | RecyclerView adapter and ViewHolder
        |--- Fragments          | Fragments classes with streams to ViewModels
        |--- ViewModels         | ViewModels classes with streams to Fragments and Data layer
        
    |--- Data
        |--- Network            | Data recevied from network
            |--- API            |
            |--- Models         |
            |--- DataSources    |
            |--- Repositories   |
            |--- Utils          |
```

### Upcoming updates

- [ ] change fonts
- [ ] add sorting
- [ ] add colors in text
