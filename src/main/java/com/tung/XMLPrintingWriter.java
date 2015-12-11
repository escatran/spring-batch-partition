package com.tung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.util.List;

/**
 * @author tung.tranduy
 * @version 10/12/2015
 */
public class XMLPrintingWriter implements ItemWriter<List<File>> {

    private static Logger logger = LoggerFactory.getLogger(XMLPrintingWriter.class);

    @Override
    public void write(List<? extends List<File>> items) throws Exception {
        logger.info("Writing list of " + items.get(0).size() + " XML files.");
    }
}
