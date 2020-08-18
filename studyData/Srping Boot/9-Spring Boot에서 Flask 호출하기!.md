# Spring Boot에서 Flask 호출하기!

## 1. 의존성 추가하기

![image-20200326103402022](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326103402022.png)

	 httpcomponents는 외부 요청을 주고받을 수 있는 여러 변수형식, 메소드를 갖고 있는 의존성이다. 추가하도록한다.

## 2. Http연결 정의
	HTTP형태 연결에 대하여 여러 설정을 할 수 있다. 코드를 참고한다.

![image-20200326103643444](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326103643444.png)

	HttpConnectionConfig를 정의한 모습이다.
	정의를 하지 않는다면 기본값으로 설정된다.
	RestTemplate 형식으로 HTTP 요청을 수행한다. 이는 RestTemplate의 설정을 변경한다고 볼 수 있다.

## 3. RestTemplateService 정의하기

![image-20200326104031369](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326104031369.png)

	이는 RestTemplateService 정의한 모습이다.
	Return 형태를 변경함으로 Xml형식, Json형식, String형식 등 원하는 형태로 응답을 받을 수 있다. 물론! RestTemplateUtil에서 조작이 필요하다.
	필자는 테스트를 위해 String형태로 요청 결과값을 받는 메소드만을 남겨놓았다.

## 4. RestTemplateUtil 정의하기
	 실질적으로 RestTemplate을 활용해 요청을 주고받는 동작을 하는 부분이다.
	요청 방식은 restTemplate.방식For결과타입 메소드 명으로 구분되어있다.

![image-20200326104423573](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326104423573.png)

	필자는 post형태의 Entitiy결과값을 요청하는 메소드를 사용했다.
	매개변수로 요청할 URI, 넘겨줄 데이터(필자는 사진 Base64 문자열을 보냈다.), 결과 형식을 입력해준다.
	현재 Flask 테스트용 API의 URI, String형태로 변환한 이미지, 반환타입을 명시해주었다.

## 5. Flask 에서 요청 받기
![image-20200326104650896](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326104650896.png)

	Flask 소스에는 request.get_data() 라는 메소드가 보인다. 요청과 함께 넘겨준 데이터를 받아올 수 있다. 이는 request.get_json() 도 있는데 json형태로 여러 데이터를 넘겼을 경우 사용할 듯 싶다.
	현재는 데이터를 1가지만 보내기 때문에 request.get_data()를 사용했다.
	return 값으로 "응답메시지" 문자열을 반환한다.

## 6. Controller 정의하기

![image-20200326105003419](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326105003419.png)

	 필자는 testimg.html로 /testimg태그를 매핑해두었다.

![image-20200326105154355](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326105154355.png)

	추후 Flask API 서버로 이미지를 전송하기 위하여 Form형태로 기본 UI를 구성했다.
	이 Form의 action값은 "upload"이다.
	Controller의 upload로 매핑되어 있는 부분을 호출한다.

![image-20200326105513768](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326105513768.png)

	집중할 부분인 이 부분이다. Autowired 되어있는 restTemplateService의 addData를 호출한다.
	호출한 값을 ResponseEntity<String>형태로 받아왔다.
	또한 이를 Terminal에 print해보았다.
	
	restTemplateService의 addData를 호출하고 -> RestTemplateUtil의 post를 호출한다. -> Util에서는 restTemplate.postForEntity를 통해 Flask API의 URI를 호출한다.
	이는 Flask의 testapi로 정의된 route를 호출한다.
	요청을 받은 FlaskAPI는 return 값으로 "응답메시지" 문자열을 반환한다.
	결과값은 ResponseEntity<String> a 에 들어온다.
	
	이를 Terminal에 출력해봤다.


​	![image-20200326105850567](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326105850567.png)

	결과값을 확인하기 위해 난잡하게 print를 찍어댔지만
	"응답메시지" 문자열을 출력한 모습이다.
	
	Spring Boot에서 RestTemplate를 통해 Flask API를 호출했다!!!

# Made by 장경석