package com.qyngchen.sbmp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 部门信息
 */
@Data
@TableName("dept")
public class DeptDo {

    /**
     * Version标识UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String deptId;

    /**
     * 部门名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 创建时间
     */
    @TableField(value = "dept_create_time")
    private Long deptCreateTime;

    /**
     * 修改时间
     */
    @TableField(value = "dept_update_time")
    private Long deptUpdateTime;
}
