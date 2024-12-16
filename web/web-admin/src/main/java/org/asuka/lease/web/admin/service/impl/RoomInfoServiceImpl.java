package org.asuka.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.asuka.lease.common.exception.RoomException;
import org.asuka.lease.common.result.ResultCodeEnum;
import org.asuka.lease.model.entity.*;
import org.asuka.lease.model.enums.ItemType;
import org.asuka.lease.model.enums.LeaseStatus;
import org.asuka.lease.model.enums.ReleaseStatus;
import org.asuka.lease.web.admin.mapper.*;
import org.asuka.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.asuka.lease.web.admin.vo.attr.AttrValueVo;
import org.asuka.lease.web.admin.vo.graph.GraphVo;
import org.asuka.lease.web.admin.vo.room.RoomDetailVo;
import org.asuka.lease.web.admin.vo.room.RoomItemVo;
import org.asuka.lease.web.admin.vo.room.RoomQueryVo;
import org.asuka.lease.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author liubo
 * 针对表【room_info(房间信息表)】的数据库操作Service实现
 */
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {
    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Resource
    private LeaseAgreementMapper leaseAgreementMapper;

    @Autowired
    private GraphInfoService graphInfoService;
    @Autowired
    private RoomAttrValueService roomAttrValueService;
    @Autowired
    private RoomFacilityService roomFacilityService;
    @Autowired
    private RoomLabelService roomLabelService;
    @Autowired
    private RoomPaymentTypeService roomPaymentTypeService;
    @Autowired
    private RoomLeaseTermService roomLeaseTermService;

    @Resource
    private GraphInfoMapper graphInfoMapper;
    @Resource
    private RoomAttrValueMapper roomAttrValueMapper;
    @Resource
    private RoomFacilityMapper roomFacilityMapper;
    @Resource
    private RoomLabelMapper roomLabelMapper;
    @Resource
    private RoomPaymentTypeMapper roomPaymentTypeMapper;
    @Resource
    private RoomLeaseTermMapper roomLeaseTermMapper;

    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @Override
    public IPage<RoomItemVo> getRoomItemVoList(Page<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.getRoomItemVoList(page, queryVo);
    }

    @Transactional
    @Override
    public void removeRoomInfoById(Long id) {
        LambdaQueryWrapper<LeaseAgreement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LeaseAgreement::getRoomId, id);
        wrapper.in(LeaseAgreement::getStatus, LeaseStatus.SIGNED, LeaseStatus.WITHDRAWING);
        Long isLeasedRoomCount = leaseAgreementMapper.selectCount(wrapper);
        if (isLeasedRoomCount > 0) {
            //证明该房间无法删除
            throw new RoomException(ResultCodeEnum.ADMIN_ROOM_DELETE_ERROR);
        }
        //可以删除
        super.removeById(id);
        this.deleteRoomOthersInfo(id);
    }

    @Override
    public void saveOrUpdateRoomSubmitVo(RoomSubmitVo roomSubmitVo) {
        //判断是更新还是新增
        boolean isUpdate = roomSubmitVo.getId() != null;
        if (isUpdate) {
            //是更新,先删再插
            this.deleteRoomOthersInfo(roomSubmitVo.getId());
        }
        super.saveOrUpdate(roomSubmitVo);

        List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            List<GraphInfo> graphInfoList =
                    graphVoList.stream()
                            .map(graphVo ->
                                    GraphInfo.builder()
                                            .url(graphVo.getUrl())
                                            .itemId(roomSubmitVo.getId())
                                            .itemType(ItemType.ROOM)
                                            .name(graphVo.getName())
                                            .build())
                            .toList();
            graphInfoService.saveBatch(graphInfoList);
        }

        List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
        if (!CollectionUtils.isEmpty(attrValueIds)) {
            List<RoomAttrValue> roomAttrValueList = attrValueIds.stream()
                    .map(attrValueId ->
                            RoomAttrValue.builder()
                                    .attrValueId(attrValueId)
                                    .roomId(roomSubmitVo.getId())
                                    .build())
                    .toList();
            roomAttrValueService.saveBatch(roomAttrValueList);
        }

        List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            List<RoomFacility> roomFacilityList = facilityInfoIds.stream()
                    .map(facilityInfoId ->
                            RoomFacility.builder()
                                    .facilityId(facilityInfoId)
                                    .roomId(roomSubmitVo.getId())
                                    .build())
                    .toList();
            roomFacilityService.saveBatch(roomFacilityList);
        }

        List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
        if (!CollectionUtils.isEmpty(labelInfoIds)) {
            List<RoomLabel> roomLabelList = labelInfoIds.stream()
                    .map(labelInfoId ->
                            RoomLabel.builder()
                                    .labelId(labelInfoId)
                                    .roomId(roomSubmitVo.getId())
                                    .build())
                    .toList();
            roomLabelService.saveBatch(roomLabelList);
        }

        List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
        if (!CollectionUtils.isEmpty(paymentTypeIds)) {
            List<RoomPaymentType> roomPaymentTypeList = paymentTypeIds.stream()
                    .map(paymentTypeId ->
                            RoomPaymentType.builder()
                                    .paymentTypeId(paymentTypeId)
                                    .roomId(roomSubmitVo.getId())
                                    .build())
                    .toList();
            roomPaymentTypeService.saveBatch(roomPaymentTypeList);
        }

        List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
        if (!CollectionUtils.isEmpty(leaseTermIds)) {
            List<RoomLeaseTerm> roomLeaseTermList = leaseTermIds.stream()
                    .map(leaseTermId ->
                            RoomLeaseTerm.builder()
                                    .leaseTermId(leaseTermId)
                                    .roomId(roomSubmitVo.getId())
                                    .build())
                    .toList();
            roomLeaseTermService.saveBatch(roomLeaseTermList);
        }

    }

    private void deleteRoomOthersInfo(Long roomId) {
        LambdaQueryWrapper<GraphInfo> graphInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getItemId,roomId);
        graphInfoLambdaQueryWrapper.eq(GraphInfo::getItemType,ItemType.ROOM);
        graphInfoService.remove(graphInfoLambdaQueryWrapper);

        LambdaQueryWrapper<RoomAttrValue> roomAttrValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomAttrValueLambdaQueryWrapper.eq(RoomAttrValue::getRoomId,roomId);
        roomAttrValueService.remove(roomAttrValueLambdaQueryWrapper);

        LambdaQueryWrapper<RoomFacility> roomFacilityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomFacilityLambdaQueryWrapper.eq(RoomFacility::getRoomId,roomId);
        roomFacilityService.remove(roomFacilityLambdaQueryWrapper);

        LambdaQueryWrapper<RoomLabel> roomLabelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomLabelLambdaQueryWrapper.eq(RoomLabel::getRoomId,roomId);
        roomLabelService.remove(roomLabelLambdaQueryWrapper);

        LambdaQueryWrapper<RoomPaymentType> roomPaymentTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomPaymentTypeLambdaQueryWrapper.eq(RoomPaymentType::getRoomId,roomId);
        roomPaymentTypeService.remove(roomPaymentTypeLambdaQueryWrapper);

        LambdaQueryWrapper<RoomLeaseTerm> roomLeaseTermLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roomLeaseTermLambdaQueryWrapper.eq(RoomLeaseTerm::getRoomId,roomId);
        roomLeaseTermService.remove(roomLeaseTermLambdaQueryWrapper);
    }

    @Override
    public RoomDetailVo getRoomDetailVo(Long id) {
        RoomInfo roomInfo = super.getById(id);
        if (roomInfo == null){
            return null;
        }

        LambdaQueryWrapper<ApartmentInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApartmentInfo::getId,roomInfo.getApartmentId());
        ApartmentInfo apartmentInfo = apartmentInfoService.list(wrapper).get(0);

        List<GraphVo> graphVoList = graphInfoMapper.getGraphVoList(ItemType.ROOM, id);

        List<AttrValueVo> attrValueVoList = roomAttrValueMapper.getAttrValueVoList(id);

        List<FacilityInfo> facilityInfoList = roomFacilityMapper.getFacilityInfoList(id);

        List<LabelInfo> labelInfoList =  roomLabelMapper.getLabelInfoList(id);

        List<PaymentType> paymentTypeList = roomPaymentTypeMapper.getPaymentTypeList(id);

        List<LeaseTerm> leaseTermList = roomLeaseTermMapper.getLeaseTermList(id);

        RoomDetailVo roomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfo,roomDetailVo);
        roomDetailVo.setApartmentInfo(apartmentInfo);
        roomDetailVo.setGraphVoList(graphVoList);
        roomDetailVo.setAttrValueVoList(attrValueVoList);
        roomDetailVo.setFacilityInfoList(facilityInfoList);
        roomDetailVo.setLabelInfoList(labelInfoList);
        roomDetailVo.setPaymentTypeList(paymentTypeList);
        roomDetailVo.setLeaseTermList(leaseTermList);
        return roomDetailVo;
    }

    @Override
    public List<RoomInfo> listFreeRoomInfo(Long id) {
        //根据apart id查询已经出租的房间的room id
        LambdaQueryWrapper<LeaseAgreement> leaseAgreementLambdaQueryWrapper = new LambdaQueryWrapper<>();
        leaseAgreementLambdaQueryWrapper.eq(LeaseAgreement::getApartmentId,id);
        leaseAgreementLambdaQueryWrapper.in(LeaseAgreement::getStatus,LeaseStatus.SIGNED,LeaseStatus.WITHDRAWING,LeaseStatus.SIGNING);
        leaseAgreementLambdaQueryWrapper.select(LeaseAgreement::getRoomId);
        List<LeaseAgreement> unUsedRoomInfo = leaseAgreementMapper.selectList(leaseAgreementLambdaQueryWrapper);
        List<Long> unUsedRoomId = unUsedRoomInfo.stream().map(LeaseAgreement::getRoomId).toList();

        LambdaQueryWrapper<RoomInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoomInfo::getApartmentId, id);
        wrapper.eq(RoomInfo::getIsRelease, ReleaseStatus.RELEASED);
        if (!unUsedRoomId.isEmpty()){
            wrapper.notIn(RoomInfo::getId,unUsedRoomId);
        }
        return roomInfoMapper.selectList(wrapper);
    }
}
