# Spring Boot에서 민감정보 은닉하기

## 1. DB 접근정보 분리하기
	기존 application.properties에 정의해놓은 datasource에 대한 data들을 또다른  properties파일로 분류해 작성해준다.
	본 필자는 config.properties로 작성했다.
	이렇게 분류한 config.properties 파일은 별도 local 경로에 저장하던지 하여 프로젝트를 공유하거나 할 때에 같이 올라가지 않도록 한다.
	물리적으로 정보를 은닉하는 방법이다.
	
	필자는 이해를 돕기위해 classpath 경로에 만들어두었다.
	이해했다면 따로 빼서 관리하도록 하자!
	간단하니 사진은 생략!

## 2. DB접근정보 Configuration 객체 만들기
	config.properties로 분류해 작성해두었다.
	이는 DB접근에 대한 내용으로 DB접근 시 필요한 정보이다.(당연히)
	DB접근이 필요한 소스에서 Bean객체화 하여 사용할 것이다.
	
	첫번째로 @Configuration을 이용해 등록하도록한다.
	필자는 GlobalpropertySource.java파일로 작성했다.

![image-20200307181137376](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200307181137376.png)

	 PropertySource로 정의되어있는 설정파일의 경로를 입력한다.
	@value annotation을 통해 PropertySource경로에 정의되어있는 값에 접근할 수 있다.
	getter를 생성하여 DataSourceBuilder로 생성할 수 있도록 만들어놓는다.

## 3. DB접근정보 실질적으로 사용하기!
	현재 작성한 소스에서 DB접근을 위해 DataSource에 접근하는 부분이 있다.
	demo/db/DatabaseConfig.java경로이다.

![image-20200307181554479](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200307181554479.png)

	@Autowired를 사용해 GlobalPropertySource를 불러온다.
	이를 사용해 @Bean,@Primary인 customDataSource() 생성자를 작성한다.
	이는 DataSource자료형을 반환하며 DataSourceBuilder를 통해 생성한다.
	앞서 정의해놓은 getter를 사용해 만들어준다.
	
	이 정보를 sqlSessionFactory에서 사용해주도록하자
	
	이렇게 설정해놓고 실행시켜보도록 한다.
	민감정보 은닉하기 완료!

# Made by 장경석