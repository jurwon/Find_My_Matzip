package com.matzip.entity;

import com.matzip.constant.BoardViewStatus;
import com.matzip.constant.UserRole;
import com.matzip.dto.BoardFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board extends BaseEntity{

    @Id
    @Column(name="board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;       //게시글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant")
    private Restaurant restaurant;//식당

    @Column(nullable = false)
    private String board_title; //제목

    @Column
    private String content ; //내용

    @Column(nullable = false)
    private int score; //평점

    @Enumerated(EnumType.STRING)
    private BoardViewStatus boardViewStatus; //게시글 상태

    /* @Column(nullable = false)
    private String writeDate; //게시 일자*/


    public static Board createBoard(BoardFormDto boardFormDto, Restaurant restaurant) {
        Board board = new Board();
        board.setId(boardFormDto.getId());
        board.setRestaurant(restaurant);
        board.setBoard_title(boardFormDto.getBoard_title());
        board.setContent(boardFormDto.getContent());
        board.setScore(boardFormDto.getScore());
        board.setBoardViewStatus(boardFormDto.getBoardViewStatus());
        return board;
    }

    //게시글 데이터 수정 업데이트로직 -> boardService 이동 후 추가
    public void updateBoard(BoardFormDto boardFormDto){
        this.board_title = boardFormDto.getBoard_title();
        this.content = boardFormDto.getContent();
        this.score = boardFormDto.getScore();
        this.boardViewStatus = boardFormDto.getBoardViewStatus();
    }

}