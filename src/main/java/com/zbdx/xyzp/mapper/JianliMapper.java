package com.zbdx.xyzp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbdx.xyzp.model.dto.JianLiDTO;
import com.zbdx.xyzp.model.entity.JianLi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface JianliMapper extends BaseMapper<JianLi> {
    List<JianLiDTO> getJianli(JianLiDTO jianLiDTO);

    Integer getMaxJianliId(String fileName);

    List<JianLiDTO> getNewFileNameByUsername(String username);

    Page<JianLiDTO> pageJianLi(Page<JianLiDTO> page, @Param("param")JianLiDTO jianLiDTO);

    List<Map<String, Object>> getUserName();

    List<Map<String, Object>> getApplicationPosition();
}
