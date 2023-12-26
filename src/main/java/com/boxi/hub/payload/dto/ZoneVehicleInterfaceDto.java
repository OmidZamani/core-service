package com.boxi.hub.payload.dto;

import java.sql.Clob;

public interface ZoneVehicleInterfaceDto {
    Clob getpolygon();
    Long gethub();
    Long getcountrydevision();
    Long getpudovehicle();
    Long getmdlvehicle();
    Long getpl();

    String getcolor();
    Long getpolygonid();
    Long getvehicleplanid();


}
