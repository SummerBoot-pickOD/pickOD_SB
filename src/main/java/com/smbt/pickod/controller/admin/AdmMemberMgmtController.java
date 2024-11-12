package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.member.AdmMemberDetailsDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberFilterDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberMgmtDTO;
import com.smbt.pickod.dto.admin.message.AdmToSendMsgDTO;
import com.smbt.pickod.dto.admin.message.AdmToWriteMsgDTO;
import com.smbt.pickod.service.admin.AdmMemberMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/admMemberMgmt")
public class AdmMemberMgmtController {
    private final AdmMemberMgmtService admMemberMgmtService;

    @GetMapping("/list")
    public String admMemberMgmt(Model model) {
        List<AdmMemberMgmtDTO> members = admMemberMgmtService.getMembersList();
        model.addAttribute("members", members);
        return "/admin/admUserManagement";
    }

    @GetMapping("/details")
    @ResponseBody
    public ResponseEntity<AdmMemberDetailsDTO> admMemberDetails(@RequestParam Long memberNum) {
             log.info(memberNum.toString());

            AdmMemberDetailsDTO details = admMemberMgmtService.getMemberDetails(memberNum);
            return ResponseEntity.ok(details);
    }

    @GetMapping("/search")
    public String admMemberSearch(AdmMemberFilterDTO admMemberFilterDTO, Model model) {
        System.out.println("Search Option: " + admMemberFilterDTO.getSearchOption());
        System.out.println("Keyword: " + admMemberFilterDTO.getKeyword());
        System.out.println("Status: " + admMemberFilterDTO.getStatus());
        List<AdmMemberMgmtDTO> members = admMemberMgmtService.filterMembersList(admMemberFilterDTO);
        model.addAttribute("members", members);
        return "/admin/admUserManagement";
    }

    @GetMapping("/toSanction")
    public String admMemberToSanction(@RequestParam Long memNum, Model model) {
        String memNickName = admMemberMgmtService.getMemberNickName(memNum);
        model.addAttribute("memNick", memNickName);
        model.addAttribute("opNick", "nick");
        return "/admin/admReport";
    }


//    @GetMapping("/details/sendMsg")
//    @ResponseBody
//    public ResponseEntity<AdmToSendMsgDTO> admMemberDetails(@RequestParam Long memberNum, @RequestParam String msg) {
//        log.info(memberNum.toString());
//
//    }
}
