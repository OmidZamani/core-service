package com.boxi.hub.payload.dto;

import java.sql.Clob;

public interface ZoneVehicleInterfaceDto {
    Clob getpolygon();
    Long gethub();
    Long getcountrydevision();
    Long getvehicleplanid();
    Long getmdlvehicle();
    String getcolor();

}
