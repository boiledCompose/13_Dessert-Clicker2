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

`StateFlow`



