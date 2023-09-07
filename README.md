# freight
화물 운송 관리를 위한 어플

---

## Settings
java 11 / spring boot 2.7.16 / SQLite 3 / queryDsl / Docker / Oracle Cloud / github Action CI/CD

---

## 테이블

<img width="95%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/88cce074-3407-4034-b14e-aae0e7f75ae3">

---

## 운행 일지 mockup

<img width="95%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/667e92c9-fffe-4124-8452-8f1b77068203">

### 일지 목록 등 참고용 이미지들
<p align="center">

<img algin="center" width="25%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/4f1095c7-4f17-475f-87cc-cae4c3996fcf">
<img algin="center" width="25%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/3707c90c-8c7e-4dee-9809-af2fbb044751">
<img algin="center" width="25%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/07ea16b6-7ece-4f53-a7f1-0aff5d4b58e9">
<img algin="center" width="20%" alt="image" src="https://github.com/jaemanc/freight/assets/104718153/82634545-00ca-4db1-aaa4-07d679f6d511">


</p>
---

## __To do__

### PROCESS

1. 화면 목록 정리
2. 테이블 설계
3. 서버 환경 설정
   3. Error 감지 : email로 
4. Flutter 설정
5. API 명세 - Swagger 사용
6. 구글 플레이 스토어??

### CODE LEVEL

1. excel download to mobile
2. log backup - rolling + 1 month
3. db backup - sqlite3 + backup??
4. ERROR - email alert

* 로그인 관련 - 최대한 리소스 적게 사용
  * 회원
    * ID / 이름 / 연락처 / 이메일(필수X)  <- JWT 값 사용.
  * 비회원
    * 최초 접속 시, 유저 아이디 UUID 생성하여 ID 값으로 사용 <- 어플 삭제 혹은, JWT 잃어버릴 경우 복구 불가.
  * 인가
    * 인터셉터 - JWT + secret_key / user_id 값으로 검증



