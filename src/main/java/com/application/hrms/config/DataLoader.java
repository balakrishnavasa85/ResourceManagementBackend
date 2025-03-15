package com.application.hrms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.application.hrms.POJO.User;
import com.application.hrms.dao.UserDao;
import com.application.hrms.POJO.ApplicationAccess;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.dao.ApplicationAccessDao;
import com.application.hrms.dao.DeductionGroupDao;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.DesignationDao;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private UserDao userRepository;

	@Autowired
	private ApplicationAccessDao aad;

	@Autowired
	private DepartmentDao depd;

	@Autowired
	private DesignationDao desd;

	@Autowired
	private DeductionGroupDao dgd;

	@Override
	public void run(String... args) throws Exception {
		// Check if data already exists
		if (aad.count() == 0) {
			aad.save(new ApplicationAccess(1, "HRMS", "2025-03-15", "2025-06-30", ""));
			aad.save(new ApplicationAccess(2, "taxsavingenable", "2025-03-15", "2025-06-30", "2025-26"));
			aad.save(new ApplicationAccess(3, "from16upload", "2025-03-15", "2025-06-30", ""));
		}
		if (depd.count() == 0) {
			depd.save(new Department(1, "Development", "y"));
			depd.save(new Department(2, "Testing", "y"));
			depd.save(new Department(3, "Administration", "y"));
		}
		if (desd.count() == 0) {
			desd.save(new Designation(1, "Lead", "y"));
			desd.save(new Designation(2, "Senior", "y"));
			desd.save(new Designation(3, "Junior", "y"));
			desd.save(new Designation(4, "Intern", "y"));
		}
		if (dgd.count() == 0) {
			dgd.save(new DeductionGroup(1, "Basic", "y",
					"{'basicsalary':'50%','hra':'20%','specialallowance':'30%','childreneducationallowance':'200','carmaintenance':'1500','leavetravelallowance':'2000','telephoneinternet':'2500','PF':'1800'}"));
		}
		if (userRepository.count() == 0) {
			userRepository.save(
					new User(1, "superadmin", "superadmin@demo.com", "Super@1234", "superadmin", "y", "0", "y", 3, 1));
			userRepository.save(new User(2, "devadmin", "devadmin@demo.com", "dev@1234", "admin", "y", "0", "y", 1, 1));
			userRepository
					.save(new User(3, "testadmin", "testadmin@demo.com", "test@1234", "admin", "y", "0", "y", 2, 1));
		}
	}
}