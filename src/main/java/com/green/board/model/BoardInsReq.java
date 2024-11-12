package com.green.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class BoardInsReq { // id 없어서 선택 불가능 / insert 만
    private String title;
    private String contents;
    private String writer;
}
