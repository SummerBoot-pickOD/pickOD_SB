package com.smbt.pickod.controller.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.service.login.LoginService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller @Slf4j
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @GetMapping("login")
    public String login(){
        return "/login/login";
    }

    @PostMapping("doLogin")
    public RedirectView login(@RequestParam("memberId") String memberId, @RequestParam("memberPassword") String memberPassword, HttpSession session){
        log.info("로그인 시도 : {}, {} ",memberId,memberPassword);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId(memberId);
        loginDTO.setMemberPassword(memberPassword);

        LoginSessionDTO loginSession = loginService.loginCheck(loginDTO);

        session.setAttribute("memberNum", loginSession.getMemberNum());

        log.info("로그인 성공 : {}, {} ",memberId,memberPassword);
        log.info("세션ID : {}",session.getId());
        return new RedirectView("/main/main");
    }
    //로그아웃은 헤더에 있는 관계로 여기선 스킵
    @GetMapping("logout")
    public RedirectView logout(HttpSession session){
        session.removeAttribute("memberNum");
        session.invalidate();
        return new RedirectView("/main/main");
    }

    @GetMapping("lostId")
    public String lostId(){
        return "login/lostIdSelectCert";
    }

    @GetMapping("lostIdEmail")
    public String lostIdEmail(){
        return "login/lostIdEmailCert";
    }

    @GetMapping("lostPswd")
    public String lostPswd(){
        return "login/lostPswdSelectCert";
    }

    @GetMapping("lostPswdEmail")
    public String lostPswdEmail(){
        return "login/lostPswdEmailCert";
    }

    @PostMapping("sendCert")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> sendCert(@RequestBody Map<String,String> email) throws MessagingException {
        Map<String,Object> response = new HashMap<>();
        log.info("인증번호 요청 받음 : {} ",email);
        String certNum = loginService.sendEmail(email.get("email"));
        response.put("cert",certNum);
        response.put("success", true);
        log.info("인증번호 : {}", certNum);
        return ResponseEntity.ok(response);
    }

    @GetMapping("showId")
    public String showId(@RequestParam("certed-email")String email, Model model){
        log.info("인증된 이메일 : {} ",email);
        String res = loginService.getEmail(email);

        model.addAttribute("email",res);
        return "login/lostIdShow";
    }

    @PostMapping("goResetPswd")
    public String resetPswd(Model model, @RequestParam("certed-email")String email){
        model.addAttribute("email",email);
        return "login/lostPswdReset";
    }

    @PostMapping("resetPswd")
    public RedirectView resetPswd(@RequestParam("email")String email, @RequestParam("newPassword")String pswd){
        log.info("비밀번호 재설정 시도 : {}, {} ",email,pswd);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId(email);
        loginDTO.setMemberPassword(pswd);

        loginService.resetPassword(loginDTO);
        log.info("비밀번호 재설정 성공");
        return new RedirectView("/login/login");
    }

}
