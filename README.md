<h1 align="center"> Class Flix </h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://github.com/dongho108/ClassFlix" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
</p>

>  Service choosing a course using Spring Boot

### 🏠 [Homepage](https://github.com/dongho/ClassFlix)

## Skill
> Java <br>
> Spring Boot 2.4.5v <br>
> JPA Hibernate <br>
> HTML, CSS, Javascript, Thymeleaf <br>
> DB : h2 <br>
> Test : JUnit5 <br>


## Author

👤  dongho

* blog: https://ksabs.tistory.com
* project review: https://ksabs.tistory.com/131?category=1016104

## About ClassFlix
Classflix는 여러 사이트에 퍼져있는 인터넷 강의들을 모아놓은 플랫폼입니다. <br><br>
나에게 맞는 강의를 찾으려면 여러번 검색해서 정리해야했지만 이제는 그럴 필요 없습니다. <br><br>
한 사이트 안에서 듣고싶은 강의를 찾고, 별점을 확인하고, 리뷰를 확인하세요. <br><br>
<br>

> home : 첫 화면부터 바로 강의목록을 보여줍니다. 정렬 기준(최신순, 이름순)을 선택할 수 있습니다. <br>
<img width="1439" alt="home" src="https://user-images.githubusercontent.com/54317630/129855753-ba4fac5d-50d2-439d-a071-5c7f6ebd37b7.png">

<br>
<br>
<br>


> 검색기능 : 홈 화면에서 자기가 찾고싶은 강의를 검색할 수 있습니다.<br>
<img width="1439" alt="home" src="https://user-images.githubusercontent.com/54317630/129857552-9ac46f51-a611-47a4-9d25-d53292ba64a7.png">

<br>
<br>
<br>


> 회원가입 : 회원을 등록할 수 있습니다. <br>

<img width="1437" alt="memberform" src="https://user-images.githubusercontent.com/54317630/129844866-c289e9aa-2436-4e35-90f3-d53225018603.png">

<br>
<br>
<br>

> 강의등록 : 강의를 등록할 수 있습니다. <br>

<img width="1423" alt="lectureForm" src="https://user-images.githubusercontent.com/54317630/129844985-6bd38942-b97f-4d1a-9cd5-0c6bfea00604.png">

<br>
<br>
<br>

> 강의를 누르면 강의에 대한 정보가 나옵니다. <br>
> 강의정보에서 해당 강의의 평균별점을 확인 할 수 있습니다. <br>
> 강의정보에서 실제 강의의 링크로 바로 연결할 수 있습니다. <br>

<img width="1425" alt="initlecture" src="https://user-images.githubusercontent.com/54317630/119301637-bf483580-bc9d-11eb-998c-1a17dc35c310.png">

<br>
<br>
<br>

> 강의에 대한 리뷰를 달고 별점을 추가할 수 있습니다. <br>

<img width="1424" alt="reviewresult" src="https://user-images.githubusercontent.com/54317630/119337622-05b38980-bcca-11eb-9be4-d370db6516d6.png">

<br>
<br>
<br>

> 리뷰를 수정할 수 있습니다. <br>

<img width="1417" alt="update" src="https://user-images.githubusercontent.com/54317630/119301706-de46c780-bc9d-11eb-8389-6f50c13fe618.png">
<img width="1420" alt="updateresult" src="https://user-images.githubusercontent.com/54317630/119301963-45647c00-bc9e-11eb-85b2-fffbd4388cf0.png">

<br>
<br>
<br>

> 리뷰를 삭제할 수 있습니다. <br>

<img width="1422" alt="delete" src="https://user-images.githubusercontent.com/54317630/119301767-f4548800-bc9d-11eb-8565-47727af7abea.png">
<img width="1425" alt="deleteresult" src="https://user-images.githubusercontent.com/54317630/119301784-fa4a6900-bc9d-11eb-8356-47d3e548bc64.png">

<br>
<br>
<br>



## Features

> 회원등록 <br>

> 강의등록 (사진등록) <br>

> 각 강의에 리뷰등록, 수정, 삭제, 별점등록 <br>

> 각 강의에서는 해당 강의의 리뷰들의 평균별점을 볼 수 있음 <br>

> 어떤 강의가 있는지 다양한 파라미터로 검색 가능 <br>

> 페이징 처리로 페이지당 깔끔하게 8개의 강의씩 볼 수 있음 <br> 


## Build process

21/08/12 <br>
add sortParam dto, ratings
refactoring lecture
refactoring memberForm, lectureForm design


21/08/11 <br>
refactoring searchForm

21/07/25 <br>
add home lectures search

21/07/21 <br>
add home lectures sorting

21/07/19 <br>
add home lectures paging

21/07/14 <br>
add querydsl settings

21/06/04 <br>
update Repository (pure jpa -> Spring Data Jpa)

21/05/21 <br>
solve issue (review-update, review-delete) <br>

21/05/20 <br>
add image-upload & show image <br>

21/05/14 <br>
add review-delete <br>

21/05/12 <br>
add review-update <br>

21/05/11 <br>
remove password in review <br>

21/05/4 <br>
add view & controller <br>

21/04/25 <br>
add domain, repository, service <br>

21/04/21 <br>
inital commit <br>
