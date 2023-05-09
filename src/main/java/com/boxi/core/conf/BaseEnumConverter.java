package com.boxi.core.conf;

public interface BaseEnumConverter<E extends Enum, V> {
    E fromValueToEnum(final V value);
    V fromEnumToValue(final E enumValue);

}