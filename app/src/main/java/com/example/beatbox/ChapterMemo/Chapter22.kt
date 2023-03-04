package com.example.beatbox.ChapterMemo

/*
Chapter22
XML drawable

안드로이드에서는 화면에 그려지는 것을 drawable 이라고 하며 Drawable 클래스의 서브 클래스로 추상화된 형태이거나 비트맵 이미지일 수 있다.
이 장에서는 형태(shape) drawable, 상태 리스트(state list) drawable, 레이어 리스트(layer list) drawable을 알아본다.

형태 drawable
- resource에서 <shape> 태그를 사용한다.
- shape로 버튼 모양을 지정해준다.
- color로 색을 지정해준다.
ex)
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval"> //원형
<solid android:color="@color/dark_blue"/> //진한 파란색
</shape>

상태 리스트 drawable
- 기본 버튼과 버튼을 클릭했을 때 버튼 모양을 다르게 하고 싶을 때 사용한다.
- 우선 기본 버튼 디자인/눌렀을 때 버튼 디자인을 생성해준다.
- <selector> 태그에 두 개의 버튼을 넣어준다.
ex)
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/button_beat_box_pressed"
        android:state_pressed="true"/>
    <item android:drawable="@drawable/button_beat_box_normal"/>
</selector>

레이어 리스트 drawable
- 레이어 리스트를 사용하면 두 개의 XML drawable을 하나로 결합할 수 있다.
ex)
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item>
        <shape android:shape = "oval">
    <solid android:color="@color/red"/>
        </shape>
    </item>
    <item>
        <shape android:shape="oval">
        <stroke android:width="4dp"
            android:color="@color/dark_red"/>
        </shape>
    </item>
</layer-list>

XML drawable을 사용해야 하는 이유
- 버튼이 눌러졌을 때 상태를 나타내는 것은 필요하다. 따라서 상태 리스트 drawable은 어떤 안드로이드 앱에서도 중요한 컴포넌트이다.
- XML drawable은 유연성이 좋다. 따라서 여러 목적으로 사용될 수 있으며, 향후에 쉽게 변경할 수 있다.
- 레이어 리스트 drawable과 형태 drawable을 조합하면 이미지 편집기를 사용하지 않아도 복잡한 배경을 만들 수 있다.
- XML drawable은 화면 밀도와 무관하다.
*/