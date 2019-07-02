package com.momo.optlog.services.optlog.po;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作日志
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OptLog {

    /**
     * id
     */
    private long id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 操作
     */
    private String opt;

    /**
     * ip
     */
    private String ip;

    /**
     * 参数
     */
    private String args;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private long updateTime;
}
