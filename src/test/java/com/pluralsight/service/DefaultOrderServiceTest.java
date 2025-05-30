package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;
import com.pluralsight.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultOrderServiceTest {

    @Mock
    private OrderRepository repo;

    @InjectMocks
    private DefaultOrderService service;

    @Test
    @DisplayName("Trow IllegalArgumentException when trying to save null order")
    void shouldThrowExceptionWhenSavingNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> service.saveOrder(null));
    }

    @Test
    @DisplayName("saveOrder with empty order should throw IllegalArgumentException")
    void shouldThrowExceptionWhenSavingEmptyOrder() {
        Order empty = new Order();
        empty.setItems(new TreeSet<>());
        assertThrows(IllegalArgumentException.class, () -> service.saveOrder(empty));
    }

    @Test
    @DisplayName("saveOrder with proper order should delegate to repository")
    void shouldSaveProperOrder() {
        CustomSandwich sandwich = new CustomSandwich();

        Order good = new Order();
        good.setItems(new TreeSet<>(Collections.singletonList(sandwich)));

        service.saveOrder(good);

        verify(repo).saveOrder(good);
    }
}
