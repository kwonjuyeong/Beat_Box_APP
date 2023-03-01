package com.example.beatbox.ChapterMemo

/*
Chapter19
데이터 바인딩과 MVVM

* MVC 아키텍처
- 규모가 작고 간단한 앱에는 적합하다.
장점 : 새로운 기능을 추가하기 쉽고 앱의 동적인 부분을 쉽게 알 수 있을 뿐 아니라 프로젝트의 초기 단계에 확고한 개발 기반을 만들워줘서 앱을 빨리 개발할 수 있다.
단점 : 프로젝트가 커지면 문제가 발생함. MVC 컨트롤러 역할인 액티비티나 프래그먼트의 규모가 커지면서 작성과 이해가 어려워져서 새로운 기능을 추가하거나 결함을 해결하는데 시간이 오래 걸린다.

MVVM 아키텍처
- 규모가 크고 컨트롤러가 복잡한 앱에 적합하다.
- 컨트롤러 클래스들이 하는 작업을 파악하여 하나의 거대한 클래스 대신 여러 클래스가 작업을 분담해 협업한다.
- 뷰와 밀접한 컨트롤러 코드를 레이아웃 파일로 옮길 수 있다.
- 동적인 컨트롤러 코드의 일부를 뷰모델 클래스에 넣어서 앱의 테스트와 검증이 쉬워진다.

MVVM 뷰모델 vs Jetpack ViewModel
- Jetpack ViewModel은 액티비티나 프래그먼트 생명주기에 걸쳐 데이터를 유지하고 관리하는 클래스이다.
- MVVM 뷰모델은 개념적인 아키텍처의 일부분을 말한다.

Data Binding
- MVVM은 데이터 바인딩을 사용한다.
- bundle.gradle -> dataBinding {enabled = true}을 추가해준다.
- bundle.gradle -> plugin에 kotlin-kapt 추가
(데이터 바인딩에서 코틀린의 애노테이션을 처리할 수 있다.)

Data Binding 사용2
- xml에서 <layout> 태그를 사용하면 이 레이아웃에 데이터 바인딩 한다는 것을 나타낸다.
- 레이아웃에 이 태그가 있으면 데이터 바인딩 라이브러리가 바인딩 클래스를 자동으로 생성한다. 기본적으로 이 클래스의 이름은 이름 끝에 Binding이 붙은 채로 자동 지정된다.
(예시 ActivityMainBinding)
- 액티비티에 바인딩 인스턴스를 인플레이트해준다.
(DataBindingUtil.setContentView(this, R.layout.activity_main))

에셋 가져오기
- 에셋은 리소스 자체라고 생각하면 된다. 즉 리소스처럼 APK에 포함되지만 시스템에서 특별한 구성(디렉터리 구조화와 참조 생성 등)을 하지 않은 리소스이다.
- 에셋은 구성하는 시스템이 없으므로 원하는 대로 에셋의 이름을 지정하거나 폴더 구조로 구성할 수 있다.
- 에셋의 단점은 참조를 하기 위한 구성이나 관리하는 시스템이 없으므로 장치의 화면 해상도, 언어, 방향 등이 달라지면 자동으로 대응할 수 없으며, 레이아웃 파일이나 다른 리소스에서 자동으로 사용할 수도 없다.
- app -> 오른쪽 마우스 클릭 -> Folder -> Assets Folder 생성 -> Assets Folder에서 Sub Directory 생성 -> 에셋 파일 넣어주기

에셋 사용하기
- 에셋은 AssetManager 클래스로 사용하며, Assetmanager 인스턴스는 어떤 Context에서도 생성할 수 있다.
- 이 앱에서 BeatBox 생성자는 AssetManager 인스턴스 참조를 인자로 받는다(private val assets: AssetManager)




데이터 바인딩하기
- 데이터 바인딩을 사용할 때는 레이아웃 파일에 데이터를 가진 객체를 선언할 수 있다.

데이터 바인딩을 객체 다이어그램으로 나타내면 아래와 같다.
    레이아웃 파일      ->  코틀린 객체
 (list_item_sound)        (Sound)

- 여기서는 음원이름을 각 버튼에 넣는다. 이 때 데이터 바인딩을 사용해서 list_item_sound 레이아웃 파일에 Sound 객체를 직접 바인딩한다.
- 그런데 이렇게 하면 아키텍쳐 관점에서 문제가 생긴다.

MVC 아키텍쳐
        뷰        ->      모델
 (list_item_sound)      (Sound)
                컨트롤러
             (MainActivity)
- 대부분의 아키텍처에서 하나의 클래스는 한 가지 책임만을 가지게 하는 것이 기본 원리이다.
- MVC의 경우에도 모델은 앱이 작동하는 방법을 나타내며, 컨트롤러는 모델과 뷰를 중재하면서 앱의 데이터를 보여주는 방법을 결정하고, 뷰는 화면에 데이터를 보여준다.

- 위와 같이 데이터 바인딩을 사용하면 각 아키텍쳐 요소의 역할 분담이 분명하게 이루어지지 않는다.
- 보여줄 뷰의 데이터를 준비하는 코드를 Sound 모델 객체가 갖게 되어 컨트롤러 역할을 하기 때문이다.
- 그러므로 데이터 바인딩을 제대로 사용하려면 ***뷰모델***이라는 새로운 객체가 필요하다. 그리고 이 객체는 보여줄 뷰의 데이터를 준비하는 방법을 결정하는 책임을 갖는다.

MVVM 아키텍쳐
        뷰       ->        뷰모델     ->      모델
(list_item_sound)    (SoundViewModel)      (Sound)

- MVVM에서는 보여줄 데이터를 형식화하기 위해 MVC에서 컨트롤러 클래스가 런타임 시에 했던 대부분의 일을 뷰모델이 담당하게 된다.
- 즉, 레이아웃에서 위젯들을 데이터와 바인딩하던 일을 뷰모델이 하게 된다.


뷰모델 생성하기
- 먼저 사용할 객체 참조를 가지는 속성을 추가한다.
- 레이아웃에서 버튼에 보여줄 제목을 찾는 title 속성도 추가한다.

뷰모델에 바인딩하기
예시)
    <data>
        <variable
            name="viewModel"
            type="com.example.beatbox.SoundViewModel"/>
    </data>
- 이 같이 정의된 viewModel은 list_item_sound의 자동 생성된 바인딩 클래스인 ListItemSoundBinding의 속성이 된다.
- 따라서 ListItemSoundBinding에서 viewModel 속성을 사용하여 뷰모델인 SoundVIewModel과 바인딩 할 수 있다.

 android:text="@{viewModel.title}"
- 버튼 제목을 바인딩 한다.

관찰 가능한 데이터
- 바인딩 데이터가 변경되면 뷰모델이 레이아웃 파일과 소통하게 만드는 것이 다음으로 할 일이다.
- 이렇게 하려면 뷰모델에서 데이터 바인딩의 Observable 인터페이스를 구현해야 한다.
- 이 인터페이스를 사용하면 바인딩 클래스가 뷰모델에 리스너를 설정할 수 있다.

BaseObservable
- 뷰모델인 SoundViewModel을 BaseObservable의 서브 클래스로 선언한다.
- SoundViewModel의 바인딩되는 속성에 @Bindable 어노테이션을 지정한다.
- 바인딩되는 속성의 값이 변경될 때마다 notifyChange() 또는 notifyPropertyChanged(Int)를 호출한다.


데이터 바인딩 추가로 알아보기
1. 람다식
- 레이아웃 파일에 지정한 바인딩 연산자인 @{} 내부에는 간단한 코틀린 표현식은 물론이고 람다식도 사용할 수 있다.
 android:onClick="@{(view) -> viewModel.onButtonClick()}"
2. 더 편리한 문법
- 데이터 바인딩에서는 큰 따옴표 안에 백틱(') 기호도 사용할 수 있다.
 android:text="@{'File name' + viewModel.title}"
- null처리 연산자도 넣을 수 있다.
 android:text="@{'File name' + viewModel.title ?? 'No file'}"
3. BindingAdapter
- 기본적으로 데이터 바인딩에서는 바인딩 표현식을 레이아웃 속성의 게터/세터 호출로 변환한다.
- 예를 들어 다음 표현식은 text 속성의 세터인 setText(string) 함수 호출로 변환되어 처리된다.
 android:text="@{'File name' + viewModel.title ?? 'No file'}"
- 그러나 이 정도로는 충분치 않고 특정 속성에 추가 처리가 필요할 때가 있다.
이 떄는 BindingAdapter를 사용한다.
@BindingAdapter("app:isGone")
fun bindIsGone(view : View, isGone : Boolean){
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}


LiveData와 데이터 바인딩
- LiveData와 데이터 바인딩은 데이터가 변경되는지 관찰하면서 변경될 때 반응하는 방법을 제공한다는 면에서 서로 유사하다.
- 실제로 LiveData와 데이터 바인딩을 같이 사용할 수 있다.
- LiveData를 사용하면 SoundViewModel이 BaseObservable의 서브 클래스가 되지 않아도 되며, @Bindable 어노테이션도 지정하지 않아도 된다.
- LiveData는 LifeCycleOwner가 필요하므로 여기서는 title 속성을 관찰할 때 사용할 LifecycleOwner를 데이터 바인딩 프레임워크에 알려주어야한다.








 */