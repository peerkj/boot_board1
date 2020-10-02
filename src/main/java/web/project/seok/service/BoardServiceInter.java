package web.project.seok.service;

import web.project.seok.domain.entity.Board;
import web.project.seok.dto.BoardDto;

import java.util.List;

public interface BoardServiceInter {
    public void savePost(BoardDto dto);
    List<BoardDto> getBoardlist(Integer pageNum);
    BoardDto getPost(Long id);
    void deletePost(Long id);

    List<BoardDto> searchPosts(String keyword);
    BoardDto convertEntityToDto(Board board);

    Integer[] getPageList(Integer pageNum);
}
