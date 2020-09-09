package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

import com.kodluyoruz.trendyolbootcamp.exception.ExceedPackageQuota;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class FixedPackageTest {

    @Test
    void it_should_increase_used_amount() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(1, 1);

        // When
        fixedPackage.use();

        // Then
        assertThat(fixedPackage.getUsedAmount()).isEqualTo(1);
    }

    @Test
    void it_should_not_use_when_quota_is_zero() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(0, 1);

        // When
        boolean isExceed = false;
        try {
            fixedPackage.use();
        } catch (ExceedPackageQuota e) {
            isExceed = true;
        }

        // Then
        assertThat(isExceed).isEqualTo(true);
    }

    @Test
    void it_should_not_use_when_quota_is_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(2, 1);

        // When
        boolean isExceed = false;
        try {
            IntStream.range(0, 3).forEach(i -> fixedPackage.use());
        } catch (ExceedPackageQuota e) {
            isExceed = true;
        }

        // Then
        assertThat(isExceed).isEqualTo(true);
    }

    @Test
    void it_should_equal_two_when_quota_is_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(2, 1);

        // When
        try {
            IntStream.range(0, 3).forEach(i -> fixedPackage.use());
        } catch (ExceedPackageQuota ignored) {

        }

        // Then
        assertThat(fixedPackage.getUsedAmount()).isEqualTo(2);
    }

    @Test
    void it_should_return_true_when_quota_is_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(2, 1);

        // When
        try {
            IntStream.range(0, 3).forEach(i -> fixedPackage.use());
        } catch (ExceedPackageQuota ignored) {

        }

        // Then
        assertThat(fixedPackage.isExceed()).isEqualTo(true);
    }

    @Test
    void it_should_return_false_when_quota_not_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(10, 1);

        // When
        IntStream.range(0, 3).forEach(i -> fixedPackage.use());

        // Then
        assertThat(fixedPackage.isExceed()).isEqualTo(false);
    }

    @Test
    void it_should_return_fee_not_depend_used_amount() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(10, 1);

        // When
        IntStream.range(0, 3).forEach(i -> fixedPackage.use());


        // Then
        assertThat(fixedPackage.getPrice()).isEqualTo(1);
    }

    @Test
    void it_should_give_exception_price_zero() {
        // Given

        // When
        boolean isIllegal=false;
        try {
            new FixedPackage(10, 0);
        }catch (IllegalArgumentException e){
            isIllegal=true;
        }


        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_price_smaller_than_zero() {
        // Given

        // When
        boolean isIllegal=false;
        try {
            new FixedPackage(10, -1);
        }catch (IllegalArgumentException e){
            isIllegal=true;
        }


        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_quota_zero() {
        // Given

        // When
        boolean isIllegal=false;
        try {
            new FixedPackage(0, 1);
        }catch (IllegalArgumentException e){
            isIllegal=true;
        }

        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_quota_smaller_than_zero() {
        // Given

        // When
        boolean isIllegal=false;
        try {
            new FixedPackage(-1, 1);
        }catch (IllegalArgumentException e){
            isIllegal=true;
        }

        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

}