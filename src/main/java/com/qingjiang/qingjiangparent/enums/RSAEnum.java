package com.qingjiang.qingjiangparent.enums;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings("all")
public enum RSAEnum {

    ORDER_RSA("order", "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJX+kp6wjRlYkClHgpwrqrsxt6SyYbDi6HpeplsDLIk/tfkITJfHEvHXlaJWVeArCR9mLo/MRXQEzZvAkuGNQks6TNGljO1lCpVJj32J2ABrCx1R2PgPcxyDH8qyYckXeYyt83+k/2qlZRxr72VbLVaz8+GZajnjZABZR0ap74QxAgMBAAECgYB73LBpGPQ7tPW/EM1bd3fSg3jaKXAgqRA/VP/c8l8Jq7Hyxxaw03TifxuS5eaeagtrgW770jycVQcRElJ4rbTKsP1b+gc7BSOXnBFwS52X8+VH/BC9Wsbi+sJzWWvWpGhAFLmNYFXwvgHTcEcPM5YxuhOOgSoM7ByLeLXj4zCJwQJBAOTWNENBzB5hYhFDnZAJbOYjQSezSonfDAYCFmE7Ni39fppwJXDJ3aj2rPm/CT6WJ2N9dLSQDaWVUsmopW1bfrkCQQCnzIqIJP09HL0/Vu/5NiEwmJ5KW3IB2iP13Cy986lC+ltbA3j2A3OaemCVOVhbhB7C+3LWKEF4Z39ffKnP9DU5AkASlFWv9EKnYM6+ihfb3vgJg2lB2Ou/X+2w+zBn+o++kitaTKqtiqZFlqmaqWkNvNE74a69DkkdXY9YMiokQ955AkAp6nNUjQDhajNwBhSOCVKDJCyuYGd7RPWkrepTTPGkNIvrkVN/CN+w4A8OV7PVsIxKoqtOCeJI5Hmx7Z8xvkwpAkA2S+UHYVxE1i94RNy5TCpuIbdjcoNEZakKCOHtHkkK8g2SVY4FkbWia1f5/A/e53c/IlFQSEM1pTL1J/ljHPN1",     "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCV/pKesI0ZWJApR4KcK6q7MbeksmGw4uh6XqZbAyyJP7X5CEyXxxLx15WiVlXgKwkfZi6PzEV0BM2bwJLhjUJLOkzRpYztZQqVSY99idgAawsdUdj4D3Mcgx/KsmHJF3mMrfN/pP9qpWUca+9lWy1Ws/PhmWo542QAWUdGqe+EMQIDAQAB"),
    USER_RSA(  "user", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJc+7MYULiuIxOv0ZiTADD8JHYWG2jX4xi8Gov8NoNDZKuM7ylk1XAdbYFCbnGIUYiqPMbLU4+MJzaclhuqQB2Q7chIp0mI7hJFayItq1TOGiCNsOGVKrai15ZVIZysTPEdB1ZbBHCs8kLIM1KTWOgxkeHH4sUSswscXn5TGyTHPAgMBAAECgYAFFhyoXK1g0Wyt8r4spjjDNe9EOlDEV+VyV2rL4Q1zeJo0oiIf9eduZ+hJTbJoqf7TJ5WoSfiP/hhocp6i7M0SyJbWzuJgCJMwA4jVTLGnG1vtctuO5wFviQ7VJXtGyxNaD7bcr2ZXzqrJJ1PeSaiYPaGNgvSG2h1YWRC8l1QEYQJBANR9o6+VLpIDq8rZCRjKslVAF5bi6RDNNHiDsAeL7WXiELnPSPsQoLTKI1Boc9aC2yR1ynFB/7Sqv/+/NrrzL3MCQQC2NvDVKkdMNRjRWODsacXWVH8ymoH33OgaQvmo4T+6HMChAZx+m6mKugiu1Ip9wTF0xH8Miniy2BcTGrc+aGU1AkEAnP+vfz20SQIZXij5xa2OEdrnEv7YwSc9C4YQ+6JxZwOe5Aq5m3bvpmRrry9d1zKcA9ZcG05uReibL7TyFTM07QJAGVFU8SlosqwGAMCKsA6aUId5DPzu5hOppEx+B/sUMbXhBf4FUooqan4iyrf5bjOzvXe393QeBSuFOUBTCbbTqQJBALKN693/sOrJ7s9i7ynJqqyDifaEQprAcwQ/kIzh4Jmo/eimwXV9BPvnKcLOCQ72ycS+TP3Xm34vUgpzGTF3IBY=", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXPuzGFC4riMTr9GYkwAw/CR2Fhto1+MYvBqL/DaDQ2SrjO8pZNVwHW2BQm5xiFGIqjzGy1OPjCc2nJYbqkAdkO3ISKdJiO4SRWsiLatUzhogjbDhlSq2oteWVSGcrEzxHQdWWwRwrPJCyDNSk1joMZHhx+LFErMLHF5+UxskxzwIDAQAB");

    private String appKey;
    private String privateKey;
    private String publicKey;



    public static RSAEnum parse(String key) {
        for (RSAEnum rsa : RSAEnum.values()) {
            if (rsa.appKey.equals(key)) {
                return rsa;
            }
        }
        return null;
    }




}
