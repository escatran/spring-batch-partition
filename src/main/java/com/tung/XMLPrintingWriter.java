package com.tung;

import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.util.List;

/**
 * @author tung.tranduy
 * @version 10/12/2015
 */
public class XMLPrintingWriter implements ItemWriter<List<File>> {
    @Override
    public void write(List<? extends List<File>> items) throws Exception {

    }
}
