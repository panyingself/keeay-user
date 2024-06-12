package com.keeay.anepoch.user.commons.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/3/28 - 17:25
 */
public class LocalDateTimeTypeHandler implements TypeHandler<LocalDateTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void setParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            ps.setString(i, formatter.format(parameter));
        } else {
            ps.setTimestamp(i, null);
        }
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        String dateStr = rs.getString(columnName);
        return dateStr != null ? LocalDateTime.parse(dateStr, formatter) : null;
    }

    @Override
    public LocalDateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        String dateStr = rs.getString(columnIndex);
        return dateStr != null ? LocalDateTime.parse(dateStr, formatter) : null;
    }

    @Override
    public LocalDateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String dateStr = cs.getString(columnIndex);
        return dateStr != null ? LocalDateTime.parse(dateStr, formatter) : null;
    }
}
