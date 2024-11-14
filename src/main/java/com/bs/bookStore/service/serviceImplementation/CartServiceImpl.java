package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.CartItemCreationDto;
import com.bs.bookStore.dto.CartItemDto;
import com.bs.bookStore.entity.Book;
import com.bs.bookStore.entity.CartItem;
import com.bs.bookStore.entity.PurchaseHistory;
import com.bs.bookStore.exceptions.CartEmptyException;
import com.bs.bookStore.exceptions.ResourceNotFoundException;
import com.bs.bookStore.repository.BookRepository;
import com.bs.bookStore.repository.CartItemRepository;
import com.bs.bookStore.repository.PurchaseHistoryRepository;
import com.bs.bookStore.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartItemRepository cartItemRepository;
    private BookRepository bookRepository;
    private PurchaseHistoryRepository purchaseHistoryRepository;
    private ModelMapper modelMapper;



    @Override
    public CartItemDto addBookToCart(CartItemCreationDto cartItemCreationDto) {
        Book book = bookRepository.findByTitleIgnoreCase(cartItemCreationDto.getBookTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Book", "Title", cartItemCreationDto.getBookTitle()));
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(cartItemCreationDto.getQuantity());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return modelMapper.map(savedCartItem, CartItemDto.class);
    }

    @Override
    public List<CartItemDto> viewCart() {
        return cartItemRepository.findAll()
                .stream().map(cartItem -> modelMapper.map(cartItem, CartItemDto.class)).collect(Collectors.toList());

    }

    @Override
    public void removeBookFromCart(Long cartItemId) {
        if (cartItemRepository.count() == 0) {
            throw new CartEmptyException("Your cart is empty");
        }
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new ResourceNotFoundException("Item", "Id", String.valueOf(cartItemId));
        }
        cartItemRepository.deleteById(cartItemId);
    }

//    @Override
//    public void checkOut(String paymentMethod) {
//
//    }

    @Override
    @Transactional
    public void checkOut() {
        List<CartItem> cartItems = cartItemRepository.findAll();

        if (cartItems.isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }

        for (CartItem cartItem : cartItems) {
            PurchaseHistory purchase = new PurchaseHistory();
            purchase.setBook(cartItem.getBook());
            purchase.setQuantity(cartItem.getQuantity());
            purchase.setPurchaseDate(LocalDateTime.now());

            purchaseHistoryRepository.save(purchase);
        }
        cartItemRepository.deleteAll();

    }


}
