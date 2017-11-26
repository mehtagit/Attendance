package com.ecom.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.atttendance.pojos.Labour;
import com.ecom.mappers.LabourMapper;

@Service
public class LabourService {

	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private JDBCTemplateService jdbcService;
	
	public List<Labour> getLabourList(String industry_id) {
		List<Labour> labourList = null;
		long start = System.currentTimeMillis();
		try {
			//String query = "SELECT L1.* , L2.photo FROM labour_details AS L1 INNER JOIN labour_photo AS L2  ON L1.industry_id=L2.industry_id AND L1.id=L2.id AND L1.industry_id=?";
			String query = "select * from labour_details where industry_id=?";
			labourList = jdbcService.geJdbcTemplate().query(query, new String[] { industry_id }, new LabourMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LabourService.getLabourList took time in millisec:" + (System.currentTimeMillis() - start));

		return labourList;
	}

	public List<Labour> getLabourListWithoutPhoto(String industry_id) {
		List<Labour> labourList = null;
		long start = System.currentTimeMillis();
		String query = "SELECT * FROM labour_details AS L1 where  industry_id=?";
		
		try {
			//String query = "select * from labour_details where industry_id=?";
			labourList = jdbcService.geJdbcTemplate().query(query, new String[] { industry_id }, new LabourMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LabourService.getLabourList Query["+query+"] took time in millisec:" + (System.currentTimeMillis() - start));

		return labourList;
	}
	public Labour getStaffDetail(String labour_id, String industry_id) {
		Labour labour = null;
		long start = System.currentTimeMillis();
		try {
			String query = "SELECT L1.* , L2.photo FROM labour_details AS L1 INNER JOIN labour_photo AS L2  ON L1.industry_id=L2.industry_id AND L1.id=L2.id AND L1.industry_id=? and L1.labour_id=?";
		//	String query = "select * from labour_details where industry_id=? and labour_id=?";
			labour = jdbcService.geJdbcTemplate().queryForObject(query, new String[] { industry_id, labour_id },
					new LabourMapper());
			labour.setStatus(true);

		} catch (EmptyResultDataAccessException e) {
			labour = new Labour();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("StaffService.validate took time in millisec:" + (System.currentTimeMillis() - start));

		return labour;
	}

	
	public Labour addLabour(Labour labour, String industry_id) {
		boolean status = false;
		int labourStatus = -1;
		int admintStatus = -1;
		List<String> queryList = null;
		String query1 = null;
		long start = System.currentTimeMillis();
		try {
			
			String maxId = "select max(CAST( id as SIGNED)) from labour_details where industry_id='" + industry_id + "'";
			int count = jdbcService.geJdbcTemplate().queryForObject(maxId, Integer.class);
			
			String labourId = ""+(count+1);
			
			queryList = labour.insert(labourId, industry_id);
			
			for(String query : queryList)
				labourStatus = jdbcService.geJdbcTemplate().update(query);
			query1 = labour.getAdminDetail().insert(labourId,industry_id);
			
			
			admintStatus  = jdbcService.geJdbcTemplate().update(query1);
			status = true;
			labour.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LabourService.addLabour Query[" + queryList.get(0) + "] Query [" + query1 + "] ");
		System.out.println("LabourService.addLabour labourStatus[" + labourStatus + "] admintStatus ["+admintStatus+"] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return labour;
	}

	public Labour updateLabour(Labour labour, String industry_id) {
		boolean status = false;
		int labourStatus = -1;
		int admintStatus = -1;
		List<String> queryList = labour.update();
		String query1 = labour.getAdminDetail().update();
		long start = System.currentTimeMillis();
		try {
			for(String query : queryList)
				labourStatus = jdbcService.geJdbcTemplate().update(query);
			admintStatus  = jdbcService.geJdbcTemplate().update(query1);
			status = true;
			labour.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LabourService.updateLabour Query[" + queryList.get(0) + "] Query [" + query1 + "] ");
		System.out.println("LabourService.updateLabour labourStatus[" + labourStatus + "] admintStatus ["+admintStatus+"] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return labour;
	}
	
	public boolean delete(Labour labour) {
		boolean status = false;
		int deleteStatus = -1;
		String query = labour.delete();
		long start = System.currentTimeMillis();
		try {
			deleteStatus  = jdbcService.geJdbcTemplate().update(query);
			status = true;
			labour.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LabourService.delete Query[" + query + "] deleteStatus[" + deleteStatus + "] Status[" + status
				+ "] took time in millisec:" + (System.currentTimeMillis() - start));

		return status;
	}
	public String getCurrentDate() {

		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 0);
		return dateFormat.format(calendar.getTime());
	}
}
