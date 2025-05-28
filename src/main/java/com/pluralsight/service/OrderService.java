package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.utils.files.FileWriterUtils;

import java.time.format.DateTimeFormatter;

public class OrderService {
    private FileWriterUtils fileWriterUtils;
    private static final String ORDER_FILE_LOCATION = "data";


    public void saveOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order details cannot be null or empty.");
        }

        if (fileWriterUtils == null) {
            fileWriterUtils = new FileWriterUtils(ORDER_FILE_LOCATION + "/" +
                    order.getOrderDate()
                            .format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")) + ".txt");
        }

        fileWriterUtils.writeLine(order.getCheckRepresentation());
    }

}
