package com.smbt.pickod.controller.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.service.login.LoginService;
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
        log.info("로그인 시도 : {} ",memberId);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setMemberId(memberId);
        loginDTO.setMemberPassword(memberPassword);

        LoginSessionDTO loginSession = loginService.loginCheck(loginDTO);

        session.setAttribute("loginInfo", loginSession.getMemberId());
        session.setAttribute("memberNum", loginSession.getMemberNum());

        return new RedirectView("/main/main");
    }
    //로그아웃은 헤더에 있는 관계로 여기선 스킵


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
    public ResponseEntity<Map<String,String>> sendCert(@RequestParam("email") String email){
        Map<String,String> response = new HashMap<>();

        //서비스 부르고? (원래는 createCert가 아니라 sendEmail이 옴)
        //ajax 통신임 이건
        //@Responsebody가 있다면 ajax/fetch등 js를 이용한 비동기 통신
        String certNum = loginService.createCert();
        response.put("cert",certNum);

        return ResponseEntity.ok(response);
    }

    @GetMapping("showId")
    public String showId(){
        return "login/lostIdShow";
    }
//    @RequestParam("email") String email,Model model
    @GetMapping("resetPswd")
    public String resetPswd(){
        //model.addAttribute("email",email);
        return "login/lostPswdReset";
    }

    @PostMapping("resetPswd")
    public RedirectView resetPswd(Model model){
//        LoginDTO loginDTO = new LoginDTO();
//        loginDTO.setMemberId((String) model.getAttribute("memberId"));
//        loginDTO.setMemberPassword((String) model.getAttribute("memberPassword"));
//
//        loginService.resetPassword(loginDTO);

        return new RedirectView("/login/login");
    }


}
