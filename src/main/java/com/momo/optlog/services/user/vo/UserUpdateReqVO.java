package com.momo.optlog.services.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新用户
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateReqVO {

    /**
     * id
     */
    private long id;

    /**
     * 用户姓名
     */
    private String userName;

}
