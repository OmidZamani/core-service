package com.boxi.core.request;

import com.boxi.core.response.PermissionDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PermissionsDto {
   List<PermissionDto> perms;
   Boolean isSuperAdmin;
}
