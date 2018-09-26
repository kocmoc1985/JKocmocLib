/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import java.io.File;

/**
 *
 * @author Administrator
 */
public class CompareFilesByLastModifyDate implements Comparable {

    private File f;

    public CompareFilesByLastModifyDate(File f) {
        this.f = f;
    }

    public File getFile() {
        return f;
    }

    @Override
    public int compareTo(Object o) {
        CompareFilesByLastModifyDate x = (CompareFilesByLastModifyDate) o;
        if (x.getFile().lastModified() < f.lastModified()) {
            return -1;
        } else if (x.getFile().lastModified() == f.lastModified()) {
            return 0;
        } else {
            return 1;
        }
    }
}
