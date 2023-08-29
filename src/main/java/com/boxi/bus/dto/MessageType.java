package com.boxi.bus.dto;

public enum MessageType {
    LOGIN,
    ONE_TIME_PASS_SEND,
    NOTIFY_PICKUP,
    NOTIFY_PICKUP_FROM_HUB,
    FAILED_PICKUP,
    CITY_HUB_RECEIVE,
    DELIVERY_HUB_RECEIVE,
    DELIVER_TIME_NOTIFICATION,
    STAFF_HAS_CONSIGNMENT,
    DELIVERED_TO_DESTINATION,
    DELIVERED_TO_HUB,
    NOT_DELIVERED,
    RETURN,
    SECONDARY_ADDRESS_DELIVERED
}
