package com.hobro11.shopping.sales;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.hobro11.shopping.sales.repository.SaleOptionRepo;
import com.hobro11.shopping.sales.service.SaleOptionWriter;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.dto.SaleOptionCreateDto;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;

@Transactional
@SpringBootTest(classes = SalesTestConfig.class)
public class SaleOptionWriterTest {

    @Autowired
    private SaleOptionWriter saleOptionWriter;

    @Autowired
    private ShopPageWriter shopPageWriter;

    @Autowired
    private SaleOptionRepo saleOptionRepo;

    private GeometryFactory factory = new GeometryFactory();

    private Long shopPageId;

    private Long saleOptionId;

    private Point newPoint(double x, double y) {
        return factory.createPoint(new Coordinate(x, y));
    }

    @BeforeEach
    public void setUp() {
        ShopPageCreateDto shopPageDto = new ShopPageCreateDto(
                1L,
                "testTitle",
                URI.create("testImgUri"),
                "testDescription",
                newPoint(127.1234, 37.5678));
        shopPageId = shopPageWriter.createShopPage(shopPageDto);

        SaleOptionCreateDto dto = new SaleOptionCreateDto(
                "testName",
                shopPageId,
                "testDescription",
                SaleOptionStatus.ACTIVE,
                10);
        saleOptionId = saleOptionWriter.createSaleOption(dto);

        SaleOptionCreateDto dto2 = new SaleOptionCreateDto(
                "testName2",
                shopPageId,
                "testDescription",
                SaleOptionStatus.ACTIVE,
                10);
        saleOptionWriter.createSaleOption(dto2);
    }

    @Test
    public void create() {
        SaleOption saleOption = saleOptionRepo.findById(saleOptionId).get();
        assertThat(saleOption.getName()).isEqualTo("testName");
        assertThat(saleOption.getShopPage().getId()).isEqualTo(shopPageId);
        assertThat(saleOption.getDescription()).isEqualTo("testDescription");
        assertThat(saleOption.getStatus()).isEqualTo(SaleOptionStatus.ACTIVE);
        assertThat(saleOption.getPrice()).isEqualTo(10);
    }

    @Test
    public void delete() {
        saleOptionWriter.deleteSaleOption(saleOptionId);
        assertThat(saleOptionRepo.findById(saleOptionId)).isEmpty();
    }
}
