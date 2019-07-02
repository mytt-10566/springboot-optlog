package com.momo.optlog.services.optlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增操作日志DTO
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptLogAddDTO {

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

}
