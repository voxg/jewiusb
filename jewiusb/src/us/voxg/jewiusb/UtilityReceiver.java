/*
 * Copyright (C) 2016 Greg Lyons <greglyons50+github@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package us.voxg.jewiusb;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.SysexMessage;

/**
 * This class listens for sysex MIDI messages coming from a EWI-USB and
 * updates a Config object to reflect the values received.
 * @author Greg Lyons <greglyons50+github@gmail.com>
 */
public class UtilityReceiver implements javax.sound.midi.Receiver {
    Config conf;
    boolean ignore = false;
    int messagesProcessed = 0;

    /**
     * Creates the listener, updating the Config provided.
     * @param c the Config to update with changes
     */
    public UtilityReceiver(Config c) {
        conf = c;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (ignore) return;
        if (message instanceof SysexMessage) {
            SysexMessage m = (SysexMessage)message;
            //for (byte b : m.getMessage()) {
            //    System.out.print(String.format("%02X ", b));
            //}
            //System.out.println();
            conf.fromSysex(new SysexMessage[] { m });
            messagesProcessed++;
        }
    }

    /**
     * Causes the listener to stop paying attention, but doesn't close the
     * underlying MIDI path.
     */
    @Override
    public void close() {
        ignore = true;
    }

    /**
     * Starts listening for sysex messages again following a previous close().
     */
    public void reopen() {
        ignore = false;
    }

    /**
     * Returns the total number of messages received.
     * @return the number of messages
     */
    public int getMessagesProcessed() {
        return messagesProcessed;
    }
   
}
