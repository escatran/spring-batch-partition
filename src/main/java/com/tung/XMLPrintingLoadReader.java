package com.tung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tung.tranduy
 * @version 09/12/2015
 */
public class XMLPrintingLoadReader implements ItemReader<List<File>> {

    private static Logger logger = LoggerFactory.getLogger(XMLPrintingLoadReader.class);
    private static int instanceCount = 0;
    private int readMethodInvocationCount = 0;
    private final int instanceId = instanceCount;

    private Map<String, String> chunk;

    public XMLPrintingLoadReader() {
        instanceCount++;
    }

    @Override
    public List<File> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        synchronized (this) {
            readMethodInvocationCount++;
        }
        logger.info("Thread " + Thread.currentThread().getName() + " is invoking method read() of com.tung.XMLPrintingLoadReader[" + instanceId + "] " + readMethodInvocationCount + " times.");
        List<File> fileList = new ArrayList<File>();
        for (Map.Entry<String, String> entry : chunk.entrySet()) {
//            logger.info("Thread " + Thread.currentThread().getName() + " is adding file " + entry.getValue() + " to process...");
            File file = new File(entry.getValue());
            fileList.add(file);
        }
        return fileList;
    }

    public Map<String, String> getChunk() {
        return chunk;
    }

    public void setChunk(Map<String, String> chunk) {
        this.chunk = chunk;
    }
}
