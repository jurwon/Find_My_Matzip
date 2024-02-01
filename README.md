# <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/b319291f-ec97-419b-949f-c6a7e3834c32" width="50" height="50"/> 맛.ZIP (부산 맛집 공유 커뮤니티 웹)
![image](https://github.com/jurwon/Find_My_Matzip/assets/35756071/a608c43c-2d40-46c2-809b-c2da4864f5c2)
- 일정 : 2023.10.02. ~ 2023.10.18 <br><br>

## 프로젝트 소개
- 넘쳐나는 광고성 맛집 정보에 지친 사람들을 위한 신뢰 기반 부산 맛집 공유 SNS입니다.
- 개인의 프로필 페이지에 나의 맛집에 대한 정보를 작성할 수 있습니다.
- 내 근처 맛집 정보를 지도에 띄워 제공합니다.
- 다양한 유저들을 팔로우하며 마음에 드는 게시글에 좋아요를 누르거나 댓글을 작성 할 수 있습니다.
<br><br>

## 팀원 구성
| 손주원(팀장) | 김경태 | 서동옥 | 손유영 | 
| :---: | :---: | :---: | :---: |
| <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/637dc64e-6e5f-4fa1-b2a7-68a0f9174de0" width="90" height="80"/>| <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/684414ca-d820-4321-9860-dd8ed2dea7ec" width="80" height="80"/> | <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/a2e19a00-19cc-419b-8a26-e1b85be54b4b" width="90" height="90"/> | <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/547f8182-a3a9-4605-8202-1f2fa82f66a7" width="80" height="80"/> | <img src="https://github.com/jurwon/Find_My_Matzip_Android/assets/35756071/074fd9af-a75c-461c-b510-4c09f8491bd5" width="90" height="80"/> |
| [@jurwon](https://github.com/jurwon) | [@RomanticToad](https://github.com/RomanticToad) | [@eastok](https://github.com/eastok) | [@sonyuyoung](https://github.com/sonyuyoung) |
<br>

## 역할 분담
### :tangerine: 손주원
- 기능 : 팔로우&팔로잉, 본인/타인 프로필 한 화면에서 구현, 회원별 작성 게시글, 총괄
<br>

### :apple: 김경태
- 기능 : 게시글 CRUD, 식당 CRUD, 메인화면, 맛잘알리스트
<br>

### :melon: 서동옥
- 기능 : 지도 API구현, 식당 Entity 작성, 공공 API활용(식당 DB재가공), 지도API와 DB결합
<br>

### :grapes: 손유영
- 기능 : 로그인, 로그아웃, 회원정보 CRUD, 회원목록관리, 레이아웃, 게시글 삭제, 랭킹
<br>

## 1. 개발 환경
- Frontend : Thymeleaf 
- Backend : SpringBoot, MySQL
- 버전관리 : GitHub
- Tool : Android Studio, Intelij, MySQL Workbench
<br>

## 2. 프로젝트 설계
![image](https://github.com/jurwon/Find_My_Matzip/assets/35756071/176d6241-a699-4cff-a566-94aa3be8ebf1)
![image](https://github.com/jurwon/Find_My_Matzip/assets/35756071/056f5b98-02c4-4506-aa7a-5b8811a546e2)

<br><br>


## 3. 페이지별 기능
### \[비회원 열람 가능 정보\]
- 로그인하지 않아도 열람 가능한 영역 (초기화면, 식당목록, 주변맛집)
  - 로그인 되어 있지 않은 경우 : 게시글 클릭가능
  - 로그인 되어 있는 경우 : 게시글 클릭시 로그인창으로 이동
  <br>
  
  | 초기화면 |
  | :---: |
  | ![초기화면](https://github.com/jurwon/Find_My_Matzip/assets/35756071/42103d36-dbb0-4f72-9c96-18978d04194a)|

  <br>
  
  | 식당목록 |
  | :---: |
  | ![식당목록](https://github.com/jurwon/Find_My_Matzip/assets/35756071/dab4ed9b-a40e-40cd-ba55-6a2849a16665) |

  <br>

   | 주변맛집 |
  | :---: |
  | ![주변맛집](https://github.com/jurwon/Find_My_Matzip/assets/35756071/414ffc8f-0f7d-453c-bb08-a24c9a5c0f76)|

  <br>
  
   ### \[로그인 및 회원가입\]
   <br>
  
  | 회원가입 |
  | :---: |
  | ![회원가입](https://github.com/jurwon/Find_My_Matzip/assets/35756071/c52097a2-2ca6-4373-ad5b-99e395de3992) |

   <br>
  
  | 로그인 |
  | :---: |
  | ![로그인](https://github.com/jurwon/Find_My_Matzip/assets/35756071/54b3bd61-6cd7-49e2-aca0-47d5de30c1d2)
 |
  
   <br>
  
  ### \[로그인 후 열람 가능 정보\]
    - 맛잘알 게시판 : 내가 팔로우한 유저들이 쓴 게시글만 모아보는 페이지
   <br>
  
  | 맛잘알 게시판 |
  | :---: |
  | ![맛잘알 게시판](https://github.com/jurwon/Find_My_Matzip/assets/35756071/03934da9-ebab-470c-8908-f906377f9b37)|

  <br>

  
  | 랭킹 |
  | :---: |
  | ![랭킹](https://github.com/jurwon/Find_My_Matzip/assets/35756071/7f5a2e17-e19a-4a55-9ea0-b61c76bc276e)


<br>

  | 내프로필 |
  | :---: |
  | ![내프로필](https://github.com/jurwon/Find_My_Matzip/assets/35756071/b018516b-b336-4e5d-b43f-6693a9ea750c)

  <br>

  | 타인 프로필 |
  | :---: |
  | ![타인 프로필](https://github.com/jurwon/Find_My_Matzip/assets/35756071/06a6a611-c5b6-4442-bbf0-fa632966b2bf)

  <br>

 | 팔로워 리스트 |
  | :---: |
  | ![팔로워 리스트](https://github.com/jurwon/Find_My_Matzip/assets/35756071/f4dc4e03-7cf3-4a2a-b07f-af74e4d704f6)|
  <br>


  | 회원정보수정 |
  | :---: |
  | ![회원정보수정](https://github.com/jurwon/Find_My_Matzip/assets/35756071/6beff259-83fd-4bf5-aec4-bf85da5df3e2)|


### \[게시글 상세페이지\]
  <br>
  
  | 게시글 상세(리뷰상세) |
  | :---: |
  | ![게시글 상세](https://github.com/jurwon/Find_My_Matzip/assets/35756071/67603b6f-adb3-40a2-8403-cd195e092349)|

 <br>

  | 게시글 상세(가게정보 상세) |
  | :---: |
  | ![가게정보 상세](https://github.com/jurwon/Find_My_Matzip/assets/35756071/0b2dcd8f-097c-497f-aa50-95e4880a5a30)|

   <br>

  | 댓글 작성 |
  | :---: |
  | ![댓글 작성](https://github.com/jurwon/Find_My_Matzip/assets/35756071/55f003d5-591b-4831-8b66-d16b591cf5e9)|

 <br>

  | 좋아요/싫어요 |
  | :---: |
  | ![좋아요/싫어요](https://github.com/jurwon/Find_My_Matzip/assets/35756071/528ede92-33ee-4be7-a10c-a153e633f9b6)|

<br>

### \[게시글/식당 추가\]
  <br>
  
  | 게시글 작성 |
  | :---: |
  | ![게시글 작성](https://github.com/jurwon/Find_My_Matzip/assets/35756071/a8757db7-1802-45e5-8f78-0aede1998dc5)|

  <br>


### \[관리자 접근 페이지\]
  <br>
  
  | 식당관리 및 검색 |
  | :---: |
  | ![식당관리 및 검색](https://github.com/jurwon/Find_My_Matzip/assets/35756071/87617247-79b8-407a-ae09-cffb48ea9c5d)|

   
  | 식당 추가 |
  | :---: |
  | ![식당 추가](https://github.com/jurwon/Find_My_Matzip/assets/35756071/2cf9f7ef-4cd5-4185-b1c8-bf187248412c)|

<br>
