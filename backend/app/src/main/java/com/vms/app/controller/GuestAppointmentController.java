package com.vms.app.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.vms.app.service.AppointmentService_guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/guest")
@RestController
public class GuestAppointmentController {

  @ModelAttribute("cp")
  public String getContextPath(HttpServletRequest request) {
    return request.getContextPath();
  }

  @Autowired
  AppointmentService_guest appointmentService_guest;

  /*** [GUEST] 신청내역 확인하기 ***/
  @GetMapping("/getMyHistory")
  public Map<String, Object> getMyHistory(Principal principal) {
    Map<String, Object> results = appointmentService_guest.getMyHistory(principal.getName());
    return results;
  }

  /*** [GUEST] 도착 알림 보내기 ***/
  @PostMapping("/sendArrived")
  public int sendArrived(long appointmentID) {
    return appointmentService_guest.sendArrived(appointmentID);
  }

  /*** [GUEST] 내 약속 가져오기 ***/
  // (guest 기능에는 없는 것 같은데 혹시 모르니 만들어 놓음)
  @GetMapping("/getMyAppointment")
  public Map<String, Object> getMyAppointment(Principal principal) {

    Map<String, Object> results = appointmentService_guest.getMyAppointment(principal.getName());

    return results;
  }
}
