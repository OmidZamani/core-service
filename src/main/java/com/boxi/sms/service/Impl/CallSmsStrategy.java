package com.boxi.sms.service.Impl;

import com.boxi.bus.dto.PluralMessage;
import com.boxi.sms.service.strategies.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CallSmsStrategy {

    private final SendLoginMessageStrategy loginMessageStrategy;
    private final SendPassWordMessageStrategy passWordMessageStrategy;
    private final NotifyPickupMessageStrategy notifyPickupMessageStrategy;
    private final NotifyPickupFromHubMessageStrategy notifyPickupFromHubMessageStrategy;
    private final FailedPickupMessageStrategy failedPickupMessageStrategy;
    private final CityHubReceiveMessageStrategy cityHubReceiveMessageStrategy;
    private final DeliveryHubReceiveMessageStrategy deliveryHubReceiveMessageStrategy;
    private final DeliveryTimeMessageStrategy deliveryTimeMessageStrategy;
    private final StaffHasConsignmenetMessageStrategy staffHasConsignmenetMessageStrategy;
    private final DeliveryToDestinationMessageStrategy deliveryToDestinationMessageStrategy;
    private final DeliveryToHubMessageStrategy deliveryToHubMessageStrategy;
    private final NotDeliveredMessageStrategy notDeliveredMessageStrategy;
    private final ReturnSmsMessageStrategy returnSmsMessageStrategy;
    private final SecondaryDestinationDeliveredMessageStrategy secondaryDestinationDeliveredMessageStrategy;


    public void executeStrategy(PluralMessage overallMessage) {
        switch (overallMessage.getMessageType()) {
            case LOGIN:
                System.out.println(">>>>>>>>>>>>>>>>>>login");
                loginMessageStrategy.execute(overallMessage);
                break;

            case ONE_TIME_PASS_SEND:
                passWordMessageStrategy.execute(overallMessage);
                break;


            case NOTIFY_PICKUP:
                notifyPickupMessageStrategy.execute(overallMessage);
                break;


            case NOTIFY_PICKUP_FROM_HUB:
                notifyPickupFromHubMessageStrategy.execute(overallMessage);
                break;


            case FAILED_PICKUP:
                failedPickupMessageStrategy.execute(overallMessage);
                break;


            case CITY_HUB_RECEIVE:
                cityHubReceiveMessageStrategy.execute(overallMessage);
                break;

            case DELIVERY_HUB_RECEIVE:
                deliveryHubReceiveMessageStrategy.execute(overallMessage);
                break;

            case DELIVER_TIME_NOTIFICATION:
                deliveryTimeMessageStrategy.execute(overallMessage);
                break;

            case STAFF_HAS_CONSIGNMENT:
                staffHasConsignmenetMessageStrategy.execute(overallMessage);
                break;

            case DELIVERED_TO_DESTINATION:
                deliveryToDestinationMessageStrategy.execute(overallMessage);
                break;

            case DELIVERED_TO_HUB:
                deliveryToHubMessageStrategy.execute(overallMessage);
                break;

            case NOT_DELIVERED:
                notDeliveredMessageStrategy.execute(overallMessage);
                break;

            case RETURN:
                returnSmsMessageStrategy.execute(overallMessage);
                break;

            case SECONDARY_ADDRESS_DELIVERED:
                secondaryDestinationDeliveredMessageStrategy.execute(overallMessage);
                break;


        }

    }

}
