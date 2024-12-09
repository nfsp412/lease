package org.asuka.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.asuka.lease.model.enums.ItemType;

@Schema(description = "标签信息表")
@TableName(value = "label_info")
@Data
public class LabelInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "类型")
    @TableField(value = "type")
    private ItemType type;

    @Schema(description = "标签名称")
    @TableField(value = "name")
    private String name;


}