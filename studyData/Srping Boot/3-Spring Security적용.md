# Spring security 사용하기!

### 1. DB에 계정 관련 테이블 설정하기
```sql
# db사용을 명시적으로 설정한다.
use testdb;

# 계정 테이블은 계정정보인 user과 권한정보인 authority 테이블로 나눴다.

# user 테이블은 보통 이러한 컬럼을 넣는다고 한다.
CREATE TABLE `user` (
    `username` VARCHAR(20) NULL DEFAULT NULL,
    `password` VARCHAR(500) NULL DEFAULT NULL,
    `name` VARCHAR(20) NULL DEFAULT NULL,
    `isAccountNonExpired` TINYINT(1) NULL DEFAULT NULL,
    `isAccountNonLocked` TINYINT(1) NULL DEFAULT NULL,
    `isCredentialsNonExpired` TINYINT(1) NULL DEFAULT NULL,
    `isEnabled` TINYINT(1) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

# authority 테이블은 username과 권한정보만을 담고있다.
# 여기서 username은 ID입니다.

CREATE TABLE `authority` (
    `username` VARCHAR(20) NULL DEFAULT NULL,
    `authority_name` VARCHAR(20) NULL DEFAULT NULL
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


# abc라는 user의 권한을 ADMIN과 USER로 추가해보았다.
INSERT INTO `authority` (`username`, `authority_name`) VALUES
    ('abc', 'ADMIN'),
    ('abc', 'USER');

# 계정 정보를 넣는다.
# password가 왜 저렇게 긴지 궁금하다!
# 그 이유는 Spring security가 hash값으로 변환해서 password를 확인하기 때문이다.
# 저 긴 password는 abcd를 hash값으로 바꾼 값이다.

INSERT INTO `user` (`username`, `password`, `name`, `isAccountNonExpired`, `isAccountNonLocked`, `isCredentialsNonExpired`, `isEnabled`) VALUES
    ('abc', '$2a$10$zNM1N.WnfC1Sq.vkqieCnuEfE3sZ3Hwo6.ytaSBtFTyg33qr2oI2G', 'JKS', 1, 1, 1, 1);

# 잘들어갔는지 확인해보자.
SELECT * from authority;
SELECT * from user;
```

### 2. DB설정에 맞게 Spring Boot 소스 작성하기



#### 2-1. Domain 생성

![image-20200229172148480](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229172148480.png)

	DB의 테이블에 맞는 Domain(java에선 VO객체)을 생성한다.
	user 테이블에 맞는 객체를 생성한다. (Authority는 생성하지 않는다.)
	Authority는 username을 key값으로 접근만한다.
	
	DB 명과 혼동을 방지하기 위해 Member라는 클래스로 정의했다.
	메소드는 getter와 setter만 정의했다. (소스참고)



#### 2-2. Mapper 구현

![image-20200229172312322](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229172312322.png)

	DB접근은 앞서 배운 Mybatis의 방식을 사용한다.
	따라서 interface로 Mapper의 내용물을 선언만 해준다.



#### 2-3. mapper 메소드 정의 xml파일 정의

![image-20200229172457491](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229172457491.png)

	/src/main/resource/mybatis/mapper/ 경로에 새로운 xml파일을 생성한다.
	
	'이미 정의되어있는 xml파일에 다른 namespace를 사용하는 mapper를 저의하면 되는것이 아닌가??'' 하는 의문이 생긴다!!
	
	하지만 안된다!
	한 xml파일에는 한 namespace만을 사용한다.
	
	위 캡처에는 readUser와 readAuthority를 읽어오기만 한다.(username을 이용)
	이유는 username(즉 ID)는 유일성을 가진다. 다만 password는 다른 유저와 겹칠 수 있다.
	따라서 유일성을 가지는 username을 가지고 접근하는 것이다.



#### 2-3 Controller 구현

![image-20200229172903560](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229172903560.png)

![image-20200229173018325](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229173018325.png)

![image-20200229173124515](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229173124515.png)

	여기까지는 Spring security를 적용하지 않았다.
	
	Mybatis를 사용해 MariaDB에 접근만 했다.
	
	여기까지 결과만 확인해보자.





### 3. Spring security 적용!



#### 3-1. 의존성(Dependency) 추가

![image-20200229173210028](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229173210028.png)

	Spring security 사용을 위해 의존성을 추가한다. (너무도 당연!)



#### 3-2. Member class 수정하기!

```java
// 외부참조
import org.springframework.security.core.GrantedAuthority;
// 상태변수 추가
private Collection<? extends GrantedAuthority> authorities;
// 이에따른 메소드 추가
public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
}
 
public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
}
```

	Security의 User를 구현하는 과정에서 Authority 정보가 필요하고 이를 위한 추가입니다.
	
	위 내용물들을 Member.java에 적절한 위치에 추가합니다.



#### 3-3. org.springframework.security.core.userdetails.User 를 상속받는 Domain 생성

![image-20200229173706152](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229173706152.png)

	 앞서 작성한 Member class는 그저 DB형태에 맞게 구현한 객체이고 Spring에서 인정하는 User의 형태는 별도로 존재한다.
	 
	 이것은 상속받은 User객체입니다.
	 앞서 만든 Member class와 매핑해주어 사용해봅니다.



#### 3-4. Service 구현하기

![image-20200229173957276](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229173957276.png)

	UserDetailsService를 상속받는 구현체를 만듭니다.
	
	중요!!! -> loadUserByUsername 함수
	username을 파라미터로 해당 사용자의 존재여부를 증명하는 부분입니다.



#### 3-5. WebSecurityConfigurerAdapter를 상속받는 Adapter 구현

![image-20200229174319257](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229174319257.png)

	이부분은 암호화방식인 password encoder를 정의합니다.
	또한 각종 요청 ignore처리, login page에 대한 처리가 정의됩니다.
	
	우선은 간단히 작성했지만 더 작성할 일이 있을겁니다.



#### 3-5. Spring security 적용 확인하기!

![image-20200229174517447](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229174517447.png)

	현재 Controller에 정의된 url 어느곳에 접근해도 로그인을 하지 않았다면 login UI로 이동하게 됩니다.
	
	현재 DB에 저장해놓은 username (abc), password (해쉬화된 abcd)으로 접속확인을 위해 abc, abcd를 입력합니다.
	
	그럼 정상동작!!!





### 4. 필요한 지식들



#### 4-1. 내가 작성한 소스들 경로 사진!

![image-20200229174837342](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229174837342.png)

	이렇게 정리해놨습니다.



#### 4-2. Mybatis에서 mapper객체를 bean으로 정의해야한다는데요??

![image-20200229175002637](C:\Users\JKS\AppData\Roaming\Typora\typora-user-images\image-20200229175002637.png)

	상단 @MapperScan 어노테이션을 보면 security.Mapper 객체를 스캔한 것을 볼 수 있습니다.
	
	그럼 Autowired 정상작동합니다.



# Made By 장경석