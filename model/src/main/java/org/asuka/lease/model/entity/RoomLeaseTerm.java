package org.asuka.lease.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;


@TableName(value = "room_lease_term")
@Data
@Schema(description = "房间租期关系表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomLeaseTerm extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "房间id")
    @TableField("room_id")
    private Long roomId;

    @Schema(description = "租期id")
    @TableField("lease_term_id")
    private Long leaseTermId;

}