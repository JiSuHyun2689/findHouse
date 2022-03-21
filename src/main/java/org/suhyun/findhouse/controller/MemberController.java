package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.suhyun.findhouse.dto.MemberDTO;
import org.suhyun.findhouse.service.MemberService;

@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {


    @Autowired
    private MemberService service;

    @GetMapping("/join")
    public void join() {

        log.info("Member Join ------------------------------------------------------------------------");

    }

    @GetMapping("/modify")
    public void modify(@Param("id") String id, Model model) {

        log.info("Member Modify ------------------------------------------------------------------------");

        MemberDTO dto = service.read(id);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute("dto") MemberDTO dto, RedirectAttributes redirectAttributes) {

        log.info("Member Join Post ------------------------------------------------------------------------");
        log.info(dto);

        String id = service.register(dto);

        if (id != null)
            redirectAttributes.addFlashAttribute("msg", "회원가입을 완료하였습니다.");

        return "redirect:/house/list";
    }


    @PostMapping("/modify")
    public String modifyPost(@ModelAttribute("dto") MemberDTO dto, RedirectAttributes redirectAttributes) {

        log.info("Member modify Post ------------------------------------------------------------------------");
        log.info(dto);

        String id = service.modify(dto);

        if (id != null)
            redirectAttributes.addFlashAttribute("msg", "회원 정보가 수정되었습니다.");

        return "redirect:/house/list";
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "/remove")
    public String removePost(@Param("id") String id, RedirectAttributes redirectAttributes) {

        log.info("Member Remove Post ------------------------------------------------------------------------");
        log.info(id);

        service.remove(id);

        redirectAttributes.addFlashAttribute("msg", "회원 탈퇴가 완료되었습니다.");

        return "redirect:/house/list";
    }
}
