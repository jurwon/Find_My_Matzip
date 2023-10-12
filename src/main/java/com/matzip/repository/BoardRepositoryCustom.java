package com.matzip.repository;


import com.matzip.dto.BoardSearchDto;
import com.matzip.dto.MainBoardDto;
import com.matzip.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    //게시글 조회 조건을 담고있는 BoardSearchDto 객체와 페이징 정보를 담고있는
    //pageable 객체를 파라미터로 받는 메소드를 정의한다 . 반환데이터로 Page<Board> 객체를 반환한다.
    //BoardRepositoryCustomImpl 클래스로가서 인터페이스를 구현해준다
    Page<Board> getAdminBoardPage(BoardSearchDto boardSearchDto, Pageable pageable);

    Page<MainBoardDto> getMainBoardPage(BoardSearchDto boardSearchDto, Pageable pageable);

}