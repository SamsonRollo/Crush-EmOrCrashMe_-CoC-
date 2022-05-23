package gen;

import java.awt.EventQueue;
import java.awt.AWTEvent;

class EventQueueProxy extends EventQueue {
 
    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
        } catch (Throwable t) {}
    }
}