package com.smbt.pickod.service.login;

import com.smbt.pickod.dto.login.LoginDTO;
import com.smbt.pickod.dto.login.LoginSessionDTO;
import com.smbt.pickod.mapper.login.LoginMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class LoginService{
    private final LoginMapper loginMapper;
    private final JavaMailSender mailSender;

    //로그인 잘못됐는지, 제재중인지 확인
    public int loginCheck(LoginDTO loginDTO) {

        LoginSessionDTO loginSessionDTO = loginMapper.isMemberExist(loginDTO).orElse(null);
        //log.info(loginDTO.toString());
        if(loginSessionDTO == null) {
            // 맞는 조합 없음
            return -1;
        }

        if(!loginMapper.checkSanction(loginSessionDTO).orElse("N").equals("N")){
            //진행중인 제재가 있음
            return -2;
        }
        return 0;
    }

    //아이디 찾기
    public String getMemberId(String inputEmail) {
        return loginMapper.findEmail(inputEmail).orElse("해당 이메일로 가입한 아이디가 없습니다.");
    }

    //비밀번호 재설정
    public void resetPassword(LoginDTO loginDTO) {
        loginMapper.resetPassword(loginDTO);
    }

    //---------------------------------이메일 인증

    //인증번호 생성
    public String createCert() {
        String cert = Integer.toString((int)(Math.random() * 1000000));

        while(cert.length() < 6){
            cert = "0" + cert;
        }

        return cert;
    }

    //이메일 폼 생성
    public MimeMessage createEmailMessage(String toEmail, String cert) throws MessagingException {
        MimeMessage mimeMsg = mailSender.createMimeMessage();
        mimeMsg.addRecipients(MimeMessage.RecipientType.TO, toEmail);
        mimeMsg.setSubject("[pickOD] 이메일 인증 인증번호");
        mimeMsg.setFrom("gabin1426@gmail.com");
        mimeMsg.setText("<p>안녕하세요. 픽오디입니다.</p> <p>이메일 인증을 원하시면 아래의 인증번호를 인증란에 입력해주세요.</p>" +
                "<p>인증번호 : <strong>"+ cert +"</strong></p>","UTF-8","html");

        return mimeMsg;
    }

    //이메일 보내기
    public String sendEmail(String toEmail) throws MessagingException {
        String cert = createCert();
        MimeMessage mail = createEmailMessage(toEmail,cert);
        mailSender.send(mail);

        return cert;
    }

    //인증번호 비교를 db가 아닌 프론트 단에서 할 거기 때문에, 인증용 model은 필요 없음.
}
