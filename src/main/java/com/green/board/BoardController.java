package com.green.board;
/*
Controller : 요청(request)을 받고 응답(response)처리하는 객체
로직처리는 하지 않는다.

Annotation(에노테이션)
@Controller - 응답을 html (데이터로 만든 화면을 응답)
@RestController - 응답을 json (데이터만 응답)

@RequestMapping - url 과 클래스 아래에 있는 Method 맵핑(연결)
                  class 에 RequestMapping 전체 메소드 주소가 맵핑

@PostMapping - url + Post 방식으로 요청이 왔을 시 담당자

요청과 응답은 (header , body) 로 이루어져 있음
header 에는 목적지(url) , 요청 방식(method) , 인코딩 등등
body 에는 전송할 값 , 데이터 담겨져있음

브라우저를 통해 요청을 보낼 때 url 과 method 를 함께 요청을 보낸다.
브라우저의 주소창에 주소값을 적고 엔터는 url + get + 데이터 보내는 방식(key/value)으로 요청을 보낸다.
데이터를 보낼 때 보여지냐 안 보여지냐 차이로 보낼 수 있는데
1. 쿼리스트링 방식 (파라미터라고 부르기도 함) , url 에 데이터를 포함하는 방식
2. body 에 담아서 보내는 방식 (FormData , JSON)
JSON (JavaScript Object Notation) : 자바스크립트에서 객체를 만들 때 사용하는 문법을
                                    이용하여 데이터를 표현하는 포맷(형식), Key & Value 로 이루어져 있음
예를 들어 name 은 홍길동 , age는 22살 , height는 178.2 데이터를 JSON 으로 표현을 하면
{
    "name" : "홍길동",
    "age" : 22,
    "height" : 178.2
}
이렇게 표현하는 문자열이다.
{} 는 객체를 의미하고 [] 배열을 의미. "" 문자열 , 숫자형은 ""없이 표현
Key 는 무조건 "" 감싸줘야 한다.

쿼리스트링 모양 : url + 쿼리스트링 (?로 시작 key=value, 여러개라면 & 구분)
               www.naver.com?name=홍길동&age=12&height=172.1

대용량의 데이터를 보내야할 때도 body 에 데이터를 담아서 보낸다.
url 은 길이제한이 있기 때문에 url 에 데이터를 포함하는 쿼리스트링은 대용량을 보낼 수 없다.

Restful 이전에는 get , post 방식 밖에 없엇음.
get 방식은 주로 쿼리스트링 방식을 사용하고 - 데이터를 읽어올 때 (간혹 삭제때도 사용)
post 방식은 body 에 데이터를 담아서 보내는 방식을 사용했었다. - 데이터를 저장/수정/삭제할 때
데이터가 있었을 때는 get 방식 처리 속도가 빠름 , 데이터 처리가 아닌 단순 화면을 띄울 때도 get 방식을 사용

예를 들어 로그인을 하는 상황에서 로그인을 하는 화면이 띄워져야 한다.
작업(1) : 로그인 하는 화면은 get 방식으로 url 은 /login 을 요청하는 로그인하는 화면이 화면에 나타났다.
         (get) / login 이렇게 표현
작업(2) : 그 다음, 아이디 / 비번을 작성하고 로그인 버튼을 누르면 (post)/login
         아이디/비번은 body 에 담아서 요청을 보냈다.

url 은 같은데 method(get/post)로 작업을 구분했다.(마치 if문 처럼)

위 작업은 2가지 밖에 없었기 때문에 같은 주소값으로 method 를 구분할 수 있었다.
그런데 CRUD(작업 4가지)를 해야되는 상황에서는 작업 구분을 주소값으로 해야 해었다.

(get) /board - 게시판 리스트 보기 화면
(get) /board_detail - 게시판 글 하나 보기 화면
(get) /board_create - 게시판 글 등록하는 화면
(post) /board_modify - 게시판 글 등록하는 작업 처리
(get) /board_modify - 게시판 글 수정하는 화면
(post) /board_modify - 게시판 글 수정하는 작업 처리
(get/post) /board_delete - 게시판 글 삭제하는 작업 처리

URL 과 method 를 함께 요청을 보낸다

첫 페이지(index 화면)를 띄울 때 소프트웨어(Frontend 작업 코드)가 모두 다운로드 됨
화면 이동은 모두 FE 코드가 작동하는 것. 화면 만들기는 Client 리소스를 사용하여 그린다(rendering)
화면마다 데이터가 필요하면 BE 요청을 한다 누가? FE 작업코드가 요청을 보냄
그래서 BE는 이제 화면을 신경쓰지 않아도 된다.
FE 코드가 요청한 작업에 응답만 잘해주면 된다.

client 리소스 : client , 즉 요청을 보낸 컴퓨터의 자원을 사용한다. (cpu , ram 등등)

Restful 방식은 화면 없이 작업만 신경쓰면 된다.
(요청의 method 는 크게 4가지로 나뉘어진다)
1. POST 방식 - create - insert 작업
2. GET 방식 -
3. PUT / PATCH 방식
4. DELETE 방식

POST , PUT/PATCH 방식은 주로 데이터를 body 에 담아서 보내고
GET , DELETE 방식은 Query String or Path Variable 을 사용해서 데이터를 보낸다.
FE 가 BE 한테 (url + method + 데이터) 요청(Request)을 하고 BE는 JSON 으로 응답(Response)
JSON - 객체를 직렬화 해서 string 으로 전송

(post) /board - 글 등록
(get) /board?page=1 - 리스트 데이터(row 가 여러개)
(get) /board/ - 끝에 /만 붙어도 위 url 과 다른 요청이 된다.
(get) /board?page=2 - 위에 page=1과 같은 요청이다 / url 이 같으면 같은 요청
(get) /board/1 - 튜플 1개 데이터(row 가 1줄) , 1은 pk , Path Variable
(put/patch) / board - 글 수정
(delete) /board - 글 삭제(Path Variable or Query String 으로 pk값 전달)


 */

import com.green.board.model.BoardInsReq;
import com.green.board.model.BoardSelOneRes;
import com.green.board.model.BoardSelRes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// final 붙은 멤버필드 DI 받을 수 있게 생성자를 만든다.
// 애노테이션 생략하면 오버로딩된 생성자를 직접 만들어주면 된다.

@RequiredArgsConstructor
@RestController // 빈 등록 + 컨트롤러 임명 , 빈등록은 스프링 컨테이너가 직접 객체화를 한다.
@RequestMapping("/board")
// 기본적으로 URL 은 /board 로 작업되게 기본값 설정

// 클라이언트 요청을 가장 먼저 받아들임
// @Rest , @Request 애노테이션 2개를 통해 url 요청을 매핑
// ex) /board 의 url 이 호출되면 BoardController 의 적절한 메서드가 실행
// service -> mapper -> service -> controller 통해서 전달받은 데이터를
// JSON 형태로 클라이언트에게 응답
public class BoardController {
    private final BoardService service;

    //@RequiredArgsConstructor 애노테이션을 붙이면 아래 생성자가 자동으로 만들어진다.
//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    //insert(Create)
    @PostMapping // (post) /board 요청이 오면 이 메소드가 응답 담당자
    // @PostMapping("/board") : @RequestMapping("/board") 이 코드가 없엇다면 url을 작성해줘야 한다.
    // @RequestBody 는 요청이 올 때 데이터가 JSON 형태로 오니깐 거기에 맞춰서 데이터를 받자는 약속
    public int insBoard(@RequestBody BoardInsReq p){
        System.out.println(p);
        return service.insBoard(p);

    }
    // 객체 > json 바꾸는 직렬화 작업
    @GetMapping
    // 위에서 @RequestMapping("/board") 해놨기 때문에 여기 메소드 부분은 /board로 실행
    public List<BoardSelRes> selBoardList(){
        return service.selBoardList();
    }

    @GetMapping("{boardId}")
    // ("{boardId}") 작성해놔서 /board/boardId 로 실행됨
    // ex board/1 -> sql board_id 1번 실행
    public BoardSelOneRes selBoardOne(@PathVariable int boardId){
        System.out.println(boardId);
        return service.selBoardOne(boardId);
    }
}


