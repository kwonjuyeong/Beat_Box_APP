package com.example.beatbox.ChapterMemo

/*
Chapter20
단위 테스트와 오디오 재생

MVVM 아키텍처가 매력적인 이유 중 하나는 단위 테스트(unit testing)가 쉽기 때문이다.

단위테스트는 앱의 각 단위가 제대로 작동하는지 검사하는 작은 프로그램들을 작성하는 것이다.
BeatBox의 단위는 클래스이므로 이 장에서 만들 단위 테스트에서는 해당 클래스들을 대상으로 테스트한다.

SoundPool 클래스
- 안드로이드 오디오 API를 쉽게 사용하도록 해주는 도구
- SoundPool 클래스는 많은 음원 파일을 메모리로 로드할 수 있으며, 재생하려는 음원의 최대 개수를 언제든 제어할 수 있다.
- SoundPool 인스턴스를 생성할 댸는 SoundPool.Builder 클래스의 build() 함수를 사용한다.
- setMaxStream(Int) 함수에서는 현재 시점에 재생할 음원의 최대 개수를 인자로 전달하여 지정할 수 있다.
- setAudioAttribute(AudioAttributes)를 사용하면 오디오 스트림의 다른 속성들을 지정할 수 있다.

에셋 사용하기
- Sound 객체는 에셋 파일 경로를 가지고 있다.
- 에셋 파일 경로의 파일을 열 때 File 클래스를 사용할 수 없고 반드시 AssetManager를 사용해야 한다.
- 경우에 따라서는 InputStream 대신 FileDescriptor가 필요할 수 있다.

음원 로드하기
- SoundPool에 음원을 로드한다. 오디오를 재생하는 다른 방법과 달리 SoundPool을 사용하면 응답이 빠르다.
- 따라서 음원 재생을 요청하면 즉시 재생이 시작된다.
- 로드할 각 음원은 자신의 정수 ID를 갖는다. 이 ID를 유지하기 위해 soundId 속성을 Sound 클래스에 추가한다.
- soundPool.load(AssetFileDescriptor, Int) 함수를 호출해 나중에 재생할 음원을 SoundPool에 로드한다.

음원 재생하기
- BeatBox 클래스에 play(Sound) 함수를 추가한다.

테스트 라이브러리 의존성 추가하기
- testImplementation 'org.mockito:mockito-core:3.3.3'
- testImplementation 'org.mockito:mockito-inline:3.3.3'

테스트 클래스 생성하기
- 단위 테스트를 작성하는 가장 편리한 방법은 테스트 프레임워크를 사용하는 것이다.
- 테스트 프레임 워크를 사용하면 안드로이드 스튜디오에서 테스트 코드를 더 쉽게 작성하고 실행할 수 있으며 결과 출력도 볼 수 있다.
- 안드로이드 테스트 프레임워크로는 JUnit이 사용되며, 안드로이드 스튜디오와 잘 통합되어 있다.
1. JUnit 테스트 클래스를 생성한다.
- ViewModel 클래스를 클릭한 후 안드로이드 메인 메뉴의 Navigete -> Test를 선택한다.
- Create New Test를 선택한 후 테스트 라이브러리를 선택하고 Ok를 누른다.

장치 테스트
- androidTest 폴더에 있는 테스트를 장치 테스트(instrumentation test)라고 한다.
- 장치 테스트는 안드로이드 장치나 에뮬레이터에서 실행된다.
- 앱이 배포된 후 APK가 실행될 시스템 프레임워크와 API를 대상으로 앱 전체를 테스트 할 수 있다는 것이 장치 테스트의 장점이다.,
- 그러니 장치 테스트는 해당 안드로이드 운영체제에서 실행되서 설정과 실행에 시간이 더 걸린다는 단점이 있다.

단위 테스트
- test 폴더에 있는 테스트를 단위 테스트(Unit test)라 한다.
- 단위 테스트는 안드로이드 런타임이 아닌 로컬 컴퓨터의 JVM(Java Virtual Machine)에서 실행되므로 빠르게 이루어진다.
- 하나의 클래스나 단위 기능을 별개로 검사함을 의미하며, 로컬 컴퓨터에서 실행되는 단위 테스트들은 test 폴더에 포함된다.
- 앱의 여러 클래스나 기능이 함께 작동하는 것을 테스트하는 통합 테스트(integration test)를 의미하기도 한다.

테스트 대상 설정하기
- 단위 테스트를 위해 test 폴더에 있는 테스트로 이동한다.
- setUp() 함수 내부에서는 테스트할 SoundViewModel의 인스턴스와 Sound의 인스턴스를 생성해야 한다.
- SoundViewModel이 음원 제목을 보여주는 방법을 아려면 Sound 인스턴스를 필요로 하기 떄문이다.

테스트 작성하기
- @Test 어노테이션이 지정된 테스트 클래스의 함수를 테스트라고 한다.
- MatcherAssert.assertThat() 함수와 is 함수를 같이 사용하며 '테스트 대상의 title 속성값이 Sound의 name 속성과 같아야함을 나타낸다
- test폴더 밑에 있는 SoundViewModelTest에서 오른쪽 마우스 버튼을 클릭하고 Run 'SoundViewModelTest'를 선택하면 단위 테스트가 실행된다.
- 테스트가 성공하면 Tests passed가 표시되고 / 실패하면 Test failed가 표시된다.

데이터 바인딩 콜백
- 클릭 리스너를 연결할 떄도 람다식으로 데이터 바인딩을 할 수 있다.
- android:onClick = "@{()-> viewModel.onButtonClicked()}"

음원 내리기
- 음원 재생이 끝나며 SoundPool.release()를 호출해 리소스를 해제해야 한다.


 */