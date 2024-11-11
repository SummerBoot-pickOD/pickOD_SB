package com.smbt.pickod.service.admin;

import com.smbt.pickod.dto.admin.member.AdmMemberDetailsDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberFilterDTO;
import com.smbt.pickod.dto.admin.member.AdmMemberMgmtDTO;
import com.smbt.pickod.dto.admin.member.AdmToSanctionDTO;
import com.smbt.pickod.mapper.admin.AdmMemberMgmtMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdmMemberMgmtService {
    private final AdmMemberMgmtMapper admMemberMgmtMapper;

    public List<AdmMemberMgmtDTO> getMembersList(){
        return admMemberMgmtMapper.getMembers();
    }

    public List<AdmMemberMgmtDTO> filterMembersList(AdmMemberFilterDTO admMemberFilterDTO) {
        return admMemberMgmtMapper.filterMembers(admMemberFilterDTO);
    }

    public Long getMemberNum(String memberId) {
        return admMemberMgmtMapper.getMemNum(memberId).orElseThrow(()->new IllegalStateException("존재하지 않는 회원 아이디입니다."));
    }
    public AdmMemberDetailsDTO getMemberDetails(Long memberNum) {
        return admMemberMgmtMapper.getMemDetails(memberNum).orElseThrow(()->new IllegalStateException("존재하지 않는 회원입니다."));
    }

    public AdmToSanctionDTO memberToSanction(String memberId) {
        return admMemberMgmtMapper.memToSanction(memberId).orElseThrow(()->new IllegalStateException("존재하지 않는 회원 아이디입니다."));
    }

    public void deleteMember(Long memberId) {
        admMemberMgmtMapper.deleteMember(memberId);
    }

}
