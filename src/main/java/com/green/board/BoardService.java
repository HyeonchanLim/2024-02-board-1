package com.green.board;

import com.green.board.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
@Service - 빈 등록 , 서비스가 로직처리 담당 , 로직처리가 있다면 여기서 처리한다.
           없으면 연결 작업만... 연결작업이 Controller 와 Persistence(DB) 연결

빈 등록 : 스프링 컨테이너에게 객체 생성을 대리로 맡기는 것, 기본적으로 싱글톤으로 객체화

xml + interface 파일을 이용해서 implements 한 class 파일을 만들 것이고 빈등록까지 해준다.
스프링 컨테이너가 빈등록한 class 파일을 객체화 할 것이다.
여기서 만든 주소값은 BoardService 객체화 할 때 DI 해준다.

 */

@RequiredArgsConstructor
@Service
// 컨트롤러는 요청을 처리하기 위해 BoardService 를 호출
// 서비스 클래스는 실제 비즈니스 로직을 수행 -> db 접근 필요하면 mapper를 통해 db작업
// mapper 까지 다녀와서 처리된 데이터 결과를 controller 로 전달
public class BoardService {
    private final BoardMapper mapper; // 빈등록 ,DI

    public int insBoard(BoardInsReq p){
        return mapper.insBoard(p);
    }
    public List<BoardSelRes> selBoardList(){
        return mapper.selBoardList();
    }
    public BoardSelOneRes selBoardOne(int p){
        return mapper.selBoardOne(p);
    }
    public int updBoard(BoardUpdReq p){
        return mapper.updBoard(p);
    }
    public int delBoard(BoardDelReq p){
        return mapper.delBoard(p);
    }
}
