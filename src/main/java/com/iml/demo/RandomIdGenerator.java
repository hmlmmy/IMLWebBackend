package com.iml.demo;

import java.util.Random;

public class RandomIdGenerator {

    public static long generateRandomId() {
        // 设置ID长度
        int idLength = 8;

        // 生成随机ID
        Random random = new Random();
        long randomId = 0;

        for (int i = 0; i < idLength; i++) {
            // 生成一个随机数字，每次都在0-9之间
            int digit = random.nextInt(10);

            // 将随机数字追加到ID上
            randomId = randomId * 10 + digit;
        }

        return randomId;
    }

    public static void main(String[] args) {
        // 在注册时调用生成随机ID的方法
        long randomId = generateRandomId();
        System.out.println("Generated Random ID: " + randomId);
    }
}
