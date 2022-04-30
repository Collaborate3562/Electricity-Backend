//package com.idigital.epam.energy.controller;
//
//import com.idigital.epam.energy.service.BillingService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.HashMap;
//
//public class PaymentController {
//    @PostMapping("/pay/{pay uuid}")
//    public ResponseEntity<MoneyTransferUrlDTO> startMoneyTransfer(@ApiParam(value = "advertisementUuid", required = true) @PathVariable String advertisementUuid,
//                                                                  @ApiParam(value = "paymentRequestFromUser", required = true) @Valid @RequestBody PaymentRequestFromUser paymentRequestFromUser) {
//        MoneyTransferUrlDTO moneyTransferUrlDTO = BillingService.getMoneyTransferUrlDTO(advertisementUuid, paymentRequestFromUser);
//        return new ResponseEntity<>(moneyTransferUrlDTO, HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "complete money transfer",
//            notes = "resident account module sends response about transfer result ",
//            response = String.class
//    )
//    @AuthorizeRequest
//    @PostMapping("/accountservice/transfermoney")
//    public ResponseEntity<HashMap<String,Boolean>> completeMoneyTransfer(@ApiParam(value = "complateTransferRequest", required = true) @RequestBody ComplateTransferRequest complateTransferRequest){
//        BillingService.completeMoneyTransfer(complateTransferRequest);
//        HashMap<String,Boolean> response = new HashMap<>();
//        response.put("success", true);
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/transfer-result")
//    public String transferResult(){
//        return "You successfully made money transfer";
//    }
//}
//
//}
