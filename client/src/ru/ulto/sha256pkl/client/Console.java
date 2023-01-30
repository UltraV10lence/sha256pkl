package ru.ulto.sha256pkl.client;

import io.netty.channel.socket.SocketChannel;
import ru.ulto.helper.java.system.UltoInputStreamReader;
import ru.ulto.netapi.v2.net.UltoNetClient;
import ru.ulto.netapi.v2.net.UltoNetContext;
import ru.ulto.netapi.v2.protocol.Packet;
import ru.ulto.netapi.v2.protocol.PacketDictionary;
import ru.ulto.sha256pkl.packet.PDecrypt;
import ru.ulto.sha256pkl.packet.PDecrypted;
import ru.ulto.sha256pkl.packet.PEncrypt;
import ru.ulto.sha256pkl.packet.PEncrypted;

public class Console {
    public static final String help = "encrypt %текст% - зашифровать\ndecrypt %текст% - расшифровать";

    public static void main(String[] args) {
        new Console();
    }

    private final String ip;
    private final int port;
    public static UltoNetClient client;
    public Console() {
        PacketDictionary.RegisterPacket(new PEncrypt(), new PDecrypt(), new PEncrypted(), new PDecrypted());

        String conn = new String(FileDownloader.GetFile("https://raw.githubusercontent.com/UltraV10lence/sha256pkl/main/connection.txt"));

        ip = conn.strip().split(":")[0];
        port = Integer.parseInt(conn.strip().split(":")[1]);

        UltoNetContext context = new UltoNetContext(ip, port, null, this::onPacket);
        client = new UltoNetClient(context);

        UltoInputStreamReader reader = new UltoInputStreamReader(System.in);
        System.out.println(help);

        new Thread(() -> {
            while (true) {
                if (reader.available() > 0) {
                    String line = reader.next();
                    if (line.startsWith("encrypt ")) onCommand(0, line.replaceFirst("encrypt ", ""));
                    else if (line.startsWith("decrypt ")) onCommand(1, line.replaceFirst("decrypt ", ""));
                    else System.out.println(help);
                }
            }
        }).start();
    }

    public void onPacket(SocketChannel channel, Packet<?> packet) {
        if (packet instanceof PEncrypted p) {
            System.out.println("Зашифрованное сообщение: " + PacketDictionary.Decode(p));
        } else if (packet instanceof PDecrypted p) {
            System.out.println("Расшифрованное сообщение: " + PacketDictionary.Decode(p));
        }
    }

    public void onCommand(int cmd, String arg) {
        try {
            switch (cmd) {
                case 0 -> client.instantSend(new PEncrypt(arg));
                case 1 -> client.instantSend(new PDecrypt(arg));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
