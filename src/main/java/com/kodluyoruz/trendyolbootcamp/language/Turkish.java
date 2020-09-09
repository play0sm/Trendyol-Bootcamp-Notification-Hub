package com.kodluyoruz.trendyolbootcamp.language;

public class Turkish implements Language {
    @Override
    public void printExceedPackageQuota() {
        System.out.println("Paket kotası aşıldı.");
    }

    @Override
    public void printNoSubscriptionFound() {
        System.out.println("Abonelik bulunamadı.");
    }

    @Override
    public void printSubscriptionExist() {
        System.out.println("Abonelik zaten var.");
    }

    @Override
    public void printUserBlocked() {
        System.out.println("Kullanıcı blocklu.");
    }
}
