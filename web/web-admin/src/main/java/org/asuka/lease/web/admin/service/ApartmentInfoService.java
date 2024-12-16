package org.asuka.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.asuka.lease.model.entity.ApartmentInfo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentDetailVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentItemVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentQueryVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentSubmitVo;

/**
* @author liubo
*  针对表【apartment_info(公寓信息表)】的数据库操作Service
*
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {

    IPage<ApartmentItemVo> getApartmentItemVoWithPageAndQueryVo(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    void saveOrUpdateApartmentSubmitVo(ApartmentSubmitVo apartmentSubmitVo);

    ApartmentDetailVo getApartmentDetailVoById(Long id);

    void removeByIdAndOther(Long id);
}
