# JDBC 관련 접근 log로 남기기!

	 Spring Boot를 실행하면 로그가 나오는 것을 볼 수 있습니다.
	Console View에 말입니다.
	Spring Boot는 기본적으로 Logback을 포함하기 때문입니다.
	하지만! Console에서의 Log는 의미가 없습니다.
	
	서버에 문제가 발생한 경우 이를 확인할 방법이 없습니다.
	이를 특정 폴더에 파일 형태로 남기게 되고 이를 날짜별로 분류하거나 용량별로 구분합니다.

## 1. DB설정 정보 수정하기 및 application.properties 수정하기

![image-20200308175540088](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308175540088.png)

	기존에 작성했던 Config.properties를 수정합니다.

![image-20200308175629040](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308175629040.png)

	또한 application.properties를 application.yml파일로 바꾸어줍니다!
	각각의 프로파일에 대한 logging path를 정의해준다고 합니다.

## 2. logback 관련 설정파일 작성!

### 설정파일 경로

![image-20200308174921280](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308174921280.png)

### logback-spring.xml

![image-20200308174957981](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308174957981.png)



### appender-console.xml

![image-20200308175139393](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308175139393.png)



### appender-file.xml

![image-20200308175158832](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308175158832.png)



	로그를 남기는 형태를 정의해줍니다.
	이와같이 설정해줍니다. 자세한 이해를 하지는 못해서 상세내용은 추후에 추가하겠습니다.

## 3. Log확인!!
	하루가 지난 후 project에 기본 log-file을 확인해보니!

![image-20200308175933822](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308175933822.png)

	20200307에 작성한 logfile이 남아있는 것을 볼 수 있었습니다!!
	날짜별로 구분된 logfile확인!

# Made by 장경석