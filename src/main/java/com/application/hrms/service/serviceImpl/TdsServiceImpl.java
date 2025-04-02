package com.application.hrms.service.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Tds;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DeductionGroupDao;
import com.application.hrms.dao.TdsDao;
import com.application.hrms.service.TdsService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TdsServiceImpl implements TdsService {

	@Autowired
	TdsDao tdsDao;

	@Autowired
	DeductionGroupDao dgd;

	@Override
	public ResponseEntity<String> create(List<Map<String, String>> requestMap) {
		try {
			List<Tds> tdsList = new ArrayList<>();
			for (int i = 0; i < requestMap.size(); i++) {
				Tds tdinfo = new Tds();
				tdinfo.setFromamount(Integer.parseInt(requestMap.get(i).get("fromAmount")));
				tdinfo.setToamount(Integer.parseInt(requestMap.get(i).get("toAmount")));
				tdinfo.setPercentage(Integer.parseInt(requestMap.get(i).get("percentage")));
				tdinfo.setName(requestMap.get(i).get("name"));
				tdsList.add(tdinfo);
			}
			if (tdsList.size() > 0) {
				tdsDao.saveAll(tdsList);
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {

				return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Tds>> getAll() {
		List<Tds> list = new ArrayList<Tds>();
		try {
			return new ResponseEntity<List<Tds>>(tdsDao.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Tds>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> delete() {
		try {
			tdsDao.deleteAll();
			return HrmsUtils.getResponeEntity("Information Deleted.", HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Map> getByAmount(Integer amount,Integer groupid) {
		Map<String, Object> response = new HashMap<>();
		try {
			Optional<Tds> tdsdata = tdsDao.findTaxSlabByAmount(amount);
			Double basicpa = Double.valueOf(amount);

			BigDecimal basicsaAmountPerMonth = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.50))
					.setScale(2, RoundingMode.HALF_UP); // 50
			BigDecimal hraAmount = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.20)).setScale(2,
					RoundingMode.HALF_UP); // 20%
			BigDecimal specialallowanceAmount = BigDecimal.valueOf(basicpa / 12).multiply(BigDecimal.valueOf(0.30))
					.setScale(2, RoundingMode.HALF_UP); // 30%

			BigDecimal tdsPercentage = BigDecimal.valueOf(tdsdata.get().getPercentage())
					.divide(BigDecimal.valueOf(100));
			BigDecimal tds = BigDecimal.valueOf(basicpa / 12).multiply(tdsPercentage).setScale(2, RoundingMode.HALF_UP);

			DeductionGroup dg = dgd.getDeductionGroupInfoById(groupid);
			JSONObject jsonObjectv = new JSONObject(dg.getValue());

			Integer childreneducationallowanceAmount = jsonObjectv.getInt("childreneducationallowance");
			Integer carmaintenanceAmount = jsonObjectv.getInt("carmaintenance");
			Integer leavetravelallowanceAmount = jsonObjectv.getInt("leavetravelallowance");
			Integer telephoneinternetAmount = jsonObjectv.getInt("telephoneinternet");
			Integer PFAmount = jsonObjectv.getInt("PF");
			Integer insuranceAmount = jsonObjectv.getInt("insurance");
			Integer professional = 200;
			BigDecimal basicsaAmountPerDay = basicsaAmountPerMonth.divide(BigDecimal.valueOf(30), 2,
					RoundingMode.HALF_UP);
			BigDecimal basicSalaryAmount = basicsaAmountPerDay.multiply(BigDecimal.valueOf(30)).setScale(2,
					RoundingMode.HALF_UP);
			BigDecimal salaryCredited = basicSalaryAmount.add(hraAmount).add(specialallowanceAmount)
					.add(BigDecimal.valueOf(childreneducationallowanceAmount))
					.add(BigDecimal.valueOf(carmaintenanceAmount)).add(BigDecimal.valueOf(leavetravelallowanceAmount))
					.add(BigDecimal.valueOf(telephoneinternetAmount)).subtract(tds)
					.subtract(BigDecimal.valueOf(professional)).subtract(BigDecimal.valueOf(PFAmount))
					.subtract(BigDecimal.valueOf(insuranceAmount)).setScale(2, RoundingMode.HALF_UP);
			response.put("peranam", basicpa);
			response.put("permonth", basicsaAmountPerMonth);
			response.put("hraAmount", hraAmount);
			response.put("specialallowanceAmount", specialallowanceAmount);
			response.put("tdsPercentage", tdsPercentage);
			response.put("tds", tds);
			response.put("dg", dg);
			response.put("childreneducationallowanceAmount", childreneducationallowanceAmount);
			response.put("carmaintenanceAmount", carmaintenanceAmount);
			response.put("leavetravelallowanceAmount", leavetravelallowanceAmount);
			response.put("telephoneinternetAmount", telephoneinternetAmount);
			response.put("PFAmount", PFAmount);
			response.put("insuranceAmount", insuranceAmount);
			response.put("professional", professional);
			response.put("basicSalaryAmount", basicSalaryAmount);
			response.put("salaryCredited", salaryCredited);
			return new ResponseEntity<Map>(response, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
