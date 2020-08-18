# Property, AuthenticationPrincipal 사용하기?
	설정파일을 읽고 현재 요청에 대한 사용자 정보를 조회하는 방법을 알아봅니다.
	API를 호출 할 때 내 정보를 json등으로 같이 넘기는 것은 Client에서 위변조를 할 가능성이 있습니다.
	
	현재 서버가 내어 준 Session정보를 토대로 하여 사용자 정보가 무엇인지를 가져오는 것이 좋은 방법입니다!

## 1. Controller 구현하기

![image-20200308180520840](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308180520840.png)


### 1-1. /openapi/~~ 경로
	@ResponseBody annotation을 사용해 String 형태로 답을 줍니다.
	즉! 화면 이동없이 API 호출처럼 그대로 문자를 response해줍니다.
	
	상단의 /openapi/~~ 경로는 앞서 Spring security의 adapter에서 ignore해주었습니다.
	따라서 로그인을 거치지 않고 접근할 수 있습니다.
	
	@AuthenticationPrincipal annotation을 통해 SecurityConfig에서 User를 상속받은 객체를 자동으로 autowired해줍니다.
	
	getIp 메소드를 통해 securityMember의 Ip정보를 가져오고
	application.yml에 정의된 대로 logging.path를 가져옵니다.
	
	결과적으로는 정상적으로 결과값을 가져오지 못합니다!!
	앞서 설명한대로 ignore처리해서 로그인을 거치지 않아 Member정보가 없기 때문!!

![image-20200308181147405](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308181147405.png)

	login 화면을 거치지 않고 바로 출력되며 SecurityMember는 가져오지 못했습니다.

### 1-2. /getMember 경로
	위와 다르게 ignore처리되지 않은 경로이므로 login UI를 먼저 거칩니다.
	따라서 SecurityMember 객체에 정보를 채웁니다.

![image-20200308181358594](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308181358594.png)

	로그인을 거친 후 접속하면

![image-20200308181424240](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200308181424240.png)

	이와 같은 값을 반환합니다.
	IP주소가 이상한데요??
	-> 아닙니다. IPv4가 아닌 IPv6형태로 값을 반환합니다.
	IPv4로는 127.0.0.1 입니다. 따라서 정상적으로 반환됨을 확인할 수 있습니다.
# Made by 장경석