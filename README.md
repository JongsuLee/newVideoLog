# newVideoLog
1. 프로젝트 주제 소개
  : 유튜브 시청 목록과 상세 페이지 구현
  
  
  
2. 팀원 명단
  : 이종수, 김형준, 이영주, 신지수
  
  
  
3. 도메인 용어 정의

    ※User : 사이트 사용자 정보
  
       -ID : 유저 아이디

       -PASSWD : 유저 암호

       -NAME : 유저 이름
  
       -GENDER : 성별

       -AGE : 유저 나이
    


    ※Video : 영상
    
        -ID : 영상 고유번호

       -TITLE : 영상 제목
  
        -UPLOADER : 영상 업로더 정보

        -UPLOAD DATE : 업로드 날짜

        -READCNT : 조회수

       -LIKE : 좋아요 수

       -HATE : 싫어요 수

       -DESCRIPTION : 영상주소 정보
  
  
  
  
4. 요구사항 : 
    1) 로그인시에만 video 상세 페이지 접속 가능
    2) 로그인 후 비디오 클릭시 로그인된 user(사용자)의 id와 video(영상)의 id를 다른 테이블에 저장
    3) 




5. ERD : https://miro.com/app/board/uXjVPa1tDi0=/
 
 
 
 
6. 트러블슈팅(에러 내역, 어떻게 해결했는지) :

      1) ModelAndView를 이용해서 화면을 불러왔는데, 해당 이동방식이 request였습니다.
         대부분의 로직에 transaction.begin()을 넣었는데, 로직이 실행되었을 떄, 이미 transaction이 실행되어 있어서 서버에서 충돌이 발생했습니다.
      
         그래서 if (! transaction.isActive()) transaction.begin()을 통해 충돌을 방지했습니다
         
         
      2) JPQL로 원하는 query를 불러오기 위해 EntitiyManager의 createQuery(), setParameter()를 활용했습니다.
         그런데 JPQL의 문법이 SQL 문법과 다소 차이가 있는 부분이 있어, 블로그에서 상세한 사용법을 찾아 적용하였습니다.
         
         
      3) javascript에서 location.href를 통해 페이지를 이동하는 방식이 redirect라는 것을 몰랐습니다.
         그래서 requestScope를 통해 테이블의 정보를 전달하던 게 무용지물이 되었습니다.
         sessionScope에 저장하는 건 불필요하다고 생각해서 Header에 파라미터를 작성하여 전송하였습니다.





7. 느낀점 : 

      ※김형준 - 강의를 들을때 이해하는것과 그 내용을 실제로 코드로 구현하는건 전혀 다른 문제라는걸 절실히 느꼈습니다:(

      ※신지수 - 하나가 작동되면 다른 하나가 먹통이었습니다. 거기다 git을 이용하여 진행하다 보니 코드 구현 뿐 아니라 git에서의 오류도 확인해야했습니다. 강사님 말씀대로 지옥에서 온 git을 열심히 볼 껄 그랬습니다 ㅎㅎ
