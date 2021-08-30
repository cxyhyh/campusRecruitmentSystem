package com.zbdx.xyzp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zbdx.xyzp.mapper.SkillMapper;
import com.zbdx.xyzp.model.dto.SkillDTO;
import com.zbdx.xyzp.model.entity.Skill;
import com.zbdx.xyzp.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SkillServiceImpl extends ServiceImpl<SkillMapper , Skill> implements SkillService {

    @Autowired
    private SkillMapper skillMapper;
    @Override
    public List<SkillDTO> getSkill() {
        return skillMapper.getSkill();
    }

    @Override
    public Page<SkillDTO> pageSkill(Page<SkillDTO> page, SkillDTO skillDTO) {
        return skillMapper.pageSkill(page, skillDTO);
    }

    @Override
    public List<Map<String, Object>> getEnglishSkill() {
        return skillMapper.getEnglishSkill();
    }

    @Override
    public List<Map<String, Object>> getComputerSkill() {
        return skillMapper.getComputerSkill();
    }

    @Override
    public List<Map<String, Object>> getAppliedPosition() {
        return skillMapper.getAppliedPosition();
    }

    @Override
    public Skill selectSkillByUsername(String username) {
        return this.skillMapper.selectSkillByUsername(username);
    }
}
