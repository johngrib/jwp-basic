package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;
import next.model.Answer;

import java.sql.*;
import java.util.List;

public abstract class Dao {

    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getJdbcTemplate() {
        if(jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate();
        }
        return jdbcTemplate;
    }

}
