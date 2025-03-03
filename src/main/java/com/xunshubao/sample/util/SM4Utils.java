package com.xunshubao.sample.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import static java.util.Objects.isNull;

public class SM4Utils {
    private static final Logger logger = LoggerFactory.getLogger(SM4Utils.class);

    private static final int DEFAULT_KEY_SIZE = 128;
    private static final String ALGORITHM = "SM4";
    private static final String SM4_ECB_ = "SM4/ECB/";
    private static final String SM4_CBC_ = "SM4/CBC/";
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();
    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    private static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();

    static {
        if (isNull(Security.getProvider(BouncyCastleProvider.PROVIDER_NAME))) {
            Security.addProvider(PROVIDER);
        }
    }

    public enum Padding {
        PKCS5("PKCS5Padding"),
        PKCS7("PKCS7Padding"),
        ISO10126("ISO10126Padding");

        private final String name;

        Padding(String name) {
            this.name = name;
        }
    }

    // region generateKey

    public static byte[] genKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        return genKey(DEFAULT_KEY_SIZE);
    }

    public static byte[] genKey(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    public static String genKeyAsHex() throws NoSuchAlgorithmException, NoSuchProviderException {
        return genKeyAsHex(DEFAULT_KEY_SIZE);
    }

    public static String genKeyAsHex(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        return Hex.toHexString(genKey(keySize));
    }

    public static String genKeyAsBase64() throws NoSuchAlgorithmException, NoSuchProviderException {
        return genKeyAsBase64(DEFAULT_KEY_SIZE);
    }

    public static String genKeyAsBase64(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        return BASE64_ENCODER.encodeToString(genKey(keySize));
    }

    // endregion generateKey

    // region ECB mode

    public static Cipher getCipherECB(Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(SM4_ECB_ + padding.name, BouncyCastleProvider.PROVIDER_NAME);
    }

    /**
     * 使用指定的加密算法和密钥对给定的字节数组进行加密
     *
     * @param data 要加密的字节数组
     * @param key  加密所需的密钥
     * @return byte[]   加密后的字节数组
     */
    public static byte[] encryptECB(byte[] data, byte[] key, Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = getCipherECB(padding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    /**
     * 使用指定的加密算法和密钥对给定的字节数组进行解密
     *
     * @param data 要解密的字节数组
     * @param key  解密所需的密钥
     * @return byte[]   解密后的字节数组
     */
    public static byte[] decryptECB(byte[] data, byte[] key, Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = getCipherECB(padding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    public static String encryptHexECB(String data, String key, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return Hex.toHexString(encryptECB(
                data.getBytes(StandardCharsets.UTF_8),
                Hex.decode(key),
                padding
        ));
    }

    public static String decryptHexECB(String data, String key, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return new String(decryptECB(
                Hex.decode(data),
                Hex.decode(key),
                padding
        ), StandardCharsets.UTF_8);
    }

    public static String encryptBase64ECB(String data, String key, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return BASE64_ENCODER.encodeToString(encryptECB(
                data.getBytes(StandardCharsets.UTF_8),
                BASE64_DECODER.decode(key),
                padding
        ));
    }

    public static String decryptBase64ECB(String data, String key, Padding padding) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return new String(decryptECB(
                BASE64_DECODER.decode(data),
                BASE64_DECODER.decode(key),
                padding
        ), StandardCharsets.UTF_8);
    }

    // endregion ECB mode

    // region CBC mode

    public static Cipher getCipherCBC(Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        return Cipher.getInstance(SM4_CBC_ + padding.name, BouncyCastleProvider.PROVIDER_NAME);
    }

    /**
     * 使用指定的加密算法和密钥对给定的字节数组进行加密
     *
     * @param data 要加密的字节数组
     * @param key  加密所需的密钥
     * @param iv   解密所需的 IV
     * @return byte[]   加密后的字节数组
     */
    public static byte[] encryptCBC(byte[] data, byte[] key, byte[] iv, Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = getCipherCBC(padding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    /**
     * 使用指定的加密算法和密钥对给定的字节数组进行解密
     *
     * @param data 要解密的字节数组
     * @param key  解密所需的密钥
     * @param iv   解密所需的 IV
     * @return byte[]   解密后的字节数组
     */
    public static byte[] decryptCBC(byte[] data, byte[] key, byte[] iv, Padding padding) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = getCipherCBC(padding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }

    public static String encryptHexCBC(String data, String key, String iv, Padding padding) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return Hex.toHexString(encryptCBC(
                data.getBytes(StandardCharsets.UTF_8),
                Hex.decode(key),
                Hex.decode(iv),
                padding
        ));
    }

    public static String decryptHexCBC(String data, String key, String iv, Padding padding) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return new String(decryptCBC(
                Hex.decode(data),
                Hex.decode(key),
                Hex.decode(iv),
                padding
        ), StandardCharsets.UTF_8);
    }

    public static String encryptBase64CBC(String data, String key, String iv, Padding padding) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return BASE64_ENCODER.encodeToString(encryptCBC(
                data.getBytes(StandardCharsets.UTF_8),
                BASE64_DECODER.decode(key),
                BASE64_DECODER.decode(iv),
                padding
        ));
    }

    public static String decryptBase64CBC(String data, String key, String iv, Padding padding) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return new String(decryptCBC(
                BASE64_DECODER.decode(data),
                BASE64_DECODER.decode(key),
                BASE64_DECODER.decode(iv),
                padding
        ), StandardCharsets.UTF_8);
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        final Padding padding = Padding.PKCS5;
        final String plainText = "123456";
//        final byte[] iv = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
//
        final String key0 = genKeyAsBase64();
//        final String key0 = genKeyAsHex();
        logger.debug("\n密钥 : {}", key0);

        String encrypt0 = encryptBase64ECB(plainText, key0, padding);
        String decrypt0 = decryptBase64ECB(encrypt0, key0, padding);
        logger.debug("\n加密 : {}\n解密 : {}", encrypt0, decrypt0);

//        String encrypt1 = encryptBase64_CBC(plainText, key0, BASE64_ENCODER.encodeToString(iv), padding);
//        String decrypt1 = decryptBase64_CBC(encrypt1, key0, BASE64_ENCODER.encodeToString(iv), padding);
//        log.debug("\n加密 : {}\n解密 : {}", encrypt1, decrypt1);
//
//        final String key1 = genKeyAsHex();
//        log.debug("\n密钥 : {}", key1);
//
//        String encrypt2 = encryptHex_ECB(plainText, key1, padding);
//        String decrypt2 = decryptHex_ECB(encrypt2, key1, padding);
//        log.debug("\n加密 : {}\n解密 : {}", encrypt2, decrypt2);
//
//        String encrypt3 = encryptHex_CBC(plainText, key1, Hex.toHexString(iv), padding);
//        String decrypt3 = decryptHex_CBC(encrypt3, key1, Hex.toHexString(iv), padding);
//        log.debug("\n加密 : {}\n解密 : {}", encrypt3, decrypt3);
    }
}
