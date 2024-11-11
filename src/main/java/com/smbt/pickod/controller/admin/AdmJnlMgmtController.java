package com.smbt.pickod.controller.admin;

import com.smbt.pickod.dto.admin.journal.AdmJnlMgmtDTO;
import com.smbt.pickod.service.admin.AdmJnlMgmtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/admJnlMgmt")
public class AdmJnlMgmtController {
    private final AdmJnlMgmtService admJnlMgmtService;

    @GetMapping("/list")
    public String admJnlMgmt(Model model) {
        List<AdmJnlMgmtDTO> jnls = admJnlMgmtService.getJnlList();
        model.addAttribute("jnls", jnls);
        return "/admin/admJournal";
    }
}
