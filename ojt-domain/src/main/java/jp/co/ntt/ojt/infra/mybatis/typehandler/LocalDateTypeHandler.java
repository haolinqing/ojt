package jp.co.ntt.ojt.infra.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDate;

/**
 * org.joda.time.LocalDate用のtypeHandler.
 */
public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDate parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setDate(i,new Date(parameter.toDate().getTime()));
	}
 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return toLocalDate(rs.getDate(columnName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return toLocalDate(rs.getDate(columnIndex));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return toLocalDate(cs.getDate(columnIndex));
	}
	
	/**
	 * LocalDate変換
	 * @param date Date
	 * @return LocalDate
	 */
	private LocalDate toLocalDate(Date date) {
		if(date != null) {
			return new LocalDate(date.getTime());
		}
		return null;
	}

}
