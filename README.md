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
