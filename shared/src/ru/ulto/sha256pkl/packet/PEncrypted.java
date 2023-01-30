package ru.ulto.sha256pkl.packet;

import ru.ulto.netapi.v2.protocol.packet.PString;

public class PEncrypted extends PString {
    public PEncrypted() {
        super();
    }
    public PEncrypted(String str) {
        super(str);
    }
}
