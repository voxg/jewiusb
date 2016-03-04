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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.midi.SysexMessage;
import javax.sound.midi.InvalidMidiDataException;

/**
 *
 * @author Greg Lyons <greglyons50+github@gmail.com>
 */
public class Config {
    public Config() {
        bank0 = new byte[] {
            (byte)0x40,
            (byte)0x40,
            (byte)0x40,
            (byte)0x40,
            (byte)0x08,
            (byte)0x7F
        };
        bank2 = new byte[] {
            (byte)0x00,
            (byte)0x00,
            (byte)0x40,// (middle C)
            (byte)0x20,// (fixed)
            (byte)0x02,// (CC2/Breath)
            (byte)0x00,// (off)
            (byte)0x00,
            (byte)0x7F,
            (byte)0x00,// (off)
            (byte)0x7F,
            (byte)0x7F
        };
    }

    public int getBreathGain() {
        return (int)bank0[0];
    }

    public void setBreathGain(int breathGain) {
        if (breathGain < 0 || breathGain > 0x7F)
            throw new IllegalArgumentException("BreathGain must be 0-127");
        bank0[0] = (byte)breathGain;
    }

    public int getBiteGain() {
        return (int)bank0[1];
    }

    public void setBiteGain(int biteGain) {
        if (biteGain < 0 || biteGain > 0x7F)
            throw new IllegalArgumentException("BiteGain must be 0-127");
        bank0[1] = (byte)biteGain;
    }

    public int getBiteAcGain() {
        return (int)bank0[2];
    }

    public void setBiteAcGain(int biteAcGain) {
        if (biteAcGain < 0 || biteAcGain > 0x7F)
            throw new IllegalArgumentException("BiteAcGain must be 0-127");
        bank0[2] = (byte)biteAcGain;
    }

    public int getPitchBendGain() {
        return (int)bank0[3];
    }

    public void setPitchBendGain(int pitchBendGain) {
        if (pitchBendGain < 0 || pitchBendGain > 0x7F)
            throw new IllegalArgumentException("PitchBendGain must be 0-127");
        bank0[3] = (byte)pitchBendGain;
    }

    public int getKeyDelay() {
        return (int)bank0[4];
    }

    public void setKeyDelay(int keyDelay) {
        if (keyDelay < 0 || keyDelay > 0xF)
            throw new IllegalArgumentException("KeyDelay must be 0-15");
        bank0[4] = (byte)keyDelay;
    }

    public int getUnknown() {
        return (int)bank0[5];
    }

    public void setUnknown(int unknown) {
        if (unknown < 0 || unknown > 0x7F)
            throw new IllegalArgumentException("Unknown must be 0-127");
        bank0[5] = (byte)unknown;
    }

    public int getMidiChannel() {
        return (int)bank2[0];
    }

    public void setMidiChannel(int midiChannel) {
        if (midiChannel < 0 || midiChannel > 15)
            throw new IllegalArgumentException("MidiChannel must be 0-15");
        bank2[0] = (byte)midiChannel;
    }

    public int getFingering() {
        return (int)bank2[1];
    }

    public void setFingering(int fingering) {
        if (fingering < 0 || fingering > 5)
            throw new IllegalArgumentException("Fingering must be 0-5");
        bank2[1] = (byte)fingering;
    }

    public int getTranspose() {
        return (int)bank2[2];
    }

    public void setTranspose(int transpose) {
        if (transpose < 0x22 || transpose > 0x5D)
            throw new IllegalArgumentException("Transpose must be 34-93");
        bank2[2] = (byte)transpose;
    }

    public int getVelocity() {
        return (int)bank2[3];
    }

    public void setVelocity(int velocity) {
        if (velocity < 0 || velocity > 0x7F)
            throw new IllegalArgumentException("Velocity must be 0-127");
        bank2[3] = (byte)velocity;
    }

    public int getBreathCC1() {
        return (int)bank2[4];
    }

    public void setBreathCC1(int breathCC1) {
        if (breathCC1 < 0 || breathCC1 > 0x7F)
            throw new IllegalArgumentException("BreathCC1 must be 0-127");
        bank2[4] = (byte)breathCC1;
    }

    public int getBreathCC2() {
        return (int)bank2[5];
    }

    public void setBreathCC2(int breathCC2) {
        if (breathCC2 < 0 || breathCC2 > 0x7F)
            throw new IllegalArgumentException("BreathCC2 must be 0-127");
        bank2[5] = (byte)breathCC2;
    }

    public int getUnknown2() {
        return (int)bank2[6];
    }

    public void setUnknown2(int unknown2) {
        if (unknown2 < 0 || unknown2 > 0x7F)
            throw new IllegalArgumentException("Unknown2 must be 0-127");
        bank2[6] = (byte)unknown2;
    }

    public int getBiteCC1() {
        return (int)bank2[7];
    }

    public void setBiteCC1(int biteCC1) {
        if (biteCC1 < 0 || biteCC1 > 0x7F)
            throw new IllegalArgumentException("BiteCC1 must be 0-127");
        bank2[7] = (byte)biteCC1;
    }

    public int getBiteCC2() {
        return (int)bank2[8];
    }

    public void setBiteCC2(int biteCC2) {
        if (biteCC2 < 0 || biteCC2 > 0x7F)
            throw new IllegalArgumentException("BiteCC2 must be 0-127");
        bank2[8] = (byte)biteCC2;
    }

    public int getPitchBendUp() {
        return (int)bank2[9];
    }

    public void setPitchBendUp(int pitchBendUp) {
        if (pitchBendUp < 0 || pitchBendUp > 0x7F)
            throw new IllegalArgumentException("PitchBendUp must be 0-127");
        bank2[9] = (byte)pitchBendUp;
    }

    public int getPitchBendDown() {
        return (int)bank2[0x0A];
    }

    public void setPitchBendDown(int pitchBendDown) {
        if (pitchBendDown < 0 || pitchBendDown > 0x7F)
            throw new IllegalArgumentException("PitchBendDown must be 0-127");
        bank2[0x0A] = (byte)pitchBendDown;
    }
    
    private class Item {
        public Item(String name, int msb, int lsb, int defaultValue, int min, int max) {
            this.name = name;
            this.msb = msb;
            this.lsb = lsb;
            this.value = defaultValue;
            this.min = min;
            this.max = max;
        }
        private final String name;
        private final int msb, lsb, min, max;
        private int value;

        public void setValue(int v) {
            if (min > v || v > max) {
                System.out.println("Bad call to setValue()");
                System.out.println(toString());
                System.out.println(v);
                throw new IllegalArgumentException(name+" value out of range");
            }
            value = v;
        }
        public byte getValue() {
            return (byte)value;
        }
        @Override
        public String toString() {
            return "jewiusb.Config.Item(" + name + ", " + msb + ", " + lsb +
                    ", " + value + ", " + min + ", " + max + ")";
        }
    }
    
    private final Item[] items = {
            new Item("breath_gain", 0, 0, 64, 0, 127),
            new Item("bite_gain", 0, 1, 64, 0, 127),
            new Item("bite_ac_gain", 0, 2, 64, 0, 127),
            new Item("pitch_bend_gain", 0, 3, 64, 0, 127),
            new Item("key_delay", 0, 4, 8, 0, 15),
            new Item("unknown", 0, 5, 127, 0, 127),
            new Item("midi_channel", 2, 0, 0, 0, 15),
            new Item("fingering", 2, 1, 0, 0, 5),
            new Item("transpose", 2, 2, 64, 0, 127),
            new Item("velocity", 2, 3, 32, 0, 127),
            new Item("breath_cc1", 2, 4, 2, 0, 127),
            new Item("breath_cc2", 2, 5, 0, 0, 127),
            new Item("unknown2", 2, 6, 0, 0, 127),
            new Item("bite_cc1", 2, 7, 127, 0, 127),
            new Item("bite_cc2", 2, 8, 0, 0, 127),
            new Item("pitch_bend_up", 2, 9, 127, 0, 127),
            new Item("pitch_bend_down", 2, 10, 127, 0, 127)
    };

    /**
     * Get the total number of configuration parameters
     * @return the number of parameters
     */
    public int maxItems() {
        return items.length;
    }
    /**
     * Returns the name of the configuration parameter for the 0-based location
     * given by the index parameter
     * @param index the location of the parameter, 0-based
     * @return the name of the parameter
     */
    public String getName(int index) {
        return items[index].name;
    }
    /**
     * Returns the current value of the configuration parameter at the 0-based
     * location provided
     * @param index the location of the parameter, 0-based
     * @return the current value
     */
    public int getValue(int index) {
        return items[index].value;
    }
    /**
     * Sets the value of the configuration parameter at the location provided.
     * An IllegalArgumentException may be thrown if the value is out of range.
     * @param index the location of the parameter, 0-based
     * @param val the value to set
     */
    public void setValue(int index, int val) {
        items[index].setValue(val);
    }
    /**
     * Sets the value of the configuration parameter with the name provided.
     * An IllegalArgumentException may be thrown if the value is out of range.
     * @param name the name of the parameter
     * @param val the value to set
     */
    public void setValue(String name, int val) {
        for (Item item : items) {
            if (item.name.equals(name)) {
                item.setValue(val);
            }
        }
    }

    /**
     * Sets the value of the configuration parameter at the EWI-USB-specific
     * address defined by msb and lsb.  (The addresses can be found online.)
     * If val is out of range, an IllegalArgumentException will be thrown.
     * @param msb the address MSB
     * @param lsb the address LSB
     * @param val the value
     */
    public void setValue(int msb, int lsb, int val) {
        if (msb == 0) {
            switch (lsb) {
                case 0:
                    setBreathGain(val);
                    break;
                case 1:
                    setBiteGain(val);
                    break;
                case 2:
                    setBiteAcGain(val);
                    break;
                case 3:
                    setPitchBendGain(val);
                    break;
                case 4:
                    setKeyDelay(val);
                    break;
                case 5:
                    setUnknown(val);
                    break;
                default:
                    throw new IllegalArgumentException("LSB out of range");
            }
        }
        if (msb == 2) {
            switch (lsb) {
                case 0:
                    setMidiChannel(val);
                    break;
                case 1:
                    setFingering(val);
                    break;
                case 2:
                    setTranspose(val);
                    break;
                case 3:
                    setVelocity(val);
                    break;
                case 4:
                    setBreathCC1(val);
                    break;
                case 5:
                    setBreathCC2(val);
                    break;
                case 6:
                    setUnknown2(val);
                    break;
                case 7:
                    setBiteCC1(val);
                    break;
                case 8:
                    setBiteCC2(val);
                    break;
                case 9:
                    setPitchBendUp(val);
                    break;
                case 10:
                    setPitchBendDown(val);
                    break;
                default:
                    throw new IllegalArgumentException("LSB out of range");
            }
        }
        throw new IllegalArgumentException("MSB out of range");
    }
    //test
    /**
     * Provides an array of SysexMessages that describe the current
     * configuration, for sending to the EWI-USB.  (The EWI-USB must be put
     * into sysex mode before sending.)
     * @return sysex messages to send to the EWI-USB
     */
    public SysexMessage[] toSysex() {
        byte[] m1 = {
            (byte)0xf0, (byte)0x47, (byte)0x7f,
            (byte)0x6d, (byte)0, (byte)0, (byte)6,
            bank0[0],
            bank0[1],
            bank0[2],
            bank0[3],
            bank0[4],
            bank0[5],
            (byte)0xf7
        };
        byte[] m2 = {
            (byte)0xf0, (byte)0x47, (byte)0x7f,
            (byte)0x6d, (byte)2, (byte)0, (byte)11,
            bank2[0],
            bank2[1],
            bank2[2],
            bank2[3],
            bank2[4],
            bank2[5],
            bank2[6],
            bank2[7],
            bank2[8],
            bank2[9],
            bank2[10],
            (byte)0xf7
        };
        try {
            SysexMessage[] messages = {
                new SysexMessage(m1, m1.length),
                new SysexMessage(m2, m2.length)
            };
            return messages;
        } catch (InvalidMidiDataException e) {
            return new SysexMessage[] {};
        }
    }
    
    /**
     * Changes the values in this Config object to match those defined by the
     * sysex messages passed in s.  Sysex messages for other devices will be
     * ignored, but EWI-USB messages with invalid data will result in an
     * IllegalArguementException.
     * @param s the messages
     */
    public void fromSysex(SysexMessage[] s) {
        for (SysexMessage m : s) {
            byte[] b = m.getData();
            if ((b.length < 7) || (m.getStatus() != 0xF0) ||
                (b[0] != 0x47) || (b[2] != 0x6d)) {
                continue;
            }
            int msb = (int)b[3];
            int lsb = (int)b[4];
            int length = (int)b[5];
            if (b.length < (6+length)) {
                continue;
            }
            for (int i = 0; i < length; i++) {
                setValue(msb, lsb + i, b[6+i]);
            }
        }
    }
    
    /**
     * Loads a sysex file and sets the configuration parameters in this Config
     * object to match the values in the file.  Sysex messages for other devices
     * will be ignored, but EWI-USB messages with invalid data will result in an
     * IllegalArguementException.
     * @param f the file to load
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidMidiDataException
     */
    public void loadSysexFile(File f) throws FileNotFoundException, IOException,
                                            InvalidMidiDataException {
        try (FileInputStream fis = new FileInputStream(f)) {
            if (! f.isFile()) { throw new IOException("Not a SYSEX file"); }
            long size = f.length();
            if (size > Integer.MAX_VALUE) {
                throw new IOException("Crazy file size, man");
            }
            byte[] barray = java.nio.file.Files.readAllBytes(f.toPath());
            boolean sysexInProgress = false;
            int counter = 0;
            int startOfSysex = -1;
            for (byte b : barray) {
                if (b == 0xf7 && sysexInProgress) {
                    byte[] m=Arrays.copyOfRange(barray,startOfSysex,counter+1);
                    SysexMessage message = new SysexMessage(m, m.length);
                    fromSysex(new SysexMessage[] { message });
                    sysexInProgress = false;
                    startOfSysex = -1;
                } else if (b == 0xf0) {
                    sysexInProgress = true;
                    startOfSysex = counter;
                }
                counter++;
            }
        }
    }
    
    /**
     * Writes the current Config parameter values to a file in a raw sysex
     * (.syx) file.
     * @param f the file to write to
     * @throws IOException
     */
    public void saveSysexFile(File f) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))) {
            SysexMessage[] ms = toSysex();
            for (SysexMessage m : ms) {
                bos.write(m.getStatus());
                bos.write(m.getData());
            }
        }
    }
    
    private byte[] bank0;
    private byte[] bank2;
}
