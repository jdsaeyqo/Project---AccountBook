# 프로젝트 소개
- 제목 : CashBook
- 구분 : 개인 프로젝트
- 개발환경 - 안드로이드 스튜디오
- 사용 언어 - Kotlin
- 내용  
수입/지출 기록  
합계 표시  
간단 메모장 적용

---
# 개요

![image1](https://github.com/jdsaeyqo/Project---AccountBook/blob/master/app/src/main/res/drawable/_cashbook_image1.jpg)
 
- Firebase 이메일 로그인, 구글 로그인
- 네이버 로그인  

수입/지출 | 메모
:------:|:-------:
![image2](https://github.com/jdsaeyqo/Project---AccountBook/blob/master/app/src/main/res/drawable/_cashbook_image2.jpg)|![image3](https://github.com/jdsaeyqo/Project---AccountBook/blob/master/app/src/main/res/drawable/_cashbook_image3.jpg)

- RecyclerView  
- Room 활용 내부 데이터베이스 저장

수입/지출 추가 | 메모 추가
:------:|:-------:
![image4](https://github.com/jdsaeyqo/Project---AccountBook/blob/master/app/src/main/res/drawable/_cashbook_image4.jpg)|![image5](https://github.com/jdsaeyqo/Project---AccountBook/blob/master/app/src/main/res/drawable/__cashbook_image5.jpg)  

- LiveData로 관찰하여 데이터 값 수정 시  메인 프래그먼트에 데이터 반영

---
# Library
~~~kotlin  
apply plugin: 'com.google.gms.google-services'
apply plugin: 'kotlin-kapt
//Naver
    implementation 'com.naver.nid:naveridlogin-android-sdk:4.2.6'
    
// support
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'    
    
// Firebase
    implementation platform('com.google.firebase:firebase-bom:25.12.0')
    implementation 'com.google.firebase:firebase-auth-ktx'
    implementation 'com.google.android.gms:play-services-auth:19.0.0'
    implementation 'com.google.firebase:firebase-auth:20.0.1'
    implementation 'com.google.firebase:firebase-database:19.6.0' 
    
// Room
    implementation "android.arch.persistence.room:runtime:2.2.5"
    kapt 'android.arch.persistence.room:compiler:1.1.1'
    androidTestImplementation "androidx.room:room-testing:2.2.6"

//LiveData,ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    implementation "androidx.activity:activity-ktx:1.2.2"
    implementation "androidx.fragment:fragment-ktx:1.3.2"
~~~

