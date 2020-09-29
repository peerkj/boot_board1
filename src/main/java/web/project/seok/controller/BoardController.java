package web.project.seok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.project.seok.dto.BoardDto;
import web.project.seok.service.BoardService;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(){
        return "board/list";
    }
    @GetMapping("/post")
    public String write(){
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto dto){
        boardService.savePost(dto);
        return "redirect:/";
    }
}
