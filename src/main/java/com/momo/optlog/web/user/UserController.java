package com.momo.optlog.web.user;

import com.momo.optlog.services.user.UserService;
import com.momo.optlog.services.user.po.User;
import com.momo.optlog.services.user.vo.UserAddReqVO;
import com.momo.optlog.services.user.vo.UserUpdateReqVO;
import com.momo.optlog.support.response.CommonResponse;
import com.momo.optlog.web.optlog.annotation.OptLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户
 *
 * @author moqinggen
 * @date 2019/07/01
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public CommonResponse list() {
        List<User> userList = userService.getUesrList();
        return CommonResponse.okResult(userList);
    }

    @PostMapping("/user")
    @OptLogger("新增用户")
    public CommonResponse addUser(@RequestBody UserAddReqVO req) {
        boolean isSuccess = userService.add(req);
        return CommonResponse.okResult(isSuccess);
    }

    @PutMapping("/user")
    @OptLogger("编辑用户")
    public CommonResponse updateUser(@RequestBody UserUpdateReqVO req) {
        boolean isSuccess = userService.update(req);
        return CommonResponse.okResult(isSuccess);
    }

    @DeleteMapping("/user/{id}")
    @OptLogger("删除用户")
    public CommonResponse deleteUser(@PathVariable long id) {
        boolean isSuccess = userService.delete(id);
        return CommonResponse.okResult(isSuccess);
    }

}
