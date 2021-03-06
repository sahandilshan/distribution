/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.siddhi.distribution.core.impl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * CompressionUtil class that holds compression and decompression algorithms.
 */
public class CompressionUtil {

    public static byte[] compressGZIP(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(byteArrayOutputStream);
        gzip.write(data);
        gzip.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompressGZIP(byte[] data) throws IOException {
        byte[] uncompressedBytes = new byte[1000000];
        InputStream inputStream = new ByteArrayInputStream(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPInputStream gzip = new GZIPInputStream(inputStream);
        int len;
        while ((len = gzip.read(uncompressedBytes)) > 0) {
            out.write(uncompressedBytes, 0, len);
        }
        gzip.close();
        inputStream.close();
        return out.toByteArray();
    }
}
