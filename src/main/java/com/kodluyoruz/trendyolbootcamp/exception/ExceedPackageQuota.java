package com.kodluyoruz.trendyolbootcamp.exception;

public class ExceedPackageQuota extends RuntimeException {
    public ExceedPackageQuota() {
        super("Package quota exceeded.");
    }
}
