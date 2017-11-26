package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.Labour;

public class LabourMapper implements RowMapper<Labour> {

	@Override
	public Labour mapRow(ResultSet rs, int rownum) throws SQLException {
		Labour labour = new Labour();
		labour.setLabour_id(rs.getString("id"));
		labour.setMobile_no(rs.getString("mobile_no"));
		labour.setFirstname(rs.getString("first_name"));
		labour.setLastname(rs.getString("last_name"));
		labour.setLevel(rs.getInt("level_id"));
		labour.setSex(rs.getString("sex"));
		labour.setAddress(rs.getString("Address"));
		labour.setCity(rs.getString("city"));
		labour.setState(rs.getString("state"));
		labour.setCountry(rs.getString("country"));
		labour.setPin_identity(rs.getString("pin_identification"));
		labour.setReleaving_date(rs.getString("releaving_date"));
		labour.setIndustry_id(rs.getString("industry_id"));
		labour.setStatus(rs.getString("status") == null || rs.getInt("status") == 0 ? false : true);
		labour.setCreatedOn(rs.getString("created_on"));
		labour.setModifiedOn(rs.getString("modified_on"));
		try{
			labour.setPhoto(rs.getString("photo"));
		}catch (Exception e) {
		}
		return labour;
	}

}
