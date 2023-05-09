package com.boxi.hub.api;
//100102	hub cayegory	HubCategoryApi	مدیریت هاب - گونه هاب
import com.boxi.core.response.Response;
import com.boxi.hub.enums.HubType;
import com.boxi.hub.payload.request.CreateHubCategoryRequest;
import com.boxi.hub.payload.request.FilterHubCategory;
import com.boxi.hub.payload.response.CreateHubCategoryResponse;
import com.boxi.hub.service.HubService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-api/hubCategory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class HubCategoryApi {

    private final HubService hubService;


    @GetMapping("/selectHubTypes")
    public Response hybTypes() {
        return  Response.ok().setPayload(HubType.select());
    }

/*    @GetMapping("/parentHub")
    public Response parentHub(@RequestParam(name = "name",required = true) String name) {
        return  Response.ok().setPayload(hubService.selectParentHub(name));
    }*/
// @PreAuthorize("hasPermission('hasAccess','10010201')")
    @PostMapping
    public Response createHubCategory(@RequestBody CreateHubCategoryRequest request) {
        CreateHubCategoryResponse response=hubService.createHubCategory(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10010202')")
    @PutMapping
    public Response edit(@RequestBody CreateHubCategoryRequest request) {
        CreateHubCategoryResponse response=hubService.updateHubCategory(request);
        return  Response.ok().setPayload(response);
    }

    // @PreAuthorize("hasPermission('hasAccess','10010203')")
    @DeleteMapping("/{id}")
    public Response delHubCategory(@PathVariable Long id) {
        hubService.deleteHubCategory(id);
        return  Response.ok();
    }


    @GetMapping("/select")
    public Response getHubCategories(@RequestParam(name = "filter",required = true) String filter) {
            return Response.ok().setPayload(hubService.selectHubCategory(filter));
    }

    @PostMapping("/filter")
    public Response filter(@RequestBody FilterHubCategory filterHubCategory,
                           @RequestParam(name = "pageNumber",defaultValue = "1",required=false) Integer pageNumber,
                           @RequestParam(name = "pageSize",defaultValue = "10",required=false) Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return  Response.ok().setPayload(hubService.filterHubCategory(filterHubCategory,pageable));
    }


    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return  Response.ok().setPayload(hubService.findHubCatById(id));
    }


}
