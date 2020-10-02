package web.project.seok.service;

import web.project.seok.dto.BoardDto;

import java.util.List;

public interface BoardServiceInter {
    public void savePost(BoardDto dto);
    List<BoardDto> getBoardlist();
    BoardDto getPost(Long id);
    void deletePost(Long id);
}
