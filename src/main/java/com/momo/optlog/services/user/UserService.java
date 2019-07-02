package com.momo.optlog.services.user;

import com.momo.optlog.services.user.po.User;
import com.momo.optlog.services.user.vo.UserAddReqVO;
import com.momo.optlog.services.user.vo.UserUpdateReqVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUesrList() {
        return null;
    }

    public boolean add(UserAddReqVO req) {
        return true;
    }

    public boolean update(UserUpdateReqVO req) {
        return true;
    }

    public boolean delete(long id) {
        return true;
    }

    public User getByToken(String token) {
        User user = User.builder()
                .id(1)
                .userName("admin")
                .build();
        return user;
    }
}
