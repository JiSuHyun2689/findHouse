package org.suhyun.findhouse.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.suhyun.findhouse.dto.WishDTO;
import org.suhyun.findhouse.service.MemberService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("/ajax")
@RequiredArgsConstructor
public class AjaxController{

    @Autowired
    private MemberService memberService;


    @GetMapping("/duplicateCheckId")
    public ResponseEntity<Boolean> duplicateCheckId(String id){

        log.info("Duplicate Check Id : " + id + ".............................................................");

        boolean result = false;

        if(memberService.read(id) != null){
            result = true;
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*
    @GetMapping("/{houseNum}/all")
    public ResponseEntity<Integer> getList(@PathVariable("houseNum") Long houseNum) {

        log.info("wish list ----  houseNum : " + houseNum + " ------------------------------------------------------");

        List<WishDTO> wishDTOList = wishService.getListOfHouse(houseNum);

        return new ResponseEntity<>(wishDTOList.size(), HttpStatus.OK);
    }



    public ResponseEntity<Map<String, Boolean>> getIdCheck(String id) {
        log.info("getIdCheck call........" + id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("result", memberService.checkID(id));
        return new ResponseEntity<Map<String, Boolean>> (map, HttpStatus.OK);
    }
*/
}
