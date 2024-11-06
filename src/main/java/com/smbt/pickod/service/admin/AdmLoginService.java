package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.login.AdmLoginDTO;
import com.smbt.pickod.dto.admin.login.AdmSessionDTO;
import com.smbt.pickod.mapper.admin.AdmLoginMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdmLoginService {
    @Autowired
    private final AdmLoginMapper admLoginMapper;
    private final JavaMailSender mailSender;
    //    private final String admEmail = "pickOD@gmail.com"
    private final String admEmail = "admin@gmail.com";

    //로그인 잘못됐는지, 제재중인지 확인
    public AdmSessionDTO login(AdmLoginDTO admLoginDTO) {

        return admLoginMapper.isAdmin(admLoginDTO).orElseThrow(() -> new IllegalStateException("관리자 로그인 정보가 틀렸습니다."));
    }

    //비밀번호 찾기
    public String getPwd(String loginId) {
        return admLoginMapper.findPwd(loginId);
    }

    /* 이메일 인증 통과 시 비밀번호 보여줌 */

    //인증번호 생성
    public String createCert() {
        return String.format("%06d", (int)(Math.random() * 1000000));
    }

    //인증번호 / 비밀번호 이메일 폼 생성
    public MimeMessage createEmail(String email, String toSend, String subject) throws MessagingException {
        MimeMessage mimeMsg = mailSender.createMimeMessage();
        mimeMsg.addRecipients(MimeMessage.RecipientType.TO, email);
        mimeMsg.setSubject(subject);
        mimeMsg.setFrom(email);
        mimeMsg.setText("<p>픽오디 관리자 인증 번호 / 비밀번호 발송 이메일</p>" +
                "<p>인증번호 : <strong>" + toSend + "</strong></p>", "UTF-8", "html");

        return mimeMsg;
    }


    //인증번호 보내기
    // "[pickOD] 관리자 이메일 인증번호"
    public String sendCode() {
        String subject = "[pickOD] 관리자 이메일 인증번호";

        try {
            MimeMessage mail = createEmail(this.admEmail, createCert(), subject);
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "관리자 이메일 인증번호 발송 완료";
    }

    // 이메일 인증번호 인증 완료 시 비밀번호 이메일 발송
    public String sendPwd() {
        String pwd = getPwd(this.admEmail);
        String subject = "[pickOD] 관리자 비밀번호";

        try {
            MimeMessage mail = createEmail(this.admEmail, pwd, subject);
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "관리자 비밀번호 발송 완료";
    }


}
