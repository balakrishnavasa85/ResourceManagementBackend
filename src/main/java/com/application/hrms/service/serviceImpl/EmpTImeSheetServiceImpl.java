package com.application.hrms.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.EmpTimeSheet;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.dao.EmpTImeSheetDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserWorkingHoursDao;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.service.EmpTimeSheetService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;
import java.time.Duration;
import java.time.Instant;import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpTImeSheetServiceImpl implements EmpTimeSheetService {

    @Autowired
    EmpTImeSheetDao etsd;

    @Autowired
    UserDao userdao;

    @Autowired
    UserWorkingHoursDao uwhd;

    @Override
    public ResponseEntity<String> emplogin(Integer id) {
        try {
            Optional<User> useri = userdao.findById(id);
            if (useri.isPresent()) {
                if (useri.get().getLogin().equals("0")) {
                    userdao.updateLoginStatus("1", id);

                    EmpTimeSheet ets = new EmpTimeSheet();
                    ets.setStatus("1");
                    ets.setUser(id);
                    ets.setLogouttime("checkout");
                    ets.setWorkinghours("0");

                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                    ets.setLogintime(myObj.format(formatter));
                    etsd.save(ets);
                    return HrmsUtils.getResponeEntity("Successfully  Login.", HttpStatus.OK);
                } else {
                    return HrmsUtils.getResponeEntity("You are already Logged In", HttpStatus.BAD_REQUEST);
                }
            } else {
                return HrmsUtils.getResponeEntity("User Not Exists", HttpStatus.OK);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> emplogout(Integer id) {
        try {

            Optional<User> useri = userdao.findById(id);
            if (useri.isPresent()) {
                EmpTimeSheet etso = etsd.findByLogedItem(id, "checkout");
                if (etso != null) {
                    EmpTimeSheet ets = new EmpTimeSheet();
                    ets.setStatus("0");
                    ets.setUser(id);
                    ets.setId(etso.getId());
                    ets.setLogintime(etso.getLogintime());

                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime startDateTime = LocalDateTime.parse(etso.getLogintime().toString(), formatter);
                    LocalDateTime endDateTime = LocalDateTime.now();
                    Duration duration = Duration.between(startDateTime, endDateTime);

                    ets.setLogouttime(myObj.format(formatter));
                    ets.setWorkinghours(String.valueOf(duration.toMillis()));
                    etsd.save(ets);
                    userdao.updateLoginStatus("0", id);
                    return HrmsUtils.getResponeEntity("Successfully  Logout.", HttpStatus.OK);
                } else {
                    return HrmsUtils.getResponeEntity("You are already Logged In", HttpStatus.BAD_REQUEST);
                }
            } else {
                return HrmsUtils.getResponeEntity("User Not Exists", HttpStatus.OK);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<Object>> UserWorkingHours() {
        try {
            List<Object[]> results = etsd.findUserWorkingHours();
            List<Object> userWorkingHours = new ArrayList<>();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] result : results) {
                Integer user = (Integer) result[0];
                Date workingdate = (Date) result[1];
                String workingHours = (String) result[2];
                String totalworkinghours = (String) result[3];
                String mode = (String) result[4];
                String formattedWorkingdate = dateFormat.format(workingdate);

                // Create a new UserWorkingHours object
                UserWorkingHours userWorkingHour = new UserWorkingHours();
                userWorkingHour.setUser(user);
                userWorkingHour.setWorkingdate(formattedWorkingdate);
                userWorkingHour.setWorkinghours(workingHours);
                userWorkingHour.setTotalworkinghours(totalworkinghours);
                userWorkingHour.setMode(mode);

                // Save the UserWorkingHours object to the database
                uwhd.save(userWorkingHour);

                // Add the UserWorkingHours object to the list
                userWorkingHours.add(userWorkingHour);
            }

            return new ResponseEntity<>(userWorkingHours, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}