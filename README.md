# spring-security-and-OAuth
스프링 시큐리티에서 OAuth 사용
> 기간 : 2022-12-18 ~ ???

## 기록
> 스프링 시큐리티를 활용해 blog를 만들면서 시큐리티에 대해 더 공부해야함을 알았다. 그래서 좀 더 체계적으로 하나씩 공부해서 이곳에 기록할 생각이다.

### 2022-12-20
> 우선 security 6.0.0이 되면서 많은 것들이 deprecated되었다. 예를 들면 enableglobalmethodsecurity, antMatcher, authorizeRequests 등 자주쓰이는 것들이 이제는 사용할 수 없었고
> Matcher들은 다 requestMatchers()를 사용하고 access말고 바로 hasRole()이나 hasAnyRole()을 사용해 권한 처리를 해야한다. enableglobalmethodsecurity도 대신에 
> EnableMethodSecurity를 사용하여 처리해야 한다! 계속 버전이 바뀌면서 많은 것들이 deprecated되고 계속해서 refactoring하는 연습을 해야겠다

[spring.io에서 공개한 6.0.0 api설명서](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/method/configuration/EnableGlobalMethodSecurity.html).

> Oauth를 이용해 구글에서 프로필 정보를 받아오기 위해 기본설정까지 마쳤다. 구글에서 프로필정보는 생각보다 간단하게 가져와졌다. url을 authorization형식의 주소를 입력하고
> security에서 oauth2Login을 통해 google client id와 secret을 입력하여 이 정보를 요청해 토큰과 프로필을 같이 발급받았다. 이제는 강제로 회원가입을 진행할 예정이다.

### 2022-12-21
> 구글로그인을 최종적으로 마무리하기 위해서 Authentication객체가 가지는 2가지 타입을 하나의 타입으로 묶어줘야했다. 일반적으로 로그인하면 UserDetails타입으로 PrincipalDetails에
> 저장을 넘겼지만 구글이나 페이스북등 우리가 프로필을 가져오는 곳에서는 OAuth로 로그인을 맡기기 때문에 OAuth2User타입으로 받기때문에 우리 DB에 저장되지도 않고 Security에 담기지도
> 않는다. 그래서 PrincipalDetails에 OAuth2User를 인터페이스로 implements하고 OAuth2User를 추가한 새로운 생성자를 선언한다. 그리고 기존 OAuth2로그인을 관리하는 서비스단에서 
> DB형식에 맞게 User 오브젝트를 선언해주고 패스워드까지 암호화하고 넘기고 화면을 보여주면 되는 식으로 구성했다. 구글 로그인은 최종적으로 끝났고 이어서 페이스북으로 로그인을 진행할
> 예정이다.

### 2022-12-22
> 페이스북과 네이버 로그인을 구현해보았다. 구글 로그인 할때 구현했던 소스에서 각각의 생성자 클래스를 만들어서 응답받는 데이터 형식에 맞게 수정만하면 돼 어렵지 않았다. 네이버는 
> application.yml설정할때 provider가 없어서 내가 직접 설정해줘야해서 조금 애먹은거 말고는 다른 포털들과 똑같이 설정했다. 직접 토큰을 받고 그에 맞는 형식으로 로그인하는 것은
> 능숙하게 할 수 있을거같다. api 사용하는 것에서 처음에는 애먹었지만 하나를 하니 다른것들은 조금만 수정해서 컴파일하면 잘 돌아가는 것을 배웠고 로그를 찍어가며 디버그하는 습관도
> 많이 성장한거 같다. 이제는 jwt를 공부하며 차후에 있을 react와 연동해서 구현할 예정이다!
