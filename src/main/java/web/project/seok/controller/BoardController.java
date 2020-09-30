package web.project.seok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.project.seok.dto.BoardDto;
import web.project.seok.service.BoardService;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> dtoList=boardService.getBoardlist();
        model.addAttribute("boardList",dtoList);
        return "board/list.html";
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

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long id,Model model){
        BoardDto dto=boardService.getPost(id);
        model.addAttribute("boardDto",dto);
        return "board/detail.html";
    }
}
