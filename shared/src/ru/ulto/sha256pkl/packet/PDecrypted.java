package ru.ulto.sha256pkl.packet;

import ru.ulto.netapi.v2.protocol.packet.PString;

public class PDecrypted extends PString {
    public PDecrypted() {
        super();
    }
    public PDecrypted(String str) {
        super(str);
    }
}
