package ru.ulto.sha256pkl.packet;

import ru.ulto.netapi.v2.protocol.packet.PString;

public class PEncrypt extends PString {
    public PEncrypt() {
        super();
    }
    public PEncrypt(String str) {
        super(str);
    }
}
