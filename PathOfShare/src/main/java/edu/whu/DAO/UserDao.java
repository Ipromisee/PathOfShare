package edu.whu.DAO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
