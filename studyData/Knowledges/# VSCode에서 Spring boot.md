# VSCode에서 Spring boot 해보기!

## 1. Spring boot 불편한점 해결방법!
	VSCode의 Spring boot 는 기존 STS와는 다르게 자동으로 생성해주는 것이 미약합니다.
	사용해보니 프로젝트 생성부터 외부에서 해주는 것이 좋았습니다.
	Spring Initializer -> https://start.spring.io/
	를 통해 GUI로 원하는 버전, 의존성을 추가할 수 있습니다.
	
	webapp/WEB-INF 경로는 자동으로 생성되지 않으니 생성해준 후 Controller와 jsp연동을 해줍니다.
	
	java 파일은 생성 시 기본 템플릿을 생성해주지 않습니다.
	
	HTML 파일은 ! <- 입력 시 기본 HTML 형태의 소스를 자동입력 할 수 있습니다.