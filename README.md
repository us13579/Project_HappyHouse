<h1>HappyHouse</h1>

<h2>프로젝트 설명</h2>

부동산의 열풍이 대단한 지금 아파트에 관심이 많으신 분들을 위해 어떻게 아파트에 대한 여러 정보들을 편하게 활용할 수 있도록 전달할 수 있을까를 고민했습니다.<br>
이 HappyHouse 프로젝트는 아파트의 시세 기록이나 지도를 통해 위치 파악, 주변 시설 파악 등을 할 수 있게 도와주는 웹입니다.<br>

<h2>프로젝트 과정</h2>

총 2명의 팀원 ( 본인 포함 ) 이 약 10일 ( 22.05.16 - 22.05.26 ) 동안 백엔드, 프론트엔드 나누지 않고 기능별로 나눠서 같이 프로젝트를 진행했습니다.<br>

<h2>프로젝트 기능</h2>

- 로그인 / 회원가입
- 커뮤니티 ( 사용자들간에 정보 공유 )
- 실시간 부동산 관련 뉴스
- 지도
- 개인 선호 지역, 아파트
- 아파트 정보
- 아파트 시세 그래프, 표 ( 4년까지 )
- 아파트 주변 시설

<h2>나의 역할</h2>

백엔드 & 프론트엔드
- 커뮤니티
- 실시간 부동산 관련 뉴스
- 아파트 정보
- 아파트 시세 그래프, 표
- 아파트 주변 시설

<h2>프로젝트 사용 기술</h2>

![tech](https://user-images.githubusercontent.com/97679742/174468560-518fc0aa-b31b-4711-889f-66f554839380.png)

- BackEnd
    - SpringBoot<br>
    Spring이 아닌 SpringBoot를 선택한 이유는 SpringBoot는 내장웹서버를 가지고 있어 jar로 실행가능할 뿐만 아니라 여러 설정을 자동화, 라이브러리 버전 관리를 자동화 해주는 편의성에 큰 장점을 느껴 SpringBoot를 선택했습니다.
    - JWT<br>
    서버 기반 인증 시스템과 달리 상태를 유지하지 않는 stateless한 구조를 갖는 토큰 기반 인증 시스템 JWT를 사용했습니다.
    - JSOUP<br>
    웹 크롤링을 위해 HTML 문서에 저장된 구문을 가져오기 위해 사용했습니다.
    - MYSQL<br>
    복잡한 쿼리를 사용하지 않고 무료이고 큰 트래픽과 데이터를 다루지 않기 때문에 ORACLE 보다 MYSQL을 선택했습니다.

- FrontEnd
    - Vue<br>
    초보자가 쉽게 접근할 수 있고 배운 것을 활용하는데 의의가 있어서 Vue 를 선택했습니다.
    - BootStrap<br>
    빠르고 페어간의 FrontEnd 의사소통을 좀 더 편하게 하며 반응형 웹을 개발하고자 BootStrap을 선택했습니다.
    - Kakaomap<br>
        간단하게 open API를 사용할 수 있으며 많은 기능들이 있어 사용했습니다.
    - vue-chartjs<br>
        차트를 쉽게 그릴 수 있는 vue의 chart.js 라이브러리를 이용하였습니다.

<h2>프로젝트 설계서</h2>

일정 관리는 `Notion`을 사용하였고<br>
코드 공유는 `gitlab`을 이용하였습니다.<br>

<a href="https://github.com/us13579/Project_HappyHouse/blob/main/Happyhouse%EC%84%A4%EA%B3%84%EC%84%9C%20.docx">`프로젝트 설계서`</a>

<h2>프로젝트 구현 결과</h2>

<a href="https://github.com/us13579/Project_HappyHouse/blob/main/%EC%B5%9C%EC%A2%85ppt.pptx">`프로젝트 ppt`</a>
