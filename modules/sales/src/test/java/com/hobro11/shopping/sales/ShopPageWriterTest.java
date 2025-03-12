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

import com.hobro11.shopping.sales.repository.ShopPageRepo;
import com.hobro11.shopping.sales.service.ShopPageWriter;
import com.hobro11.shopping.sales.service.dto.ShopPageCreateDto;


@Transactional
@SpringBootTest(classes = SalesPostgreSqlTestConfig.class)
public class ShopPageWriterTest {

    @Autowired
    private ShopPageWriter shopPageWriter;

    @Autowired
    private ShopPageRepo shopPageRepo;

    private Long shopPageId;

    private GeometryFactory factory = new GeometryFactory();

    private Long memberId = 1L;

    private Point newPoint(double x, double y) {
        return factory.createPoint(new Coordinate(x, y));
    }

    @BeforeEach
    public void setUp() {

        ShopPageCreateDto dto = new ShopPageCreateDto(
                memberId,
                "testTitle",
                URI.create("testImgUri"),
                "testDescription",
                newPoint(127.1234, 37.5678));
        shopPageId = shopPageWriter.createShopPage(dto);
    }

    @Test
    public void create() {

        ShopPage shopPage = shopPageRepo.findById(shopPageId).get();
        assertThat(shopPage.getTitle()).isEqualTo("testTitle");
        assertThat(shopPage.getThumbnailUri()).isEqualTo(URI.create("testImgUri"));
        assertThat(shopPage.getDescription()).isEqualTo("testDescription");
        assertThat(shopPage.getLocation().getX()).isEqualTo(127.1234);
        assertThat(shopPage.getLocation().getY()).isEqualTo(37.5678);
        assertThat(shopPage.getStatus()).isEqualTo(ShopPageStatue.ACTIVE);
        assertThat(shopPage.getMemberId()).isEqualTo(memberId);
    }

    @Test
    public void update() {
        ShopPage shopPage = shopPageRepo.findById(shopPageId).get();

        shopPageWriter.updateTitle(shopPageId, "newTitle");
        shopPageWriter.updateThumbnailUri(shopPageId, URI.create("newImgUri"));
        shopPageWriter.updateDescription(shopPageId, "newDescription");
        shopPageWriter.updateStatus(shopPageId, ShopPageStatue.INACTIVE);
        shopPageWriter.updateLocation(shopPageId, newPoint(123.1234, 45.5678));

        shopPage = shopPageRepo.findById(shopPageId).get();
        assertThat(shopPage.getMemberId()).isEqualTo(memberId);
        assertThat(shopPage.getTitle()).isEqualTo("newTitle");
        assertThat(shopPage.getThumbnailUri()).isEqualTo(URI.create("newImgUri"));
        assertThat(shopPage.getDescription()).isEqualTo("newDescription");
        assertThat(shopPage.getStatus()).isEqualTo(ShopPageStatue.INACTIVE);
        assertThat(shopPage.getLocation().getX()).isEqualTo(123.1234);
        assertThat(shopPage.getLocation().getY()).isEqualTo(45.5678);
    }

    @Test
    public void delete() {
        shopPageWriter.deleteShopPage(shopPageId);
        assertThat(shopPageRepo.findById(shopPageId)).isEmpty();
    }

}
