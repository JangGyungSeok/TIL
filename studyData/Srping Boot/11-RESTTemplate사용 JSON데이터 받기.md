# RESTTemplate사용 JSON데이터 받기

## 1. 의존성 추가

![image-20200413112644677](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413112644677.png)

	Json형태의 데이터를 다루기 위한 jackson관련 의존성,
	RESTTemplate 및 http관련 자원을 사용하기 위한 httpclient 의존성 추가

## 2. http관련 설정하기

![image-20200413112829148](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413112829148.png)

	HttpConnectionConfig 자바 설정파일을 추가해준다.
	이는 외부 연결에 대한 설정을 다루는 파일이다.
	현재 연결시간, 읽기시간, 커넥션 수 등을 설정해준 모습이다.

## 3. RestTemplate사용을 위한 준비

![image-20200413113019661](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413113019661.png)

	이와 같은 RestTemplate를 사용하기 위한 Service를 작성해준다.
	이와 연결되는 RestTemplateUtil 또한 작성해준다.
	RestTemplateUtil에는 직접적으로 restTemplate를 사용하는 부분이다.

![image-20200413113313841](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413113313841.png)

	Json형태로 데이터를 응답받기 위한 형태이다.
	restTemplate. 을 입력후 사용가능한 메소드를 확인해보면 좋다.
	메소드명이 상당히 직관적이기 때문에 한눈에 알아들을 수 있을것이다.
	
	현재는 localhost의 5000번 포트로 열려있는 서비스에 요청을 보내는 모습이다.
	imgString이라는 데이터를 전송하고 결과물은 JsonVO.class형태로 받는다.

![image-20200413113546021](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200413113546021.png)

	JsonVO의 형태이다. 이는 요청에 대한 응답으로 Json형태의 데이터에서
	@XxmlElement annotation을 통해 Json의 key값과 변수를 1:1매핑해준다.



	 Controller를 작성하여 개별적으로 사용해보도록한다.

# Made by 장경석