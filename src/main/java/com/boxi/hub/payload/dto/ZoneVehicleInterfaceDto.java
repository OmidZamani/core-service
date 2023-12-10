package com.boxi.hub.payload.dto;

import java.sql.Clob;

public interface ZoneVehicleInterfaceDto {
    Clob getpolygon();
    Long gethub();
    Long getcountrydevision();
    Long getpudovehicle();
    Long getmdlvehicle();

    String getcolor();
    Long getpolygonid();


}
