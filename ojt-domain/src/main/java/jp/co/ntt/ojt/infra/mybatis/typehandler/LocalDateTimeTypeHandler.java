package jp.co.ntt.ojt.infra.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDateTime;

/**
 * org.joda.time.LocalDateTime用のtypeHandler.
 */
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
			throws SQLException {
		Timestamp timestamp = new Timestamp(parameter.toDate().getTime());
		ps.setTimestamp(i, timestamp);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toLocalDateTime(rs.getTimestamp(columnName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toLocalDateTime(rs.getTimestamp(columnIndex));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toLocalDateTime(cs.getTimestamp(columnIndex));
	}
	
	/**
	 * LocalTime変換.
	 * @param time Time
	 * @return LocalTime
	 */
	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if(timestamp != null) {
			return new LocalDateTime(timestamp.getTime());
		}
		return null;
		
	}

}
