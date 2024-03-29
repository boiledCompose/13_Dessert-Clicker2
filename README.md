## ViewModel

`ViewModel`은 Android 프레임워크에서 액티비티가 소멸되고 다시 생성될 때 폐기되면 안되는 앱 관련 데이터를 저장한다. 앱은 구성 변경 중에 자동으로 `ViewModel`객체를 유지하므로 객체가 보유하고 있는 데이터는 재구성 후에 즉시 사용이 가능하다.


## ViewModel 추가하기

### 1. 의존성 추가

`build.gradle.kts (Module :app)`의 `dependencies` 블록에 다음 종속 항목을 추가한다.
```gradle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
```
<br>

### 2. `ViewModel` 클래스 생성

`ViewModel()` 클래스를 확장하는 클래스를 만든다.

```kotlin
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {   }
```
<br>

### 3. 상태 UI의 모델 생성

데이터 클래스 형식의 상태 UI 모델을 생성한다. 여기에 앱을 사용 중에 관리하고 구성 변경 중 삭제돼선 안되는 변수들을 넣는다.

```kotlin
data class MyUiState( ... )
```
<br>

### 4. UI 모델 변수 생성

`StateFlow`는 현재 상태와 새로운 상태 업데이트를 내보내는 관찰 가능한 데이터 홀더 흐름이다.

`MutableStateFlow` 클래스를 사용해 객체를 생성한다. 이 객체를 읽기 전용으로 선언하려면 `asStateFlow()`메서드를 사용한다.

`StateFlow`객체의 값에 접근하려면 `.value`를 사용한다.

```kotlin
private val _uiState = MutableStateFlow(GameUiState())
val uiState = _uiState.asStateFlow()
```

## ViewModel 클래스와 매개변수

Android Studio는 기본적으로 매개변수를 허용하지 않는다. 허나 `ViewModelFactory`를 사용하여 특정 뷰모델이 인수를 받도록 설정할 수 있다.

다음 코드는 추후에 배울 저장소를 뷰모델 객체의 인수로 전달하는 예제다.

`Factory` 객체를 만든다. 이 코드는 `ViewModel` 클래스 내 `companion object`에 작성되었다.
```kotlin
companion object {
   val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
         val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
         val marsPhotosRepository = application.container.marsPhotosRepository
         MarsViewModel(marsPhotosRepository = marsPhotosRepository)
      }
   }
}
```

만들어 놓은 `Factory`객체를 `ViewModel` 객체에 적용한다.
```kotlin
val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)
```



