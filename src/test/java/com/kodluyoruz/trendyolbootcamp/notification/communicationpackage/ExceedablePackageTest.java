package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

import com.kodluyoruz.trendyolbootcamp.exception.ExceedPackageQuota;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ExceedablePackageTest {

    @Test
    void it_should__use_when_quota_not_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(1, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        exceedablePackage.use();

        // Then
        assertThat(exceedablePackage.getUsedAmount()).isEqualTo(1);
    }

    @Test
    void it_should_use_over_package_when_quota_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(5, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        IntStream.range(0, 3).forEach(i -> exceedablePackage.use());


        // Then
        assertThat(exceedablePackage.isExceed()).isEqualTo(true);
        assertThat(exceedablePackage.getOverPackage().getUsedAmount()).isEqualTo(2);
    }

    @Test
    void it_should_not_use_over_package_when_over_package_quota_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(1, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        try {
            IntStream.range(0, 3).forEach(i -> exceedablePackage.use());
        } catch (ExceedPackageQuota ignore) {

        }

        // Then
        assertThat(exceedablePackage.isExceed()).isEqualTo(true);
        assertThat(exceedablePackage.getOverPackage().getUsedAmount()).isEqualTo(1);
        assertThat(((FixedPackage) exceedablePackage.getOverPackage()).isExceed()).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_when_over_package_is_null() {
        // Given

        // When
        boolean isIllegal = false;
        try {
            new ExceedablePackage(1, 1, null);
        } catch (IllegalArgumentException e) {
            isIllegal = true;
        }

        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_return_package_fee() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(5, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        exceedablePackage.use();

        // Then
        assertThat(exceedablePackage.getPrice()).isEqualTo(1);
    }

    @Test
    void it_should_return_package_fee_with_fixed_over_package_when_package_quota_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(5, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        IntStream.range(0, 3).forEach(i -> exceedablePackage.use());

        // Then
        assertThat(exceedablePackage.getPrice()).isEqualTo(2);
    }

    @Test
    void it_should_return_package_fee_with_fixed_over_package_when_over_package_quota_exceed() {
        // Given
        FixedPackage fixedPackage = new FixedPackage(1, 1);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, fixedPackage);

        // When
        try {
            IntStream.range(0, 3).forEach(i -> exceedablePackage.use());
        } catch (ExceedPackageQuota ignore) {

        }

        // Then
        assertThat(exceedablePackage.getPrice()).isEqualTo(2);
    }

    @Test
    void it_should_return_package_fee_with_flexible_over_package_when_package_quota_exceed() {
        // Given
        FlexiblePackage flexiblePackage = new FlexiblePackage(5);
        ExceedablePackage exceedablePackage = new ExceedablePackage(1, 1, flexiblePackage);

        // When
        IntStream.range(0, 3).forEach(i -> exceedablePackage.use());

        // Then
        assertThat(exceedablePackage.getPrice()).isEqualTo(11);
    }
}