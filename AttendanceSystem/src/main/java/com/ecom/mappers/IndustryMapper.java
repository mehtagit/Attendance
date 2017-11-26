package com.ecom.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.atttendance.pojos.Industry;

public class IndustryMapper implements RowMapper<Industry> {

	@Override
	public Industry mapRow(ResultSet rs, int rownum) throws SQLException {

		Industry industry = new Industry();
		industry.setIndustry_id(rs.getString("industry_id"));
		industry.setTin_no(rs.getString("tin_no"));
		industry.setRegistration_pin(rs.getString("registration_pin"));
		industry.setName(rs.getString("name"));
		industry.setOwner_name(rs.getString("owner_name"));
		industry.setDefaultOutTime(rs.getString("default_outime"));
		industry.setContact_no(rs.getString("contact_no"));
		industry.setAddress(rs.getString("address"));
		industry.setStatus(rs.getString("status") == null ? 0 :  rs.getInt("status"));
		industry.setCreated_on(rs.getString("created_on"));
		industry.setModified_on(rs.getString("modified_on"));

		return industry;
	}

}
