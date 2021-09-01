package com.zbdx.xyzp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbdx.xyzp.model.dto.SkillDTO;
import com.zbdx.xyzp.model.entity.Skill;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface SkillService extends IService<Skill> {
    List<SkillDTO> getSkill();

    Page<SkillDTO> pageSkill(Page<SkillDTO> page , SkillDTO skillDTO);

    List<Map<String , Object>> getEnglishSkill();

    List<Map<String , Object>> getComputerSkill();

    List<Map<String , Object>> getAppliedPosition();

    Skill selectSkillByUsername(String username);

    Skill selectByUsername(String username);
}
