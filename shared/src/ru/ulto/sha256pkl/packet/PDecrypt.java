package ru.ulto.sha256pkl.packet;

import ru.ulto.netapi.v2.protocol.packet.PString;

public class PDecrypt extends PString {
    public PDecrypt() {
        super();
    }
    public PDecrypt(String str) {
        super(str);
    }
}
