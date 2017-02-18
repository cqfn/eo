/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 eolang.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.compiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * Class comment.
 *
 * @author John Page (johnpagedev@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class EoResourceFiles {

    /**
     * Attribute comment.
     */
    private final String path;

    /**
     * Method comment.
     *
     * @param path P.
     */
    public EoResourceFiles(final String path) {
        this.path = path;
    }

    /**
     * Method comment.
     *
     * @return Something.
     * @throws IOException If.
     */
    public String formattedNames() throws IOException {
        final List<String> filenames = new ArrayList<>(0);
        try (
            BufferedReader br =
                new BufferedReader(
                    new InputStreamReader(new Resource(this.path).asStream())
                )
        ) {
            String resource;
            resource = br.readLine();
            while (resource != null) {
                if ("eo".equals(FilenameUtils.getExtension(resource))) {
                    filenames.add(resource);
                }
                resource = br.readLine();
            }
        }
        return String.join("\n ", filenames);
    }

    /**
     * Method comment.
     *
     * @param filename F.
     * @return Something.
     * @throws IOException If.
     */
    public String eolang(final String filename) throws IOException {
        return String.format(
            "\nEOLANG:\n%s",
            IOUtils.toString(
                new Resource(this.path + filename).asStream(),
                Charset.defaultCharset()
            ));
    }

    /**
     * Method comment.
     *
     * @param filename F.
     * @return Something.
     * @throws IOException If.
     */
    public String java(final String filename) throws IOException {
        final StringBuilder java = new StringBuilder(0);
        java.append("\nJAVA:\n");
        final Program program = new Program(
            IOUtils.toString(
                new Resource(this.path + filename).asStream(),
                Charset.defaultCharset()
            )
        );
        program.save(new StringOutput(java));
        java.append('\n');
        return java.toString();
    }
}
