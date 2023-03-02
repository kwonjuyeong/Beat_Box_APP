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


 */