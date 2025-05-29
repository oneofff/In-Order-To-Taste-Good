package com.pluralsight.repository;

import com.pluralsight.model.order.Order;
import com.pluralsight.utils.files.FileWriterUtils;

import java.time.format.DateTimeFormatter;

public class FileOrderRepository implements OrderRepository {

    private FileWriterUtils fileWriterUtils;
    private static final String ORDER_FILE_LOCATION = "receipts/";

    private FileOrderRepository() {
    }

    private static class SingletonHelper {
        private static final FileOrderRepository INSTANCE = new FileOrderRepository();
    }

    public static FileOrderRepository getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public void saveOrder(Order order) {
        if (fileWriterUtils == null) {
            fileWriterUtils = new FileWriterUtils(ORDER_FILE_LOCATION +
                    order.getOrderDate()
                            .format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")) + ".txt");
        }

        fileWriterUtils.writeLine(order.getCheckRepresentation());
    }
}
