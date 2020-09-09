package com.kodluyoruz.trendyolbootcamp.notification.communicationpackage;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class FlexiblePackageTest {

    @Test
    void it_should_increase_used_amount() {
        // Given
        FlexiblePackage flexiblePackage = new FlexiblePackage(1);

        // When
        flexiblePackage.use();

        // Then
        assertThat(flexiblePackage.getUsedAmount()).isEqualTo(1);
    }

    @Test
    void it_should_return_one() {
        // Given

        FlexiblePackage flexiblePackage = new FlexiblePackage(1);

        // When
        flexiblePackage.use();
        double fee = flexiblePackage.getPrice();

        // Then
        assertThat(fee).isEqualTo(1);
    }

    @Test
    void it_should_return_zero() {
        // Given
        FlexiblePackage flexiblePackage = new FlexiblePackage(1);

        // When
        double fee = flexiblePackage.getPrice();

        // Then
        assertThat(fee).isEqualTo(0);
    }

    @Test
    void it_should_return_one_when_price_per_use_float() {
        // Given
        FlexiblePackage flexiblePackage = new FlexiblePackage(0.1);

        // When
        IntStream.range(0, 10).forEach(i -> flexiblePackage.use());

        double fee = flexiblePackage.getPrice();

        // Then
        assertThat(fee).isEqualTo(1);
    }

    @Test
    void it_should_return_zero_when_price_per_use_zero() {
        // Given
        FlexiblePackage flexiblePackage = new FlexiblePackage(0);

        // When
        IntStream.range(0, 10).forEach(i -> flexiblePackage.use());

        double fee = flexiblePackage.getPrice();

        // Then
        assertThat(fee).isEqualTo(0);
    }

    @Test
    void it_should_give_exception_price_zero() {
        // Given

        // When
        boolean isIllegal = false;
        try {
            new FlexiblePackage(0);
        } catch (IllegalArgumentException e) {
            isIllegal = true;
        }


        // Then
        assertThat(isIllegal).isEqualTo(true);
    }

    @Test
    void it_should_give_exception_price_smaller_than_zero() {
        // Given

        // When
        boolean isIllegal = false;
        try {
            new FlexiblePackage(-1);
        } catch (IllegalArgumentException e) {
            isIllegal = true;
        }


        // Then
        assertThat(isIllegal).isEqualTo(true);
    }
}