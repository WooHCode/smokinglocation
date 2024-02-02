## -- smokinglocation --
### &#x2615; 서울시 내에 공공기관에서 운영하는 흡연구역의 위치를 안내해주는 웹페이지입니다.

##### 개발기간 : 23.8.02 ~ 24.01.25
##### 개발인원 : 4인

---

### &#x1F4BB; 사용 기술스택:
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">

- Java

<img src="https://img.shields.io/badge/springboot-BFF0B6?style=flat-square&logo=springboot&logoColor=green">

- Spring, SpringBoot, Spring web Mvc, Spring Data JPA, Spring Validation,Spring security, jwt, websocket, lombok, mail

<img src="https://img.shields.io/badge/postgresql-51ADCE?style=flat-square&logo=postgresql&logoColor=">

- Postgresql, Amazon RDS

<img src="https://img.shields.io/badge/AWS-F2E1B9?style=flat-square&logo=AWS&logoColor=">

- AWS EC2,AWS route53, Apache Tomcat, Docker

##### etc

- P6spy(로그 콘솔창 출력 라이브러리), gson, open api

---

### 🛠️ 개발 도구:
- Intellij, putty, aws console, postman, pgAdmin,dbeaver, git, docker

## &#x1F517; 링크:

#### 배포된 홈페이지 링크 : https://www.smokinglocation.com

---

#### &#x1F4D8; 기능 설명

1. 서울시 내 흡연구역 위치 정보 안내

<img width="1707" alt="스크린샷 2024-02-02 오후 10 11 07" src="https://github.com/WooHCode/smokinglocation/assets/112393201/5bb538aa-c449-4fa7-a5ee-1ccb07b37137">

- 설명 : 서울시 공공 api포털에서 제공하는 json데이터를 cron scheduler 로 가져와 필요한 데이터만 사용할 수 있도록 파싱 후 db에 저장, 해당 데이터를 네이버 지도 api를 통해 화면에 보여줍니다.


2. N+1
- 문제: Employee 엔티티와 Attendance 엔티티는 1 : 다 관계로 매핑되어있음. 그리하여 Employee를 전체조회하면 각 Employee에 관련된 Attendance가 추가로 여러번 쿼리가 발생되어 성능이 저하됨.
- 해결 : Default값인 @ManyToOne(fetch = FetchType.EAGER)에서 @ManyToOne(fetch = FetchType.LAZY)로 변경하여 실제 데이터가 필요한 시점에서 데이터를 가져올 수 있도록 변경 
- 관련링크 : [https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/entity/Attendance.java ==> 24 Line](https://github.com/WooHCode/joelpage/blob/ca2e130fdb36909b19ec0f87ca84cb547f898146/src/main/java/joel/joelpage/entity/Attendance.java#L24)

3. 재귀호출
- 문제 : LoginMember 엔티티와 연관된 필드가 JSON으로 serialize(직렬화) 될 때 무한 재귀 호출이 발생함. 그로인해 서버가 다운됨. LoginMember 엔티티가 Employee엔티티 내부에 있고, LoginMember 엔티티에 Employee가 내부에 있기때문에 LoginMember를 JSON으로 직렬화 하려고 할때, 내부에 있는 Employee필드를 다시 직렬화 하게되고 Employee필드를 직렬화 하게 될때, LoginMember를 다시 직렬화하는 과정을 반복하면서 발생함.
- 해결 : @JsonIgnore 어노테이션을 사용하여 LoginMember필드가 JSON으로 다시 직렬화 되지 않도록 설정
- 관련링크 : [https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/entity/Employee.java  ==> 55 ~ 58 Line](https://github.com/WooHCode/joelpage/blob/b28b5d15fd0a5c42e52d08a3f0eb0761c1dea090/src/main/java/joel/joelpage/entity/Employee.java#L55-L58) 
4. CORS 문제
- 문제 : 로컬 개발 환경에서는 프론트 서버와 통신에 문제가 없었지만, 서버를 EC2에 배포하다 보니 서버로 api요청 시 CORS문제가 발생함.
- 해결 : webConfig클래스를 @Configuration으로 등록하고 요청에 대한 Origin을 허용하여 CORS정책에 의한 Block을 해결
- 관련 링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/config/WebConfig.java
5. LocalDateTime 사용 문제
- 문제 : LocalDateTime으로 시간 비교 시 duration을 사용해서 비교를 하게되면 다음날이 되어 시간이 00시 이후가 되면 차이 값이 -가된다.
- 해결: difference.getSeconds()/3600 의 값이 - 로 나온다면 +24를 더해서 하루가 지났다는것을 인지시켜준다.
- 관련링크 : [https://github.com/WooHCode/joelpage/blob/ca2e130fdb36909b19ec0f87ca84cb547f898146/src/main/java/joel/joelpage/service/EmployeeService.java  ==> 117 ~ 130 Line](https://github.com/WooHCode/joelpage/blob/ca2e130fdb36909b19ec0f87ca84cb547f898146/src/main/java/joel/joelpage/service/EmployeeService.java#L117-L130)


---


---



---
### 📘 추가 예정 사항
1. Oauth2를 활용하여 Kakao, Google, Naver로 로그인이 가능하도록 구현
2. Spring Security를 도입하여 별도로 나눠서 구현한 Jwt와 통합, CSRF(Cross-Site Request Forgery) 공격 방지, XSS(Cross-Site Scripting) 방어, 세션 관리, 브라우저 캐싱 방지 등 보안 기능을 강화.
3. QueryDSL을 도입하여 동적쿼리 생성, 컴파일 시점의 문법 오류 검출 등 API추가.

