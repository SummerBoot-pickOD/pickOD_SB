package com.smbt.pickod.controller.signup;

import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.service.login.LoginService;
import com.smbt.pickod.service.signup.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("register")
    public String registerMember(@ModelAttribute SignUpMemberDTO signUpMemberDTO) {
        signupService.signupMember(signUpMemberDTO);

        return "redirect:/login/login";
    }

    @PostMapping("sendCert")
    @ResponseBody
    public ResponseEntity<Map<String,String>> sendCert(@RequestParam("email") String email){
        Map<String,String> response = new HashMap<>();

        //서비스 부르고? (원래는 createCert가 아니라 sendEmail이 옴)
        //ajax 통신임 이건
        String certNum = loginService.createCert();
        response.put("cert",certNum);

        return ResponseEntity.ok(response);
    }

    @GetMapping("isNickUnique")
    @ResponseBody
    public Map<String,String> isNickDup(@RequestParam("nickname") String nickname){
        Map<String,String> response = new HashMap<>();

        String unique = signupService.isNicknameUnique(nickname) ? "Yes" : "No";

        response.put("isUnique",unique);
        return response;
    }
}
