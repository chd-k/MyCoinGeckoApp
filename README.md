# MyCoinGeckoApp

### Description

There is an app that show crypto data provided by [CoinGecko API](https://www.coingecko.com/)

### Realization
#### Technology stack

- Retrofit
- OkHttp
- Coil
- Coroutines
- Flow
- ViewModel
- View binding
- MVVM
- Material Design

#### Features

- `DiffUtil` and `AdapterDataObserver` for scrolling `RecyclerView` to the top after changing list
- color state list resources in `ChipGroup` for highlighting checked `Chip`

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
            |--- API            | Files to make requests to the API
            |--- Models         | Representation of API responses
            |--- DataSources    | Remote DataSource 
            |--- Repositories   | Files that retrieve and manipulate data 
            |--- Utils          | Utility files (constants and response wrapper)
```

### Upcoming updates

- [ ] change fonts
- [ ] add sorting
- [ ] add colors in percentage change
