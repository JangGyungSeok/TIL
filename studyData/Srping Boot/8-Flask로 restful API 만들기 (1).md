# Flask로 restful API 만들기 (1)

## 기본적인 flask 구조를 만들어보자!

### 1. VSCode Python 환경설정



![image-20200316171747015](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316171747015.png)

![image-20200316171938525](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316171938525.png)

	Python을 먼저 설치해준다.
	필자는 가장 최신버전을 받았다. (작성일 기준 3.7.7)
	
	3.7.7 버전을 클릭 후 자신의 운영체제에 맞는 버전을 설치한다.
	tensorflow는 64bit에서만 지원한다고한다.


![image-20200316171607189](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316171607189.png)

	VSCode에서도 Python 사용을 위해 설치가 필요하다.
	이와 같이 VSCode상에서 Python디버그 등을 위해 Extension을 install하였다.

### 2. VSCode상에서 기본적인 flask 코딩하기

![image-20200316172153054](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316172153054.png)

	처음으로 할 것은 flask 사용을 위해 flask를 다운로드 하는 것이다.
	cmd 또는 VSCode의 terminal을 통해 다운로드 하도록 한다.

![image-20200316172336324](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316172336324.png)

	필자가 작성한 기본적인 flask 코드이다.
	@app.route('값')을 통해 url에 대한 html파일 등 반환값을 매핑해준다.

![image-20200316172507812](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316172507812.png)

	실행하게 되면 이와 같은 내용이 TERMINAL에 보여진다.
	보여진 URL을 복붙해보라는 얘기이다.

![image-20200316172616607](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316172616607.png)

	앞서 작성한 flask 코드에 맞게 /main에 해당하는 url을 호출했다.
	값이 정상적으로 나옵니다.

![image-20200316172659994](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200316172659994.png)

	이번에는 /testjson 을 호출해보도록한다.
	jsonify를 통해 json형태로 변환된 값이 호출된다!!
	이를 통해 spring boot환경에서 분석 결과값을 변환한 json형태의 값을 호출할 예정이다.

# Made By 장경석