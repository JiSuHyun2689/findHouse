package org.suhyun.findhouse.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.suhyun.findhouse.dto.HouseDTO;
import org.suhyun.findhouse.dto.PageRequestDTO;
import org.suhyun.findhouse.service.HouseService;

@Controller
@Log4j2
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController {

    private final HouseService houseService;

    @GetMapping("/test")
    public void test(Long houseNum, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model) {
        log.info("Sample controller test -----------------------------------");

        log.info("house read " + houseNum + " ...........................");

        HouseDTO dto = houseService.read(houseNum);

        model.addAttribute("dto", dto);
    }
}
