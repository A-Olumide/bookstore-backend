package com.bs.bookStore.service.serviceImplementation;

import com.bs.bookStore.dto.PurchaseHistoryDto;
import com.bs.bookStore.entity.PurchaseHistory;
import com.bs.bookStore.repository.PurchaseHistoryRepository;
import com.bs.bookStore.service.PurchaseHistoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    private PurchaseHistoryRepository purchaseHistoryRepository;
    private ModelMapper modelMapper;

    @Override
    public Page<PurchaseHistoryDto> getAllPurchases(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<PurchaseHistory> purchaseHistoryPage = purchaseHistoryRepository.findAll(pageable);
        return purchaseHistoryPage.map(purchaseHistory -> modelMapper.map(purchaseHistory, PurchaseHistoryDto.class));
    }
}
