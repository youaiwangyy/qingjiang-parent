package com.qingjiang.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuppressWarnings("all")
public enum RSAEnum {
    SERVER("server",
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJNZ4z885EQnMWsk46ESprcNyJ2pKWM-MDOeRvmYS8-UvjEkoptrjuzRKgEp2yIR_4L1oahQC0fDdb0rnFHTlp67eAnxnQSVTUxA_6iCEBKD0SmPRkL_z823W_kRSlF-GJ7Otdr7eLKtahJG-_h-UqE1srsmRKUFDC022PvbvQg3AgMBAAECgYANGVaMTpt0O4-3GKZrsT7E0yLccSdOvT6HuDBTC-jhYTWvJLSX4G3qO4XiaJgkZTkzqSZf0g3OqV-UG4Zf99FR1IvVa_sQz4Ng1wly8_RHUatAnqj4bWJ5SxZg76EATrAqFPV7NuqvPkSZUO4yHT6QkkJgpqr9AHqa-FT65lckAQJBAMSHt9w2rcFkga2xIpcdJijn2IHM2knb0hGGuRzMk5RUHXQLKFS-8-tzDwkyKvE3iQW7VMnRE7elAzgDWz-yCwECQQC_8H9wVgvWzRfOLMbjg0Hd1JBU2jUFRVT0gHFYJurVnzWjc8OYUT8cfX2BepNPYgX1qLSeM_Sh3_AgwlO-I6s3AkEAl3DRoDLOP9k4CGUX94QxtJG7h4IYr5NhU7oSCPwnuMP8kkBKysr-YO-fpYFwD8KwpqcNFhsza28PhOkpNnL3AQJACeGpzfDAH0jjxtuLSb9CLtd4rsWlISZWn1fVfgJS6LiPnGCjWz4jj6XuRjI3T0juoZ0T32kxWPwccrNOeGWBtQJBAIA4uglOeLlZ6rBIFXamEpTgO7XvWslvUANqC5z9NRzqz3OdLq8foEkE7DRm2K5sMU6V6_X1aZBglzW98vSX-FM",
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTWeM_POREJzFrJOOhEqa3DcidqSljPjAznkb5mEvPlL4xJKKba47s0SoBKdsiEf-C9aGoUAtHw3W9K5xR05aeu3gJ8Z0ElU1MQP-oghASg9Epj0ZC_8_Nt1v5EUpRfhiezrXa-3iyrWoSRvv4flKhNbK7JkSlBQwtNtj7270INwIDAQAB",
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4l4RMz2kpuAb2MNZWRzPRcc3AMyoZhRunDInGYBAHaGeiMAVnanZrikGeepNzr6_WY5jgewdAHFP6Rretw2BAoJ8eYBgFPzVhUmNdfb6ORhNjVBeTjmy7kKXHfRXuKoq0o2w0AITaQbauS3c8yCsRV0jEGMpZyc5WEMDhL8zTUQIDAQAB"),

    ORDER("order",
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALiXhEzPaSm4BvYw1lZHM9FxzcAzKhmFG6cMicZgEAdoZ6IwBWdqdmuKQZ56k3Ovr9ZjmOB7B0AcU_pGt63DYECgnx5gGAU_NWFSY119vo5GE2NUF5OObLuQpcd9Fe4qirSjbDQAhNpBtq5LdzzIKxFXSMQYylnJzlYQwOEvzNNRAgMBAAECgYBy1rCUKKVAMyEBoLh3zKxMLQTYAfy7C7h8YKyGIdVXBnkSydVS1UT--nCAa7m4SyCVvDirz15fYEBOgBhdniMOgGp0vAGvl76zsbRVFX4BT-2-fkXXiQuzRtVMuLCzlMGJRxlXX6vBpzVZfymjCgM8pty8wrEPiKv5BHgFAr0ukQJBAPwX4rU6Q5E6oz23SO-G9x8DG0cOb5pBEpnnCLuFNRwiVoN9q2nq1gPi9KOowHyYVSc9M8IN7XGiXsyflHVQ4zcCQQC7c9Y8i3TRYKnvjTiifatokfYmX_pLZpQTv-c6vNLXLBQH2tpOmgIc-4dL0JTVxKwzgJp2vx8ngF4isfRUmlG3AkEAwJFGXoWJQ5D1TZmKn14Lu6bV2WuPmNeIbAPtycCxlzIrv_npTNlUbleZ5MJtdz-uHHFF7kBRVvJfVBO6hfsp9QJADKddJukjEZ882XbnA9EH6R8xRpXkB4L9K_ggW86zTOMk1wLnCbdLvTkdgPirk2KbXT6pLqroJ3p64Eyy-aMRMQJBAPjbGSAEzAgdEMGGRbYvVDO3WoxvitGl-LpOyfIdV4pXr0b0eOVBlUL-E5E3t6tLlB1l5fP0B1saNRainFfb46Y",
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4l4RMz2kpuAb2MNZWRzPRcc3AMyoZhRunDInGYBAHaGeiMAVnanZrikGeepNzr6_WY5jgewdAHFP6Rretw2BAoJ8eYBgFPzVhUmNdfb6ORhNjVBeTjmy7kKXHfRXuKoq0o2w0AITaQbauS3c8yCsRV0jEGMpZyc5WEMDhL8zTUQIDAQAB",
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTWeM_POREJzFrJOOhEqa3DcidqSljPjAznkb5mEvPlL4xJKKba47s0SoBKdsiEf-C9aGoUAtHw3W9K5xR05aeu3gJ8Z0ElU1MQP-oghASg9Epj0ZC_8_Nt1v5EUpRfhiezrXa-3iyrWoSRvv4flKhNbK7JkSlBQwtNtj7270INwIDAQAB");


    private String appKey;
    private String privateKey;
    private String publicKey;
    private String appPublicKey;


    public static RSAEnum parse(String key) {
        for (RSAEnum rsa : RSAEnum.values()) {
            if (rsa.appKey.equals(key)) {
                return rsa;
            }
        }
        return null;
    }


}
