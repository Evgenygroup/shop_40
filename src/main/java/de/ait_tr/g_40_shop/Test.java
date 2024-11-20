package de.ait_tr.g_40_shop;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "1";
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
    }
}
