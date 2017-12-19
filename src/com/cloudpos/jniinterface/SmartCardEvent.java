package com.cloudpos.jniinterface;

public class SmartCardEvent {
    public static int SMART_CARD_EVENT_INSERT_CARD = 0;
    public static int SMART_CARD_EVENT_REMOVE_CARD = 1;
    public int nEventID = -1;
    public int nSlotIndex = -1;
}
