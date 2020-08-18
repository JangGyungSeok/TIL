# thymeleaf 사용하기

## 1. 의존성 및 설정 추가

![image-20200413111111299](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413111111299.png)

	thymeleaf와 security를 html에서 사용하기 위한 의존성 추가!

![image-20200413111150488](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413111150488.png)

![image-20200413111317745](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413111317745.png)

	이와 같은 설정은 application.properties 또는 application.yml에 정의하여 설정을 변경할 수 있다.
	또는 아래 사진과 같이 java파일로 설정할 수 있다.
	
	다만! 설정을 하지 않아도 default값으로 설정이 되므로 문제는 없다.
	필요한 사항만 변경을 하도록한다.

## 2. Controller작성 및 HTML 파일 경로

![image-20200413111430193](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413111459099.png)![image-20200413111533386](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413111533386.png)

	이와 같이 Controller에서 경로를 생략하고 입력할 경우
	
	resource/templates경로로 자동 매핑된다.
	이는 thymeleaf default설정에 의한 값이다. 변경이 필요하면 변경해도 좋다.
	
	또한 css,font,images,js 파일은 여타 프로젝트와 같이 정적데이터를 저장하는 static경로에 저장해 사용하도록 한다.

## 3. HTML에서의 사용
	HTML에서 spring security의 권한정보에 따라 다른 컨텐츠를 출력해야하는 경우가 있다. 예를들면 로그인 시 '로그인버튼 -> 로그아웃버튼' 으로 변경한다.
![image-20200413112117533](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413112117533.png)

	이를 HTML태그 내에 사용한다. spring security의 자원을 사용하겠다는 뜻이다.

![image-20200413112152783](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413112152783.png)

	이러한 형식으로 security의 정보에 따라 View에 대한 권한 설정을 할 수 있다.



# Made by 장경석