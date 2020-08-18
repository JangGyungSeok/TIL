# Spring Boot 환경에서 이미지 저장하지 않고 다루기!!

## 1. Multipart / form-data (MultipartFile)
	사용자가 업로드한 File을 핸들러에서 손쉽게 다룰 수 있도록 도와주는 Handler의 매개변수입니다.
	UI에서 사용자가 업로드한 파일을 Controller에서 사용하기 위해 form은 Multipart/form-data형태로 받아오고 이를 Spring Boot 소스에서는 MultipartFile형태로 받아오게 된다.

![image-20200326110725324](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326110725324.png)

	form은 이런 형태로 작성한다.

![image-20200326110826651](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326110826651.png)

	RequestParam으로 file을 가져온다. 형태는 MultipartFile이다.
	Multipart형태로 받은 파일은 Base64의 encodeBase64를 통해 byte[] 형태의 데이터로 전환된다.
	또한 이를 String형태로 변환했다. (jsp에서 사용하기 위함)
	
	StringBuilder를 사용해 "data:image/jpg;base64,"를 앞에 붙여준다.
	이는 jsp에서 src에 입력했을 시 Base64소스 뿐 아니라 형태도 정해주어야 사용할 수 있기 때문이다.
	
	이를 model 객체를 사용해 uploadedImage라는 변수로 넘겨준다.
	또한 return 되면서 upload.jsp를 호출한다.

![image-20200326111341033](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326111341033.png)

	<image>태그 안에 src속성을 Controller에서 처리되어 넘어온 uploadedImage객체를 넣어준다.
	
	이는 이미지로 출력된다!!

## 2. 과정설명 , 이유설명
	 (1) 과정
	 사용자가 form을 통해 이미지를 업로드한다!
	 이를 MultipartFile형태로 받아온다
	 이미지를 Base64형태로 변환한다.
	 StringBuilder를 사용해 Base64에 형태를 붙여준다.
	 Model객체에 addAttribute 해준다.
	 upload.jsp로 화면이 전환된다.
	 Image태그의 src속성에 Model에 넘겨준 객체를 사용한다.
	 이미지가 출력된다.
	 
	 (2) 이유
	 파일을 서버에 저장하며 사용하지 않은 이유가 있다.
	서버 용량을 고려한다면 Image업로드마다 쌓이게 될 이미지파일이 걱정이다.
	이를 해소하고자 업로드 받은 Image를 파일이 아닌 변수로만 갖도록 하였다.

## 3. 동작사진

![image-20200326111936008](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326111936008.png)

	이러한 이미지를 넘겨줄 것이다.

![image-20200326112001499](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326112001499.png)

	파일 선택 후 업로드 버튼을 누른다.

![image-20200326112031353](C:\Users\jusku\AppData\Roaming\Typora\typora-user-images\image-20200326112031353.png)

	"/upload" 로 매핑된 메소드가 실행되며 Base64형태로 넘어온 이미지를
	출력한다!!


# Made by 장경석