package com.jf.moonson.business.order.repo.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table m_order
 */
@Data
@SuperBuilder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.commodity_code
     *
     * @mbg.generated
     */
    private String commodityCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.count
     *
     * @mbg.generated
     */
    private Integer count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column m_order.money
     *
     * @mbg.generated
     */
    private Integer money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}