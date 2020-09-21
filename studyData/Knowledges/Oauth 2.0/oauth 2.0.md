# oauth 2.0

## 1. oauth란?

	대부분의 서비스는 인증(Authentication)과 권한(Authorization)에 대한 기능이 필요하다.
	인증과 권한에 대한 기능은 다양한 방법으로 제공되고 있는데 대표적인 방법으로 oauth를 사용한다.
	
	oauth는 서버와 클라이언트 사이에 인증을 완료하면 권한부여의 결과물로 access token을 전송한다.
	클라이언트는 access token을 header에 포함한 형태로 접근권한을 얻을 수 있다.
	서버는 header에 포함된 access token을 확인해 권한을 확인하고 결과값을 반환합니다.
	
	서버는 access token을 기준으로 인증, 권한관리를 진행해 cookie와 session으로 클라이언트의 상태정보를 유지할 필요가 없습니다.


## 2. oauth 2.0

### 2-1. 설명
	현재 대다수가 사용하고 있는 oauth는 2.0버전이다.
	
	oauth 2.0에서 바뀐점은 크게 다음과 같다.
	
	1. 모바일 어플리케이션에서도 사용이 용이해졌다.
	2. 반드시 HTTPS를 사용해 보안이 강화되었다.
	3. Access Toekn의 만료기간이 생겼다.

### 2-2. oauth 2.0 구성요소

![image-20200918151539734](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200918151539734.png)

	1. Resource Owner(=User) : 사용자
	2. Client : 어플리케이션 서버(User가 아니다.)
	3. Authorization Server : 권한을 관리하는 서버이다. Access Token, Refresh Token을 관리한다.
	4. Resource Server : oauth 2.0을 관리하는 서버(google, facebook,naver 등)의 자원을 관리하는 서버이다. oauth 2.0관리 서버 자체 api를 의미한다.

### 2-3. 인증방식

	인증방식은 크게 4가지이다.
	
	1. Authorization Code Grant
	2. Implicit Grant
	3. Resource owner Password Credentials Grant
	4. Client Credentials Grant
	
	각 인증 방식에는 장단점이 존재한다.
	다만 가장 많이 쓰는 형태는 Authorization Code Grant(인증코드 권한부여 방식)

#### 2-3-1. Authorization Code Grant

![image-20200918151558478](C:\Users\user\AppData\Roaming\Typora\typora-user-images\image-20200918151558478.png)

	1. Resource Owner(사용자)가 Client(서버)에 인증 요청한다.
	2. Client는 Authorization Request를 통해 Resource Owner에게 인증할 수단을 보낸다.
	3. Resource owner는 해당 Request를 통해 인증을 진행하고 인증을 완료했다는 신호로 Authorization Grant를 url에 실어 Client에게 보낸다.
	4. Client는 해당 권한증서(Authorization Grant)를 Authorization Server에 보낸다.
	5. Authorization Server는 권한증서를 확인한 후 Access Token, Refresh Token을 반환한다.
	6. Client는 해당 Access Token을 DB에 저장하거나 Resource Owner에 넘긴다.
	7. Resource Owner가 Resource Server에 요청할 때 Header에 Token값을 포함해 요청한다.
	8. Resource Server는 Access Token의 유효여부를 확인 후 resource를 반환한다.
	9. Access Token이 만료되었거나 위조외었다면 Authorization Server에 Refresh Token을 보내 Access Token을 재발급받는다.
	10. 이후 반복


