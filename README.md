MVVM (Model–View–ViewModel) is an architectural pattern used in software development to separate concerns, improve testability, and make the code more maintainable.
It’s widely used in Android (Jetpack Compose, XML + ViewBinding), .NET/WPF, and other frameworks.

**🔑 Components of MVVM**:

**Model (M)**
- Represents data and business logic.
- Deals with APIs, databases, repositories, and data sources.
- Should not know anything about the UI.

Example:
- data class User(val id: Int, val name: String)

**View (V)**

- The UI layer (Activity, Fragment, or Composable in Jetpack Compose).
- Displays data to the user.
- Observes the ViewModel for changes.
- Should not contain business logic.

Example (Jetpack Compose):

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState()
    Text(text = user?.name ?: "Loading...")
}


**ViewModel (VM)**

- Acts as a bridge between Model and View.
- Exposes LiveData / StateFlow to UI.
- Handles UI logic, state management, and transforms data from Model for View.
- Survives configuration changes (like rotation).

Example:

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loadUser(id: Int) {
        viewModelScope.launch {
            _user.value = repository.getUser(id)
        }
    }
}

**🔄 MVVM Flow**
User Action -> View -> ViewModel -> Model -> ViewModel -> View


- View listens to ViewModel.
- ViewModel fetches data from Model.
- Model provides raw data (from DB, API, etc.).
- ViewModel prepares/filters data.
- View updates automatically via observation.

**✅ Advantages of MVVM**

Separation of concerns → cleaner code.

Easier unit testing (ViewModel can be tested without UI).

Handles configuration changes gracefully.

Works perfectly with Jetpack libraries (LiveData, StateFlow, Hilt, Room, Retrofit).
