package de.caterwings.catering;

import de.caterwings.catering.dto.CateringProductRequest;
import de.caterwings.catering.dto.CateringProductResponse;
import de.caterwings.catering.entity.CateringProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AbstractCateringTest {

    protected CateringProductEntity getCateringProductEntity() {
        CateringProductEntity entity = new CateringProductEntity();
        entity.setId("5cfe299a9e6c8057c12dde64");
        entity.setTitle("title");
        entity.setVendorUid("5cfe299a9e6c8057c12dde63");
        entity.setPrice(100d);
        entity.setDietaryFlag("vegan");
        entity.setDescription("this is test product");
        entity.setImage("5cfea543e2507016ba4d6ebd");
        entity.setViews(3);
        return entity;
    }

    protected CateringProductRequest getCateringProductRequest() {
        CateringProductRequest request = new CateringProductRequest();
        BeanUtils.copyProperties(getCateringProductEntity(), request);
        return request;
    }

    protected CateringProductResponse getCateringProductResponse() {
        CateringProductResponse response = new CateringProductResponse();
        BeanUtils.copyProperties(getCateringProductEntity(), response);
        return response;
    }

    protected Resource getImageResource() {
        return new ClassPathResource("/image.jpeg", Application.class);
    }
}
