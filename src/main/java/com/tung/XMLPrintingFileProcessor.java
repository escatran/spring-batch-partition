package com.tung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.io.File;
import java.util.List;

/**
 * @author tung.tranduy
 * @version 09/12/2015
 */
public class XMLPrintingFileProcessor implements ItemProcessor<List<File>, List<File>> {

    private static Logger logger = LoggerFactory.getLogger(XMLPrintingFileProcessor.class);


    @Override
    public List<File> process(List<File> xmlFiles) throws Exception {
        logger.info("Processing these files: " + xmlFiles);
        return null;
    }

}
