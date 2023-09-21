package com.boxi.hub.api;

import com.boxi.core.response.Response;
import com.boxi.hub.enums.BankType;
import com.boxi.hub.payload.dto.BankAccountDto;
import com.boxi.hub.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/core-api/bankAccount")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BankAccountApi {

    private final BankAccountService bankAccountService;

    @PostMapping()
    public Response create(@RequestBody BankAccountDto dto) {
        return Response.ok().setPayload(bankAccountService.create(dto));
    }

    @PutMapping()
    public Response edit(@RequestBody BankAccountDto dto) {
        return Response.ok().setPayload(bankAccountService.edit(dto));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        bankAccountService.delete(id);
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable Long id) {
        return Response.ok().setPayload(bankAccountService.findById(id));
    }

    @GetMapping("/BankType")
    public Response BankType() {
        return Response.ok().setPayload(BankType.select());
    }

    @GetMapping("/select")
    public Response select(@RequestParam(name = "filter") String filter){
        return Response.ok().setPayload(bankAccountService.select(filter));
    }

}
