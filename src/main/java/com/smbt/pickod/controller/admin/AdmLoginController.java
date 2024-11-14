package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.login.AdmLoginDTO;
import com.smbt.pickod.dto.admin.login.AdmSessionDTO;
import com.smbt.pickod.service.admin.AdmLoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdmLoginController {
    private final AdmLoginService admLoginService;

    @GetMapping("admLogin")
    public String login(){
        return "/admin/admLogin";
    }


    @PostMapping("doAdmLogin")
    public RedirectView login(@RequestParam("memberId") String memberId, @RequestParam("memberPassword") String memberPassword, HttpSession session){
        log.info("관리자 로그인 시도 : {}, {} ", memberId, memberPassword);
        AdmLoginDTO admLoginDTO = new AdmLoginDTO();
        admLoginDTO.setMemberId(memberId);
        admLoginDTO.setMemberPassword(memberPassword);

        try {
            AdmSessionDTO admSession = admLoginService.login(admLoginDTO);
            session.setAttribute("memberNum", admSession.getMemberNum());
            session.setAttribute("memberId", admSession.getMemberId());

            log.info("로그인 성공 : {}, {} ",memberId,memberPassword);
            log.info("세션ID : {}",session.getId());
            return new RedirectView("/admin/admMemberMgmt/list");
        } catch (Exception e) {
            return new RedirectView("/admin/admLogin");
        }
    }

    @GetMapping("/admLogout")
    public RedirectView logout(HttpSession session){
        session.removeAttribute("memberNum");
        session.invalidate();
        return new RedirectView("/admin/admLogin");
    }

    @GetMapping("/findPwd")
    public String findPwd(){
        return "admin/admEmailCert";
    }

//    @PostMapping("emailCert")
//    public String sendEmail
}
