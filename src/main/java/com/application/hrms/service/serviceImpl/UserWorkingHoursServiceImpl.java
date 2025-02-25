package com.application.hrms.service.serviceImpl;

import com.application.hrms.dao.DeductionGroupDao;
import com.application.hrms.dao.LeaveRequestDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserWorkingDaysDao;
import com.application.hrms.dao.UserWorkingHoursDao;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.service.UserWorkingHoursService;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Slf4j
@Service
public class UserWorkingHoursServiceImpl implements UserWorkingHoursService {

	@Autowired
	UserWorkingHoursDao uwhd;

	@Autowired
	UserWorkingDaysDao uwdd;

	@Autowired
	UserDao ud;

	@Autowired
	DeductionGroupDao dgd;

	@Autowired
	LeaveRequestDao lrd;

	@Override
	public ResponseEntity<List<UserWorkingHours>> getByUserId(Integer id) {
		List<UserWorkingHours> list = new ArrayList<UserWorkingHours>();
		try {
			List<UserWorkingHours> lists = uwhd.findByUser(id);
			return new ResponseEntity<List<UserWorkingHours>>(lists, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWorkingHours>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserWorkingHours>> getByUserIdMonth(Integer id, Integer month, Integer year) {
		List<UserWorkingHours> list = new ArrayList<UserWorkingHours>();
		try {
			List<UserWorkingHours> lists = uwhd.findByUserAndMonth(id, month, year);
			return new ResponseEntity<List<UserWorkingHours>>(lists, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWorkingHours>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Map> generatePayslip() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<Object[]> results = uwhd.generatePayslip();
			List<Object> userWorkingdays = new ArrayList<>();

			for (Object[] result : results) {
				System.out.println(result[1]);
				BigInteger dayscountBigInt = (BigInteger) result[0];
				Integer dayscount = dayscountBigInt.intValue();
//				Integer userStr = (Integer) result[1];
				Integer user =  (Integer) result[1];
				String month = (String) result[2];
				String yearStr = (String) result[3];
				Integer year = Integer.parseInt(yearStr);
				BigInteger numberofdaysBigInt = (BigInteger) result[4];
				Integer numberofdays = numberofdaysBigInt.intValue();
				BigInteger numberofholidaysBigInt = (BigInteger) result[5];
				String name = (String) result[6];
				String accountnumber = (String) result[7];
				String ifsccode = (String) result[8];
				String pf = (String) result[9];
				String uan = (String) result[10];
				String bankname = (String) result[11];
				Integer numberofholidays = numberofholidaysBigInt.intValue();

				User userDetails = ud.getUserDetailById(user);
				DeductionGroup dg = dgd.getDeductionGroupInfoById(userDetails.getDeductionGroup().getId());
				Double basicpa = Double.parseDouble(userDetails.getBasicpa());

				BigDecimal basicsaAmountPerMonth = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.50))
						.setScale(2, RoundingMode.HALF_UP); // 50%
				BigDecimal basicsaAmountPerDay = basicsaAmountPerMonth.divide(BigDecimal.valueOf(numberofdays), 2,
						RoundingMode.HALF_UP);

				LocalDate today = LocalDate.now();
				LocalDate startDate = today.minusMonths(1).withDayOfMonth(25); // Calculate last month's 25th
				LocalDate endDate = today.withDayOfMonth(24); // Calculate this month's 24th
				int weekendCount = 0;
				for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
					DayOfWeek day = date.getDayOfWeek();
					if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
						weekendCount++;
					}
				}

				Integer approvedLeaves = lrd.countApprovedLeaves(user);
				approvedLeaves = (approvedLeaves != null) ? approvedLeaves : 0;

				BigDecimal basicSalaryAmount = basicsaAmountPerDay
						.multiply(BigDecimal.valueOf(dayscount + numberofholidays + approvedLeaves + weekendCount))
						.setScale(2, RoundingMode.HALF_UP);
				BigDecimal hraAmount = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.20)).setScale(2,
						RoundingMode.HALF_UP); // 20%
				BigDecimal specialallowanceAmount = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.30))
						.setScale(2, RoundingMode.HALF_UP); // 30%
				BigDecimal tds = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.10)).setScale(2,
						RoundingMode.HALF_UP);

				JSONObject jsonObjectv = new JSONObject(dg.getValue());

				Integer childreneducationallowanceAmount = jsonObjectv.getInt("childreneducationallowance");
				Integer carmaintenanceAmount = jsonObjectv.getInt("carmaintenance");
				Integer leavetravelallowanceAmount = jsonObjectv.getInt("leavetravelallowance");
				Integer telephoneinternetAmount = jsonObjectv.getInt("telephoneinternet");
				Integer PFAmount = jsonObjectv.getInt("PF");
				Integer professional = 200;

				BigDecimal salaryCredited = basicSalaryAmount.add(hraAmount).add(specialallowanceAmount)
						.add(BigDecimal.valueOf(childreneducationallowanceAmount))
						.add(BigDecimal.valueOf(carmaintenanceAmount))
						.add(BigDecimal.valueOf(leavetravelallowanceAmount))
						.add(BigDecimal.valueOf(telephoneinternetAmount)).subtract(tds)
						.subtract(BigDecimal.valueOf(professional)).subtract(BigDecimal.valueOf(PFAmount))
						.setScale(2, RoundingMode.HALF_UP);

				UserWorkingDays userWorkingDay = new UserWorkingDays();
				userWorkingDay.setUser(userDetails);
				userWorkingDay.setDayscount(dayscount + numberofholidays + approvedLeaves + weekendCount);
				userWorkingDay.setName(name);
				userWorkingDay.setMonth(month);
				userWorkingDay.setYear(year);
				userWorkingDay.setLastmonthnumberofdays(numberofdays);
				userWorkingDay.setDeductiongroup(dg.getName());
				userWorkingDay.setBasicamount(basicSalaryAmount.doubleValue());
				userWorkingDay.setHraamount(hraAmount.doubleValue());
				userWorkingDay.setSpecialallowanceamount(specialallowanceAmount.doubleValue());
				userWorkingDay.setChildreneducationallowanceamount(childreneducationallowanceAmount);
				userWorkingDay.setCarmaintenanceamount(carmaintenanceAmount);
				userWorkingDay.setLeavetravelallowanceamount(leavetravelallowanceAmount);
				userWorkingDay.setTelephoneinternetamount(telephoneinternetAmount);
				userWorkingDay.setPfamount(PFAmount);
				userWorkingDay.setProfessionaltax(200);
				userWorkingDay.setTds(tds.doubleValue());
				userWorkingDay.setSalarycredited(salaryCredited.doubleValue());
				userWorkingDay.setAccountnumber(accountnumber);
				userWorkingDay.setIfsccode(ifsccode);
				userWorkingDay.setPf(pf);
				userWorkingDay.setUan(uan);
				userWorkingDay.setBankname(bankname);
				uwdd.save(userWorkingDay);

				userWorkingdays.add(userWorkingDay);
			}
			response.put("payslip", userWorkingdays);
			return new ResponseEntity<Map>(response, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}