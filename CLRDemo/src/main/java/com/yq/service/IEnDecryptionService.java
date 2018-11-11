package com.yq.service;

/**
 * Simple to Introduction
 * className: IEnDecryptionService
 *
 * @author EricYang
 * @version 2018/11/10 23:11
 */
public interface IEnDecryptionService {
    public String encrypt(String plaintext);
    public String decrypt(String ciphertext);
}
