package org.asuka.lease.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.asuka.lease.model.enums.ItemType;

import java.io.Serial;

@Schema(description = "图片信息表")
@TableName(value = "graph_info")
@Data
public class GraphInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "图片名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "图片所属对象类型")
    @TableField(value = "item_type")
    private ItemType itemType;

    @Schema(description = "图片所有对象id")
    @TableField(value = "item_id")
    private Long itemId;

    @Schema(description = "图片地址")
    @TableField(value = "url")
    private String url;

}