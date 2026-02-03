/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projeto.chamados.security;
import java.security.SecureRandom;
import java.util.Base64;
/**
 *
 * @author heitor
 */
public class TokenUtil {
    
     private static final SecureRandom random = new SecureRandom();

    public static String gerarToken() {
        byte[] bytes = new byte[48]; // 64 chars
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
