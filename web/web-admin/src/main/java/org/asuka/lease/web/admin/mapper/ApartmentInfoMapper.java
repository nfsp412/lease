package org.asuka.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.asuka.lease.model.entity.ApartmentInfo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentItemVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentQueryVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentSubmitVo;

/**
* @author liubo
*  针对表【apartment_info(公寓信息表)】的数据库操作Mapper
*
*  com.atguigu.lease.model.ApartmentInfo
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> selectApartmentItemVoWithPageAndQueryVo(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);


}




