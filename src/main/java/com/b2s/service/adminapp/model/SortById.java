package com.b2s.service.adminapp.model;

import java.util.Comparator;

/**
 * Created by spattabiraman on 12/20/2018.
 */
public class SortById implements Comparator<Product> {

    public int compare(Product o1, Product o2) {
        return o1.getProductId()
                .compareTo(o2.getProductId());
    }
}
