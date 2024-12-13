package org.asuka.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.asuka.lease.model.enums.ReleaseStatus;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;

@Schema(description = "公寓信息表")
@TableName(value = "apartment_info")
@Data
public class ApartmentInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "公寓名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "公寓介绍")
    @TableField(value = "introduction")
    private String introduction;

    @Schema(description = "所处区域id")
    @TableField(value = "district_id")
    private Long districtId;

    @Schema(description = "所处区域名称")
    @TableField(value = "district_name")
    private String districtName;

    @Schema(description = "所处城市id")
    @TableField(value = "city_id")
    private Long cityId;

    @Schema(description = "所处城市名称")
    @TableField(value = "city_name")
    private String cityName;

    @Schema(description = "所处省份id")
    @TableField(value = "province_id")
    @NotNull(message = "省份ID不能为空")
    private Long provinceId;

    @Schema(description = "所处区域名称")
    @TableField(value = "province_name")
    private String provinceName;

    @Schema(description = "详细地址")
    @TableField(value = "address_detail")
    private String addressDetail;

    @Schema(description = "经度")
    @TableField(value = "latitude")
    private String latitude;

    @Schema(description = "纬度")
    @TableField(value = "longitude")
    private String longitude;

    @Schema(description = "公寓前台电话")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "是否发布")
    @TableField(value = "is_release")
    private ReleaseStatus isRelease;

}