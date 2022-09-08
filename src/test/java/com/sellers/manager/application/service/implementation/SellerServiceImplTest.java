package com.sellers.manager.application.service.implementation;

import com.sellers.manager.application.assembler.SellerAssembler;
import com.sellers.manager.application.dto.SellerDTO;
import com.sellers.manager.application.dto.SellerDataAndStatesDTO;
import com.sellers.manager.application.dto.SellerWithStatesDTO;
import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.entity.Seller;
import com.sellers.manager.application.gateway.SellerGateway;
import com.sellers.manager.application.service.gateway.RegionService;
import com.sellers.manager.application.service.implementation.SellerServiceImpl;
import com.sellers.manager.application.validator.SellerValidator;
import com.sellers.manager.userinterface.exception.NoContentException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class SellerServiceImplTest {

    @Mock
    SellerGateway sellerGateway;
    @Mock
    SellerValidator sellerValidator;
    @Mock
    SellerAssembler sellerAssembler;
    @Mock
    RegionService regionService;

    SellerServiceImpl sellerServiceImpl;

    Seller seller;
    SellerDTO sellerDTO;
    SellerWithStatesDTO sellerWithStatesDTO;
    SellerDataAndStatesDTO sellerDataAndStatesDTO;
    Region region;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sellerServiceImpl = spy(new SellerServiceImpl(sellerGateway, sellerValidator, sellerAssembler, regionService));

        seller = Seller.builder().build();
        sellerDTO = SellerDTO.builder().build();
        sellerWithStatesDTO = SellerWithStatesDTO.builder().build();
        sellerDataAndStatesDTO = SellerDataAndStatesDTO.builder().build();
        region = Region.builder().build();
    }

    @Test
    @DisplayName("Testing GET for all sellers list")
    @Tag("unit")
    void getAllSellers() {
        List<Seller> sellerList = new ArrayList<>();
        List<SellerDataAndStatesDTO> sellerDTOList = new ArrayList<>();
        sellerList.add(seller);
        sellerDTOList.add(sellerDataAndStatesDTO);

        Mockito.when(sellerGateway.getAllSellers()).thenReturn(sellerList);
        Mockito.when(sellerAssembler.toSellerDataAndStatesDTO(seller)).thenReturn(sellerDataAndStatesDTO);

        Assertions.assertEquals(sellerDTOList, sellerServiceImpl.getAllSellers());
        Mockito.verify(sellerServiceImpl, times(1)).getAllSellers();
        Mockito.verify(sellerGateway, times(1)).getAllSellers();
        Mockito.verify(sellerAssembler, atLeast(1)).toSellerDataAndStatesDTO(seller);
    }

    @Test
    @DisplayName("Testing getting seller by id")
    @Tag("unit")
    void getById() {
        Mockito.when(sellerGateway.getById(sellerDTO.getId())).thenReturn(Optional.ofNullable(seller));
        Mockito.when(sellerAssembler.toSellerWithStatesDTO(seller)).thenReturn(sellerWithStatesDTO);

        Assertions.assertEquals(sellerWithStatesDTO, sellerServiceImpl.getById(sellerDTO.getId()));
        Mockito.verify(sellerServiceImpl, times(1)).getById(sellerDTO.getId());
        Mockito.verify(sellerGateway, times(1)).getById(sellerDTO.getId());
        Mockito.verify(sellerAssembler, times(1)).toSellerWithStatesDTO(seller);
    }

    @Test
    @DisplayName("Testing exception on getById")
    @Tag("unit")
    void getByIdUnsuccessfully() {
        Optional<Seller> emptySeller = Optional.empty();
        Mockito.when(sellerGateway.getById(sellerDTO.getId())).thenReturn(emptySeller);

        Assertions.assertThrows(NoContentException.class, () -> sellerServiceImpl.getById(sellerDTO.getId()));
        Mockito.verify(sellerServiceImpl, times(1)).getById(sellerDTO.getId());
        Mockito.verify(sellerGateway, times(1)).getById(sellerDTO.getId());
        Mockito.verify(sellerAssembler, times(0)).toSellerWithStatesDTO(seller);
    }

    @Test
    @DisplayName("Testing creation of a seller")
    @Tag("unit")
    void createSeller() {
        List<Seller> sellerList = new ArrayList<>();
        sellerList.add(seller);

        Mockito.when(sellerGateway.getAllSellers()).thenReturn(sellerList);
        Mockito.doNothing().when(sellerValidator).sellerUniqueName(sellerList, sellerDTO);
        Mockito.when(regionService.getByName(sellerDTO.getRegion())).thenReturn(region);
        Mockito.when(sellerAssembler.toSeller(sellerDTO)).thenReturn(seller);
        Mockito.when(sellerGateway.save(seller)).thenReturn(seller);
        Mockito.when(sellerAssembler.toSellerWithStatesDTO(seller)).thenReturn(sellerWithStatesDTO);

        Assertions.assertEquals(sellerWithStatesDTO, sellerServiceImpl.createSeller(sellerDTO));
        Mockito.verify(sellerServiceImpl, times(1)).createSeller(sellerDTO);
        Mockito.verify(sellerValidator, times(1)).sellerUniqueName(sellerList, sellerDTO);
        Mockito.verify(sellerAssembler, times(1)).toSeller(sellerDTO);
        Mockito.verify(sellerGateway, times(1)).save(seller);
        Mockito.verify(sellerAssembler, times(1)).toSellerWithStatesDTO(seller);
    }


}