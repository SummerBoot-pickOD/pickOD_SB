package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.member.AdmMemberDetailsDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberFilterDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberMgmtDTO;
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
    public ResponseEntity<?> admMemberDetails(@RequestParam Long memberNum) {
        try {
//            Long memNum = admMemberMgmtService.getMemberNum(memberId);
            AdmMemberDetailsDTO details = admMemberMgmtService.getMemberDetails(memberNum);
            return ResponseEntity.ok(details);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

//    @GetMapping("/search")
//    public String admMemberSearch(@RequestParam String keyword, Model model) {
//        List<AdmMemberFilterDTO> members = admMemberMgmtService.filterMembersList(keyword);
//    }

}
