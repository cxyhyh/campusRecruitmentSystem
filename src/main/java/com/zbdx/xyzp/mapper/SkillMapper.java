package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.SkillDTO;
import com.zbdx.xyzp.model.entity.Skill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface SkillMapper extends BaseMapper<Skill> {

    List<SkillDTO> getSkill();

    Page<SkillDTO> pageSkill(Page<SkillDTO> page , @Param("param") SkillDTO skillDTO);

    List<Map<String , Object>> getEnglishSkill();

    List<Map<String , Object>> getComputerSkill();

    List<Map<String , Object>> getAppliedPosition();



}
