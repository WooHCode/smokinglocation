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

- gson, open api

---

### 🛠️ 개발 도구:
- Intellij, putty, aws console, postman, pgAdmin,dbeaver, git, docker

## &#x1F517; 링크:

#### 배포된 홈페이지 링크 : https://www.smokinglocation.com

---

#### &#x1F4D8; 기능 설명

1. 서울시 내 흡연구역 위치 정보 안내

<img width="1707" alt="스크린샷 2024-02-02 오후 10 11 07" src="https://github.com/WooHCode/smokinglocation/assets/112393201/5bb538aa-c449-4fa7-a5ee-1ccb07b37137">

- 설명 : 1. 서울시 공공 api포털에서 제공하는 json데이터를 cron scheduler 로 가져와 필요한 데이터만 사용할 수 있도록 파싱 후 db에 저장, 해당 데이터를 네이버 지도 api를 통해 화면에 보여줍니다.
        2. 길찾기 기능, 현재 위치에서 가까운 흡연구역은 어디에 있는지 알려주는 기능도 구현되어있습니다.
       


2. 회원 기능
<img width="1692" alt="스크린샷 2024-02-02 오후 10 13 44" src="https://github.com/WooHCode/smokinglocation/assets/112393201/8104b2be-ab76-486b-ba53-78acfc80031e">

- 설명 : spring security를 활용해 기본적인 정보만 활용하여 회원 로그인 기능도 구현되어있으며, kakao, naver, google oauth2 를 활용한 소셜 로그인 기능까지 구현되어있습니다.




3. 채팅 기능
<img width="440" alt="스크린샷 2024-02-02 오후 10 30 19" src="https://github.com/WooHCode/smokinglocation/assets/112393201/b4e8bacf-8a66-436c-99ad-3d0720aef61f">

- 설명 : 관리자와 채팅할 수 있는 기능입니다. websocket을 통해 생성된 채팅방을 구독하여 관리자가 해당 채팅방에서 답변을 진행 할 수 있는 기능입니다.

<img width="500" alt="스크린샷 2024-02-02 오후 10 37 41" src="https://github.com/WooHCode/smokinglocation/assets/112393201/100aad73-4cd7-4a27-8277-40ec044c5514">
<img width="499" alt="스크린샷 2024-02-02 오후 10 38 33" src="https://github.com/WooHCode/smokinglocation/assets/112393201/8b550608-d33c-466f-9863-acf88daa3767">




4. 이메일 답변기능
<img width="627" alt="스크린샷 2024-02-02 오후 10 40 09" src="https://github.com/WooHCode/smokinglocation/assets/112393201/a5c67abb-41c9-4ded-87c0-5968c2bda09a">

- 설명 : 회원이 문의를 남겼을 때 관리자가 답변을 보내면 이메일로 결과를 알려주는 기능입니다.

<img width="804" alt="스크린샷 2024-02-02 오후 10 50 17" src="https://github.com/WooHCode/smokinglocation/assets/112393201/ecfb60c1-144b-4d7c-b6bc-f85026a854cd">



