package com.boxi.personalizationform.api;


import com.boxi.core.response.Response;
import com.boxi.personalizationform.payload.dto.PersonalizationFormDto;
import com.boxi.personalizationform.service.PersonalizationFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/core-api/personalizationform")
@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonalizationFormApi {

    public final PersonalizationFormService personalizationFormService;

    @PostMapping
    private Response create(@RequestBody PersonalizationFormDto dto) {
        log.warn(dto.toJson());
        return Response.ok().setPayload(personalizationFormService.create(dto));
    }

    @PutMapping
    private Response edit(@RequestBody PersonalizationFormDto dto) {
        log.warn(dto.toJson());
        return Response.ok().setPayload(personalizationFormService.edit(dto));
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        personalizationFormService.delete(id);
        return Response.ok();
    }

    @GetMapping
    private Response getUserIdAndPermissionId(@RequestParam(name = "userId") Long userId, @RequestParam(name = "permissionId") Long permissionId) {
        return Response.ok().setPayload(personalizationFormService.getByUseridAndPermissionId(userId, permissionId));
    }

    @GetMapping("/findbycode")
    private Response getUserIdAndPermissionId(@RequestParam(name = "userId") Long userId, @RequestParam(name = "permissionId") String permissionCode) {

        return Response.ok().setPayload(personalizationFormService.findByCode(userId, permissionCode));
    }
}
