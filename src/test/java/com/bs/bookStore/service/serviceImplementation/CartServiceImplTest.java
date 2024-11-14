package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.CartItemCreationDto;
import com.bs.bookStore.dto.CartItemDto;
import com.bs.bookStore.entity.Book;
import com.bs.bookStore.entity.CartItem;
import com.bs.bookStore.entity.PurchaseHistory;
import com.bs.bookStore.exceptions.CartEmptyException;
import com.bs.bookStore.repository.BookRepository;
import com.bs.bookStore.repository.CartItemRepository;
import com.bs.bookStore.repository.PurchaseHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void viewCart_ShouldReturnListOfItems() {
        List<CartItem> cartItems = Arrays.asList(new CartItem(), new CartItem());

        when(cartItemRepository.findAll()).thenReturn(cartItems);

        when(modelMapper.map(any(CartItem.class), eq(CartItemDto.class))).thenReturn(new CartItemDto());

        List<CartItemDto> result = cartService.viewCart();

        assertEquals(2, result.size());
        verify(cartItemRepository, times(1)).findAll();
    }



    @Test
    void removeBookFromCart_ShouldThrowException_WhenCartIsEmpty() {
        when(cartItemRepository.count()).thenReturn(0L);

        Long cartItemId = 1L;

        assertThrows(CartEmptyException.class, () -> {
            cartService.removeBookFromCart(cartItemId);
        });

        verify(cartItemRepository, never()).deleteById(anyLong());
    }

    @Test
    void checkOut_ShouldMoveItemsToPurchaseHistory_WhenCartIsNotEmpty() {
        List<CartItem> cartItems = Arrays.asList(new CartItem(), new CartItem());

        when(cartItemRepository.findAll()).thenReturn(cartItems);

        cartService.checkOut();

        verify(purchaseHistoryRepository, times(2)).save(any(PurchaseHistory.class));
        verify(cartItemRepository, times(1)).deleteAll();
    }

}