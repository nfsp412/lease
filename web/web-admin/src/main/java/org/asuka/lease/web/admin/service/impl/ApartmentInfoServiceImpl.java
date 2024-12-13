package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import kotlin.jvm.internal.Lambda;
import org.asuka.lease.common.exception.LeaseException;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.model.entity.*;
import org.asuka.lease.model.enums.ItemType;
import org.asuka.lease.web.admin.mapper.*;
import org.asuka.lease.web.admin.service.*;
import org.asuka.lease.web.admin.vo.apartment.ApartmentDetailVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentItemVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentQueryVo;
import org.asuka.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import org.asuka.lease.web.admin.vo.fee.FeeValueVo;
import org.asuka.lease.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liubo
 * 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
 */
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {
    @Autowired
    private GraphInfoService graphInfoService;

    @Autowired
    private ApartmentFacilityService apartmentFacilityService;

    @Autowired
    private ApartmentLabelService apartmentLabelService;

    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;


    @Resource
    private ApartmentInfoMapper apartmentInfoMapper;

    @Resource
    private ProvinceInfoMapper provinceInfoMapper;
    @Resource
    private CityInfoMapper cityInfoMapper;
    @Resource
    private DistrictInfoMapper districtInfoMapper;

    @Resource
    private GraphInfoMapper graphInfoMapper;
    @Resource
    private LabelInfoMapper labelInfoMapper;
    @Resource
    private FeeValueMapper feeValueMapper;
    @Resource
    private FacilityInfoMapper facilityInfoMapper;

    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    public IPage<ApartmentItemVo> getApartmentItemVoWithPageAndQueryVo(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.selectApartmentItemVoWithPageAndQueryVo(page, queryVo);
    }

    @Transactional
    @Override
    public void saveOrUpdateApartmentSubmitVo(ApartmentSubmitVo apartmentSubmitVo) {
        /*
        判断是更新还是新增
        需要先判断,否则下面代码执行后id会回显
        id为空证明是新增
        id不为空证明是更新
        对于更新而言,因为没办法确定删除的是什么,所以先删除再新增
         */
        boolean isUpdate = apartmentSubmitVo.getId() != null;

        /*
        公寓信息的更新或新增,直接调用super即可,或者还是自动注入
        ApartmentSubmitVo是ApartmentInfo子类,所以可以传参
        问题是前端传参没有省市区的名称,所以这里应该先获取再插入
         */
//        apartmentInfoService.saveOrUpdate(apartmentSubmitVo);
        //获取省市区的名称
        ProvinceInfo provinceInfo = provinceInfoMapper.selectById(apartmentSubmitVo.getProvinceId());
        CityInfo cityInfo = cityInfoMapper.selectById(apartmentSubmitVo.getCityId());
        DistrictInfo districtInfo = districtInfoMapper.selectById(apartmentSubmitVo.getDistrictId());
        apartmentSubmitVo.setProvinceName(provinceInfo.getName());
        apartmentSubmitVo.setCityName(cityInfo.getName());
        apartmentSubmitVo.setDistrictName(districtInfo.getName());
        super.saveOrUpdate(apartmentSubmitVo);

        if (isUpdate) {
            this.deleteApartmentOthersInfo(apartmentSubmitVo.getId());
        }

        /*
        新增图片列表
        使用save batch批量插入
        使用 spring 的 CollectionUtils 判空
         */
        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            List<GraphInfo> graphInfoList =
                    graphVoList.stream()
                            .map(graphVo ->
                                    GraphInfo.builder()
                                            .url(graphVo.getUrl())
                                            .name(graphVo.getName())
                                            .itemId(apartmentSubmitVo.getId())
                                            .itemType(ItemType.APARTMENT)
                                            .build())
                            .toList();
            graphInfoService.saveBatch(graphInfoList);
        }
        /*
        新增配套列表
        同理
         */
        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            List<ApartmentFacility> apartmentFacilityList =
                    facilityInfoIds.stream()
                            .map(facilityId ->
                                    ApartmentFacility.builder()
                                            .apartmentId(apartmentSubmitVo.getId())
                                            .facilityId(facilityId)
                                            .build())
                            .toList();
            apartmentFacilityService.saveBatch(apartmentFacilityList);
        }
        /*
        新增标签列表
        同理
         */
        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList =
                    labelIds.stream()
                            .map(labelId ->
                                    ApartmentLabel.builder()
                                            .labelId(labelId)
                                            .apartmentId(apartmentSubmitVo.getId())
                                            .build())
                            .toList();
            apartmentLabelService.saveBatch(apartmentLabelList);
        }
        /*
        新增杂费列表
        同理
         */
        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            List<ApartmentFeeValue> apartmentFeeValueList =
                    feeValueIds.stream()
                            .map(feeValueId ->
                                    ApartmentFeeValue.builder()
                                            .feeValueId(feeValueId)
                                            .apartmentId(apartmentSubmitVo.getId())
                                            .build())
                            .toList();
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }
    }

    @Override
    public ApartmentDetailVo getApartmentDetailVoById(Long id) {
        ApartmentInfo apartmentInfo = this.getById(id);
        if (apartmentInfo == null) {
            return null;
        }
        //获取图片列表
        List<GraphVo> graphVoList = graphInfoMapper.getGraphVoList(ItemType.APARTMENT, id);
        //获取标签列表
        List<LabelInfo> labelInfoList = labelInfoMapper.getLabelInfoList(id);
        //获取杂费列表
        List<FacilityInfo> facilityInfoList = facilityInfoMapper.getFacilityInfoList(id);
        //获取配套列表
        List<FeeValueVo> feeValueVoList = feeValueMapper.getFeeValueVoList(id);

        //拼接bean
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphVoList);
        apartmentDetailVo.setLabelInfoList(labelInfoList);
        apartmentDetailVo.setFacilityInfoList(facilityInfoList);
        apartmentDetailVo.setFeeValueVoList(feeValueVoList);
        return apartmentDetailVo;
    }

    @Transactional
    @Override
    public void removeByIdAndOther(Long id) {
        //先判断是否有房间信息
        LambdaQueryWrapper<RoomInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoomInfo::getApartmentId, id);
        Long apartmentRoomCount = roomInfoMapper.selectCount(wrapper);
        if (apartmentRoomCount > 0) {
            //如果有房间信息,则抛出异常
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }
        super.removeById(id);
        this.deleteApartmentOthersInfo(id);
    }

    /**
     * 根据apartment id删除对应的图片,配套,标签,杂费四项数据
     *
     * @param apartmentId 公寓ID
     */
    private void deleteApartmentOthersInfo(Long apartmentId) {
        /*
            删除图片列表
            图片的item type指的是公寓还是房间,这里是公寓类型
            图片的item id 这里指的是公寓的id
             */
        LambdaQueryWrapper<GraphInfo> graphInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getItemType, ItemType.APARTMENT);
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getItemId, apartmentId);
        graphInfoService.remove(graphInfoLambdaQueryWrapper);
            /*
            删除配套列表
            删除的是公寓和配套的关系表 Apartment Facility 里面的公寓id的信息
            而不是配套信息表 Facility Info
             */
        LambdaQueryWrapper<ApartmentFacility> apartmentFacilityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        apartmentFacilityLambdaQueryWrapper.eq(ApartmentFacility::getApartmentId, apartmentId);
        apartmentFacilityService.remove(apartmentFacilityLambdaQueryWrapper);
            /*
            删除标签列表
            同理
             */
        LambdaQueryWrapper<ApartmentLabel> apartmentLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        apartmentLabelLambdaQueryWrapper.eq(ApartmentLabel::getApartmentId, apartmentId);
        apartmentLabelService.remove(apartmentLabelLambdaQueryWrapper);
            /*
            删除杂费列表
            同理
             */
        LambdaQueryWrapper<ApartmentFeeValue> apartmentFeeValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        apartmentFeeValueLambdaQueryWrapper.eq(ApartmentFeeValue::getApartmentId, apartmentId);
        apartmentFeeValueService.remove(apartmentFeeValueLambdaQueryWrapper);
    }
}
