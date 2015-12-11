package com.tung;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.util.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tung.tranduy
 * @version 09/12/2015
 */
public class FilePartitioner implements Partitioner {

    private String xmlFolderLocation;
    private int filePerThread;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Assert.hasLength(xmlFolderLocation);
        Map<String, ExecutionContext> executionContexts = new HashMap<String, ExecutionContext>();
        File xmlDir = new File(xmlFolderLocation);
        if (xmlDir.isDirectory()) {
            File[] files = xmlDir.listFiles();
            int fileCount = 0;
            int totalFileCount = 0;
            int partitionCount = 0;
            Map<String, String> chunk = new HashMap<String, String>();
            for (File file: files) {
                if (file.isFile()) {
                    if (fileCount < filePerThread && totalFileCount < files.length) {
                        totalFileCount++;
                        chunk.put(file.getName(), file.getAbsolutePath());
                        fileCount = chunk.size();
                    }
                    if (fileCount == filePerThread || totalFileCount == files.length) {
                        // load enough file to this partition.
                        // create a new partition by creating new ExecutionContext and new chunk
                        partitionCount++;
                        ExecutionContext stepExecutionContext = new ExecutionContext();
                        stepExecutionContext.put("chunk", chunk);
                        chunk = new HashMap<String, String>();
                        executionContexts.put(String.valueOf("partition" + partitionCount), stepExecutionContext);
                        fileCount = 0;
                    }
                }
            }
        }
        return executionContexts;
    }

    public String getXmlFolderLocation() {
        return xmlFolderLocation;
    }

    public void setXmlFolderLocation(String xmlFolderLocation) {
        this.xmlFolderLocation = xmlFolderLocation;
    }

    public int getFilePerThread() {
        return filePerThread;
    }

    public void setFilePerThread(int filePerThread) {
        this.filePerThread = filePerThread;
    }
}
