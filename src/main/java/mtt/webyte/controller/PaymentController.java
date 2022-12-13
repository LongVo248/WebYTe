package mtt.webyte.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import mtt.webyte.config.PaypalPaymentIntent;
import mtt.webyte.config.PaypalPaymentMethod;
import mtt.webyte.dto.AppointmentPayment;
import mtt.webyte.dto.AppointmentScheduleDTO;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.MedicalBill;
import mtt.webyte.services.MedicalBillService;
import mtt.webyte.services.impl.PaypalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/pay")
public class PaymentController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService paypalService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @Autowired
    private MedicalBillService medicalbillService;

    @PostMapping("")
    public Map<String,Object> pay(@RequestBody() AppointmentPayment appoint  ){
        System.out.println(appoint.toString());
        Map<String,Object> linkPayment=new HashMap<>();
        String cancelUrl ="http://localhost:4200/#/user/payment/";
        String successUrl = "http://localhost:4200/#/user/payment/?id="+appoint.getIdappointmentSchedule() ;
        try {
            Payment payment = paypalService.createPayment(
                    appoint.getPrice(),
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl
            );
            for(Links links : payment.getLinks()){
                if("approval_url".equals(links.getRel())){
                    linkPayment.put("linkPayment",links.getHref());
                    return linkPayment;
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return null;
    }
    @GetMapping("/cancel")
    public String cancelPay(){
        return "cancel";
    }
    @PostMapping("/success")
    public Map<String,Object> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, @RequestBody AppointmentScheduleDTO appointmentScheduleDTO){
        Map<String,Object> success=new HashMap<>();
//        Medical_bills medical_bills= new Medical_bills();
        MedicalBill medicalBill = new MedicalBill();
        System.out.println(appointmentScheduleDTO.toString());
        try {

            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){

//                medical_bills.setPrice(appointmentScheduleDTO.getPrice());
//                medical_bills.setAppointscheduleid(appointmentScheduleDTO.getIdappointmentSchedule());
//                medical_bills.setStatus("success");
//                Medical_bills createMedicalBill = medicalbillService.insertMedical_bills(medical_bills);
                medicalBill.setTotalPrice(appointmentScheduleDTO.getPrice());
                AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
                appointmentSchedule.setAppointmentId(appointmentScheduleDTO.getAppointmentId());
                appointmentSchedule.setAppointmentStatus("success");
                medicalBill.setAppointmentSchedule(appointmentSchedule);
                MedicalBill createMedicalBill = medicalbillService.insertMedicalBill(medicalBill);
                if(createMedicalBill!=null){
                    success.put("success","success");
                    return success;
                }

            }
            success.put("success","error");
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return success;
    }
}
