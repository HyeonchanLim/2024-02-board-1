package com.green.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardSelRes { // id 있어서 선택 가능 -> select 가능
    private int boardId;
    private String title;
    private String writer;
    private String createdAt;


}
