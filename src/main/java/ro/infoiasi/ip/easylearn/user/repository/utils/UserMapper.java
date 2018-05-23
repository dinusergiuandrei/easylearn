package ro.infoiasi.ip.easylearn.user.repository.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.user.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper <User> {
    @Nullable
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setPassword(resultSet.getString("password"));
        user.setSecretAnswer(resultSet.getString("secretAnswer"));
        user.setSecretPassword(resultSet.getString("secretPassword"));
        user.setEmail(resultSet.getString("email"));
        user.setScore(resultSet.getLong("score"));

        return user;
    }
}
