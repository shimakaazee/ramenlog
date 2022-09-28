package com.jec.ramenlog.dto;

import com.jec.ramenlog.entity.Shop;
import com.jec.ramenlog.entity.ShopDescription;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShopDto extends Shop {

    private List<ShopDescription> descriptions = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
