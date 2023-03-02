package com.example.beatbox.ChapterMemo

/*
Chapter21
스타일과 테마

색상 리소스
- res/values/color에 색상을 추가한다.

스타일
- 스타일은 위젯에 적용할 수 있는 속성들의 집합이다.
- 프로젝트 도구 창의 res 폴더에서 오른쪽 마우스 버튼을 클릭한 후 New -> Android Resource File 선텍 -> File name에 styles를 입력하고 OK 버튼 클릭
- <style>태그에 이름을 입력하고 <item> 태그에 속성을 입력한다.
- xml에서 적용할 위젯에 style = "@style/적용할스타일"을 추가해준다.

스타일의 상속
- 스타일은 상속도 지원하므로 다른 스타일로부터 상속받아 오버라이드 할 수 있다.
- BeatBoxButton이라는 스타일을 상속받는 스타일을 만들고 싶다면 name에 BeatBoxButton.Strong 처럼 사용하면 된다.
- 위와같이 쓰면 BeatBoxButton의 속성을 상속받아 BeatBoxButton 속성을 가지면서 Strong의 특성도 가질 수 있다.

테마(Theme)
- 스타일은 속성들을 한곳에서 정의한 후 원하는 어떤 위젯에도 적용할 수 있다.
- 하지만 스타일의 단점은 일일이 위젯마다 적용해야 한다는 것이다. 만일 여러 레이아웃에 많은 버튼을 갖는 복잡한 앱이라면 모든 버튼에 일일이 추가해야하기 떄문에 번거로울 것이다.
- 테마는 일련의 속성들을 한곳에서 정의할 수 있으며, 스타일과는 달리 그 속성들이 앱 전체에 자동으로 적용된다는 특징이 있다.

테마 변경하기
- manifest.xml 파일을 열어 application 태그의 theme 속성을 살펴본다.
- android:theme 속성에 적용된 테마를 살펴보면 어떤 테마인지 알 수 있고, 적용된 테마는 themes.xml 파일에 선언되어 있다.
- 새 프로젝트를 생성할 떄 'Use legacy android.support libraries'를 선택하면 AppTheme 대신 AppCompat테마가 지정된다.
(AppTheme은 Theme.AppCompat.Light.DarkActionBar로부터 속성을 상속받는다.)

AppCompat 라이브러리에는 다음 세가지 주요 테마가 있다.
1. Theme.AppCompat - 어두운 분위기의 테마
2. Theme.AppCompat.Light - 밝은 분위기의 테마
3. Theme.AppCompat.Light.DarkActionBar - 어두운 앱 바를 갖는 밝은 분위기의 테마

앱 테마 색상 변경하기
- themes.xml 의 속성들을 변경한다.
- 스타일과 다른 점은 테마는 어떤 위젯도 사용할 수 있는 속성을 가져야하기 때문에 textStyle과 같은 범위보다 넓은 범위의 속성을 사용한다 예를 들면 colorPrimary같은 것이다.

테마 속성 오버라이드 하기
- 테마 속성을 오버라이드하려면 어떤 안드로이드 테마 속성들이 있는지 알아야 한다.
- 그런데 어떤 테마 속성들이 있는지, 오바리이드 가능한 것은 어떤 것인지, 심지어는 그런 속성들이 무슨 일을 하는지에 대해 문서화된 것들이 없다.

필요한 테마 찾기
- 테마의 배경색을 변경하려면 배경색 속성을 오버라이드하면 된다.
- 배경색 속성의 이름을 찾으려면 먼저 부모 테마인 Theme.AppCompat 에 배경색을 의미하는 이름의 속성이 설정되어 있는지 살펴봐야 한다.
- 만일 Theme.AppCompat 테마에 그런 속성이 없으면 다시 이 테마의 부모 테마로 이동해 확인하는 식으로 계속 찾아야 한다.
- 부모테마로 찾는 방법은 Theme.AppCompat에 Ctrl + 클릭 -> 다음 테마 -> Ctrl + 클릭을 반복하여 속성이 나올 때 까지 상속받은 부모 테마를 추적해야 한다.
예시)
windowBackground 속성을 찾기 위해
- Theme.AppCompat
- Base.theme.AppCompat
- Base.V7.Theme.AppCompat
- Platform.AppCompat 을 거치게 되면 찾게 된다.

버튼 속성 변경하기
- 위 예시로 들어보면 테마 Base.V7.Theme.AppCompat에 buttonStyle 속성이 있다.
- 이를 바꿔준 후 원래 있던 style을 삭제해준다.


 */