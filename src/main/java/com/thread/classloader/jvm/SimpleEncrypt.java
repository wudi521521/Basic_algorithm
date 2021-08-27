package com.thread.classloader.jvm;


/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/26 15:07
 */
public class SimpleEncrypt {

    private final static String plain = "Hello ClassLoader";

    private static final byte ENCRYPT_FACTOR=(byte)0xff;

    public static void main(String[] args) {
        byte[] bytes = plain.getBytes();
        byte[] encrypt = new byte[bytes.length];
        for (int i=0;i<bytes.length;i++){
            encrypt[i]=(byte)(bytes[i]^ENCRYPT_FACTOR);
        }
        System.out.println(new String(encrypt));
    }
}
