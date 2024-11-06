package com.smbt.pickod.controller.signup;

import com.smbt.pickod.dto.signup.SignUpMemberDTO;
import com.smbt.pickod.service.login.LoginService;
import com.smbt.pickod.service.signup.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("signupTerms")
    public String signupTerms() {
        return "/signup/signupTerms";
    }

    @GetMapping("signup")
    public String signup() {
        return "/signup/signup";
    }

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

    @PostMapping("isNickUnique")
    @ResponseBody
    public ResponseEntity<Map<String,Object>> isNickDup(@RequestBody Map<String,String> nickname){
        Map<String,Object> response = new HashMap<>();

        boolean isUnique = signupService.isNicknameUnique(nickname.get("nickname"));
        response.put("success", true);
        response.put("isUnique", isUnique);
        return ResponseEntity.ok(response);
    }
}
