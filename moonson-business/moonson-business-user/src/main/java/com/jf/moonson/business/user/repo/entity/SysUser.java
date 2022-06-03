package com.jf.moonson.business.user.repo.entity;

import com.jf.moonson.common.enums.BooleanEnum;
import com.jf.moonson.common.enums.GenderEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_user
 */
@Data
@SuperBuilder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
     * Database Column Remarks:
     *   用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.ID
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   手机号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.MOBILE
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * Database Column Remarks:
     *   昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.NICKNAME
     *
     * @mbg.generated
     */
    private String nickname;

    /**
     * Database Column Remarks:
     *   登录密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.PASSWORD
     *
     * @mbg.generated
     */
    private String password;

    /**
     * Database Column Remarks:
     *   性别:0=未知,1=男,2=女
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.GENDER
     *
     * @mbg.generated
     */
    private GenderEnum gender;

    /**
     * Database Column Remarks:
     *   生日
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.BIRTHDAY
     *
     * @mbg.generated
     */
    private LocalDateTime birthday;

    /**
     * Database Column Remarks:
     *   状态:0=正常,2=停用
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.STATUS
     *
     * @mbg.generated
     */
    private BooleanEnum status;

    /**
     * Database Column Remarks:
     *    创建人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CREATOR
     *
     * @mbg.generated
     */
    private String creator;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.CREATE_TIME
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * Database Column Remarks:
     *   修改人
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.MODIFIER
     *
     * @mbg.generated
     */
    private String modifier;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.UPDATE_TIME
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * Database Column Remarks:
     *   是否删除:0=否,1=是
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.DELETE_FLAG
     *
     * @mbg.generated
     */
    private Byte deleteFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}