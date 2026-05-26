package br.com.summit.school.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encriptador {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashcode = encoder.encode("admin");
        System.out.println(hashcode);
    }
}
