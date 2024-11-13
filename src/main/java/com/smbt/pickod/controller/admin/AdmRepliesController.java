package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlRepliesDTO;
import com.smbt.pickod.dto.admin.journal.AdmJnlReplyDetailsDTO;
import com.smbt.pickod.dto.admin.journal.AdmPlaceReplyDetailsDTO;
import com.smbt.pickod.dto.admin.place.AdmPlaceRepliesDTO;
import com.smbt.pickod.service.admin.AdmRepliesMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/admReplies")
public class AdmRepliesController {
    private final AdmRepliesMgmtService admRepliesMgmtService;

    // 여행 발자국 관리 페이지 [댓글보기]
    @GetMapping("/journal")
    public String getJnlReplies(@RequestParam Long jnlNum, Model model) {
        List<AdmJnlRepliesDTO> replies = admRepliesMgmtService.jnlReplies(jnlNum);
        String jnlTitle = admRepliesMgmtService.getJnlTitle(jnlNum);
        model.addAttribute("replies", replies);
        model.addAttribute("postType", "여행 발자국");
        model.addAttribute("postTitle", jnlTitle);
        model.addAttribute("postId", jnlNum);
        return "admin/admReplies";
    }

    // 여행 발자국 댓글 페이지 [상세보기]
    @GetMapping("journalCmt/details")
    @ResponseBody
    public ResponseEntity<AdmJnlReplyDetailsDTO> getJnlCmtDetails(@RequestParam Long cmtId) {
        log.info(cmtId.toString());

        AdmJnlReplyDetailsDTO details = admRepliesMgmtService.jnlReplyDetails(cmtId);
        return ResponseEntity.ok(details);
    }

    // 장소 관리 페이지 [댓글보기]
    @GetMapping("/place")
    public String getPlaceReplies(@RequestParam Long placeId, Model model) {
        List<AdmPlaceRepliesDTO> replies = admRepliesMgmtService.placeReplies(placeId);
        String placeName = admRepliesMgmtService.getPlaceTitle(placeId);
        model.addAttribute("replies", replies);
        model.addAttribute("postType", "장소");
        model.addAttribute("postTitle", placeName);
        model.addAttribute("postId", placeId);
        return "admin/admReplies";
    }

    // 장소 댓글 페이지 [상세보기]
    @GetMapping("/placeCmt/details")
    @ResponseBody
    public ResponseEntity<AdmPlaceReplyDetailsDTO> getPlaceCmtDetails(@RequestParam Long cmtId) {
        log.info(cmtId.toString());

        AdmPlaceReplyDetailsDTO details = admRepliesMgmtService.placeReplyDetails(cmtId);
        return ResponseEntity.ok(details);
    }


    // 댓글 삭제
    @GetMapping("/deleteCmt")
    public String deleteCmt(@RequestParam Long cmtId) {
        admRepliesMgmtService.deleteCmt(cmtId);
        return "redirect:/admin/admMemberMgmt/list";
    }

}
