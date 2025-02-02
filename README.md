<img width="482" alt="image" src="https://github.com/user-attachments/assets/93e7a75d-b160-473f-ab01-09ffb1cdecde" /># 의약품 WMS(Warehouse Management System) : All live 榮
![올리브영-removebg-preview](https://github.com/user-attachments/assets/86e0a6dd-3e26-4279-9658-9f6b10e621b5)


## 기획 배경
2019년 11월 17일에 중국에서 COVID-19가 최초로 보고되어 전세계적으로 수많은 확진자 및 사상자를 배출(2023년 9월까지 6억 9천만명이 확진)되며 그에 따라 여러 의약 회사에서 백신도 개발하며 전세계가 합작한 결과 2023년 5월 5일 WHO에서는 펜데믹을 해제했다.
하지만 지속적인 코로나 바이러스 변이로 인해 재유행함에 따라 국내 및 해외 증상 관련 약품들이 부족한 현상 발생함에 따라 **의약품 재고 및 유통 관리 창고시스템(WMS)을 개발**하여 대한민국의 의약품 보급난을 해결하는데 도움이 되고자 다짐


<br>
<br>

## 프로젝트 목표
**1. code-chain WMS 서비스 개발** : 누설성 원칙을 기반으로 하는 WMS 설계 및 구현

**2. 공급망 탄력성 강화** : 재고수준에 대한 가시성을 제공하여 공급장애의 위험성을 완화

**3. 의약품 유통관리표준(KGSP)에 부합** : 지정의약품 등의 특별 취급 의약품 법령에 따른 관리 표준 충족

<br>
<br>

## 기술적 목표
![](https://velog.velcdn.com/images/coo9292/post/111ea0db-42f0-4049-8b4e-b6f4d7cc4a9f/image.png)

- 위와 같은 스프링 MVC 및 계층형 아키텍처의 기술적 목표를 잡고 구현하고자 함

<br>
<br>

## 프로젝트 구조

### 1. 사용 기술
![](https://velog.velcdn.com/images/coo9292/post/1d0a94cf-8d5c-41bd-aec6-88626e852fe8/image.png)

- Spring Boot + Thymeleaf를 통한 MVC 기술을 이용해 SSR을 해서 화면에 랜더링

- 데이터 접근 기술은 SQL에 대한 이해를 위하여 JPA가 아닌 MyBatis를 선택

<br>

### 2. 핵심 기능(3PL 구조)
![](https://velog.velcdn.com/images/coo9292/post/59dc93b2-3a23-497f-8f2c-5c2d7ce4f369/image.png)
- 3PL(Third Party Logistics)현재 물류 시스템 중에서 가장 보편적으로 이용되는 방식으로 기업이 물류 업무의 일부 또는 전체를 위탁하여 아웃소싱하는 물류 시스템

- 즉 운송뿐 아니라 창고 보관, 주문 처리, 재고 관리 등 종합적인 물류 서비스를 제공함에 따라
**회원 관리,  창고 관리 , 재고 관리 , 재무 관리 , 입고 관리 , 출고 관리 , 고객센터**(공지사항 게시판 , 1대1 문의 게시판) 페이지를 구현할 것

<br>

### 3. All live 榮 WMS 프로세스

![](https://velog.velcdn.com/images/coo9292/post/37f3e60d-4491-47c7-889e-164ee0b6ade2/image.svg)


- 그림과 같은 형태로 입고부터 출고까지의 프로세스가 흘러갈 것임
- 의약품 법령에 의거하여 입고 및 출고는 검수를 통해 재고에 입고 및 출고가 됨
- 클라이언트는 **의약회사**이고 검수를 하고 입고를 하고 출고를 하는 주체는 WMS의 한 창고를 맡은 한 창고 관리자임

<br>

### 4. 패키지 구조
- config : 설정 정보 클래스 정의
- constant : Enum 정의

- exception : ```RuntimeException``` 상속받은 커스텀 예외 클래스 정의
- domain : 핵심 데이터가 관리되는 엔티티 클래스 정의

- controller : 프레젠테이션 계층에 속하는 Controller 클래스 정의(HTTP 요청 정보를 토대로 HTTP 응답을 매핑하는 클래스)
- dto : 컨트롤러에서 사용자 폼에서 입력받는 데이터를 매핑해서 서버 단(Service 계층)으로 보내기 위한 DTO(Data Transfer Object) 클래스들을 정의
- service : 비즈니스 로직을 처리하는 Service 계층 클래스 정의
- mapper : 데이터 접근 계층의 클래스로 DB와의 영속성을 관리하기 위한 Mapper 인터페이스들 정의

<br>

### 5. 네이밍 컨벤션
- 변수 및 메서드 명 : camelCase
- 클래스명 : PascalCase

- 테이블명 : UPPER_SNAKE_CASE
- 패키지명 , 테이블 칼럼명 : snake_case

<br>

### 6. 계층 별 메서드 및 URI 네이밍 규칙
- 모든 팀원들이 일관된 네이밍을 유지하고자 각 메서드 및 URI에 대한 네이밍 규칙을 정함

- 다음에서 정의한 틀 안에서 세부적인 요구사항에 따른 메서드가 추가거나 변경되고 네이밍도 약간씩 변할 수 있음

- 예시는 창고(Warehouse) 관리 페이지에 대한 네이밍을 하겠음 , 즉 창고 페이지에 대한 URI 및 컨트롤러 , 서비스 , 매퍼 계층에서의 메서드 네이밍을 정할 것임

#### URI
- 목록 페이지 조회 : /warehouses GET
- 상세 페이지 조회 : /warehouses/{id} GET
- 수정 폼 조회 : /warehouses/{id}/update GET(수정 시 동일한 URI POST 방식)

- 삭제 : /warehouses/{id}/delete POST 
- 등록 폼 조회 : /warehouses/save GET(등록 시 동일한 URI POST 방식)



#### Controller 기본 메서드명
- 목록 페이지 조회 : getWarehouses()
- 상세 페이지 조회 : getWarehouse()
- 수정 폼 조회 & 수정: updateWarehouseForm()
- 등록 폼 조회 & 등록 : saveWarehouseForm()
- 삭제 : deleteOneWarehouse()


#### Service 메서드명
- 목록 페이지 조회 : findWarehouses()
- 상세 페이지 조회 : findWarehouse()
- 수정 : updateWarehouse()
- 등록 : saveWarehouse()
- 삭제 : deleteWarehouse()


#### Mapper
- 목록 페이지 조회 : findAll()
- 상세 페이지 조회 : findById()
- 수정 : update()
- 등록 : save()
- 삭제 : delete()

<br>
<br>

## 팀원 및 내 역할
#### 내 역할
팀장 , 재고 관리 페이지 설계 , 창고 관리 페이지 설계 , Github 관리 , 회의록 관리 , 일정(WBS) 관리

#### 팀원(4명) 역할
- K : 입고 관리 페이지 설계 , Notion 관리 , 
- P : 대시보드 설계 , 재무 관리 페이지 설계 , Spring Security 적용 , 산출물 및 기획안 작성
- C : 회원 관리 , 로그인 페이지 설계 , 고객센터 설계 , 시연 영상 편집 
- J : 출고 관리 페이지 설계

<br>
<br>

## Git 관리
#### Branch(9)
![](https://velog.velcdn.com/images/coo9292/post/e418065c-d601-403e-b8dd-2bdad140a07f/image.png)
- ```main 브랜치``` : 배포용 브랜치
-  ```develop 브랜치``` : 각 기능 별 개발 후 merge용 브랜치
- ```feature 브랜치``` : 팀원 간 각 기능 별 구현 브랜치

<br>

#### Pull Request(39)
![](https://velog.velcdn.com/images/coo9292/post/747392e5-8551-4384-b502-256a2658b94e/image.png)
39번의 PR을 통한 git flow 관리 및 conflict 방지

<br>

#### Commit(203)

![](https://velog.velcdn.com/images/coo9292/post/76d18559-78db-498a-b8bc-0f35d6bc308b/image.png)

![](https://velog.velcdn.com/images/coo9292/post/3b6f116f-4121-4e43-8adc-73df83ec82d5/image.png)

총 203개의 main 브랜치 커밋


<br>
<br>



## 요구사항 분석 및 도메인 설계
### 들어가기 전
- 모든 의약품 WMS를 담당하는 의약 회사(지오영 , 동원약품 , 백제약품 , ...)는 표준 의약 법령에 의거하여 3PL 방식으로 수행됨
- 그에 따라 All live 榮 WMS 또한 모든 설계를 의약품 법령에 근거하여 설계하였음

<br>

### 1. 회원 설계
- 권한은 **총 관리자(ADMIN) , 창고 관리자(MANAGER) , 회사(COMPANY)**로 구분됨

- 한 "총 관리자"가 의약품 WMS를 관리 , 모든 창고 관리자와 회사는 총 관리자에 의해 회원 가입 승인이 됨
- 한 "창고"에는 한 "창고 관리자"만 배정되고, 한 "창고"에 여러 "회사"들이 입고 및 출고를 하게 됨

<br>

### ★ 2. 의약품 설계

![](https://velog.velcdn.com/images/coo9292/post/5b3668d8-5827-4f1b-9eb5-e6cdd88611f3/image.png)

- 의약품 법령에 의거하여 의약품의 종류를 일반, 마약, 폭발물, 생물학적제제로 분류하고 의약품 보관 온도를 상온, 냉동, 냉장으로 분류하기로 결정


<br>


### ★ 3. 창고 설계

![](https://velog.velcdn.com/images/coo9292/post/fb1e0547-5b2a-4a6d-a419-95dc0e0edcda/image.svg)

- 해당 그림은 창고를 세로로 잘랐을 때의 단면도

- 한 창고에는 상온 , 냉장 , 냉동 의약품이 보관하는 영역이 층 별로 분리됨

<br>

![](https://velog.velcdn.com/images/coo9292/post/6089c9f8-22a3-40a4-b202-0099bc85f476/image.png)

- 해당 그림은 창고를 가로로 잘랐을 때의 단면도

- 창고의 각 층마다 여러 섹션이 속함, 각 섹션에는 여러 블록이 속하고 각 블럭 위에 실제 의약품이 재고가 담긴 파레트가 적재
- 따라서 크기 순서는 창고 => 층 => 섹션 => 블럭 => 파레트


<br>

### 4. 입고 설계
- 입고 시 같은 생산일자, 같은 제품일 경우 한 파레트로 묶여서 창고의 블럭에 적재됨

- 입고 요청에 이상이 있을 경우 이상유무 체크박스에 체크 후 반려 이유 작성 후 반려됨


<br>

### 5. 출고 설계
- 입고와 마찬가지로 회사의 출고 요청 시 출고요청서를 작성하면, 창고관리자가 검수 단계를 밟게 됨

- 검수 합격하면 출고가 완료되고, 검수 불합격하면 반려되고 회사에게 반려 사유를 전달해줌


<br>

### 6. 재무 설계
- 연 단위 계약 , 기업체와 기간에 따른 계약 비용 산정

- 매출 조회 : 계약한 회사들의 사용료 산정
- 지출 조회 : 창고에서 발생된 비용에 다른 지출 내역 조회(ex : 공과금 , 임대료 , 부자재비 , 인건비 등등)

<br>
<br>

## ERD 설계
![](https://velog.velcdn.com/images/coo9292/post/3815eaf6-37ca-4e13-ad2f-18452a893b47/image.png)


[이 링크](https://www.erdcloud.com/d/E6iQPxSRmuLZqKYCT)에서 ERD를 자세히 확인할 수 있음

<br>
<br>

## Flow Chart 설계

#### 1. 재고 관리

![](https://velog.velcdn.com/images/coo9292/post/c136fb17-e0b8-40d0-bd83-9fc4cf994e7f/image.png)

#### 2. 창고 관리
![](https://velog.velcdn.com/images/coo9292/post/eeaf6af7-7745-4104-853d-b014e49c5922/image.png)


[이 링크](https://app.diagrams.net/#G1NIGiwJ8J_AbUkhJuomLXx3L7yV0vSAJT#%7B%22pageId%22%3A%22C5RBs43oDa-KdzZeNtuy%22%7D)에서 플로우 차트를 자세히 확인할 수 있음.

<br>
<br>


## Use - Case Diagram 설계

#### 1. 재고 관리
![](https://velog.velcdn.com/images/coo9292/post/e72d450c-9861-4369-96a0-81dd7d802c74/image.png)

<br>

#### 2. 창고 관리
![](https://velog.velcdn.com/images/coo9292/post/724237f9-d997-4e6d-a1bf-892e5f0bf1d1/image.png)

<br>

[이 링크](https://app.diagrams.net/#G1NIGiwJ8J_AbUkhJuomLXx3L7yV0vSAJT#%7B%22pageId%22%3A%22geDZxhMXNpkiSSZ967Mz%22%7D)에서 유스케이스 다이어그램을 자세히 확인할 수 있음.

<br>
<br>


## 내가 구현한 핵심 기능
### 1. 재고 관리
#### ① 검색 조건 별 재고 목록 조회
![](https://velog.velcdn.com/images/coo9292/post/2d37d132-caf7-448c-a997-932b8fd35b30/image.png)
검색 조건 : 보관 온도 , 의약품 종류 , 회사 이름 , 재고 코드 , 유효기간 , 의약품 이름

<br>

#### ② 재고 상세 조회
![](https://velog.velcdn.com/images/coo9292/post/0f57149a-4e14-4253-af97-b50781ca0749/image.png)
- 목록에서 보이지 않는 상세 정보 출력
- 재고 사진은 스프링 프레임워크가 제공하는 ```UrlResource``` API를 통해 출력
- 해당 화면을 거쳐서 수정 폼 접근 가능

<br>

#### ③ 재고 수정
![](https://velog.velcdn.com/images/coo9292/post/1472ed51-f2a3-4efd-abe6-b9bc28fcaf5a/image.png)
- 실제 재고 실사를 통해 발생된 수량 오차를 수정해주는 작업을 해주는 페이지
- 즉 수량에 대한 수정이 가능

<br>
<br>

### 2. 창고 관리
#### ① 검색 조건 별 창고 목록 조회
![](https://velog.velcdn.com/images/coo9292/post/95f73887-4a27-443f-b714-cfb40a8d361d/image.png)
- 검색 조건은 창고 이름 , 창고 주소로 가능
- Kakao Map API를 통해 창고 위치를 카카오 지도로 식별 가능

<br>

#### ② 창고 상세 조회
![](https://velog.velcdn.com/images/coo9292/post/2306bffe-ec2d-41d2-8fec-0e58f08ccba5/image.png)

- 창고에 대한 상세 정보 확인 가능
- Kakao Map API를 통해 창고 위치를 카카오 지도로 식별 가능

<br>

#### ③ 창고 수정
![](https://velog.velcdn.com/images/coo9292/post/e8435f8d-20d8-4521-be28-534e4dd62cf9/image.png)
수정을 위해 사용자의 입력 값 유지

<br>

<img src="https://velog.velcdn.com/images/coo9292/post/38aff0bf-396d-4455-93d1-53ff22133fd0/image.png" width="350">

카카오 우편번호 찾기 API를 통해 주소를 찾을 수 있음

<br>


#### ④ 창고 등록
![](https://velog.velcdn.com/images/coo9292/post/6c886e0e-9908-4e5c-b2e6-ba44ff6ef686/image.png)
새로운 창고를 해당 폼에서 등록 가능

<br>

<img src="https://velog.velcdn.com/images/coo9292/post/8ad19cff-9113-4ae4-9ae1-a95bb3c1e4a4/image.png" width="350">

사진과 같이 카카오 우편번호 찾기 API를 통해 주소를 찾을 수 있음

<br>
<br>

### 3. 공통 레이아웃 정의
![](https://velog.velcdn.com/images/coo9292/post/7fbf7075-ffc3-4d56-9506-d8675ed0bf96/image.png)

- 모든 팀원들이 일관된 레이아웃 안에서 작업을 하기 위하여 위 사진과 같은 공통 레이아웃 html(layout.html)을 배포

- ```Thymeleaf```의 ```Fragment```를 이용해서 공통 템플릿 레이아웃을 정의

<br>

#### 공통 레이아웃 html 정의(layout.html)
```html
<html th:fragment="layout(content)" xmlns:th="http://www.thymeleaf.org">
  <head>
	<!-- 부트스트랩 css 태그 , 메타 데이터 태그 등-->
  </head>
  
  <body>
    <!--네비게이션 바 관련 태그 , 페이지 헤더 관련 태그 등-->
	<div th:insert="${content}" class="container-fluid"></div>
    <!--페이지 footer 관련 태그들 , 부트스트랩 js 태그 등-->
  </body>

</html>
```
```th:fragment```를 통해 fragment(조각)를 선언 , 이때 content 파라메터도 덩달아 선언하면서 외부로부터 태그를 주입받겠다고 지정

<br>

#### 공통 레이아웃 호출
```html
<html th:replace="~{fragment/layout.html :: layout(~{::section})}" xmlns:th="http://www.thymeleaf.org">
 <section>
 	<!--핵심 기능 구현-->
 </section>
</html>
```

- ```th:replace```를 통해 어떤 fragment로 현재 html파일을 "대체"할지 지정했음
- ```fragment/layout.html```을 호출해서 ```<section></section>```영역을 content 파라메터에 넘기고자 함
- 이에 따라 모든 팀원들은 일관된 경로에 공통 레이아웃 파일 배치만 하고  ```<section></section>``` 영역 안에 자신이 맡은 기능만 정의하면 됨
- 해당 ```<section></section>``` 영역이 템플릿 레이아웃의 ```th:insert``` 내부에 삽입되어 동작
- 코드의 중복도 방지할 뿐더러 자신이 맡은 기능 외의 코드에 대한 유지보수에 대한 신경을 쓰지 않아도 되며 SRP를 준수한 설계로 볼 수 있음


<br>
<br>


## ★ Trouble Shooting : MyBatis와 resultMap

#### 문제점
- MyBatis에서 xml파일에서 등록은 ```<insert>``` , 수정은 ```<update>```를 통해 함에 따라 조회를 할 때도 마찬가지로 ```<select>```를 통해 가능

- 하지만 다음과 같이 ```<select>``` 만으로  **조인**을 할 경우 문제가 생김

<br>


#### StockMapper.xml
```xml
<select id="findAll" resultType="Stock">
    SELECT *
    FROM STOCK s
    JOIN PRODUCT p
    ON s.product_id = p.product_id
    JOIN MEMBER m
    ON p.member_id = m.member_id
    JOIN PALLET a
    ON a.pallet_id = s.pallet_id
    JOIN INBOUND_REQUEST_PRODUCT i
    ON i.inbound_request_product_id = a.inbound_request_product_id
    JOIN WAREHOUSE w
    ON s.warehouse_id = w.warehouse_id
    
  	<!-- where문 동적쿼리 코드 생략-->
  </select>
```
- 전체 재고 목록을 조회하는 쿼리이며, 가독성을 위하여 where문은 생략하였음
- 즉 ```resultType="Stock"```으로 하여 재고(Stock) 타입의 리턴값을 받고자 했음

<br>


#### 테스트 코드
```java
@SpringBootTest
public class MapperTest {
  @Autowired
  private StockMapper stockMapper;

   @Test
    void abc(){
      List<Stock> stocks = stockMapper.findAll(new StockSearch() , new Member());
      for (Stock stock : stocks) {
        Assertions.assertThat(stock.getProduct()).isNull();
      }
    }
}
```

#### 테스트 결과
![](https://velog.velcdn.com/images/coo9292/post/c1df7393-f555-4049-8351-dc3bd2ac0c59/image.png)
- isNull 테스트 시 성공하는 것을 보면서 조회가 되지 않는 것을 확인할 수 있었음

- 왜 null일까 한참을 고민하면서 구글링을 해본 결과 연관관계에 대한 조인 조회를 할 경우에는 ```<select>``` 태그만으론 부족하고 ```<resultMap>```이라는 것을 함께 써야한다고 한다는 사실을 알게됨

- 왜 ```<resultMap>```이란 친구를 써야되는지 고민했지만, 가장 핵심적인 문제는 자바와 DB 간 연관관계를 맺는 패러다임 자체가 다르기 때문임.

- 자바에서의 연관관계는 **객체**를 통해 맺고 DB에서의 연관관계는 **외래 키**를 통해 맺는다.
- DB에서 BIGINT 값인 외래 키를 조회해봤자 자바에서는 연관관계에 대한 필드를 Member 타입으로 정의하고 있으니 원활하게 자바에 매핑이 되지 않는 것임.
- 따라서 ```<resultMap>```이란 태그를 통해 ```<select>```를 통해 조회할 결과를 담을 그릇을 선언해줄 수 있다.



<br>
<br>

#### resultMap 개요
- 공식문서에는 다음과 같이 정의됨

- resultMap엘리먼트는 마이바티스에서 가장 중요하고 강력한 엘리먼트이다.
- ResultSet에서 데이터를 가져올때 작성되는 JDBC코드를 대부분 줄여주는 역할을 담당한다. 
- ResultMap은 간단한 구문에서는 매핑이 필요하지 않고 복잡한 구문에서 관계를 서술하기 위해 필요하다.

- 이처럼 복잡한 연관관계 조회 시 쓸 수 있는 MyBatis에서도 적극 권장하고 있는 방법

- 하지만 위의 문제점에서 보다시피 굳이 복잡하지 않더라도 **연관관계 조회를 단 하나만 하더라도 이 방법을 써야됨을 알았다**

<br>
<br>

#### resultMap 정의

- ```<select>``` 태그를 통해 조회된 각 칼럼을 적절한 ```<resultMap>```에 담아서 리턴해줌

- 다음과 같이 정의해줄 수 있음
```xml
<resultMap id="stocks" type="Stock">
    <id column="stock_id" property="id"/>
    <result column="stock_code" property="stockCode"/>
    <association property="product" javaType="Product">
      <id column="product_id" property="id"/>
      <result column="product_name" property="productName"/>
      <result column="store_temperature" property="storeTemperature"/>
      <result column="product_type" property="productType"/>
      <association property="member" javaType="Member">
        <id column="member_id" property="id"/>
        <result column="name" property="name"/>
      </association>
    </association>

    <association property="pallet" javaType="Pallet">
      <id column="pallet_id" property="id"/>
      <association property="inboundRequestProduct" javaType="InboundRequestProduct">
        <id column="inbound_request_product_id" property="id"/>
        <result property="expirationDate" column="expiration_date"/>
      </association>
    </association>

    <association property="warehouse" javaType="Warehouse">
      <id column="warehouse_id" property="id"/>
      <result column="warehouse_name" property="name"/>
    </association>
  </resultMap>
```
- ```<resultMap>``` 태그 : 조회할 id 및 칼럼들을 해당 영역 내부에 정의할 수 있음, id 속성에 resultMap 이름을 지정하고, property에 리턴할 객체 타입을 지정하면 됨

- ```<id>``` 태그 : id(PK) 매핑 태그 , column에 칼럼명 , property에 필드명 지정해주면 됨

- ```<result>``` 태그 : 칼럼 매핑 태그 , column에 칼럼명 , property에 필드명 지정해주면 됨
- ```<association>``` 태그 : 연관관계 객체 매핑 태그 , property에 테이블명 , javaType에 객체명 , column에 가져올 외래키 명 지정해주면 됨

<br>
<br>

#### resultMap 사용한 StockMapper.xml
```xml
<select id="findAll" resultMap="stocks">
    SELECT *
    FROM STOCK s
    JOIN PRODUCT p
    ON s.product_id = p.product_id
    JOIN MEMBER m
    ON p.member_id = m.member_id
    JOIN PALLET a
    ON a.pallet_id = s.pallet_id
    JOIN INBOUND_REQUEST_PRODUCT i
    ON i.inbound_request_product_id = a.inbound_request_product_id
    JOIN WAREHOUSE w
    ON s.warehouse_id = w.warehouse_id
    
  	<!-- where문 동적쿼리 코드 생략-->
  </select>
```
- 문제점 코드에서 resultType을 resultMap으로 바꾼 후 정의한 resultMap을 지정하였음

- 동일한 코드로 테스트를 해보겠음

<br>
<br>

#### 결과 및 깨달은점
![](https://velog.velcdn.com/images/coo9292/post/ab5b33ea-782e-426a-a15e-6843a5489a02/image.png)

- 테스트 실패가 되며 null이 아닌 Product 타입의 객체가 잘 담기는 것을 알 수 있었음



```java
@SpringBootTest
public class MapperTest {
  @Autowired
  private StockMapper stockMapper;

   @Test
    void abc(){
      List<Stock> stocks = stockMapper.findAll(new StockSearch() , new Member());
      for (Stock stock : stocks) {
      	Assertions.assertThat(stock.getProduct()).isInstanceOf(Product.class);
      }
    }
}
```

-  Product 인스턴스인지 확인하려고 테스트 코드를 리팩토링해도 잘 출력되는 것을 알 수 있었음
- <span style="color:red">즉 단순 조회가 아닌 연관관계에 대한 조인 조회 시에는 무조건 resultMap을 활용해야한다는 사실을 깨달음</span>


<br>
<br>


## 활용방안 및 기대효과
#### 1. 창고의 전반적인 관리 기능
- 올리브영 WMS는 의약 창고를 관리하는 wms로서 의약품의 입고부터 출고까지, 사용자(제약 및 수입회사)부터 창고 관리자까지 물류의 모든 부분을 맡음.

- 사용자는 입고 요청, 재고 조회, 출고 요청을 통해 사용자의 의약품 관리할 수 있고,
창고 관리자는 입고 요청에 대한 승인 및 재고 관리, 출고 승인 등 여러가지 쏟아지는 요청을 한 번에 관리할 수 있음.

<br>

#### 2. 물류 전산화를 통한 효과적 운영
- 올리브영 WMS의 기능으로 창고 관리자는 물류의 전산화를 통해 창고를 효과적으로
운영 가능

- 이를 통해 현장 근로자 및 관리자가 창고 관리에 더욱 집중할 수 있는 환경 조성

<br>

#### 3. 콜드체인 물류 
- 의약품은 보관 온도와 종류에 따라 관리가 필요하기 때문에 창고 관리가 중요

- 결과적으로 사용자는 올리브영 WMS를 사용하는 창고를 더 믿고 사용할 수 있게 될 것임.


<br>

## 회고
#### 백엔드
- WMS의 핵심 파트인 재고 및 창고 파트를 맡음에 따라 WMS를 이해하는데 큰 도움이 되었고 구현 결과는 만족스러웠지만, 7개의 테이블 이상의 조인 쿼리로 인해 모든 테이블에 직접 조인 쿼리를 작성하고 resultMap을 통해 테이블에 속한 모든 칼럼들을 지정함으로써 시간이 오래걸리고 실수로 몇 개의 칼럼을 지정 못하여 치명적인 ```NullPointerException```을 마주할 수밖에 없었습니다.

- 직접 조인 쿼리를 작성하지 않고도 서브 쿼리 혹은 DB의 프로시저를 활용하면 좀 더 간편하게 작성할 수 있었겠지만, 해당 기술에 대한 지식이 부족했음에 따라 익숙한 조인 쿼리를 끝까지 사용하였습니다.
- 결과적으로 마감 시간 안에 제가 생각했던 목표까지 구현을 완성하였기에 뿌듯하였지만, 서브쿼리 , 프로시저를 활용한 리팩토링을 하면서 스킬업을 해나갈 계획입니다.

- 그리고 MyBatis는 개발자가 직접 SQL Mapper가 되어 SQL을 작성하는 반면 JPA는 SQL 매핑을 JPA가 대신 해주니 개발자가 해야되는 많은 책임 중 일부분을 상당히 덜어주는데 일조함을 이번 계기를 통해 확실히 체감을 하게 되었습니다.



<br>

#### 프론트엔드
- Thymeleaf를 통한 SSR 방식은 익숙하였으나 외부 Kakao Map API , 주소 API와 같은 외부 API를 사용하다보니 REST API 통신을 통한 JavaScript 코드를 활용하게 되었습니다.
- 하지만 JavaScript 구현을 해본 적이 없다보니 JavaScript로 이루어진 API 표준 Document를 이해하기가 어려웠지만, API 가이드대로 차근차근 진행함에 따라 결과가 잘 구현됨을 확인할 수 있었습니다.

- 추후 JavaScript에 대한 이해도를 올려 자유자재로 외부 API를 능숙하게 활용할 수 있게 할 것이고, 백엔드에서의 REST API를 통한 JSON 데이터를 프론트엔드로 보내어 ajax로 JavaScript로 처리하는 것까지 구현하여 MSA 환경에 대한 리팩토링을 해나갈 계획입니다.

<br>

#### 테스트
- TDD를 진행함에 따라 JUnit 5 라이브러리를 이용하여 매 기능이 구현될 때마다 테스트를 진행하였습니다.

- 테스트는 given/when/then 패턴을 통하여 일관된 형식을 갖고 진행하였기 때문에 저와 팀원들이 테스트를 수월하게 진행할 수 있었고, 선 테스트를 통해 오류를 바로잡고 후에 개발한 결과 당장은 번거롭고 시간이 길게 느껴졌지만, 결과적으로 봤을 때 개발하는 데 시간이 많이 단축됨을 느꼈습니다.
- <span style="color:blue">또한 기능 하나 개발할 때마다 테스트해보는 것을 원칙으로 삼자</span>
<br>

#### DB
- 페이징 처리 , 동시 수정을 위한 트리거의 활용 , SQL 작성 등 DB를 다루는 데 있어서 많은 부족한 부분이 이번 프로젝트를 통해 드러났습니다.

- 백엔드 개발자는 DB 또한 능숙하게 다룰 줄 알아야 프로젝트의 성능 및 속도도 향상시킬 수 있으므로 계속해서 DB에 대한 공부를 놓치지 않고 해나갈 계획입니다.

<br>

