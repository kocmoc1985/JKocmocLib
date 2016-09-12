/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author KOCMOC
 */
public class MyStreams {
    
    public static void keep_in_mind_IOUtils(){
//        IOUtils.copyLarge(null, null);
        
    }
    
    /**
     * @tags object to input stream
     * @param buf
     * @return 
     */
    public static InputStream get_input_stream_from_object(byte[] buf){
        return new ByteArrayInputStream(buf);
    }
    
    public static byte[] object_to_byte_array(Object o) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(o);
        byte[] object_bytes = bos.toByteArray();
        return object_bytes;
    }
    
}
