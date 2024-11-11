package com.green.board;

import com.green.board.model.BoardInsReq;
import com.green.board.model.BoardSelOneRes;
import com.green.board.model.BoardSelRes;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/*
src > main > resource > mappers 폴더 아래에 이름이 같은 xml 파일을 만든다.
(사실 똑같은 이름을 할 필요는 없으나 관리상 용이하게 하기 위해 같은 이름을 쓴다.)

xml + interface 파일을 이용해서 implements 한 class 파일을 만들 것이고 빈등록까지 해준다.
>> 스프링 컨테이너가 빈등록한 class 파일을 객체화 할 것이다. 여기서 만든 주소값을 BoardService 객체화
할 때 DI 해준다.

insert , update , delete 의 리턴타입은 int 하면 됨.
 */

// mybatis를 사용해 mapper 가 sql쿼리를 db에 실행하도록 매핑
// mapper.xml 에 namespace 로 mapper 인터페이스로 연결 -> 인터페이스에서 xml 쿼리문 사용 가능
// 클래스에서 인터페이스에 있는 메소드(파라미터)로 데이터 넘어오면 xml에 작성한 쿼리문으로 실행
@Mapper
public interface BoardMapper {
    int insBoard(BoardInsReq p);
    List<BoardSelRes> selBoardList();
    BoardSelOneRes selBoardOne(int p);
}
